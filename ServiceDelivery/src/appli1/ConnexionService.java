package appli1;

import JMS.JMSServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by lucas on 21/05/16.
 */
public class ConnexionService extends UnicastRemoteObject implements IConnexionService {

    private static final long serialVersionUID = 1L;
    private IService service;

    public ConnexionService() throws RemoteException {
        super();
        //System.out.println("dans le constructeur de connexion " + jmsServer);
        service = new SPApp1();
    }

    //IService service = new SPApp1(jmsServer);
    @Override
    public IService login() throws RemoteException {
        return service;
    }
}
