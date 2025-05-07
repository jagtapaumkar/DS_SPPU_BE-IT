import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static final int amount = 10;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            BankAccount bankAccount = (BankAccount) registry.lookup("BankAccount");

            System.out.println("Current balance: " + bankAccount.getBalance());

            System.out.println("Depositing → " + amount);
            bankAccount.deposit(amount);
            System.out.println("Balance after deposit: " + bankAccount.getBalance());

            System.out.println("Withdrawing → " + amount);
            bankAccount.withdraw(amount);
            System.out.println("Balance after withdrawal: " + bankAccount.getBalance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
