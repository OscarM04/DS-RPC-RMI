package Bank;

import dataAcces.DAOAccount;
import dataAcces.DAOUser;
import entitys.TransactionEntity;
import exceptions.CustomException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

public class BankController extends UnicastRemoteObject implements IBank {

    public BankController() throws RemoteException {

    }

    @Override
    public void accountOpening(
            long ci, String username, String password, String name, double initialAmount, boolean newUser
    ) throws CustomException {
        try {
            // AGREGAR LOGICA SI EL USUARIO EXISTE SOLO AGREGARLE UNA CUENTA MAS
            if ( newUser ){
                DAOUser daoUser = new DAOUser();
                daoUser.create( ci, username, password, name);
            }

            DAOAccount daoAccount = new DAOAccount();
            Date date = new Date();
            String accountNumber = String.valueOf(date.getTime());
            System.out.println( accountNumber.substring( accountNumber.length() - 8 ));
            daoAccount.create(
                    ci, initialAmount, Integer.parseInt(accountNumber.substring( accountNumber.length() - 8 ))
            );
        } catch (CustomException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<String> transaction(double amount, String description, long accountNumber, long destAccount) throws CustomException {
        DAOAccount daoAccount = new DAOAccount();
        return daoAccount.operation( amount, "", accountNumber,"transference", destAccount);
    }

    @Override
    public List<String> withdrawal(  double amount, long accountNumber) throws CustomException {
        DAOAccount daoAccount = new DAOAccount();
        return daoAccount.operation( amount*-1, "", accountNumber,"withdrawal", 0);
    }

    @Override
    public List<String> deposit( double amount, String description, long accountNumber) throws CustomException {
        DAOAccount daoAccount = new DAOAccount();
        return daoAccount.operation( amount, description, accountNumber,"deposit",0);
    }

    @Override
    public List<TransactionEntity> viewAccount(long accountNumber) throws RemoteException, CustomException {
        DAOAccount daoAccount = new DAOAccount();
        return daoAccount.listTransactions( accountNumber);
    }
}
