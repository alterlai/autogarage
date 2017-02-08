package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import controller.SimulatorController;
import model.Car;
import model.Location;

@SuppressWarnings("serial")
public class SimulatorView extends View {
	
	private Dimension size;
	private BufferedImage carParkImage;
	private SimulatorController controller;
	
	
	/**
	 * Set size to 0
	 * set controller object
	 * @param controller
	 */
	public SimulatorView(SimulatorController controller) {
		size = new Dimension(0, 0);
		this.controller = controller;
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
		int totalSpots = controller.getNumberOfFloors() * controller.getNumberOfRows() * controller.getNumberOfPlaces(); // total number of spots.
		
		Graphics2D g = carParkImage.createGraphics();
        for(int floor = 0; floor < controller.getNumberOfFloors(); floor++) {
            for(int row = 0; row < controller.getNumberOfRows(); row++) {
                for(int place = 0; place < controller.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = controller.getCarAt(location);
                    
                    Color color; 	// Location of the spot
                    
                    if (car == null) {
                    	if (location.isPassSpot(controller.getNumberOfPassHolders(), controller.getNumberOfFloors(), controller.getNumberOfRows(), controller.getNumberOfPlaces()))	 // If the spot has no car but is a pass spot.
                    		color = Location.PASSCOLOR;
                    	else {								//If the spot does not have a car and is not a pass spot.
                    		color =  Color.white;
                    	}
                    }
                    else { 
                    	color = car.getColor();			//If there is a car.
                    }
                    drawPlace(g, location, color);
                }
            }
        }
		repaint();
	}
	
	
	/**
	 * Set the default size for this JPanel.
	 */
    public Dimension getPreferredSize() {
        return new Dimension(800, 400);
    }    
    
    
    /**
     * Paint a place on this car park view in a given color.
     */
    private void drawPlace(Graphics g, Location location, Color color) {
        g.setColor(color);
        /*g.fillRect(
            location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 60 + (location.getRow() % 2) * 20,
            40 + location.getPlace() * 10,
            20 - 1,
            10 - 1
        );*/
        
        
        // DYNAMIC
		int width = carParkImage.getWidth();
		int height = carParkImage.getHeight();
        
        g.fillRect(
        		location.getFloor() * (width/3) + 
                (1 + (int)Math.floor(location.getRow() * 0.5)) * (width/13) +                 
                (location.getRow() % 2) * (width/40),

                (height/40) + location.getPlace() * (height/30),
                
                (width/40) - (width/800),
                (height/40) - (width/400)
        );
    }
}
