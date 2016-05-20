package appli1;


import appli1.IService;
import appli1.Service;
import iRMI.IRMIRegistry;


import java.rmi.Naming;
import java.rmi.RMISecurityManager;

/**
 * Created by lucas on 20/05/16.
 */
public class Main {

    public static void main(String[] args) {
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new RMISecurityManager());
            }
            //On récupére notre RMI Universel
            IRMIRegistry stub = (IRMIRegistry) Naming.lookup("rmi://localhost:1098/RMIUniversel");
            //On crée le stub
            IService myService = new Service();
            //On link notre service
            stub.bind("MonService", myService);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
