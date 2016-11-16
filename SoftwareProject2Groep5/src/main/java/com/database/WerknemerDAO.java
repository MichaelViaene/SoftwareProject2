package com.database;

import com.model.Login;
import com.model.Werknemer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vik Mortier on 4/11/2016.
 */
public class WerknemerDAO {

    public static boolean deleteWerknemer (Werknemer werknemer) {

        try {
            Database.openDatabase();
            Connection con = Database.getConn();

            if(con == null){
                Database.openDatabase();
                con = Database.getConnection();
            }

            if(con != null){
                String query = "Update Medewerker SET actief = ? WHERE medewerker_id = ? ";
                PreparedStatement preparedStatement = con.prepareStatement(query);

                preparedStatement.setBoolean(1, werknemer.isActief());
                preparedStatement.setInt(2, werknemer.getWerknemerId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
                con.close();

            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static boolean updateWerknemer (Werknemer werknemer) {

        try {
            Database.openDatabase();
            Connection con = Database.getConn();

            if(con == null){
                Database.openDatabase();
                con = Database.getConnection();
            }

            if(con != null){
                String query = "Update Medewerker SET actief = ?, naam = ?, voornaam = ? WHERE medewerker_id = ? ";
                PreparedStatement preparedStatement = con.prepareStatement(query);

                preparedStatement.setBoolean(1, werknemer.isActief());
                preparedStatement.setString(2, werknemer.getNaam());
                preparedStatement.setString(3, werknemer.getVoornaam());
                preparedStatement.setInt(4, werknemer.getWerknemerId());

                preparedStatement.executeUpdate();
                preparedStatement.close();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean insertWerknemer(Werknemer werknemer) {

        try {
            Database.openDatabase();
            Connection con = Database.getConn();

            if(con == null){
                Database.openDatabase();
                con = Database.getConnection();
            }

            if(con != null){
                String query = "INSERT INTO Medewerker (actief, naam, voornaam)" + "VALUES (?, ?, ?) ";

                PreparedStatement preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

                preparedStatement.setBoolean(1, werknemer.isActief());
                preparedStatement.setString(2, werknemer.getNaam());
                preparedStatement.setString(3, werknemer.getVoornaam());

                preparedStatement.execute();

                ResultSet resultset = preparedStatement.getGeneratedKeys();
                resultset.next();
                werknemer.setWerknemerId(resultset.getInt(1));


                resultset.close();
                preparedStatement.close();
                con.close();
            }

        } catch(Exception ex){
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public static List<Werknemer> getAllWerknemers () {

        List<Werknemer> werknemers = new ArrayList<>();
        ResultSet resultSet;
        PreparedStatement preparedStatement;

        try {
            Connection con = Database.getConn();
            if (con == null) {
                Database.openDatabase();
                con = Database.getConnection();
            }

            if (con != null) {
                String query = "SELECT * FROM Medewerker, Login WHERE Medewerker.medewerker_id = Login.medewerker_id ";
                preparedStatement = con.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Werknemer werknemer = new Werknemer();
                    Login login = new Login();

                    werknemer.setWerknemerId(resultSet.getInt("medewerker_id"));
                    werknemer.setActief(resultSet.getBoolean("actief"));
                    werknemer.setNaam(resultSet.getString("naam"));
                    werknemer.setVoornaam(resultSet.getString("voornaam"));
                    login.setUsername(resultSet.getString("username"));
                    login.setBevoegdheid(resultSet.getInt("bevoegdheid"));
                    login.setPassword(resultSet.getString("passwoord"));
                    login.setLogin_id(resultSet.getInt("login_id"));
                    werknemer.setLogin(login);
                    werknemers.add(werknemer);
                }

                resultSet.close();
                preparedStatement.close();
                con.close();
            }

        } catch (Exception ex){
            System.out.println(ex);
        }

        return werknemers;
    }

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

    public static Werknemer getWerkById(int medewerker_id) {
        Werknemer werknemer = new Werknemer();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            Connection con = Database.getConnection();
            if (con == null) {
                Database.openDatabase();
                con = Database.getConnection();
            }
            if (con != null) {
                String query = "SELECT * FROM Medewerker WHERE medewerker_id=? LIMIT 1";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, medewerker_id);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    werknemer.setWerknemerId(resultSet.getInt("medewerker_id"));
                    werknemer.setActief(resultSet.getBoolean("actief"));
                    werknemer.setNaam(resultSet.getString("naam"));
                    werknemer.setVoornaam(resultSet.getString("voornaam"));
                }
                resultSet.close();
                preparedStatement.close();
                //con.close();
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return werknemer;
    }

}
