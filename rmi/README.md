Certainly! Here's the complete `README.md` file with all the **Java RMI BankAccount application** code included for reference.

---

### 📄 README.md

```markdown
# 🏦 Java RMI Bank Account Application

This project demonstrates a simple **Remote Method Invocation (RMI)** application in Java that simulates basic banking operations such as `deposit`, `withdraw`, and `getBalance`.

---

## 📁 Project Structure

```

rmi/
├── BankAccount.java           # Remote Interface
├── BankAccountImpl.java       # Implementation of Remote Interface
├── Server.java                # RMI Server
├── Client.java                # RMI Client

````

---

## 🧠 Features

- Remote method invocation using Java RMI
- Basic banking operations: deposit, withdraw, check balance
- Client-server architecture using RMI registry

---

## 🛠️ Requirements

- Java Development Kit (JDK) 8 or higher
- Command Prompt or Terminal

---

## 📦 Java Code

### `BankAccount.java`
```java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankAccount extends Remote {
    void deposit(int amount) throws RemoteException;
    void withdraw(int amount) throws RemoteException;
    double getBalance() throws RemoteException;
}
````

---

### `BankAccountImpl.java`

```java
import java.rmi.RemoteException;

public class BankAccountImpl implements BankAccount {
    private double amount = 50;

    @Override
    public synchronized void deposit(int amount) throws RemoteException {
        this.amount += amount;
    }

    @Override
    public synchronized void withdraw(int amount) throws RemoteException {
        this.amount -= amount;
    }

    @Override
    public synchronized double getBalance() throws RemoteException {
        return amount;
    }
}
```

---

### `Server.java`

```java
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) {
        try {
            BankAccountImpl bankAccount = new BankAccountImpl();
            BankAccount exportObject = (BankAccount) UnicastRemoteObject.exportObject(bankAccount, 0);

            Registry registry = LocateRegistry.createRegistry(1099);

            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":1099/BankAccount";
            Naming.rebind(url, exportObject);

            System.out.println("Server is ready and waiting for client...");
        } catch (RemoteException | UnknownHostException | MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Error while trying to connect to BankAccount object");
        }
    }
}
```

---

### `Client.java`

```java
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        int amount = 10;

        try {
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":1099/BankAccount";
            BankAccount bankAccount = (BankAccount) Naming.lookup(url);

            System.out.println("Current balance: " + bankAccount.getBalance());
            System.out.println("Depositing → " + amount);
            bankAccount.deposit(amount);
            System.out.println("Balance after deposit: " + bankAccount.getBalance());

            System.out.println("Withdrawing → " + amount);
            bankAccount.withdraw(amount);
            System.out.println("Balance after withdrawal: " + bankAccount.getBalance());

        } catch (UnknownHostException | MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
```

---

## 🧪 How to Compile and Run

### 1. Navigate to the project directory:

```bash
cd C:\Users\ASUS\Desktop\rmi
```

### 2. Compile all `.java` files:

```bash
javac *.java
```

### 3. Run the server in a **new terminal window**:

```bash
java Server
```

### 4. Run the client in another **new terminal window**:

```bash
java Client
```

---

## ✅ Sample Output

```
Current balance: 50.0
Depositing → 10
Balance after deposit: 60.0
Withdrawing → 10
Balance after withdrawal: 50.0
```

---

## 📌 Notes

* Ensure port **1099** is free before running the server.
* If you get `Port already in use`, kill the process using the port or change the port in both `Server.java` and `Client.java`.
* Always run the **Server before Client**.
* You can open multiple terminals and run `Client` to simulate concurrent access.

---

