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

            System.out.println("On peut chercher des synonymes dans notre dictionnaire, " +
                    "par exemple les synonymes de 'Soumille' ou 'imaginaire':");
            System.out.println("Synonymes de Soumille:");
            for(String s : connec.getSynonym("Soumille")){
                System.out.println("--> " + s);
            }
            System.out.println("Synonymes de imaginaire:");
            for(String s : connec.getSynonym("imaginaire")){
                System.out.println("--> " + s);
            }

            System.out.println("On peut également ajouter des synonymes: on ajoute 'skidlucas' " +
                    "et 'Le Maitre' en synonyme de 'Martinez':");
            List<String> synonymsToAdd = new ArrayList<>();
            synonymsToAdd.add("skidlucas");
            synonymsToAdd.add("Le Maitre");
            connec.addWord("Martinez", synonymsToAdd);
            System.out.println("Puis on voit que les synonymes de Martinez sont:");
            for(String s : connec.getSynonym("Martinez")){
                System.out.println("--> " + s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
