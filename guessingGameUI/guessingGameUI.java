import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class guessingGameUI {
    static int attempts = 0;


    public static void main(String[] args) {
        JFrame window = new JFrame("Number Guessing Game");
        window.setSize(400, 300);
        window.setLayout(new GridLayout(4, 1));


        JPanel instructionPanel = new JPanel(new FlowLayout());
        JLabel instructions = new JLabel("Guess the number between 1 and 100!");
        instructionPanel.add(instructions);
        window.add(instructionPanel);


        JPanel inputPanel = new JPanel(new FlowLayout());
        JTextField guessField = new JTextField(10);
        inputPanel.add(new JLabel("Enter your guess:"));
        inputPanel.add(guessField);
        window.add(inputPanel);


        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton guessButton = new JButton("Guess");
        JButton quitButton = new JButton("Quit");
        buttonPanel.add(guessButton);
        buttonPanel.add(quitButton);
        window.add(buttonPanel);


        JPanel resultPanel = new JPanel(new FlowLayout());
        JLabel resultLabel = new JLabel("Result will be displayed here.");
        resultPanel.add(resultLabel);
        window.add(resultPanel);


        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;


        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userInput = guessField.getText();


                if ((userInput) != null && userInput.length() > 0) {
                    int guess = Integer.parseInt(userInput);
                    attempts++;


                    if (guess < randomNumber) {
                        resultLabel.setText("Your guess is too low! Attempts: " + attempts);
                    } else if (guess > randomNumber) {
                        resultLabel.setText("Your guess is too high! Attempts: " + attempts);
                    } else {
                        resultLabel.setText("Congratulations! You guessed the number in " + attempts + " attempts.");
                    }
                } else {
                    resultLabel.setText("Please enter a valid number.");
                }
                guessField.setText("");
            }
        });
        
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultLabel.setText("The secret number was: " + randomNumber);
            }
        });
    }

}
