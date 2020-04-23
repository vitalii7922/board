package com.tsystems.javaschool.data;
import com.tsystems.javaschool.dto.StationDto;
import com.tsystems.javaschool.service.StationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class StationBean {

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
}
