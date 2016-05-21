package appli1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by lucas on 20/05/16.
 */
public interface IService extends Remote {

    /**
     * Return the service's description
     * @throws RemoteException
     */
    String getInformation() throws RemoteException;
}
