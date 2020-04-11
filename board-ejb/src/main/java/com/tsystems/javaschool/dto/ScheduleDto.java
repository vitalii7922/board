package com.tsystems.javaschool.dto;


import com.tsystems.javaschool.model.Station;
import com.tsystems.javaschool.model.Train;

import java.io.Serializable;


public class ScheduleDto implements Serializable, Comparable<ScheduleDto> {
    long id;

    private String arrivalTime;

    private String departureTime;

    Train train;

    Station station;

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public int compareTo(ScheduleDto o) {
        return train.getNumber() - o.getTrain().getNumber();
    }
}
