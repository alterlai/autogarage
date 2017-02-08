package model;
import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();
    private int type = 0;

    public boolean addCar(Car car) {
        return queue.add(car);
    }

    public Car removeCar() {
        return queue.poll();
    }

    public int carsInQueue(){
    	return queue.size();
    }
    public Car peek()
    {
    	return this.queue.peek();
    }
    public int getType()
    {
    	return type;
    }
    public void setType(int type)
    {
    	this.type = type;
    }
}
