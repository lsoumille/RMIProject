package RMIRegistry;

import java.rmi.Remote;

/**
 * Created by lucas on 19/05/16.
 */
public interface IRMIRegistry extends Remote {

    /**
     * Add a new entry <name , obj> in our Registry
     * @param name
     * @param obj
     */
    void bind(String name, Object obj);

    /**
     * Replace the entry for the specified name in our Registry
     * @param name
     * @param obj
     */
    void rebind(String name, Object obj);

    /**
     * Return the corresponding Object
     * @return
     */
    Object lookup(String name);

    /**
     * Update the name of the corresponding entry with the newName
     * @param name
     * @param newName
     */
    void rename(String name, String newName);

    /**
     * Remove the entry our Registry
     * @param name
     */
    void unbind(String name);

    /**
     * Return an array with all the entry names
     * @return
     */
    String[] list();

    /**
     * AJouter d'autres features
     */
}
