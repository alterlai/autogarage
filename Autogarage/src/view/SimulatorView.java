package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import controller.SimulatorController;
import model.*;

@SuppressWarnings("serial")
public class SimulatorView extends View {
	
	private Dimension size;
	private BufferedImage carParkImage;
	private SimulatorController controller;
	
	private String[] legendaNames;
	private Color[] legendaColors;	
	
	/**
	 * Set size to 0
	 * set controller object
	 * @param controller
	 */
	public SimulatorView(SimulatorController controller) {
		size = new Dimension(0, 0);
		this.controller = controller;
		
		legendaNames = new String[] {"Adhoc", "ParkingPass spots", "ParkingPass", "Reservation", "Empty spot"};
		legendaColors = new Color[] {AdHocCar.COLOR, PassPlaceHolder.COLOR, ParkingPassCar.COLOR, ReservationCar.COLOR, Color.WHITE};
	}
	
	
	/**
	 * The starting method for the Graphics
	 */
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (carParkImage == null) {
            return;
        }
        
        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
        	g.drawImage(carParkImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
        	g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }


	/**
	 * Updating the view.
	 * Go trough all floors, rows and places. And call drawPlace() with the proper color.
	 */
	public void updateView() {
        // Create a new car park image if the size has changed.
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        } 
		
		Graphics2D g = carParkImage.createGraphics();
        for(int floor = 0; floor < controller.getNumberOfFloors(); floor++) {
            for(int row = 0; row < controller.getNumberOfRows(); row++) {
                for(int place = 0; place < controller.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = controller.getCarAt(location);
                    Color color = car == null ? Color.white : car.getColor();
                    drawPlace(g, location, color);
                }
            }
        }
		repaint();
	}   
    
    
    /**
     * Paint a place on this car park view in a given color.
     */
    private void drawPlace(Graphics g, Location location, Color color) {
		int width = carParkImage.getWidth();
		int height = carParkImage.getHeight();
		
		// Scaledown the simulator part to make space for the legenda
		int simWidth = (int)(width * 0.8);
		int simHeight = (int)(height * 1);
        
		g.setColor(color);
		
		// Calculate where the place will be
    	int floorPosX = ((simWidth/controller.getNumberOfFloors())/4) + location.getFloor() * (simWidth/controller.getNumberOfFloors());
    	int floorWidth = (simWidth/controller.getNumberOfFloors()) - ((simWidth/controller.getNumberOfFloors())/2);
    	
						// Center										// Path size (big space)																	// Space between rows
		int rowPosX = (floorWidth/(controller.getNumberOfRows())/2) + (int)Math.floor(location.getRow() * 0.5) * (floorWidth/(controller.getNumberOfRows())*2) + (location.getRow() % 2) * ((floorWidth/controller.getNumberOfRows())/2);
		int rowWidth = (floorWidth/controller.getNumberOfRows())/2;
		
		int placeY = (simHeight/controller.getNumberOfPlaces()/2) + location.getPlace() * (simHeight/controller.getNumberOfPlaces());
		int placeWidth = rowWidth - (int)(rowWidth * 0.2);
		int placeHeight = (int)((simHeight/controller.getNumberOfPlaces())*0.6);
		
		// Draw the place
		g.fillRect(floorPosX + rowPosX, placeY, placeWidth, placeHeight);
		
		
		// Draw the legenda
		for(int i = 0; i < legendaNames.length; i++) {
			g.setColor(legendaColors[i]);
			g.fillRect(width - (width/6), (height/20) + (i * (height/20)), (width/80), (height/40));
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, (width+height)/100));
			g.drawString(legendaNames[i], width - (width/8), (height/20) + (i * (height/20)) + ((height/160) + (g.getFont().getSize() / 2)));
		}
    }
    
	/**
	 * Set the default size for this JPanel.
	 */
    public Dimension getPreferredSize() {
        return new Dimension(800, 400);
    } 
    
}
