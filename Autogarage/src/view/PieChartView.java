package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import controller.SimulatorController;

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
        
        HashMap<String, Integer> totalCarInfo = controller.getTotalCarInfo();
        int width = pieChartImage.getWidth();
        int height = pieChartImage.getHeight();
        int circleSize = (width + height) / 4;
        
        int y = totalCarInfo.get("adhoc") / (540/100);
        int yRot = (360/100) * y;
        int y2 = totalCarInfo.get("card") / (540/100);
        int y2Rot = (360/100) * y2;
        int empty = 100 - y - y2;
        
        Graphics g = pieChartImage.getGraphics();
        g.setColor(getBackground());
        g.fillRect(0, 0, width, height);
        g.setColor(new Color(250,250,250));
        g.fillArc((width/2)-(circleSize/2), (height/2)-(circleSize/2), circleSize, circleSize, 0, 360);
        g.fillRect(width - 120, height - 60, 10, 10);
        
        g.setColor(Color.BLUE);
        g.fillArc((width/2)-(circleSize/2), (height/2)-(circleSize/2), circleSize, circleSize, 90, yRot);
        g.fillRect(width - 120, height - 100, 10, 10);
        
        g.setColor(Color.RED);
        g.fillArc((width/2)-(circleSize/2), (height/2)-(circleSize/2), circleSize, circleSize, yRot+90, y2Rot);
        g.fillRect(width - 120, height - 80, 10, 10);
        
        g.setColor(Color.BLACK);
        g.drawString("adhoc: " + y + "%", width - 100, height - 100 + (5 + (g.getFont().getSize() / 2)));
        g.drawString("card: " + y2 + "%", width - 100, height - 80 + (5 + (g.getFont().getSize() / 2)));
        g.drawString("empty spots: " + empty + "%", width - 100, height - 60 + (5 + (g.getFont().getSize() / 2)));
        repaint();
	}
	
	
	/**
	 * Set the default size for this JPanel.
	 */
    public Dimension getPreferredSize() {
        return new Dimension(800, 400);
    }  
}