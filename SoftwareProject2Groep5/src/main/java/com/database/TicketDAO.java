package com.database;
import com.model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

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
}
