package com.tsystems.javaschool.sender;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class Sender {
    public static void main(String[] args) throws NamingException, JMSException {
        Hashtable<String, String> props = new Hashtable<>();
        props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.put("java.naming.provider.url", "tcp://localhost:61616");
        props.put("queue.js-queue", "second");
        props.put("connectionFactoryNames", "queueCF");

        Context context = new InitialContext(props);
        QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("queueCF");
        Queue queue = (Queue) context.lookup("js-queue");

        QueueConnection connection = connectionFactory.createQueueConnection();
        connection.start();

        QueueSession session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

        QueueSender sender = session.createSender(queue);
        TextMessage message = session.createTextMessage("Hello from main");

        sender.send(message);
        sender.close();
        session.close();
        connection.close();
    }
}
