

import appli1.IConnexionService;
import appli1.IService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by lucas on 20/05/16.
 */
public class ClientAppli1 {

    private static JMSClient jmsClient;

    public static void main(String[] args) {
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            Registry reg =  LocateRegistry.getRegistry(1098);

            iRMI.IRMIRegistry stub = (iRMI.IRMIRegistry) reg.lookup("RMIUniversel");
            IConnexionService connec = (IConnexionService) stub.lookup("MonService");
            IService monServiceSmart = connec.login();

            System.out.println(monServiceSmart.getInformation());

            jmsClient = new JMSClient();
            jmsClient.configure();
            jmsClient.initClient(monServiceSmart.subscribe("ClientAppli1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
