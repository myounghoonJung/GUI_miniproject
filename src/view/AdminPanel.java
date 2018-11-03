package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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

public class AdminPanel extends JPanel implements ActionListener {

	private Frame tempf;
	private JTable tbUser;
	private JTextField tfUserSearch;
	private JTable tbBook;
	private JTextField tfBookSearch;
	private DefaultTableModel dtmUser;
	private DefaultTableModel dtmBook;
	private JComboBox<String> cbUserSearch;
	private JComboBox<String> cbBookSearch;

	private Manager manager = new Manager();

	public AdminPanel(Frame f) {
		tempf = f;
		setSize(780, 680);
		setLayout(null);

		// Panel 생성
		// MainPanel
		JPanel adminMainPanel = new JPanel();
		adminMainPanel.setBackground(new Color(255, 215, 0));
		adminMainPanel.setBounds(0, 0, 780, 600);
		adminMainPanel.setLayout(null);

		// SubPanel
		JPanel adminSubPanel = new JPanel();
		adminSubPanel.setBackground(new Color(255, 218, 185));
		adminSubPanel.setBounds(0, 600, 780, 80);
		adminSubPanel.setLayout(null);

		// component 생성
		// mainpanel
		// 회원
		JLabel lblUserList = new JLabel("회원 목록");
		lblUserList.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblUserList.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserList.setBounds(314, 17, 152, 25);
		adminMainPanel.add(lblUserList);

		Object[] colUser = { "아이디", "이름", "핸드폰번호", "대여중인 도서 권수" };
		Object[][] rowUser = new Object[0][colUser.length];
		dtmUser = new DefaultTableModel(rowUser, colUser);
		tbUser = new JTable(dtmUser);
		JScrollPane scrollUser = new JScrollPane(tbUser);
		scrollUser.setBounds(40, 115, 700, 160);
		adminMainPanel.add(scrollUser);

		// 테이블에 유저데이터 모두 넣기
		int i = 0;
		Object[][] row = new Object[Frame.getUserList().size()][4];
		for (User u : Frame.getUserList()) {
			row[i][0] = u.getUserId();
			row[i][1] = u.getUserName();
			row[i][2] = u.getUserPhoneNo();
			row[i][3] = u.getBorrowBookCount();
			dtmUser.addRow(row[i++]);
		}

		JButton btnUserSearch = new JButton("회원 조회");
		btnUserSearch.setBounds(623, 55, 117, 29);
		adminMainPanel.add(btnUserSearch);
		btnUserSearch.setActionCommand("UserSearch");

		cbUserSearch = new JComboBox<String>();
		cbUserSearch.setModel(new DefaultComboBoxModel<String>(new String[] { "전체 회원", "아이디", "이름", "핸드폰번호" }));
		cbUserSearch.setBounds(42, 56, 101, 27);
		adminMainPanel.add(cbUserSearch);

		tfUserSearch = new JTextField();
		tfUserSearch.setBounds(138, 56, 487, 26);
		adminMainPanel.add(tfUserSearch);
		tfUserSearch.setColumns(10);

		JButton btnUserDelete = new JButton("회원 삭제");
		btnUserDelete.setBounds(623, 85, 117, 29);
		adminMainPanel.add(btnUserDelete);
		btnUserDelete.setActionCommand("UserDelete");

		// 도서
		JLabel lblBookList = new JLabel("도서 목록");
		lblBookList.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblBookList.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookList.setBounds(316, 295, 150, 25);
		adminMainPanel.add(lblBookList);

		Object[] colBook = { "도서번호", "도서명", "저자", "출판사", "장르", "대출 여부" };
		Object[][] rowBook = new Object[0][colBook.length];
		dtmBook = new DefaultTableModel(rowBook, colBook);
		tbBook = new JTable(dtmBook);
		JScrollPane scrollBook = new JScrollPane(tbBook);
		scrollBook.setBounds(40, 402, 700, 180);
		adminMainPanel.add(scrollBook);

		int i2 = 0;
		Object[][] row2 = new Object[Frame.getBookList().size()][6];
		for (String key : Frame.getBookList().keySet()) {
			row2[i2][0] = Frame.getBookList().get(key).getBookNo();
			row2[i2][1] = Frame.getBookList().get(key).getBookTitle();
			row2[i2][2] = Frame.getBookList().get(key).getAuthor();
			row2[i2][3] = Frame.getBookList().get(key).getPublisher();
			row2[i2][4] = Frame.getBookList().get(key).getGenre();
			String borrowStatus;
			if (Frame.getBookList().get(key).isBorrowPossibility())
				borrowStatus = "대출 가능";
			else
				borrowStatus = "대출 중";
			row2[i2][5] = borrowStatus;
			dtmBook.addRow(row2[i2++]);
		}

		cbBookSearch = new JComboBox();
		cbBookSearch.setModel(
				new DefaultComboBoxModel(new String[] { "전체 도서", "도서번호", "도서명", "저자", "출판사", "장르", "대출 도서" }));
		cbBookSearch.setBounds(42, 334, 101, 27);
		adminMainPanel.add(cbBookSearch);

		tfBookSearch = new JTextField();
		tfBookSearch.setBounds(138, 333, 487, 26);
		adminMainPanel.add(tfBookSearch);
		tfBookSearch.setColumns(10);

		JButton btnBookSearch = new JButton("도서 조회");
		btnBookSearch.setBounds(623, 333, 117, 29);
		adminMainPanel.add(btnBookSearch);
		btnBookSearch.setActionCommand("BookSearch");

		JButton btnBookDelete = new JButton("도서 삭제");
		btnBookDelete.setBounds(623, 371, 117, 29);
		adminMainPanel.add(btnBookDelete);
		btnBookDelete.setActionCommand("BookDelete");

		JButton btnBookBorrow = new JButton("도서 대출");
		btnBookBorrow.setBounds(391, 371, 117, 29);
		adminMainPanel.add(btnBookBorrow);
		btnBookBorrow.setActionCommand("BookBorrow");

		JButton btnBookReturn = new JButton("도서 반납");
		btnBookReturn.setBounds(508, 371, 117, 29);
		btnBookReturn.setActionCommand("BookReturn");
		adminMainPanel.add(btnBookReturn);

		JButton btnBorrowInfo = new JButton("대출 정보");
		btnBorrowInfo.setBounds(508, 85, 117, 29);
		btnBorrowInfo.setActionCommand("BorrowInfo");
		adminMainPanel.add(btnBorrowInfo);

		// subpanel
		JButton btnLogout = new JButton("로그아웃");
		btnLogout.setBounds(315, 25, 150, 29);
		adminSubPanel.add(btnLogout);
		btnLogout.setActionCommand("Logout");

		JButton btnUserAdd = new JButton("회원 추가");
		btnUserAdd.setBounds(153, 25, 150, 29);
		adminSubPanel.add(btnUserAdd);
		btnUserAdd.setActionCommand("UserAdd");

		JButton btnBookAdd = new JButton("도서 추가");
		btnBookAdd.setBounds(477, 25, 150, 29);
		adminSubPanel.add(btnBookAdd);
		btnBookAdd.setActionCommand("BookAdd");

		// 리스너
		btnLogout.addActionListener(this);

		btnUserSearch.addActionListener(this);
		btnUserAdd.addActionListener(this);
		btnUserDelete.addActionListener(this);
		btnBorrowInfo.addActionListener(this);

		btnBookSearch.addActionListener(this);
		btnBookAdd.addActionListener(this);
		btnBookDelete.addActionListener(this);
		btnBookBorrow.addActionListener(this);
		btnBookReturn.addActionListener(this);

		add(adminMainPanel);
		add(adminSubPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 회원찾기버튼 누르면 테이블에 특정 회원 보여줌
		if (e.getActionCommand().equals("UserSearch")) {
			dtmUser.setRowCount(0);

			if (cbUserSearch.getSelectedItem().equals("전체 회원")) {
				for (User u : Frame.getUserList()) {
					Object[][] row = new Object[1][4];
					row[0][0] = u.getUserId();
					row[0][1] = u.getUserName();
					row[0][2] = u.getUserPhoneNo();
					row[0][3] = u.getBorrowBookCount();
					dtmUser.addRow(row[0]);
				}
			} // end of 전체회원
			else if (cbUserSearch.getSelectedItem().equals("아이디")) {
				for (User u : Frame.getUserList()) {
					if (u.getUserId().equals(tfUserSearch.getText())) {
						Object[][] row = new Object[1][4];
						row[0][0] = u.getUserId();
						row[0][1] = u.getUserName();
						row[0][2] = u.getUserPhoneNo();
						row[0][3] = u.getBorrowBookCount();
						dtmUser.addRow(row[0]);
					}
				}
			} // end of id
			else if (cbUserSearch.getSelectedItem().equals("이름")) {
				for (User u : Frame.getUserList()) {
					if (u.getUserName().equals(tfUserSearch.getText())) {
						Object[][] row = new Object[1][4];
						row[0][0] = u.getUserId();
						row[0][1] = u.getUserName();
						row[0][2] = u.getUserPhoneNo();
						row[0][3] = u.getBorrowBookCount();
						dtmUser.addRow(row[0]);
					}
				}
			} // end of name
			else if (cbUserSearch.getSelectedItem().equals("핸드폰번호")) {
				for (User u : Frame.getUserList()) {
					if (u.getUserPhoneNo().equals(tfUserSearch.getText())) {
						Object[][] row = new Object[1][4];
						row[0][0] = u.getUserId();
						row[0][1] = u.getUserName();
						row[0][2] = u.getUserPhoneNo();
						row[0][3] = u.getBorrowBookCount();
						dtmUser.addRow(row[0]);
					}
				}
			} // end of phoneNo
		} // end of UserSearch

		// 회원추가 버튼 누르면 회원가입패널로 넘어감 (어드민용으로 따로 만들어야할 듯)
		else if (e.getActionCommand().equals("UserAdd")) {

		}

		else if (e.getActionCommand().equals("BorrowInfo")) {
			try {
				int row = tbUser.getSelectedRow();
				for (User u : Frame.getUserList()) {
					if (u.getUserId().equals(dtmUser.getValueAt(row, 0))) {
						new BorrowInfo(u);
					}
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "테이블에서 삭제할 회원을 선택해주세요", "오류", JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
		}

		// 테이블에서 선택된 유저 삭제
		else if (e.getActionCommand().equals("UserDelete")) {
			try {
				int row = tbUser.getSelectedRow();
				for (User u : Frame.getUserList()) {
					if (u.getUserId().equals(dtmUser.getValueAt(row, 0))) {
						Frame.getUserList().remove(u);
						break; // ConcurrentModificationException 발생 억제
					}
				}
				manager.userDataSave();
				JOptionPane.showMessageDialog(null, "삭제를 완료했습니다.", "회원 삭제 완료", JOptionPane.OK_OPTION);
				Manager.changePanel(tempf, AdminPanel.this, new AdminPanel(tempf));
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "테이블에서 삭제할 회원을 선택해주세요", "오류", JOptionPane.ERROR_MESSAGE);
			}
		} // end of UserDelete

		// 도서 찾기
		else if (e.getActionCommand().equals("BookSearch")) {
			dtmBook.setRowCount(0);

			if (cbBookSearch.getSelectedItem().equals("전체 도서")) {
				for (String key : Frame.getBookList().keySet()) {
					Object[][] row = new Object[1][6];
					row[0][0] = Frame.getBookList().get(key).getBookNo();
					row[0][1] = Frame.getBookList().get(key).getBookTitle();
					row[0][2] = Frame.getBookList().get(key).getAuthor();
					row[0][3] = Frame.getBookList().get(key).getPublisher();
					row[0][4] = Frame.getBookList().get(key).getGenre();
					String borrowStatus;
					if (Frame.getBookList().get(key).isBorrowPossibility())
						borrowStatus = "대출 가능";
					else
						borrowStatus = "대출 중";
					row[0][5] = borrowStatus;
					dtmBook.addRow(row[0]);
				}
			} // end of 전체 도서
			else if (cbBookSearch.getSelectedItem().equals("도서번호")) {
				for (String key : Frame.getBookList().keySet()) {
					if (Frame.getBookList().get(key).getBookNo().equals(tfBookSearch.getText())) {
						Object[][] row = new Object[1][6];
						row[0][0] = Frame.getBookList().get(key).getBookNo();
						row[0][1] = Frame.getBookList().get(key).getBookTitle();
						row[0][2] = Frame.getBookList().get(key).getAuthor();
						row[0][3] = Frame.getBookList().get(key).getPublisher();
						row[0][4] = Frame.getBookList().get(key).getGenre();
						String borrowStatus;
						if (Frame.getBookList().get(key).isBorrowPossibility())
							borrowStatus = "대출 가능";
						else
							borrowStatus = "대출 중";
						row[0][5] = borrowStatus;
						dtmBook.addRow(row[0]);
					}
				}
			} // end of bookNo
			else if (cbBookSearch.getSelectedItem().equals("도서명")) {
				for (String key : Frame.getBookList().keySet()) {
					if (Frame.getBookList().get(key).getBookTitle().equals(tfBookSearch.getText())) {
						Object[][] row = new Object[1][6];
						row[0][0] = Frame.getBookList().get(key).getBookNo();
						row[0][1] = Frame.getBookList().get(key).getBookTitle();
						row[0][2] = Frame.getBookList().get(key).getAuthor();
						row[0][3] = Frame.getBookList().get(key).getPublisher();
						row[0][4] = Frame.getBookList().get(key).getGenre();
						String borrowStatus;
						if (Frame.getBookList().get(key).isBorrowPossibility())
							borrowStatus = "대출 가능";
						else
							borrowStatus = "대출 중";
						row[0][5] = borrowStatus;
						dtmBook.addRow(row[0]);
					}
				}
			} // end of bookTitle
			else if (cbBookSearch.getSelectedItem().equals("저자")) {
				for (String key : Frame.getBookList().keySet()) {
					if (Frame.getBookList().get(key).getAuthor().equals(tfBookSearch.getText())) {
						Object[][] row = new Object[1][6];
						row[0][0] = Frame.getBookList().get(key).getBookNo();
						row[0][1] = Frame.getBookList().get(key).getBookTitle();
						row[0][2] = Frame.getBookList().get(key).getAuthor();
						row[0][3] = Frame.getBookList().get(key).getPublisher();
						row[0][4] = Frame.getBookList().get(key).getGenre();
						String borrowStatus;
						if (Frame.getBookList().get(key).isBorrowPossibility())
							borrowStatus = "대출 가능";
						else
							borrowStatus = "대출 중";
						row[0][5] = borrowStatus;
						dtmBook.addRow(row[0]);
					}
				}
			} // end of Author
			else if (cbBookSearch.getSelectedItem().equals("출판사")) {
				for (String key : Frame.getBookList().keySet()) {
					if (Frame.getBookList().get(key).getPublisher().equals(tfBookSearch.getText())) {
						Object[][] row = new Object[1][6];
						row[0][0] = Frame.getBookList().get(key).getBookNo();
						row[0][1] = Frame.getBookList().get(key).getBookTitle();
						row[0][2] = Frame.getBookList().get(key).getAuthor();
						row[0][3] = Frame.getBookList().get(key).getPublisher();
						row[0][4] = Frame.getBookList().get(key).getGenre();
						String borrowStatus;
						if (Frame.getBookList().get(key).isBorrowPossibility())
							borrowStatus = "대출 가능";
						else
							borrowStatus = "대출 중";
						row[0][5] = borrowStatus;
						dtmBook.addRow(row[0]);
					}
				}
			} // end of Publisher
			else if (cbBookSearch.getSelectedItem().equals("장르")) {
				for (String key : Frame.getBookList().keySet()) {
					if (Frame.getBookList().get(key).getGenre().equals(tfBookSearch.getText())) {
						Object[][] row = new Object[1][6];
						row[0][0] = Frame.getBookList().get(key).getBookNo();
						row[0][1] = Frame.getBookList().get(key).getBookTitle();
						row[0][2] = Frame.getBookList().get(key).getAuthor();
						row[0][3] = Frame.getBookList().get(key).getPublisher();
						row[0][4] = Frame.getBookList().get(key).getGenre();
						String borrowStatus;
						if (Frame.getBookList().get(key).isBorrowPossibility())
							borrowStatus = "대출 가능";
						else
							borrowStatus = "대출 중";
						row[0][5] = borrowStatus;
						dtmBook.addRow(row[0]);
					}
				}
			} // end of Genre
			else if (cbBookSearch.getSelectedItem().equals("대출 도서")) {
				for (String key : Frame.getBookList().keySet()) {
					if (!Frame.getBookList().get(key).isBorrowPossibility()) {
						Object[][] row = new Object[1][6];
						row[0][0] = Frame.getBookList().get(key).getBookNo();
						row[0][1] = Frame.getBookList().get(key).getBookTitle();
						row[0][2] = Frame.getBookList().get(key).getAuthor();
						row[0][3] = Frame.getBookList().get(key).getPublisher();
						row[0][4] = Frame.getBookList().get(key).getGenre();
						String borrowStatus;
						if (Frame.getBookList().get(key).isBorrowPossibility())
							borrowStatus = "대출 가능";
						else
							borrowStatus = "대출 중";
						row[0][5] = borrowStatus;
						dtmBook.addRow(row[0]);
					}
				}
			} // end of Genre
		} // end of bookSearch

		// 도서 추가 패널로 이동
		else if (e.getActionCommand().equals("BookAdd")) {
			Manager.changePanel(tempf, AdminPanel.this, new BookAddPanel(tempf));
		} // end of BookAdd

		// 도서 대출
		else if (e.getActionCommand().equals("BookBorrow")) {
			try {
				int row = tbBook.getSelectedRow();
				dtmBook.getValueAt(row, 0); // 에러띄우기용

				String borrowUserId = JOptionPane.showInputDialog(null, "대출하는 회원의 아이디를 입력해주세요.", "도서 대출",
						JOptionPane.QUESTION_MESSAGE);

				boolean borrowUserIdVal = false;
				boolean borrowBookVal = false;

				for (String key : Frame.getBookList().keySet()) {
					if (key.equals(dtmBook.getValueAt(row, 0))) {
						if (Frame.getBookList().get(key).isBorrowPossibility()) {
							borrowBookVal = true;
							for (User u : Frame.getUserList()) {
								if (u.getUserId().equals(borrowUserId)) {
									manager.setBookBorrow(key, borrowUserId, u);
									borrowUserIdVal = true;
									break;
								}
							}
						}
					}
				}

				if (!borrowBookVal) {
					JOptionPane.showMessageDialog(null, "대출 중인 도서입니다.\n대출이 불가합니다.", "오류", JOptionPane.ERROR_MESSAGE);
				} else if (!borrowUserIdVal) {
					JOptionPane.showMessageDialog(null, "입력하신 아이디가 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				} else if (borrowBookVal && borrowUserIdVal) {
					manager.bookDataSave();
					manager.userDataSave();
					Manager.changePanel(tempf, AdminPanel.this, new AdminPanel(tempf));
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "테이블에서 대출할 도서를 선택해주세요", "오류", JOptionPane.ERROR_MESSAGE);
			}
		} // end of BookBorrow

		// 도서 반납
		else if (e.getActionCommand().equals("BookReturn")) {
			try {
				int row = tbBook.getSelectedRow();
				dtmBook.getValueAt(row, 0); // 에러띄우기용

				String returnUserId = JOptionPane.showInputDialog(null, "반납하는 회원의 아이디를 입력해주세요.", "도서 반납",
						JOptionPane.QUESTION_MESSAGE);

				boolean returnUserIdVal = false;
				boolean returnBookVal = false;

				for (String key : Frame.getBookList().keySet()) {
					if (key.equals(dtmBook.getValueAt(row, 0)) && !Frame.getBookList().get(key).isBorrowPossibility()) {
						returnBookVal = true;
						for (User u : Frame.getUserList()) {
							if (u.getUserId().equals(returnUserId)) {
								manager.setBookReturn(key, u);
								returnUserIdVal = true;
								break;
							}
						}
					}
				}

				if (!returnBookVal) {
					JOptionPane.showMessageDialog(null, "대출 중인 도서가 아닙니다.", "오류", JOptionPane.ERROR_MESSAGE);
				} else if (!returnUserIdVal) {
					JOptionPane.showMessageDialog(null, "아이디가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				} else if (returnBookVal && returnUserIdVal) {
					manager.bookDataSave();
					manager.userDataSave();
					Manager.changePanel(tempf, AdminPanel.this, new AdminPanel(tempf));
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "테이블에서 반납할 도서를 선택해주세요", "오류", JOptionPane.ERROR_MESSAGE);
			}

		} // end of BookReturn

		// 테이블에서 선택된 도서 삭제
		else if (e.getActionCommand().equals("BookDelete")) {
			try {
				int row = tbBook.getSelectedRow();
				for (String key : Frame.getBookList().keySet()) {
					if (key.equals(dtmBook.getValueAt(row, 0)) && Frame.getBookList().get(key).isBorrowPossibility()) {
						Frame.getBookList().remove(key);
						break;
					}
				}
				manager.bookDataSave();
				JOptionPane.showMessageDialog(null, "삭제를 완료했습니다.", "도서 삭제 완료", JOptionPane.OK_OPTION);
				Manager.changePanel(tempf, AdminPanel.this, new AdminPanel(tempf));
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "테이블에서 삭제할 도서를 선택해주세요", "오류", JOptionPane.ERROR_MESSAGE);
			}
		} // end of BookDelete

		// 관리자 로그아웃, 로그인화면으로 넘어감
		else if (e.getActionCommand().equals("Logout")) {
			Manager.changePanel(tempf, AdminPanel.this, new LoginPanel(tempf));
		}

	} // end of actionPerformed()
}
