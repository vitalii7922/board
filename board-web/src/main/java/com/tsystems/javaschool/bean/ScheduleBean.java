package com.tsystems.javaschool.bean;

import com.tsystems.javaschool.dto.ScheduleDto;
import com.tsystems.javaschool.receiver.Receiver;
import com.tsystems.javaschool.service.ScheduleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import java.util.List;

@Named
@ApplicationScoped
public class ScheduleBean {

    @Inject
    ScheduleService scheduleService;

    @Push
    @Inject
    PushContext scheduleChannel;

    private String stationId;

    private String stationName;


    private List<ScheduleDto> scheduleDtoList;

    String page = "schedule";

    private static final Log log = LogFactory.getLog(ScheduleBean.class);

    public List<ScheduleDto> getSchedules() {
        return scheduleDtoList;
    }

    public String view() {
        return page;
    }

    @PostConstruct
    public void init() {
        try {
            QueueReceiver receiver = Receiver.initReceiver("first");
            receiver.setMessageListener(message -> {
                try {
                    TextMessage msg = (TextMessage) message;
                    if (Long.parseLong(msg.getText()) == Long.parseLong(stationId)) {
                        setSchedules(msg.getText());
                        scheduleChannel.send("update");
                        log.info("-----------schedule's updated on station: " + stationId);
                    }
                } catch (JMSException e) {
                    log.error(e.getCause());
                }
            });
        } catch (Exception e) {
            log.error(e.getCause());
        }
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setSchedules(String stationId) {
        this.stationId = stationId;
        scheduleDtoList = scheduleService.retrieveSchedules(stationId);
        log.info("-------schedule initialized by station id: " + stationId);
    }

    public void setSchedules(String stationId, String stationName) {
        this.stationId = stationId;
        setStationName(stationName);
        scheduleDtoList = scheduleService.retrieveSchedules(stationId);
        log.info("-------schedule initialized by station id: " + stationId);
    }
}
