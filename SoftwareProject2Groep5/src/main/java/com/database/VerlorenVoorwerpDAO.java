package com.database;

import java.sql.*;
import java.util.ArrayList;
import com.model.*;

public class VerlorenVoorwerpDAO {

	public static ArrayList<VerlorenVoorwerp> getAll() {

		ArrayList<VerlorenVoorwerp> list = new ArrayList<>();

		try {
			Connection con = Database.getConnection();
			if (con == null) {
				Database.openDatabase();
				con = Database.getConnection();
			}

			Statement st = null;
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Verloren_voorwerpen WHERE aanwezig = 1;");

			while (rs.next()) {
				VerlorenVoorwerp voorwerp = new VerlorenVoorwerp();
				voorwerp.setVoorwerpid(rs.getInt("verloren_id"));
				voorwerp.setNaam(rs.getString("naam"));
				voorwerp.setOmschrijving(rs.getString("omschrijving"));
				voorwerp.setDatum(rs.getString("datum_aankomst"));
				voorwerp.setStation(rs.getString("station"));

				list.add(voorwerp);
			}
			st.close();
		}

		catch (Exception ex) {
			System.out.println(ex);
		}
		return list;

	}

	public static boolean controleId(int id) {

		if (id < 0) {
			return false;
		}

		try {
			Connection con = Database.getConnection();
			if (con == null) {
				Database.openDatabase();
				con = Database.getConnection();
			}

			Statement st = null;
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Verloren_voorwerpen WHERE verloren_id = " + id + ";");

			int controle = -1;
			if (rs.next()) {
				controle = rs.getInt(1);
			}

			if (controle == id)
				return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(1);
		}

		return false;
	}

	public static boolean deleteVoorwerp(int id) {

		if (id < 0)
			return false;
		if (controleId(id) == false) {
			return false;
		}

		try {
			Connection con = Database.getConnection();
			if (con == null) {
				Database.openDatabase();
				con = Database.getConnection();
			}

			PreparedStatement st = con
					.prepareStatement("UPDATE Verloren_voorwerpen SET aanwezig = 0 where verloren_id= ?");
			st.setInt(1, id);
			st.executeUpdate();
			con.commit();
			return true;
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + " : " + e.getMessage());
			System.exit(1);
		}
		return false;

	}

	public static VerlorenVoorwerp getVoorwerpPerId(int id) {
		if (id < 0)
			return null;
		if (controleId(id) == false) {
			return null;
		}

		try {
			Connection con = Database.getConnection();
			if (con == null) {
				Database.openDatabase();
				con = Database.getConnection();
			}

			Statement st = null;
			st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Verloren_voorwerpen WHERE verloren_id = " + id + ";");

			VerlorenVoorwerp voorwerp = new VerlorenVoorwerp();
			while (rs.next()) {
				voorwerp.setVoorwerpid(rs.getInt(1));
				voorwerp.setNaam(rs.getString(2));
				voorwerp.setOmschrijving(rs.getString(3));
				voorwerp.setDatum(rs.getString(4));
				voorwerp.setStation(rs.getString(5));
			}
			st.close();
			return voorwerp;
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + " : " + e.getMessage());
			System.exit(1);
		}
		return null;

	}

	public static boolean insertVoorwerp(VerlorenVoorwerp voorwerp) {
		if (voorwerp == null) {
			return false;
		}
		if (controleId(voorwerp.getVoorwerpid()) == true) {
			return false;

		}

		try {
			Connection con = Database.getConnection();
			if (con == null) {
				Database.openDatabase();
				con = Database.getConnection();
			}

			PreparedStatement preparedPush = null;
			String pushStatement = "INSERT INTO Verloren_voorwerpen (naam, omschrijving, datum_aankomst,aanwezig,station) VALUES (?,?,?,?,?);";

			con.setAutoCommit(false);

			preparedPush = con.prepareStatement(pushStatement);

			preparedPush.setString(1, voorwerp.getNaam());
			preparedPush.setString(2, voorwerp.getOmschrijving());
			preparedPush.setString(3, voorwerp.getDatum());
			preparedPush.setBoolean(4, true);
			preparedPush.setString(5, voorwerp.getStation());
			preparedPush.executeUpdate();

			preparedPush.close();
			con.commit();

		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(1);
		}
		return false;
	}

	public static ArrayList<VerlorenVoorwerp> getVoorwerpByStation(String station) {

		ArrayList<VerlorenVoorwerp> list = new ArrayList<>();

		try {
			Connection con = Database.getConnection();
			if (con == null) {
				Database.openDatabase();
				con = Database.getConnection();
			}

			String query = "SELECT * FROM Verloren_voorwerpen WHERE station=?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, station);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				VerlorenVoorwerp voorwerp = new VerlorenVoorwerp();
				voorwerp.setVoorwerpid(rs.getInt("verloren_id"));
				voorwerp.setNaam(rs.getString("naam"));
				voorwerp.setOmschrijving(rs.getString("omschrijving"));
				voorwerp.setDatum(rs.getString("datum_aankomst"));
				voorwerp.setStation(rs.getString("station"));

				list.add(voorwerp);
			}
			rs.close();
			preparedStatement.close();

		}

		catch (Exception ex) {
			System.out.println(ex);
		}
		return list;

	}

}
