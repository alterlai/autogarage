package model;

import java.util.Random;
import java.awt.*;

public class ReservationCar extends Car {
	private static final Color COLOR=Color.green;
	//TODO add mutator methods for fees.
	private static double hourlyRate = 2.20;		// The cost of parking per minute.
	private static double bonusFee = 1.50;				// Bonus fee for reservations
	
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
    public void pay()
    {
    	Car.BANK.addBalance(hourlyRate / 60 * this.getStayedMinutes() + bonusFee);
    }
}