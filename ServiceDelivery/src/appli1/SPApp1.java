package appli1;

import JMS.JMSServer;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by lucas on 21/05/16.
 */
public class SPApp1 implements IService, Serializable {

    //private JMSServer jmsServer;
    private MessageProducer prod = null;

    public SPApp1() {
    }

    @Override
    public String getInformation() throws RemoteException {
        return "Un super service";
    }

    @Override
    public String subscribe(String idClient) throws RemoteException, JMSException {
        JMSServer jmsServer = new JMSServer();
        String queueName = idClient + "_queue";
        prod = jmsServer.initQueue(queueName);
        jmsServer.sendMessage(prod); //tmp
        return queueName;
    }
}
