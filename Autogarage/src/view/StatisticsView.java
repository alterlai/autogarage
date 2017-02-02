package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;

import controller.*;

public class StatisticsView extends View{
	private SimulatorController controller;
	private populationChart
	
	private JLabel totalCarsLabel;
	
	/**
	 * Constructor
	 * @param controller controlling the view.
	 */
	public StatisticsView(SimulatorController controller)
	{
		this.controller = controller;
		this.setLayout(new GridLayout(3, 2));
		JPanel carPopulation = new JPanel();
		add(carPopulation);
		
		totalCarsLabel = new JLabel("Total amount of cars: " + 0);
		this.add(totalCarsLabel);
	}
	
	/**
	 * Updates the contents of the JFrame.
	 */
	@Override
	public void updateView() {
		HashMap<String, Integer> totalCarInfo = controller.getTotalCarInfo();
		totalCarsLabel.setText("Total amount of cars: " + totalCarInfo.get("all"));
	}
	
	public class PieChartSample extends Application {
		 
	    @Override public void start(Stage stage) {
	        Scene scene = new Scene(new Group());
	        stage.setTitle("Imported Fruits");
	        stage.setWidth(500);
	        stage.setHeight(500);
	 
	        ObservableList<PieChart.Data> pieChartData =
	                FXCollections.observableArrayList(
	                new PieChart.Data("Grapefruit", 13),
	                new PieChart.Data("Oranges", 25),
	                new PieChart.Data("Plums", 10),
	                new PieChart.Data("Pears", 22),
	                new PieChart.Data("Apples", 30));
	        final PieChart chart = new PieChart(pieChartData);
	        chart.setTitle("Imported Fruits");

	        ((Group) scene.getRoot()).getChildren().add(chart);
	        stage.setScene(scene);
	        stage.show();
	    }
	 
	    public static void main(String[] args) {
	        launch(args);
	    }
	}
}
