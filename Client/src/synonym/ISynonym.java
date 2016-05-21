package synonym;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by lucas on 21/05/16.
 */
public interface ISynonym extends Remote {

    List<String> getSynonym(String word);

    void addWord(String toWord, List<String> synonymsForWord);
}
