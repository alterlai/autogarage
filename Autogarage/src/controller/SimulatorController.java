package controller;

import model.Car;
import model.Location;
import model.SimulatorModel;
import view.SimulatorView;
import java.util.HashMap;

public class SimulatorController {
	private SimulatorModel model;
	private SimulatorView view;
	
	/**
	 * Set the view for the controller
	 * @param view 	the view for the controller
	 */
	public void setView(SimulatorView view)
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
	 * --------- Methods for the model ---------
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
		return model.getTotalCarInfo();
	}
	
	/* ----------------------------------------
	 * --------- Methods for both ---------
	 * ----------------------------------------
	 */
	
	public void resetSimulation()
	{
		model.reset();
		view.updateView();
	}

	/* ----------------------------------------
	 * --------- Methods for the view ---------
	 * ----------------------------------------
	 */
	public void drawGUI()
	{
		updateViews();
		view.makeInputUI();
	}
	
	/**
	 * This method will ask each view to update it's display with new information.
	 */
	public void updateViews()
	{
		view.updateView(); //TODO make a list of all the views instead of just one variable and loop over the list.
	}

}
