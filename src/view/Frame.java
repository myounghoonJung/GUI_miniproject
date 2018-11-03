package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import vo.Book;
import vo.User;

public class Frame extends JFrame {
	
	private static User loginUser; // 로그인한 사용자 계정
	
	private static List<User> userList = new ArrayList<>(); // 모든 회원객체가 담긴 리스트
	private static Map<String, Book> bookList = new HashMap<>(); // 모든 도서객체가 담긴 맵

	
	
	public static User getLoginUser() {
		return loginUser;
	}
	public static void setLoginUser(User loginUser) {
		Frame.loginUser = loginUser;
	}
	public static List<User> getUserList() {
		return userList;
	}
	public static void setUserList(List<User> userList) {
		Frame.userList = userList;
	}
	public static Map<String, Book> getBookList() {
		return bookList;
	}
	public static void setBookList(Map<String, Book> bookList) {
		Frame.bookList = bookList;
	}
	
	
	public Frame() {
		setTitle("도서 관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(780, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		
		
		// LoginPanel 등록
		new LoginPanel(this);
		
		setVisible(true);
	}


}
