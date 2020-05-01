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

@Named
@ApplicationScoped
public class StationBean {

    @Inject
    StationService stationService;

    private List<StationDto> stationDtoList;

    @Push
    @Inject
    PushContext stationChannel;

    private static final Log log = LogFactory.getLog(StationBean.class);

    public List<StationDto> getStationDtoList() {
        return stationDtoList;
    }

    @PostConstruct
    public void init() {
        try {
            QueueReceiver receiver = Receiver.initReceiver("second");
            receiver.setMessageListener(message -> {
                TextMessage msg = (TextMessage) message;
                refreshStationList();
                stationChannel.send("update");
                log.info("stations's list " + msg);
            });
        } catch (Exception e) {
            log.error(e.getCause());
        }
        stationDtoList = stationService.retrieveStations();
    }

    private void refreshStationList() {
        stationDtoList = stationService.retrieveStations();
    }
}
