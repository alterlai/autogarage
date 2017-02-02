package model;

import java.awt.*;

public abstract class Car {

    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;
    protected int stayMinutes;
    protected static Bank BANK;

    /**
     * Constructor for objects of class Car
     */
    public Car() {
    }
    
    /**
     * Set the bank to regulate payment.
     * @param bank
     */
    public static void setBank(Bank bank)
    {
    	BANK = bank;
    }
    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
        this.stayMinutes = minutesLeft;
    }
    
    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public boolean getHasToPay() {
        return hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    public void tick() {
        minutesLeft--;
    }
    
    public int getStayedMinutes()
    {
    	return this.stayMinutes;
    }
    
    public abstract Color getColor();
    public abstract void pay();
    
}