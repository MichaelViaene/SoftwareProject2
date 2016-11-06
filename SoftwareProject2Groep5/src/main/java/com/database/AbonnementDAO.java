package com.database;

import com.model.Abonnement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Vik Mortier on 30/10/2016.
 */
public class AbonnementDAO {

    public static boolean writeAbonnement(Abonnement abonnement) {


        try {
            Connection con = Database.getConnection();
            if (con == null) {
                Database.openDatabase();
                con = Database.getConnection();
            }

            if (con != null) {

                //Waar word de medewerker geschreven die dit abo aanmaakt? Bestaan er geen andere types zoals bvb zone brussel. Functie nodig om de id v/d klant op te halen.

                String query = "INSERT INTO Abonnement (klant_id, begindatum, einddatum, actief, klasse, vertrek_station, aankomst_station, type, prijs)" + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, 5); //HARDCODED TIJDELIJK
                preparedStatement.setObject(2, abonnement.getBeginDatum());
                preparedStatement.setObject(3, abonnement.getEindDatum());
                preparedStatement.setInt(4, abonnement.getActief());
                preparedStatement.setInt(5, abonnement.getKlasse());
                preparedStatement.setString(6, abonnement.getBeginStation());
                preparedStatement.setString(7, abonnement.getEindStation());
                preparedStatement.setInt(8, abonnement.getType());
                preparedStatement.setDouble(9, abonnement.getPrijs());

                preparedStatement.execute();
                preparedStatement.close();
                con.close();

            }

        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public static boolean checkAbonnement(int klant_id){
        //checkt voor een bestaand abonnement van een bepaald klantID.

        Boolean check = false;

        try {
            Connection con = Database.getConnection();
            if (con == null) {
                Database.openDatabase();
                con = Database.getConnection();
            }

            if (con != null) {
                String query = "SELECT * FROM Abonnement WHERE klant_id= ?";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, klant_id);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    check = true;
                }

                resultSet.close();
                preparedStatement.close();
                con.close();
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return check;
    }
}
