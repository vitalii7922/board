package com.tsystems.javaschool.bean;
import com.tsystems.javaschool.dto.StationDto;
import com.tsystems.javaschool.receiver.Receiver;
import com.tsystems.javaschool.service.StationService;
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
public class StationsBean {

    @Inject
    StationService stationService;

    private List<StationDto> stationDtoList;

    @Inject
    Receiver receiver;

    @Push
    @Inject
    PushContext stationChannel;

    private static final Log log = LogFactory.getLog(StationsBean.class);

    public List<StationDto> getStationDtoList() {
        return stationDtoList;
    }

    @PostConstruct
    public void init() {
        try {
            QueueReceiver queueReceiver = receiver.initReceiver("second");
            queueReceiver.setMessageListener(message -> {
                TextMessage msg = (TextMessage) message;
                refreshStationList();
                stationChannel.send("update");
                try {
                    log.info("stations's list " + msg.getText());
                } catch (JMSException e) {
                    log.error(e.getCause());
                }
            });
        } catch (Exception e) {
            log.error(e.getCause());
        }
        log.info("-----------Receiver of stationBean has been started----------------");
        stationDtoList = stationService.retrieveStations();
    }

    private void refreshStationList() {
        stationDtoList = stationService.retrieveStations();
    }
}
