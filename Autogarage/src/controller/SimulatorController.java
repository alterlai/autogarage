package controller;

import java.util.HashMap;

import model.Car;
import model.Location;
import model.SimulatorModel;
import view.*;
import model.*;

public class SimulatorController {
	private SimulatorModel model;
	private MainFrame view;
	private Bank bank;
	
	/**
	 * Set the view for the controller
	 * @param view 	the view for the controller
	 */
	public void setView(MainFrame view)
	{
		this.view = view;
	}
	
	/**
	 * Set the model for the controller
	 * @param model	the model for the controller.
	 */
	public void setModel(SimulatorModel model)
	{
		this.model = model;
	}

	
	/* ----------------------------------------
	 * --------- Methods to the model ---------
	 * ----------------------------------------
	 */
	
	public void startSimulation(int duration)
	{
		model.setSimulationLength(duration); // Get the value that is in the simulation length field and set it in the model.
		model.start();						// Start the simulation.
	}
	
	public void stopSimulation(boolean flag)
	{
		model.setRunning(flag);
	}
	
	public int getNumberOfFloors()
	{
		return model.getNumberOfFloors();
	}
	public int getNumberOfRows()
	{
		return model.getNumberOfRows();
	}
	public int getNumberOfPlaces()
	{
		return model.getNumberOfPlaces();
	}
	public int getTotalNumberOfPlaces()
	{
		return model.getNumberOfPlaces() * model.getNumberOfRows() * model.getNumberOfFloors();
	}
	
	public Car getCarAt(Location location)
	{
		return model.getCarAt(location);
	}
	
	public int getCurrentTick()
	{
		return model.getCurrentTick();
	}
	
	public void setRunning(boolean flag)
	{
		model.setRunning(flag);
	}
	
	public HashMap<String, Integer> getTotalCarInfo()
	{
		try {
			return model.getTotalCarInfo();
		}
		catch (NullPointerException e)
		{
			return null;
		}
	}
	
	/**
	 * @return the bank that's being used by the model.
	 */
	public Bank getBank()
	{
		return model.getBank();
	}
	
	/**
     * Sets the number of ad hoc cars arriving per hour during th week.
     * @param int input	cars per hour.
     */
    public void setWeekDayArrivals(int input){
    	model.setWeekDayArrivals(input);
    }
    
    /**
     * Sets the number of ad hoc cars arriving per hour in the weekend.
     * @param int input	cars per hour.
     */
    public void setWeekendArrivals(int input){
    	model.setWeekendArrivals(input);
    }
    /**
     * Sets the number of pass holders arriving each hour in a weekday.
     * @param input int cars per hour.
     */
    public void setWeekDayPassArrivals(int input){
    	model.setWeekDayPassArrivals(input);
    }
    public void setWeekendPassArrivals(int input){
    	model.setWeekendPassArrivals(input);
    }
    public void setWeekDayReservedArrivals(int input){
    	model.setWeekDayReservedArrivals(input);
    }
    public void setWeekendReservedArrivals(int input){
    	model.setWeekendReservedArrivals(input);
    }
    public void setEnteringSpeed(int input){
    	model.setEnteringSpeed(input);
    }
    public void setPaymentSpeed(int input){
    	model.setPaymentSpeed(input);
    }
    public void setExitSpeed(int input){
    	model.setExitSpeed(input);
    }
    public void setNumberOfFloors(int input){
    	model.setNumberOfFloors(input);
    }
    public void setNumberOfRows(int input){
    	model.setNumberOfRows(input);
    }
    public void setNumberOfPlaces(int input){
    	model.setNumberOfPlaces(input);
    }
    public void setNumberOfPassholders(int input){
    	model.setNumberOfPassholders(input);
    }
    public void setHourlyFee(Double input){
    	AdHocCar.setHourlyRate(input);
    	ReservationCar.setHourlyRate(input);
    }
    public void setBonusHourlyFee(Double input){
    	ReservationCar.setBonusFee(input);
    }
	public void setParkingpassFee(int input){
		ParkingPassCar.setMonthlyRate(input);
	}
    
	/**
	 * returns the time object.
	 * @return
	 */
	public Time getTime()
	{
		return model.getTime();
	}
	
	/* ----------------------------------------
	 * --------- Methods for both ---------
	 * ----------------------------------------
	 */
	
	public void resetSimulation()
	{
		model.reset();
		view.reset();
		view.updateView();
		
	}

	/* ----------------------------------------
	 * --------- Methods to the view ---------
	 * ----------------------------------------
	 */
	
	/**
	 * This method will ask each view to update it's display with new information.
	 */
	public void updateViews()
	{
		view.updateView();
	}
	
	/**
	 * Set the interval between each tick.
	 * @param interval
	 */
	public void setTickPause(int interval)
	{
		model.setTickPause(interval);
	}

}
