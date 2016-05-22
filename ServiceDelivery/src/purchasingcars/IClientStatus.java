package purchasingcars;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by lucas on 21/05/16.
 */
public interface IClientStatus extends Remote {

    boolean getSolvency() throws RemoteException;

    void updateSolvency() throws RemoteException;

    void call(String message) throws RemoteException;
}
