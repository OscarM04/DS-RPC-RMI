package User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUser extends Remote {

    Boolean checkIfExists(String ci) throws RemoteException;
    Boolean canCreateAccount(String ci) throws RemoteException;
}
