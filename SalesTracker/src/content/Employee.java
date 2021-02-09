package content;

/**
 * 
 * @author author
 * Employee class, can only be used through child class.
 *
 */
public class Employee {

	private final int employeeNumber;
	private String name;
	
	/**
	 * @param employeeNumber
	 */
	protected Employee(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the employeeNumber
	 */
	public int getEmployeeNumber() {
		return employeeNumber;
	}

	@Override
	public String toString() {
		return "Employee [employeeNumber=" + employeeNumber + ", name=" + name + "]";
	}
	
	
	
}
