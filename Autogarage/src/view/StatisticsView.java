package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import controller.*;

public class StatisticsView extends View{
	private SimulatorController controller;
	
	private JLabel totalCarsLabel;
	
	/**
	 * Constructor
	 * @param controller controlling the view.
	 */
	public StatisticsView(SimulatorController controller)
	{
		this.controller = controller;
		
		totalCarsLabel = new JLabel("Total amount of cars: " + 0);
		this.add(totalCarsLabel);
	}
	
	/**
	 * Updates the contents of the JFrame.
	 */
	@Override
	public void updateView() {
		HashMap<String, Integer> totalCarInfo = controller.getTotalCarInfo();
		totalCarsLabel.setText("Total amount of cars: " + totalCarInfo.get("all"));
	}
}
