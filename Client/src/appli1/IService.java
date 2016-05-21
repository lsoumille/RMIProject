package appli1;

import javax.jms.JMSException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by lucas on 20/05/16.
 */
public interface IService extends Remote {

    String getInformation() throws RemoteException;
    String subscribe(String idClient) throws RemoteException, JMSException;
}
