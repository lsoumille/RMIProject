package RMIRegistry.Utils;

import java.rmi.Remote;
import java.util.*;

/**
 * @author Lucas Martinez
 * @version 19/05/16.
 */
public class RegistryAPI {
    /**
     * TODO: ajouter des fonctionnalités (voir sujet + autres idées)
     */

    //HashMap containing couples (Name, Object) simulating the RMI register
    private static HashMap<String, Remote> map = new HashMap<>();

    //List containing only the names of the objects, by order of addition
    private static List<String> keys = new ArrayList<>();

    /**
     * Adds the (key, obj) to the hashmap and the key to the list
     * @param key
     * @param obj
     */
    public void add(String key, Remote obj){
        map.put(key, obj);
        if (keys.contains(key)){
            keys.remove(key);
        }
        keys.add(key);
    }

    /**
     * Returns the object with the name key
     * @param key
     * @return
     */
    public Remote get(String key){
        if(map.containsKey(key)){
            return map.get(key);
        }

        return null;
    }

    /**
     * Removes the couple (key, obj) from the hashmap as well as the key from the list
     * @param key
     */
    public void remove(String key){
        if (map.containsKey(key)){
            map.remove(key);
            keys.remove(key);
        }
    }

    /**
     * Renames the name of the object in both the hashmap and the list
     * @param oldKey
     * @param newKey
     */
    public void rename(String oldKey, String newKey){
        if (map.containsKey(oldKey) && !map.containsKey(newKey)){
            map.put(newKey, map.remove(oldKey));

            int index = keys.indexOf(oldKey);
            keys.set(index, newKey);
        }
    }

    /**
     * Returns the list of the objects' names
     * @return
     */
    public String[] list(){
        String[] entries = new String[map.size()];
        for (int i = 0; i < keys.size(); ++i){
            entries[i] = keys.get(i);
        }

        return entries;
    }

    /**
     * Returns the names of the n last objects added
     * @param n
     * @return
     */
    public String[] getLastEntries(int n){
        String[] lastEntries = new String[n];
        int last = n - 1;

        for (int i = 0; i < n; i++, last--){
            lastEntries[i] = keys.get(last);
        }

        return lastEntries;
    }

    public static HashMap<String, Remote> getMap() {
        return map;
    }

    public static void setMap(HashMap<String, Remote> map) {
        RegistryAPI.map = map;
    }

    public static List<String> getKeys() {
        return keys;
    }

    public static void setKeys(List<String> keys) {
        RegistryAPI.keys = keys;
    }
}
