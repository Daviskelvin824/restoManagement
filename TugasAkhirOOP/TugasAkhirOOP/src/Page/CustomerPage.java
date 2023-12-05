package Page;

import java.util.ArrayList;
import java.util.Scanner;

import model.Employee;
import model.Menu;
import model.Reservation;
import model.Restaurant;
import util.GetEmployee;
import util.GetMenu;
import util.GetOrder;
import util.GetReservation;
import util.GetRestaurant;

public class CustomerPage {
	String name,reservationID;
	Scanner sc = new Scanner (System.in);
	ArrayList<Reservation> reservationList = new ArrayList();
	ArrayList<Restaurant> restaurantList = new ArrayList();
	ArrayList<Employee> employeeList = new ArrayList();
	ArrayList<Menu> menulist = new ArrayList();
	private void clrSc() {
		for(int i=0;i<30;i++) {
			System.out.println();
		}
	}
	
	public CustomerPage(String name, String reservationId) {
		this.name = name;
		this.reservationID = reservationId;
		cPage();
	}
	
	private void refresh() {
		reservationList.removeAll(reservationList);
		employeeList.removeAll(employeeList);
		restaurantList.removeAll(restaurantList);
		menulist.removeAll(menulist);
	}
	
	private void init() {
		reservationList = GetReservation.GetReservations("SELECT * FROM msreservation WHERE ReservationID = '"+reservationID+"'");
		employeeList = GetEmployee.getEmployees("SELECT * FROM msemployee WHERE EmployeeID = '"+reservationList.get(0).getEmployeeId()+"'");
		restaurantList = GetRestaurant.GetResto("SELECT * FROM msrestaurant WHERE RestaurantID = '"+employeeList.get(0).getRestaurantId()+"'");
		if(restaurantList.get(0).getId()==2 || restaurantList.get(0).getId()==3 || restaurantList.get(0).getId()==6) {
			menulist = GetMenu.GetMenus("SELECT * FROM msmenu WHERE MenuType IN('Normal','Special')");
		}else {
			menulist = GetMenu.GetMenus("SELECT * FROM msmenu WHERE MenuType IN('Normal','LocalSpecial')");
		}
	}
	
	private void showMenu() {
		if(restaurantList.get(0).getId()==2 || restaurantList.get(0).getId()==3 || restaurantList.get(0).getId()==6) {
			int cnt=1;
			System.out.println("|Name                             |Type         |Price       |Location          |Description                                                    ");
			System.out.println("--------------------------------------------------------------------------------------------------------------------------");
			for (Menu menu : menulist) {
				System.out.printf("|%d.%-33s|%-13s|%-12d|%-18s|%s\n",cnt,menu.getName(),menu.getType(),menu.getPrice(),menu.getLocation(),menu.getDesc());
				cnt++;
			}
		}else {
			int cnt=1;
			System.out.println("|Name                               |Type            |Price          |Description                                                    ");
			System.out.println("--------------------------------------------------------------------------------------------------------------------------");
			for (Menu menu : menulist) {
				System.out.printf("|%d.%-33s|%-16s|%-15d|%s\n",cnt,menu.getName(),menu.getType(),menu.getPrice(),menu.getDesc());
				cnt++;
			}
		}
	}
	
	private void cPage() {
		clrSc();
		init();
		System.out.println("Welcome, "+reservationList.get(0).getCustomerName());
		System.out.println("To Our Restaurant in "+ restaurantList.get(0).getRestaurantName());
		System.out.println("Here's our menu: ");
		while(true) {
			showMenu();
			int choice,qty;
			String chr;
			System.out.println("Choose which food would you like to order(You can order multiple foods) ");
			System.out.print(">> ");
			choice = sc.nextInt();sc.nextLine();
			System.out.println("How many of this menu you want?");
			System.out.print(">> ");
			qty = sc.nextInt();sc.nextLine();
			
			GetOrder.insertOrder("INSERT INTO msorder (MenuID,Quantity,OrderStatus,CustomerName)"
					+ "VALUES ('"+menulist.get(choice-1).getMenuId()+"','"+qty+"','In Order','"+name+"');");
			refresh();
			init();
			System.out.println("Would you like to add another order? (Y/N)");
			System.out.print(">> ");
			chr = sc.nextLine();
			if(chr.equalsIgnoreCase("Y")) {
				continue;
			}else if(chr.equalsIgnoreCase("N"))break;
			
		}
		System.out.println("Thank you for your order, please wait for the food, Thank you...");
		sc.nextLine();
		
	}
	
	
}
