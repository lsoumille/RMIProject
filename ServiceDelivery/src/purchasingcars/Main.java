package purchasingcars;

import iRMI.IRMIRegistry;
import purchasingcars.business.Car;

import java.rmi.Naming;

/**
 * Created by lucas on 20/05/16.
 */
public class Main {

    public static void main(String[] args) {
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            //On récupére notre RMI Universel
            IRMIRegistry stub = (IRMIRegistry)  Naming.lookup("rmi://localhost:1098/RMIUniversel");
            //On crée le stub
            IConnexionService myService = new ConnexionService();
            //On link notre service
            stub.rebind("/ConnexionPC", myService);

            Car redCard = new Car("red");
            stub.rebind("/PurchasingCars/red", redCard);
            System.out.println("Service ajouté");
            //Toutes les 5 secs on appelle les clients qui ont demandé à etre rappelé
            while(true){
                PurchaseService.processRecalls();
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
