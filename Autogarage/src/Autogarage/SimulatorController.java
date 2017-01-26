package Autogarage;

import java.awt.event.*;

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

	
	public void startSimulation(int duration)
	{
		model.run(10);
	}
	
	/**
	 * This method will ask each view to update it's display with new information.
	 */
	public void updateViews()
	{
		view.updateView(); //TODO make a list of all the views instead of just one variable and loop over the list.
	}
	
	/*
	 * The the amount of floors, rows and places.
	 */
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
	
	public void makeInputUI()
	{
		view.makeInputUI();
	}

}
