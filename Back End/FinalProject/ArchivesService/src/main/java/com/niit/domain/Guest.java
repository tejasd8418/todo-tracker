package com.niit.domain;

public class Guest {

    private int guestId;
    private String guestEmailId;

    public Guest(int guestId, String guestEmailId) {
        this.guestId = guestId;
        this.guestEmailId = guestEmailId;
    }

    public Guest() {
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getGuestEmailId() {
        return guestEmailId;
    }

    public void setGuestEmailId(String guestEmailId) {
        this.guestEmailId = guestEmailId;
    }
}
