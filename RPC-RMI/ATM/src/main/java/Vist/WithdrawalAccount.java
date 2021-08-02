package Vist;

import Bank.IBank;
import User.IUser;
import exceptions.CustomException;

import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class WithdrawalAccount {

    private IUser userController;
    private IBank bankController;

    public void setUserController(IUser userController) {
        this.userController = userController;
    }

    public void setBankController(IBank bankController) {
        this.bankController = bankController;
    }

    public void show(String  username) throws RemoteException, CustomException {
        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        int option; //Guardaremos la opcion del usuario
        float amountWithdrawal;

        while (!exit) {
            System.out.println("************* Mini-banco: Retiro de cuenta *************");
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
                System.out.print("Selecciona la cuenta a retirar: ");
                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        amountWithdrawal = this.getWithdrawalAmount();
                        if(amountWithdrawal > 0){
                            List<String> result = bankController.withdrawal(amountWithdrawal,accountList.get(0));
                            for (String toPrint :
                                    result) {
                                System.out.println(toPrint);
                            }
                        }else{
                            System.out.println("Debes ingresar un monto superior a 0");
                            exit = true;
                        }
                        break;
                    case 2:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        if ( accountList.get(1) == null) break;
                        amountWithdrawal = this.getWithdrawalAmount();
                        if(amountWithdrawal > 0){
                            List<String> result = bankController.withdrawal(amountWithdrawal,accountList.get(1));
                            for (String toPrint :
                                    result) {
                                System.out.println(toPrint);
                            }
                        }else{
                            System.out.println("Debes ingresar un monto superior a 0");
                            exit = true;
                        }
                        break;
                    case 3:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        if ( accountList.get(2) == null) break;
                        amountWithdrawal = this.getWithdrawalAmount();
                        if(amountWithdrawal > 0){
                            List<String> result = bankController.withdrawal( amountWithdrawal,accountList.get(2));
                            for (String toPrint :
                                    result) {
                                System.out.println(toPrint);
                            }
                        }else{
                            System.out.println("Debes ingresar un monto superior a 0");
                            exit = true;
                        }
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


    public float getWithdrawalAmount(){
        String amountString;
        float amount;
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Ingresa el monto a retirar: ");
        amountString = keyboard.nextLine();

        try {
            amount = Float.parseFloat(amountString);
            if (amount <= 0) return 0;
            return amount;
        } catch (NumberFormatException e) {
            System.out.println("Debes insertar un número");
            return 0;
        }
    }

}
