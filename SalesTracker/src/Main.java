

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import content.Employee;
import content.InvalidSalesException;
import content.Salesperson;
import content.Worker;

/**
 * 
 * @author authorName
 * Main program to handle employee order details
 *
 */
public class Main {

	private static List<Employee> employeeList = new ArrayList<>();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			int check = 1;
			while(check == 1) {
				System.out.print("Employee Number: ");
				int employeeNumber = sc.nextInt();
				System.out.println("Name: ");
				String name = sc.next();
				System.out.print("Enter 1 for salesperson and 2 for worker): ");
				int employeeCheck = sc.nextInt();
				
				switch(employeeCheck) {
					case 1: {
						System.out.println("Sales Amount ($): ");
						double salesAmount = sc.nextDouble();
						
						// Constructing Sales person object
						Employee employee = new Salesperson(employeeNumber, name, salesAmount);
						employeeList.add(employee);
						break;
					}
					case 2: {
						System.out.println("Hours Worked: ");
						int hoursWorked = sc.nextInt();
						System.out.println("Hourly rate ($): ");
						double hourlyRate = sc.nextDouble();
						
						// Constructing worker object
						Employee employee = new Worker(employeeNumber, name, hoursWorked, hourlyRate);
						employeeList.add(employee);
						break;
					}
					default: {
						System.out.println("Wrong Input! Try again.");
						continue;
					}
				
				}
				System.out.println("Enter 0 for printing and 1 for adding more: ");
				check = sc.nextInt();
			}
			
			for(Employee employee: employeeList) {
				System.out.println(employee.toString());
			}
		} catch (InvalidSalesException e) {
			System.out.println("Exception occured: " + e.getMessage());
			System.out.println("Exiting...");
			
		}
		catch (Exception e) {
			System.out.println("Exception occured: " + e.getMessage());
			System.out.println("Exiting...");
			
		}
		
		
		sc.close();
	}

}
