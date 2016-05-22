package purchasingcars;

import JMS.ClientJMS;
import purchasingcars.business.Car;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by lucas on 21/05/16.
 */
public class ClientPurchaseCars extends UnicastRemoteObject implements IClientStatus {

    private boolean solvency;

    public ClientPurchaseCars(boolean solvency) throws RemoteException {
        super();
        this.solvency = solvency;
    }

    public void start() {
        try {
            //Creation de l'objet client
            ClientPurchaseCars cli = new ClientPurchaseCars(true);
            ClientJMS jms;
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            Registry reg =  LocateRegistry.getRegistry(1098);

            iRMI.IRMIRegistry stub = (iRMI.IRMIRegistry) reg.lookup("RMIUniversel");
            //On se connecte a l'application
            IConnexionService connec = (IConnexionService) stub.lookup("/ConnexionPC");
            IPurchaseService carService = connec.accessService();
            System.out.println("--->  Demonstration de la plateforme PurchasingCars  <---");
            //Affichage du catalogue
            System.out.println("----- Affichage du Catalogue de voitures disponibles : -----");
            for(Car c : carService.getCatalogue()){
                System.out.println("-> " + c.getNom());
            }
            //On achète la voiture
            System.out.println("----- Achat de la voiture \"red\" -----");
            Car c = carService.buyCar("red", cli);
            if(c == null) {
                System.out.println("Transaction impossible");
                return;
            } else {
                System.out.println("----- Transaction réalisée ! Avons nous encore de l'argent ? " + cli.solvency);
            }
            //On vend la voiture
            System.out.println("----- Vente de la voiture \"red\" -----");
            carService.sellCar(c, cli);
            System.out.println("----- Avons nous récupéré de l'argent ? " + cli.solvency);

            //Test du rappel
            System.out.println("----- Je demande a être rappelé (réponse dans < 5 secs) -----" );
            carService.beRecalled(cli);

            Thread.sleep(5000);

            //Abonnement
            System.out.println("----- On s'abonne au système de notification -----");
            String nomQueue = carService.subscribe("Lucas");
            //initiation des configuration
            jms = new ClientJMS();
            jms.initClient(nomQueue);

            System.out.println("----- On va faire un achat et une vente pour les notifications -----");
            c = carService.buyCar("red", cli);
            if(c == null){
                System.out.println("Transaction impossible");
                return;
            }
            carService.sellCar(c, cli);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean getSolvency() throws RemoteException {
        return solvency;
    }

    @Override
    public void updateSolvency() throws RemoteException {
        solvency = ! solvency;
    }

    @Override
    public void call(String message) throws RemoteException {
        System.out.println("Le serveur vous a contacté et vous a laissé le message suivant : " + message);
    }

    public static void main(String[] args) {
        try {
            new ClientPurchaseCars(true).start();
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }
}
