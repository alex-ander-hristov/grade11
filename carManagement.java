import java.util.Scanner;
public class carManagement {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        car [] carsArr = {
                new car("toyota", "yaris", 2020, 15000, true),
                new car("honda", "jazz", 2017, 13000, false),
                new car("ford", "fusion", 2021, 17000, true),
                new car("ford", "focus", 2018, 10000, false)
        };

        int choice = 0;
        do {
            System.out.println("1.Display all cars");
            System.out.println("2.Display available cars for lease");
            System.out.println("3.Display most expensive car");
            System.out.println("4.Display average price of cars");
            System.out.println("5.Lease a car");
            System.out.println("6.Exit");
            System.out.println("Enter option: ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    displayAll(carsArr);
                    break;
                case 2:
                    displayAvailable(carsArr);
                    break;
                case 3:
                    System.out.println("most expensive car: " + mostExpensive(carsArr).make + " " + mostExpensive(carsArr).model);
                    break;
                case 4:
                    System.out.println("average price: " + averageP(carsArr));
                    break;
                case 5:
                    System.out.print("Enter car index to lease (0-3): ");
                    int leaseChoice = scan.nextInt();
                    leaseCar(carsArr, leaseChoice);
                    break;
                case 6:
                    System.out.println("Thank you. Goodbye.");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }while(choice != 6);
    }
    public static void displayAvailable (car[] cars){
        boolean available = false;
        for (car c : cars) {
            if (c.isLeased == false) {
                c.displayInfo();
                available = true;
            }
        }
        if (available == false) {
            System.out.println("no cars for lease");
        }
    }
    public static void displayAll(car[] cars) {
        for (car c : cars) {
            c.displayInfo();
        }
    }

    public static car mostExpensive (car[] cars){
        double highest = cars[0].price   ;
        int index = 0;
        for(int i = 1; i< cars.length; i++){
            if(cars[i].price > highest ) {
                highest = cars[i].price;
                index = i;

            }
        }
        return cars[index];
    }

    public static double averageP (car[] cars){
        double total = 0;
        int index = 0;
        for (car c : cars) {
            total += c.price;
        }
        return total/cars.length;
    }


    public static void leaseCar(car[] cars, int leaseChoice) {
        if (leaseChoice >= 0 && leaseChoice < cars.length) {
            if (!cars[leaseChoice].isLeased) {
                cars[leaseChoice].isLeased = true;
                System.out.println("You have leased the car: " + cars[leaseChoice].make + " " + cars[leaseChoice].model);
            } else {
                System.out.println("car not available");
            }
        } else {
            System.out.println("invalid index");
        }
    }
}

class car{
    String make;
    String model;
    int year;
    double price;
    boolean isLeased;


    public car (String make, String model, int year, double price, boolean isLeased){
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.isLeased = isLeased;
    }


    public void displayInfo() {
        System.out.println("------------\nmake: "+make + "\nmodel: " + model + "\nyear: " + year + "\nprice: " + price + "\nleased: " + isLeased + "\n------------");
    }
}
