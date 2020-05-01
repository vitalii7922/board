package com.tsystems.javaschool.data;
import com.tsystems.javaschool.dto.StationDto;
import com.tsystems.javaschool.service.StationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class StationBean implements Serializable {

    @Inject
    StationService stationService;

    private List<StationDto> stationDtoList;

    public List<StationDto> getStations() {
        return stationDtoList;
    }

    @PostConstruct
    public void setStations() {
        stationDtoList = stationService.retrieveStations();
    }

    public void processAjaxBehavior(javax.faces.event.AjaxBehaviorEvent event) {
        System.out.println("-----ajax-----");
        stationDtoList = stationService.retrieveStations();
    }
}
