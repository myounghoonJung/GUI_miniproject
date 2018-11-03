package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class BorrowPanel extends JPanel implements ActionListener {

	private Frame tempf;
	private JTable tbBook;
	private DefaultTableModel dtmBook;
	private JComboBox<String> cbBookSearch;
	private JTextField tfBookSearch;

	private Manager manager = new Manager();

	public BorrowPanel(Frame f) {
		tempf = f;

		setSize(780, 680);
		setLayout(null);

		// Panel 생성
		// MainPanel
		JPanel borrowMainPanel = new JPanel();
		borrowMainPanel.setBackground(new Color(255, 215, 0));
		borrowMainPanel.setBounds(0, 0, 780, 600);
		borrowMainPanel.setLayout(null);

		// SubPanel
		JPanel borrowSubPanel = new JPanel();
		borrowSubPanel.setBackground(new Color(255, 218, 185));
		borrowSubPanel.setBounds(0, 600, 780, 80);
		borrowSubPanel.setLayout(null);

		JLabel lblBookList = new JLabel("도서 목록");
		lblBookList.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblBookList.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookList.setBounds(316, 107, 150, 25);
		borrowMainPanel.add(lblBookList);

		Object[] colBook = { "도서번호", "도서명", "저자", "출판사", "장르", "대출 여부" };
		Object[][] rowBook = new Object[0][colBook.length];
		dtmBook = new DefaultTableModel(rowBook, colBook);
		tbBook = new JTable(dtmBook);
		JScrollPane scrollBook = new JScrollPane(tbBook);
		scrollBook.setBounds(40, 208, 700, 263);
		borrowMainPanel.add(scrollBook);

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
		cbBookSearch.setBounds(42, 146, 101, 27);
		borrowMainPanel.add(cbBookSearch);

		tfBookSearch = new JTextField();
		tfBookSearch.setBounds(138, 145, 487, 26);
		borrowMainPanel.add(tfBookSearch);
		tfBookSearch.setColumns(10);

		JButton btnBookSearch = new JButton("도서 조회");
		btnBookSearch.setBounds(623, 145, 117, 29);
		borrowMainPanel.add(btnBookSearch);
		btnBookSearch.setActionCommand("BookSearch");

		JButton btnBookBorrow = new JButton("도서 대출");
		btnBookBorrow.setBounds(623, 173, 117, 29);
		borrowMainPanel.add(btnBookBorrow);
		btnBookBorrow.setActionCommand("BookBorrow");

		JButton btnBack = new JButton("뒤로 가기");
		btnBack.setBounds(331, 25, 117, 29);
		btnBack.setActionCommand("Back");
		borrowSubPanel.add(btnBack);

		// 리스너
		btnBookSearch.addActionListener(this);
		btnBookBorrow.addActionListener(this);
		btnBack.addActionListener(this);

		add(borrowMainPanel);
		add(borrowSubPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("BookBorrow")) {
			try {
				int row = tbBook.getSelectedRow();
				dtmBook.getValueAt(row, 0); // 에러띄우기용

				String borrowUserId = Frame.getLoginUser().getUserId();

				boolean borrowUserIdVal = false;
				boolean borrowBookVal = false;

				for (String key : Frame.getBookList().keySet()) {
					if (key.equals(dtmBook.getValueAt(row, 0))) {
						if (Frame.getBookList().get(key).isBorrowPossibility()) {
							borrowBookVal = true;
							for (User u : Frame.getUserList()) {
								if (u.getUserId().equals(borrowUserId)) {
									manager.setBookBorrow(key, borrowUserId, u);
									Frame.getLoginUser().getBorrowBookList().add(key);
									Frame.getLoginUser()
											.setBorrowBookCount(Frame.getLoginUser().getBorrowBookList().size());
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
					Manager.changePanel(tempf, BorrowPanel.this, new BorrowPanel(tempf));
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "테이블에서 대출할 도서를 선택해주세요", "오류", JOptionPane.ERROR_MESSAGE);
			}
		} // end of BookBorrow

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

		else if (e.getActionCommand().equals("Back")) {
			Manager.changePanel(tempf, BorrowPanel.this, new UserPanel(tempf));
		}
	}
}
