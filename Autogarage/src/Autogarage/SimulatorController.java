package Autogarage;

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
}
