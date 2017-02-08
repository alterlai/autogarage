package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import controller.SimulatorController;
import model.AdHocCar;
import model.ParkingPassCar;
import model.ReservationCar;

@SuppressWarnings("serial")
public class PieChartView extends View {

	private Dimension size;
	private BufferedImage pieChartImage;
	private SimulatorController controller;
	
	public PieChartView(SimulatorController controller) {
		size = new Dimension(0, 0);
		this.controller = controller;
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
        
        // Calculations
        float y = totalCarInfo.get("pass") / (controller.getTotalNumberOfPlaces()/100f);
        int yRot = Math.round(y*3.6f);
        float y2 = totalCarInfo.get("adhoc") / (controller.getTotalNumberOfPlaces()/100f);
        int y2Rot = Math.round(y2*3.6f);
        float y3 = totalCarInfo.get("reservation") / (controller.getTotalNumberOfPlaces()/100f);
        int y3Rot = Math.round(y3*3.6f);
        float empty = 100 - y - y2 - y3;
        
        // Get the graphics from the image to draw on
        Graphics g = pieChartImage.getGraphics();
        
        // Draw rect to refresh
        g.setColor(getBackground());
        g.fillRect(0, 0, width, height);
        
        // Draw Arcs
        g.setColor(new Color(230,230,230));
        g.fillArc((width/3)-(circleSize/2), (height/2)-(circleSize/2), circleSize, circleSize, 0, 360);							// Draw Arc
        g.fillRect(width - 140, height - 40, 10, 10);																			// Draw rect for legenda
        
        g.setColor(ParkingPassCar.COLOR);
        g.fillArc((width/3)-(circleSize/2), (height/2)-(circleSize/2), circleSize, circleSize, 90, -yRot);						// Draw Arc
        g.fillRect(width - 140, height - 100, 10, 10);																			// Draw rect for legenda
        
        g.setColor(AdHocCar.COLOR);
        g.fillArc((width/3)-(circleSize/2), (height/2)-(circleSize/2), circleSize, circleSize, -yRot+90, -y2Rot);				// Draw Arc
        g.fillRect(width - 140, height - 80, 10, 10);																			// Draw rect for legenda
        
        g.setColor(ReservationCar.COLOR);
        g.fillArc((width/3)-(circleSize/2), (height/2)-(circleSize/2), circleSize, circleSize, -(yRot+y2Rot)+90, -y3Rot);		// Draw Arc
        g.fillRect(width - 140, height - 60, 10, 10);																			// Draw rect for legenda
        
        // Draw the names of the legenda
        g.setColor(Color.BLACK);
        g.drawString("Pass: " + Math.round(y) + "%", width - 120, height - 100 + (5 + (g.getFont().getSize() / 2)));
        g.drawString("Adhoc: " + Math.round(y2) + "%", width - 120, height - 80 + (5 + (g.getFont().getSize() / 2)));
        g.drawString("Reserved: " + Math.round(y3) + "%", width - 120, height - 60 + (5 + (g.getFont().getSize() / 2)));
        g.drawString("Empty spots: " + Math.round(empty) + "%", width - 120, height - 40 + (5 + (g.getFont().getSize() / 2)));
        
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