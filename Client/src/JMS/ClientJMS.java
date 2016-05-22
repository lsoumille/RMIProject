package JMS;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * @author Lucas Martinez
 * @version 21/05/16.
 */
public class ClientJMS implements MessageListener {

    private Connection connect = null;
    private Session session = null;
    InitialContext context = null;

    public ClientJMS() {
        configure();
    }

    /**
     * configure the context and the connection at the activeMQ
     */
    public void configure() {
        try {
            // Create a connection
            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

            context = new InitialContext(properties);

            ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
            connect = factory.createConnection();
            connect.start();
        } catch (JMSException | NamingException e){
            e.printStackTrace();
        }
    }

    /**
     * Set the client as a receiver
     * @param queueName
     */
    public void initClient(String queueName){
        try{

            session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("dynamicQueues/" + queueName);
            MessageConsumer qReceiver = session.createConsumer(queue);
            qReceiver.setMessageListener(this);

        } catch(JMSException | NamingException e){
            e.printStackTrace();
        }
    }

    /**
     * Action executed when a message is received
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Recu un message de la queue: "+ (((TextMessage) message).getText()));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
