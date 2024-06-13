package ra.bussiness;

import java.util.Scanner;

public class Book {
    private static int idCounter=1;
    private StringBuilder bookName, author, descriptions;
    private double importPrice, exportPrice;
    private float interest;
    private boolean bookStatus;
    private int bookId;

    public Book(){
        this.bookId=idCounter++;
        this.bookStatus=true;
    }

    public int getBookId(){return bookId;}
    public StringBuilder getBookName(){return bookName;}
    public StringBuilder getAuthor(){return author;}
    public StringBuilder getDescription(){return descriptions;}
    public double getImportPrice(){return importPrice;}
    public double getExportPrice(){return exportPrice;}
    public float getInterest(){return interest;}
    public boolean getBookStatus(){return bookStatus;}

    public void setBookName(StringBuilder bookName) {
        if (bookName != null && bookName.length() > 0) {
            this.bookName = bookName;
        } else {
            throw new IllegalArgumentException("Book name cannot be empty");
        }
    }
    public void setAuthor(StringBuilder author) {
        if (author != null && author.length() > 0) {
            this.author = author;
        } else {
            throw new IllegalArgumentException("Author cannot be empty");
        }
    }
    public void setDescriptions(StringBuilder descriptions) {
        if (descriptions != null && descriptions.length() >= 10) {
            this.descriptions = descriptions;
        } else {
            throw new IllegalArgumentException("Description must be at least 10 characters");
        }
    }
    public void setImportPrice(double importPrice) {
        if (importPrice > 0) {
            this.importPrice = importPrice;
        } else {
            throw new IllegalArgumentException("Import price must be greater than 0");
        }
    }
    public void setExportPrice(double exportPrice) {
        if (exportPrice >= 1.2 * importPrice ) {
            this.exportPrice = exportPrice;
        } else {
            throw new IllegalArgumentException("Export price must be greater than 1.2 times the import price");
        }
    }
    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }
    public void inputData() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter book name: ");
            String input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                setBookName(new StringBuilder(input));
                break;
            } else {
                System.err.println("Book name cannot be empty. Please try again:");
            }
        }


        while (true) {
            System.out.print("Enter author: ");
            String input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                setAuthor(new StringBuilder(input));
                break;
            } else {
                System.err.println("Author cannot be empty. Please try again:");
            }
        }

        while (true) {
            System.out.print("Enter descriptions: ");
            String input = scanner.nextLine();
            if (input != null && input.length() >= 10) {
                setDescriptions(new StringBuilder(input));
                break;
            } else {
                System.err.println("Description must be at least 10 characters. Please try again:");
            }
        }

        while (true) {
            System.out.print("Enter import price: ");
            double input = scanner.nextDouble();
            if (input > 0) {
                setImportPrice(input);
                break;
            } else {
                System.err.println("Import price must be greater than 0. Please try again:");
            }
        }

        while (true) {
            System.out.print("Enter export price: ");
            double input = scanner.nextDouble();
            if (input >= 1.2 * importPrice) {
                setExportPrice(input);
                break;
            } else {
                System.err.println("Export price must be greater than 1.2 times the import price. Please try again:");
            }
        }

       //tinh lai suat
        this.interest = (float) (exportPrice - importPrice);
    }
    public void displayData() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Book Name: " + bookName);
        System.out.println("Author: " + author);
        System.out.println("Descriptions: " + descriptions);
        System.out.println("Import Price: " + importPrice);
        System.out.println("Export Price: " + exportPrice);
        System.out.println("Interest: " + interest);
        System.out.println("Book Status: " + (bookStatus ? "Available" : "Unavailable"));
    }
}
