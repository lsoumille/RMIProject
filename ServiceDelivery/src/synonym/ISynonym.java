package synonym;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by lucas on 21/05/16.
 */
public interface ISynonym extends Remote {

    /**
     * Return the a list of the synonyms corresponding to the word
     * @param word
     * @return
     * @throws RemoteException
     */
    List<String> getSynonym(String word) throws RemoteException;

    /**
     * add a word with his synonyms
     * @param toWord
     * @param synonymsForWord
     * @throws RemoteException
     */
    void addWord(String toWord, List<String> synonymsForWord) throws RemoteException;
}
