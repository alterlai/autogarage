package Autogarage;

public class Simulator {
	private SimulatorModel simulatorModel;
	private SimulatorView simulatorView;
	
	public Simulator()
	{
		simulatorModel = new SimulatorModel();
		simulatorView = new SimulatorView(3, 6, 30);
		simulatorView.setModel(simulatorModel);
		simulatorModel.setView(simulatorView);
	}
}
