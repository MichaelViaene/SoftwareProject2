package com.ehbrail;
import com.model.Ticket;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.ButtonGroup;

import com.database.TicketDAO;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.control.DatePicker;

/**
 * 
 * @author Pieter
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
	    private ToggleGroup heenTerug;

	    @FXML
	    private RadioButton heenRadio;

	    @FXML
	    private DatePicker datumHeenDatePicker;

	    @FXML
	    private RadioButton heenVertrekRadio;

	    @FXML
	    private ToggleGroup heenVertrekAankomst;

	    @FXML
	    private RadioButton heenAankomstRadio;

	    @FXML
	    private Pane paneTerug;

	    @FXML
	    private DatePicker datumTerugDatePicker;

	    @FXML
	    private RadioButton terugVertrekRadio;

	    @FXML
	    private ToggleGroup terugVertrekAankomst;

	    @FXML
	    private RadioButton terugAankomstRadio;

	    @FXML
	    private RadioButton eersteKlasseRadio;

	    @FXML
	    private ToggleGroup klasse;

	    @FXML
	    private RadioButton tweedeKlasseRadio;

	    @FXML
	    void showPaneTerug(ActionEvent event) {
	    	paneTerug.setVisible(true);
	    }
	    
	    @FXML
	    void paneTerugWeg(ActionEvent event) {
	    	paneTerug.setVisible(false);
	    }
    
        
    @FXML
    void onClickKoopTicket(ActionEvent event) {
    	String vertrekStation = vanField.getText();
    	String eindStation = naarField.getText();
    	int type, klasse, heen, terug;
    	LocalDate datumHeen = datumHeenDatePicker.getValue();
    	LocalDate datumTerug = datumTerugDatePicker.getValue();
    	LocalDateTime datumAankoop;
    	
    	if(heenRadio.isSelected()){
    		type = 0; //Indien heen reis
    	} else{
    		type = 1; //Indien heen en terug reis
    	}
    	if(eersteKlasseRadio.isSelected()){
    		klasse = 0; //Indien eerste klasse
    	} else {
    		klasse = 1; // indien tweede klasse
    	} 
    	if(heenVertrekRadio.isSelected()){
    		heen = 0;
    	} else {
    		heen = 1;
    	} 
    	if(terugVertrekRadio.isSelected()){
    		terug = 0;
    	} else{
    		terug = 1;
    	}
    	
    	TicketDAO ticketDao = new TicketDAO();
    	
    	Ticket ticket = new Ticket();
    }

    @FXML
    void switchStations(ActionEvent event) {
    	String vertrekStation = vanField.getText();
    	String eindStation = naarField.getText();
    	String temp;
    	
    	temp = eindStation;
    	eindStation = vertrekStation;
    	vertrekStation = temp;
    	vanField.setText(vertrekStation);
    	naarField.setText(eindStation);
    }

}
