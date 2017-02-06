package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import controller.SimulatorController;

@SuppressWarnings("serial")
public class GraphView extends View {
	
	private Dimension size;
	private BufferedImage graphImage;
	private SimulatorController controller;
	
	private int lastVal;
	private int lastVal2;
	
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
		
		int y = (height/100) * ( totalCarInfo.get("pass") / (540/100) );
		int y2 = (height/100) * ( totalCarInfo.get("adhoc") / (540/100) );
		
        Graphics g = graphImage.getGraphics();
        g.copyArea(30, 0, width-30, height, -30, 0);
        g.setColor(getBackground());
        g.fillRect(width-30, 0, width, height);
        g.setColor(Color.BLUE);
        g.drawLine(width-30, height - lastVal, width, height - y);
        g.setColor(Color.RED);
        g.drawLine(width-30, height - lastVal2, width, height - y2);
        lastVal = y;
        lastVal2 = y2;
        repaint();
	}
	
	
	/**
	 * Set the default size for this JPanel.
	 */
    public Dimension getPreferredSize() {
        return new Dimension(300, 200);
    }   
	
}
