package com.database;

import com.ehbrail.WerknemerController;
import com.model.Abonnement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Vik Mortier on 30/10/2016.
 */
public class AbonnementDAO {

    public static boolean writeAbonnement(Abonnement abonnement) {
        try (Connection con = DataSource.getConnection()){
            if (con != null) {
                //Waar word de medewerker geschreven die dit abo aanmaakt? Bestaan er geen andere types zoals bvb zone brussel. Functie nodig om de id v/d klant op te halen.

                String query = "INSERT INTO Abonnement (klant_id, begindatum, einddatum, actief, klasse, vertrek, aankomst, prijs,medewerker_id,station,korting_id)" + "values (?,?,?,?, ?, ?, ?, ?, ?, ?, ?)" ;
                try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	                preparedStatement.setInt(1, 5); //HARDCODED TIJDELIJK
	                preparedStatement.setObject(2, abonnement.getBeginDatum());
	                preparedStatement.setObject(3, abonnement.getEindDatum());
	                preparedStatement.setInt(4, abonnement.getActief());
	                preparedStatement.setInt(5, abonnement.getKlasse());
	                preparedStatement.setString(6, abonnement.getBeginStation());
	                preparedStatement.setString(7, abonnement.getEindStation());
	                preparedStatement.setDouble(8, abonnement.getPrijs());
	                preparedStatement.setInt(9, abonnement.getMedewerker_id()); //HARDCODED TIJDELIJK
	                preparedStatement.setString(10, abonnement.getStation()); //HARDCODED TIJDELIJK
	                preparedStatement.setInt(11, abonnement.getKorting_id()); //HARDCODED
	                
	                
	                preparedStatement.execute();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        } return true;
    }

    public static boolean checkAbonnement(int klant_id){
        //checkt voor een bestaand abonnement van een bepaald klantID.

        Boolean check = false;

        try (Connection con = DataSource.getConnection()){
            if (con != null) {
                String query = "SELECT * FROM Abonnement WHERE klant_id= ?";
                try(PreparedStatement preparedStatement = con.prepareStatement(query)){
	                preparedStatement.setInt(1, klant_id);
	                try (ResultSet resultSet = preparedStatement.executeQuery()){
		                while (resultSet.next()) {
		                    check = true;
		                }
	                } catch (Exception ex) {
	                    System.out.println(ex);
	                }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } return check;
    }
}
