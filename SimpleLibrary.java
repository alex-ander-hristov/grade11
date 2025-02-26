import java.util.Scanner;
public class SimpleLibrary {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        book[] booksArr = {
                new book("The Firm", "Grisham", 1991),
                new book("Catch-22", "Heller", 1953),
                new book("In Cold Blood", "Capote", 1966),
                new book("Things Fall Apart", "Achebe", 1958)
        };

        int choice = 0;
        do {
            System.out.println("\n1. Display all books");
            System.out.println("2. Borrow book");
            System.out.println("3. Return book");
            System.out.println("4. Find book by title");
            System.out.println("5. Exit");
            System.out.print("Enter option: ");
            choice = scan.nextInt();
            scan.nextLine();


            switch (choice) {
                case 1:
                    displayAll(booksArr);
                    break;
                case 2:
                    borrow(booksArr);
                    break;
                case 3:
                    returnBook(booksArr);
                    break;
                case 4:
                    findBookByTitle(booksArr);
                    break;
                case 5:
                    System.out.println("Thank you. Goodbye.");
                    break;
                default:
                    System.out.println("invalid input");
            }
        } while (choice != 5);


    }


    public static void displayAll(book[] books) {
        System.out.println("\nAll books: ");
        for (book c : books) {
            System.out.println(c.getDetails());

            if (c.getBorrowerName() == null) {
                System.out.println("Book available");
            } else {
                System.out.println("Borrower name: " + c.getBorrowerName());
            }
        }
    }

    public static void borrow(book[] books) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the book index to borrow (0-3)");
        int borrowBook = scan.nextInt();
        scan.nextLine();

        if (borrowBook >= 0 && borrowBook < books.length) {
            book bookToBorrow = books[borrowBook];
            if (bookToBorrow.getBorrowerName() == null) {
                System.out.print("Borrower name: ");
                String borrowerName = scan.nextLine();
                bookToBorrow.borrowBook(borrowerName);
                System.out.println("Book borrowed.");
            } else {
                System.out.println("Book is already borrowed.");
            }
        } else {
            System.out.println("invalid index");
        }
    }


    public static void returnBook(book[] books) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the book index to return (0-3)");
        int returnBookIndex = scan.nextInt();
        scan.nextLine();

        if (returnBookIndex >= 0 && returnBookIndex < books.length) {
            book bookToReturn = books[returnBookIndex];
            if (bookToReturn.getBorrowerName() != null) {
                bookToReturn.returnBook();
                System.out.println("Book returned.");
            } else {
                System.out.println("Book was not borrowed.");
            }
        } else {
            System.out.println("invalid index");
        }
    }

    public static void findBookByTitle(book[] books) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter book title: ");
        String title = scan.nextLine();
        int index = -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i].title.equalsIgnoreCase(title)) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            System.out.println("Book found: \n" + books[index].getDetails() + "\n---------");
        } else {
            System.out.println("No such book.");
        }
    }
}

class book {
    String title;
    String author;
    int yearPublished;
    String borrowerName;


    public book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.borrowerName = null;
    }

    public String getDetails() {
        String details = ("---------" + "\nTitle: " + title + "\nAuthor: " + author + "\nYear Published: " + yearPublished);
        return details;
    }

    public void updateBookInfo(String newTitle, String newAuthor, int newYearPublished) {
        this.title = newTitle;
        this.author = newAuthor;
        this.yearPublished = newYearPublished;
    }

    public void borrowBook(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public void returnBook() {
        this.borrowerName = null;
    }

    public String getBorrowerName() {
        return borrowerName;
    }
}

