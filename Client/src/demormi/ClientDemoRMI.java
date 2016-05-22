package demormi;

import iRMI.IRMIRegistry;

import java.io.NotSerializableException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author Lucas Martinez
 * @version 22/05/16.
 */
public class ClientDemoRMI {


    public void start() {
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            IRMIRegistry stub = (IRMIRegistry) Naming.lookup("rmi://localhost:1098/RMIUniversel");

            //test des fonctions de notre RMI
            System.out.println("\t--> Démonstration des fonctionnalités offertes par notre RMI Universel <--");
            System.out.println("RemoteObj est une classe comportant uniquement une donnée membre : un int 'n'\n");
            System.out.println("----- On bind un objet RemoteObj10 --> n = 10 -----");
            stub.rebind("RemoteObj10", new RemoteObj(10));

            System.out.println("----- On affiche la liste des objets dans notre registre -----");
            for (String str : stub.list()){
                System.out.println(str);
            }

            System.out.println("----- On lookup l'objet RemoteObj10 -----");
            RemoteObj ro = (RemoteObj) stub.lookup("RemoteObj10");
            System.out.println(ro);

            System.out.println("----- On rebind RemoteObj10 --> n = 20, on bind RemoteObj30 --> n = 30 -----");
            stub.rebind("RemoteObj30", new RemoteObj(30));
            stub.rebind("RemoteObj10", new RemoteObj(20));

            System.out.println("----- On affiche la liste des objets dans notre registre -----");
            for (String str : stub.list()){
                System.out.println(str);
            }

            System.out.println("----- On lookup les objets RemoteObj10 et RemoteObj30 -----");
            ro = (RemoteObj) stub.lookup("RemoteObj10");
            System.out.println(ro);
            ro = (RemoteObj) stub.lookup("RemoteObj30");
            System.out.println(ro);

            System.out.println("----- On unbind RemoteObj10 et on rename RemoteObj30 en NewRemoteObj30 -----");
            stub.unbind("RemoteObj10");
            stub.rename("RemoteObj30", "NewRemoteObj30");

            System.out.println("----- On affiche la liste des objets dans notre registre -----");
            for (String str : stub.list()){
                System.out.println(str);
            }


            System.out.println("----- On bind RemoteObj10 --> n = 10 -----");
            stub.rebind("RemoteObj10", new RemoteObj(10));
            System.out.println("----- On affiche la liste des 10 derniers (clé, objet) enregistrés dans notre registre (plus récent en 1er) -----");
            for (String str : stub.getLastEntries(10)){
                System.out.println(str);
            }

            System.out.println("----- On peut également afficher uniquement leur clé -----");
            for (String str : stub.getLastKeys(10)){
                System.out.println(str);
            }

            System.out.println("----- On vérifie qu'un objet n'est pas déjà bound en levant une exception -----");
            try {
                System.out.println("On essaye de bind RemoteObj10...");
                stub.bind("RemoteObj10", new RemoteObj(10));
            } catch (AlreadyBoundException e){
                System.out.println("---> EXCEPTION");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ClientDemoRMI().start();
    }
}
