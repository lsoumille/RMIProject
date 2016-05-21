package appli1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by lucas on 21/05/16.
 */
public class ConnexionService extends UnicastRemoteObject implements IConnexionService {

    private static final long serialVersionUID = 1L;

    public ConnexionService() throws RemoteException {
        super();
    }

    IService service = new SPApp1();
    @Override
    public IService login() throws RemoteException {
        return service;
    }
}
