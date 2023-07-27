import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TransactionHistory {
    private List<String> tns;

    public TransactionHistory() {
        tns = new ArrayList<>();
    }

    public void addTransaction(String transaction) {
        tns.add(transaction);
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        if (tns.isEmpty()) {
            System.out.println("Sorry , No Transaction Found.");
        } else {
            for (String transaction : tns) {
                System.out.println(transaction);
            }
        }
        System.out.println();
    }
}

class Withdraw {
    public static void withdraw(Account Account, double amt) {
        if (amt <= Account.getBalance()) {
            Account.setBalance(Account.getBalance() - amt);
            String transaction = "Withdraw: Rs." + amt;
            Account.getTransactionHistory().addTransaction(transaction);
            System.out.println("Successfully withdraw Rs." + amt);
        } else {
            System.out.println("You Have Insufficient Balance.Cann't be withdraw Rs." + amt);
        }
    }
}

class Deposit {
    public static void deposit(Account Account, double amt) {
        Account.setBalance(Account.getBalance() + amt);
        String transaction = "Deposit: Rs." + amt;
        Account.getTransactionHistory().addTransaction(transaction);
        System.out.println("Successfully Deposited Money By ATM Rs." + amt);
    }
}

class Transfer {
    public static void transfer(Account sender, Account customer, double amt) {
        if (amt <= sender.getBalance()) {
            sender.setBalance(sender.getBalance() - amt);
            customer.setBalance(customer.getBalance() + amt);

            String transaction = "Transfer: Rs." + amt + " from " + sender.getUserId() + " to " + customer.getUserId();
            sender.getTransactionHistory().addTransaction(transaction);
            customer.getTransactionHistory().addTransaction(transaction);

            System.out.println("Successfully  Money Transferred Rs." + amt + " to " + customer.getUserId());
        } else {
            System.out.println(" You Have Insufficient Balance. Cann't be Transferred Rs." + amt);
        }
    }
}

class Quit {
    public static void quit() {
        System.out.println("Thank you for using the ATM!");
        System.exit(0);
    }
}

class Account {
    private String userId;
    private String pin;
    private double balance;
    private TransactionHistory th;

    public Account(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
        this.balance = 0.0;
        this.th = new TransactionHistory();
    }

    public String getUserId() {
        return userId;
    }

    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public TransactionHistory getTransactionHistory() {
        return th;
    }
}

public class Atm {
    private static final String Login_Id = "satyaatm";
    private static final String Password = "2023";
    private Account Account;

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the ATM!");

        System.out.print("Login ID: ");
        String userId = sc.nextLine();

        System.out.print("Password: ");
        String pin = sc.nextLine();

        if (userId.equals(Login_Id) && pin.equals(Password)) {
            Account = new Account(userId, pin);
            boolean continueTransaction = true;

            while (continueTransaction) {
                System.out.println("\nPlease Choose an option:");
                System.out.println("1. transaction History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        Account.getTransactionHistory().displayTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter the amount to withdraw: Rs.");
                        double withdrawamt = sc.nextDouble();
                        Withdraw.withdraw(Account, withdrawamt);
                        break;
                    case 3:
                        System.out.print("Enter the amount to deposit: Rs. ");
                        double depositamt = sc.nextDouble();
                        Deposit.deposit(Account, depositamt);
                        break;
                    case 4:
                        System.out.print("Enter the customer's User ID: ");
                        String CustomerId = sc.next();
                        System.out.print("Enter the amount to transfer: Rs.");
                        double transferamt = sc.nextDouble();
                        Account customerAccount = new Account(CustomerId, "");
                        Transfer.transfer(Account, customerAccount, transferamt);
                        break;
                    case 5:
                        Quit.quit();
                        break;
                    default:
                        System.out.println("Invalid choice.Retry...!!!");
                }
            }
        } else {
            System.out.println("Invalid user ID or PIN. Access denied.");
        }
    }

    public static void main(String[] args) {
        Atm atm = new Atm();
        atm.start();
    }
}
