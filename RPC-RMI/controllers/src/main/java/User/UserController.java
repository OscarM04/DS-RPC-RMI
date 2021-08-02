package User;

import dataAcces.DAOAccount;
import dataAcces.DAOUser;
import exceptions.CustomException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class UserController extends UnicastRemoteObject implements IUser {
    /**
     * Creates and exports a new UnicastRemoteObject object using an
     * anonymous port.
     *
     * <p>The object is exported with a server socket
     * created using the {@link RMISocketFactory} class.
     *
     * @throws RemoteException if failed to export object
     * @since JDK1.1
     */
    public UserController() throws RemoteException {
    }

    @Override
    public float accountBalance(long accountNumber) throws RemoteException, CustomException {
        DAOUser daoUser = new DAOUser();
        return daoUser.accountBalance( accountNumber);
    }

    @Override
    public Boolean checkIfExists(long ci) throws RemoteException {
        DAOUser daoUser = new DAOUser();
        return daoUser.checkIfUserExists(ci);
    }

    @Override
    public Boolean canCreateAccount(long ci) throws RemoteException {
        Boolean canCreate = false;
        DAOUser daoUser = new DAOUser();
        long numberOfAccounts = daoUser.numberOfAccounts( ci);
        if (numberOfAccounts< 3) canCreate = true;
        return canCreate;
    }

    @Override
    public Boolean signin(String username, String password) throws RemoteException, CustomException {
        DAOUser daoUser = new DAOUser();
        return daoUser.signin( username, password);
    }

    @Override
    public List<Long> listAccounts(String username) throws RemoteException {
        DAOUser daoUser = new DAOUser();
        return daoUser.listAccounts( username);
    }

    @Override
    public List<String> checkAnotherAccount(long accountNumber, long ci) throws RemoteException {
        DAOAccount daoAccount = new DAOAccount();
        return daoAccount.checkAnotherAccount(accountNumber, ci);
    }

}
