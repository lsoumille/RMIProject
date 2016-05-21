package synonym;

import iRMI.IRMIRegistry;
import purchasingcars.ConnexionService;
import purchasingcars.IConnexionService;
import purchasingcars.business.Car;

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
            IRMIRegistry stub = (IRMIRegistry)  Naming.lookup("rmi://localhost:1098/RMIUniversel");
            //On crée le stub
            Synonym myService = new Synonym();
            //On link notre service
            stub.bind("/Synonym", myService);
            System.out.println("Service ajouté");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
