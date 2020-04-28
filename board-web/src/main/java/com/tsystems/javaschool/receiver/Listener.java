package com.tsystems.javaschool.receiver;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Named
public class Listener implements MessageListener {

    @Inject
    @Push
    PushContext someChannel;

    public void onMessage(Message m) {
        try {
            TextMessage msg = (TextMessage) m;
            someChannel.send("someMessage");
            System.out.println("following message is received:" + msg.getText());
        } catch (JMSException e) {
            System.out.println(e);
        }
    }
}
