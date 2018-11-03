package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.Frame;
import vo.Book;
import vo.User;

public class Manager {

	public Manager() {
		userDataLoad();
		bookDataLoad();
	}

	public void userDataSave() {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("data/userdata.dat")))) {
			oos.writeObject(Frame.getUserList());
			System.out.println("유저데이터 저장 완료!");
		} catch (FileNotFoundException e) {
			System.out.println("유저데이터 파일이 없어 새로 만들었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void userDataLoad() {
		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("data/userdata.dat")))) {
			Frame.getUserList().clear();
			List<User> temp = ((List<User>) ois.readObject());
			Frame.getUserList().addAll(temp);
			for (User u : Frame.getUserList()) {
				System.out.println(u);
			}
			System.out.println("유저데이터 불러오기 완료!");
		} catch (FileNotFoundException e) {
			System.out.println("유저데이터 파일이 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void bookDataSave() {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("data/bookdata.dat")))) {
			oos.writeObject(Frame.getBookList());
			System.out.println("도서데이터 저장 완료!");
		} catch (FileNotFoundException e) {
			System.out.println("도서데이터 파일이 없어 새로 만들었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bookDataLoad() {
		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("data/bookdata.dat")))) {
			Frame.getBookList().clear();
			Map<String, Book> temp = ((Map<String, Book>) ois.readObject());
			Frame.setBookList(temp);
			for (String key : Frame.getBookList().keySet()) {
				System.out.println("도서번호 : " + key + ", 도서 : " + Frame.getBookList().get(key));
			}
			System.out.println("도서데이터 불러오기 완료!");
		} catch (FileNotFoundException e) {
			System.out.println("도서데이터 파일이 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setBookBorrow(String key, String borrowUserId, User user) {
		if (user.getBorrowBookCount() < 3) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			String borrowDate = sdf.format(cal.getTime());
			cal.add(Calendar.DAY_OF_MONTH, 15);
			String returnDate = sdf.format(cal.getTime());

			Frame.getBookList().get(key).setBorrowPossibility(false);
			Frame.getBookList().get(key).setBorrowDate(borrowDate);
			Frame.getBookList().get(key).setReturnDate(returnDate);
			Frame.getBookList().get(key).setBorrowUserId(borrowUserId);

			user.getBorrowBookList().add(key);
			user.setBorrowBookCount(user.getBorrowBookList().size());
			user.setBorrowBookHistoryCount(user.getBorrowBookHistoryCount() + 1);
			JOptionPane.showMessageDialog(null, "도서 대출이 완료되었습니다.", "도서 대출 완료", JOptionPane.OK_OPTION);
		} else {
			JOptionPane.showMessageDialog(null, "도서 대출 가능 권수가 최대입니다.\n도서 대출이 불가합니다.", "도서 대출 불가",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setBookReturn(String key, User user) {
		Frame.getBookList().get(key).setBorrowPossibility(true);
		Frame.getBookList().get(key).setBorrowDate(null);
		Frame.getBookList().get(key).setReturnDate(null);
		Frame.getBookList().get(key).setBorrowUserId(null);

		user.getBorrowBookList().remove(key);
		user.setBorrowBookCount(user.getBorrowBookList().size());
		JOptionPane.showMessageDialog(null, "도서 반납이 완료되었습니다.", "도서 반납 완료", JOptionPane.OK_OPTION);
	}

	public static void changePanel(Frame view, JPanel cp, JPanel np) {
		view.remove(cp);
		view.add(np);
		view.repaint();
	}

}
