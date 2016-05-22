package purchasingcars;

import JMS.JMSServer;
import iRMI.IRMIRegistry;
import purchasingcars.business.Car;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 21/05/16.
 */
public class PurchaseService extends UnicastRemoteObject implements IPurchaseService{

    private IRMIRegistry rmiUniversel;

    private final String K_NOMMAGE = "/PurchasingCars/";

    private static List<IClientStatus> toRecalled;

    private JMSServer jms;

    private List<MessageProducer> prod;

    protected PurchaseService() throws RemoteException, MalformedURLException, NotBoundException {
        super();
        rmiUniversel = (IRMIRegistry) Naming.lookup("rmi://localhost:1098/RMIUniversel");
        toRecalled = new ArrayList<>();
        jms = new JMSServer();
        prod = new ArrayList<>();
    }

    @Override
    public String getInformation() throws RemoteException {
        return "Great place to buy and sell your cars " + "Author : Lucas and Lucas";
    }

    @Override
    public synchronized Car buyCar(String key, IClientStatus cli) throws RemoteException {
        try {
            String nameInRegistry = K_NOMMAGE + key;
            Car wantedCar = (Car) rmiUniversel.lookup(nameInRegistry);
            if(wantedCar != null && cli.getSolvency()){
                rmiUniversel.unbind(nameInRegistry);
                cli.updateSolvency();
                if(! prod.isEmpty()){
                    jms.sendMessage(prod, "Vite il ne va plus en rester");
                }
                return wantedCar;
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public synchronized boolean sellCar(Car car, IClientStatus cli) throws RemoteException {
        try {
            String nameInRegistry = K_NOMMAGE + car.getNom();
            for(String key : rmiUniversel.list()){
                if(key.equals(nameInRegistry))
                    return false;
            }
            rmiUniversel.bind(nameInRegistry, car);
            cli.updateSolvency();
            if(! prod.isEmpty()){
                jms.sendMessage(prod, "Une nouvelle voiture est disponible");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<Car> getCatalogue() throws RemoteException {
        List<Car> cars = new ArrayList<>();
        for(String key : rmiUniversel.list()){
            if(rmiUniversel.isService(key) && key.equals("PurchasingCars")){
                cars.add((Car) rmiUniversel.lookup(key));
            }
        }
        return cars;
    }

    @Override
    public boolean beRecalled(IClientStatus cli) throws RemoteException {
        toRecalled.add(cli);
        return true;
    }

    public static void processRecalls() throws RemoteException{
        for(IClientStatus cli : toRecalled){
            cli.call("Le service est disponible");
        }
        toRecalled.clear();
    }

    @Override
    public String subscribe(String nameCli) throws RemoteException {
        prod.add(jms.initQueue(nameCli));
        return nameCli;
    }
}
