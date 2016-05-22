package purchasingcars;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by lucas on 21/05/16.
 */
public interface IClientStatus extends Remote {
    /**
     * Return the solvency of the client
     * @return
     * @throws RemoteException
     */
    boolean getSolvency() throws RemoteException;

    /**
     * Update his Solvency, if true it becomes false or the contrary
     * @throws RemoteException
     */
    void updateSolvency() throws RemoteException;

    /**
     * Application uses this method to call a client
     * @param message
     * @throws RemoteException
     */
    void call(String message) throws RemoteException;
}
