package com.ehbrail;
import com.model.Ticket;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.database.TicketDAO;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 * 
 * @author Pieter & Thijs
 *
 */

public class wTicketTabController implements Initializable{

	ArrayList<String> list;
		
	   @FXML private TextField vanField;
	   @FXML private TextField naarField;
	   @FXML private Button switchStationsButton;
	   @FXML private Button koopTicketButton;
	   @FXML private RadioButton heenEnTerugRadio;
	   @FXML private ToggleGroup heenTerug;
	   @FXML private RadioButton heenRadio;
	   @FXML private DatePicker datumHeenDatePicker;
	   @FXML private RadioButton heenVertrekRadio;
	   @FXML private ToggleGroup heenVertrekAankomst;
	   @FXML private RadioButton heenAankomstRadio;
	   @FXML private Pane painTerug;
	   @FXML private DatePicker datumTerugDatePicker;
	   @FXML private RadioButton terugVertrekRadio;
	   @FXML private ToggleGroup terugVertrekAankomst;
	   @FXML private RadioButton terugAankomstRadio;
	   @FXML private RadioButton eersteKlasseRadio;
	   @FXML private ToggleGroup klasse;
	   @FXML private RadioButton tweedeKlasseRadio;
	   @FXML private Label terugLabel;
	   

	      
	   @FXML
	    void showPaneTerug(ActionEvent event) {
		   painTerug.setVisible(false);
	    }

	   @FXML
	   void showPaneHeenTrug(ActionEvent event) {
		   painTerug.setVisible(true);
	   }
       
       @Override
       public void initialize(URL location, ResourceBundle resources) {
       list = LoginController.getList();
        TextFields.bindAutoCompletion(vanField,list);
        TextFields.bindAutoCompletion(naarField,list);
       }
        
    @FXML
    void onClickKoopTicket(ActionEvent event) {
    	String vertrekStation = vanField.getText();
   		String eindStation = naarField.getText();
   		int type, klasse, heen, terug;
   		LocalDate datumHeen = datumHeenDatePicker.getValue();
   		LocalDate datumTerug = datumTerugDatePicker.getValue();
   		LocalDateTime datumAankoop = LocalDateTime.now(ZoneId.of( "Europe/Brussels" ));
   		LocalDate todayLocalDate = LocalDate.now(ZoneId.of( "Europe/Brussels" ));
   		list = LoginController.getList();
    	/*
		Voorbeeld localDate, LocaldateTime (present)
		LocalDate todayLocalDate = LocalDate.now(ZoneId.of( "Europe/Brussels" ) );
		LocalDateTime todayLocalDateTime = LocalDateTime.now(ZoneId.of( "Europe/Brussels" ));
    	*/   		
    	if (controleerVanField() == false || controleerNaarField() == false || datumHeen == null || vertrekStation.isEmpty() || eindStation.isEmpty() || datumHeen.isBefore(todayLocalDate) || (heenEnTerugRadio.isSelected() && (datumTerug == null || datumTerug.isBefore(datumHeen)))){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error Dialog");
    		alert.setHeaderText(null);
    		alert.setContentText("Foutieve gegevens!");

    		alert.showAndWait();
    	} else {
    		if(heenRadio.isSelected()){
        		type = 0; //Indien heen reis
        		datumTerug = datumHeen;
        	} else{
        		type = 1; //Indien heen en terug reis
        	}
    		if(eersteKlasseRadio.isSelected()){
        		klasse = 1; //Indien eerste klasse
        	} else {
        		klasse = 2; // indien tweede klasse
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
        		
        	Ticket ticket = new Ticket(vertrekStation,eindStation,1,klasse,type,1,datumAankoop,datumHeen,datumTerug,WerknemerController.getLogin().getMedewerker_id());
        	
        	TicketDAO.writeTicket(ticket);
        	
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Information Dialog");
        	alert.setHeaderText(null);
        	alert.setContentText("Ticket aangemaakt!");

        	alert.showAndWait();
        	
        	System.out.println(ticket.toString());
        	vanField.clear();
        	naarField.clear();
        	datumHeenDatePicker.setValue(null);
        	datumTerugDatePicker.setValue(null);
        	tweedeKlasseRadio.setSelected(true);
        	heenVertrekRadio.setSelected(true);
        	terugVertrekRadio.setSelected(true);
        	heenRadio.setSelected(true);
        	painTerug.setVisible(false);
        	
    	}
    	
    }  
    public boolean controleerVanField(){
    	list = LoginController.getList();
    	for (int i = 0; i < list.size();i++){
    		if(list.get(i).equals(vanField.getText())){
    			return true;
    		}
    	}
    	
    	return false;
    }
    public boolean controleerNaarField(){
    	list = LoginController.getList();
    	for (int i = 0; i < list.size();i++){
    		if(list.get(i).equals(naarField.getText())){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    @FXML private void switchStations(ActionEvent event) throws IOException {
        try {
            String temp = vanField.getText();
            vanField.setText(naarField.getText());
            naarField.setText(temp);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


}
