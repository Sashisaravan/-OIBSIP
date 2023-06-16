import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class NumGuess extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel promptLabel;
    private JTextField inputField;
    private JButton guessButton;
    private JTextArea resultArea;
    private int targetNumber;
    private int maxAttempts;
    private int score;
    private int attempts;

    public NumGuess() {
        setTitle("Number Guessing Game");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        promptLabel = new JLabel("Enter your guess:");
        inputField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultArea = new JTextArea(10, 60);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        setLayout(new FlowLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(promptLabel);
        topPanel.add(inputField);
        topPanel.add(guessButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(scrollPane);

        add(topPanel);
        add(bottomPanel);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });
    }

    public void startGame(int minRange, int maxRange, int maxAttempts) {
        Random random = new Random();
        targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
        score = 0;
        attempts = 0;

        resultArea.setText("");
        promptLabel.setText("Enter your guess:");
        inputField.setText("");
        this.maxAttempts = maxAttempts;

        setVisible(true);
    }

    private void processGuess() {
        int guess;
        try {
            guess = Integer.parseInt(inputField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            return;
        }

        attempts++;
        if (guess == targetNumber) {
            resultArea.append("Congratulations! You guessed the correct number.\n");
            score += maxAttempts - attempts + 1;
            displayScore();
            playAgain();
        } else if (guess < targetNumber) {
            resultArea.append("The number is higher than your guess. Attempts remaining: " + (maxAttempts - attempts) + "\n");
        } else {
            resultArea.append("The number is lower than your guess. Attempts remaining: " + (maxAttempts - attempts) + "\n");
        }

        if (attempts == maxAttempts) {
            resultArea.append("Game over! You have used all your attempts.\n");
            resultArea.append("The correct number was: " + targetNumber + "\n");
            displayScore();
            playAgain();
        }

        inputField.setText("");
    }

    private void displayScore() {
        resultArea.append("Your current score is: " + score + "\n");
    }

    private void playAgain() {
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            startGame(1, 100, 5);
        } else {
            JOptionPane.showMessageDialog(null, "Thank you for playing the Number Guessing Game!\nYour final score is: " + score);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                NumGuess game = new NumGuess();
                game.startGame(1, 100, 5);
            }
        });
    }
}
