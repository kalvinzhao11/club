package com.kal.club.Entity;

import javax.persistence.*;

public class Point extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pointid;

    private Integer score;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private User user;

    public Point() {
    }

    public Point(Integer score, User user) {
        this.score = score;
        this.user = user;
    }

    public long getPointid() {
        return pointid;
    }

    public void setPointid(long pointid) {
        this.pointid = pointid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
