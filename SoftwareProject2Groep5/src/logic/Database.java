package logic;

import java.sql.*;

public class Database {

	private static Statement st;
	private static ResultSet rs;
	private static Connection con = null;

	public static void openDatabase() {

		if (con == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://DT-SRV-DT5/SP2GR5", "SP2GR5", "p783D");

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void closeDatabase() {
		if (con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}
	}
	
	public static Connection getConnection() {
		return con;
	}

	/*
	 * public void getData() { try { String query = "select * from name"; rs =
	 * st.executeQuery(query); System.out.println("INFORMATIE UIT DB"); while
	 * (rs.next()) { String prenom = rs.getString("first"); String nom =
	 * rs.getString("last"); System.out.println("Voornaam: " + prenom +
	 * "   Achternaam: " + nom); } }
	 * 
	 * catch (Exception ex) { System.out.println(ex); } }
	 */
}
