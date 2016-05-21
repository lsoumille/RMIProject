package purchasingcars;

import purchasingcars.business.Car;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by lucas on 21/05/16.
 */
public interface IPurchaseService extends Remote {

    /**
     * Return the service's description
     * @throws RemoteException
     */
    String getInformation() throws RemoteException;

    Car buyCar(String key, IClientStatus cli) throws Exception;

    boolean sellCar(Car car, IClientStatus cli) throws Exception;

    List<Car> getCatalogue() throws Exception;
}
