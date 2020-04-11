package com.tsystems.javaschool.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Station implements Serializable, Comparable<Station> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "originStation", fetch = FetchType.EAGER)
    private List<Train> trainsDeparture;

    @OneToMany(mappedBy = "destinationStation")
    private List<Train> trainsArrive;

    @OneToMany(mappedBy = "station")
    private List<Schedule> schedules;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Train> getTrainsDeparture() {
        return trainsDeparture;
    }

    public void setTrainsDeparture(List<Train> trainsDeparture) {
        this.trainsDeparture = trainsDeparture;
    }

    public List<Train> getTrainsArrive() {
        return trainsArrive;
    }

    public void setTrainsArrive(List<Train> trainsArrive) {
        this.trainsArrive = trainsArrive;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public int compareTo(Station o) {
        return name.compareTo(o.name);
    }
}
