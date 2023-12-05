package model;

public class Menu {
	private String menuId, name, type, desc, location;
	private int price;

	public Menu(String menuId, String name, String type, String desc, String location, int price) {
		super();
		this.menuId = menuId;
		this.name = name;
		this.type = type;
		this.desc = desc;
		this.location = location;
		this.price = price;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
