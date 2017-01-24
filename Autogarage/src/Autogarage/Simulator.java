package Autogarage;

public class Simulator {
	private SimulatorModel model;
	private SimulatorView view;
	private SimulatorController controller;
	
	public Simulator()
	{
		controller = new SimulatorController();
		model = new SimulatorModel(controller);
		view = new SimulatorView(3, 6, 30, controller);
	}
	
}
