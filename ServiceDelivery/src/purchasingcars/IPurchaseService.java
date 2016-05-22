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

    /**
     * Buy the car with the specified key if the client has money
     * @param key
     * @param cli
     * @return
     * @throws RemoteException
     */
    Car buyCar(String key, IClientStatus cli) throws RemoteException;

    /**
     * Sell the car, give money to the client
     * @param car
     * @param cli
     * @return
     * @throws RemoteException
     */
    boolean sellCar(Car car, IClientStatus cli) throws RemoteException;

    /**
     * Return all the available cars
     * @return
     * @throws RemoteException
     */
    List<Car> getCatalogue() throws RemoteException;

    /**
     * Clients call this method to be recalled
     * @param cli
     * @return
     * @throws RemoteException
     */
    boolean beRecalled(IClientStatus cli) throws RemoteException;

    /**
     * Subscribe to the notifications
     * @param nameCli
     * @return
     * @throws RemoteException
     */
    String subscribe(String nameCli) throws RemoteException;
}
