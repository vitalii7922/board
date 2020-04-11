package com.tsystems.javaschool.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "arrive_time")
    private LocalDateTime arrivalTime;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @ManyToOne
    @JoinColumn(name = "train_id")
    Train train;

    @ManyToOne
    @JoinColumn(name = "station_id")
    Station station;

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
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
}

