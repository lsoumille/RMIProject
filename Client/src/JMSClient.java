import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author Lucas Martinez
 * @version 21/05/16.
 */
public class JMSClient implements javax.jms.MessageListener {

    private javax.jms.Connection connect = null;
    private javax.jms.Session session = null;
    InitialContext context = null;

    public JMSClient() {
        configure();
    }

    public void configure() {
        try {	// Create a connection
            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

            context = new InitialContext(properties);

            javax.jms.ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
            connect = factory.createConnection();

            connect.start();
        } catch (javax.jms.JMSException | NamingException e){
            e.printStackTrace();
        }
    }

    public void initClient(String queueName){
        try{

            session = connect.createSession(false,javax.jms.Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("dynamicQueues/" + queueName);
            javax.jms.MessageConsumer qReceiver = session.createConsumer(queue);
            qReceiver.setMessageListener(this);

        } catch(JMSException | NamingException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        // Methode permettant au consommateur de consommer effectivement chaque msg recu
        // via la queue
        try {
            System.out.print("Recu un message de la queue: "+((MapMessage)message).getString("nom"));
            System.out.println(((MapMessage)message).getString("num"));
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
