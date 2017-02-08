package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import controller.SimulatorController;
import model.AdHocCar;
import model.ParkingPassCar;
import model.ReservationCar;

@SuppressWarnings("serial")
public class GraphView extends View {
	
	private Dimension size;
	private BufferedImage graphImage;
	private SimulatorController controller;
	
	private int lastVal;
	private int lastVal2;
	private int lastVal3;
	
	public GraphView(SimulatorController controller) {
		size = new Dimension(0, 0);
		this.controller = controller;
	}
	
	/**
	 * The starting method for the Graphics
	 */
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (graphImage == null) {
            return;
        }
        
        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
        	g.drawImage(graphImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
        	g.drawImage(graphImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }
	
	@Override
	public void updateView() {
        // Create a new car park image if the size has changed.
        if (!size.equals(getSize())) {
            size = getSize();
            graphImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        }
        
		HashMap<String, Integer> totalCarInfo = controller.getTotalCarInfo();
		int width = graphImage.getWidth();
		int height = graphImage.getHeight();
		
		int nPlaces = controller.getNumberOfPlaces() * controller.getNumberOfRows() * controller.getNumberOfFloors();
		
		int passVal = (height/100) * ( totalCarInfo.get("pass") / (nPlaces/100) );
		int adhocVal = (height/100) * ( totalCarInfo.get("adhoc") / (nPlaces/100) );
		int reservationVal = (height/100) * ( totalCarInfo.get("reservation") / (nPlaces/100) );
		
		// Graphics2D to use strokes for bigger lines
        Graphics2D g = (Graphics2D)graphImage.getGraphics();
        
        // Copy the graph to the left
        g.copyArea(30, 0, width-30, height, -30, 0);
        
        // Draw rect to refresh
        g.setColor(getBackground());
        g.fillRect(width-30, 0, width, height);
        
        // Draw the graph lines
        g.setColor(ParkingPassCar.COLOR);
        g.setStroke(new BasicStroke(4));
        g.draw(new Line2D.Float(width-30, height - lastVal, width, height - passVal));
        
        g.setColor(AdHocCar.COLOR);
        g.setStroke(new BasicStroke(4));
        g.draw(new Line2D.Float(width-30, height - lastVal2, width, height - adhocVal));
        
        g.setColor(ReservationCar.COLOR);
        g.setStroke(new BasicStroke(4));
        g.draw(new Line2D.Float(width-30, height - lastVal3, width, height - reservationVal));
        
        // Change the previous values
        lastVal = passVal;
        lastVal2 = adhocVal;
        lastVal3 = reservationVal;
        
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
