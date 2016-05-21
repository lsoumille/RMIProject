package appli1;

import JMS.JMSServer;

import javax.jms.JMSException;
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

    /**
     * Return the name of the queue which the client subscribed to
     * @param idClient
     * @return
     * @throws RemoteException
     */
    String subscribe(String idClient) throws RemoteException, JMSException;

}
