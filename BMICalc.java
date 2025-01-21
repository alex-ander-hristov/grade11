import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//BMICalculator class
class BMICalculator {
    private double weight;
    private double height;

    //constructor to initialize weight and height
    public BMICalculator(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    //calculate BMI based on weight and height
    public double calculateBMI() {
        return weight / (height * height);
    }

    //determine BMI category based on the BMI value
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

//BMICalc class - create GUI for BMI calculation
public class BMICalc extends JFrame {
    private JTextField weightField, heightField;
    private JLabel resultLabel, bmiCategoryLabel, errorMessageLabel;

    //constructor to initialize the GUI
    public BMICalc() {
        setTitle("BMI Calculator");
        setSize(350, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        //creating labels and text fields for weight and height
        JLabel weightLabel = new JLabel("Enter Weight (kg): ");
        weightField = new JTextField(10);
        JLabel heightLabel = new JLabel("Enter Height (m): ");
        heightField = new JTextField(10);

        //creating button to calculate BMI and labels for displaying results
        JButton calculateButton = new JButton("Calculate BMI");
        resultLabel = new JLabel("BMI: ");
        bmiCategoryLabel = new JLabel("Category: ");
        errorMessageLabel = new JLabel();

        //positions/sizes
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

                //clear previous error messages
                errorMessageLabel.setText("");

                //check if weight/height fields are empty
                if (weightText.isEmpty() || heightText.isEmpty()) {
                    errorMessageLabel.setText("Please enter weight and height.");
                    return;
                }

                //check if entered values are valid
                if (!isValidNumber(weightText) || !isValidNumber(heightText)) {
                    errorMessageLabel.setText("Please enter valid values.");
                    return;
                }

                //take the weight and height from text fields
                double weight = Double.parseDouble(weightText);
                double height = Double.parseDouble(heightText);

                //check if weight and height are positive
                if (weight <= 0 || height <= 0) {
                    errorMessageLabel.setText("Weight and Height must be positive numbers.");
                    return;
                }

                //create BMICalculator instance and calculate BMI
                BMICalculator bmiCalc = new BMICalculator(weight, height);
                double bmi = bmiCalc.calculateBMI();
                String category = bmiCalc.getBMICategory(bmi);

                //display BMI and category
                resultLabel.setText("BMI: " + bmi);
                bmiCategoryLabel.setText("You are: " + category);
            }
        });

        //add components to frame
        add(weightLabel);
        add(weightField);
        add(heightLabel);
        add(heightField);
        add(calculateButton);
        add(resultLabel);
        add(bmiCategoryLabel);
        add(errorMessageLabel);
    }

    //run the application
    public static void main(String[] args) {
        BMICalc frame = new BMICalc();
        frame.setVisible(true);
    }

    //check if string can be converted to a number
    private boolean isValidNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
