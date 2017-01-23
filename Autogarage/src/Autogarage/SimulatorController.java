package Autogarage;

public class SimulatorController {
	private SimulatorModel simulatorModel;
	private SimulatorView simulatorView;
	
	/**
	 * Constructor of the controller
	 */
	public SimulatorController(SimulatorView simulatorView, SimulatorModel simulatorModel)
	{
		this.simulatorModel = simulatorModel;
		this.simulatorView = simulatorView;
	}
}
