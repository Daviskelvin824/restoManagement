package util;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import model.Menu;

public class GetMenu {
	private static Connect con = Connect.getInstance();
	private static ArrayList<Menu> menuList = new ArrayList<>();
	private static ArrayList<Menu> menuListCnt = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);
	public static ArrayList<Menu> GetMenus(String query){
		menuList.removeAll(menuList);
		con.rs = con.execQuery(query);
		try {
			while (con.rs.next()) {

				String menuId = con.rs.getString("MenuID");
				String name = con.rs.getString("MenuName");
				String type = con.rs.getString("MenuType");
				String desc = con.rs.getString("MenuDescription");
				String location = con.rs.getString("MenuLocation");
				int price = Integer.parseInt(con.rs.getString("Price"));
				menuList.add(new Menu(menuId, name, type, desc, location, price));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuList;
		
	}
	public static ArrayList<Menu> GetMenus2(String query){
		menuListCnt.removeAll(menuListCnt);
		con.rs = con.execQuery(query);
		try {
			while (con.rs.next()) {

				String menuId = con.rs.getString("MenuID");
				String name = con.rs.getString("MenuName");
				String type = con.rs.getString("MenuType");
				String desc = con.rs.getString("MenuDescription");
				String location = con.rs.getString("MenuLocation");
				int price = Integer.parseInt(con.rs.getString("Price"));
				menuListCnt.add(new Menu(menuId, name, type, desc, location, price));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuListCnt;
		
	}
	
	public static void insertMenu(String query) {
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
	
	public static void updateMenu(String updateQuery) {
	    Statement statement = null;
	    try {
	        statement = con.getStatement();
	        int rowsAffected = statement.executeUpdate(updateQuery);

	        if (rowsAffected > 0) {
	            System.out.println("Menu updated successfully!");
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
	
	public static void deleteMenu(String delQuery) {
	    Statement statement = null;
	    try {
	        statement = con.getStatement();
	        int rowsAffected = statement.executeUpdate(delQuery);

	        if (rowsAffected > 0) {
	            System.out.println("Menu deleted successfully!");
	            System.out.println("Press enter to continue..."); sc.nextLine();
	        } else {
	            System.out.println("Failed to delete order.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        con.closeStatement(statement);
	    }
	}
	
}
