package bank;
import Bank.BankController;
import User.UserController;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BANK {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        Registry registry = LocateRegistry.createRegistry(6666);
        //System.setProperty("java.rmi.server.hostname","127.0.0.1");
        registry.rebind("UserController", new UserController());
        registry.rebind("BankController", new BankController());
        System.out.println("Server Ready");
    }
}
