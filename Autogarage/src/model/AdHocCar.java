package model;

import java.util.Random;
import java.awt.*;

public class AdHocCar extends Car {
	private static final Color COLOR=Color.red;
	//TODO add mutator methods for fees.
	private static double hourlyRate = 2.20;		// The cost of parking per minute.
	
    public AdHocCar() {
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
    	Car.BANK.addBalance(hourlyRate / 60 * this.getStayedMinutes());
    }
}