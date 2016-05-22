package purchasingcars;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by lucas on 21/05/16.
 */
public class ConnexionService extends UnicastRemoteObject implements IConnexionService, Serializable {

    public ConnexionService() throws RemoteException, MalformedURLException, NotBoundException {
        super();
    }

    IPurchaseService service = new PurchaseService();

    @Override
    public IPurchaseService accessService() throws RemoteException {
        return service;
    }

    @Override
    public String getInformation() throws RemoteException {
        return "Great place to buy and sell your cars " + "Author : Lucas and Lucas";
    }
}
