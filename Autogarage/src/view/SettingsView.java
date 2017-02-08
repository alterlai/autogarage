package view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.SimulatorController;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class SettingsView extends View{
	private SimulatorController controller;
	private JTextField simTickPauseTF;
	private JTextField weekDayArrivalsField;
	private JTextField weekendArrivalsField;
	private JTextField weekdayPassField;
	private JTextField weekendPassField;
	private JTextField weekdayReservedField;
	private JTextField weekendReservedField;
	private JTextField enterSpeedField;
	private JTextField paymentSpeedField;
	private JTextField exitSpeedField;
	private JTextField numberOfFloorsField;
	private JTextField numberOfRowsField;
	private JTextField numberOfPlacesField;
	
	public SettingsView(SimulatorController controller)
	{
		this.controller = controller;
		setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(10, 10));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBackground(Color.WHITE);
		panel_2.add(panel_4, BorderLayout.WEST);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{111, 86, 0};
		gbl_panel_4.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel delayLabel = new JLabel("Tick delay");
		GridBagConstraints gbc_delayLabel = new GridBagConstraints();
		gbc_delayLabel.anchor = GridBagConstraints.WEST;
		gbc_delayLabel.insets = new Insets(0, 0, 5, 5);
		gbc_delayLabel.gridx = 0;
		gbc_delayLabel.gridy = 0;
		panel_4.add(delayLabel, gbc_delayLabel);
		delayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		delayLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		simTickPauseTF = new JTextField("100");
		GridBagConstraints gbc_simTickPauseTF = new GridBagConstraints();
		gbc_simTickPauseTF.insets = new Insets(0, 0, 5, 0);
		gbc_simTickPauseTF.anchor = GridBagConstraints.NORTHWEST;
		gbc_simTickPauseTF.gridx = 1;
		gbc_simTickPauseTF.gridy = 0;
		panel_4.add(simTickPauseTF, gbc_simTickPauseTF);
		simTickPauseTF.setColumns(10);
		
		JLabel weekDayArrivalsLabel = new JLabel("Average number of cars arriving each hour");
		GridBagConstraints gbc_weekDayArrivalsLabel = new GridBagConstraints();
		gbc_weekDayArrivalsLabel.insets = new Insets(0, 0, 5, 0);
		gbc_weekDayArrivalsLabel.gridwidth = 2;
		gbc_weekDayArrivalsLabel.gridx = 0;
		gbc_weekDayArrivalsLabel.gridy = 1;
		panel_4.add(weekDayArrivalsLabel, gbc_weekDayArrivalsLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 2;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		panel_4.add(separator, gbc_separator);
		
		JLabel lblWeekday = new JLabel("Weekday ad hoc");
		GridBagConstraints gbc_lblWeekday = new GridBagConstraints();
		gbc_lblWeekday.anchor = GridBagConstraints.EAST;
		gbc_lblWeekday.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeekday.gridx = 0;
		gbc_lblWeekday.gridy = 3;
		panel_4.add(lblWeekday, gbc_lblWeekday);
		
		weekDayArrivalsField = new JTextField();
		weekDayArrivalsField.setText("100");
		GridBagConstraints gbc_weekDayArrivalsField = new GridBagConstraints();
		gbc_weekDayArrivalsField.insets = new Insets(0, 0, 5, 0);
		gbc_weekDayArrivalsField.fill = GridBagConstraints.HORIZONTAL;
		gbc_weekDayArrivalsField.gridx = 1;
		gbc_weekDayArrivalsField.gridy = 3;
		panel_4.add(weekDayArrivalsField, gbc_weekDayArrivalsField);
		weekDayArrivalsField.setColumns(10);
		
		JLabel lblWeekend = new JLabel("Weekend ad hoc");
		GridBagConstraints gbc_lblWeekend = new GridBagConstraints();
		gbc_lblWeekend.anchor = GridBagConstraints.EAST;
		gbc_lblWeekend.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeekend.gridx = 0;
		gbc_lblWeekend.gridy = 4;
		panel_4.add(lblWeekend, gbc_lblWeekend);
		
		weekendArrivalsField = new JTextField();
		weekendArrivalsField.setText("200");
		GridBagConstraints gbc_weekendArrivalsField = new GridBagConstraints();
		gbc_weekendArrivalsField.insets = new Insets(0, 0, 5, 0);
		gbc_weekendArrivalsField.fill = GridBagConstraints.HORIZONTAL;
		gbc_weekendArrivalsField.gridx = 1;
		gbc_weekendArrivalsField.gridy = 4;
		panel_4.add(weekendArrivalsField, gbc_weekendArrivalsField);
		weekendArrivalsField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Weekday passholders");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 5;
		panel_4.add(lblNewLabel, gbc_lblNewLabel);
		
		weekdayPassField = new JTextField();
		weekdayPassField.setText("50");
		GridBagConstraints gbc_weekdayPassField = new GridBagConstraints();
		gbc_weekdayPassField.insets = new Insets(0, 0, 5, 0);
		gbc_weekdayPassField.fill = GridBagConstraints.HORIZONTAL;
		gbc_weekdayPassField.gridx = 1;
		gbc_weekdayPassField.gridy = 5;
		panel_4.add(weekdayPassField, gbc_weekdayPassField);
		weekdayPassField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Weekend passholders");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 6;
		panel_4.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		weekendPassField = new JTextField();
		weekendPassField.setText("5");
		GridBagConstraints gbc_weekendPassField = new GridBagConstraints();
		gbc_weekendPassField.insets = new Insets(0, 0, 5, 0);
		gbc_weekendPassField.fill = GridBagConstraints.HORIZONTAL;
		gbc_weekendPassField.gridx = 1;
		gbc_weekendPassField.gridy = 6;
		panel_4.add(weekendPassField, gbc_weekendPassField);
		weekendPassField.setColumns(10);
		
		JLabel lblWeekdayReservations = new JLabel("Weekday reservations");
		GridBagConstraints gbc_lblWeekdayReservations = new GridBagConstraints();
		gbc_lblWeekdayReservations.anchor = GridBagConstraints.EAST;
		gbc_lblWeekdayReservations.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeekdayReservations.gridx = 0;
		gbc_lblWeekdayReservations.gridy = 7;
		panel_4.add(lblWeekdayReservations, gbc_lblWeekdayReservations);
		
		weekdayReservedField = new JTextField();
		weekdayReservedField.setText("50");
		GridBagConstraints gbc_weekdayReservedField = new GridBagConstraints();
		gbc_weekdayReservedField.insets = new Insets(0, 0, 5, 0);
		gbc_weekdayReservedField.fill = GridBagConstraints.HORIZONTAL;
		gbc_weekdayReservedField.gridx = 1;
		gbc_weekdayReservedField.gridy = 7;
		panel_4.add(weekdayReservedField, gbc_weekdayReservedField);
		weekdayReservedField.setColumns(10);
		
		JLabel lblWeekendReservations = new JLabel("Weekend reservations");
		GridBagConstraints gbc_lblWeekendReservations = new GridBagConstraints();
		gbc_lblWeekendReservations.anchor = GridBagConstraints.EAST;
		gbc_lblWeekendReservations.insets = new Insets(0, 0, 0, 5);
		gbc_lblWeekendReservations.gridx = 0;
		gbc_lblWeekendReservations.gridy = 8;
		panel_4.add(lblWeekendReservations, gbc_lblWeekendReservations);
		
		weekendReservedField = new JTextField();
		weekendReservedField.setText("100");
		GridBagConstraints gbc_weekendReservedField = new GridBagConstraints();
		gbc_weekendReservedField.fill = GridBagConstraints.HORIZONTAL;
		gbc_weekendReservedField.gridx = 1;
		gbc_weekendReservedField.gridy = 8;
		panel_4.add(weekendReservedField, gbc_weekendReservedField);
		weekendReservedField.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBackground(Color.WHITE);
		panel_2.add(panel_5, BorderLayout.CENTER);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{111, 86, 0};
		gbl_panel_5.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JLabel lblNewLabel_2 = new JLabel("Amount of cars that can:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		panel_5.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 2;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 1;
		panel_5.add(separator_1, gbc_separator_1);
		
		JLabel lblEnteringCarsPer = new JLabel("Enter per minute");
		GridBagConstraints gbc_lblEnteringCarsPer = new GridBagConstraints();
		gbc_lblEnteringCarsPer.anchor = GridBagConstraints.EAST;
		gbc_lblEnteringCarsPer.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnteringCarsPer.gridx = 0;
		gbc_lblEnteringCarsPer.gridy = 2;
		panel_5.add(lblEnteringCarsPer, gbc_lblEnteringCarsPer);
		
		enterSpeedField = new JTextField();
		enterSpeedField.setText("8");
		GridBagConstraints gbc_enterSpeedField = new GridBagConstraints();
		gbc_enterSpeedField.anchor = GridBagConstraints.WEST;
		gbc_enterSpeedField.insets = new Insets(0, 0, 5, 0);
		gbc_enterSpeedField.gridx = 1;
		gbc_enterSpeedField.gridy = 2;
		panel_5.add(enterSpeedField, gbc_enterSpeedField);
		enterSpeedField.setColumns(10);
		
		JLabel lblAmountOfCars = new JLabel("Pay per minute");
		GridBagConstraints gbc_lblAmountOfCars = new GridBagConstraints();
		gbc_lblAmountOfCars.anchor = GridBagConstraints.EAST;
		gbc_lblAmountOfCars.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmountOfCars.gridx = 0;
		gbc_lblAmountOfCars.gridy = 3;
		panel_5.add(lblAmountOfCars, gbc_lblAmountOfCars);
		
		paymentSpeedField = new JTextField();
		paymentSpeedField.setText("5");
		GridBagConstraints gbc_paymentSpeedField = new GridBagConstraints();
		gbc_paymentSpeedField.anchor = GridBagConstraints.WEST;
		gbc_paymentSpeedField.insets = new Insets(0, 0, 5, 0);
		gbc_paymentSpeedField.gridx = 1;
		gbc_paymentSpeedField.gridy = 3;
		panel_5.add(paymentSpeedField, gbc_paymentSpeedField);
		paymentSpeedField.setColumns(10);
		
		JLabel lblExitPerMinute = new JLabel("Exit per minute");
		GridBagConstraints gbc_lblExitPerMinute = new GridBagConstraints();
		gbc_lblExitPerMinute.anchor = GridBagConstraints.EAST;
		gbc_lblExitPerMinute.insets = new Insets(0, 0, 5, 5);
		gbc_lblExitPerMinute.gridx = 0;
		gbc_lblExitPerMinute.gridy = 4;
		panel_5.add(lblExitPerMinute, gbc_lblExitPerMinute);
		
		exitSpeedField = new JTextField();
		exitSpeedField.setText("5");
		GridBagConstraints gbc_exitSpeedField = new GridBagConstraints();
		gbc_exitSpeedField.anchor = GridBagConstraints.WEST;
		gbc_exitSpeedField.insets = new Insets(0, 0, 5, 0);
		gbc_exitSpeedField.gridx = 1;
		gbc_exitSpeedField.gridy = 4;
		panel_5.add(exitSpeedField, gbc_exitSpeedField);
		exitSpeedField.setColumns(10);
		
		JLabel lblGarageSettings = new JLabel("Garage settings");
		GridBagConstraints gbc_lblGarageSettings = new GridBagConstraints();
		gbc_lblGarageSettings.gridwidth = 2;
		gbc_lblGarageSettings.insets = new Insets(0, 0, 5, 0);
		gbc_lblGarageSettings.gridx = 0;
		gbc_lblGarageSettings.gridy = 6;
		panel_5.add(lblGarageSettings, gbc_lblGarageSettings);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.gridwidth = 2;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 7;
		panel_5.add(separator_2, gbc_separator_2);
		
		JLabel lblNumberOfFloors = new JLabel("Number of floors");
		GridBagConstraints gbc_lblNumberOfFloors = new GridBagConstraints();
		gbc_lblNumberOfFloors.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfFloors.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfFloors.gridx = 0;
		gbc_lblNumberOfFloors.gridy = 8;
		panel_5.add(lblNumberOfFloors, gbc_lblNumberOfFloors);
		
		numberOfFloorsField = new JTextField();
		numberOfFloorsField.setText("3");
		GridBagConstraints gbc_numberOfFloorsField = new GridBagConstraints();
		gbc_numberOfFloorsField.anchor = GridBagConstraints.WEST;
		gbc_numberOfFloorsField.insets = new Insets(0, 0, 5, 0);
		gbc_numberOfFloorsField.gridx = 1;
		gbc_numberOfFloorsField.gridy = 8;
		panel_5.add(numberOfFloorsField, gbc_numberOfFloorsField);
		numberOfFloorsField.setColumns(10);
		
		JLabel lblNumberOfRows = new JLabel("Number of Rows");
		GridBagConstraints gbc_lblNumberOfRows = new GridBagConstraints();
		gbc_lblNumberOfRows.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfRows.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfRows.gridx = 0;
		gbc_lblNumberOfRows.gridy = 9;
		panel_5.add(lblNumberOfRows, gbc_lblNumberOfRows);
		
		numberOfRowsField = new JTextField();
		numberOfRowsField.setText("6");
		GridBagConstraints gbc_numberOfRowsField = new GridBagConstraints();
		gbc_numberOfRowsField.anchor = GridBagConstraints.WEST;
		gbc_numberOfRowsField.insets = new Insets(0, 0, 5, 0);
		gbc_numberOfRowsField.gridx = 1;
		gbc_numberOfRowsField.gridy = 9;
		panel_5.add(numberOfRowsField, gbc_numberOfRowsField);
		numberOfRowsField.setColumns(10);
		
		JLabel lblNumberOfPlaces = new JLabel("Number of Places");
		GridBagConstraints gbc_lblNumberOfPlaces = new GridBagConstraints();
		gbc_lblNumberOfPlaces.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfPlaces.insets = new Insets(0, 0, 0, 5);
		gbc_lblNumberOfPlaces.gridx = 0;
		gbc_lblNumberOfPlaces.gridy = 10;
		panel_5.add(lblNumberOfPlaces, gbc_lblNumberOfPlaces);
		
		numberOfPlacesField = new JTextField();
		numberOfPlacesField.setText("30");
		GridBagConstraints gbc_numberOfPlacesField = new GridBagConstraints();
		gbc_numberOfPlacesField.anchor = GridBagConstraints.WEST;
		gbc_numberOfPlacesField.gridx = 1;
		gbc_numberOfPlacesField.gridy = 10;
		panel_5.add(numberOfPlacesField, gbc_numberOfPlacesField);
		numberOfPlacesField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);
		
		JButton btnApply = new JButton("Apply");
		panel_3.add(btnApply);
		
		JButton btnResetDefaults = new JButton("Reset defaults");
		btnResetDefaults.addActionListener(e -> resetValues());
		panel_3.add(btnResetDefaults);
		btnApply.addActionListener(e -> applySettings());
	}
	
	@Override
	public void updateView() {
		// Nothing to update.
		
	}
	
	/**
	 * Get the value that's put in the input field as an integer.
	 * @return the value as an integer
	 */
	private int getFieldInt(JTextField field)
	{
		try{
    		return Integer.parseInt(field.getText());
    	}
    	catch (NumberFormatException e) {
    		showError("Enter a valid number"); 
    		return 0;
    	}
	}
	
	public void applySettings()
	{
		controller.setTickPause(getFieldInt(simTickPauseTF));
		controller.setWeekDayArrivals(getFieldInt(weekDayArrivalsField));
		controller.setWeekendArrivals(getFieldInt(weekDayArrivalsField));
		controller.setWeekDayPassArrivals(getFieldInt(weekdayPassField));
		controller.setWeekendPassArrivals(getFieldInt(weekendPassField));
		controller.setWeekDayReservedArrivals(getFieldInt(weekdayReservedField));
		controller.setWeekendReservedArrivals(getFieldInt(weekendReservedField));
		// TODO: MAKE TICKET COST SETTING.
		
		JOptionPane.showMessageDialog(this, "Settings have been applied", "Settings applied", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void resetValues()
	{
		simTickPauseTF.setText("100");
		weekDayArrivalsField.setText("100");
		weekendArrivalsField.setText("200");
		weekdayPassField.setText("50");
		weekendPassField.setText("5");
		weekdayReservedField.setText("50");
		weekendReservedField.setText("100");
		enterSpeedField.setText("8");
		paymentSpeedField.setText("5");
		exitSpeedField.setText("5");
		numberOfFloorsField.setText("3");
		numberOfRowsField.setText("6");
		numberOfPlacesField.setText("30");
	}
}
