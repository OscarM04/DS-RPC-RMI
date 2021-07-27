import User.IUser;
import User.UserController;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BANK {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        Registry registry = LocateRegistry.createRegistry(6666);
        //System.setProperty("java.rmi.server.hostname","127.0.0.1");
        registry.rebind("UserController", new UserController());
        System.out.println("Server Ready");
    }
}
