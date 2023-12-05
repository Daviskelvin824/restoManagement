package model;

public class Order {
	private String CustomerName, menuId,orderStatus;
	private int quantity;

	public Order(String CustomerName, String menuId, int quantity,String OrderStatus) {
		super();
		this.CustomerName = CustomerName;
		this.menuId = menuId;
		this.quantity = quantity;
		this.orderStatus = OrderStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	
	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
