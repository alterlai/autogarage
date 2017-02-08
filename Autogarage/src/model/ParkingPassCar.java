package model;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {

	public static final Color COLOR=Color.blue;
	private static int monthlyRate = 80;
	
    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    public Color getColor(){
    	return COLOR;
    }
    
	public static int getMonthlyRate()
	{
		return monthlyRate;
	}
	
	public static void setMonthlyRate(int newRate) {
		monthlyRate = newRate;
	}
    
    public void pay(Car car)
    {
    	//pass holders payment is handled in the model.
    }
}
