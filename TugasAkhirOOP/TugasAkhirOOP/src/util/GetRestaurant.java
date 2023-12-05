package util;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Restaurant;

public class GetRestaurant {
	private static ArrayList<Restaurant> restaurantList = new ArrayList<>();
	private static Connect con = Connect.getInstance();
	
	
	public static ArrayList<Restaurant> GetResto(String query) {
		restaurantList.removeAll(restaurantList);
		con.rs = con.execQuery(query);
		try {
			while (con.rs.next()) {

				String name = con.rs.getString("RestaurantName");
				int id = Integer.parseInt(con.rs.getString("RestaurantID"));
				restaurantList.add(new Restaurant(id, name));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return restaurantList;
	}	
	
	
}
