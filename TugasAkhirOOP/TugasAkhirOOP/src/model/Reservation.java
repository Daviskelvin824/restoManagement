package model;

public class Reservation {
	private String reservationId, employeeId, customerName, tableType;
	private int quantity;

	public Reservation(String reservationId, String employeeId, String customerName, String tableType, int quantity) {
		super();
		this.reservationId = reservationId;
		this.employeeId = employeeId;
		this.customerName = customerName;
		this.tableType = tableType;
		this.quantity = quantity;
	}

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
