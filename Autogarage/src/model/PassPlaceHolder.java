package model;

import java.awt.*;

public class PassPlaceHolder extends Car {
	public static final Color COLOR=Color.magenta;	
	
    public PassPlaceHolder() {
    	int stayMinutes = 999999999;
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    public Color getColor(){
    	return COLOR;
    }
    
    public void pay(Car car)
    {
    	//pass holders payment is handled in the model.
    }
}
