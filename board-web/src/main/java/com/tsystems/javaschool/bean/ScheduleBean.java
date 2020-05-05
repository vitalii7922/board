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
/**
 * author Vitalii Nefedov
 */
@Named
@ApplicationScoped
public class ScheduleBean {

    @Inject
    ScheduleService scheduleService;

    @Push
    @Inject
    PushContext scheduleChannel;

    @Inject
    Receiver receiver;

    private String stationId;

    private String stationName;

    private List<ScheduleDto> scheduleDtoList;

    String page = "schedule";

    private static final Log log = LogFactory.getLog(ScheduleBean.class);

    /**
     * @return scheduleDtoList
     */
    public List<ScheduleDto> getSchedules() {
        return scheduleDtoList;
    }

    /**
     * @return page
     */
    public String view() {
        return page;
    }

    /**
     * initialize receiver and listen to messages from application "railway reservation system"
     */
    @PostConstruct
    public void init() {
        try {
            QueueReceiver queueReceiver = receiver.initReceiver("first");
            queueReceiver.setMessageListener(message -> {
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
        log.info("-----------Receiver of scheduleBean has been started----------------");
    }

    /**
     * @return name of a station
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * @param stationName station name
     */
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    /**
     * initialize scheduleDtoList
     *
     * @param stationId station id
     */
    public void setSchedules(String stationId) {
        this.stationId = stationId;
        scheduleDtoList = scheduleService.retrieveSchedules(stationId);
        log.info("-------schedule initialized by station id: " + stationId);
    }

    /**
     * initialize station id, station name and scheduleDtoList
     *
     * @param stationId station id
     * @param stationName station name
     */
    public void setSchedules(String stationId, String stationName) {
        this.stationId = stationId;
        setStationName(stationName);
        scheduleDtoList = scheduleService.retrieveSchedules(stationId);
        log.info("-------schedule initialized by station id: " + stationId);
    }
}
