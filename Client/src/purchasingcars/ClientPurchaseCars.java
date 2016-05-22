package purchasingcars;

import JMS.ClientJMS;
import purchasingcars.IConnexionService;
import purchasingcars.IPurchaseService;
import purchasingcars.business.Car;

import java.io.Serializable;
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

    public static void main(String[] args) {
        try {
            ClientPurchaseCars cli = new ClientPurchaseCars(true);
            ClientJMS jms;
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            Registry reg =  LocateRegistry.getRegistry(1098);

            iRMI.IRMIRegistry stub = (iRMI.IRMIRegistry) reg.lookup("RMIUniversel");
            IConnexionService connec = (IConnexionService) stub.lookup("/MonService");
            IPurchaseService monService = connec.accessService();
            for(Car c : monService.getCatalogue()){
                System.out.println(c.getNom());
            }
            Car c = monService.buyCar("red", cli);
            if(c == null) {
                System.out.println("Transaction impossible");
            }
            monService.sellCar(c, cli);
            System.out.println(cli.solvency);
            monService.beRecalled(cli);

            //Abonnement
            String nomQueue = monService.subscribe("Lucas");
            jms = new ClientJMS();
            jms.initClient(nomQueue);

            c = monService.buyCar("red", cli);
            monService.sellCar(c, cli);
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
}
