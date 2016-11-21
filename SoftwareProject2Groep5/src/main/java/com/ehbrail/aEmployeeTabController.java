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
 * Created by jorda on 4/11/2016.
 */
public class aEmployeeTabController implements Initializable {
    //Aanmaak enum, zo kan de gebruiker kiezen tussen actief of onactief. Maar kan je 0 en 1 doorsturen naar de DB. Zou eigenlijk in de klasse werknemer moeten.
    private enum actief{onactief(0),actief(1);
        private int value;
        actief(int value) {this.value = value;}
    }

    @FXML private Button createUserButton;
    @FXML private TextField naamField;
    @FXML private TextField voornaamField;
    @FXML private ComboBox<aEmployeeTabController.actief> actiefBox;
    @FXML private Label formuleLabel;

    ObservableList<actief> actiefList = FXCollections.observableArrayList(actief.onactief,actief.actief);

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
