package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import controller.SimulatorController;
import model.AdHocCar;
import model.ParkingPassCar;
import model.PassPlaceHolder;
import model.ReservationCar;

@SuppressWarnings("serial")
public class PieChartView extends View {

	private Dimension size;
	private BufferedImage pieChartImage;
	private SimulatorController controller;
	
	private String[] cars;
	private Color[] colors;
	private String[] legendaNames;
	
	public PieChartView(SimulatorController controller) {
		size = new Dimension(0, 0);
		this.controller = controller;
		
        cars = new String[]{"adhoc", "placeholders", "pass", "reservation", "free"};
        colors = new Color[] {AdHocCar.COLOR, PassPlaceHolder.COLOR, ParkingPassCar.COLOR, ReservationCar.COLOR, new Color(230,230,230)};
        legendaNames = new String[] {"Adhoc", "ParkingPass spots", "ParkingPass", "Reservation", "Empty spot"};
	}
	
	/**
	 * The starting method for the Graphics
	 */
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (pieChartImage == null) {
            return;
        }
        
        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
        	g.drawImage(pieChartImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
        	g.drawImage(pieChartImage, 0, 0, currentSize.width, currentSize.height, null);
        }        
    }
	
	@Override
	public void updateView() {
        // Create a new car park image if the size has changed.
        if (!size.equals(getSize())) {
            size = getSize();
            pieChartImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        }
        
        // Get total car info
        HashMap<String, Integer> totalCarInfo = controller.getTotalCarInfo();
        
        // Local variables
        int width = pieChartImage.getWidth();
        int height = pieChartImage.getHeight();
        int circleSize = (width + height) / 4;
        
        // Get the graphics from the image to draw on
        Graphics g = pieChartImage.getGraphics();
        
        // Draw rect to refresh
        g.setColor(getBackground());
        g.fillRect(0, 0, width, height);
        
        int startPoint = 90;
        for(int i = 0; i < cars.length; i++) {        	
        	float data = (float)totalCarInfo.get(cars[i]) / (controller.getTotalNumberOfPlaces()/100f);
        	int rot = Math.round(data*3.6f);
        	
        	// Draw arc
        	g.setColor(colors[i]);
        	g.fillArc((width/3)-(circleSize/2), (height/2)-(circleSize/2), circleSize, circleSize, startPoint, -rot);
        	startPoint -= rot;
        	
        	// Draw legenda item
        	g.fillRect(width - 160, height - (cars.length*20) + (i*20), 10, 10);
        	g.setColor(Color.BLACK);
        	g.drawString(legendaNames[i] + ": " + Math.round(data) + "%", width - 140, height - cars.length*20 + i*20 + (5 + (g.getFont().getSize() / 2)));
        }
        
        // Repaint
        repaint();
	}
	
	
	/**
	 * Set the default size for this JPanel.
	 */
    public Dimension getPreferredSize() {
        return new Dimension(300, 200);
    }  
}