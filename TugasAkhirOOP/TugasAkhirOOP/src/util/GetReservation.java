package util;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import model.Reservation;

public class GetReservation {
	static Scanner sc = new Scanner(System.in);
	private static ArrayList<Reservation> reservationList = new ArrayList<>();
	private static Connect con = Connect.getInstance();
	public static ArrayList<Reservation> GetReservations(String query){
		reservationList.removeAll(reservationList);
		con.rs = con.execQuery(query);
		try {
			while (con.rs.next()) {

				String reservationId = con.rs.getString("ReservationID");
				String employeeId = con.rs.getString("EmployeeID");
				String name = con.rs.getString("CustomerName");
				String type = con.rs.getString("TableType");
				int quantity = Integer.parseInt(con.rs.getString("Quantity"));
				reservationList.add(new Reservation(reservationId, employeeId, name, type, quantity));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservationList;
	}
	
	public static void insertReservation(String query) {
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
}
