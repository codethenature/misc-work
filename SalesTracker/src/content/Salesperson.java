package content;

import java.text.DecimalFormat;

/**
 * 
 * @author author
 * Salesperson class
 */
public class Salesperson extends Employee {

	private final double MAX_SALES_LIMIT = 2000;
	private final double MIN_SALES_LIMIT = 0;
	
	private double sales;
	private double rate;
	private double commission;
	
	
	/**
	 * 
	 * @param employeeNumber
	 * @param name
	 * @param salesAmount
	 * @throws InvalidSalesException
	 */
	public Salesperson(int employeeNumber, String name, double salesAmount) throws InvalidSalesException {
		super(employeeNumber);
		super.setName(name);
		
		if(salesAmount < MIN_SALES_LIMIT || salesAmount > MAX_SALES_LIMIT ) {
			throw new InvalidSalesException("Sales amount should be between 0$ and 2000$.");
		}
		
		this.sales = salesAmount;
		this.commission = calculateCommission(salesAmount);
	}
	
	/**
	 * 
	 * @param salesAmount
	 * @return calculated commission rate based on sales amount
	 */
	private double calculateCommissionRate(double salesAmount) {
		
		if(salesAmount >= 0 && salesAmount < 100.00) {
			return 5;
		} else if(salesAmount >= 100.01 && salesAmount < 1000.0) {
			return 7;
		} else {
			return 9;
		}
		
	}
	
	/**
	 * 
	 * @param salesAmount
	 * @return  calculated commission by multipying sales amount by rate percentage
	 */
	private double calculateCommission(double salesAmount) {
		
		this.rate = calculateCommissionRate(salesAmount);
		
		return (rate / 100) * salesAmount;
		
	}

	@Override
	public String toString() {
		DecimalFormat newFormat = new DecimalFormat("#.##");
		return
			"Employee Category: Sales Person \n" +
			"Employee Number: " + this.getEmployeeNumber()  + "\n" + 
			"Name: " + this.getName() + "\n" + 
			"Sales: " + Double.valueOf(newFormat.format(sales)) + "$\n" + 
			"Rate: " + Double.valueOf(newFormat.format(rate)) + "%\n" + 
			"Commission: " + Double.valueOf(newFormat.format(commission)) + "$\n";
	}
	
	

}
