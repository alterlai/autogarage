package view;

import model.Bank;
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
	private JTextField standardEntranceTf;
	private JTextField passEntranceTf;
	private JTextField paymentQueueTf;
	private JTextField exitQueueTf;
	private View graphPanel;
	private View piePanel;
	private JTextField servedCarsTf;
	private JTextField missedCarsTf;
	private JTextField totalBalanceTf;
	private JTextField adHocPaidTf;
	private JTextField adHocBalanceTf;
	private JTextField passPaidTf;
	private JTextField passBalanceTf;	
	private JTextField reservationPaidTf;
	private JTextField reservationBalanceTf;

	/**
	 * Create the panel.
	 */
	public StatisticsView(SimulatorController controller) {
		this.controller = controller;
		
		setLayout(new GridLayout(0, 2, 0, 0));
		
		/*
		 * Statistics panel
		 */
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
		gbl_populationData.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 1, 0};
		gbl_populationData.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_populationData.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE, 0.0};
		populationData.setLayout(gbl_populationData);
	
		
		// Total label
		JLabel totalCarsLb = new JLabel("Total amount of cars");
		GridBagConstraints gbc_totalCarsLb = new GridBagConstraints();
		gbc_totalCarsLb.anchor = GridBagConstraints.EAST;
		gbc_totalCarsLb.insets = new Insets(0, 0, 5, 5);
		gbc_totalCarsLb.gridx = 0;
		gbc_totalCarsLb.gridy = 0;
		populationData.add(totalCarsLb, gbc_totalCarsLb);
		totalCarsLb.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// Total text field
		totalCarsTf = new JTextField();
		GridBagConstraints gbc_totalCarsTf = new GridBagConstraints();
		gbc_totalCarsTf.insets = new Insets(0, 0, 5, 5);
		gbc_totalCarsTf.gridx = 1;
		gbc_totalCarsTf.gridy = 0;
		populationData.add(totalCarsTf, gbc_totalCarsTf);
		totalCarsTf.setText("0");
		totalCarsTf.setEditable(false);
		totalCarsTf.setColumns(10);
		
		// AdHoc label
		JLabel lblAdhocCars = new JLabel("AdHoc");
		GridBagConstraints gbc_lblAdhocCars = new GridBagConstraints();
		gbc_lblAdhocCars.anchor = GridBagConstraints.EAST;
		gbc_lblAdhocCars.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdhocCars.gridx = 0;
		gbc_lblAdhocCars.gridy = 1;
		populationData.add(lblAdhocCars, gbc_lblAdhocCars);
		lblAdhocCars.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// AdHoc text field
		adHocTF = new JTextField();
		adHocTF.setText("0");
		adHocTF.setEditable(false);
		adHocTF.setColumns(10);
		GridBagConstraints gbc_adHocTF = new GridBagConstraints();
		gbc_adHocTF.insets = new Insets(0, 0, 5, 5);
		gbc_adHocTF.gridx = 1;
		gbc_adHocTF.gridy = 1;
		populationData.add(adHocTF, gbc_adHocTF);
		
		// Parking pass label
		JLabel lblParkingPass = new JLabel("Parking pass");
		GridBagConstraints gbc_lblParkingPass = new GridBagConstraints();
		gbc_lblParkingPass.insets = new Insets(0, 0, 5, 5);
		gbc_lblParkingPass.anchor = GridBagConstraints.EAST;
		gbc_lblParkingPass.gridx = 0;
		gbc_lblParkingPass.gridy = 2;
		populationData.add(lblParkingPass, gbc_lblParkingPass);
		
		// Parking pass text field
		passCarTf = new JTextField();
		GridBagConstraints gbc_passCarTf = new GridBagConstraints();
		gbc_passCarTf.insets = new Insets(0, 0, 5, 5);
		gbc_passCarTf.gridx = 1;
		gbc_passCarTf.gridy = 2;
		populationData.add(passCarTf, gbc_passCarTf);
		passCarTf.setText("0");
		passCarTf.setEditable(false);
		passCarTf.setColumns(10);
		
		// Reservation label
		JLabel reservationLb = new JLabel("Reservation");
		GridBagConstraints gbc_reservationLb = new GridBagConstraints();
		gbc_reservationLb.anchor = GridBagConstraints.EAST;
		gbc_reservationLb.insets = new Insets(0, 0, 5, 5);
		gbc_reservationLb.gridx = 0;
		gbc_reservationLb.gridy = 3;
		populationData.add(reservationLb, gbc_reservationLb);
		
		// Reservation text field
		reserverationTf = new JTextField();
		reserverationTf.setText("0");
		reserverationTf.setEditable(false);
		reserverationTf.setColumns(10);
		GridBagConstraints gbc_reserverationTf = new GridBagConstraints();
		gbc_reserverationTf.insets = new Insets(0, 0, 5, 5);
		gbc_reserverationTf.gridx = 1;
		gbc_reserverationTf.gridy = 3;
		populationData.add(reserverationTf, gbc_reserverationTf);
		
		// Free spots label
		JLabel freeSpotsLb = new JLabel("Free spots");
		GridBagConstraints gbc_freeSpotsLb = new GridBagConstraints();
		gbc_freeSpotsLb.anchor = GridBagConstraints.EAST;
		gbc_freeSpotsLb.insets = new Insets(0, 0, 5, 5);
		gbc_freeSpotsLb.gridx = 0;
		gbc_freeSpotsLb.gridy = 4;
		populationData.add(freeSpotsLb, gbc_freeSpotsLb);
		
		// Free spots text field
		freeSpotsTf = new JTextField();
		freeSpotsTf.setText("0");
		freeSpotsTf.setEditable(false);
		freeSpotsTf.setColumns(10);
		GridBagConstraints gbc_freeSpotsTf = new GridBagConstraints();
		gbc_freeSpotsTf.insets = new Insets(0, 0, 5, 5);
		gbc_freeSpotsTf.gridx = 1;
		gbc_freeSpotsTf.gridy = 4;
		populationData.add(freeSpotsTf, gbc_freeSpotsTf);
		
		// Served cars label
		JLabel servedCarsLb = new JLabel("Cars served");
		GridBagConstraints gbc_servedCarsLb = new GridBagConstraints();
		gbc_servedCarsLb.anchor = GridBagConstraints.EAST;
		gbc_servedCarsLb.insets = new Insets(0, 0, 5, 5);
		gbc_servedCarsLb.gridx = 0;
		gbc_servedCarsLb.gridy = 5;
		populationData.add(servedCarsLb, gbc_servedCarsLb);
		
		// Served cars text field
		servedCarsTf = new JTextField();
		GridBagConstraints gbc_servedCarsTf = new GridBagConstraints();
		gbc_servedCarsTf.insets = new Insets(0, 0, 5, 5);
		gbc_servedCarsTf.gridx = 1;
		gbc_servedCarsTf.gridy = 5;
		populationData.add(servedCarsTf, gbc_servedCarsTf);
		servedCarsTf.setText("0");
		servedCarsTf.setEditable(false);
		servedCarsTf.setColumns(10);
		
		// Missed cars label
		JLabel missedCarsLb = new JLabel("Cars missed");
		GridBagConstraints gbc_missedCarsLb = new GridBagConstraints();
		gbc_missedCarsLb.anchor = GridBagConstraints.EAST;
		gbc_missedCarsLb.insets = new Insets(0, 0, 5, 5);
		gbc_missedCarsLb.gridx = 0;
		gbc_missedCarsLb.gridy = 6;
		populationData.add(missedCarsLb, gbc_missedCarsLb);
		
		// Missed cars text field
		missedCarsTf = new JTextField();
		GridBagConstraints gbc_missedCarsTf = new GridBagConstraints();
		gbc_missedCarsTf.insets = new Insets(0, 0, 5, 5);
		gbc_missedCarsTf.gridx = 1;
		gbc_missedCarsTf.gridy = 6;
		populationData.add(missedCarsTf, gbc_missedCarsTf);
		missedCarsTf.setText("0");
		missedCarsTf.setEditable(false);
		missedCarsTf.setColumns(10);
		
		// Standard entrance queue length label
		JLabel lblCarsInStandardEntrance = new JLabel("Cars in standard entrance queue");
		GridBagConstraints gbc_lblCarsInStandardEntrance = new GridBagConstraints();
		gbc_lblCarsInStandardEntrance.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarsInStandardEntrance.anchor = GridBagConstraints.EAST;
		gbc_lblCarsInStandardEntrance.gridx = 2;
		gbc_lblCarsInStandardEntrance.gridy = 0;
		populationData.add(lblCarsInStandardEntrance, gbc_lblCarsInStandardEntrance);
		
		// Standard entrance queue length text field
		standardEntranceTf = new JTextField();
		standardEntranceTf.setEditable(false);
		standardEntranceTf.setText("0");
		GridBagConstraints gbc_standardEntranceTf = new GridBagConstraints();
		gbc_standardEntranceTf.insets = new Insets(0, 0, 5, 0);
		gbc_standardEntranceTf.gridx = 3;
		gbc_standardEntranceTf.gridy = 0;
		populationData.add(standardEntranceTf, gbc_standardEntranceTf);
		standardEntranceTf.setColumns(10);
		
		// Pass holders entrance queue length label
		JLabel lblCarsInPassEntrance = new JLabel("Cars in passholders entrance queue");
		GridBagConstraints gbc_lblCarsInPassEntrance = new GridBagConstraints();
		gbc_lblCarsInPassEntrance.anchor = GridBagConstraints.EAST;
		gbc_lblCarsInPassEntrance.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarsInPassEntrance.gridx = 2;
		gbc_lblCarsInPassEntrance.gridy = 1;
		populationData.add(lblCarsInPassEntrance, gbc_lblCarsInPassEntrance);
		
		// Pass holders entrance queue length text field
		passEntranceTf = new JTextField();
		passEntranceTf.setEditable(false);
		passEntranceTf.setText("0");
		GridBagConstraints gbc_passEntranceTf = new GridBagConstraints();
		gbc_passEntranceTf.insets = new Insets(0, 0, 5, 0);
		gbc_passEntranceTf.gridx = 3;
		gbc_passEntranceTf.gridy = 1;
		populationData.add(passEntranceTf, gbc_passEntranceTf);
		passEntranceTf.setColumns(10);
		
		// Payment queue length label
		JLabel lblCarsInPayment = new JLabel("Cars in payment queue");
		GridBagConstraints gbc_lblCarsInPayment = new GridBagConstraints();
		gbc_lblCarsInPayment.anchor = GridBagConstraints.EAST;
		gbc_lblCarsInPayment.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarsInPayment.gridx = 2;
		gbc_lblCarsInPayment.gridy = 2;
		populationData.add(lblCarsInPayment, gbc_lblCarsInPayment);
		
		// Payment queue length text field
		paymentQueueTf = new JTextField();
		paymentQueueTf.setEditable(false);
		paymentQueueTf.setText("0");
		GridBagConstraints gbc_paymentQueueTf = new GridBagConstraints();
		gbc_paymentQueueTf.insets = new Insets(0, 0, 5, 0);
		gbc_paymentQueueTf.gridx = 3;
		gbc_paymentQueueTf.gridy = 2;
		populationData.add(paymentQueueTf, gbc_paymentQueueTf);
		paymentQueueTf.setColumns(10);
		
		// Exit queue length label
		JLabel lblCarsInExit = new JLabel("Cars in exit queue");
		GridBagConstraints gbc_lblCarsInExit = new GridBagConstraints();
		gbc_lblCarsInExit.anchor = GridBagConstraints.EAST;
		gbc_lblCarsInExit.insets = new Insets(0, 0, 5, 5);
		gbc_lblCarsInExit.gridx = 2;
		gbc_lblCarsInExit.gridy = 3;
		populationData.add(lblCarsInExit, gbc_lblCarsInExit);
		
		// Exit queue length text field
		exitQueueTf = new JTextField();
		exitQueueTf.setText("0");
		exitQueueTf.setEditable(false);
		GridBagConstraints gbc_exitQueueTf = new GridBagConstraints();
		gbc_exitQueueTf.insets = new Insets(0, 0, 5, 0);
		gbc_exitQueueTf.gridx = 3;
		gbc_exitQueueTf.gridy = 3;
		populationData.add(exitQueueTf, gbc_exitQueueTf);
		exitQueueTf.setColumns(10);
		
		
		/*
		 * Balance panel
		 */
		JPanel BalancePanel = new JPanel();
		add(BalancePanel);
		BalancePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel BalanceTitle = new JLabel("Balance overview");
		BalanceTitle.setVerticalAlignment(SwingConstants.TOP);
		BalanceTitle.setHorizontalAlignment(SwingConstants.CENTER);
		BalancePanel.add(BalanceTitle, BorderLayout.NORTH);
		
		JPanel Balance = new JPanel();
		Balance.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		Balance.setBackground(Color.WHITE);
		BalancePanel.add(Balance, BorderLayout.CENTER);
		
		GridBagLayout gbl_balance = new GridBagLayout();
		gbl_balance.columnWidths = new int[] {0, 0, 1};
		gbl_balance.rowHeights = new int[] {0, 0, 0, 0, 0, 1};
		gbl_balance.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_balance.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		Balance.setLayout(gbl_balance);
		
		
		// Total balance label
		JLabel lbltotalBalance = new JLabel("Total balance");
		GridBagConstraints gbc_lbltotalBalance = new GridBagConstraints();
		gbc_lbltotalBalance.insets = new Insets(0, 0, 5, 5);
		gbc_lbltotalBalance.gridx = 0;
		gbc_lbltotalBalance.gridy = 0;
		Balance.add(lbltotalBalance, gbc_lbltotalBalance);
		lbltotalBalance.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// Total balance text field
		totalBalanceTf = new JTextField();
		GridBagConstraints gbc_totalBalanceTf = new GridBagConstraints();
		gbc_totalBalanceTf.insets = new Insets(0, 0, 5, 0);
		gbc_totalBalanceTf.gridx = 1;
		gbc_totalBalanceTf.gridy = 0;
		Balance.add(totalBalanceTf, gbc_totalBalanceTf);
		totalBalanceTf.setText("0");
		totalBalanceTf.setEditable(false);
		totalBalanceTf.setColumns(10);
		
		// Total AdHocs paid label
		JLabel lbladHocPaid = new JLabel("AdHocs served");
		GridBagConstraints gbc_lbladHocPaid = new GridBagConstraints();
		gbc_lbladHocPaid.insets = new Insets(0, 0, 5, 5);
		gbc_lbladHocPaid.gridx = 0;
		gbc_lbladHocPaid.gridy = 1;
		Balance.add(lbladHocPaid, gbc_lbladHocPaid);
		lbladHocPaid.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// Total AdHocs paid text field
		adHocPaidTf = new JTextField();
		GridBagConstraints gbc_adHocPaidTf = new GridBagConstraints();
		gbc_adHocPaidTf.insets = new Insets(0, 0, 5, 0);
		gbc_adHocPaidTf.gridx = 1;
		gbc_adHocPaidTf.gridy = 1;
		Balance.add(adHocPaidTf, gbc_adHocPaidTf);
		adHocPaidTf.setText("0");
		adHocPaidTf.setEditable(false);
		adHocPaidTf.setColumns(10);
		
		// Total AdHocs revenue label
		JLabel lbladHocBalance = new JLabel("AdHocs revenue");
		GridBagConstraints gbc_lbladHocBalance = new GridBagConstraints();
		gbc_lbladHocBalance.insets = new Insets(0, 0, 5, 5);
		gbc_lbladHocBalance.gridx = 0;
		gbc_lbladHocBalance.gridy = 2;
		Balance.add(lbladHocBalance, gbc_lbladHocBalance);
		lbladHocBalance.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// Total AdHocs revenue text field
		adHocBalanceTf = new JTextField();
		GridBagConstraints gbc_adHocBalanceTf = new GridBagConstraints();
		gbc_adHocBalanceTf.insets = new Insets(0, 0, 5, 0);
		gbc_adHocBalanceTf.gridx = 1;
		gbc_adHocBalanceTf.gridy = 2;
		Balance.add(adHocBalanceTf, gbc_adHocBalanceTf);
		adHocBalanceTf.setText("0");
		adHocBalanceTf.setEditable(false);
		adHocBalanceTf.setColumns(10);
		
		//Total Parking pass paid label
		JLabel lblpassPaid = new JLabel("Passholders served");
		GridBagConstraints gbc_lblpassPaid = new GridBagConstraints();
		gbc_lblpassPaid.insets = new Insets(0, 0, 5, 5);
		gbc_lblpassPaid.gridx = 0;
		gbc_lblpassPaid.gridy = 3;
		Balance.add(lblpassPaid, gbc_lblpassPaid);
		lblpassPaid.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//Total Parking pass paid text field
		passPaidTf = new JTextField();
		GridBagConstraints gbc_passPaidTf = new GridBagConstraints();
		gbc_passPaidTf.insets = new Insets(0, 0, 5, 0);
		gbc_passPaidTf.gridx = 1;
		gbc_passPaidTf.gridy = 3;
		Balance.add(passPaidTf, gbc_passPaidTf);
		passPaidTf.setText("0");
		passPaidTf.setEditable(false);
		passPaidTf.setColumns(10);
		
		//Total Parking pass revenue label
		JLabel lblpassBalance = new JLabel("Passholders revenue");
		GridBagConstraints gbc_lblpassBalance = new GridBagConstraints();
		gbc_lblpassBalance.insets = new Insets(0, 0, 5, 5);
		gbc_lblpassBalance.gridx = 0;
		gbc_lblpassBalance.gridy = 4;
		Balance.add(lblpassBalance, gbc_lblpassBalance);
		lblpassBalance.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//Total Parking pass revenue text field
		passBalanceTf = new JTextField();
		GridBagConstraints gbc_passBalanceTf = new GridBagConstraints();
		gbc_passBalanceTf.insets = new Insets(0, 0, 5, 0);
		gbc_passBalanceTf.gridx = 1;
		gbc_passBalanceTf.gridy = 4;
		Balance.add(passBalanceTf, gbc_passBalanceTf);
		passBalanceTf.setText("0");
		passBalanceTf.setEditable(false);
		passBalanceTf.setColumns(10);
		
		//Total Reservation paid label
		JLabel lblreservationPaid = new JLabel("Reservations served");
		GridBagConstraints gbc_lblreservationPaid = new GridBagConstraints();
		gbc_lblreservationPaid.insets = new Insets(0, 0, 5, 5);
		gbc_lblreservationPaid.gridx = 0;
		gbc_lblreservationPaid.gridy = 5;
		Balance.add(lblreservationPaid, gbc_lblreservationPaid);
		lblreservationPaid.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//Total Reservation paid text field
		reservationPaidTf = new JTextField();
		GridBagConstraints gbc_reservationPaidTf = new GridBagConstraints();
		gbc_reservationPaidTf.insets = new Insets(0, 0, 5, 0);
		gbc_reservationPaidTf.gridx = 1;
		gbc_reservationPaidTf.gridy = 5;
		Balance.add(reservationPaidTf, gbc_reservationPaidTf);
		reservationPaidTf.setText("0");
		reservationPaidTf.setEditable(false);
		reservationPaidTf.setColumns(10);
		
		//Total Reservation revenue label
		JLabel lblreservationBalance = new JLabel("Reservations revenue");
		GridBagConstraints gbc_lblreservationBalance = new GridBagConstraints();
		gbc_lblreservationBalance.insets = new Insets(0, 0, 5, 5);
		gbc_lblreservationBalance.gridx = 0;
		gbc_lblreservationBalance.gridy = 6;
		Balance.add(lblreservationBalance, gbc_lblreservationBalance);
		lblreservationBalance.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//Total Reservation revenue text field
		reservationBalanceTf = new JTextField();
		GridBagConstraints gbc_reservationBalanceTf = new GridBagConstraints();
		gbc_reservationBalanceTf.insets = new Insets(0, 0, 5, 0);
		gbc_reservationBalanceTf.gridx = 1;
		gbc_reservationBalanceTf.gridy = 6;
		Balance.add(reservationBalanceTf, gbc_reservationBalanceTf);
		reservationBalanceTf.setText("0");
		reservationBalanceTf.setEditable(false);
		reservationBalanceTf.setColumns(10);
		
		
		
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
		standardEntranceTf.setText("" + totalCarInfo.get("standardEntranceQueue"));
		passEntranceTf.setText("" + totalCarInfo.get("passEntranceQueue"));
		paymentQueueTf.setText("" + totalCarInfo.get("paymentQueue"));
		exitQueueTf.setText("" + totalCarInfo.get("exitQueue"));
		servedCarsTf.setText("" + totalCarInfo.get("served"));
		missedCarsTf.setText("" + totalCarInfo.get("missed"));
		adHocPaidTf.setText("" + totalCarInfo.get("adhoc served"));
		passPaidTf.setText("" + totalCarInfo.get("pass served"));
		reservationPaidTf.setText("" + totalCarInfo.get("reserved served"));
		
		// Balance Overview.
		Bank bank = controller.getBank();
	
		totalBalanceTf.setText(String.format("%.2f", bank.getTotalBalance()));
		adHocBalanceTf.setText(String.format("%.2f", bank.getAdHocBalance()));
		passBalanceTf.setText(String.format("%.2f", bank.getPassBalance()));
		reservationBalanceTf.setText(String.format("%.2f", bank.getReservedBalance()));


		
	} 
}
