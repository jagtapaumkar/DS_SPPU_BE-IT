import java.rmi.RemoteException;

public class BankAccountImpl implements BankAccount {
    private double amount = 50;

    @Override
    public void deposit(int amount) throws RemoteException {
        this.amount += amount;
    }

    @Override
    public void withdraw(int amount) throws RemoteException {
        this.amount -= amount;
    }

    @Override
    public double getBalance() throws RemoteException {
        return amount;
    }
}
