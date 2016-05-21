package synonym;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lucas on 21/05/16.
 */
public class Synonym extends UnicastRemoteObject implements ISynonym, Serializable {

    Map<String, List<String>> dict;

    public Synonym() throws RemoteException {
        dict = new HashMap<>();
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
