package Bank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BankController extends UnicastRemoteObject implements IBank {


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
    protected BankController() throws RemoteException {
    }

    @Override
    public void accountOpening() {

    }

    @Override
    public void transaction() {

    }

    @Override
    public void withdrawal() {

    }

    @Override
    public void deposit() {

    }
}
