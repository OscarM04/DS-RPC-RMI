package atm;

import Bank.IBank;
import User.IUser;
import exceptions.CustomException;

import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AccountMenu {
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
        float amountDeposit;

        while (!exit) {
            System.out.println("************* Mini-banco: Deposito a cuentas *************");
            List<Long> accountList = userController.listAccounts( username);
            int i = 1;
            for (long account:accountList
            ) {
                System.out.println(i +". Cuenta: "+ account);
                i++;
            }

            System.out.println("7. A cuentas de terceros");
            System.out.println("0. Salir");
            System.out.println("************* ******************************* *************");
            try {
                System.out.print("Escribe una de las opciones: ");
                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        this.getDepostAmount( accountList.get(0));
                        exit = true;
                        break;
                    case 2:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        if ( accountList.get(1) == null) break;
                        this.getDepostAmount( accountList.get(1));
                        exit = true;
                        break;
                    case 3:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        if ( accountList.get(2) == null) break;
                        this.getDepostAmount( accountList.get(2));
                        exit = true;
                        break;
                    case 7:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        this.thirdAccountMenuShow();
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
            }catch (IndexOutOfBoundsException e){

            }
        }
    }

    public void thirdAccountMenuShow() throws RemoteException {
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


        if( accountData != null){
            String continueStep;
            System.out.println("Dueño: " + accountData.get(0) + "- Usuario:"+ accountData.get(1));
            System.out.print("Deseas continuar con el proceso:(responde 's' o 'si' para continuar)");
            continueStep = keyboard.nextLine();
            if(continueStep.equals("s") || continueStep.equals("si")){
                this.getDepostAmount(Long.parseLong(accountNumber));
            }
        }
    }


    public void getDepostAmount( long accountNumber){
        String amountString;
        String description;
        float amount;
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Ingresa la descripcion del deposito: ");
        description = keyboard.nextLine();
        System.out.print("Ingresa el monto a depositar: ");
        amountString = keyboard.nextLine();

        try {
            amount = Float.parseFloat(amountString);
            List<String> result = bankController.deposit( amount , description, accountNumber);
            for (String toPrint :
                    result) {
                System.out.println(toPrint);
            }
        } catch (NumberFormatException e) {
            System.out.println("Debes insertar un número");

        } catch (CustomException | RemoteException e) {
        }
    }
}
