package com.database;

import java.sql.*;
import java.util.ArrayList;

import com.model.Klant;
import com.model.VerlorenVoorwerp;

/**
 *
 * @author Ilias El Mesaoudi created 18-11-2016
 **/

public class KlantDAO {

	public static boolean insertKlant(Klant klant) {
		if (klant == null) {
			return false;
		}
		try (Connection con = Database.getConnection()){
			String pushStatement = "INSERT INTO Klant (adres_id, geboortedatum,gsmnummer,commentaar,actief,naam,voornaam) VALUES (?,?,?,?,?,?,?);";
			con.setAutoCommit(false);
			try (PreparedStatement preparedPush = con.prepareStatement(pushStatement)) {
				preparedPush.setInt(1, klant.getAdresid());
				preparedPush.setObject(2, klant.getGeboortedatum());
				preparedPush.setString(3, klant.getGsmnummer());
				preparedPush.setString(4, klant.getCommentaar());
				preparedPush.setBoolean(5, klant.isActief());
				preparedPush.setString(6, klant.getNaam());
				preparedPush.setString(7, klant.getVoornaam());

				preparedPush.executeUpdate();
				con.commit();
			}
			catch (Exception ex) {
				System.out.println(ex);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static ArrayList<Klant> getAll() {

		ArrayList<Klant> list = new ArrayList<>();
		try (Connection con = Database.getConnection()){
			try (Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM Klant")){
				while (rs.next()) {
					Klant klant = new Klant();
					klant.setKlantid(rs.getInt("klant_id"));
					klant.setVoornaam(rs.getString("voornaam"));
					klant.setNaam(rs.getString("naam"));
					list.add(klant);
				}
			} catch (Exception ex) {
                System.out.println(ex);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}


}