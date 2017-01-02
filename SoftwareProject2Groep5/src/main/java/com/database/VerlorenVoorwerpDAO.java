package com.database;

/**
*
* @author Ilias El Mesaoudi
**/

import java.sql.*;
import java.util.ArrayList;
import com.model.*;

public class VerlorenVoorwerpDAO {

	public static ArrayList<VerlorenVoorwerp> getAll() {

		ArrayList<VerlorenVoorwerp> list = new ArrayList<>();
		try (Connection con = DataSource.getConnection()){
			try (Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM Verloren_voorwerpen WHERE aanwezig = 1;")){
				while (rs.next()) {
					VerlorenVoorwerp voorwerp = new VerlorenVoorwerp();
					voorwerp.setVoorwerpid(rs.getInt("verloren_id"));
					voorwerp.setNaam(rs.getString("naam"));
					voorwerp.setOmschrijving(rs.getString("omschrijving"));
					voorwerp.setDatum(rs.getDate("datum_aankomst"));
					voorwerp.setStation(rs.getString("station"));
	
					list.add(voorwerp);
				}
			} catch (Exception ex) {
                System.out.println(ex);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public static boolean deleteVoorwerp(int id) {

		if (id < 0)
			return false;
		try (Connection con = DataSource.getConnection()){
			try (PreparedStatement st = con.prepareStatement("UPDATE Verloren_voorwerpen SET aanwezig = 0 where verloren_id= ?")){
				st.setInt(1, id);
				st.executeUpdate();
				return true;
			} catch (Exception ex) {
                System.out.println(ex);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static VerlorenVoorwerp getVoorwerpPerId(int id) {
		if (id < 0)
			return null;
		try (Connection con = DataSource.getConnection()){
			try(Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM Verloren_voorwerpen WHERE verloren_id = " + id + ";")){
				VerlorenVoorwerp voorwerp = new VerlorenVoorwerp();
				while (rs.next()) {
					voorwerp.setVoorwerpid(rs.getInt(1));
					voorwerp.setNaam(rs.getString(2));
					voorwerp.setOmschrijving(rs.getString(3));
					voorwerp.setDatum(rs.getDate(4));
					voorwerp.setStation(rs.getString(5));
				}
				return voorwerp;
			} catch (Exception ex) {
                System.out.println(ex);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static boolean insertVoorwerp(VerlorenVoorwerp voorwerp) {
		if (voorwerp == null) {
			return false;
		}
		try (Connection con = DataSource.getConnection()){
			String pushStatement = "INSERT INTO Verloren_voorwerpen (naam, omschrijving, datum_aankomst,aanwezig,station) VALUES (?,?,?,?,?);";
			con.setAutoCommit(false);
			try (PreparedStatement preparedPush = con.prepareStatement(pushStatement)){
	
				preparedPush.setString(1, voorwerp.getNaam());
				preparedPush.setString(2, voorwerp.getOmschrijving());
				preparedPush.setDate(3, voorwerp.getDatum());
				preparedPush.setBoolean(4, true);
				preparedPush.setString(5, voorwerp.getStation());
				preparedPush.executeUpdate();
				con.commit();
				return true;
			} catch (Exception ex) {
                System.out.println(ex);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} return false;
	}

	public static ArrayList<VerlorenVoorwerp> getVoorwerpByStation(String station) {

		ArrayList<VerlorenVoorwerp> list = new ArrayList<>();
		try (Connection con = DataSource.getConnection()){
			String query = "SELECT * FROM Verloren_voorwerpen WHERE station=? AND aanwezig=true";
			try (PreparedStatement preparedStatement = con.prepareStatement(query)){
				preparedStatement.setString(1, station);
				try (ResultSet rs = preparedStatement.executeQuery()){
		
					while (rs.next()) {
						VerlorenVoorwerp voorwerp = new VerlorenVoorwerp();
						voorwerp.setVoorwerpid(rs.getInt("verloren_id"));
						voorwerp.setNaam(rs.getString("naam"));
						voorwerp.setOmschrijving(rs.getString("omschrijving"));
						voorwerp.setDatum(rs.getDate("datum_aankomst"));
						voorwerp.setStation(rs.getString("station"));
		
						list.add(voorwerp);
					}
				}
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public static boolean updateVoorwerp(VerlorenVoorwerp voorwerp) {
		if (voorwerp == null) {
			return false;
		}
		try (Connection con = DataSource.getConnection()){
			String pushStatement = "UPDATE Verloren_voorwerpen SET naam=?, omschrijving=?, datum_aankomst=?, station=? WHERE verloren_id=?;";

			con.setAutoCommit(false);
			try (PreparedStatement update = con.prepareStatement(pushStatement)){
				update.setString(1, voorwerp.getNaam());
				update.setString(2, voorwerp.getOmschrijving());
				update.setDate(3, voorwerp.getDatum());
				update.setString(4, voorwerp.getStation());
				update.setInt(5, voorwerp.getVoorwerpid());
	
				int aantalVeranderingen = update.executeUpdate();
				con.commit();
	
				if (aantalVeranderingen == 1)
					return true;
				return false;
			} catch (Exception ex) {
                 System.out.println(ex);
             }
		} catch (SQLException e) {
			e.printStackTrace();
		} return false;
	}

}