package Autogarage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SimulatorRunner {
	/**
	 * Main class in the application.
	 */
	public static void main(String[] args)
	{
		//making the frame
		JFrame frame = new JFrame("Autogarage");
		frame.setSize(800, 600);
		frame.setVisible(true);
		
		//adding tab pages to the frame
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		//adding the panel for running the simulator
		JPanel panel = new JPanel();
		tabbedPane.addTab("Simulator", null, panel, null);
		
		//setting up the button to start the simulator
		JButton btnStartSimulation = new JButton("Start simulation");
		btnStartSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {        
		        //running the actual simulator
		        @SuppressWarnings("unused")
		        Simulator controller = new Simulator();

			}
		});
		
		//adding the above created button to the first panel
		panel.add(btnStartSimulation);
		
		//adding the panel for Car statistics
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Car Statistics", null, panel_1, null);
		
		//adding the panel for the Revenue
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Revenue", null, panel_2, null);
		
		//adding the panel for the graph showing when the car park is the busiest
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Busy Periods", null, panel_3, null);
		
		//adding the panel for other/undefined purposes
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Other", null, panel_4, null);
	}
}
