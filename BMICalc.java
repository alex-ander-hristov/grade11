import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class BMICalculator {
    private double weight;
    private double height;


    public BMICalculator(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }


    public double calculateBMI() {
        return weight / (height * height);
    }


    public String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24.9) {
            return "Normal weight";
        } else if (bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}


public class BMICalc extends JFrame {
    private JTextField weightField, heightField;
    private JLabel resultLabel, bmiCategoryLabel, errorMessageLabel;


    public Main() {
        setTitle("BMI Calculator");
        setSize(350, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);


        JLabel weightLabel = new JLabel("Enter Weight (kg): ");
        weightField = new JTextField(10);
        JLabel heightLabel = new JLabel("Enter Height (m): ");
        heightField = new JTextField(10);


        JButton calculateButton = new JButton("Calculate BMI");
        resultLabel = new JLabel("BMI: ");
        bmiCategoryLabel = new JLabel("Category: ");
        errorMessageLabel = new JLabel();


        weightLabel.setBounds(30, 30, 150, 30);
        weightField.setBounds(180, 30, 120, 30);
        heightLabel.setBounds(30, 70, 150, 30);
        heightField.setBounds(180, 70, 120, 30);
        calculateButton.setBounds(100, 110, 150, 30);
        resultLabel.setBounds(30, 160, 200, 30);
        bmiCategoryLabel.setBounds(30, 200, 200, 30);
        errorMessageLabel.setBounds(30, 240, 250, 30);


        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String weightText = weightField.getText();
                String heightText = heightField.getText();


                errorMessageLabel.setText("");


                if (weightText.isEmpty() || heightText.isEmpty()) {
                    errorMessageLabel.setText("Please enter weight and height.");
                    return;
                }


                if (!isValidNumber(weightText) || !isValidNumber(heightText)) {
                    errorMessageLabel.setText("Please enter valid values.");
                    return;
                }


                double weight = Double.parseDouble(weightText);
                double height = Double.parseDouble(heightText);


                if (weight <= 0 || height <= 0) {
                    errorMessageLabel.setText("Weight and Height must be positive numbers.");
                    return;
                }


                BMICalculator bmiCalc = new BMICalculator(weight, height);
                double bmi = bmiCalc.calculateBMI();
                String category = bmiCalc.getBMICategory(bmi);


                resultLabel.setText("BMI: " + bmi);
                bmiCategoryLabel.setText("You are: " + category);
            }
        });


        add(weightLabel);
        add(weightField);
        add(heightLabel);
        add(heightField);
        add(calculateButton);
        add(resultLabel);
        add(bmiCategoryLabel);
        add(errorMessageLabel);
    }


    public static void main(String[] args) {
        Main frame = new Main();
        frame.setVisible(true);
    }


    private boolean isValidNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
