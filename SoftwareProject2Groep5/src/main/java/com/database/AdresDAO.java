package com.database;

import com.model.Adres;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void insertAdres(Adres adres) {
        PreparedStatement preparedStatement = null;
        AdresDAO adresDAO = new AdresDAO();
        try {
            Connection con = Database.getConnection();
            if (con == null) {
                Database.openDatabase();
                con = Database.getConnection();
            }
            if (adres != null) {
                //Check of adres al in de DB zit
                List<Adres> adresList = adresDAO.checkAdres(adres);
                if (adresList.size() == 0) {

                    String query = "INSERT INTO Adres (adres_id,stad,gemeente,straat,huisnr,brievenbus,postcode) VALUES (NULL,?,?,?,?,?,?);";
                    con.setAutoCommit(false);
                    //preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                    preparedStatement = con.prepareStatement(query);

                    preparedStatement.setString(1, adres.getStad());
                    preparedStatement.setString(2, adres.getGemeente());
                    preparedStatement.setString(3, adres.getStraat());
                    preparedStatement.setInt(4, adres.getHuisnr());
                    preparedStatement.setString(5, adres.getBrievenbus());
                    preparedStatement.setInt(6, adres.getPostcode());

                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    con.commit();
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public ArrayList<Adres> checkAdres(Adres adres) throws SQLException{
        ArrayList<Adres> adresList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            Connection con = Database.getConnection();
            if (con == null) {
                Database.openDatabase();
                con = Database.getConnection();
            }

            String sql = "SELECT * FROM Adres WHERE stad= ? AND gemeente= ? AND straat=? AND huisnr=? AND brievenbus=? AND postcode=?;";
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1,adres.getStad());
            preparedStatement.setString(2,adres.getGemeente());
            preparedStatement.setString(3,adres.getStraat());
            preparedStatement.setInt(4,adres.getHuisnr());
            preparedStatement.setString(5,adres.getBrievenbus());
            preparedStatement.setInt(6,adres.getPostcode());
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Adres adres1 = new Adres();
                adres1.setAdres_id(rs.getInt("adres_id"));
                adres1.setStad(rs.getString("stad"));
                adres1.setGemeente(rs.getString("gemeente"));
                adres1.setStraat(rs.getString("straat"));
                adres1.setHuisnr(rs.getInt("huisnr"));
                adres1.setBrievenbus(rs.getString("brievenbus"));
                adres1.setPostcode(rs.getInt("postcode"));

                adresList.add(adres1);
            }
            if (adresList.size() > 0){
                System.out.println("Dit adres stond al in de DB en werd dus niet toegevoegd!! Met ID: ");
                adresList.forEach(adres1 -> System.out.println(adres1.getAdres_id()));
            }
            else System.out.println("Gz dit adres bestond nog niet en is toegevoegd aan de DB");

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

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {System.out.println(ex);}
            }
        }
        return adresList;
    }


}
