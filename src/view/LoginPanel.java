package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.jws.soap.SOAPBinding.Use;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import controller.Manager;
import vo.User;

public class LoginPanel extends JPanel implements ActionListener, MouseListener, FocusListener {

	private JTextField txtFldLoginId;
	private JPasswordField pwFldLogin;
	private JButton btnLogin;
	private JLabel lblLoginPw;

	private boolean loginValidity;

	private Manager manager = new Manager();

	// 관리자 계정
	private String adminId = "admin";
	private String adminPw = "admin";

	private Frame tempf;

	public LoginPanel(Frame f) {
		tempf = f;
		setSize(780, 680);
		setLayout(null);

		// 로그인 메인 패널
		JPanel loginMainPanel = new JPanel();
		loginMainPanel.setBackground(new Color(255, 215, 0));
		loginMainPanel.setBounds(0, 0, 780, 600);
		loginMainPanel.setLayout(null);

		JLabel lblLoginTitle = new JLabel("kh 도서관");
		lblLoginTitle.setForeground(Color.BLACK);
		lblLoginTitle.setBounds(178, 134, 423, 137);
		lblLoginTitle.setFont(new Font("Lucida Grande", Font.BOLD, 90));
		lblLoginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		loginMainPanel.add(lblLoginTitle);

		// 아이디 입력 텍스트필드
		txtFldLoginId = new JTextField();
		txtFldLoginId.setBounds(284, 378, 211, 26);
		txtFldLoginId.setForeground(SystemColor.inactiveCaptionText);
		txtFldLoginId.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldLoginId.setText("아이디");
		txtFldLoginId.setColumns(10);
		loginMainPanel.add(txtFldLoginId);

		// 패스워드필드 위에 보여줄 텍스트
		lblLoginPw = new JLabel("패스워드");
		lblLoginPw.setBounds(318, 421, 143, 16);
		lblLoginPw.setForeground(SystemColor.inactiveCaptionText);
		lblLoginPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginPw.setCursor(new Cursor(2));
		loginMainPanel.add(lblLoginPw);

		// 패스워드 입력 패스워드필드
		pwFldLogin = new JPasswordField();
		pwFldLogin.setBounds(284, 416, 211, 26);
		pwFldLogin.setToolTipText("");
		loginMainPanel.add(pwFldLogin);

		// 로그인 버튼
		btnLogin = new JButton("로그인");
		btnLogin.setBounds(279, 454, 221, 29);
		btnLogin.setActionCommand("Login");
		loginMainPanel.add(btnLogin);

		// 로그인 서브 패널
		JPanel loginSubPanel = new JPanel();
		loginSubPanel.setBackground(new Color(255, 218, 185));
		loginSubPanel.setBounds(0, 600, 780, 80);
		loginSubPanel.setLayout(null);

		JButton btnRegister = new JButton("회원가입");
		btnRegister.setActionCommand("RegisterPanel");
		btnRegister.setBounds(301, 25, 178, 29);
		loginSubPanel.add(btnRegister);

		JButton btnIdPwFinder = new JButton("아이디/패스워드 찾기");
		btnIdPwFinder.setBounds(111, 25, 178, 29);
		btnIdPwFinder.setActionCommand("IdPwFinderPanel");
		loginSubPanel.add(btnIdPwFinder);

		// 리스너 모음

		// 로그인버튼 누르게되면 로그인
		btnLogin.addActionListener(this);

		// 아이디입력칸에서 엔터를 치게되면 로그인
		txtFldLoginId.registerKeyboardAction(this, "Login", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED);

		// 패스워드입력칸에서 엔터를 치게되면 로그인
		pwFldLogin.registerKeyboardAction(this, "Login", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED);

		// "패스워드를 입력하세요."라는 텍스트를 클릭하면 텍스트가 사라지고 패스워드필드에 커서가 활성화됨.
		lblLoginPw.addMouseListener(this);

		// 아이디 텍스트필드와 패스워드 필드에 포커스 온
		txtFldLoginId.addFocusListener(this);
		pwFldLogin.addFocusListener(this);

		// 회원가입 버튼 누르면 회원가입 패널로 변경
		btnRegister.addActionListener(this);

		// 아이디/패스워드 찾기 패널로 변경
		btnIdPwFinder.addActionListener(this);

		add(loginMainPanel);
		add(loginSubPanel);
		f.getContentPane().add(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 로그인 버튼을 클릭하면 다음 화면으로 넘어감(패널 변경)
		if (e.getActionCommand() == "Login") {

			// 패스워드필드 -> 문자열 저장
			String pw = "";
			for (char c : pwFldLogin.getPassword()) {
				pw += c;
			}

			// 사용자 정보 찾기, 아이디/패스워드 일치하는 지 확인
			loginValidity = false;
			for (User u : Frame.getUserList()) {
				if (txtFldLoginId.getText().equals(u.getUserId()) && pw.equals(u.getUserPw())) {
					loginValidity = true;

					// 로그인된 유저정보 담기
					Frame.setLoginUser(u);
				}
			}

			// 관리자 로그인
			if (txtFldLoginId.getText().equals(adminId) && pw.equals(adminPw)) {
				JOptionPane.showMessageDialog(null, "관리자 계정으로 로그인합니다.", "관리자 로그인", JOptionPane.OK_OPTION);
				Manager.changePanel(tempf, LoginPanel.this, new AdminPanel(tempf));
			}
			// 사용자 로그인
			else if (loginValidity) {
				JOptionPane.showMessageDialog(null, Frame.getLoginUser().getUserName() + "님 환영합니다.", "사용자 로그인",
						JOptionPane.YES_OPTION);
				Manager.changePanel(tempf, LoginPanel.this, new UserPanel(tempf));
			} else if (!loginValidity) {
				JOptionPane.showMessageDialog(null, "아이디 또는 패스워드를 잘못 입력하셨습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
			}

		} // end of "login"

		// 회원가입 패널로 넘어감
		else if (e.getActionCommand() == "RegisterPanel") {
			Manager.changePanel(tempf, LoginPanel.this, new RegisterUserPanel(tempf));
		} else if (e.getActionCommand() == "IdPwFinderPanel") {
			Manager.changePanel(tempf, LoginPanel.this, new IdPwFinderPanel(tempf));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// "패스워드를 입력하세요."라는 텍스트를 클릭하면 텍스트가 사라지고 패스워드필드에 커서가 활성화됨.
		if (e.getSource() == lblLoginPw) {
			lblLoginPw.setVisible(false);
			pwFldLogin.requestFocus();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void focusGained(FocusEvent e) {
		// 아이디 입력필드에 포커스온되면 패스워드 텍스트 사라지기
		if (e.getSource() == txtFldLoginId) {
			if (txtFldLoginId.getText().equals("아이디")) {
				txtFldLoginId.setText("");
				txtFldLoginId.setForeground(Color.BLACK);
				txtFldLoginId.setHorizontalAlignment(SwingConstants.LEADING);
			}
		}
		// 패스워드 입력필드에 포커스온되면 패스워드 텍스트 사라지기
		else if (e.getSource() == pwFldLogin) {
			lblLoginPw.setVisible(false);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// 아이디 입력필드가 빈칸이고 포커스 아웃되면
		if (e.getSource() == txtFldLoginId) {
			if (txtFldLoginId.getText().equals("")) {
				txtFldLoginId.setText("아이디");
				txtFldLoginId.setForeground(SystemColor.inactiveCaptionText);
				txtFldLoginId.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		// 패스워드 필드가 빈칸이고 포커스 아웃되면
		else if (e.getSource() == pwFldLogin) {
			if (pwFldLogin.getPassword().length == 0) {
				lblLoginPw.setVisible(true);
			}
		}
	}
}
