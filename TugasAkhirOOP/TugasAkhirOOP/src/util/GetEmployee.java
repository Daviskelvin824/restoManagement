package util;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Employee;

public class GetEmployee {
	private static ArrayList<Employee> employeeList = new ArrayList<>();
	private static Connect con = Connect.getInstance();
	
	public static ArrayList<Employee> getEmployees(String query){
		employeeList.removeAll(employeeList);
		con.rs = con.execQuery(query);
		try {
			while (con.rs.next()) {

				String employeeId = con.rs.getString("EmployeeID");
				String name = con.rs.getString("EmployeeName");
				int restaurantId = Integer.parseInt(con.rs.getString("RestaurantID"));
				employeeList.add(new Employee(employeeId, name, restaurantId));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeeList;
	}
	
}
