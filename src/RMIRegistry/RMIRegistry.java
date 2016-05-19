package RMIRegistry;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by lucas on 19/05/16.
 */
public class RMIRegistry extends UnicastRemoteObject implements IRMIRegistry {

    public RMIRegistry(int numport) throws RemoteException{
        super(numport);
    }

    public RMIRegistry() throws RemoteException{
        super();
    }

    @Override
    public void bind(String name, Object obj) {

    }

    @Override
    public void rebind(String name, Object obj) {

    }

    @Override
    public Object lookup(String name) {
        return null;
    }

    @Override
    public void rename(String name, String newName) {

    }

    @Override
    public void unbind(String name) {

    }

    @Override
    public String[] list() {
        return new String[0];
    }
}
