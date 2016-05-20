package ServiceDelivery.appli1;

import ServiceDelivery.iRMI.IRMIRegistry;
import ServiceDelivery.iRMI.NonSerializableException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by lucas on 20/05/16.
 */
public class Main {

    public static void main(String[] args) {
        try {
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
