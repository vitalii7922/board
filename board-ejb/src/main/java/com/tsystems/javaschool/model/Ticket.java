package com.tsystems.javaschool.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    private Train train;

    @ManyToOne
    private Passenger passenger;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }


    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
