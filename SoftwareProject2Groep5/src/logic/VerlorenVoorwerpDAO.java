package logic;

import java.sql.*;
import java.util.ArrayList;

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
				voorwerp.setPersoonid(rs.getInt("verloren_id"));
				voorwerp.setNaam(rs.getString("naam"));
				voorwerp.setOmschrijving(rs.getString("omschrijving"));
				voorwerp.setDatum(rs.getString("datum_aankomst"));
				voorwerp.setPersoonid(rs.getInt("persoon_id"));
				voorwerp.setAanwezig(rs.getBoolean("aanwezig"));

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

	public static boolean insertVoorwerp(VerlorenVoorwerp voorwerp){
		if (voorwerp == null) {
			return false;
		}
		if (controleId(voorwerp.getVoorwerpid())==true) {
			return false;
			
		}
		
		try {
			Connection con = Database.getConnection();
			if (con == null) {
				Database.openDatabase();
				con = Database.getConnection();
			}
		
		PreparedStatement preparedPush= null;
		String pushStatement = "INSERT INTO Verloren_voorwerpen (verloren_id, naam, omschrijving, datum_aankomst, persoon_id, aanwezig) VALUES (?,?,?,?,?,?);";
		
		con.setAutoCommit(false);
		
		preparedPush = con.prepareStatement(pushStatement,PreparedStatement.RETURN_GENERATED_KEYS);

		
		preparedPush.setInt(1, voorwerp.getVoorwerpid());
		preparedPush.setString(2, voorwerp.getNaam());
		preparedPush.setString(3, voorwerp.getOmschrijving());
		preparedPush.setString(4, voorwerp.getDatum());
		preparedPush.setInt(5, voorwerp.getPersoonid());
		preparedPush.setBoolean(6, voorwerp.getAanwezig());
		
		preparedPush.executeUpdate();
		
		preparedPush.close();
		con.commit();
		
		
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(1);
		}				
		return false;
	}

}
