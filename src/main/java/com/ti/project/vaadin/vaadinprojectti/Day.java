package com.ti.project.vaadin.vaadinprojectti;

/**
 * Created by Bartosz on 10.06.2017.
 */
public class Day {
    private String hourToReserve;
    private String reservedOn;

    public Day(String hourToReserve, String reservedOn) {
        this.hourToReserve = hourToReserve;
        this.reservedOn = reservedOn;
    }

    public String getHourToReserve() {
        return hourToReserve;
    }

    public void setHourToReserve(String hourToReserve) {
        this.hourToReserve = hourToReserve;
    }

    public String getReservedOn() {
        return reservedOn;
    }

    public void setReservedOn(String reservedOn) {
        this.reservedOn = reservedOn;
    }
}
