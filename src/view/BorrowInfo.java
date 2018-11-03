package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import vo.User;

import java.awt.Color;
import javax.swing.JTable;

public class BorrowInfo extends JFrame {

	private JPanel borrowInfoPanel;
	private JTable table;

	public BorrowInfo(User user) {
		setTitle("대출 정보");
		setSize(600, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		borrowInfoPanel = new JPanel();
		borrowInfoPanel.setBackground(Color.ORANGE);
		borrowInfoPanel.setLayout(null);

		Object[] colBook = { "도서번호", "도서명", "대출일", "반납예정일" };
		Object[][] rowBook = new Object[0][colBook.length];
		DefaultTableModel dtm = new DefaultTableModel(rowBook, colBook);
		table = new JTable(dtm);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(25, 39, 550, 200);
		borrowInfoPanel.add(scroll);

		int i = 0;
		Object[][] row = new Object[user.getBorrowBookList().size()][4];
		for (String key : user.getBorrowBookList()) {
			row[i][0] = Frame.getBookList().get(key).getBookNo();
			row[i][1] = Frame.getBookList().get(key).getBookTitle();
			row[i][2] = Frame.getBookList().get(key).getBorrowDate();
			row[i][3] = Frame.getBookList().get(key).getReturnDate();
			dtm.addRow(row[i++]);
		}

		setContentPane(borrowInfoPanel);
		setVisible(true);
	}
}
