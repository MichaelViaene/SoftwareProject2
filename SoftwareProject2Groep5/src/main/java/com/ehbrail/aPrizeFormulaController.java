package com.ehbrail;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Michael on 18/11/2016.
 */
public class aPrizeFormulaController {

	public class aPrizeFormulaController implements Initializable {
	    @FXML private Button submitFormulaButton;
	    @FXML private TextField formuleField;
	    @FXML private Label formuleLabel;

	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	        actiefBox.setItems(actiefList);
	    }


	    @FXML private void createUserEvent(ActionEvent event) throws IOException {
	        try {
	            //DO SOMETHING
	            System.out.println("Naam: " +naamField.getText()+",   Voornaam: "+voornaamField.getText()+",   Actief status: "+actiefBox.getValue().value);
	        }
	        catch (Exception e){
	            System.out.println(e);
	        }
	    }


	}
}
