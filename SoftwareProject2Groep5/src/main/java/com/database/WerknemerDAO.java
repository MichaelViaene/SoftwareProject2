package com.database;

import com.model.Login;
import com.model.Werknemer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Vik Mortier on 4/11/2016.
 */
public class WerknemerDAO {

    public static Werknemer getWerknemerById(int medewerker_id){

        Werknemer werknemer = new Werknemer();
        Login login = new Login();

        try {
            Connection con = Database.getConnection();
            if (con == null) {
                Database.openDatabase();
                con = Database.getConnection();
            }

            if (con != null) {
                String query = "SELECT * FROM Medewerker, Login WHERE Medewerker.medewerker_id = ? AND Medewerker.medewerker_id = Login.medewerker_id ";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, medewerker_id);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {

                    werknemer.setWerknemerId(resultSet.getInt("medewerker_id"));
                    werknemer.setActief(resultSet.getBoolean("actief"));
                    werknemer.setNaam(resultSet.getString("naam"));
                    werknemer.setVoornaam(resultSet.getString("voornaam"));
                    login.setUsername(resultSet.getString("username"));
                    login.setBevoegdheid(resultSet.getInt("bevoegdheid"));
                    login.setPassword(resultSet.getString("passwoord"));
                    login.setLogin_id(resultSet.getInt("login_id"));
                    werknemer.setLogin(login);
                }

                resultSet.close();
                preparedStatement.close();
                con.close();
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return werknemer;

    }

    public static Werknemer getWerknemerByLoginId(int loginId){

        Werknemer werknemer = new Werknemer();
        Login login = new Login();

        try {
            Connection con = Database.getConnection();
            if (con == null) {
                Database.openDatabase();
                con = Database.getConnection();
            }

            if (con != null) {
                String query = "SELECT * FROM Medewerker, Login WHERE Login.login_id = ? AND Medewerker.medewerker_id = Login.medewerker_id ";
                PreparedStatement preparedStatement = con.prepareStatement(query);

                preparedStatement.setInt(1, loginId);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {

                    werknemer.setWerknemerId(resultSet.getInt("medewerker_id"));
                    werknemer.setActief(resultSet.getBoolean("actief"));
                    werknemer.setNaam(resultSet.getString("naam"));
                    werknemer.setVoornaam(resultSet.getString("voornaam"));
                    login.setUsername(resultSet.getString("username"));
                    login.setBevoegdheid(resultSet.getInt("bevoegdheid"));
                    login.setPassword(resultSet.getString("passwoord"));
                    login.setLogin_id(resultSet.getInt("login_id"));
                    werknemer.setLogin(login);
                }

                resultSet.close();
                preparedStatement.close();
                con.close();
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return werknemer;
    }

    public static boolean insertWerknemer (Werknemer werknemer){

        try {
            Connection con = Database.getConnection();
            if (con == null) {
                Database.openDatabase();
                con = Database.getConnection();
            }

            if (con != null) {
                String query = "INSERT INTO Medewerker (actief, naam, voornaam)" + "values (?, ?, ?)" ;
                PreparedStatement preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

                preparedStatement.setBoolean(1, werknemer.isActief());
                preparedStatement.setString(2, werknemer.getNaam());
                preparedStatement.setString(3, werknemer.getVoornaam());

                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    werknemer.setWerknemerId(1);
                }

                resultSet.close();
                preparedStatement.close();
                con.close();

            }

        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public static boolean setWerknemerInactief(int medewerkerId, boolean actief){

        try {
            Connection con = Database.getConnection();
            if (con == null) {
                Database.openDatabase();
                con = Database.getConnection();
            }

            if (con != null) {
                String query = "UPDATE Medewerker SET actief = ?" + "WHERE medewerker_id = ?" ;
                PreparedStatement preparedStatement = con.prepareStatement(query);



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

}
