package purchasingcars;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by lucas on 21/05/16.
 */
public interface IConnexionService extends Remote, Serializable {

    /**
     * Give the acces at the purchase service
     * @return
     * @throws RemoteException
     */
    IPurchaseService accessService() throws RemoteException;

    /**
     * Return the service's description
     * @throws RemoteException
     */
    String getInformation() throws RemoteException;
}
