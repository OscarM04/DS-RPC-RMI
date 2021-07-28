package Vist;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    public void show(){
        TransactionMenu transactionMenu = new TransactionMenu();
        LoginForm loginForm = new LoginForm();
        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        int option; //Guardaremos la opcion del usuario

        while (!exit) {
            System.out.println("************* BIENVENIDO A RMI/RPC Mini-banco *************");
            System.out.println("1. Apertura de cuenta");
            System.out.println("2. Realizar transacción");
            System.out.println("3. Salir");
            System.out.println("************* ******************************* *************");
            try {
                System.out.print("Escribe una de las opciones: ");
                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        loginForm.getDocument();
                        break;
                    case 2:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        boolean successLogin = loginForm.login();

                        if(successLogin){
                            System.out.println("Credenciales validas");
                            transactionMenu.show();
                        }else{
                            System.out.println("Credenciales invalidas");
                        }

                        break;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }



}
