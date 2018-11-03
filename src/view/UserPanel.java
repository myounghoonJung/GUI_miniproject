package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.Manager;
import vo.User;

public class UserPanel extends JPanel implements ActionListener {

	private Frame tempf;
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfPhone;
	private JTextField tfBorrowCount;
	private JTextField tfBorrowCountAll;
	private JTable table;

	private JButton btnInfoRevision;
	private JButton btnRevisionComplete;

	private Manager manager = new Manager();

	public UserPanel(Frame f) {
		tempf = f;

		setSize(780, 680);
		setLayout(null);

		// Panel 생성
		// MainPanel
		JPanel userMainPanel = new JPanel();
		userMainPanel.setBackground(new Color(255, 215, 0));
		userMainPanel.setBounds(0, 0, 780, 600);
		userMainPanel.setLayout(null);

		// SubPanel
		JPanel userSubPanel = new JPanel();
		userSubPanel.setBackground(new Color(255, 218, 185));
		userSubPanel.setBounds(0, 600, 780, 80);
		userSubPanel.setLayout(null);

		JLabel lblUser = new JLabel("회원 정보");
		lblUser.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setBounds(265, 47, 250, 50);
		userMainPanel.add(lblUser);

		JLabel lblId = new JLabel("아이디");
		lblId.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblId.setBounds(32, 143, 61, 16);
		userMainPanel.add(lblId);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(148, 139, 130, 26);
		userMainPanel.add(tfId);
		tfId.setColumns(10);

		JLabel lblName = new JLabel("이름");
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblName.setBounds(303, 143, 61, 16);
		userMainPanel.add(lblName);

		tfName = new JTextField();
		tfName.setEditable(false);
		tfName.setBounds(400, 139, 130, 26);
		userMainPanel.add(tfName);
		tfName.setColumns(10);

		JLabel lblPhone = new JLabel("핸드폰번호");
		lblPhone.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPhone.setBounds(548, 143, 74, 16);
		userMainPanel.add(lblPhone);

		tfPhone = new JTextField();
		tfPhone.setEditable(false);
		tfPhone.setBounds(621, 139, 130, 26);
		userMainPanel.add(tfPhone);
		tfPhone.setColumns(10);

		JLabel lblBorrowCount = new JLabel("현재 대출 권수");
		lblBorrowCount.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblBorrowCount.setBounds(32, 191, 104, 16);
		userMainPanel.add(lblBorrowCount);

		tfBorrowCount = new JTextField();
		tfBorrowCount.setEditable(false);
		tfBorrowCount.setBounds(148, 187, 130, 26);
		userMainPanel.add(tfBorrowCount);
		tfBorrowCount.setColumns(10);

		JLabel lblBorrowCountAll = new JLabel("총 대출 권수");
		lblBorrowCountAll.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblBorrowCountAll.setBounds(303, 191, 85, 16);
		userMainPanel.add(lblBorrowCountAll);

		tfBorrowCountAll = new JTextField();
		tfBorrowCountAll.setEditable(false);
		tfBorrowCountAll.setBounds(400, 187, 130, 26);
		userMainPanel.add(tfBorrowCountAll);
		tfBorrowCountAll.setColumns(10);

		JLabel lblBook = new JLabel("대출 정보");
		lblBook.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		lblBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblBook.setBounds(265, 287, 250, 50);
		userMainPanel.add(lblBook);

		Object[] colBook = { "도서번호", "도서명", "대출일", "반납예정일" };
		Object[][] rowBook = new Object[0][colBook.length];
		DefaultTableModel dtm = new DefaultTableModel(rowBook, colBook);
		table = new JTable(dtm);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(40, 365, 700, 200);
		userMainPanel.add(scroll);

		int i = 0;
		Object[][] row = new Object[Frame.getLoginUser().getBorrowBookList().size()][4];
		for (String key : Frame.getLoginUser().getBorrowBookList()) {
			row[i][0] = Frame.getBookList().get(key).getBookNo();
			row[i][1] = Frame.getBookList().get(key).getBookTitle();
			row[i][2] = Frame.getBookList().get(key).getBorrowDate();
			row[i][3] = Frame.getBookList().get(key).getReturnDate();
			dtm.addRow(row[i++]);
		}

		btnInfoRevision = new JButton("정보 수정");
		btnInfoRevision.setBounds(634, 187, 117, 29);
		btnInfoRevision.setActionCommand("InfoRevision");
		userMainPanel.add(btnInfoRevision);

		JButton btnLogout = new JButton("로그아웃");
		btnLogout.setBounds(331, 25, 117, 29);
		btnLogout.setActionCommand("Logout");
		userSubPanel.add(btnLogout);

		JButton btnBookSearch = new JButton("도서 검색");
		btnBookSearch.setBounds(202, 25, 117, 29);
		btnBookSearch.setActionCommand("BookSearch");
		userSubPanel.add(btnBookSearch);

		btnRevisionComplete = new JButton("수정 완료");
		btnRevisionComplete.setVisible(false);
		btnRevisionComplete.setBounds(634, 187, 117, 29);
		btnRevisionComplete.setActionCommand("RevisionComplete");
		userMainPanel.add(btnRevisionComplete);

		User u = Frame.getLoginUser();
		tfId.setText(u.getUserId());
		tfName.setText(u.getUserName());
		tfPhone.setText(u.getUserPhoneNo());
		tfBorrowCount.setText(u.getBorrowBookCount() + "");
		tfBorrowCountAll.setText(u.getBorrowBookHistoryCount() + "");
		System.out.println(u.getBorrowBookList());

		JButton btnBookReturn = new JButton("도서 반납");
		btnBookReturn.setBounds(623, 324, 117, 29);
		btnBookReturn.setActionCommand("BookReturn");
		userMainPanel.add(btnBookReturn);

		// 리스너
		btnLogout.addActionListener(this);
		btnBookSearch.addActionListener(this);
		btnInfoRevision.addActionListener(this);
		btnRevisionComplete.addActionListener(this);
		btnBookReturn.addActionListener(this);

		add(userMainPanel);

		add(userSubPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Logout")) {
			Manager.changePanel(tempf, UserPanel.this, new LoginPanel(tempf));
		} else if (e.getActionCommand().equals("InfoRevision")) {
			tfName.setEditable(true);
			tfPhone.setEditable(true);
			btnRevisionComplete.setVisible(true);
			btnInfoRevision.setVisible(false);
		} else if (e.getActionCommand().equals("RevisionComplete")) {
			tfName.setEditable(false);
			tfPhone.setEditable(false);
			btnRevisionComplete.setVisible(false);
			btnInfoRevision.setVisible(true);

			for (User u : Frame.getUserList()) {
				if (u.getUserId().equals(tfId.getText())) {
					u.setUserName(tfName.getText());
					u.setUserPhoneNo(tfPhone.getText());
				}
			}
			manager.userDataSave();
			JOptionPane.showMessageDialog(null, "정보 수정이 완료되었습니다.", "정보 수정 완료", JOptionPane.OK_OPTION);
		} else if (e.getActionCommand().equals("BookSearch")) {
			Manager.changePanel(tempf, UserPanel.this, new BorrowPanel(tempf));
		}

		// 도서 반납
		else if (e.getActionCommand().equals("BookReturn")) {
			try {
				int row = table.getSelectedRow();
				table.getValueAt(row, 0); // 에러띄우기용

				String returnUserId = Frame.getLoginUser().getUserId();

				boolean returnUserIdVal = false;
				boolean returnBookVal = false;

				for (String key : Frame.getBookList().keySet()) {
					if (key.equals(table.getValueAt(row, 0)) && !Frame.getBookList().get(key).isBorrowPossibility()) {
						returnBookVal = true;
						for (User u : Frame.getUserList()) {
							if (u.getUserId().equals(returnUserId)) {
								manager.setBookReturn(key, u);
								Frame.getLoginUser().getBorrowBookList().remove(key);
								Frame.getLoginUser()
										.setBorrowBookCount(Frame.getLoginUser().getBorrowBookList().size());
								returnUserIdVal = true;
								manager.bookDataSave();
								manager.userDataSave();
								Manager.changePanel(tempf, UserPanel.this, new UserPanel(tempf));
								break;
							}
						}
					}
				}

				if (!returnBookVal) {
					JOptionPane.showMessageDialog(null, "대출 중인 도서가 아닙니다.", "오류", JOptionPane.ERROR_MESSAGE);
				} else if (!returnUserIdVal) {
					JOptionPane.showMessageDialog(null, "아이디가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "테이블에서 반납할 도서를 선택해주세요", "오류", JOptionPane.ERROR_MESSAGE);
			}

		} // end of BookReturn
	}
}
