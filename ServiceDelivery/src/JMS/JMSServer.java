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

    public void configure() {
        try {	// Create a connection
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

    public MessageProducer initQueue(String name){
        try {
            Queue queue = (Queue) context.lookup("dynamicQueues/" + name);
            return session.createProducer(queue);
        } catch(JMSException | NamingException e){
            e.printStackTrace();
        }
        return null;

    }

    public void sendMessage(List<MessageProducer> prod, String message) throws JMSException{

        //TODO : faire une vraie méthode et pas prendre le copier/coller du TP5
        /*for (int i=1;i<=10;i++){
            //Fabriquer un message
            MapMessage mess = session.createMapMessage();
            mess.setInt("num",i);
            mess.setString("nom",i+"-");
            if (i%2==0)
                mess.setStringProperty("typeMess","important");
            if (i==1) mess.setIntProperty("numMess",1);

            prod.send(mess);
        }*/

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
