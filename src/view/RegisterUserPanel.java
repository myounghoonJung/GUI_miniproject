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
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Manager;
import vo.User;

public class RegisterUserPanel extends JPanel implements ActionListener, MouseListener, FocusListener, KeyListener {

	private JTextField txtFldRegisterId;
	private JPasswordField pwFldRegister;
	private JPasswordField pwFldRegister2;
	private JTextField txtFldRegisterName;
	private JTextField txtFldRegisterPhoneNo;
	private JLabel lblRegisterIdConfirm;
	private JLabel lblRegisterPwConfirm;
	private JLabel lblRegisterPw;
	private JLabel lblRegisterPw2;
	private JLabel lblRegisterPhoneNoOverlapped;
	private JLabel lblRegisterPwConfirm2;
	private JLabel lblRegisterName;
	private JLabel lblRegisterIdOverlapped;

	private boolean idValidity;
	private boolean idOverlapped;
	private boolean pwValidity;
	private boolean pwValidity2;
	private boolean nameValidity;
	private boolean phoneNoValidity;
	private boolean phoneNoOverlapped;
	private boolean phoneNoDigit;

	private Manager manager = new Manager();

	private Frame tempf;

	public RegisterUserPanel(Frame f) {
		tempf = f;
		setSize(780, 680);
		setLayout(null);

		JPanel registerMainPanel = new JPanel();
		registerMainPanel.setBackground(new Color(255, 215, 0));
		registerMainPanel.setBounds(0, 0, 780, 600);
		registerMainPanel.setLayout(null);

		// 회원가입 패널 타이틀
		JLabel lblRegisterTitle = new JLabel("회원가입");
		lblRegisterTitle.setFont(new Font("Lucida Grande", Font.BOLD, 75));
		lblRegisterTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterTitle.setBounds(229, 65, 321, 140);
		registerMainPanel.add(lblRegisterTitle);

		// 아이디 입력 필드
		txtFldRegisterId = new JTextField();
		txtFldRegisterId.setForeground(SystemColor.inactiveCaptionText);
		txtFldRegisterId.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldRegisterId.setText("아이디");
		txtFldRegisterId.setBounds(284, 229, 211, 26);
		txtFldRegisterId.setColumns(10);
		registerMainPanel.add(txtFldRegisterId);

		// 아이디 유효성 메세지 출력용
		lblRegisterIdConfirm = new JLabel("영대소문자 구분 / 숫자 가능");
		lblRegisterIdConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterIdConfirm.setBounds(165, 254, 450, 16);
		registerMainPanel.add(lblRegisterIdConfirm);

		// 패스워드 텍스트
		lblRegisterPw = new JLabel("패스워드");
		lblRegisterPw.setForeground(SystemColor.inactiveCaptionText);
		lblRegisterPw.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		lblRegisterPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterPw.setBounds(350, 287, 79, 16);
		registerMainPanel.add(lblRegisterPw);

		// 패스워드 입력 필드
		pwFldRegister = new JPasswordField();
		pwFldRegister.setBounds(284, 282, 211, 26);
		registerMainPanel.add(pwFldRegister);

		// 패스워드 유효성 메세지 출력용
		lblRegisterPwConfirm = new JLabel("영대소문자 구분 / 숫자, 특수문자 가능");
		lblRegisterPwConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterPwConfirm.setBounds(165, 307, 450, 16);
		registerMainPanel.add(lblRegisterPwConfirm);

		// 패스워드 확인 텍스트
		lblRegisterPw2 = new JLabel("패스워드 확인");
		lblRegisterPw2.setForeground(SystemColor.inactiveCaptionText);
		lblRegisterPw2.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		lblRegisterPw2.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterPw2.setBounds(342, 340, 96, 16);
		registerMainPanel.add(lblRegisterPw2);

		// 패스워드 확인 입력 필드
		pwFldRegister2 = new JPasswordField();
		pwFldRegister2.setBounds(284, 335, 211, 26);
		registerMainPanel.add(pwFldRegister2);

		// 회원 이름 입력 필드
		txtFldRegisterName = new JTextField();
		txtFldRegisterName.setForeground(SystemColor.inactiveCaptionText);
		txtFldRegisterName.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldRegisterName.setText("이름");
		txtFldRegisterName.setBounds(284, 388, 211, 26);
		txtFldRegisterName.setColumns(10);
		registerMainPanel.add(txtFldRegisterName);

		// 회원가입 버튼
		JButton btnRegister = new JButton("회원가입");
		btnRegister.setBounds(284, 494, 211, 29);
		btnRegister.setActionCommand("Register");
		registerMainPanel.add(btnRegister);

		// 패스워드 동일한지 알려주는 라벨
		lblRegisterPwConfirm2 = new JLabel("New label");
		lblRegisterPwConfirm2.setVisible(false);
		lblRegisterPwConfirm2.setForeground(new Color(0, 0, 0));
		lblRegisterPwConfirm2.setBounds(495, 287, 200, 16);
		registerMainPanel.add(lblRegisterPwConfirm2);

		// 이름 유효성 라벨
		lblRegisterName = new JLabel("New label");
		lblRegisterName.setVisible(false);
		lblRegisterName.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterName.setBounds(165, 413, 450, 16);
		registerMainPanel.add(lblRegisterName);

		// 회원 핸드폰번호 중복 확인 라벨
		lblRegisterPhoneNoOverlapped = new JLabel("new label");
		lblRegisterPhoneNoOverlapped.setVisible(false);
		lblRegisterPhoneNoOverlapped.setBounds(495, 446, 200, 16);
		registerMainPanel.add(lblRegisterPhoneNoOverlapped);

		// 회원 핸드폰번호 입력 필드
		txtFldRegisterPhoneNo = new JTextField();
		txtFldRegisterPhoneNo.setText("핸드폰번호를 입력해주세요. (- 포함)");
		txtFldRegisterPhoneNo.setForeground(SystemColor.inactiveCaptionText);
		txtFldRegisterPhoneNo.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldRegisterPhoneNo.setBounds(284, 441, 211, 26);
		txtFldRegisterPhoneNo.setColumns(10);
		registerMainPanel.add(txtFldRegisterPhoneNo);

		// 아이디 중복 확인 안내 라벨
		lblRegisterIdOverlapped = new JLabel("New label");
		lblRegisterIdOverlapped.setVisible(false);
		lblRegisterIdOverlapped.setBounds(495, 234, 200, 16);
		registerMainPanel.add(lblRegisterIdOverlapped);

		// 로그인 서브 패널
		JPanel registerSubPanel = new JPanel();
		registerSubPanel.setBackground(new Color(255, 218, 185));
		registerSubPanel.setBounds(0, 600, 780, 80);
		registerSubPanel.setLayout(null);

		// 뒤로가기
		JButton btnBack = new JButton("메인으로");
		btnBack.setBounds(315, 25, 150, 29);
		registerSubPanel.add(btnBack);
		btnBack.setActionCommand("Back");

		// 아이디 입력필드 리스너
		txtFldRegisterId.addKeyListener(this); // 아이디 유효성 검사
		txtFldRegisterId.addFocusListener(this); // 아이디 입력필드에 포커스온 되면

		// 패스워드 입력필드 리스너
		pwFldRegister.addKeyListener(this); // 패스워드 유효성 검사
		pwFldRegister.addFocusListener(this); // 패스워드 입력필드 포커스 온
		lblRegisterPw.addMouseListener(this); // 패스워드 클릭 시

		// 패스워드 입력필드2 리스너
		pwFldRegister2.addKeyListener(this); // 패스워드 동일한지 검사
		pwFldRegister2.addFocusListener(this); // 패스워드2 입력필드 포커스 온
		lblRegisterPw2.addMouseListener(this); // 패스워드2 클릭 시

		// 이름 입력필드 리스너
		txtFldRegisterName.addFocusListener(this); // 이름 입력필드에 포커스온
		txtFldRegisterName.addKeyListener(this); // 이름 유효성

		// 핸드폰번호 입력필드 리스너
		txtFldRegisterPhoneNo.addKeyListener(this);
		txtFldRegisterPhoneNo.addFocusListener(this);

		// 회원가입 버튼 리스너
		btnRegister.addActionListener(this);

		btnBack.addActionListener(this);

		add(registerMainPanel);
		add(registerSubPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 회원가입 버튼
		if (e.getActionCommand() == "Register") {
			if (idValidity && !idOverlapped && !txtFldRegisterId.getText().equals("")
					&& !txtFldRegisterId.getText().equals("아이디") && pwValidity2 && nameValidity && phoneNoValidity
					&& !phoneNoOverlapped) {
				System.out.println("회원가입");
				String pw = "";
				for (int i = 0; i < pwFldRegister.getPassword().length; i++) {
					pw += pwFldRegister.getPassword()[i];
				}
				User registerUser = new User(Frame.getUserList().size() + 1, txtFldRegisterId.getText(), pw,
						txtFldRegisterName.getText(), txtFldRegisterPhoneNo.getText());
				Frame.getUserList().add(registerUser);
				manager.userDataSave();
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "회원가입 완료", JOptionPane.OK_OPTION);
				Manager.changePanel(tempf, RegisterUserPanel.this, new LoginPanel(tempf));
				for (int i = 0; i < Frame.getUserList().size(); i++) {
					System.out.println(Frame.getUserList().get(i));
				}
			} else {
				System.out.println("회원가입 불가!");
				JOptionPane.showMessageDialog(null, "입력하신 정보를 다시 확인해주세요.", "회원가입 실패", JOptionPane.ERROR_MESSAGE);
			}
		} // end of "Register"
			// 뒤로가기 버튼
		else if (e.getActionCommand() == "Back") {
			Manager.changePanel(tempf, RegisterUserPanel.this, new LoginPanel(tempf));
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 패스워드 텍스트 클릭 시
		if (e.getSource() == lblRegisterPw) {
			lblRegisterPw.setVisible(false);
			pwFldRegister.requestFocus();
		}
		// 패스워드 확인 텍스트 클릭 시
		else if (e.getSource() == lblRegisterPw2) {
			lblRegisterPw2.setVisible(false);
			pwFldRegister2.requestFocus();
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
		// 아이디 입력필드에 포커스온 되면
		if (e.getSource() == txtFldRegisterId) {
			if (txtFldRegisterId.getText().equals("아이디")) {
				txtFldRegisterId.setText("");
				txtFldRegisterId.setForeground(Color.BLACK);
				txtFldRegisterId.setHorizontalAlignment(SwingConstants.LEADING);
			}
		}
		// 패스워드 입력필드에 포커스온 되면
		else if (e.getSource() == pwFldRegister) {
			lblRegisterPw.setVisible(false);
		}
		// 패스워드 확인 입력필드에 포커스온 되면
		else if (e.getSource() == pwFldRegister2) {
			lblRegisterPw2.setVisible(false);
		}
		// 이름 입력 필드에 포커스온 되면
		else if (e.getSource() == txtFldRegisterName) {
			if (txtFldRegisterName.getText().equals("이름")) {
				txtFldRegisterName.setText("");
				txtFldRegisterName.setForeground(Color.BLACK);
				txtFldRegisterName.setHorizontalAlignment(SwingConstants.LEADING);
			}
		}
		// 핸드폰번호 입력 필드에 포커스온 되면
		else if (e.getSource() == txtFldRegisterPhoneNo) {
			if (txtFldRegisterPhoneNo.getText().equals("핸드폰번호를 입력해주세요. (- 포함)")) {
				txtFldRegisterPhoneNo.setText("");
				txtFldRegisterPhoneNo.setForeground(Color.BLACK);
				txtFldRegisterPhoneNo.setHorizontalAlignment(SwingConstants.LEADING);
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// 빈칸이면서 아이디 입력필드에 포커스 아웃
		if (e.getSource() == txtFldRegisterId) {
			if (txtFldRegisterId.getText().equals("")) {
				txtFldRegisterId.setText("아이디");
				txtFldRegisterId.setForeground(SystemColor.inactiveCaptionText);
				txtFldRegisterId.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		// 빈칸이면서 패스워드 입력필드에 포커스 아웃
		else if (e.getSource() == pwFldRegister) {
			if (pwFldRegister.getPassword().length == 0) {
				lblRegisterPw.setVisible(true);
			}
		}
		// 빈칸이면서 패스워드 확인 입력필드에 포커스 아웃
		else if (e.getSource() == pwFldRegister2) {
			if (pwFldRegister2.getPassword().length == 0) {
				lblRegisterPw2.setVisible(true);
			}
		}
		// 빈칸이면서 이름 입력 필드에 포커스 아웃
		else if (e.getSource() == txtFldRegisterName) {
			if (txtFldRegisterName.getText().equals("")) {
				txtFldRegisterName.setText("이름");
				txtFldRegisterName.setForeground(SystemColor.inactiveCaptionText);
				txtFldRegisterName.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		// 빈칸이면서 핸드폰번호 입력 필드에 포커스 아웃
		else if (e.getSource() == txtFldRegisterPhoneNo) {
			if (txtFldRegisterPhoneNo.getText().equals("")) {
				txtFldRegisterPhoneNo.setText("핸드폰번호를 입력해주세요. (- 포함)");
				txtFldRegisterPhoneNo.setForeground(SystemColor.inactiveCaptionText);
				txtFldRegisterPhoneNo.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 아이디 영대소문자, 숫자 가능
		if (e.getSource() == txtFldRegisterId) {
			idValidity = true;
			idOverlapped = false;
			// 아이디 형식이 올바른지
			for (int i = 0; i < txtFldRegisterId.getText().length(); i++) {
				if (!Character.isUpperCase(txtFldRegisterId.getText().charAt(i))
						&& !Character.isLowerCase(txtFldRegisterId.getText().charAt(i))
						&& !Character.isDigit(txtFldRegisterId.getText().charAt(i))) {
					idValidity = false;
				}
			}
			// 아이디 중복 검사
			for (int i = 0; i < Frame.getUserList().size(); i++) {
				if (txtFldRegisterId.getText().equals(Frame.getUserList().get(i).getUserId())) {
					idOverlapped = true;
				}
			}

			// 아이디가 빈칸이 아니고 중복이 아니고 유효하면
			if (!txtFldRegisterId.getText().equals("")) {
				if (!idOverlapped) {
					if (idValidity) {
						lblRegisterIdOverlapped.setVisible(true);
						lblRegisterIdOverlapped.setText("사용가능한 아이디입니다.");
						lblRegisterIdOverlapped.setForeground(Color.blue);
					} else
						lblRegisterIdOverlapped.setVisible(false);
				}
				// 아이디가 중복이면
				else {
					lblRegisterIdOverlapped.setVisible(true);
					lblRegisterIdOverlapped.setText("아이디가 중복되었습니다.");
					lblRegisterIdOverlapped.setForeground(Color.red);
				}
			} else
				lblRegisterIdOverlapped.setVisible(false);

			// 아이디가 유효하지 않으면
			if (!idValidity) {
				lblRegisterIdConfirm.setText("아이디는 영문 대소문자, 숫자만 가능합니다.");
				lblRegisterIdConfirm.setForeground(Color.red);
			}
			// 아이디가 유효하면
			else {
				lblRegisterIdConfirm.setText("영대소문자 구분 / 숫자 가능");
				lblRegisterIdConfirm.setForeground(Color.black);
			}

		} // end of txtFldRegisterId

		// 패스워드 영대소문자, 숫자, 특수문자 가능
		else if (e.getSource() == pwFldRegister) {
			pwValidity = true;
			for (int i = 0; i < pwFldRegister.getPassword().length; i++) {
				if (pwFldRegister.getPassword()[i] == ' ') {
					pwValidity = false;
				}
			}
			if (!pwValidity) {
				lblRegisterPwConfirm.setText("패스워드는 영문 대소문자, 숫자, 특수문자만 가능합니다.");
				lblRegisterPwConfirm.setForeground(Color.red);
			} else {
				lblRegisterPwConfirm.setText("영대소문자 구분 / 숫자, 특수문자 가능");
				lblRegisterPwConfirm.setForeground(Color.black);
			}

			// 패스워드 동일한지 판단
			if (pwFldRegister2.getPassword().length != 0) {
				if (pwFldRegister.getPassword().length == pwFldRegister2.getPassword().length) {
					pwValidity = true;
					for (int i = 0; i < pwFldRegister.getPassword().length; i++) {
						if (pwFldRegister.getPassword()[i] != pwFldRegister2.getPassword()[i])
							pwValidity = false;
					}
					if (pwValidity) {
						lblRegisterPwConfirm2.setVisible(true);
						lblRegisterPwConfirm2.setText("패스워드가 동일합니다.");
						lblRegisterPwConfirm2.setForeground(Color.blue);
						pwValidity2 = true;
					} else {
						lblRegisterPwConfirm2.setVisible(true);
						lblRegisterPwConfirm2.setText("패스워드가 다릅니다.");
						lblRegisterPwConfirm2.setForeground(Color.red);
						pwValidity2 = false;
					}
				} else {
					lblRegisterPwConfirm2.setVisible(true);
					lblRegisterPwConfirm2.setText("패스워드가 다릅니다.");
					lblRegisterPwConfirm2.setForeground(Color.red);
					pwValidity2 = false;
				}
			}
		} // end of pwFldRegister

		// 패스워드 동일한지 판단
		else if (e.getSource() == pwFldRegister2) {
			if (pwFldRegister.getPassword().length == pwFldRegister2.getPassword().length) {
				pwValidity = true;
				for (int i = 0; i < pwFldRegister.getPassword().length; i++) {
					if (pwFldRegister.getPassword()[i] != pwFldRegister2.getPassword()[i])
						pwValidity = false;
				}
				if (pwValidity) {
					lblRegisterPwConfirm2.setVisible(true);
					lblRegisterPwConfirm2.setText("패스워드가 동일합니다.");
					lblRegisterPwConfirm2.setForeground(Color.blue);
					pwValidity2 = true;
				} else {
					lblRegisterPwConfirm2.setVisible(true);
					lblRegisterPwConfirm2.setText("패스워드가 다릅니다.");
					lblRegisterPwConfirm2.setForeground(Color.red);
					pwValidity2 = false;
				}
			} else {
				lblRegisterPwConfirm2.setVisible(true);
				lblRegisterPwConfirm2.setText("패스워드가 다릅니다.");
				lblRegisterPwConfirm2.setForeground(Color.red);
				pwValidity2 = false;
			}
		} // end of pwFldRegister2

		// 이름 문자만 가능
		else if (e.getSource() == txtFldRegisterName) {
			nameValidity = true;
			for (int i = 0; i < txtFldRegisterName.getText().length(); i++) {
				if (!Character.isLetter(txtFldRegisterName.getText().charAt(i))) {
					nameValidity = false;
				}
			}
			if (nameValidity) {
				lblRegisterName.setVisible(false);
			} else {
				lblRegisterName.setVisible(true);
				lblRegisterName.setText("이름은 영문/한글만 가능합니다.");
				lblRegisterName.setForeground(Color.red);
			}
		} // end of txtFldRegisterName

		else if (e.getSource() == txtFldRegisterPhoneNo) {
			phoneNoValidity = false;
			phoneNoDigit = true;
			phoneNoOverlapped = false;
			// 핸드폰번호 중복 확인, 유효성 검사
			if (txtFldRegisterPhoneNo.getText().length() == 13) {
				for (int i = 0; i < txtFldRegisterPhoneNo.getText().length(); i++) {
					if (i == 3 || i == 8)
						continue;
					if (!Character.isDigit(txtFldRegisterPhoneNo.getText().charAt(i))) {
						System.out.println(i + ": " + txtFldRegisterPhoneNo.getText().charAt(i));
						phoneNoDigit = false;
					}
				}
				for (int i = 0; i < Frame.getUserList().size(); i++) {
					if (txtFldRegisterPhoneNo.getText().equals(Frame.getUserList().get(i).getUserPhoneNo())) {
						phoneNoOverlapped = true;
						lblRegisterPhoneNoOverlapped.setVisible(true);
						lblRegisterPhoneNoOverlapped.setForeground(Color.RED);
						lblRegisterPhoneNoOverlapped.setText("이미 가입된 번호입니다.");
					}
				}

				if (!phoneNoOverlapped && phoneNoDigit && txtFldRegisterPhoneNo.getText().charAt(3) == '-'
						&& txtFldRegisterPhoneNo.getText().charAt(8) == '-') {
					lblRegisterPhoneNoOverlapped.setVisible(true);
					lblRegisterPhoneNoOverlapped.setText("가입 가능한 번호입니다.");
					lblRegisterPhoneNoOverlapped.setForeground(Color.BLUE);
					phoneNoValidity = true;
				}
			} else {
				lblRegisterPhoneNoOverlapped.setVisible(true);
				lblRegisterPhoneNoOverlapped.setForeground(Color.RED);
				lblRegisterPhoneNoOverlapped.setText("핸드폰번호가 올바르지 않습니다.");
			}
		}

	}

}
