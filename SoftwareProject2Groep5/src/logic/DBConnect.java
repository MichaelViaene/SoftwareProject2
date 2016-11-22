package logic;

import java.sql.*;
//test
public class DBConnect {

	private Statement st;
	private ResultSet rs;

	public DBConnect()
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con= DriverManager.getConnection("jdbc:mysql://DT-SRV-DT5/SP2GR5","SP2GR5","p783D");
			st = con.createStatement();

		} catch (Exception e1){ 
			e1.printStackTrace();
		}
	
	}

	public void getData() {
		try {
			String query = "select * from name";
			rs = st.executeQuery(query);
			System.out.println("INFORMATIE UIT DB");
			while (rs.next()) {
				String prenom = rs.getString("first");
				String nom = rs.getString("last");
				System.out.println("Voornaam: " + prenom + "   Achternaam: " + nom);
			}
		}

		catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
