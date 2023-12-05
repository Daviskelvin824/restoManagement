package Page;

import java.util.ArrayList;
import java.util.Scanner;

import model.Employee;
import model.Menu;
import model.Order;
import model.Reservation;
import model.Restaurant;
import util.GetEmployee;
import util.GetMenu;
import util.GetOrder;
import util.GetReservation;
import util.GetRestaurant;

public class AdminPage {

	Scanner sc = new Scanner(System.in);
	String name,employeeID;
	ArrayList<Reservation> reservationList = new ArrayList();
	ArrayList<Restaurant> restaurantList = new ArrayList();
	ArrayList<Employee> employeeList = new ArrayList();
	ArrayList<Menu> menulist = new ArrayList();
	ArrayList<Menu> menulistCnt = new ArrayList();
	ArrayList<Order> orderList = new ArrayList();
	private void clrSc() {
		for(int i=0;i<30;i++) {
			System.out.println("");
		}
	}
	
	public AdminPage(String name, String employeeId) {
		this.name = name;
		this.employeeID = employeeId;
		admPage();
	}
	
	private void refresh() {
		reservationList.removeAll(reservationList);
		orderList.removeAll(orderList);
		employeeList.removeAll(employeeList);
		restaurantList.removeAll(restaurantList);
		menulist.removeAll(menulist);
		menulistCnt.removeAll(menulistCnt);
	}
	
	private void init() {
		reservationList = GetReservation.GetReservations("SELECT * FROM msreservation WHERE EmployeeID = '"+employeeID+"'");
		orderList = GetOrder.GetOrders("SELECT * FROM msorder WHERE CustomerName = '"+reservationList.get(0).getCustomerName()+"'");
		for (Reservation reserve : reservationList) {
			int cnt=1;
			if(!(reserve.getCustomerName().equals(reservationList.get(cnt).getCustomerName()))) {
				orderList = GetOrder.GetOrders("SELECT * FROM msorder WHERE CustomerName = '"+reservationList.get(cnt).getCustomerName()+"'");
			}
			cnt++;
		}
		employeeList = GetEmployee.getEmployees("SELECT * FROM msemployee WHERE EmployeeID = '"+employeeID+"'");
		restaurantList = GetRestaurant.GetResto("SELECT * FROM msrestaurant WHERE RestaurantID = '"+employeeList.get(0).getRestaurantId()+"'");
		if(restaurantList.get(0).getId()==2 || restaurantList.get(0).getId()==3 || restaurantList.get(0).getId()==6) {
			menulist = GetMenu.GetMenus("SELECT * FROM msmenu WHERE MenuType IN('Normal','Special')");
		}else {
			menulist = GetMenu.GetMenus("SELECT * FROM msmenu WHERE MenuType IN('Normal','LocalSpecial')");
		}
		menulistCnt = GetMenu.GetMenus2("SELECT * FROM msmenu");
	}
	
	private void printOrder() {
		System.out.println("|MenuID|Quantity|Order Status|Customer Name");
		System.out.println("-------------------------------------------");
		for (Order order : orderList) {
			System.out.printf("|%-6s|%-8d|%-12s|%s\n",order.getMenuId(),order.getQuantity(),order.getOrderStatus(),order.getCustomerName());
		}
	}
	
	private void showMenu() {
		if(restaurantList.get(0).getId()==2 || restaurantList.get(0).getId()==3 || restaurantList.get(0).getId()==6) {
			System.out.println("|MenuID|Name                          |Type         |Price       |Location          |Description                                                    ");
			System.out.println("--------------------------------------------------------------------------------------------------------------------------");
			for (Menu menu : menulist) {
				System.out.printf("|%-6s|%-30s|%-13s|%-12d|%-18s|%s\n",menu.getMenuId(),menu.getName(),menu.getType(),menu.getPrice(),menu.getLocation(),menu.getDesc());
			}
		}else {
			System.out.println("|MenuID|Name                          |Type            |Price          |Description                                                    ");
			System.out.println("--------------------------------------------------------------------------------------------------------------------------");
			for (Menu menu : menulist) {
				System.out.printf("|%-6s|%-30s|%-16s|%-15d|%s\n",menu.getMenuId(),menu.getName(),menu.getType(),menu.getPrice(),menu.getDesc());
			}
		}
	}
	
	private void admPage() {
		clrSc();
		init();
		System.out.println(employeeID +"-"+name);
		int choice;
		do {
			System.out.println("1. Make Reservation");
			System.out.println("2. Check Out");
			System.out.println("3. Update Menu");
			System.out.println("4. Delete Menu");
			System.out.println("5. Add Menu");
			System.out.println("6. Exit");
			choice = sc.nextInt();sc.nextLine();
			switch(choice) {
			case 1:
				makeReservation();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				updateMenu();
				break;
			case 4:
				deleteMenu();
				break;
			case 5:
				addMenu();
				break;
			case 6:
				clrSc();
				System.out.print("Press Enter to continue...");
				sc.nextLine();
				break;
			}
			
		}while(choice!=6);
	}

	private void addMenu() {
	    clrSc();
	    String MenuID, MenuName, MenuType, MenuDescription = null, MenuLocation = null;
	    Integer Price = 0;
	    showMenu();

	    // Generate the formatted MenuID
	    int nextMenuNumber = menulistCnt.size()+1;
	    MenuID = String.format("ME%03d", nextMenuNumber);

	    System.out.println("Generated MenuID: " + MenuID);

	    System.out.print("Menu Name: ");
	    MenuName = sc.nextLine();

	    do {
	        System.out.print("Price : ");
	        Price = sc.nextInt();
	        sc.nextLine();
	    } while (Price > 10000000 || Price < 1);

	    do {
	        System.out.print("Menu Type: ");
	        MenuType = sc.nextLine();
	    } while (!(MenuType.equalsIgnoreCase("Normal")) && !(MenuType.equalsIgnoreCase("Special")) && !(MenuType.equalsIgnoreCase("LocalSpecial")));

	    if (MenuType.equals("Normal")) {
	        GetMenu.insertMenu("INSERT INTO msmenu(MenuID, MenuName, MenuType, Price) VALUES ('" + MenuID + "', '" + MenuName + "', '" + MenuType + "', " + Price + ")");
	    } else if (MenuType.equals("Special") && (restaurantList.get(0).getId() == 2 || restaurantList.get(0).getId() == 3 || restaurantList.get(0).getId() == 6)) {
	        System.out.print("Menu Description: ");
	        MenuDescription = sc.nextLine();
	        GetMenu.insertMenu("INSERT INTO msmenu(MenuID, MenuName, MenuType, Price, MenuDescription) VALUES ('" + MenuID + "', '" + MenuName + "', '" + MenuType + "', " + Price + ", '" + MenuDescription + "')");
	    } else if (MenuType.equals("LocalSpecial") && (restaurantList.get(0).getId() == 1 || restaurantList.get(0).getId() == 4 || restaurantList.get(0).getId() == 5)) {
	        System.out.print("Menu Description: ");
	        MenuDescription = sc.nextLine();
	        System.out.print("Menu Location: ");
	        MenuLocation = sc.nextLine();
	        GetMenu.insertMenu("INSERT INTO msmenu(MenuID, MenuName, MenuType, Price, MenuDescription, MenuLocation) VALUES ('" + MenuID + "', '" + MenuName + "', '" + MenuType + "', " + Price + ", '" + MenuDescription + "', '" + MenuLocation + "')");
	    }
	    refresh();
	    init();
	}


	private void deleteMenu() {
		clrSc();
		String MenuID;
		showMenu();
		System.out.print("Choose which menu you want to Delete: ");
		MenuID = sc.nextLine();
		for (Order order : orderList) {
			if(order.getMenuId().equals(MenuID)) {
				if(order.getOrderStatus().equals("In Order")) {
					System.out.println("Menu is Still In Order");
					sc.nextLine();
					return;
				}
			}
		}
		GetMenu.deleteMenu("DELETE FROM msmenu WHERE MenuID = '" + MenuID + "'");
		refresh();
		init();
		
	}

	private void updateMenu() {
		clrSc();
		String MenuID,MenuName, MenuType,MenuDescription,MenuLocation;
		Integer Price=0;
		showMenu();
		System.out.print("Choose which menu you want to Update: ");
		MenuID = sc.nextLine();
		System.out.print("Menu Name: ");
		MenuName = sc.nextLine();
		
		do {
			System.out.print("Price : ");
			Price = sc.nextInt();sc.nextLine();
		}while(Price>10000000 || Price<1);
		
		do {
			System.out.print("Menu Type: ");
			MenuType = sc.nextLine();			
		}while(!(MenuType.equalsIgnoreCase("Normal")) && !(MenuType.equalsIgnoreCase("Special")) && !(MenuType.equalsIgnoreCase("LocalSpecial")));
		
		if(MenuType.equals("Normal")) {
			GetMenu.updateMenu("UPDATE msmenu SET MenuName = '" + MenuName + "', MenuType = '" + MenuType + "', Price = " + Price + " WHERE MenuID = '" + MenuID + "'");
		}
		else if(MenuType.equals("Special")&&(restaurantList.get(0).getId()==2 || restaurantList.get(0).getId()==3 
				|| restaurantList.get(0).getId()==6)) {
			System.out.print("Menu Description: ");
			MenuDescription = sc.nextLine();
			GetMenu.updateMenu("UPDATE msmenu SET MenuName = '" + MenuName + "', MenuType = '" + MenuType + "', Price = " 
			+ Price + ",MenuDescription = '"+MenuDescription +"' WHERE MenuID = '" + MenuID + "'");
		}
		
		else if(MenuType.equals("LocalSpecial")&&(restaurantList.get(0).getId()==1 || restaurantList.get(0).getId()==4 
				|| restaurantList.get(0).getId()==5)) {
			System.out.print("Menu Description: ");
			MenuDescription = sc.nextLine();
			System.out.print("Menu Location: ");
			MenuLocation = sc.nextLine();
			GetMenu.updateMenu("UPDATE msmenu SET MenuName = '" + MenuName + "', MenuType = '" + MenuType + "', Price = " 
					+ Price + ",MenuDescription = '"+MenuDescription + "', MenuLocation = '"+ MenuLocation +"' WHERE MenuID = '" + MenuID + "'");
		}
		
		
	}

	private void checkOut() {
		clrSc();
		System.out.println("Checkout Customer Order");
		Integer totalBill = 0;
		String orderStatus,menuID;
		printOrder();
		System.out.print("Choose which Order is Done(Pick the MenuID): ");
		menuID = sc.nextLine();
		GetOrder.updateOrder("UPDATE msorder SET OrderStatus = 'Finalized' WHERE MenuID = '"+menuID+"'");
		System.out.println("Here is the total Bill:");
		for (Order order : orderList) {
			if(order.getMenuId().equals(menuID)) {
				totalBill = order.getQuantity();
				break;
			}
		}
		for (Menu menu : menulist) {
			if(menu.getMenuId().equals(menuID)) {
				totalBill*=menu.getPrice();
				break;
			}
		}
		System.out.println("Rp."+totalBill);
		sc.nextLine();
		
	}

	private void makeReservation() {
		clrSc();
		String customerName,tableType = null,reservationID;
		int tableCount,qty;
		System.out.println("Making Reservation for Customer!");
		System.out.print("Customer Name: ");
		customerName = sc.nextLine();
		System.out.print("Table Quantity: ");
		tableCount = sc.nextInt();sc.nextLine();
		do {
			System.out.print("People per table (max 10/table): ");
			qty = sc.nextInt();sc.nextLine();
		}while(qty>10 || qty<1);
		if(qty<=2) tableType = "Romantic";
		else if(qty<=4) tableType = "General";
		else if(qty<=10) tableType = "Family";
		int nextReservationNumber = reservationList.size() ;
	    reservationID = String.format("RE%03d", nextReservationNumber);
		
		GetReservation.insertReservation("INSERT INTO msreservation(ReservationID,EmployeeID,CustomerName,TableType,Quantity)"
				+ "VALUES ('"+reservationID+"','"+employeeID+"','"+customerName+"','"+tableType+"','"+tableCount+"')");
		refresh();
		init();
	}
	
}	
