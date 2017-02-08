package model;

/**
 * This class will keep track of the revenue and balance of the garage.
 * @author Jeroen van der Laan.
 *
 */
public class Bank {
	private double totalBalance;
	private double adHocBalance;
	private double passBalance;
	private double reservedBalance;
	
	public Bank()
	{
		totalBalance = 0;
	}
	
	public void addBalance(double amount, Car car)
	{
		totalBalance += amount;
		System.out.println("Payment added. Total balance: " + totalBalance);
		if (car instanceof AdHocCar) adHocBalance += amount;
		if (car instanceof ParkingPassCar) passBalance += amount;
		if (car instanceof ReservationCar) reservedBalance += amount;
	}
	
	public double getTotalBalance()
	{
		return totalBalance;
	}
	
	/**
	 * @return the total amount of money paid by ad hoc cars.
	 */
	public double getAdHocBalance()
	{
		return this.adHocBalance;
	}
	
	/**
	 * @return total amount paid by parking pass holders.
	 */
	public double getPassBalance()
	{
		return this.passBalance;
	}
	
	/**
	 * @return the total amount of money paid by reserved spots.
	 */
	public double getReservedBalance()
	{
		return this.reservedBalance;
	}
	
	
	
}
