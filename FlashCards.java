import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class FlashCards extends JFrame {
    // Data structure: ArrayList for card storage
    private ArrayList<FlashCard> cardList;
    private int currentCardIndex;

    // GUI Components
    private JTextArea questionArea, answerArea;
    private JLabel statusLabel, timerLabel;
    private JButton showAnswerButton, nextButton, prevButton,
            addButton, editButton, deleteButton, startTimerButton;

    // Timer
    private javax.swing.Timer studyTimer;
    private int secondsRemaining;
    private static final int STUDY_SESSION_DURATION = 300; // 5 minutes

    // File handling
    private static final String DATA_FILE = "flashcards.dat";

    // Constructor
    public FlashCards() {
        super("FlashCards Application");
        cardList = new ArrayList<>();
        currentCardIndex = -1;

        loadCards(); // Load saved cards

        // Initialize GUI
        initializeComponents();
        setupLayout();
        addEventListeners();

        // Show first card if available
        if (!cardList.isEmpty()) {
            currentCardIndex = 0;
            showCurrentCard();
        }

        // Window settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // GUI
    private void initializeComponents() {
        questionArea = new JTextArea(5, 30);
        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setFont(new Font("Arial", Font.PLAIN, 16));

        answerArea = new JTextArea(5, 30);
        answerArea.setEditable(false);
        answerArea.setLineWrap(true);
        answerArea.setWrapStyleWord(true);
        answerArea.setFont(new Font("Arial", Font.PLAIN, 16));
        answerArea.setForeground(Color.BLUE);

        // Buttons for CRUD operations
        showAnswerButton = new JButton("Show Answer");
        nextButton = new JButton("Next");
        prevButton = new JButton("Previous");
        addButton = new JButton("Add Card");
        editButton = new JButton("Edit Card");
        deleteButton = new JButton("Delete Card");

        // Timer components
        startTimerButton = new JButton("Start Study Timer");
        timerLabel = new JLabel("Timer: Not running");
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));

        statusLabel = new JLabel("No cards available. Add a new card to get started.");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    // Set up layout
    private void setupLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Card display area
        JPanel cardPanel = new JPanel(new BorderLayout(10, 10));
        cardPanel.setBorder(BorderFactory.createTitledBorder("Flash Card"));

        JScrollPane questionScroll = new JScrollPane(questionArea);
        questionScroll.setBorder(BorderFactory.createTitledBorder("Question"));
        cardPanel.add(questionScroll, BorderLayout.NORTH);

        JScrollPane answerScroll = new JScrollPane(answerArea);
        answerScroll.setBorder(BorderFactory.createTitledBorder("Answer"));
        cardPanel.add(answerScroll, BorderLayout.CENTER);

        // Button panel (CRUD operations)
        JPanel buttonPanel = new JPanel(new GridLayout(1, 6, 5, 5));
        buttonPanel.add(prevButton);
        buttonPanel.add(showAnswerButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Timer panel
        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.add(startTimerButton, BorderLayout.WEST);
        timerPanel.add(timerLabel, BorderLayout.CENTER);

        // Combine all panels
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(statusLabel, BorderLayout.CENTER);
        topPanel.add(timerPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        add(mainPanel);
    }

    // Event listeners
    private void addEventListeners() {
        // Show answer button
        showAnswerButton.addActionListener(e -> {
            if (currentCardIndex >= 0 && currentCardIndex < cardList.size()) {
                answerArea.setText(cardList.get(currentCardIndex).getAnswer());
            }
        });

        // Navigation buttons
        nextButton.addActionListener(e -> {
            if (cardList.isEmpty()) return;
            currentCardIndex = (currentCardIndex + 1) % cardList.size();
            showCurrentCard();
        });

        prevButton.addActionListener(e -> {
            if (cardList.isEmpty()) return;
            currentCardIndex = (currentCardIndex - 1 + cardList.size()) % cardList.size();
            showCurrentCard();
        });

        // CRUD operations
        addButton.addActionListener(e -> addNewCard());
        editButton.addActionListener(e -> editCurrentCard());
        deleteButton.addActionListener(e -> deleteCurrentCard());

        // Timer control
        startTimerButton.addActionListener(e -> toggleStudyTimer());
    }

    // Display current card
    private void showCurrentCard() {
        if (currentCardIndex >= 0 && currentCardIndex < cardList.size()) {
            FlashCard currentCard = cardList.get(currentCardIndex);
            questionArea.setText(currentCard.getQuestion());
            answerArea.setText("");
            statusLabel.setText("Card " + (currentCardIndex + 1) + " of " + cardList.size());
        }
    }

    // Add new card
    private void addNewCard() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        JTextField questionField = new JTextField(20);
        JTextField answerField = new JTextField(20);

        panel.add(new JLabel("Question:"));
        panel.add(questionField);
        panel.add(new JLabel("Answer:"));
        panel.add(answerField);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Add New Flash Card",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String question = questionField.getText().trim();
            String answer = answerField.getText().trim();

            if (!question.isEmpty() && !answer.isEmpty()) {
                FlashCard newCard = new FlashCard(question, answer);
                cardList.add(newCard);
                currentCardIndex = cardList.size() - 1;
                showCurrentCard();
                saveCards();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Both question and answer must be provided.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Edit card
    private void editCurrentCard() {
        if (currentCardIndex < 0 || currentCardIndex >= cardList.size()) {
            JOptionPane.showMessageDialog(this,
                    "No card selected to edit.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        FlashCard currentCard = cardList.get(currentCardIndex);
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        JTextField questionField = new JTextField(currentCard.getQuestion(), 20);
        JTextField answerField = new JTextField(currentCard.getAnswer(), 20);

        panel.add(new JLabel("Question:"));
        panel.add(questionField);
        panel.add(new JLabel("Answer:"));
        panel.add(answerField);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Edit Flash Card",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String question = questionField.getText().trim();
            String answer = answerField.getText().trim();

            if (!question.isEmpty() && !answer.isEmpty()) {
                currentCard.setQuestion(question);
                currentCard.setAnswer(answer);
                showCurrentCard();
                saveCards();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Both question and answer must be provided.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Delete card
    private void deleteCurrentCard() {
        if (currentCardIndex < 0 || currentCardIndex >= cardList.size()) {
            JOptionPane.showMessageDialog(this,
                    "No card selected to delete.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this flash card?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            cardList.remove(currentCardIndex);
            if (cardList.isEmpty()) {
                currentCardIndex = -1;
                questionArea.setText("");
                answerArea.setText("");
                statusLabel.setText("No cards available. Add a new card to get started.");
            } else {
                currentCardIndex = Math.min(currentCardIndex, cardList.size() - 1);
                showCurrentCard();
            }
            saveCards();
        }
    }

    // Timer control
    private void toggleStudyTimer() {
        if (studyTimer == null) {
            // Start timer
            secondsRemaining = STUDY_SESSION_DURATION;
            updateTimerDisplay();

            studyTimer = new javax.swing.Timer(1000, e -> {
                secondsRemaining--;
                updateTimerDisplay();

                if (secondsRemaining <= 0) {
                    studyTimer.stop();
                    studyTimer = null;
                    JOptionPane.showMessageDialog(this,
                            "Study session complete! Time to take a break.",
                            "Timer Finished", JOptionPane.INFORMATION_MESSAGE);
                    startTimerButton.setText("Start Study Timer");
                }
            });

            studyTimer.start();
            startTimerButton.setText("Stop Timer");
        } else {
            // Stop timer
            studyTimer.stop();
            studyTimer = null;
            timerLabel.setText("Timer: Not running");
            startTimerButton.setText("Start Study Timer");
        }
    }

    // Update timer display
    private void updateTimerDisplay() {
        int minutes = secondsRemaining / 60;
        int seconds = secondsRemaining % 60;
        timerLabel.setText(String.format("Timer: %02d:%02d remaining", minutes, seconds));
    }

    // Save cards to file
    private void saveCards() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(cardList);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Error saving flash cards: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Load cards from file (REQUIREMENT: File Handling)
    private void loadCards() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
                cardList = (ArrayList<FlashCard>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this,
                        "Error loading flash cards: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlashCards());
    }
}


class FlashCard implements Serializable {
    private static final long serialVersionUID = 1L;
    private String question;
    private String answer;

    public FlashCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Getters and setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String toString() {
        return "Q: " + question + "\nA: " + answer;
    }
}
