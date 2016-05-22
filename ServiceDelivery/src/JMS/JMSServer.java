package JMS;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.List;

/**
 * @author Lucas Martinez
 * @version 21/05/16.
 */
public class JMSServer {
    private javax.jms.Connection connect;
    private javax.jms.Session session;
    private InitialContext context;

    public JMSServer() {
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

            javax.jms.ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
            connect = factory.createConnection();

            session = connect.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
            connect.start();

        } catch (javax.jms.JMSException | NamingException e){
            e.printStackTrace();
        }
    }

    /**
     * Create a queue for a client
     * @param name
     * @return
     */
    public MessageProducer initQueue(String name){
        try {
            Queue queue = (Queue) context.lookup("dynamicQueues/" + name);
            return session.createProducer(queue);
        } catch(JMSException | NamingException e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Send a message at all queues in the list prod
     * @param prod
     * @param message
     * @throws JMSException
     */
    public void sendMessage(List<MessageProducer> prod, String message) throws JMSException{
        TextMessage mess = session.createTextMessage();
        mess.setText(message);
        for(MessageProducer p : prod){
            p.send(mess);
        }
    }

    @Override
    public String toString() {
        return "JMSServer{} " + connect + context;
    }
}
