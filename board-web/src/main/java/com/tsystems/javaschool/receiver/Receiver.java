package com.tsystems.javaschool.receiver;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Hashtable;

@Named
public class Receiver {

    @Inject
    Listener listener;


    public void receive() {
        try {
            Hashtable<String, String> props = new Hashtable<>();
            props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.put("java.naming.provider.url", "tcp://localhost:61616");
            props.put("queue.js-queue", "second");
            props.put("connectionFactoryNames", "queueCF");
            InitialContext ctx = new InitialContext(props);
            QueueConnectionFactory f = (QueueConnectionFactory) ctx.lookup("queueCF");
            QueueConnection con = f.createQueueConnection();
            con.start();
            //2) create Queue session
            QueueSession ses = con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            //3) get the Queue object
            Queue t = (Queue) ctx.lookup("js-queue");
            //4)create QueueReceiver
            QueueReceiver receiver = ses.createReceiver(t);
            //5) create listener object
//            Listener listener = new Listener();
            //6) register the listener object with receiver
            receiver.setMessageListener(listener);
            System.out.println("Receiver1 is ready, waiting for messages...");
            System.out.println("press Ctrl+c to shutdown...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
