package content;

import java.text.DecimalFormat;

/**
 * 
 * @author author
 *
 */
public class Worker extends Employee {

	private int hoursWorked;
	private double hourlyRate;
	private double pay;
	
	/**
	 * 
	 * @param employeeNumber
	 * @param name
	 * @param hoursWorked
	 * @param hourlyRate
	 */
	public Worker(int employeeNumber, String name, int hoursWorked, double hourlyRate) {
		super(employeeNumber);
		super.setName(name);
		
		this.hourlyRate = hourlyRate;
		this.hoursWorked = hoursWorked;
		
		this.pay = calculatePay(hoursWorked, hourlyRate);
	}
	
	/**
	 * 
	 * @param hoursWorked
	 * @param hourlyRate
	 * @return calculated pay after multiplying hours worked with hourly rate
	 */
	private double calculatePay(int hoursWorked, double hourlyRate) {
		
		return hoursWorked * hourlyRate;
		
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		DecimalFormat newFormat = new DecimalFormat("#.##");
		return
			"Employee Category: Worker \n" +
			"Employee Number: " + this.getEmployeeNumber()  + "\n" + 
			"Name: " + this.getName() + "\n" + 
			"Hours worked: " + hoursWorked + " hours\n" + 
			"Hourly Rate: " + Double.valueOf(newFormat.format(hourlyRate)) + "$\n" + 
			"Pay: " + Double.valueOf(newFormat.format(pay)) + "$\n";
	}
	
	
}
