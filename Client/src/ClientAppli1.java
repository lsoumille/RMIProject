import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by lucas on 20/05/16.
 */
public class ClientAppli1 {

    public static void main(String[] args) {
        try {

            Registry reg =  LocateRegistry.getRegistry(1098);

            IRMIRegistry stub = (IRMIRegistry) reg.lookup("RMIUniversel");
            IService recupServ = (IService) stub.lookup("MonService");
            recupServ.printInformation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
