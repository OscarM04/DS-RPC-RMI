import Bank.IBank;
import User.IUser;
import Vist.MainMenu;
import User.UserController;
import entitys.TransactionEntity;
import exceptions.CustomException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;


public class ATM {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, CustomException {
        MainMenu menu = new MainMenu();
        Registry registry = LocateRegistry.getRegistry("localhost",6666);
        IUser userController = (IUser) registry.lookup("UserController");
        //System.out.println(userController.canCreateAccount(59292999));
        //System.out.println(userController.listAccounts(59292999));
        IBank bankController = (IBank) registry.lookup("BankController");
        try {
            //System.out.println( bankController.transaction(50,"pago pollo", 58005555, 56333555));
             List<TransactionEntity> list = bankController.viewAccount(5633555);
            for (TransactionEntity entity:list) {
                System.out.println(entity);
            }
            System.out.println( list);
        }catch (CustomException e ){
            System.out.println( e.getMessage());
        }
        //List<TransactionEntity> list = bankController.viewAccount(56333555);
        //bankController.deposit( 56333555, 27952663);
        //userController.checkAnotherAccount( 56333555, 27952663 );
        //menu.show();
    }
}
