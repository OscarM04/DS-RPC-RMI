package atm;
import Bank.IBank;
import User.IUser;
import exceptions.CustomException;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ATM {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, CustomException {
        MainMenu menu = new MainMenu();
        Registry registry = LocateRegistry.getRegistry("localhost",6666);
        IUser userController = (IUser) registry.lookup("UserController");
        IBank bankController = (IBank) registry.lookup("BankController");
        try {
            menu.setBankController(bankController);
            menu.setUserController(userController);
            menu.show();
        }catch (CustomException e){
            System.out.println(e.getMessage());
        } catch (NumberFormatException e ){
        }
    }

}
