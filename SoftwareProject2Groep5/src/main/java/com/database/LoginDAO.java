package com.database;

import java.sql.*;

import com.model.Login;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.log.Log;

public class LoginDAO {

	public Login getLoginByUsername(String username) {
		Login login = new Login();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			Connection con = Database.getConnection();
			if (con == null) {
				Database.openDatabase();
				con = Database.getConnection();
			}
			if (con != null) {
				String query = "SELECT * FROM Login WHERE username=? LIMIT 1";
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, username);
				resultSet = preparedStatement.executeQuery();
				//login_id(int11),username(vchar45),passwoord(vchar64),bevoegdheid(int11),medewerker_id(int11)
				while (resultSet.next()) {
					login.setLogin_id(resultSet.getInt("login_id"));
					login.setUsername(resultSet.getString("username"));
					login.setPassword(resultSet.getString("passwoord"));
					login.setBevoegdheid(resultSet.getInt("bevoegdheid"));
					login.setMedewerker_id(resultSet.getInt("medewerker_id"));
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {System.out.println(ex);}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {System.out.println(ex);}
			}
		}
		return login;
	}

	public void insertLogin(Login login) {
		PreparedStatement preparedStatement = null;
		LoginDAO loginDAO = new LoginDAO();
		try {
			Connection con = Database.getConnection();
			if (con == null) {
				Database.openDatabase();
				con = Database.getConnection();
			}
			if (login != null) {
				String query = "INSERT INTO Login (login_id,username,passwoord,bevoegdheid,medewerker_id) VALUES (NULL,?,?,?,?);";
				con.setAutoCommit(false);
				//preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, login.getUsername());
				preparedStatement.setString(2, login.getPassword());
				preparedStatement.setInt(3, login.getBevoegdheidInt());
				preparedStatement.setInt(4, login.getMedewerker_id());
				preparedStatement.executeUpdate();
				preparedStatement.close();
				con.commit();
			}
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

//TODO update methode
}