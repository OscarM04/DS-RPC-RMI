package atm;

import Bank.IBank;
import User.IUser;
import exceptions.CustomException;

import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TransferMenu {

    private IUser userController;
    private IBank bankController;

    public void setUserController(IUser userController) {
        this.userController = userController;
    }

    public void setBankController(IBank bankController) {
        this.bankController = bankController;
    }

    public void show(String username) throws RemoteException, CustomException {
        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        int option; //Guardaremos la opcion del usuario
        float amountDeposit;

        while (!exit) {
            System.out.println("************* Mini-banco: Transferencia entre cuentas *************");
            List<Long> accountList = userController.listAccounts( username);
            int i = 1;
            for (long account:accountList
            ) {
                System.out.println(i +". Consultar: "+ account);
                i++;
            }
            System.out.println("0. Salir");
            System.out.println("************* ******************************* *************");
            try {
                System.out.print("Escribe la cuenta de donde quieres transferir: ");
                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        long sourceAccount = accountList.get(0);
                        accountList.remove( 0);

                        this.destinyAccount(sourceAccount, accountList);
                        break;
                    case 2:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        if ( accountList.get(1) == null) break;
                        long sourceAccount1 = accountList.get(1);
                        accountList.remove( 1);
                        this.destinyAccount( sourceAccount1, accountList);
                        break;
                    case 3:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        if ( accountList.get(2) == null) break;
                        long sourceAccount2 = accountList.get(2);
                        accountList.remove( 2);
                        this.destinyAccount( sourceAccount2, accountList);
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

    public void destinyAccount(long originAccount, List<Long> accounts) throws RemoteException, CustomException {
        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        int option; //Guardaremos la opcion del usuario

        //EN TEORIA ES IMPRIMIR LAS CUENTAS QUE TENGA SOBRANTE, OSEA 0 A 2, si tiene 0 solo estaria la opcion a terceros
        while (!exit) {
            System.out.println("************* Mini-banco: Transferencia entre cuentas *************");
            int i = 1;
            for (long account:accounts
            ) {
                System.out.println(i +". Consultar: "+ account);
                i++;
            }
            System.out.println("7. Cuenta de terceros");
            System.out.println("0. Salir");
            System.out.println("************* ******************************* *************");
            try {
                System.out.print("Seleccione la cuenta de destino: ");
                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        this.getTransferAmount(originAccount, accounts.get(0));
                        exit = true;
                        break;
                    case 2:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        this.getTransferAmount(originAccount, accounts.get(1));
                        exit = true;
                        break;
                    case 7:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        this.thirdAccountMenuShow( originAccount);
                        exit = true;
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
            }
        }
    }

    public void thirdAccountMenuShow(long originAccount) throws RemoteException, CustomException {
        String accountNumber;
        String document;

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Ingresa los datos de la cuenta de terceros:");
        System.out.print("Número de cuenta: ");
        accountNumber = keyboard.nextLine();
        System.out.print("Documento de identidad del titular: ");
        document = keyboard.nextLine();

        List<String> accountData = userController.checkAnotherAccount(
                Long.parseLong(accountNumber.trim()), Long.parseLong(document.trim())
        );


        if (accountData != null) {
            String continueStep;
            System.out.println("Dueño de la cuenta fulanito de tal");
            System.out.print("Deseas continuar con el proceso:(responde 's' o 'si' para continuar)");
            continueStep = keyboard.nextLine();
            if (continueStep.equals("s") || continueStep.equals("si")) {
                this.getTransferAmount(originAccount, Long.parseLong(accountNumber));
            }
        }
    }


    public void getTransferAmount(long accountNumber, long destination) throws RemoteException, CustomException {
        String amountString;
        float amount;
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Ingresa el monto a transferir: ");
        amountString = keyboard.nextLine();

        try {
            amount = Float.parseFloat(amountString);
            List<String> result = bankController.transaction(amount,"",accountNumber,destination);
            for (String toPrint :
                    result) {
                System.out.println(toPrint);
            }
        } catch (NumberFormatException e) {
            System.out.println("Debes insertar un número");
        }
    }
}
