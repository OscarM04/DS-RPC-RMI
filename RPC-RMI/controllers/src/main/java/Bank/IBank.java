package Bank;

import entitys.TransactionEntity;
import exceptions.CustomException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IBank extends Remote {

    void accountOpening(
            long ci, String username, String password, String name, double initialAmount, boolean newUser
    ) throws RemoteException, CustomException;
    List<String> transaction(double amount, String description, long accountNumber, long destAccount) throws RemoteException, CustomException;
    List<String> withdrawal( double amount, long accountNumber) throws RemoteException, CustomException;
    List<String> deposit(
            double amount, String description, long accountNumber
    ) throws RemoteException, CustomException;
    List<TransactionEntity> viewAccount(long accountNumber) throws RemoteException, CustomException;
}
