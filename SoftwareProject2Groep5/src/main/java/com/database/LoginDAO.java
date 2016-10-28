package com.database;

import java.sql.*;

import com.model.Login;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

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
				String query = "SELECT * FROM Login WHERE username=?";
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, username);
				resultSet = preparedStatement.executeQuery();
				//login_id(int11),username(vchar45),passwoord(vchar45),bevoegdheid(int11),salt(vchar45)
				while (resultSet.next()) {
					login.setLogin_id(resultSet.getInt("login_id"));
					login.setUsername(resultSet.getString("username"));
					login.setPassword(resultSet.getString("passwoord"));
					login.setSalt(resultSet.getString("salt"));
					login.setBevoegdheid(resultSet.getInt("bevoegdheid"));
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
}