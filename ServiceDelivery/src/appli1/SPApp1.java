package appli1;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by lucas on 21/05/16.
 */
public class SPApp1 implements IService, Serializable {

    @Override
    public String getInformation() throws RemoteException {
        return "Un super service";
    }
}
