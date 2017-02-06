package view;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import controller.SimulatorController;

public class SettingsView extends View{
	private SimulatorController controller;
	
	public SettingsView(SimulatorController controller)
	{
		this.controller = controller;
		
		setLayout(new BorderLayout());
		add(new JLabel("SETTINGS PAGE"), BorderLayout.NORTH);
	}
	
	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}
	
}
