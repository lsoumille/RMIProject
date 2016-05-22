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
    public void bind(String name, Serializable obj) throws NotSerializableException, AlreadyBoundException {
        if ((obj instanceof Serializable) || (obj instanceof Externalizable)){
            registry.add(name, obj);
        } else {
            throw new NotSerializableException("The object is not serializable.");
        }
    }

    @Override
    public void rebind(String name, Serializable obj) throws NotSerializableException {
        if ((obj instanceof Serializable) || (obj instanceof Externalizable)){
            registry.addAnyway(name, obj);
        } else {
            throw new NotSerializableException("The object is not serializable.");
        }
    }

    @Override
    public Serializable lookup(String name) {
        return registry.get(name);
    }

    @Override
    public void rename(String name, String newName) {
        registry.rename(name, newName);
    }

    @Override
    public void unbind(String name) {
        registry.remove(name);
    }

    @Override
    public String[] list() {
        return registry.list();
    }

    @Override
    public String[] getLastKeys(int n){
        return registry.getLastKeys(n);
    }

    @Override
    public String[] getLastEntries(int n) throws RemoteException {
        return registry.getLastEntries(n);
    }

    @Override
    public boolean isService(String name) throws RemoteException{
        return registry.isService(name);
    }
}
