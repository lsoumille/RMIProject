package RMIRegistry;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by lucas on 19/05/16.
 */
public interface IRMIRegistry extends Remote {

    /**
     * Add a new entry <name , obj> in our Registry
     * @param name
     * @param obj
     */
    void bind(String name, Object obj) throws RemoteException;;

    /**
     * Replace the entry for the specified name in our Registry
     * @param name
     * @param obj
     */
    void rebind(String name, Object obj) throws RemoteException;;

    /**
     * Return the corresponding Object
     * @return
     */
    Object lookup(String name) throws RemoteException;;

    /**
     * Update the name of the corresponding entry with the newName
     * @param name
     * @param newName
     */
    void rename(String name, String newName) throws RemoteException;;

    /**
     * Remove the entry our Registry
     * @param name
     */
    void unbind(String name) throws RemoteException;;

    /**
     * Return an array with all the entry names
     * @return
     */
    String[] list() throws RemoteException;;

    /**
     * AJouter d'autres features
     */
}
