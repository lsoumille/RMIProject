package RMIRegistry;

import RMIRegistry.Exceptions.NonSerializableException;
import RMIRegistry.Utils.RegistryAPI;

import java.io.Externalizable;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Lucas Soumille
 * @version 19/05/16.
 */
public class RMIRegistry extends UnicastRemoteObject implements IRMIRegistry {

    private RegistryAPI map;

    public RMIRegistry(int numport) throws RemoteException{
        super(numport);
        map = new RegistryAPI();
    }

    public RMIRegistry() throws RemoteException{
        super();
        map = new RegistryAPI();
    }

    @Override
    public void bind(String name, Remote obj) throws NonSerializableException {
        if ((obj instanceof Serializable) || (obj instanceof Externalizable)){
            map.add(name, obj);
        } else {
            throw new NonSerializableException("The object is not serializable.");
        }
    }

    @Override
    public void rebind(String name, Remote obj) throws NonSerializableException {
        if ((obj instanceof Serializable) || (obj instanceof Externalizable)){
            map.add(name, obj);
        } else {
            throw new NonSerializableException("The object is not serializable.");
        }
    }

    @Override
    public Remote lookup(String name) {
        return map.get(name);
    }

    @Override
    public void rename(String name, String newName) {
        map.rename(name, newName);
    }

    @Override
    public void unbind(String name) {
        map.remove(name);
    }

    @Override
    public String[] list() {
        return map.list();
    }

    @Override
    public String[] getLastEntries(int n){
        return map.getLastEntries(n);
    }
}
