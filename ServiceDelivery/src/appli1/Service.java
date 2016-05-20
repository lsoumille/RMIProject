package appli1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by lucas on 20/05/16.
 */
public class Service extends UnicastRemoteObject implements IService {

    public Service() throws RemoteException {
        super();
    }

    @Override
    public void printInformation() throws RemoteException {
        System.out.println("Mon Stub");
    }
}
