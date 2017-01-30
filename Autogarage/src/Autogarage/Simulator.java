package Autogarage;

import view.*;

public class Simulator {
	private SimulatorModel model;
	private SimulatorView view;
	private SimulatorController controller;
	
	public Simulator()
	{
		controller = new SimulatorController();
		model = new SimulatorModel(controller);
		MainFrame view = new MainFrame(controller);
		controller.setView(view);
		controller.setModel(model);
	}
	
}
