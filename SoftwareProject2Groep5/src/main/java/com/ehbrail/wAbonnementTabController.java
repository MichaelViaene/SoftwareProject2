package com.ehbrail;

import java.net.URL;
import java.util.ResourceBundle;

import com.model.Abonnement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class wAbonnementTabController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	
    @FXML
    private TextField vanField;

    @FXML
    private Button switchStationsButton;

    @FXML
    private RadioButton ritRadioButton;

    @FXML
    private RadioButton belgieRadioButton;

    @FXML
    private DatePicker datepickerBegin;

    @FXML
    private TextField naarField;

    @FXML
    private DatePicker datepickerEinde;

    @FXML
    private RadioButton eersteRadioButton;

    @FXML
    private RadioButton tweedeRadioButton;

    @FXML
    private ChoiceBox<Abonnement> kortingenid;

    @FXML
    private Label prijsid;

    @FXML
    private Button koopbutton;

    @FXML
    void switchStations(ActionEvent event) {

    }

	

}
