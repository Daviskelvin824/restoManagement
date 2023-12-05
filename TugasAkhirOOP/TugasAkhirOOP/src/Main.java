import java.util.ArrayList;
import java.util.Scanner;

import Page.AdminPage;
import Page.CustomerPage;
import model.Employee;
import model.Reservation;
import util.GetEmployee;
import util.GetReservation;

public class Main {
	Scanner sc = new Scanner(System.in);
	ArrayList<Reservation> reservationList = new ArrayList<>();
	ArrayList<Employee> employeeList = new ArrayList<>();

	private void logo() {
		System.out.println("  _                                  _    _  \r\n"
				+ " | |                           /\\   | |  | | \r\n"
				+ " | |     __ _ _ __   ___ _ __ /  \\  | |__| | \r\n"
				+ " | |    / _` | '_ \\ / _ \\ '__/ /\\ \\ |  __  | \r\n"
				+ " | |___| (_| | |_) |  __/ | / ____ \\| |  | | \r\n"
				+ " |______\\__,_| .__/ \\___|_|/_/    \\_\\_|  |_| \r\n"
				+ "             | |                             \r\n"
				+ "             |_|                             ");
	}
	
	private void clrSc() {
		for(int i=0;i<30;i++) {
			System.out.println("");
		}
	}
	
	public Main() {
		reservationList = GetReservation.GetReservations("SELECT * FROM msreservation");
		employeeList = GetEmployee.getEmployees("SELECT * FROM msemployee");
		int choice;
		do {
			clrSc();
			logo();
			System.out.println("Welcome to Our Restaurant Reservation Program!!");
			System.out.println("Make sure You have Reservation First! (If you haven't reserved, please choose menu 2)");
			System.out.println("1. Sign In");
			System.out.println("2. Exit");
			System.out.print(">> ");
			choice = sc.nextInt();sc.nextLine();
			switch(choice) {
			case 1:
				signIn();
				break;
			case 2:
				clrSc();
				logo();
				System.out.println("Thank you for useing our Application ! Have a nice Day");
				break;
			}
		}while(choice!=2);
	}

	private void signIn() {
		clrSc();
		String name;
		System.out.print("Enter your name that you used for reservation: ");
		name = sc.nextLine();
		int choice;
		System.out.println("Are you a Customer or Admin?");
		System.out.println("1. Customer");
		System.out.println("2. Admin");
		System.out.print(">> ");
		choice = sc.nextInt();sc.nextLine();
		if(choice==1) {
			int cnt=0;
			for (Reservation reservedName : reservationList) {
				if(reservedName.getCustomerName().equals(name)) {
					CustomerPage c = new CustomerPage(name,reservedName.getReservationId());
					cnt=1;
					break;
				}
			}
			if(cnt==0) {
				System.out.println("You are not in the reservation List, pleaser reserve first by choosing Make Reservation Menu");
			}
		}else if(choice==2){
			int cnt=0;
			String id;
			System.out.print("Enter your EmployeeID:");
			id = sc.nextLine();
			for (Employee employee : employeeList) {
				if(employee.getEmployeeName().equals(name) && employee.getEmployeeId().equals(id)) {
					AdminPage a = new AdminPage(name,employee.getEmployeeId());
					cnt=1;
					break;
				}
			}
			if(cnt==0) {
				System.out.println("You are not an admin");
			}
		}else {
			System.out.println("Invalid");
		}
		
	}

	public static void main(String[] args) {
		new Main();
	}

}
