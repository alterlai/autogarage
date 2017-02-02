package model;

/**
 * This class will keep track of the revenue and balance of the garage.
 * @author Jeroen van der Laan.
 *
 */
public class Bank {
	private double totalBalance;
	
	public Bank()
	{
		totalBalance = 0;
	}
	
	public void addBalance(double amount)
	{
		totalBalance += amount;
		System.out.println("Payment added. Total balance: " + totalBalance);
	}
	
	public double getTotalBalance()
	{
		return totalBalance;
	}
}
