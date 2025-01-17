import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Product class
class Product {
    private String name;
    private double price;
    private int quantity;

    //constructor to initialize product with name, price, quantity
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    //getter and setter methods for name, price, quantity
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //method to display product details in console
    public void display () {
        System.out.println("Name: " + getName() + ", Price: " + getPrice() + ", Quantity: " + getQuantity());
    }

    //method to return product details as string
    public String turnToString() {
        return "Name: " + getName() + ", Price: " + getPrice() + ", Quantity: " + getQuantity();
    }
}

//Inventory class - holds products and manages inventory
class Inventory {
    private Product[] products = new Product[3]; //array to store up to 3 products
    private int productCount = 0; //track number of products in the inventory

    //method to add a product to the inventory
    public boolean addProduct(String name, double price, int quantity) {
        if (productCount < 3) { //check if there is space for more products
            products[productCount] = new Product(name, price, quantity);
            productCount++;
            return true;
        } else {
            System.out.println("Maximum product quantity reached");
            return false;
        }
    }

    //method to update an existing product's quantity based on name and price
    public boolean updateProduct (String name, double price, int quantity) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equals(name) && products[i].getPrice() == price) {
                products[i].setQuantity(quantity);
                products[i].display();
                return true;
            }
        }
        System.out.println("Product not found: " + name);
        return false;
    }

    //method to return a formatted string of all products in the inventory
    public String displayAllProducts() {
        if (productCount == 0) {
            return "No products found";
        }

        String inventoryDetails = "";
        for (int i = 0; i < productCount; i++) {
            inventoryDetails += products[i].turnToString() + " \n "; //turn product details to string
        }
        return inventoryDetails;
    }
}

//Main (InventoryManagementSystem) class
public class InventoryManagementSystem {
    private static Inventory inventory = new Inventory(); //create instance of Inventory class used as reference


    public static void main(String[] args) {
        JFrame frame = new JFrame("Inventory Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        //create text fields for product name, price, quantity
        JTextField nameField = new JTextField(30);
        JTextField priceField = new JTextField(15);
        JTextField quantityField = new JTextField(10);

        //create text area for product details
        JTextArea displayArea = new JTextArea(20, 60);
        displayArea.setEditable(false); // Make display area non-editable

        //set font for labels
        Font labelFont = new Font("Arial", Font.BOLD, 18);

        //create labels
        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setFont(labelFont);
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(labelFont);
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(labelFont);

        //set positions
        nameLabel.setBounds(200, 50, 150, 30);
        nameField.setBounds(350, 50, 200, 30);

        priceLabel.setBounds(200, 100, 150, 30);
        priceField.setBounds(350, 100, 200, 30);

        quantityLabel.setBounds(200, 150, 150, 30);
        quantityField.setBounds(350, 150, 200, 30);

        //create buttons
        JButton addButton = new JButton("Add Product");
        JButton updateButton = new JButton("Update Product");
        JButton displayButton = new JButton("Display Products");

        //font buttons
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        addButton.setFont(buttonFont);
        updateButton.setFont(buttonFont);
        displayButton.setFont(buttonFont);

        //size buttons
        addButton.setPreferredSize(new Dimension(220, 60));
        updateButton.setPreferredSize(new Dimension(220, 60));
        displayButton.setPreferredSize(new Dimension(220, 60));


        //position buttons
        addButton.setBounds(50, 230, 220, 60);
        updateButton.setBounds(280, 230, 220, 60);
        displayButton.setBounds(510, 230, 220, 60);

        //scroll pane for the display area
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(50, 320, 700, 200);

        //add components to the frame
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(priceLabel);
        frame.add(priceField);
        frame.add(quantityLabel);
        frame.add(quantityField);
        frame.add(addButton);
        frame.add(updateButton);
        frame.add(displayButton);
        frame.add(scrollPane);

        //action listener for the add product button
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double price;
                int quantity;
                try {
                    //take price and quantity from input fields
                    price = Double.parseDouble(priceField.getText());
                    quantity = Integer.parseInt(quantityField.getText());
                    //add products and update display area
                    if (inventory.addProduct(name, price, quantity)) {
                        displayArea.setText("Product added: " + name + ", Price: " + price + ", Quantity: " + quantity);
                    } else {
                        displayArea.setText("Failed to add product: Maximum reached.");
                    }
                } catch (NumberFormatException ex) {
                    displayArea.setText("Invalid input for price or quantity."); // Handle invalid input
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double price;
                int quantity;
                try {
                    //take price and quantity from input fields
                    price = Double.parseDouble(priceField.getText());
                    quantity = Integer.parseInt(quantityField.getText());
                    //update product and display result
                    if (inventory.updateProduct(name, price, quantity)) {
                        displayArea.setText("Product updated: " + name);
                    } else {
                        displayArea.setText("Product not found: " + name);
                    }
                } catch (NumberFormatException ex) {
                    displayArea.setText("Invalid input for price or quantity."); // Handle invalid input
                }
            }
        });


        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayArea.setText(inventory.displayAllProducts()); // Display all products in inventory
            }
        });

        frame.setVisible(true);
    }
}
