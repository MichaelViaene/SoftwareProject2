package com.database;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.model.Login;
import com.mysql.jdbc.*;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.log.Log;

public class LoginDAO {

	public Login getLoginByUsername(String username) {
		Login login = new Login();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection con = null;
		try {
			 con = Database.getConn();
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

			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {System.out.println(ex);}
			}

		}
		return login;
	}

	public static boolean insertLogin(Login login) {
		PreparedStatement preparedStatement = null;

		try {
			Connection con = Database.getConn();
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
				con.close();
			}
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}

		return true;
	}

	public static boolean updateLoginBevoegdheid(Login login){

		try {
			Database.openDatabase();
			Connection con = Database.getConn();

			if(con == null){
				Database.openDatabase();
				con = Database.getConnection();
			}

			if(con != null){
				String query = "Update Login SET bevoegdheid = ? WHERE medewerker_id = ? ";
				PreparedStatement preparedStatement = con.prepareStatement(query);

				preparedStatement.setInt(1, login.getBevoegdheidInt());
				preparedStatement.setInt(2, login.getMedewerker_id());

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

	public static boolean changePassbyMedewerker (Login login){

		try{

			Connection con = Database.getConn();

			if (con == null) {
			Database.openDatabase();
			con = Database.getConnection();
			}

			PreparedStatement st = con.prepareStatement("UPDATE Login SET passwoord = ? WHERE medewerker_id= ?");
			st.setString(1, login.getPassword());
			st.setInt(2, login.getMedewerker_id());
			st.executeUpdate();
			} catch (SQLException e) {
			System.err.println(e.getClass().getName() + " : " + e.getMessage());
			System.exit(1);
			return false;
		}

		return true;

	}

	public static boolean changePassbyLogin(Login login, String HashedPass) {
		try {
			Connection con = Database.getConn();
			if (con == null) {
				Database.openDatabase();
				con = Database.getConnection();
			}
			PreparedStatement st = con.prepareStatement("UPDATE Login SET passwoord = ? WHERE login_id= ?");
			st.setString(1, HashedPass);
			st.setInt(2, login.getLogin_id());
			st.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + " : " + e.getMessage());
			System.exit(1);
			return false;
		}

		return true;
	}

	public static Login getLoginByID(int id) {
		Login login = new Login();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Connection con = null;
		try {
			 con = Database.getConn();
			if (con == null) {
				Database.openDatabase();
				con = Database.getConnection();
			}
			if (con != null) {
				String query = "SELECT * FROM Login WHERE login_id=? LIMIT 1";
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1,id);
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
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {System.out.println(ex);}
			}
		}
		return login;
	}


}