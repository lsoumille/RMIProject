package ServiceDelivery.iRMI;

import RMIRegistry.Exceptions.NonSerializableException;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Lucas Soumille
 * @version 19/05/16.
 */
public interface IRMIRegistry extends Remote {

    /**
     * Add a new entry <name , obj> in our Registry
     * @param name
     * @param obj
     */
    void bind(String name, Remote obj) throws RemoteException, NonSerializableException;;

    /**
     * Replace the entry for the specified name in our Registry
     * @param name
     * @param obj
     */
    void rebind(String name, Remote obj) throws RemoteException, NonSerializableException;;

    /**
     * Return the corresponding Object
     * @return
     */
    Remote lookup(String name) throws RemoteException;

    /**
     * Update the name of the corresponding entry with the newName
     * @param name
     * @param newName
     */
    void rename(String name, String newName) throws RemoteException;

    /**
     * Remove the entry our Registry
     * @param name
     */
    void unbind(String name) throws RemoteException;

    /**
     * Return an array with all the entry names
     * @return
     */
    String[] list() throws RemoteException;

    /**
     * Return an array with the n last entry names
     * @return
     */
    String[] getLastEntries(int n) throws RemoteException;
}
