import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Product {
    private String name;
    private double price;
    private int quantity;


    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
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
    public void display () {
        System.out.println("Name: " + getName() + ", Price: " + getPrice() + ", Quantity: " + getQuantity());
    }
    public String turnToString() {
        return "Name: " + getName() + ", Price: " + getPrice() + ", Quantity: " + getQuantity();
    }
}


class Inventory {
    private Product[] products = new Product[3];
    private int productCount = 0;


    public boolean addProduct(String name, double price, int quantity) {
        if (productCount < 10) {
            products[productCount] = new Product(name, price, quantity);
            productCount++;
            return true;
        } else {
            System.out.println("Maximum product quantity reached");
            return false;
        }
    }


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


    public String displayAllProducts() {
        if (productCount == 0) {
            return "No products found";
        }


        String inventoryDetails = "";
        for (int i = 0; i < productCount; i++) {
            inventoryDetails += products[i].turnToString() + " \n ";
        }
        return inventoryDetails;
    }
}


public class InventoryManagementSystem {
    private static Inventory inventory = new Inventory();


    public static void main(String[] args) {
        JFrame frame = new JFrame("Inventory Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);


        JTextField nameField = new JTextField(30);
        JTextField priceField = new JTextField(15);
        JTextField quantityField = new JTextField(10);


        JTextArea displayArea = new JTextArea(20, 60);
        displayArea.setEditable(false);


        Font labelFont = new Font("Arial", Font.BOLD, 18);


        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setFont(labelFont);
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(labelFont);
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(labelFont);


        nameLabel.setBounds(200, 50, 150, 30);
        nameField.setBounds(350, 50, 200, 30);


        priceLabel.setBounds(200, 100, 150, 30);
        priceField.setBounds(350, 100, 200, 30);


        quantityLabel.setBounds(200, 150, 150, 30);
        quantityField.setBounds(350, 150, 200, 30);


        JButton addButton = new JButton("Add Product");
        JButton updateButton = new JButton("Update Product");
        JButton displayButton = new JButton("Display Products");


        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        addButton.setFont(buttonFont);
        updateButton.setFont(buttonFont);
        displayButton.setFont(buttonFont);


        addButton.setPreferredSize(new Dimension(220, 60));
        updateButton.setPreferredSize(new Dimension(220, 60));
        displayButton.setPreferredSize(new Dimension(220, 60));


        addButton.setFocusPainted(false);
        updateButton.setFocusPainted(false);
        displayButton.setFocusPainted(false);


        addButton.setBounds(50, 230, 220, 60);
        updateButton.setBounds(280, 230, 220, 60);
        displayButton.setBounds(510, 230, 220, 60);


        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(50, 320, 700, 200);


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


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double price;
                int quantity;
                try {
                    price = Double.parseDouble(priceField.getText());
                    quantity = Integer.parseInt(quantityField.getText());
                    if (inventory.addProduct(name, price, quantity)) {
                        displayArea.setText("Product added: " + name + ", Price: " + price + ", Quantity: " + quantity);
                    } else {
                        displayArea.setText("Failed to add product: Maximum reached.");
                    }
                } catch (NumberFormatException ex) {
                    displayArea.setText("Invalid input for price or quantity.");
                }
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double price;
                int quantity;
                try {
                    price = Double.parseDouble(priceField.getText());
                    quantity = Integer.parseInt(quantityField.getText());
                    if (inventory.updateProduct(name, price, quantity)) {
                        displayArea.setText("Product updated: " + name);
                    } else {
                        displayArea.setText("Product not found: " + name);
                    }
                } catch (NumberFormatException ex) {
                    displayArea.setText("Invalid input for price or quantity.");
                }
            }
        });


        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText(inventory.displayAllProducts());
            }
        });


        frame.setVisible(true);
    }
}



