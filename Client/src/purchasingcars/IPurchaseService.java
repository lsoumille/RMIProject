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

    Car buyCar(String key, IClientStatus cli) throws RemoteException;

    boolean sellCar(Car car, IClientStatus cli) throws RemoteException;

    List<Car> getCatalogue() throws RemoteException;

    boolean beRecalled(IClientStatus cli) throws RemoteException;

    String subscribe(String nameCli) throws RemoteException;
}
