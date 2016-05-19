package RMIRegistry.Utils;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lucas Martinez
 * @version 19/05/16.
 */
public class RegistryAPI {
    /**
     * TODO: ajouter des fonctionnalités (voir sujet + autres idées)
     */

    public static HashMap<String, Remote> map = new HashMap<>();

    public void add(String key, Remote obj){
        map.put(key, obj);
    }

    public Remote get(String key){
        if(map.containsKey(key)){
            return map.get(key);
        }

        return null;
    }

    public void remove(String key){
        if (map.containsKey(key)){
            map.remove(key);
        }
    }

    public void rename(String oldKey, String newKey){
        if (map.containsKey(oldKey)){
            map.put(newKey, map.remove(oldKey));
        }
    }

    public String[] list(){
        String[] entries = new String[map.size()];
        int i = 0;
        for (Map.Entry<String, Remote> entry : map.entrySet()){
            entries[i] = entry.getKey();
            ++i;
        }
        return entries;
    }

    public String[] getLastEntries(int n){
        String[] lastEntries = new String[n];
        //todo

        return lastEntries;
    }
}
