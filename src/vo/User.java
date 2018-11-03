package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import controller.Manager;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int userNo = 0;
	private String userId; // 아이디
	private String userPw; // 패스워드
	private String userName; // 이름
	private String userPhoneNo; // 핸드폰번호 - 000-0000-0000 형식으로 저장, 핸드폰번호로 같은 사람 중복가입 막기.

	private boolean borrowSuspend = false; // true: 대출이용정지, false: 대출이용가능
	private List<String> borrowBookList = new ArrayList<>(); // 유저가 대출한 도서번호 저장
	private int borrowBookCount; // 유저가 현재 대출한 책 권수, 최대 3권까지 가능
	private int borrowBookHistoryCount = 0; // 유저가 총 대출한 책 권수

	public User() {
	}

	public User(int userNo, String userId, String userPw, String userName, String userPhoneNo) {
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userPhoneNo = userPhoneNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhoneNo() {
		return userPhoneNo;
	}

	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}

	public boolean isBorrowSuspend() {
		return borrowSuspend;
	}

	public void setBorrowSuspend(boolean borrowSuspend) {
		this.borrowSuspend = borrowSuspend;
	}

	public List<String> getBorrowBookList() {
		return borrowBookList;
	}

	public void setBorrowBookList(List<String> borrowBookList) {
		this.borrowBookList = borrowBookList;
	}

	public int getBorrowBookCount() {
		return borrowBookCount;
	}

	public void setBorrowBookCount(int borrowBookCount) {
		this.borrowBookCount = borrowBookCount;
	}

	public int getBorrowBookHistoryCount() {
		return borrowBookHistoryCount;
	}

	public void setBorrowBookHistoryCount(int borrowBookHistoryCount) {
		this.borrowBookHistoryCount = borrowBookHistoryCount;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", userPhoneNo=" + userPhoneNo + ", borrowSuspend=" + borrowSuspend + ", borrowBookList="
				+ borrowBookList + ", borrowBookCount=" + borrowBookCount + ", borrowBookHistoryCount="
				+ borrowBookHistoryCount + "]";
	}

}
