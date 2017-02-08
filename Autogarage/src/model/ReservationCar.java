package model;

import java.util.Random;
import java.awt.*;

public class ReservationCar extends Car {
	private static final Color COLOR=Color.green;
	//TODO add mutator methods for fees.
	private static double hourlyRate = 2.20;		// The cost of parking per minute.
	private static double bonusFee = 1.50;			// Bonus fee for reservations
	
    public ReservationCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
    }
    
    public Color getColor(){
    	return COLOR;
    }
    
    @Override
    public void pay(Car car)
    {
    	Car.BANK.addBalance(hourlyRate / 60 * this.getStayedMinutes() + bonusFee, car);
    }
    
    public static double getHourlyRate() {
    	return hourlyRate;
    }
    
    public static void setHourlyRate(Double newRate) {
    	hourlyRate = newRate;
    }
    
    public static double getBonusFee() {
    	return bonusFee;
    }
    
    public static void setBonusFee(Double newFee) {
    	bonusFee = newFee;
    }
}