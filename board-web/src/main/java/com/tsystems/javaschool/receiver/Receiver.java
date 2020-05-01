package com.tsystems.javaschool.receiver;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class Receiver {
    private Receiver() {}
    public static QueueReceiver initReceiver(String first) throws NamingException, JMSException {
        Hashtable<String, String> props = new Hashtable<>();
        props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.put("java.naming.provider.url", "tcp://localhost:61616");
        props.put("queue.js-queue", first);
        props.put("connectionFactoryNames", "queueCF");
        InitialContext ctx = new InitialContext(props);
        QueueConnectionFactory f = (QueueConnectionFactory) ctx.lookup("queueCF");
        QueueConnection con = f.createQueueConnection();
        con.start();
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        activeMQConnectionFactory.setUserName("admin");
        activeMQConnectionFactory.setPassword("admin");
        QueueSession ses = con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue t = (Queue) ctx.lookup("js-queue");
        return ses.createReceiver(t);
    }
}
