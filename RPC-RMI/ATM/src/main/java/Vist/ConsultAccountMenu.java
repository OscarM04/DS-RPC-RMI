package Vist;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsultAccountMenu {

    public void show(){
        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        int option; //Guardaremos la opcion del usuario

        while (!exit) {
            System.out.println("************* Mini-banco: Menú de transacciones *************");
            System.out.println("1. Consultar: *****-4567");
            System.out.println("1. Consultar: *****-4569");
            System.out.println("3. Consultar: *****-4570");
            System.out.println("4. Salir");
            System.out.println("************* ******************************* *************");
            try {
                System.out.print("Escribe una de las opciones: ");
                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");

                        System.out.println("tienes 5000$");

                        System.out.println("Transaccion: 12121 Monto: 5000$ hacia:****-12121");
                        System.out.println("Transaccion: 12121 Monto: 5000$ hacia:****-12121");
                        System.out.println("Transaccion: 12121 Monto: 5000$ hacia:****-12121");
                        System.out.println("Transaccion: 12121 Monto: 5000$ hacia:****-12121");
                        System.out.println("Transaccion: 12121 Monto: 5000$ hacia:****-12121");
                        break;
                    case 2:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("tienes 9000$");
                        System.out.println("Transaccion: 12121 Monto: 5000$ hacia:****-12121");
                        System.out.println("Transaccion: 12121 Monto: 5000$ hacia:****-12121");
                        System.out.println("Transaccion: 12121 Monto: 5000$ hacia:****-12121");
                        System.out.println("Transaccion: 12121 Monto: 5000$ hacia:****-12121");
                        System.out.println("Transaccion: 12121 Monto: 5000$ hacia:****-12121");
                        break;
                    case 3:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("tienes 5000$");
                        System.out.println("Transaccion: 12121 Monto: 5000$ hacia:****-12121");
                        System.out.println("Transaccion: 12121 Monto: 5000$ hacia:****-12121");
                        System.out.println("Transaccion: 12121 Monto: 5000$ hacia:****-12121");
                        System.out.println("Transaccion: 12121 Monto: 5000$ hacia:****-12121");
                        System.out.println("Transaccion: 12121 Monto: 5000$ hacia:****-12121");
                        break;
                    case 4:
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
