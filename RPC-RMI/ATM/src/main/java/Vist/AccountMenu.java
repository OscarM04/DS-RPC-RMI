package Vist;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountMenu {

    public void show(){
        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        int option; //Guardaremos la opcion del usuario
        float amountDeposit;

        while (!exit) {
            System.out.println("************* Mini-banco: Deposito a cuentas *************");
            System.out.println("1. Cuenta *****-123");
            System.out.println("2. Cuenta *****-123");
            System.out.println("3. Cuenta *****-123");
            System.out.println("4. A cuentas de terceros");
            System.out.println("5. Salir");
            System.out.println("************* ******************************* *************");
            try {
                System.out.print("Escribe una de las opciones: ");
                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        amountDeposit = this.getDepostAmount();
                        if(amountDeposit > 0){
                            System.out.println("Se realizo el deposito exitosamente a tal cuenta");
                            System.out.println("El nuevo monto es tal");
                        }else{
                            System.out.println("Debes ingresar un monto superior a 0");
                            exit = true;
                        }
                        break;
                    case 2:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        amountDeposit = this.getDepostAmount();
                        if(amountDeposit > 0){
                            System.out.println("Se realizo el deposito exitosamente a tal cuenta");
                            System.out.println("El nuevo monto es tal");
                        }else{
                            System.out.println("Debes ingresar un monto superior a 0");
                            exit = true;
                        }
                        break;
                    case 3:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        amountDeposit = this.getDepostAmount();
                        if(amountDeposit > 0){
                            System.out.println("Se realizo el deposito exitosamente a tal cuenta");
                            System.out.println("El nuevo monto es tal");
                        }else{
                            System.out.println("Debes ingresar un monto superior a 0");
                            exit = true;
                        }
                        break;
                    case 4:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        boolean success = this.thirdAccountMenuShow();
                        if(!success){
                            exit = true;
                        }
                        break;
                    case 5:
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

    public boolean thirdAccountMenuShow(){
        String accountNumber;
        String document;

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Ingresa los datos de la cuenta de terceros:");
        System.out.print("Número de cuenta: ");
        accountNumber = keyboard.nextLine();
        System.out.print("Documento de identidad del titular: ");
        document = keyboard.nextLine();

        //aca validas si existe blabla bla y retornas true o false

        boolean exist = true;

        if(exist){
            String continueStep;
            System.out.println("Dueño de la cuenta fulanito de tal");
            System.out.print("Deseas continuar con el proceso:(responde 's' o 'si' para continuar)");
            continueStep = keyboard.nextLine();
            if(continueStep.equals("s") || continueStep.equals("si")){
                float amountDeposit = this.getDepostAmount();
                if(amountDeposit > 0){
                    System.out.println("Se realizo el deposito exitosamente");
                }else{
                    System.out.println("Debes ingresar un monto superior a 0");
                    exist = false;
                }
            }else{
                exist = false;
            }

        }
        return exist;
    }


    public float getDepostAmount(){
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
            return amount;
        } catch (NumberFormatException e) {
            System.out.println("Debes insertar un número");
            return 0;
        }
    }
}
