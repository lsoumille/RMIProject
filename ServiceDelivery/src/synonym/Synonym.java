package synonym;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by lucas on 21/05/16.
 */
public class Synonym extends UnicastRemoteObject implements ISynonym, Serializable {
    
    Map<String, List<String>> dict;

    public Synonym() throws RemoteException {
        dict = new HashMap<>();
        dict.put("Soumille", new ArrayList<>(Arrays.asList("Sousou", "vrioche")));
        dict.put("imaginaire", new ArrayList<>(Arrays.asList("abstrait", "fantaisiste", "fictionnel")));
    }

    @Override
    public List<String> getSynonym(String word) {
        return dict.get(word);
    }

    @Override
    public void addWord(String toWord, List<String> synonymsForWord) {
        dict.put(toWord, synonymsForWord);
    }
}
