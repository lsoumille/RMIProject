package RMIRegistry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * Created by lucas on 19/05/16.
 */
public class Main {

    public static void main(String[] args) {
        try {
            RMIRegistry serv = new RMIRegistry(10003);
            Naming.rebind("rmi://localhost:1098/RMIUniversel", serv);

            System.out.println("Ready");
        } catch (RemoteException | MalformedURLException e){
            e.printStackTrace();
        }
    }
}
