package atm;

import Bank.IBank;
import User.IUser;
import exceptions.CustomException;

import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {

    private IBank bankController;
    private IUser userController;

    public void show() throws CustomException {
        TransactionMenu transactionMenu = new TransactionMenu();
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
                        this.getDocument();
                        break;
                    case 2:
                        System.out.println("\n\n\n\n\n\n\n\n\n\n");
                        String username;
                        String password;
                        Scanner keyboard = new Scanner(System.in);
                        System.out.println("Ingresa tus credenciales");
                        System.out.print("Nombre de usuario: ");
                        username = keyboard.nextLine();
                        System.out.print("Contraseña: ");
                        password = keyboard.nextLine();
                        boolean successLogin = this.userController.signin( username, password);

                        if(successLogin){
                            System.out.println("Credenciales validas");
                            transactionMenu.setBankController( this.bankController);
                            transactionMenu.setUserController( this.userController);
                            transactionMenu.show( username);
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
            } catch (InputMismatchException | RemoteException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }catch (CustomException e ){

            }
        }
    }

    public void getDocument() throws RemoteException, CustomException {
            String document;

            Scanner keyboard = new Scanner(System.in);
            System.out.print("Introduzca su número de documento: ");
            document = keyboard.nextLine();
            long ci = Long.parseLong( document.trim());
            boolean userExists = this.userController.checkIfExists(ci);

            boolean canCreateAccount = this.userController.canCreateAccount( ci);

            if ( userExists ) {
                if ( !canCreateAccount ) {
                    System.out.println("Ya posee 3 cuentas creadas");
                    return;
                }
                String username;
                String password;

                //Scanner teclado2 = new Scanner(System.in);
                System.out.println("Ingresa tus credenciales");
                System.out.print("Nombre de usuario: ");
                username = keyboard.nextLine();
                System.out.print("Contraseña: ");
                password = keyboard.nextLine();

                boolean successLogin = this.userController.signin( username, password);

                if (successLogin) {
                    System.out.println("Credenciales validas");
                    String initialQtyString;
                    Float initialQty;

                    Scanner teclado = new Scanner(System.in);
                    System.out.print("Introduzca la cantidad de $ de deposito inicial para su nueva cuenta: ");
                    initialQtyString = teclado.nextLine();
                    try {
                        initialQty = Float.parseFloat(initialQtyString);
                        System.out.println("tu cantidad inicial : " + initialQty + "!");
                        this.bankController.accountOpening( ci, username,password,"",initialQty, false);
                    } catch (NumberFormatException e) {
                        System.out.println("Debes insertar un número");
                        return;
                    }
                } else {
                    System.out.println("Credenciales invalidas");
                    return;
                }
            }else {
                String name;
                String username;
                String password;

                Scanner teclado = new Scanner(System.in);
                System.out.println("Nuevo usuario");
                System.out.print("Introduzca su nombre: ");
                name = teclado.nextLine();
                System.out.print("Nombre de usuario: ");
                username = teclado.nextLine();
                System.out.print("Contraseña: ");
                password = teclado.nextLine();

                System.out.println("Documento: " + document + "!");
                System.out.println("Nombre: " + name + "!");
                System.out.println("Usuario: " + username + "!");
                System.out.println("Contraseña: " + password + "!");
                System.out.print("Introduzca la cantidad de $ de deposito inicial para su nueva cuenta: ");
                String initialQtyString;
                Float initialQty;
                initialQtyString = teclado.nextLine();
                try {
                    initialQty = Float.parseFloat(initialQtyString);
                    System.out.println("Tu cantidad inicial : " + initialQty + "!");
                    this.bankController.accountOpening( ci, username,password, name,initialQty, true);
                } catch (NumberFormatException e) {
                    System.out.println("Debes insertar un número");
                    return;
                }

            }
    }


    public IBank getBankController() {
        return bankController;
    }

    public void setBankController(IBank bankController) {
        this.bankController = bankController;
    }

    public IUser getUserController() {
        return userController;
    }

    public void setUserController(IUser userController) {
        this.userController = userController;
    }
}
