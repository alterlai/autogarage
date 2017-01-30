package Autogarage;

import java.util.Random;

/**
 * The SimulatorModel is in charge of all the information of the simulation. The model will also handle the main tick loop.
 * The main tick loop will issue view updates to the controller which will delegate them to the view.
 * It will also order the queues to update and the cars to act.
 * @author Jeroen van der Laan
 * @version 0.4
 *
 */
public class SimulatorModel implements Runnable{
	private SimulatorController controller; //The controller that's controlling this model.
	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	private static final String RESERVED = "3";

	// Queues
	private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;
    
    //Tick information.
    private int tickPause = 100;
    private int simulationLength = 50;
    private int currentTick = 1;

    int weekDayArrivals= 100; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals= 50; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour
    int weekDayReservedArrivals = 50; // average number of arriving cars per hour
    int weekendReservedArrivals = 100; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute
    
    private int numberOfFloors;	// The amount of floors in the garage.
    private int numberOfRows;	// The amount of rows in each floor.
    private int numberOfPlaces;	// The amount of places in each row
    private int numberOfOpenSpots;	// The amount of free spots in the garage.
    private Car[][][] cars;			//Car array of all the cars in the garage.
    
    //Multithreading info
    private Thread t;
    private String threadName = "model";
    private boolean running = true;
    

    public SimulatorModel(SimulatorController controller) {
        this.controller = controller;
        //Generate new queues.
    	entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        
        // Populate fields.
        this.numberOfFloors = 3;
        this.numberOfRows = 6;
        this.numberOfPlaces = 30;
        this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
    }

    /**
     * The run command will run the simulation by issuing ticks.
     * @param ammountOfTicks The amount of ticks to be simulated.
     */
    public void run() {
        int i = 0;
    	while (i < simulationLength && running)
    	{
    		tick();
    		i++;
    		currentTick++;
    	}
    }
    
    /**
     * The start method starts a thread and will start the simulation ticks.
     */
    public void start () {
        if (t == null) {
           System.out.println("Running thread: " +  threadName );
           t = new Thread (this, threadName);	// Create a new thead.
           running = true; 						// Set the running flag.
           t.start ();							// Start thread's execution.
        }
        else
        {
        	t = null;	// Delete the thread.
        	start();	// Remake a new thread and start the simulation.
        }
     }
    
    /**
     * This will reset the simulation info.
     */
    public void reset()
    {
    	entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        currentTick = 1;
    }
    
    /**
     * Main tick loop of the application
     * Each tick will advance the time and will let the cars move around.
     */
    private void tick() {
    	advanceTime();				// Advance the time inside the simulation
    	handleExit();				// Handle cars exiting the garage
    	controller.updateViews(); 
    	// Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	handleEntrance();
    	
    	// Let the cars tick 
    	for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

    }

    private void handleEntrance(){
    	carsArriving();
    	carsEntering(entrancePassQueue);
    	carsEntering(entranceCarQueue);  	
    }
    
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    private void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);    	
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);
        numberOfCars=getNumberOfCars(weekDayReservedArrivals, weekendReservedArrivals);
        addArrivingCars(numberOfCars, RESERVED);
    }

    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && 
    			getNumberOfOpenSpots()>0 && 
    			i<enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = getFirstFreeLocation();
            setCarAt(freeLocation, car);
            i++;
        }
    }
    
    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = getFirstLeavingCar();
        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car);
        	}
            car = getFirstLeavingCar();
        }
    }

    private void carsPaying(){
        // Let cars pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
    	}
    }
    
    /**
     * Process the leaving queue
     */
    private void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
    	}	
    }
    
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
    	switch(type) {
    	case AD_HOC: 
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new AdHocCar());
            }
            break;
            
    	case PASS:
            for (int i = 0; i < numberOfCars; i++) {
            	entrancePassQueue.addCar(new ParkingPassCar());
            }
            break;
            
    	case RESERVED:
    		for (int i = 0; i < numberOfCars; i++) {
            	entrancePassQueue.addCar(new ReservationCar());
            }
            break;
            
    	}
    }
    
    private void carLeavesSpot(Car car){
    	removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }
   
    /**
     * Get the first car that's leaving
     * @return Car the car that is leaving.
     */
    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }
    
    public int getNumberOfFloors() {
        return this.numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getNumberOfOpenSpots(){
    	return numberOfOpenSpots;
    }
    
    /**
     * Return the car that is at a given location.
     * @param location	The location of which you want to get the car
     * @return	Car		the car at the given locaion.
     */
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }
    
    /**
     * Check if the given location is a valid location.
     * @param location
     * @return wether the location is a valid location.
     */
    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
    
    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }
    
    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        numberOfOpenSpots++;
        return car;
    }

    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * Return the current tick of the simulation
     * @return current tick
     */
    public int getCurrentTick()
    {
    	return currentTick;
    }
    
   /**
    * Set the length of the simulation
    */
    public void setSimulationLength(int length)
    {
    	this.simulationLength = length;
    }
    
    /**
     * Set the running flag. Setting this to false will stop the simulation
     * @param flag
     */
    public void setRunning( boolean flag)
    {
    	running = flag;
    }
}
