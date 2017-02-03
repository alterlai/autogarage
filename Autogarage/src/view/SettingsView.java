package view;

import javax.swing.JLabel;

import controller.SimulatorController;

public class SettingsView extends View{
	private SimulatorController controller;
	
	public SettingsView(SimulatorController controller)
	{
		this.controller = controller;
		
		this.add(new JLabel("SETTINGS PAGE"));
	}
	
	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}
	
}
