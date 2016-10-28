package com.database;
import com.model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vik Mortier on 28/10/2016.
 */
public class TicketDAO {

    public Boolean writeTicket(Ticket ticket) {


        try {
            Connection con = Database.getConnection();
            if (con == null) {
                Database.openDatabase();
                con = Database.getConnection();
            }

            if (con != null) {
                String query = "INSERT INTO Ticket (vertrek, aankomst, datum_aankoop, datum_heen, datum_terug, class, prijs, type, medewerker_id)" + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, ticket.getVertrekStation());
                preparedStatement.setString(2, ticket.getEindStation());
                preparedStatement.setObject(3, ticket.getDatumAankoop());
                preparedStatement.setObject(4, ticket.getDatumHeen());
                preparedStatement.setObject(5, ticket.getDatumTerug());
                preparedStatement.setInt(6, ticket.getKlasse());
                preparedStatement.setDouble(7, ticket.getPrijs());
                preparedStatement.setInt(8, ticket.getType());

                /*
                Waar is de betreffende medewerkerID? Die moet accesable zijn zolang het programma draait.
                Hardcoded 2 momenteel hieronder
                */

                preparedStatement.setInt(9, 1);

                preparedStatement.execute();
                con.close();

            }

        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public List<Ticket> readTickets (){

        List<Ticket> tickets = new LinkedList<>();

        try {
            Connection con = Database.getConnection();
            if (con == null) {
                Database.openDatabase();
                con = Database.getConnection();
            }

            if (con != null) {
                String query = "SELECT * FROM Ticket";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {

                    Ticket ticket = new Ticket();

                    ticket.setTicket_id(resultSet.getInt("ticket_id"));
                    ticket.setVertrekStation(resultSet.getString("vertrek"));
                    ticket.setEindStation(resultSet.getString("aankomst"));
                    ticket.setDatumAankoop(resultSet.getTimestamp("datum_aankoop").toLocalDateTime());
                    ticket.setDatumHeen(resultSet.getDate("datum_heen").toLocalDate());
                    ticket.setDatumTerug(resultSet.getDate("datum_terug").toLocalDate());
                    ticket.setKlasse(resultSet.getInt("class"));
                    ticket.setPrijs(resultSet.getDouble("prijs"));
                    ticket.setType(resultSet.getInt("type"));

                    /* resultSet.getInt("medewerker_id");
                       nog niet geimplementeerd.
                     */

                    tickets.add(ticket);
                }
                resultSet.close();
                preparedStatement.close();
                con.close();
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return tickets;
    }
}
