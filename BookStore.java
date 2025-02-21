import java.util.Scanner;
public class BookStore {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] titles = {"Catch 22", "1984", "In Cold Blood"};
        double[] prices = {12, 12, 12};
        int[] quantities = {5, 5, 5};

        int choice = 0;

        while (choice != 4) {
            System.out.println("\n1. Show available books");
            System.out.println("2. Search for a book");
            System.out.println("3. Purchase a book");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scan.nextInt();
            scan.nextLine();

            if (choice == 1) {
                for (int i = 0; i < titles.length; i++) {
                    System.out.println((i + 1) + ". name: " + titles[i] + " | price: " + prices[i] + " | quantity: " + quantities[i]);
                }
            } else if (choice == 2) {
                System.out.print("\nEnter the title of the book: ");
                String search = scan.nextLine().toLowerCase();
                boolean found = false;
                for (int i = 0; i < titles.length; i++) {
                    if (titles[i].toLowerCase().equals(search)) {
                        System.out.println("Found - name: " + titles[i] + " | price: " + prices[i] + " | quantity: " + quantities[i]);
                        found = true;
                    }
                }
                if (found == false) {
                    System.out.println("Book not found");
                }
            } else if (choice == 3) {
                System.out.print("\nEnter book number you want to buy (1-3): ");
                int bookNumber = scan.nextInt();
                if (bookNumber < 1 || bookNumber > titles.length) {
                    System.out.println("Invalid number.");
                } else {
                    int index = bookNumber - 1;
                    if (quantities[index] > 0) {
                        System.out.println("You selected book: " + titles[index] + " with price: " + prices[index]);
                        System.out.print("Insert money: ");
                        double money = scan.nextDouble();
                        if (money >= prices[index]) {
                            quantities[index]--;
                            System.out.println("Book purchased. Your change: " + (money - prices[index]));
                        } else {
                            System.out.println("Insufficient funds");
                        }
                    } else {
                        System.out.println("Book is out of stock");
                    }
                }
            } else if (choice != 4) {
                System.out.println("Invalid option");
            }
        }

        System.out.println("Thank you! Goodbye!");
    }
}
