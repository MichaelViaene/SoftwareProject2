package com.database;

import com.model.Adres;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Jordan Vander Elst
 */
public class AdresDAO {
    public ArrayList<Adres> getAll() {
        ArrayList<Adres> adresList = new ArrayList<>();
        ResultSet rs = null;
        Statement st = null;
        try {
            Connection con = Database.getConnection();
            if (con == null) {
                Database.openDatabase();
                con = Database.getConnection();
            }

            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM Adres;");

            while (rs.next()) {
                Adres adres = new Adres();
                adres.setAdres_id(rs.getInt("adres_id"));
                adres.setStad(rs.getString("stad"));
                adres.setGemeente(rs.getString("gemeente"));
                adres.setStraat(rs.getString("straat"));
                adres.setHuisnr(rs.getInt("huisnr"));
                adres.setBrievenbus(rs.getString("brievenbus"));
                adres.setPostcode(rs.getInt("postcode"));

                adresList.add(adres);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {System.out.println(ex);}
            }

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {System.out.println(ex);}
            }

        }
        return adresList;
    }
}
