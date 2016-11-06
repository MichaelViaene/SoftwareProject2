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
			ResultSet rs = st.executeQuery("SELECT * FROM Verloren_voorwerpen;");

			while (rs.next()) {
				VerlorenVoorwerp voorwerp = new VerlorenVoorwerp();
				voorwerp.setVoorwerpid(rs.getInt("verloren_id"));
				voorwerp.setNaam(rs.getString("naam"));
				voorwerp.setOmschrijving(rs.getString("omschrijving"));
				voorwerp.setDatum(rs.getString("datum_aankomst"));
				voorwerp.setAanwezig(rs.getBoolean("aanwezig"));
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

	public static boolean voorwerpGevonden(int id) {

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

			PreparedStatement setGevonden = null;
			String update = "UPDATE Verloren_voorwerpen set aanwezig=0 WHERE verloren_id =?;";

			con.setAutoCommit(false);

			setGevonden = con.prepareStatement(update);

			setGevonden.setInt(1, id);

			setGevonden.executeUpdate();

			con.commit();
			return true;
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + " : " + e.getMessage());
			System.exit(1);
		}

		return false;
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
			String pushStatement = "INSERT INTO Verloren_voorwerpen (naam, omschrijving, datum_aankomst, aanwezig, station) VALUES (?,?,?,?,?);";

			con.setAutoCommit(false);

			preparedPush = con.prepareStatement(pushStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			preparedPush.setString(1, voorwerp.getNaam());
			preparedPush.setString(2, voorwerp.getOmschrijving());
			preparedPush.setString(3, voorwerp.getDatum());
			preparedPush.setBoolean(4, voorwerp.getAanwezig());
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
				voorwerp.setAanwezig(rs.getBoolean("aanwezig"));
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
