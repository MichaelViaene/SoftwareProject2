package com.database;

import java.sql.*;

public class Database {
	
	public static Connection getConnection() {

		Connection con = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://DT-SRV-DT5/SP2GR5", "SP2GR5", "p783D");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static boolean testConn(){
		boolean test = false;
		try {
			Connection con = getConnection();
			if (con != null) {
				test = true;
			}
			else test = false;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return test;
	}
// Legacy code, mag weg indien er geen problemen meer zijn -Jenne	
/*
	private static Connection con = null;

	public static Connection getConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://DT-SRV-DT5/SP2GR5", "SP2GR5", "p783D");
		} catch(Exception e){}

		return null;
	}

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

	public static boolean testConn(){
		boolean test = false;
		try {
			openDatabase();
			if (con != null) {
				test = true;
			}
			else test = false;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return test;
	}
*/
}
