package com.room.microservice.domain;

import javax.persistence.*;

@Entity
public class RateAndComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String comment;

    private int rating;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Room roomRateAndComment;

    private boolean isAllowed;

    public RateAndComment() {
    }

    public RateAndComment(String comment, int rating, Room roomRateAndComment, boolean isAllowed) {
        this.comment = comment;
        this.rating = rating;
        this.roomRateAndComment = roomRateAndComment;
        this.isAllowed = isAllowed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Room getRoomRateAndComment() {
        return roomRateAndComment;
    }

    public void setRoomRateAndComment(Room roomRateAndComment) {
        this.roomRateAndComment = roomRateAndComment;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }
}
