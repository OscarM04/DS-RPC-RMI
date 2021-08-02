package User;

import exceptions.CustomException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IUser extends Remote {

    float accountBalance (long accountNumber) throws RemoteException, CustomException;
    Boolean checkIfExists(long ci) throws RemoteException;
    Boolean canCreateAccount(long ci) throws RemoteException;
    Boolean signin(String username, String password) throws RemoteException, CustomException;
    List<Long> listAccounts(String username) throws RemoteException;
    List<String> checkAnotherAccount( long accountNumber, long ci) throws RemoteException;
}
