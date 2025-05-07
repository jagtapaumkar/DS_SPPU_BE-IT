import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) {
        try {
            BankAccountImpl bankAccount = new BankAccountImpl();
            BankAccount stub = (BankAccount) UnicastRemoteObject.exportObject(bankAccount, 0);

            Registry registry = LocateRegistry.createRegistry(1099); // Start RMI registry on port 1099
            registry.rebind("BankAccount", stub); // Bind the remote object with the name "BankAccount"

            System.out.println("Server is ready and waiting for client...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
