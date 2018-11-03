package vo;

import java.io.Serializable;
import java.util.Calendar;

public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	private String bookNo; // 도서번호
	private String bookTitle; // 도서명
	private String author; // 저자
	private String publisher; // 출판사
	private String genre; // 장르 - 소설, 인문, 과학, 역사, 자기계발, 여행, IT, 만화
	
	private String borrowDate; // 대출한 날짜
	private String returnDate; // 대출기간은 15일
	private boolean borrowPossibility = true; // true : 대출 가능, false : 대출 불가능
	private boolean borrowReservation = false; // true : 대출예약 되어있음, false: 대출예약 없음
	
	private String borrowUserId;// 도서대출해간 회원의 아이디
	
	public Book() {}

	public Book(String bookNo, String bookTitle, String author, String publisher, String genre) {
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.author = author;
		this.publisher = publisher;
		this.genre = genre;
	}

	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public boolean isBorrowPossibility() {
		return borrowPossibility;
	}
	public void setBorrowPossibility(boolean borrowPossibility) {
		this.borrowPossibility = borrowPossibility;
	}
	public boolean isBorrowReservation() {
		return borrowReservation;
	}
	public void setBorrowReservation(boolean borrowReservation) {
		this.borrowReservation = borrowReservation;
	}
	public String getBorrowUserId() {
		return borrowUserId;
	}
	public void setBorrowUserId(String borrowUserId) {
		this.borrowUserId = borrowUserId;
	}

	@Override
	public String toString() {
		return "Book [bookNo=" + bookNo + ", bookTitle=" + bookTitle + ", author=" + author + ", publisher=" + publisher
				+ ", genre=" + genre + ", borrowDate=" + borrowDate + ", returnDate=" + returnDate
				+ ", borrowPossibility=" + borrowPossibility + ", borrowReservation=" + borrowReservation + ", borrowUserId=" + borrowUserId + "]";
	}
}
