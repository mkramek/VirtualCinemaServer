package com.cinema.virtualcinema.model;

import javax.persistence.*;

@Entity
@Table(name = "seat_status")
public class SeatStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "seat")
    private Seat seat;

    @Column(name = "status")
    private Integer status;

    @Column(name = "sender")
    private String sender;

    public SeatStatus() {}

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
