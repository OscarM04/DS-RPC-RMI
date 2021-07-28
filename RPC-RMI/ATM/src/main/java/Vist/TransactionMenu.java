package Vist;

import java.util.InputMismatchException;
import java.util.Scanner;


public class TransactionMenu {

    public void show(){
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
            System.out.println("5. Salir");
            System.out.println("************* ******************************* *************");
            try {
                System.out.print("Escribe una de las opciones: ");
                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        consultAccountMenu.show();
                        break;
                    case 2:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        accountMenu.show();
                        break;
                    case 3:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        withdrawalAccount.show();
                        break;
                    case 4:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        transferMenu.show();
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }
}
