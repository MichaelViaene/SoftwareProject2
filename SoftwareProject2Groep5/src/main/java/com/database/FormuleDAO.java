package com.database;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;



public class FormuleDAO {

    public String getFormuleActive() {
    	String form=new String();
    	try (Connection con = Database.getConnection()){
            String query = "SELECT * FROM Formule WHERE active=1";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	            try (ResultSet resultSet = preparedStatement.executeQuery()){
		            //login_id(int11),username(vchar45),passwoord(vchar64),bevoegdheid(int11),medewerker_id(int11)
		            while (resultSet.next()) {
		            	form=resultSet.getString("formule");
		            }
	            } catch (Exception ex) {
                    System.out.println(ex);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return form;
    }

    public static boolean insertFormule(String formule, boolean active) {
        try (Connection con = Database.getConnection()){
            if (formule != null) {
                String query = "INSERT INTO Formule (formule, active) VALUES (?,?);";
                con.setAutoCommit(false);
                try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	                //preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
	                preparedStatement.setString(1, formule);
	                preparedStatement.setBoolean(2, active);
	                preparedStatement.executeUpdate();
	                con.commit();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        } 
        return true;
    }

    public static boolean updateFormule(String formule, boolean active) {
        try (Connection con = Database.getConnection()){
            String query = "Update Formule SET active = ? WHERE formule = ? ";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	            preparedStatement.setBoolean(1, active);
	            preparedStatement.setString(2, formule);
	            preparedStatement.executeUpdate();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }    

    public static ArrayList<String>getAllFormules() {
        ArrayList<String> formules =new ArrayList<String>();
        try (Connection con = Database.getConnection()){
            String query = "SELECT * FROM formule";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	            try (ResultSet resultSet = preparedStatement.executeQuery()){
		            //login_id(int11),username(vchar45),passwoord(vchar64),bevoegdheid(int11),medewerker_id(int11)
		            while (resultSet.next()) {
		                formules.add(resultSet.getString("formule"));
		            }
	            }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return formules;
    }

}