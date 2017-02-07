package view;

import controller.SimulatorController;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.HashMap;

@SuppressWarnings("serial")
public class StatisticsView extends View {
	private SimulatorController controller;
	private JTextField totalCarsTf;
	private JTextField adHocTF;
	private JTextField passCarTf;
	private JTextField reserverationTf;
	private JTextField freeSpotsTf;
	private JTextField adHocEntranceQueueField;
	private JTextField passEntranceQueueField;
	private JTextField paymentQueueField;
	private JTextField exitQueueField;
	private View graphPanel;
	private View piePanel;

	/**
	 * Create the panel.
	 */
	public StatisticsView(SimulatorController controller) {
		this.controller = controller;
		
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel PopulationPanel = new JPanel();
		add(PopulationPanel);
		PopulationPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPopulationOverview = new JLabel("Population overview");
		lblPopulationOverview.setHorizontalAlignment(SwingConstants.CENTER);
		PopulationPanel.add(lblPopulationOverview, BorderLayout.NORTH);
		
		JPanel populationData = new JPanel();
		populationData.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		populationData.setBackground(Color.WHITE);
		PopulationPanel.add(populationData, BorderLayout.CENTER);
		
		GridBagLayout gbl_populationData = new GridBagLayout();
		gbl_populationData.columnWidths = new int[] {0, 0, 0, 0, 1};
		gbl_populationData.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 1};
		gbl_populationData.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_populationData.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		populationData.setLayout(gbl_populationData);
		
		JLabel totalCarsLb = new JLabel("Total amount of cars");
		GridBagConstraints gbc_totalCarsLb = new GridBagConstraints();
		gbc_totalCarsLb.anchor = GridBagConstraints.EAST;
		gbc_totalCarsLb.insets = new Insets(0, 0, 5, 5);
		gbc_totalCarsLb.gridx = 0;
		gbc_totalCarsLb.gridy = 0;
		populationData.add(totalCarsLb, gbc_totalCarsLb);
		totalCarsLb.setHorizontalAlignment(SwingConstants.RIGHT);
		
		totalCarsTf = new JTextField();
		GridBagConstraints gbc_totalCarsTf = new GridBagConstraints();
		gbc_totalCarsTf.insets = new Insets(0, 0, 5, 5);
		gbc_totalCarsTf.gridx = 1;
		gbc_totalCarsTf.gridy = 0;
		populationData.add(totalCarsTf, gbc_totalCarsTf);
		totalCarsTf.setText("0");
		totalCarsTf.setEditable(false);
		totalCarsTf.setColumns(10);
		
		JLabel lblCarsInEnter = new JLabel("Ad hoc cars in entrance queue");
		GridBagConstraints gbc_lblCarsInEnter = new GridBagConstraints();
		gbc_lblCarsInEnter.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarsInEnter.anchor = GridBagConstraints.EAST;
		gbc_lblCarsInEnter.gridx = 2;
		gbc_lblCarsInEnter.gridy = 0;
		populationData.add(lblCarsInEnter, gbc_lblCarsInEnter);
		
		adHocEntranceQueueField = new JTextField();
		adHocEntranceQueueField.setEditable(false);
		adHocEntranceQueueField.setText("0");
		GridBagConstraints gbc_adHocEntranceQueueField = new GridBagConstraints();
		gbc_adHocEntranceQueueField.insets = new Insets(0, 0, 5, 0);
		gbc_adHocEntranceQueueField.gridx = 3;
		gbc_adHocEntranceQueueField.gridy = 0;
		populationData.add(adHocEntranceQueueField, gbc_adHocEntranceQueueField);
		adHocEntranceQueueField.setColumns(10);
		
		JLabel passCarLb = new JLabel("Parking pass");
		GridBagConstraints gbc_passCarLb = new GridBagConstraints();
		gbc_passCarLb.anchor = GridBagConstraints.EAST;
		gbc_passCarLb.insets = new Insets(0, 0, 5, 5);
		gbc_passCarLb.gridx = 0;
		gbc_passCarLb.gridy = 1;
		populationData.add(passCarLb, gbc_passCarLb);
		
		//
		passCarTf = new JTextField();
		GridBagConstraints gbc_passCarTf = new GridBagConstraints();
		gbc_passCarTf.insets = new Insets(0, 0, 5, 5);
		gbc_passCarTf.gridx = 1;
		gbc_passCarTf.gridy = 1;
		populationData.add(passCarTf, gbc_passCarTf);
		passCarTf.setText("0");
		passCarTf.setEditable(false);
		passCarTf.setColumns(10);
		
		JLabel lblPassholdersInEntrance = new JLabel("Passholders in entrance queue");
		GridBagConstraints gbc_lblPassholdersInEntrance = new GridBagConstraints();
		gbc_lblPassholdersInEntrance.anchor = GridBagConstraints.EAST;
		gbc_lblPassholdersInEntrance.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassholdersInEntrance.gridx = 2;
		gbc_lblPassholdersInEntrance.gridy = 1;
		populationData.add(lblPassholdersInEntrance, gbc_lblPassholdersInEntrance);
		
		passEntranceQueueField = new JTextField();
		passEntranceQueueField.setEditable(false);
		passEntranceQueueField.setText("0");
		GridBagConstraints gbc_passEntranceQueueField = new GridBagConstraints();
		gbc_passEntranceQueueField.insets = new Insets(0, 0, 5, 0);
		gbc_passEntranceQueueField.gridx = 3;
		gbc_passEntranceQueueField.gridy = 1;
		populationData.add(passEntranceQueueField, gbc_passEntranceQueueField);
		passEntranceQueueField.setColumns(10);
		
		//Adhoc label
		JLabel lblAdhocCars = new JLabel("AdHoc");
		GridBagConstraints gbc_lblAdhocCars = new GridBagConstraints();
		gbc_lblAdhocCars.anchor = GridBagConstraints.EAST;
		gbc_lblAdhocCars.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdhocCars.gridx = 0;
		gbc_lblAdhocCars.gridy = 2;
		populationData.add(lblAdhocCars, gbc_lblAdhocCars);
		lblAdhocCars.setHorizontalAlignment(SwingConstants.RIGHT);
		
		adHocTF = new JTextField();
		GridBagConstraints gbc_adHocTF = new GridBagConstraints();
		gbc_adHocTF.insets = new Insets(0, 0, 5, 5);
		gbc_adHocTF.gridx = 1;
		gbc_adHocTF.gridy = 2;
		populationData.add(adHocTF, gbc_adHocTF);
		adHocTF.setText("0");
		adHocTF.setEditable(false);
		adHocTF.setColumns(10);
		
		JLabel lblCarsInPayment = new JLabel("Cars in payment queue");
		GridBagConstraints gbc_lblCarsInPayment = new GridBagConstraints();
		gbc_lblCarsInPayment.anchor = GridBagConstraints.EAST;
		gbc_lblCarsInPayment.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarsInPayment.gridx = 2;
		gbc_lblCarsInPayment.gridy = 2;
		populationData.add(lblCarsInPayment, gbc_lblCarsInPayment);
		
		paymentQueueField = new JTextField();
		paymentQueueField.setEditable(false);
		paymentQueueField.setText("0");
		GridBagConstraints gbc_paymentQueueField = new GridBagConstraints();
		gbc_paymentQueueField.insets = new Insets(0, 0, 5, 0);
		gbc_paymentQueueField.gridx = 3;
		gbc_paymentQueueField.gridy = 2;
		populationData.add(paymentQueueField, gbc_paymentQueueField);
		paymentQueueField.setColumns(10);
		
		JLabel reservationLb = new JLabel("Reservation");
		GridBagConstraints gbc_reservationLb = new GridBagConstraints();
		gbc_reservationLb.anchor = GridBagConstraints.EAST;
		gbc_reservationLb.insets = new Insets(0, 0, 5, 5);
		gbc_reservationLb.gridx = 0;
		gbc_reservationLb.gridy = 3;
		populationData.add(reservationLb, gbc_reservationLb);
		
		reserverationTf = new JTextField();
		reserverationTf.setText("0");
		reserverationTf.setEditable(false);
		reserverationTf.setColumns(10);
		GridBagConstraints gbc_reserverationTf = new GridBagConstraints();
		gbc_reserverationTf.insets = new Insets(0, 0, 5, 5);
		gbc_reserverationTf.gridx = 1;
		gbc_reserverationTf.gridy = 3;
		populationData.add(reserverationTf, gbc_reserverationTf);
		
		JLabel lblCarsInExit = new JLabel("Cars in exit queue");
		GridBagConstraints gbc_lblCarsInExit = new GridBagConstraints();
		gbc_lblCarsInExit.anchor = GridBagConstraints.EAST;
		gbc_lblCarsInExit.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarsInExit.gridx = 2;
		gbc_lblCarsInExit.gridy = 3;
		populationData.add(lblCarsInExit, gbc_lblCarsInExit);
		
		exitQueueField = new JTextField();
		exitQueueField.setText("0");
		exitQueueField.setEditable(false);
		GridBagConstraints gbc_exitQueueField = new GridBagConstraints();
		gbc_exitQueueField.insets = new Insets(0, 0, 5, 0);
		gbc_exitQueueField.gridx = 3;
		gbc_exitQueueField.gridy = 3;
		populationData.add(exitQueueField, gbc_exitQueueField);
		exitQueueField.setColumns(10);
		
		JLabel freeSpotsLb = new JLabel("Free spots");
		GridBagConstraints gbc_freeSpotsLb = new GridBagConstraints();
		gbc_freeSpotsLb.anchor = GridBagConstraints.EAST;
		gbc_freeSpotsLb.insets = new Insets(0, 0, 5, 5);
		gbc_freeSpotsLb.gridx = 0;
		gbc_freeSpotsLb.gridy = 4;
		populationData.add(freeSpotsLb, gbc_freeSpotsLb);
		
		freeSpotsTf = new JTextField();
		freeSpotsTf.setText("0");
		freeSpotsTf.setEditable(false);
		freeSpotsTf.setColumns(10);
		GridBagConstraints gbc_freeSpotsTf = new GridBagConstraints();
		gbc_freeSpotsTf.insets = new Insets(0, 0, 5, 5);
		gbc_freeSpotsTf.gridx = 1;
		gbc_freeSpotsTf.gridy = 4;
		populationData.add(freeSpotsTf, gbc_freeSpotsTf);
		
		JPanel BalancePanel = new JPanel();
		add(BalancePanel);
		BalancePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel BalanceTitle = new JLabel("Balance overview");
		BalanceTitle.setVerticalAlignment(SwingConstants.TOP);
		BalanceTitle.setHorizontalAlignment(SwingConstants.CENTER);
		BalancePanel.add(BalanceTitle, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(Color.WHITE);
		BalancePanel.add(panel, BorderLayout.CENTER);
		
		
		// Graphview stuff
		JPanel GraphViewWrapper = new JPanel();
		GraphViewWrapper.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GraphViewWrapper.setLayout(new BorderLayout(0, 0));
		add(GraphViewWrapper);
		
		JLabel graphView = new JLabel("Graph view");
		graphView.setHorizontalAlignment(SwingConstants.CENTER);
		GraphViewWrapper.add(graphView, BorderLayout.NORTH);
		
		graphPanel = new GraphView(controller);
		graphPanel.setBackground(Color.WHITE);
		GraphViewWrapper.add(graphPanel);
		
		
		// PieChartview stuff
		JPanel PieChartViewWrapper = new JPanel();
		PieChartViewWrapper.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		PieChartViewWrapper.setLayout(new BorderLayout(0, 0));
		add(PieChartViewWrapper);
		
		JLabel pieView = new JLabel("PieChart view");
		pieView.setHorizontalAlignment(SwingConstants.CENTER);
		PieChartViewWrapper.add(pieView, BorderLayout.NORTH);
		
		piePanel = new PieChartView(controller);
		piePanel.setBackground(Color.WHITE);
		PieChartViewWrapper.add(piePanel);

	}

	/**
	 * Updates the contents of the JFrame.
	 */
	@Override
	public void updateView() {
		graphPanel.updateView();
		piePanel.updateView();
		
		// Population Overview.
		HashMap<String, Integer> totalCarInfo = controller.getTotalCarInfo();
		totalCarsTf.setText("" + totalCarInfo.get("all"));
		adHocTF.setText("" + totalCarInfo.get("adhoc"));
		passCarTf.setText("" + totalCarInfo.get("pass"));
		reserverationTf.setText("" + totalCarInfo.get("reservation"));
		freeSpotsTf.setText("" + totalCarInfo.get("free"));
		adHocEntranceQueueField.setText("" + totalCarInfo.get("entranceCarQueue"));
		passEntranceQueueField.setText("" + totalCarInfo.get("entrancePassQueue"));
		paymentQueueField.setText("" + totalCarInfo.get("paymentQueue"));
		exitQueueField.setText("" + totalCarInfo.get("exitCarQueue"));
		
		//Balance Overview.
	} 
}
