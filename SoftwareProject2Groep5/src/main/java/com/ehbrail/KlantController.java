package com.ehbrail;

import com.database.AdresDAO;
import com.database.KlantDAO;
import com.model.Adres;
import com.model.Klant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class KlantController {

	@FXML
	private TextField voornaamid;

	@FXML
	private TextField naamid;

	@FXML
	private DatePicker datepicker;

	@FXML
	private TextField gsmid;

	@FXML
	private TextField plaatsnaamid;

	@FXML
	private TextField postcodeid;

	@FXML
	private TextField straatid;

	@FXML
	private TextField huisnummerid;

	@FXML
	private TextField brievenbusid;

	@FXML
	private Button toevoegenButton;

	@FXML
	private Button cancelButton;

	@FXML
	void toevoegen(ActionEvent event) {
		

		if (voornaamid.getText() != "" && naamid.getText() != "" && datepicker.getValue() != null
				&& gsmid.getText() != "" && plaatsnaamid.getText() != "" && postcodeid.getText() != ""
				&& straatid.getText() != "" && huisnummerid.getText() != "" && brievenbusid.getText() != "") {
			
			
			int postcode = Integer.parseInt(postcodeid.getText());
			int huisnummer = Integer.parseInt(huisnummerid.getText());
			
			Adres adres = new Adres(1, plaatsnaamid.getText(), straatid.getText(), huisnummer, brievenbusid.getText(),
					postcode);
			AdresDAO a = new AdresDAO();
			a.insertAdres(adres);
			int adresid = a.getAdresId(adres);
			Klant klant = new Klant(adresid, datepicker.getValue(), gsmid.getText(), "", true, naamid.getText(),
					voornaamid.getText());
			KlantDAO.insertKlant(klant);
			clearVelden();

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Information Alert");
			alert.setContentText("Klant informaties werden toegevoegd");
			alert.show();

		} else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Ongeldige Velden");
			alert.setHeaderText(null);
			alert.setContentText("Alle velden moeten ingevuld worden.");
			alert.show();
		}

	}

	public void clearVelden() {
		voornaamid.clear();
		naamid.clear();
		datepicker.setValue(null);
		gsmid.clear();
		plaatsnaamid.clear();
		postcodeid.clear();
		straatid.clear();
		huisnummerid.clear();
		brievenbusid.clear();
	}
}
