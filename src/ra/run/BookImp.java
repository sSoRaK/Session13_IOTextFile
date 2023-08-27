package ra.run;

import ra.entity.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookImp implements Serializable {
    static List<Book> bookList = new ArrayList<>();
    static File file = new File("demo.txt");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("************************* MENU ************************");
            System.out.println("1. Nhập thông tin các sách");
            System.out.println("2. In thông tin các sách ra file demo.txt");
            System.out.println("3. Đọc file demo.txt và in ra các sách có giá trong khoảng 10000 đến 20000");
            System.out.println("4. Thoát");
            System.out.print("Nhập lựa chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    BookImp.inputData();
                    break;
                case 2:
                    BookImp.writeDataToFile("demo.txt");
                    break;
                case 3:
                    BookImp.readFromFileAndPrintInRange("demo.txt", 10000, 20000);
                    break;
                case 4:
                    System.out.print("Bạn có muốn xóa dữ liệu sau khi thoát (Y/N): ");
                    String confirmDelete = scanner.nextLine();
                    if (confirmDelete.equalsIgnoreCase("Y")) {
                        file.delete();
                        System.exit(0);
                    } else {
                        System.exit(0);
                    }
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
            System.out.println("Nhập thông tin sách thứ " + (i + 1) + " : ");
            Book book = new Book();
            book.inputData(scanner, bookList);
            bookList.add(book);
        }
        System.out.println("Đã thêm sách thành công.");
    }

    private static void writeDataToFile(String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName, true)) { // true: để ghi chèn tiếp data, false: để ghi đè
            for (Book book : bookList) {
                String bookInfo = book.getBookId() + "," + book.getBookName() + "," + book.getPrice() + "\n";
                fos.write(bookInfo.getBytes());
            }
            System.out.println("Ghi thông tin sách vào file thành công.");
        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static void readFromFileAndPrintInRange(String fileName, float minPrice, float maxPrice) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            StringBuilder content = new StringBuilder();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                content.append(new String(buffer, 0, bytesRead));
            }

            String[] lines = content.toString().split("\n");
            System.out.println("Sách trong khoảng giá (10000 - 20000): ");
            for (String line : lines) {
                String[] parts = line.split(",");
                String bookId = parts[0];
                String bookName = parts[1];
                float price = Float.parseFloat(parts[2]);

                if (price >= minPrice && price <= maxPrice) {
                    System.out.printf("BookId: %-25s BookName: %-25s Price: %-25.1f\n", bookId, bookName, price);
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
    // Đọc file demo.txt và in ra các sách có giá trong khoảng 10000 đến 20000
}
