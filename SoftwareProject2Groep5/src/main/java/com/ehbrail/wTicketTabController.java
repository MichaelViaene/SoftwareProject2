package com.ehbrail;
import com.model.Ticket;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

/**
 * 
 * @author Pieter & Thijs
 *
 */

public class wTicketTabController {

    @FXML
    private TextField vanField;

    @FXML
    private TextField naarField;

    @FXML
    private Button switchStationsButton;

    @FXML
    private Button koopTicketButton;

    @FXML
    private RadioButton heenEnTerugRadio;

    @FXML
    private RadioButton heenRadio;

    @FXML
    private RadioButton heenVertrekRadio;

    @FXML
    private RadioButton heenAankomstRadio;

    @FXML
    private RadioButton terugVertrekRadio;

    @FXML
    private RadioButton terugAankomstRadio;

    @FXML
    private Spinner<Ticket> uurTerugSpin;

    @FXML
    private Spinner<Ticket> minuutTerugSpin;

    @FXML
    private Spinner<Ticket> uurHeenSpin;

    @FXML
    private Spinner<Ticket> minuutHeenSpin;

    @FXML
    void onClickKoopTicket(ActionEvent event) {

    }

    @FXML
    void switchStations(ActionEvent event) {

    }

}
