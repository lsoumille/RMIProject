package BasicClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by lucas on 20/05/16.
 */
public interface IService extends Remote {

    void printInformation() throws RemoteException;
}
