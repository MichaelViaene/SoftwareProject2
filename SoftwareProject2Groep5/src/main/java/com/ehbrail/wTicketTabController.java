package com.ehbrail;
import com.model.Ticket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import java.util.*;

import javafx.embed.swing.SwingNode;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import java.util.*;

import javafx.embed.swing.SwingNode;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
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

	private ResourceBundle language;
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


		  language = resources;

		  language = resources;

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
    	if (controleerVanField() == false || controleerNaarField() == false || vertrekStation.isEmpty() || eindStation.isEmpty() ){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error Dialog");
    		alert.setHeaderText(null);
    		alert.setContentText("FOUTIEVE STATIONS");
    		alert.showAndWait();
    	} else if (datumHeen == null || datumHeen.isBefore(todayLocalDate) || (heenEnTerugRadio.isSelected() && (datumTerug == null || datumTerug.isBefore(datumHeen)))){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error Dialog");
    		alert.setHeaderText(null);
    		alert.setContentText("FOUTIEVE DATUMS");
    		alert.showAndWait();
    	}
    	else {
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
<<<<<<< HEAD

=======
        	


			createPDF(ticket,language);




>>>>>>> refs/heads/Dev_Pieter
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


    private void createPDF(Ticket ticket, ResourceBundle language){
    	try {
			JasperPrint jasperPrint = null;
			HashMap<String,Object> params = new HashMap<String,Object>();
			//parameters voor Resourcebundle
			String van = language.getString("RouteInfo.van");
			String tot = language.getString("RouteInfo.naar");
			String op = language.getString("Ticket.op");
			String klasseLabel = language.getString("Abonnement.klasse");

			//Parameters van ticket
			int ticket_id = 15;

			if (ticket.getType() == 0) {
				String typeTekst = language.getString("Ticket.enkelereis");
				String type = language.getString("Ticket.enkel");
				params.put("typeTekst",typeTekst);
				params.put("type",type);
			}
			if (ticket.getType() == 1){
				String typeTekst = language.getString("Ticket.heenenterugreis");
				String type = language.getString("Ticket.heenenterug");
				params.put("typeTekst",typeTekst);
				params.put("type",type);
			}

			String klasseTekst = String.valueOf(ticket.getKlasse())+"e";
			String vertrekStation = ticket.getVertrekStation();
			String eindStation = ticket.getEindStation();
			String prijs = String.format("%.2f",ticket.getPrijs());
			LocalDateTime datumAankoop = ticket.getDatumAankoop() ; //LocalDateTime.now(ZoneId.of( "Europe/Brussels" ));
			LocalDate datumHeen = ticket.getDatumHeen()  ; // LocalDate.of(2016,12,16);

			int datumAankoop_year = datumAankoop.getYear();
			int datumAankoop_month = datumAankoop.getMonthValue();
			int datumAankoop_day = datumAankoop.getDayOfMonth();
			int datumAankoop_hour= datumAankoop.getHour();
			int datumAankoop_minute = datumAankoop.getMinute();
			int datumHeen_year = datumHeen.getYear();
			int datumHeen_month = datumHeen.getMonthValue();
			int datumHeen_day = datumHeen.getDayOfMonth();
			String realPath = "src/main/resources/com/ehbrail";
			params.put("realPath",realPath);
			//Resourcebundle
			params.put("van",van);
			params.put("tot",tot);
			params.put("op",op);
			params.put("klasseLabel",klasseLabel);
			//Ticket params
			params.put("ticket_id",ticket_id);
			params.put("vertrekStation",vertrekStation);
			params.put("eindStation",eindStation);
			params.put("klasseTekst",klasseTekst);
			params.put("prijs",prijs);
			params.put("datumAankoop_year",datumAankoop_year);
			params.put("datumAankoop_month",datumAankoop_month);
			params.put("datumAankoop_day",datumAankoop_day);
			params.put("datumAankoop_hour",datumAankoop_hour);
			params.put("datumAankoop_minute",datumAankoop_minute);
			params.put("datumHeen_year",datumHeen_year);
			params.put("datumHeen_month",datumHeen_month);
			params.put("datumHeen_day",datumHeen_day);

			System.out.println("Generating PDF...");
			//JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/TrainTicket.jrxml");
			//jasperPrint = JasperFillManager.fillReport(jasperReport,params, new JREmptyDataSource());
			jasperPrint = JasperFillManager.fillReport("src/main/resources/TrainTicket.jasper",params,new JREmptyDataSource() );

			/**
			Stage stage = new Stage();
			final SwingNode swingNode = new SwingNode();
			swingNode.setContent(new JRViewer(jasperPrint));
			StackPane pane = new StackPane();
			pane.getChildren().add(swingNode);
			stage.setScene(new Scene(pane, 800, 800));
			stage.show();
			 **/

			JRPrintPreview printPreview = new JRPrintPreview(jasperPrint);
			printPreview.show();
/**
			//JasperExportManager.exportReportToPdfFile(jasperPrint, "trainTicket.pdf");
			JasperViewer jasperViewer = new JasperViewer(jasperPrint);
			jasperViewer.setVisible(true);
**/
		}
		catch (Exception e){e.printStackTrace();}
	}

}
