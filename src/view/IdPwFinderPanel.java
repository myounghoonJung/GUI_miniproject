package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Manager;
import vo.User;

public class IdPwFinderPanel extends JPanel implements ActionListener, FocusListener {

	private Frame tempf;
	private JTextField txtFldIdName;
	private JTextField txtFldIdPhoneNo;
	private JTextField txtFldPwName;
	private JTextField txtFldPwId;
	private JTextField txtFldPwPhoneNo;

	private boolean idFinderValidity;
	private boolean pwFinderValidity;

	private Manager manager = new Manager();

	public IdPwFinderPanel(Frame f) {
		tempf = f;
		setSize(780, 680);
		setLayout(null);

		// MainPanel
		JPanel finderMainPanel = new JPanel();
		finderMainPanel.setBackground(new Color(255, 215, 0));
		finderMainPanel.setBounds(0, 0, 780, 600);
		finderMainPanel.setLayout(null);

		JLabel lblIdFinder = new JLabel("아이디 찾기");
		lblIdFinder.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdFinder.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		lblIdFinder.setBounds(147, 124, 184, 44);
		finderMainPanel.add(lblIdFinder);

		txtFldIdName = new JTextField();
		txtFldIdName.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldIdName.setText("이름");
		txtFldIdName.setForeground(SystemColor.inactiveCaptionText);
		txtFldIdName.setBounds(439, 114, 200, 26);
		finderMainPanel.add(txtFldIdName);
		txtFldIdName.setColumns(10);

		txtFldIdPhoneNo = new JTextField();
		txtFldIdPhoneNo.setText("핸드폰번호 (- 포함)");
		txtFldIdPhoneNo.setForeground(SystemColor.inactiveCaptionText);
		txtFldIdPhoneNo.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldIdPhoneNo.setBounds(439, 152, 200, 26);
		finderMainPanel.add(txtFldIdPhoneNo);
		txtFldIdPhoneNo.setColumns(10);

		JLabel lblPwFinder = new JLabel("패스워드 찾기");
		lblPwFinder.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		lblPwFinder.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwFinder.setForeground(Color.BLACK);
		lblPwFinder.setBounds(147, 355, 184, 44);
		finderMainPanel.add(lblPwFinder);

		txtFldPwName = new JTextField();
		txtFldPwName.setForeground(SystemColor.inactiveCaptionText);
		txtFldPwName.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldPwName.setText("이름");
		txtFldPwName.setBounds(439, 369, 200, 26);
		finderMainPanel.add(txtFldPwName);
		txtFldPwName.setColumns(10);

		txtFldPwId = new JTextField();
		txtFldPwId.setForeground(SystemColor.inactiveCaptionText);
		txtFldPwId.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldPwId.setText("아이디");
		txtFldPwId.setBounds(439, 331, 200, 26);
		finderMainPanel.add(txtFldPwId);
		txtFldPwId.setColumns(10);

		txtFldPwPhoneNo = new JTextField();
		txtFldPwPhoneNo.setHorizontalAlignment(SwingConstants.CENTER);
		txtFldPwPhoneNo.setForeground(SystemColor.inactiveCaptionText);
		txtFldPwPhoneNo.setText("핸드폰번호 (- 포함)");
		txtFldPwPhoneNo.setBounds(439, 407, 200, 26);
		finderMainPanel.add(txtFldPwPhoneNo);
		txtFldPwPhoneNo.setColumns(10);

		JButton btnIdFinder = new JButton("아이디 찾기");
		btnIdFinder.setBounds(481, 190, 117, 29);
		btnIdFinder.setActionCommand("IdFinder");
		finderMainPanel.add(btnIdFinder);

		JButton btnPwFinder = new JButton("패스워드 찾기");
		btnPwFinder.setBounds(481, 445, 117, 29);
		btnPwFinder.setActionCommand("PwFinder");
		finderMainPanel.add(btnPwFinder);

		// SubPanel
		JPanel finderSubPanel = new JPanel();
		finderSubPanel.setBackground(new Color(255, 218, 185));
		finderSubPanel.setBounds(0, 600, 780, 80);
		finderSubPanel.setLayout(null);

		JButton btnBack = new JButton("메인으로");
		btnBack.setBounds(315, 25, 150, 29);
		btnBack.setActionCommand("Back");
		finderSubPanel.add(btnBack);

		// 리스너 등록
		txtFldIdName.addFocusListener(this);
		txtFldIdPhoneNo.addFocusListener(this);
		txtFldPwId.addFocusListener(this);
		txtFldPwName.addFocusListener(this);
		txtFldPwPhoneNo.addFocusListener(this);

		btnBack.addActionListener(this);
		btnIdFinder.addActionListener(this);
		btnPwFinder.addActionListener(this);

		add(finderMainPanel);
		add(finderSubPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Back") {
			Manager.changePanel(tempf, IdPwFinderPanel.this, new LoginPanel(tempf));
		} else if (e.getActionCommand() == "IdFinder") {
			idFinderValidity = false;
			for (int i = 0; i < Frame.getUserList().size(); i++) {
				if (txtFldIdName.getText().equals(Frame.getUserList().get(i).getUserName())
						&& txtFldIdPhoneNo.getText().equals(Frame.getUserList().get(i).getUserPhoneNo())) {
					JOptionPane.showMessageDialog(null, "아이디 : " + Frame.getUserList().get(i).getUserId(), "아이디 찾기 완료",
							JOptionPane.OK_OPTION);
					idFinderValidity = true;
				}
			}
			if (!idFinderValidity) {
				JOptionPane.showMessageDialog(null, "입력하신 정보를 다시 확인해주세요.", "아이디 찾기 실패", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getActionCommand() == "PwFinder") {
			pwFinderValidity = false;
			for (int i = 0; i < Frame.getUserList().size(); i++) {
				if (txtFldPwName.getText().equals(Frame.getUserList().get(i).getUserName())
						&& txtFldPwPhoneNo.getText().equals(Frame.getUserList().get(i).getUserPhoneNo())
						&& txtFldPwId.getText().equals(Frame.getUserList().get(i).getUserId())) {
					JOptionPane.showMessageDialog(null, "패스워드 : " + Frame.getUserList().get(i).getUserPw(),
							"패스워드 찾기 완료", JOptionPane.OK_OPTION);
					pwFinderValidity = true;
				}
			}
			if (!pwFinderValidity) {
				JOptionPane.showMessageDialog(null, "입력하신 정보를 다시 확인해주세요.", "패스워드 찾기 실패", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// 아이디 찾기 : 이름 입력필드에 포커스 온
		if (e.getSource() == txtFldIdName) {
			if (txtFldIdName.getText().equals("이름")) {
				txtFldIdName.setText("");
				txtFldIdName.setForeground(Color.BLACK);
				txtFldIdName.setHorizontalAlignment(SwingConstants.LEADING);
			}
		}
		// 아이디 찾기 : 핸드폰 입력필드에 포커스 온
		else if (e.getSource() == txtFldIdPhoneNo) {
			if (txtFldIdPhoneNo.getText().equals("핸드폰번호 (- 포함)")) {
				txtFldIdPhoneNo.setText("");
				txtFldIdPhoneNo.setForeground(Color.BLACK);
				txtFldIdPhoneNo.setHorizontalAlignment(SwingConstants.LEADING);
			}
		}
		// 패스워드 찾기 : 아이디 입력필드에 포커스 온
		else if (e.getSource() == txtFldPwId) {
			if (txtFldPwId.getText().equals("아이디")) {
				txtFldPwId.setText("");
				txtFldPwId.setForeground(Color.BLACK);
				txtFldPwId.setHorizontalAlignment(SwingConstants.LEADING);
			}
		}
		// 패스워드 찾기 : 이름 입력필드에 포커스 온
		else if (e.getSource() == txtFldPwName) {
			if (txtFldPwName.getText().equals("이름")) {
				txtFldPwName.setText("");
				txtFldPwName.setForeground(Color.BLACK);
				txtFldPwName.setHorizontalAlignment(SwingConstants.LEADING);
			}
		}
		// 패스워드 찾기 : 핸드폰번호 입력필드에 포커스 온
		else if (e.getSource() == txtFldPwPhoneNo) {
			if (txtFldPwPhoneNo.getText().equals("핸드폰번호 (- 포함)")) {
				txtFldPwPhoneNo.setText("");
				txtFldPwPhoneNo.setForeground(Color.BLACK);
				txtFldPwPhoneNo.setHorizontalAlignment(SwingConstants.LEADING);
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// 아아디 찾기 : 빈칸이면서 이름 입력필드에 포커스 아웃
		if (e.getSource() == txtFldIdName) {
			if (txtFldIdName.getText().equals("")) {
				txtFldIdName.setText("이름");
				txtFldIdName.setForeground(SystemColor.inactiveCaptionText);
				txtFldIdName.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		// 아아디 찾기 : 빈칸이면서 핸드폰번호 입력필드에 포커스 아웃
		else if (e.getSource() == txtFldIdPhoneNo) {
			if (txtFldIdPhoneNo.getText().equals("")) {
				txtFldIdPhoneNo.setText("핸드폰번호 (- 포함)");
				txtFldIdPhoneNo.setForeground(SystemColor.inactiveCaptionText);
				txtFldIdPhoneNo.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		// 패스워드 찾기 : 빈칸이면서 아이디 입력필드에 포커스 아웃
		else if (e.getSource() == txtFldPwId) {
			if (txtFldPwId.getText().equals("")) {
				txtFldPwId.setText("아이디");
				txtFldPwId.setForeground(SystemColor.inactiveCaptionText);
				txtFldPwId.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		// 패스워드 찾기 : 빈칸이면서 이름 입력필드에 포커스 아웃
		else if (e.getSource() == txtFldPwName) {
			if (txtFldPwName.getText().equals("")) {
				txtFldPwName.setText("이름");
				txtFldPwName.setForeground(SystemColor.inactiveCaptionText);
				txtFldPwName.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		// 패스워드 찾기 : 빈칸이면서 핸드폰번호 입력필드에 포커스 아웃
		else if (e.getSource() == txtFldPwPhoneNo) {
			if (txtFldPwPhoneNo.getText().equals("")) {
				txtFldPwPhoneNo.setText("핸드폰번호 (- 포함)");
				txtFldPwPhoneNo.setForeground(SystemColor.inactiveCaptionText);
				txtFldPwPhoneNo.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
	}

}
