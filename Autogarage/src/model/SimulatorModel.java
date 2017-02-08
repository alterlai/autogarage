package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import controller.*;

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
	private Time time;
	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	private static final String RESERVED = "3";

	// Queues
	private CarQueue standardEntranceQueue;
    private CarQueue passEntranceQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitQueue;
    private CarQueue placeholderQueue;
    
    //Tick information.
    private int tickPause = 100;
    private int simulationLength = 50;
    private int currentTick = 1;

    int weekDayArrivals= 100; 			// average number of arriving cars per hour
    int weekendArrivals = 200; 			// average number of arriving cars per hour
    int weekDayPassArrivals= 50; 		// average number of arriving cars per hour
    int weekendPassArrivals = 5; 		// average number of arriving cars per hour
    int weekDayReservedArrivals = 50; 	// average number of arriving cars per hour
    int weekendReservedArrivals = 100; 	// average number of arriving cars per hour
    
    private int numberOfPassHolders = 90;	// Amount of customers with a pass.

    int enterSpeed = 8; 	// number of cars that can enter per minute
    int paymentSpeed = 5;	// number of cars that can pay per minute
    int exitSpeed = 5;		// number of cars that can leave per minute
    
    private int numberOfFloors;								// The amount of floors in the garage.
    private int numberOfRows;								// The amount of rows in each floor.
    private int numberOfPlaces;								// The amount of places in each row
    private int numberOfOpenSpots;							// The amount of free spots in the garage.
    private Car[][][] cars;									//Car array of all the cars in the garage.
    
    private boolean arePlaceholdersSet = false;
    
    private int numberOfAdHocsServed;		// The amount of AdHocs that have paid.
    private int numberOfPassesServed;		// The amount of Parking pass cars that have paid.
    private int numberOfReservationsServed;	// The amount of Reservation cars that have paid. 
    
    //Multithreading info
    private Thread t;						//Running thread.
    private String threadName = "model";	//threadname.
    private boolean running = true;			//Flag for the thread to run.
    
    // Statistics
    private HashMap<String, Integer> totalCarInfo;	// Hashmap containing the total amount of cars in the garage.
    private Bank bank;								// The bank regulating money.
    private int numberOfServedCars;					// The amount of served cars.
    private int numberOfMissedCars; 				// The amount of missed cars because of business.
    
    // Car queue statistics
    private int entranceLength;
    private int passEntranceLength;
    private int paymentLength;
    private int exitLength;
        
    /**
     * Constructor for the SimulatorModel class.
     * @param controller
     */
    public SimulatorModel(SimulatorController controller) {
        this.controller = controller;
        time = new Time();
        //Generate new queues.
    	standardEntranceQueue = new CarQueue();
        passEntranceQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitQueue = new CarQueue();
        placeholderQueue = new CarQueue();
        
        //Construct the banks.
        bank = new Bank();
        Car.setBank(bank);
        
        //Construct hashmaps for car information and balance information and initialize variables.
        resetCarInfo();
        resetBalanceInfo();
        
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
        
        if (!arePlaceholdersSet) {
        	placePlaceholders(numberOfPassHolders);
        }
        
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
    	standardEntranceQueue = new CarQueue();
        passEntranceQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitQueue = new CarQueue();
        placeholderQueue = new CarQueue();
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        currentTick = 1;
        this.numberOfOpenSpots = numberOfFloors*numberOfRows*numberOfPlaces;
        this.numberOfServedCars = 0;
        this.numberOfMissedCars = 0;
        this.numberOfAdHocsServed = 0;
        this.numberOfPassesServed = 0;
        this.numberOfReservationsServed = 0;
        this.entranceLength = 0;
        this.passEntranceLength = 0;
        this.paymentLength = 0;
        this.exitLength = 0;
        time = new Time();
        bank = new Bank();
        Car.setBank(bank);
        resetCarInfo();
        resetBalanceInfo();
        this.arePlaceholdersSet = false;
    }
    
    /**
     * Main tick loop of the application
     * Each tick will advance the time and will let the cars move around.
     */
    private void tick() {
    	time.advanceTime();				// Advance the time inside the simulation
    	handleExit();				// Handle cars exiting the garage
    	controller.updateViews(); 
    	// Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	
    	// Reset queue variables to 0
    	entranceLength = 0;
    	passEntranceLength = 0;
    	paymentLength = 0;
    	exitLength = 0;
    	
    	handleEntrance();
    	
    	//Loop over every element in the car count and reset the value to 0 for counting cars per tick.
    	Iterator it = totalCarInfo.entrySet().iterator();
    	while (it.hasNext())
    	{
    		HashMap.Entry pair = (HashMap.Entry)it.next();
    		totalCarInfo.put((String)pair.getKey(), 0);
    	}
    	
    	// Let the cars tick 
    	for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                        
                        // Car counting.
                        incrementTotal("all");
                        if (car instanceof AdHocCar) incrementTotal("adhoc");
                        if (car instanceof ParkingPassCar) incrementTotal("pass");
                        if (car instanceof ReservationCar) incrementTotal("reservation");
                    }
                }
            }
        }
    }
    
    private void placePlaceholders(int placeHolders) {
    	int i = 0;
    	while (placeHolders > i) {
    		placeholderQueue.addCar(new PassPlaceHolder());
    		Car car = placeholderQueue.removeCar();
        	Location freeLocation = getFirstFreeLocation();
        	setCarAt(freeLocation, car);
    		i++;
    	}
    	placeholdersAreSet();
    }
    
    private void placeholdersAreSet() {
    	this.arePlaceholdersSet = true;
    }

    /**
     * This method will help increment total counts in the car total hashmap.
     */
    private void incrementTotal(String name)
    {
    	int tempvalue = totalCarInfo.get(name);
    	tempvalue++;
    	totalCarInfo.put(name, tempvalue);
    }

    private void handleEntrance(){
    	carsArriving();
    	carsEntering(passEntranceQueue);
    	carsEntering(standardEntranceQueue);  	
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
        	
        	if (car instanceof ParkingPassCar) {
        		Car placeholder = getFirstPlaceholder();
        		if (placeholder != null) {
        			carLeavesSpot(placeholder);
        			Location freeLocation = getFirstFreeLocation();
        			setCarAt(freeLocation, car);
        		}
        		i++;
        	} else {
        		Location freeLocation = getFirstFreeLocation();
        		setCarAt(freeLocation, car);
        		i++;
        	}
        }
    }
    
    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = getFirstLeavingCar();
        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            if (paymentCarQueue.carsInQueue() < paymentSpeed) {
	            	paymentCarQueue.addCar(car);
	            	paymentLength++;
	            }
        	} else {
        		carLeavesSpot(car);
        		placePlaceholders(1);
        	}
            car = getFirstLeavingCar();
        }
    }

    private void carsPaying(){
        // Let cars pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            car.pay(car);	//Make the car pay.   
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
    	while (exitQueue.carsInQueue()>0 && i < exitSpeed){
            Car car = exitQueue.removeCar();
            
            numberOfServedCars++;
            if (car instanceof AdHocCar) this.numberOfAdHocsServed++;
            if (car instanceof ParkingPassCar) this.numberOfPassesServed++;
            if (car instanceof ReservationCar) this.numberOfReservationsServed++;
            
            i++;
    	}	
    }
    
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = time.getDay() < 5
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
            	if (standardEntranceQueue.carsInQueue() > enterSpeed) {
            		numberOfMissedCars++;
            	} else {            	
            		standardEntranceQueue.addCar(new AdHocCar());
            		entranceLength++;
            	}	
            }
            break;
            
    	case PASS:
            for (int i = 0; i < numberOfCars; i++) {
            	if (passEntranceQueue.carsInQueue() > enterSpeed) {
            		numberOfMissedCars++;
            	} else {            	
            		passEntranceQueue.addCar(new ParkingPassCar());
            		passEntranceLength++;
            	}
            }
            break;
            
    	case RESERVED:
    		for (int i = 0; i < numberOfCars; i++) {
    			if (passEntranceQueue.carsInQueue() > enterSpeed) {
            		numberOfMissedCars++;
            	} else {            	
            		passEntranceQueue.addCar(new ReservationCar());
            		passEntranceLength++;
            	}
            }
            break;
            
    	}
    }
    
    private void carLeavesSpot(Car car){
    	removeCarAt(car.getLocation());
    	if (exitQueue.carsInQueue() < exitSpeed) {
    		exitQueue.addCar(car);
    		exitLength++;
    	}
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
    
    public Car getFirstPlaceholder() {
    	for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car instanceof PassPlaceHolder) {
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
    
    /**
     * Reset all the car counting data.
     */
    public void resetCarInfo()
    {
    	totalCarInfo = new HashMap<>(); 
        totalCarInfo.put("all", 0);
    	totalCarInfo.put("pass", 0);
    	totalCarInfo.put("adhoc", 0);
    	totalCarInfo.put("reservation", 0);
    	totalCarInfo.put("standardEntranceQueue", 0);
    	totalCarInfo.put("paymentQueue", 0);
    	totalCarInfo.put("passEntranceQueue", 0);
    	totalCarInfo.put("exitQueue", 0);
    	totalCarInfo.put("free", 0); 
    	totalCarInfo.put("served", 0);
    	totalCarInfo.put("missed", 0); 	
    	totalCarInfo.put("adhoc served", 0); 	
    	totalCarInfo.put("pass served", 0); 	
    	totalCarInfo.put("reserved served", 0); 	
    }
    
    /**
     * Generate a new bank object and set the car's bank reference to the new bank.
     */
    private void resetBalanceInfo()
    {
    	bank = new Bank();
    	Car.BANK = bank;
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
    
    /**
     * Set the tick interval between each tick.
     * @param interval
     */
    public void setTickPause(int interval) {
    	this.tickPause = interval;
    }
    
    /**
     * Sets the number of ad hoc cars arriving per hour during th week.
     * @param int input	cars per hour.
     */
    public void setWeekDayArrivals(int input){
    	this.weekDayArrivals = input;
    }
    
    /**
     * Sets the number of ad hoc cars arriving per hour in the weekend.
     * @param int input	cars per hour.
     */
    public void setWeekendArrivals(int input){
    	this.weekendArrivals = input;
    }
    /**
     * Sets the number of pass holders arriving each hour in a weekday.
     * @param input int cars per hour.
     */
    public void setWeekDayPassArrivals(int input){
    	this.weekDayPassArrivals = input;
    }
    public void setWeekendPassArrivals(int input){
    	this.weekendPassArrivals = input;
    }
    public void setWeekDayReservedArrivals(int input){
    	this.weekDayReservedArrivals = input;
    }
    public void setWeekendReservedArrivals(int input){
    	this.weekendReservedArrivals = input;
    }
    public void setEnteringSpeed(int input){
    	this.enterSpeed = input;
    }
    public void setPaymentSpeed(int input){
    	this.paymentSpeed = input;
    }
    public void setExitSpeed(int input){
    	this.exitSpeed = input;
    }
    public void setNumberOfFloors(int input){
    	this.numberOfFloors = input;
    }
    public void setNumberOfRows(int input){
    	this.numberOfRows = input;
    }
    public void setNumberOfPlaces(int input){
    	this.numberOfPlaces = input;
    }
    public void setNumberOfPassholders(int input){
    	this.numberOfPassHolders = input;
    }    
    
    /**
     * Returns the total amount of cars parked in the garage that are not in queue.
     */
    public HashMap<String, Integer> getTotalCarInfo()
    {
    	totalCarInfo.put("free", this.numberOfOpenSpots);
    	totalCarInfo.put("standardEntranceQueue", this.entranceLength);
    	totalCarInfo.put("paymentQueue", this.paymentLength);
    	totalCarInfo.put("passEntranceQueue", this.passEntranceLength);
    	totalCarInfo.put("exitQueue", this.exitLength);
    	totalCarInfo.put("served", this.numberOfServedCars);
    	totalCarInfo.put("missed", this.numberOfMissedCars);
    	totalCarInfo.put("adhoc served", this.numberOfAdHocsServed);
    	totalCarInfo.put("pass served", this.numberOfPassesServed);
    	totalCarInfo.put("reserved served", this.numberOfReservationsServed);
    	return this.totalCarInfo;
    }
    
    public Bank getBank()
    {
    	return this.bank;
    }
    
    /**
     * Let all the passholders pay their monthly fee.
     */
    public void PayPassClients()
    {
    	bank.addBalance(this.numberOfPassHolders * ParkingPassCar.getMonthlyRate(), new ParkingPassCar());
    }
    
    public Time getTime()
    {
    	return this.time;
    }
}
