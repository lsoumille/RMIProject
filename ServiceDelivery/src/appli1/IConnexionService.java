package appli1;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by lucas on 21/05/16.
 */
public interface IConnexionService extends Remote, Serializable {

    public IService login() throws RemoteException;
}
