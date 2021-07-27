package Bank;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBank extends Remote {

    void accountOpening() throws RemoteException;
    void transaction() throws RemoteException;
    void withdrawal() throws RemoteException;
    void deposit() throws RemoteException;
}
