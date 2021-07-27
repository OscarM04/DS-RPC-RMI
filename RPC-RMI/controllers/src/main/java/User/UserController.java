package User;

import dataAcces.DAOUser;
import exceptions.CustomException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

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

    public void create() throws SQLException, CustomException {
        DAOUser daoUser = new DAOUser();
        daoUser.create();

    }

    @Override
    public Boolean checkIfExists(String ci) throws RemoteException {
        DAOUser daoUser = new DAOUser();
        return daoUser.checkIfUserExists(ci);
    }

    @Override
    public Boolean canCreateAccount(String ci) throws RemoteException {
        Boolean canCreate = false;
        DAOUser daoUser = new DAOUser();
        Integer numberOfAccounts = daoUser.numberOfAccounts( ci);
        if (numberOfAccounts< 3) canCreate = true;
        return canCreate;
    }
}
