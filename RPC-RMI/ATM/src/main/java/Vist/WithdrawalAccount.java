package Vist;

import java.util.InputMismatchException;
import java.util.Scanner;

public class WithdrawalAccount {

    public void show(){
        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        int option; //Guardaremos la opcion del usuario
        float amountWithdrawal;

        while (!exit) {
            System.out.println("************* Mini-banco: Retiro de cuenta *************");
            System.out.println("1. Cuenta *****-123 500$");
            System.out.println("2. Cuenta *****-123 1200$");
            System.out.println("3. Cuenta *****-123 100$");
            System.out.println("5. Salir");
            System.out.println("************* ******************************* *************");
            try {
                System.out.print("Selecciona la cuenta a retirar: ");
                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        amountWithdrawal = this.getWithdrawalAmount();

                        //aca abajo tambien seria si el monto supera el monto de la cuenta y toda esa broma
                        if(amountWithdrawal > 0){
                            System.out.println("Se realizo el retiro exitosamente");
                            System.out.println("El nuevo monto es tal");
                        }else{
                            System.out.println("Debes ingresar un monto superior a 0");
                            exit = true;
                        }
                        break;
                    case 2:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        amountWithdrawal = this.getWithdrawalAmount();
                        if(amountWithdrawal > 0){
                            System.out.println("Se realizo el retiro exitosamente");
                            System.out.println("El nuevo monto es tal");
                        }else{
                            System.out.println("Debes ingresar un monto superior a 0");
                            exit = true;
                        }
                        break;
                    case 3:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        amountWithdrawal = this.getWithdrawalAmount();
                        if(amountWithdrawal > 0){
                            System.out.println("Se realizo el retiro exitosamente");
                            System.out.println("El nuevo monto es tal");
                        }else{
                            System.out.println("Debes ingresar un monto superior a 0");
                            exit = true;
                        }
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
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
            return amount;
        } catch (NumberFormatException e) {
            System.out.println("Debes insertar un número");
            return 0;
        }
    }

}
