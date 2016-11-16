package com.ehbrail;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.database.AbonnementDAO;
import com.model.Abonnement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Created by Ilias El Mesaoudi on 14/11/2016.
 */

public class wAbonnementTabController implements Initializable {

	ObservableList<String> listReductie = FXCollections.observableArrayList("Senior", "18-25", "-18");
	ArrayList<String> list;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		kortingenid.setItems(listReductie);
		list = LoginController.getList();
		TextFields.bindAutoCompletion(vanField, list);
		TextFields.bindAutoCompletion(naarField, list);

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
	private ChoiceBox<String> kortingenid;

	@FXML
	private Label prijsid;

	@FXML
	private Button koopbutton;

	@FXML
	private Button berekenPrijsId;

	@FXML
	private ToggleGroup soortAbonnement;

	@FXML
	private ToggleGroup klasse;

	@FXML
	private void switchStations(ActionEvent event) throws IOException {
		try {
			String temp = vanField.getText();
			vanField.setText(naarField.getText());
			naarField.setText(temp);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@FXML
	void HeelBelgie(ActionEvent event) {
		vanField.setDisable(true);
		naarField.setDisable(true);
		vanField.clear();
		naarField.clear();
	}

	@FXML
	void selecteerRit(ActionEvent event) {
		vanField.setDisable(false);
		naarField.setDisable(false);
	}

	@FXML
	void koopAbonnement(ActionEvent event) {
		String van = null, naar = null;
		int klasse = 0;
		LocalDate begin;
		LocalDate einde;
		double prijs = 300;

		if ((ritRadioButton.isSelected() || belgieRadioButton.isSelected()) && (vanField.getText() != ""
				&& naarField.getText() != "" && datepickerBegin.getValue() != null && datepickerEinde.getValue() != null
				&& (eersteRadioButton.isSelected() || tweedeRadioButton.isSelected())
				&& kortingenid.getValue() != null)) {

			if (ritRadioButton.isSelected()) {
				van = vanField.getText();
				naar = naarField.getText();
			}
			if (belgieRadioButton.isSelected()) {
				van = "België";
				naar = "België";
			}

			begin = datepickerBegin.getValue();
			einde = datepickerEinde.getValue();

			if (eersteRadioButton.isSelected()) {
				klasse = 1;
			} else if (tweedeRadioButton.isSelected()) {
				klasse = 2;
			}

			if (kortingenid.getValue() == "Senior") {
				prijs = prijs - 50;
			} else if (kortingenid.getValue() == "18-25") {
				prijs = prijs - 80;
			} else if (kortingenid.getValue() == "-18") {
				prijs = prijs - 150;
			}

			AbonnementDAO.writeAbonnement(new Abonnement(1, 1, WerknemerController.getLogin().getMedewerker_id(),
					klasse, 1, prijs, null, van, naar, begin, einde, "Brussel"));
			clearVelden();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Information Alert");
			alert.setContentText("Abonnement werd gecreëerd.");
			alert.show();

		} else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Ongeldige Velden");
			alert.setHeaderText(null);
			alert.setContentText("Alle velden moeten ingevuld worden.");
			alert.show();

		}

	}

	@FXML
	void prijsBerekening(ActionEvent event) {
		double prijs = 300;
		if (kortingenid.getValue() == "Senior") {
			prijs = prijs - 50;
		} else if (kortingenid.getValue() == "18-25") {
			prijs = prijs - 80;
		} else if (kortingenid.getValue() == "-18") {
			prijs = prijs - 150;
		}

		String totaal = String.valueOf(prijs);
		prijsid.setText(totaal);
		
		
	}

	public void clearVelden() {

		vanField.clear();
		naarField.clear();
		ritRadioButton.setSelected(false);
		belgieRadioButton.setSelected(false);
		eersteRadioButton.setSelected(false);
		tweedeRadioButton.setSelected(false);
		datepickerBegin.setValue(null);
		datepickerEinde.setValue(null);
		prijsid.setText(null);
		kortingenid.setValue(null);

	}

}
