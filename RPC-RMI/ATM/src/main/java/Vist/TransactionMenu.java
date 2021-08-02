package Vist;

import Bank.IBank;
import User.IUser;
import exceptions.CustomException;

import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class TransactionMenu {

    private IUser userController;
    private IBank bankController;

    public void setUserController(IUser userController) {
        this.userController = userController;
    }

    public void setBankController(IBank bankController) {
        this.bankController = bankController;
    }

    public void show(String username) throws RemoteException, CustomException {
        AccountMenu accountMenu = new AccountMenu();
        ConsultAccountMenu consultAccountMenu = new ConsultAccountMenu();
        WithdrawalAccount withdrawalAccount = new WithdrawalAccount();
        TransferMenu transferMenu = new TransferMenu();
        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        int option; //Guardaremos la opcion del usuario

        while (!exit) {
            System.out.println("************* Mini-banco: Menú de transacciones *************");
            System.out.println("1. Consultar cuenta");
            System.out.println("2. Deposito a cuenta");
            System.out.println("3. Retiro de cuenta");
            System.out.println("4. Transferencia entre cuentas");
            System.out.println("0. Salir");
            System.out.println("************* ******************************* *************");
            try {
                System.out.print("Escribe una de las opciones: ");
                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        consultAccountMenu.setBankController( this.bankController);
                        consultAccountMenu.setUserController( this.userController);
                        consultAccountMenu.show( username);
                        break;
                    case 2:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        accountMenu.setBankController( this.bankController);
                        accountMenu.setUserController( this.userController);
                        accountMenu.show( username);
                        break;
                    case 3:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        withdrawalAccount.setBankController( this.bankController);
                        withdrawalAccount.setUserController( this.userController);
                        withdrawalAccount.show( username);
                        break;
                    case 4:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        transferMenu.setBankController( this.bankController);
                        transferMenu.setUserController( this.userController);
                        transferMenu.show( username);
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
            }catch (IndexOutOfBoundsException e){

            }
        }
    }


}
