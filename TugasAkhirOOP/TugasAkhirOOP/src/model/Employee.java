package model;

public class Employee {
	private String employeeId, employeeName;
	private int restaurantId;

	public Employee(String employeeId, String employeeName, int restaurantId) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.restaurantId = restaurantId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

}
