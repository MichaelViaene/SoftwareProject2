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
        try (Connection con = Database.getConnection()) {
            try (Statement st = con.createStatement()){
            	try (ResultSet rs = st.executeQuery("SELECT * FROM Adres;")){
		
		            while (rs.next()) {
		                Adres adres = new Adres();
		                adres.setAdres_id(rs.getInt("adres_id"));
		                adres.setPlaatsnaam(rs.getString("plaatsnaam"));
		                adres.setStraat(rs.getString("straat"));
		                adres.setHuisnr(rs.getInt("huisnr"));
		                adres.setBrievenbus(rs.getString("brievenbus"));
		                adres.setPostcode(rs.getInt("postcode"));
		
		                adresList.add(adres);
		            }
            	} catch (Exception ex) {
                    System.out.println(ex);
            	}
            }
            catch (Exception ex) {
                System.out.println(ex);
        	}
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return adresList;
    }

    public void insertAdres(Adres adres) {
        AdresDAO adresDAO = new AdresDAO();
        try (Connection con = Database.getConnection()){
            
            if (adres != null) {
                //Check of adres al in de DB zit
                List<Adres> adresList = adresDAO.checkAdres(adres);
                if (adresList.size() == 0) {

                    String query = "INSERT INTO Adres (adres_id,plaatsnaam,straat,huisnr,brievenbus,postcode) VALUES (NULL,?,?,?,?,?);";
                    con.setAutoCommit(false);
                    try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	                    //preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
	                    preparedStatement.setString(1, adres.getPlaatsnaam());
	                    preparedStatement.setString(2, adres.getStraat());
	                    preparedStatement.setInt(3, adres.getHuisnr());
	                    preparedStatement.setString(4, adres.getBrievenbus());
	                    preparedStatement.setInt(5, adres.getPostcode());
	                    
	                    preparedStatement.executeUpdate();
	                    con.commit();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public ArrayList<Adres> checkAdres(Adres adres) throws SQLException{
        ArrayList<Adres> adresList = new ArrayList<>();
        try (Connection con = Database.getConnection()){
            String sql = "SELECT * FROM Adres WHERE plaatsnaam= ? AND straat=? AND huisnr=? AND brievenbus=? AND postcode=?;";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
	            preparedStatement.setString(1,adres.getPlaatsnaam());
	            preparedStatement.setString(2,adres.getStraat());
	            preparedStatement.setInt(3,adres.getHuisnr());
	            preparedStatement.setString(4,adres.getBrievenbus());
	            preparedStatement.setInt(5,adres.getPostcode());
	            
	            try (ResultSet rs = preparedStatement.executeQuery()){
		            while (rs.next()) {
		                Adres adres1 = new Adres();
		                adres1.setAdres_id(rs.getInt("adres_id"));
		                adres1.setPlaatsnaam(rs.getString("plaatsnaam"));
		                adres1.setStraat(rs.getString("straat"));
		                adres1.setHuisnr(rs.getInt("huisnr"));
		                adres1.setBrievenbus(rs.getString("brievenbus"));
		                adres1.setPostcode(rs.getInt("postcode"));
		
		                adresList.add(adres1);
		            }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } catch (Exception ex) {
                System.out.println(ex);
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
        return adresList;
    }

//TODO update en delete methode voorzien
}
