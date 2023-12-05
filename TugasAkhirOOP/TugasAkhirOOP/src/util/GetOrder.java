package util;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import model.Order;

public class GetOrder {
	private static ArrayList<Order> orderList = new ArrayList<>();
	private static Connect con = Connect.getInstance();
	static Scanner sc = new Scanner(System.in);
	public static ArrayList<Order> GetOrders(String query){
		orderList.removeAll(orderList);
		con.rs = con.execQuery(query);
		try {
			while (con.rs.next()) {

				String menuId = con.rs.getString("MenuID");
				int quantity = Integer.parseInt(con.rs.getString("Quantity"));
				String orderStatus = con.rs.getString("OrderStatus");
				String customerName = con.rs.getString("CustomerName");
				orderList.add(new Order(customerName,menuId, quantity,orderStatus));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}
	
	public static void insertOrder(String query) {
        Statement statement = null;
        try {
            statement = con.getStatement();
            int rowsAffected = statement.executeUpdate(query);

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully!");
                System.out.println("Press enter to continue..."); sc.nextLine();
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            con.closeStatement(statement);
        }
    }
	
	public static void updateOrder(String updateQuery) {
	    Statement statement = null;
	    try {
	        statement = con.getStatement();
	        int rowsAffected = statement.executeUpdate(updateQuery);

	        if (rowsAffected > 0) {
	            System.out.println("Order updated successfully!");
	            System.out.println("Press enter to continue..."); sc.nextLine();
	        } else {
	            System.out.println("Failed to update order.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        con.closeStatement(statement);
	    }
	}

}
