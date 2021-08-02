package Vist;

import Bank.IBank;
import User.IUser;
import entitys.TransactionEntity;
import exceptions.CustomException;

import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsultAccountMenu{

    private IUser userController;
    private IBank bankController;

    public void setUserController(IUser userController) {
        this.userController = userController;
    }

    public void setBankController(IBank bankController) {
        this.bankController = bankController;
    }

    public void show(String username) throws RemoteException {
        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        int option; //Guardaremos la opcion del usuario

        while (!exit) {
            System.out.println("************* Mini-banco: Menú de transacciones *************");
            List<Long> accountList = userController.listAccounts( username);
            int i = 1;
            for (long account:accountList
                 ) {
                System.out.println(i +". Cuenta: "+ account);
                i++;
            }
            System.out.println("0. Salir");
            System.out.println("************* ******************************* *************");
            try {
                System.out.print("Escribe una de las opciones: ");
                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        List<TransactionEntity> transactions = bankController.viewAccount(accountList.get(0));
                        for (TransactionEntity transaction :
                                transactions) {
                            System.out.println(
                                    transaction
                            );
                        }
                        System.out.println("Balance: " + userController.accountBalance( accountList.get(0)));
                        break;
                    case 2:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        if ( accountList.get(1) == null) break;
                        List<TransactionEntity> transactions2 = bankController.viewAccount(accountList.get(1));
                        for (TransactionEntity transaction :
                                transactions2) {
                            System.out.println(
                                    transaction
                            );
                        }
                        System.out.println("Balance: " + userController.accountBalance(accountList.get(1)));
                        break;
                    case 3:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        if ( accountList.get(2) == null) break;
                        List<TransactionEntity> transactions3 = bankController.viewAccount(accountList.get(2));
                        for (TransactionEntity transaction :
                                transactions3) {
                            System.out.println(
                                    transaction
                            );
                        }
                        System.out.println("Balance: " + userController.accountBalance(accountList.get(2)));
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Opcion Invalida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            } catch (CustomException | IndexOutOfBoundsException e){

            }
        }
    }
}
