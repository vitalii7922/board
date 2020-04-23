package com.tsystems.javaschool.receiver;

import com.tsystems.javaschool.data.StationBean;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


public class Listener implements MessageListener {

    @Inject
    StationBean stationBean;

    public void onMessage(Message m) {
        try {
            TextMessage msg = (TextMessage) m;
            System.out.println("following message is received:" + msg.getText());
        } catch (JMSException e) {
            System.out.println(e);
        }
    }
}
