package ra.run;

import ra.entity.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookImp implements Serializable {
    static List<Book> bookList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("************************* MENU ************************");
            System.out.print("1. Nhập thông tin các sách");
            System.out.print("2. In thông tin các sách ra file demo.txt");
            System.out.print("3. Đọc file demo.txt và in ra các sách có giá trong khoảng 10000 đến 20000");
            System.out.print("4. Thoát");
            System.out.print("Nhập lựa chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    BookImp.inputData();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Lựa chọn không đúng, vui lòng nhập lại!");
                    break;
            }
        }
    }

    public static void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số lượng sách cần thêm: ");
        int numBook = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numBook; i++) {
            System.out.print("Nhập thông tin sách thứ " + (i + 1) + " : ");
            Book book = new Book();
            book.inputData(scanner, bookList);
            bookList.add(book);
        }
        System.out.println("Đã thêm sách thành công.");
    }

    public static void writeDataToFile(List<Book> bookList) {
        File file = new File("demo.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(bookList);
            oos.flush();
        } catch (FileNotFoundException e) {
            System.err.println("File không tồn tại!");
        } catch (IOException e) {
            System.err.println("Lỗi ghi dữ liệu ra file!");
        } catch (Exception e) {
            System.err.println("Xảy ra lỗi trong quá trình ghi dữ liệu ra file!");
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                System.err.println("Xảy ra lỗi khi đóng các stream!");
            } catch (Exception e) {
                System.err.println("Xảy ra lỗi trong quá trình đóng các stream!");
            }
        }
    }

    public static List<Book> readDataFromFile() {
        File file = new File("demo.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Book> bookListRead = null;
        try {
            // 2. khởi tạo đối tượng FileInputStream
            fis = new FileInputStream(file);
            // 3. khởi tạo đối tượng ObjectInputStream
            ois = new ObjectInputStream(fis);
            // 4. đọc dữ liệu Object từ file (readObject())
            bookListRead = (List<Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Không tồn tại file!");
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file!");
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình đọc dữ liệu từ file.");
        } finally {
            // 6. đóng các stream
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                System.err.println("Có lỗi khi đóng stream!");
            } catch (Exception e) {
                System.err.println("Có lỗi trong quá trình đóng các stream.");
            }
            return bookListRead;
        }
    }
}
