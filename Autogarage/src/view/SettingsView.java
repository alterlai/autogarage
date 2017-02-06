package view;

import javax.swing.JLabel;

import controller.SimulatorController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SettingsView extends View{
	private SimulatorController controller;
	private JTextField simTickPauseTF;
	
	public SettingsView(SimulatorController controller)
	{
		this.controller = controller;
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Tick delay (default: 50)");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.add(lblNewLabel);
		
		simTickPauseTF = new JTextField("50");
		panel.add(simTickPauseTF);
		simTickPauseTF.setColumns(10);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(e -> applySettings()); 
		panel.add(btnApply);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
	}
	
	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Get the value that's put in the input field.
	 * @return
	 */
	public int getSimTickPauseTF()
	{
		try{
    		return Integer.parseInt(simTickPauseTF.getText());
    	}
    	catch (NumberFormatException e) {
    		showError("Enter a valid number"); 
    		return 0;
    	}
	}
	
	public void applySettings()
	{
		controller.setTickPause(getSimTickPauseTF());
	}
	
}
