package com.kal.club.Entity;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventid;

    public Event() {
    }
}
