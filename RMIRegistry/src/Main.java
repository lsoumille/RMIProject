import iRMI.RMIRegistry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

/**
 * @author Lucas Soumille
 * @version 19/05/16.
 */
public class Main {

    public static void main(String[] args) {
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new RMISecurityManager());
            }
            RMIRegistry serv = new RMIRegistry(10003);
            Naming.rebind("rmi://localhost:1098/RMIUniversel", serv);

            System.out.println("Ready");
        } catch (RemoteException | MalformedURLException e){
            e.printStackTrace();
        }
    }
}
