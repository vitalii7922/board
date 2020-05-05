package com.tsystems.javaschool.receiver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Hashtable;

@Named
@ApplicationScoped
public class Receiver {

    private static final Log log = LogFactory.getLog(Receiver.class);

    /**
     * @param queueName queueName
     * @return receiver
     */
    public QueueReceiver initReceiver(String queueName) {
        QueueReceiver receiver = null;
        try {
            Hashtable<String, String> props = new Hashtable<>();
            props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.put("java.naming.provider.url", "tcp://localhost:61616");
            props.put("queue.js-queue", queueName);
            props.put("connectionFactoryNames", "queueCF");
            InitialContext ctx = new InitialContext(props);
            QueueConnectionFactory f = (QueueConnectionFactory) ctx.lookup("queueCF");
            QueueConnection con = f.createQueueConnection();
            con.start();
            QueueSession ses = con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue t = (Queue) ctx.lookup("js-queue");
             receiver = ses.createReceiver(t);
        } catch (Exception e) {
            log.info(e.getCause());
        }
        return receiver;
    }
}
