import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Lucas Martinez
 * @version 19/05/16.
 */
public interface IBasicClient extends Remote {
    void doStuff() throws RemoteException;
}
