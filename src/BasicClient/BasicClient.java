package BasicClient;

import RMIRegistry.Exceptions.NonSerializableException;
import RMIRegistry.IRMIRegistry;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Lucas MARTINEZ
 * @version 19/05/16
 */
public class BasicClient extends UnicastRemoteObject implements IBasicClient {

    BasicClient() throws RemoteException {
    }

    BasicClient(int port) throws RemoteException {
        super(port);
    }

    @Override
    public void doStuff() throws RemoteException{
        System.out.println("rien pour l'instant");
    }

    public static void main(String[] args) {
        try {

            IRMIRegistry stub = (IRMIRegistry) Naming.lookup("rmi://localhost:1098/RMIUniversel");

            //test des fonctions
            System.out.println("----- On bind RemoteObj10 --> n = 10 -----");
            stub.bind("RemoteObj10", new RemoteObj(10));

            System.out.println("----- On affiche la liste des objets sur le serveur -----");
            for (String str : stub.list()){
                System.out.println(str);
            }

            System.out.println("----- On lookup l'objet RemoteObj10 -----");
            RemoteObj ro = (RemoteObj) stub.lookup("RemoteObj10");
            System.out.println("RemoteObj10: " + ro.getN());

            System.out.println("----- On rebind RemoteObj10 --> n = 20, on bind RemoteObj30 --> n = 30 -----");
            stub.bind("RemoteObj30", new RemoteObj(30));
            stub.rebind("RemoteObj10", new RemoteObj(20));

            System.out.println("----- On affiche la liste des objets sur le serveur -----");
            for (String str : stub.list()){
                System.out.println(str);
            }

            System.out.println("----- On lookup les objets RemoteObj10 et RemoteObj30 -----");
            ro = (RemoteObj) stub.lookup("RemoteObj10");
            System.out.println("RemoteObj10: " + ro.getN());
            ro = (RemoteObj) stub.lookup("RemoteObj30");
            System.out.println("RemoteObj30: " + ro.getN());

            System.out.println("----- On unbind RemoteObj10 et on rename RemoteObj30 -----");
            stub.unbind("RemoteObj10");
            stub.rename("RemoteObj30", "NewRemoteObj30");

            System.out.println("----- On affiche la liste des objets sur le serveur -----");
            for (String str : stub.list()){
                System.out.println(str);
            }


            System.out.println("----- On bind RemoteObj10 --> n = 10 -----");
            stub.bind("RemoteObj10", new RemoteObj(10));
            System.out.println("----- On affiche la liste des 2 derniers objets enregistrés sur le serveur (plus récent en 1er) -----");
            for (String str : stub.getLastEntries(2)){
                System.out.println(str);
            }

        } catch (RemoteException | NotBoundException | MalformedURLException | NonSerializableException e) {
            e.printStackTrace();
        }

    }
}
