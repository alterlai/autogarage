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
import model.PassPlaceHolder;
import model.ReservationCar;

@SuppressWarnings("serial")
public class GraphView extends View {
	
	private Dimension size;
	private BufferedImage graphImage;
	private SimulatorController controller;
	
	private float[] lastVals;
	private String[] cars;
	private Color[] colors;
	
	public GraphView(SimulatorController controller) {
		size = new Dimension(0, 0);
		this.controller = controller;
		
        cars = new String[]{"adhoc", "placeholders", "pass", "reservation"};
        colors = new Color[]{AdHocCar.COLOR, PassPlaceHolder.COLOR, ParkingPassCar.COLOR, ReservationCar.COLOR};
        lastVals = new float[cars.length];
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
		
		// Graphics2D to use strokes for bigger lines
        Graphics2D g = (Graphics2D)graphImage.getGraphics();       
        
        // Copy the graph to the left
        g.copyArea(30, 0, width-30, height, -30, 0);
        
        // Draw rect to refresh
        g.setColor(getBackground());
        g.fillRect(width-30, 0, width, height);
        
        for(int i = 0; i < cars.length; i++) {
        	float data = (height/100) * ((float)totalCarInfo.get(cars[i]) / (controller.getTotalNumberOfPlaces()/100f));
            g.setColor(colors[i]);
            g.setStroke(new BasicStroke(4));
            g.draw(new Line2D.Float(width-30, height - lastVals[i], width, height - data));
            lastVals[i] = data;
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
