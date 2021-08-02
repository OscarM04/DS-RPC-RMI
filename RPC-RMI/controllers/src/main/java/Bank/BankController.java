package Bank;

import dataAcces.DAOAccount;
import dataAcces.DAOUser;
import entitys.TransactionEntity;
import exceptions.CustomException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankController extends UnicastRemoteObject implements IBank {

    public BankController() throws RemoteException {

    }

    @Override
    public void accountOpening(
            long ci, String username, String password, String name, float initialAmount, boolean newUser
    ) throws CustomException {
        try {
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
    public List<String> transaction(float amount, String description, long accountNumber, long destAccount) throws CustomException {
        DAOAccount daoAccount = new DAOAccount();
        DAOUser daoUser = new DAOUser();
        if (daoUser.accountBalance(accountNumber) < amount) {
            List<String> result = new ArrayList<>();
            result.add("Monto a transferir mayor que el balance de la cuenta");
            return result;
        } else {
            return daoAccount.operation(amount, "", accountNumber, "transference", destAccount);
        }
    }

    @Override
    public List<String> withdrawal(  float amount, long accountNumber) throws CustomException {
        DAOAccount daoAccount = new DAOAccount();
        List<String> result = new ArrayList<>();
        DAOUser daoUser= new DAOUser();
        if (daoUser.accountBalance( accountNumber) < amount){
            result.add("Monto a retirar mayor que el balance de la cuenta");
            return result;
        }else{
            return daoAccount.operation( amount*-1, "", accountNumber,"withdrawal", 0);
        }
    }

    @Override
    public List<String> deposit( float amount, String description, long accountNumber) throws CustomException {
        DAOAccount daoAccount = new DAOAccount();
        return daoAccount.operation( amount, description, accountNumber,"deposit",0);
    }

    @Override
    public List<TransactionEntity> viewAccount(long accountNumber) throws RemoteException, CustomException {
        DAOAccount daoAccount = new DAOAccount();
        return daoAccount.listTransactions( accountNumber);
    }
}
