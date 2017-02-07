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
        
        int y = totalCarInfo.get("pass") / (540/100);
        int yRot = Math.round(y*3.6f);
        int y2 = totalCarInfo.get("adhoc") / (540/100);
        int y2Rot = Math.round(y2*3.6f);
        int y3 = totalCarInfo.get("reservation") / (540/100);
        int y3Rot = Math.round(y3*3.6f);
        int empty = 100 - y - y2 - y3;
        
        Graphics g = pieChartImage.getGraphics();
        g.setColor(getBackground());
        g.fillRect(0, 0, width, height);
        g.setColor(new Color(230,230,230));
        g.fillArc((width/3)-(circleSize/2), (height/2)-(circleSize/2), circleSize, circleSize, 0, 360);
        g.fillRect(width - 120, height - 40, 10, 10);
        
        g.setColor(Color.BLUE);
        g.fillArc((width/3)-(circleSize/2), (height/2)-(circleSize/2), circleSize, circleSize, 90, -yRot);
        g.fillRect(width - 120, height - 100, 10, 10);
        
        g.setColor(Color.RED);
        g.fillArc((width/3)-(circleSize/2), (height/2)-(circleSize/2), circleSize, circleSize, -yRot+90, -y2Rot);
        g.fillRect(width - 120, height - 80, 10, 10);        
        
        g.setColor(Color.GREEN);
        g.fillArc((width/3)-(circleSize/2), (height/2)-(circleSize/2), circleSize, circleSize, -(yRot+y2Rot)+90, -y3Rot);
        g.fillRect(width - 120, height - 60, 10, 10);
        
        g.setColor(Color.BLACK);
        g.drawString("Pass: " + y + "%", width - 100, height - 100 + (5 + (g.getFont().getSize() / 2)));
        g.drawString("Adhoc: " + y2 + "%", width - 100, height - 80 + (5 + (g.getFont().getSize() / 2)));
        g.drawString("Reserved: " + y3 + "%", width - 100, height - 60 + (5 + (g.getFont().getSize() / 2)));
        g.drawString("Empty spots: " + empty + "%", width - 100, height - 40 + (5 + (g.getFont().getSize() / 2)));
        repaint();
	}
	
	
	/**
	 * Set the default size for this JPanel.
	 */
    public Dimension getPreferredSize() {
        return new Dimension(300, 200);
    }  
}