//BookManagement
package ra.run;

import java.util.InputMismatchException;
import java.util.Scanner;
import ra.bussiness.Book;

public class BookManagement {
    private static final int MAX_BOOKS = 100;
    private static ra.bussiness.Book[] bookList = new ra.bussiness.Book[MAX_BOOKS];
    private static int bookCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("****************JAVA-HACKATHON-05-BASIC-MENU***************");
            System.out.println("1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách");
            System.out.println("2. Hiển thị thông tin tất cả sách trong thư viện");
            System.out.println("3. Sắp xếp sách theo lợi nhuận tăng dần");
            System.out.println("4. Xóa sách theo mã sách");
            System.out.println("5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả");
            System.out.println("6. Thay đổi thông tin sách theo mã sách");
            System.out.println("7. Thoát");
            System.out.print("Chọn: ");
            String choiceInput = scanner.nextLine();
            if (choiceInput.matches("\\d+")) { 
            int choice = Integer.parseInt(choiceInput);
            switch (choice) {
                case 1:
                    addBooks(scanner);
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    sortBooksByInterest();
                    break;
                case 4:
                    deleteBookById(scanner);
                    break;
                case 5:
                    searchBooks(scanner);
                    break;
                case 6:
                    updateBookById(scanner);
                    break;
                case 7:
                    System.out.println("Thoát chương trình");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
    }
    } else {
            System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
}
        }
    }

    private static void addBooks(Scanner scanner) {
        System.out.print("Nhập số lượng sách cần thêm: ");
        String input = scanner.nextLine();

        while (!input.matches("\\d+")) {
            System.out.print("Đây là 1 số, vui lòng nhập lại: ");
            input = scanner.nextLine();
        }
        int n = Integer.parseInt(input);
        for (int i = 0; i < n && bookCount < MAX_BOOKS; i++) {
            Book book = new Book();
            book.inputData();
            bookList[bookCount++] = book;
        }
    }

    private static void displayBooks() {
        if (bookCount == 0 || bookList.length == 0) {
            System.out.println("Chưa có sách");
            return;
        }

        for (int i = 0; i < bookCount; i++) {
            bookList[i].displayData();
            System.out.println("--------------------");
        }
    }

    private static void sortBooksByInterest() {
        if (bookCount == 0 || bookList.length == 0) {
            System.out.println("Chưa có sách");
            return;
        }
        for (int i = 0; i < bookCount - 1; i++) {
            for (int j = i + 1; j < bookCount; j++) {
                if (bookList[i].getInterest() > bookList[j].getInterest()) {
                    Book temp = bookList[i];
                    bookList[i] = bookList[j];
                    bookList[j] = temp;
                }
            }
        }
        System.out.println("Sắp xếp thành công! Danh sách sách sau khi sắp xếp:");
    }

    private static void deleteBookById(Scanner scanner) {
        System.out.print("Nhập mã sách cần xóa: ");
        int id = scanner.nextInt();
        if (bookCount == 0 || bookList.length == 0) {
            System.out.println("Chưa có sách trong menu");
            return;
        }
        for (int i = 0; i < bookCount; i++) {
            if (bookList[i].getBookId() == id) {
                for (int j = i; j < bookCount - 1; j++) {
                    bookList[j] = bookList[j + 1];
                }
                bookList[--bookCount] = null; 
                System.out.println("Xóa sách thành công!");
                return;
            }
        }
        System.out.println("Không tìm thấy mã sách này.");
    }

    private static void searchBooks(Scanner scanner) {
        System.out.print("Nhập  mô tả hoặc tựa của sách : ");
        String searchString = scanner.nextLine().toLowerCase();
        if (bookCount == 0 || bookList.length == 0) {
            System.out.println("Chưa có sách");
            return;
        }
        for (int i = 0; i < bookCount; i++) {
            if (bookList[i].getBookName().toString().toLowerCase().contains(searchString) ||
                bookList[i].getDescription().toString().toLowerCase().contains(searchString)) {
                bookList[i].displayData();
                System.out.println("----------------------");
            }
        }
    }
//case 6
    private static void updateBookById(Scanner scanner) {
        System.out.print("Nhập mã sách cần cập nhật: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        if (bookCount == 0 || bookList.length == 0) {
            System.out.println("Chưa có sách");
            return;
        }
        for (int i = 0; i < bookCount; i++) {
            if (bookList[i].getBookId() == id) {
                Book book = bookList[i];
                System.out.println("Thông tin sách hiện tại:");
                book.displayData();

                System.out.println("Chọn thông tin cần thay đổi:");
                System.out.println("1. Tên sách");
                System.out.println("2. Tác giả");
                System.out.println("3. Mô tả");
                System.out.println("4. Giá nhập");
                System.out.println("5. Giá xuất");
                System.out.println("6. Trạng thái sách");

                int choice = scanner.nextInt();
                scanner.nextLine(); 
                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên sách mới: ");
                        String newName = scanner.nextLine();
                        System.out.println("Tên sách cũ: " + book.getBookName());
                        book.setBookName(new StringBuilder(newName));
                        System.out.println("Tên sách mới: " + book.getBookName());
                        break;
                    case 2:
                        System.out.print("Nhập tên tác giả mới: ");
                        String newAuthor = scanner.nextLine();
                        System.out.println("Tác giả cũ: " + book.getAuthor());
                        book.setAuthor(new StringBuilder(newAuthor));
                        System.out.println("Tác giả mới: " + book.getAuthor());
                        break;
                    case 3:
                        System.out.print("Nhập mô tả mới: ");
                        String newDescription = scanner.nextLine();
                        System.out.println("Mô tả cũ: " + book.getDescription());
                        book.setDescriptions(new StringBuilder(newDescription));
                        System.out.println("Mô tả mới: " + book.getDescription());
                        break;
                    case 4:
                        System.out.print("Nhập giá nhập mới: ");
                        double newImportPrice = scanner.nextDouble();
                        System.out.println("Giá nhập cũ: " + book.getImportPrice());
                        book.setImportPrice(newImportPrice);
                        System.out.println("Giá nhập mới: " + book.getImportPrice());
                        book.setExportPrice(book.getExportPrice()); // Recalculate interest
                        System.out.println("Lợi nhuận mới: " + book.getInterest());
                        break;
                    case 5:
                        System.out.print("Nhập giá xuất mới: ");
                        double newExportPrice = scanner.nextDouble();
                        System.out.println("Giá xuất cũ: " + book.getExportPrice());
                        book.setExportPrice(newExportPrice);
                        System.out.println("Giá xuất mới: " + book.getExportPrice());
                        System.out.println("Lợi nhuận mới: " + book.getInterest());
                        break;
                    case 6:
                        System.out.print("Nhập trạng thái sách mới (true for available, false for unavailable): ");
                        boolean newStatus = scanner.nextBoolean();
                        System.out.println("Trạng thái sách cũ: " + (book.getBookStatus() ? "Available" : "Unavailable"));
                        book.setBookStatus(newStatus);
                        System.out.println("Trạng thái sách mới: " + (book.getBookStatus() ? "Available" : "Unavailable"));
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }

                System.out.println("Cập nhật sách thành công!");
                book.displayData();
            }
        }
        System.out.println("Không tìm thấy mã sách này.");
    }
}
