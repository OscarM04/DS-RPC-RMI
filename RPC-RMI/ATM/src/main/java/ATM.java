import Vist.MainMenu;
//import User.IUser;
//import User.UserController;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ATM {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        MainMenu menu = new MainMenu();
        //Registry registry = LocateRegistry.getRegistry("localhost",6666);
        //IUser userController = (IUser) registry.lookup("UserController");
        //System.out.println(userController.canCreateAccount("59292999"));
        menu.show();
    }
}
