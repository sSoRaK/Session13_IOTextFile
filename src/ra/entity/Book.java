package ra.entity;

import java.util.List;
import java.util.Scanner;

public class Book {
    private String bookId;
    private String bookName;
    private float price;

    public Book() {
    }

    public Book(String bookId, String bookName, float price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void inputData(Scanner scanner, List<Book> bookList) {
        System.out.print("Nhập mã sách: ");
        this.bookId = scanner.nextLine();
        System.out.print("Nhập tên sách: ");
        this.bookName = scanner.nextLine();
        System.out.print("Nhập giá tiền: ");
        this.price = Float.parseFloat(scanner.nextLine());
    }

    public void displayData() {
        System.out.printf("%-25s%-25s%-25.2f", bookId, bookName, price);
    }
}
