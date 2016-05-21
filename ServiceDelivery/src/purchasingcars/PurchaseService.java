package purchasingcars;

import appli1.IService;
import iRMI.IRMIRegistry;
import purchasingcars.business.Car;

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

    protected PurchaseService() throws RemoteException, MalformedURLException, NotBoundException {
        super();
        rmiUniversel = (IRMIRegistry) Naming.lookup("rmi://localhost:1098/RMIUniversel");
    }

    @Override
    public String getInformation() throws RemoteException {
        return "Great place to buy and sell your cars " + "Author : Lucas and Lucas";
    }

    @Override
    public synchronized Car buyCar(String key, IClientStatus cli) throws Exception {
        String nameInRegistry = K_NOMMAGE + key;
        Car wantedCar = (Car) rmiUniversel.lookup(nameInRegistry);
        if(wantedCar != null && cli.getSolvency()){
            rmiUniversel.unbind(nameInRegistry);
            cli.updateSolvency();
            return wantedCar;
        }
        return null;
    }

    @Override
    public synchronized boolean sellCar(Car car, IClientStatus cli) throws Exception {
        String nameInRegistry = K_NOMMAGE + car.getNom();
        for(String key : rmiUniversel.list()){
            if(key.equals(nameInRegistry))
                return false;
        }
        rmiUniversel.bind(nameInRegistry, car);
        cli.updateSolvency();
        return true;
    }

    @Override
    public List<Car> getCatalogue() throws Exception {
        List<Car> cars = new ArrayList<>();
        for(String key : rmiUniversel.list()){
            System.out.println(key.split("/")[1]
            );
            if(key.split("/")[1].equals("PurchasingCars")){
                cars.add((Car) rmiUniversel.lookup(key));
            }
        }
        return cars;
    }
}
