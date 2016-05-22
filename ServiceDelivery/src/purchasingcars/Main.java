package purchasingcars;

import iRMI.IRMIRegistry;
import purchasingcars.business.Car;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;

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
            IConnexionService myService = new ConnexionService();
            //On link notre service
            stub.bind("/MonService", myService);

            Car redCard = new Car("red");
            stub.bind("/PurchasingCars/red", redCard);
            System.out.println("Service ajouté");
            while(true){
                PurchaseService.processRecalls();
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
