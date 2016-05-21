package synonym;

import purchasingcars.IClientStatus;
import purchasingcars.IConnexionService;
import purchasingcars.IPurchaseService;
import purchasingcars.business.Car;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 21/05/16.
 */
public class ClientSynonym {

    public static void main(String[] args) {
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            Registry reg =  LocateRegistry.getRegistry(1098);

            iRMI.IRMIRegistry stub = (iRMI.IRMIRegistry) reg.lookup("RMIUniversel");
            ISynonym connec = (ISynonym) stub.lookup("/Synonym");
            List<String> synonymsToAdd = new ArrayList<>();
            synonymsToAdd.add("Suceur");
            synonymsToAdd.add("LeMaitre");
            connec.addWord("Martinez", synonymsToAdd);
            System.out.println("Synonyme pour Martinez");
            for(String s : connec.getSynonym("Martinez")){
                System.out.println(s + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
