package iRMI;

import Utils.RegistryAPI;

import java.io.Externalizable;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Lucas Soumille
 * @version 19/05/16.
 */
public class RMIRegistry extends UnicastRemoteObject implements IRMIRegistry {

    private RegistryAPI registry;

    public RMIRegistry(int numport) throws RemoteException{
        super(numport);
        registry = new RegistryAPI();
    }

    public RMIRegistry() throws RemoteException{
        super();
        registry = new RegistryAPI();
    }

    @Override
    public void bind(String name, Serializable obj) throws RemoteException, AlreadyBoundException {
        registry.add(name, obj);
    }

    @Override
    public void rebind(String name, Serializable obj) throws RemoteException {
        registry.addAnyway(name, obj);
    }

    @Override
    public Serializable lookup(String name) throws RemoteException {
        return registry.get(name);
    }

    @Override
    public void rename(String name, String newName) throws RemoteException {
        registry.rename(name, newName);
    }

    @Override
    public void unbind(String name) throws RemoteException {
        registry.remove(name);
    }

    @Override
    public String[] list() throws RemoteException {
        return registry.list();
    }

    @Override
    public String[] getLastKeys(int n) throws RemoteException {
        return registry.getLastKeys(n);
    }

    @Override
    public String[] getLastEntries(int n) throws RemoteException {
        return registry.getLastEntries(n);
    }

    @Override
    public boolean isService(String name) throws RemoteException {
        return registry.isService(name);
    }
}
