import java.util.Scanner;
public class VerndingMachineWithArrays {
    public static void main(String[] args) {
        String[] items = {"Cola", "Chips", "Chocolate", "Water"};
        double[] prices = {1.50, 2.00, 3.00, 1.25};
        int[] quantities = {10, 10, 10, 10};
        Scanner scan = new Scanner(System.in);
        int choice;

        do {
            displayI(items, prices, quantities);
            System.out.println("enter code of item (1-4) or 0 to exit: ");
            choice = scan.nextInt();

            if (choice == 0) {
                System.out.println("Thank you. Goodbye!");
                break;
            }

            if (choice >= 1 && choice <= 4) {
            int itemIndex = choice - 1;
            if (quantities[itemIndex] > 0) {
                System.out.println("You selected " + items[itemIndex] + ". The price is $" + prices[itemIndex]);
                System.out.print("Insert money: ");
            } else {
                System.out.println(items[itemIndex] + " is out of stock");}

            double insertedMoney = scan.nextDouble();
            if (insertedMoney >= prices[itemIndex]) {
                double change = insertedMoney - prices[itemIndex];
                System.out.println("Purchase successful! Your change: " + change);
                quantities[itemIndex]--;
            } else {
                System.out.println("Insufficient funds"); }

            } else {
                System.out.println("Invalid choice. Please enter valid item code (1-4)"); }
        } while (choice != 0);
    }

    public static void displayI(String[] items, double[] prices, int[] quantities) {
        System.out.println("Available items:\nCode|Name|Price|Availability");
        for (int i = 0; i < items.length; i++) {
            System.out.println((i + 1)+ " " + items[i] + " - $" + prices[i] + " (Quantity: " + quantities[i] + ")");
        }
    }
}
