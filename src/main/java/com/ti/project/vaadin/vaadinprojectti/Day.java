package com.ti.project.vaadin.vaadinprojectti;

/**
 * Created by Bartosz on 10.06.2017.
 */
public class Day {
    private String hourToBook;
    private String reservedOn;

    public Day(String hourToBook, String reservedBy) {
        this.hourToBook = hourToBook;
        this.reservedOn = reservedBy;
    }

    public String getHourToBook() {
        return hourToBook;
    }

    public void setHourToBook(String hourToBook) {
        this.hourToBook = hourToBook;
    }

    public String getReservedOn() {
        return reservedOn;
    }

    public void setReservedOn(String reservedOn) {
        this.reservedOn = reservedOn;
    }
}