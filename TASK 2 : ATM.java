import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    private Map<String, User> users;
    private User currentUser;
    private Scanner scanner;

    public ATM() {
        users = new HashMap<>();
        users.put("1234", new User("Sashi", "1234", 1000));
        users.put("5678", new User("Saravanan", "5678", 500));
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter User PIN: ");
        String userPin = scanner.nextLine();

        if (authenticateUser(userId, userPin)) {
            System.out.println("Authentication successful. Welcome, " + currentUser.getName() + "!");
            displayMenu();
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
    }

    private boolean authenticateUser(String userId, String userPin) {
        if (users.containsKey(userId)) {
            User user = users.get(userId);
            if (user.getPin().equals(userPin)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    private void displayMenu() {
        boolean quit = false;

        while (!quit) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    displayTransactionHistory();
                    break;
                case 6:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void checkBalance() {
        System.out.println("Current Balance: $" + currentUser.getBalance());
    }

    private void withdraw() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        if (amount <= currentUser.getBalance()) {
            currentUser.withdraw(amount);
            System.out.println("Withdrawal of $" + amount + " successful.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private void deposit() {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        currentUser.deposit(amount);
        System.out.println("Deposit of $" + amount + " successful.");
    }

    private void transfer() {
        System.out.print("Enter the recipient's User ID: ");
        String recipientId = scanner.nextLine();
        User recipient = users.get(recipientId);

        if (recipient != null) {
            System.out.print("Enter the amount to transfer: $");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            if (amount <= currentUser.getBalance()) {
                currentUser.withdraw(amount);
                recipient.deposit(amount);
                System.out.println("Transfer of $" + amount + " to " + recipient.getName() + " successful.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Recipient User ID not found.");
        }
    }

    private void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : currentUser.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}

class User {
    private String name;
    private String pin;
    private double balance;
    private StringBuilder transactionHistory;

    public User(String name, String pin, double balance) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        transactionHistory = new StringBuilder();
    }

    public String getName() {
        return name;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        balance -= amount;
        transactionHistory.append("Withdrawal of $").append(amount).append("\n");
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.append("Deposit of $").append(amount).append("\n");
    }

    public void addTransaction(String transaction) {
        transactionHistory.append(transaction).append("\n");
    }

    public String[] getTransactionHistory() {
        return transactionHistory.toString().split("\n");
    }
}

