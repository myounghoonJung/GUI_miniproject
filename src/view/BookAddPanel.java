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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Manager;
import vo.Book;

public class BookAddPanel extends JPanel implements ActionListener{

	private Frame tempf;
	private JTextField tfBookNo;
	private JTextField tfBookTitle;
	private JTextField tfAuthor;
	private JTextField tfPublisher;
	private JComboBox cbGenre;
	
	private Manager manager = new Manager();
	
	public BookAddPanel(Frame f) {
		tempf = f;
		setSize(780, 680);
		setLayout(null);
		
		// Panel 생성
		// MainPanel
		JPanel bookAddMainPanel = new JPanel();
		bookAddMainPanel.setBackground(new Color(255, 215, 0));
		bookAddMainPanel.setBounds(0, 0, 780, 600);
		bookAddMainPanel.setLayout(null);
		
		// SubPanel
		JPanel bookAddSubPanel = new JPanel();
		bookAddSubPanel.setBackground(new Color(255, 218, 185));
		bookAddSubPanel.setBounds(0, 600, 780, 80);
		bookAddSubPanel.setLayout(null);
		
		
		// 컴포넌트
		JLabel label = new JLabel("도서 추가");
		label.setFont(new Font("Lucida Grande", Font.BOLD, 60));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(225, 61, 329, 105);
		bookAddMainPanel.add(label);
		
		tfBookNo = new JTextField();
		tfBookNo.setBounds(353, 196, 200, 26);
		bookAddMainPanel.add(tfBookNo);
		tfBookNo.setColumns(10);
		
		JLabel lblBookNo = new JLabel("도서번호");
		lblBookNo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblBookNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookNo.setBounds(241, 199, 70, 20);
		bookAddMainPanel.add(lblBookNo);
		
		tfBookTitle = new JTextField();
		tfBookTitle.setBounds(353, 260, 200, 26);
		bookAddMainPanel.add(tfBookTitle);
		tfBookTitle.setColumns(10);
		
		JLabel lblBookTitle = new JLabel("도서명");
		lblBookTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblBookTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookTitle.setBounds(241, 263, 70, 20);
		bookAddMainPanel.add(lblBookTitle);
		
		tfAuthor = new JTextField();
		tfAuthor.setBounds(353, 319, 200, 26);
		bookAddMainPanel.add(tfAuthor);
		tfAuthor.setColumns(10);
		
		JLabel lblAuthor = new JLabel("저자");
		lblAuthor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthor.setBounds(241, 322, 70, 20);
		bookAddMainPanel.add(lblAuthor);
		
		tfPublisher = new JTextField();
		tfPublisher.setBounds(353, 385, 200, 26);
		bookAddMainPanel.add(tfPublisher);
		tfPublisher.setColumns(10);
		
		JLabel lblPublisher = new JLabel("출판사");
		lblPublisher.setHorizontalAlignment(SwingConstants.CENTER);
		lblPublisher.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPublisher.setBounds(241, 388, 70, 20);
		bookAddMainPanel.add(lblPublisher);
		
		JLabel lblGenre = new JLabel("장르");
		lblGenre.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenre.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblGenre.setBounds(241, 456, 70, 20);
		bookAddMainPanel.add(lblGenre);
		
		cbGenre = new JComboBox();
		cbGenre.setModel(new DefaultComboBoxModel(new String[] {"소설", "인문", "과학", "역사", "자기계발", "여행", "IT", "만화"}));
		cbGenre.setBounds(353, 453, 200, 27);
		bookAddMainPanel.add(cbGenre);
		
		JButton btnBookAdd = new JButton("도서 추가");
		btnBookAdd.setBounds(315, 522, 150, 29);
		bookAddMainPanel.add(btnBookAdd);
		btnBookAdd.setActionCommand("BookAdd");
		
		JButton btnBack = new JButton("메인으로");
		btnBack.setBounds(315, 25, 150, 29);
		btnBack.setActionCommand("Back");
		bookAddSubPanel.add(btnBack);
		
		
		
		
		
		
		
		
		// 리스너
		btnBookAdd.addActionListener(this);
		btnBack.addActionListener(this);
		
		
		
		add(bookAddMainPanel);
		add(bookAddSubPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("BookAdd")) {
			Frame.getBookList().put(tfBookNo.getText(), new Book(tfBookNo.getText(),tfBookTitle.getText(),tfAuthor.getText(),tfPublisher.getText(),cbGenre.getSelectedItem().toString()));
			JOptionPane.showMessageDialog(null, "도서추가를 완료했습니다.", "도서 추가 완료", JOptionPane.OK_OPTION);
			manager.bookDataSave();
			manager.bookDataLoad();
		}
		else if (e.getActionCommand().equals("Back")) {
			Manager.changePanel(tempf, BookAddPanel.this, new AdminPanel(tempf));
		}
	}
}
