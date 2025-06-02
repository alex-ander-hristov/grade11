import javax.swing.*;
import java.awt.*;

public class FlashCards extends JFrame {
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel feedbackLabel;
    private JLabel scoreLabel;

    public FlashcardQuizUI() {
        setTitle("Flashcard Quiz");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        questionLabel = new JLabel("Question will appear here", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        answerField = new JTextField();
        submitButton = new JButton("Submit Answer");
        feedbackLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);

        add(questionLabel);
        add(answerField);
        add(submitButton);
        add(feedbackLabel);
        add(scoreLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new FlashcardQuizUI();
    }
}
