package com.ehbrail;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.database.AdresDAO;
import com.database.KlantDAO;
import com.database.VerlorenVoorwerpDAO;
import com.model.Adres;
import com.model.Klant;
import com.model.VerlorenVoorwerp;
import com.sun.corba.se.spi.activation._ActivatorImplBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class EditKlantController implements Initializable {

	private ResourceBundle language;

	@FXML
	private TextField filter;

	@FXML
	private TableView<Klant> tableview;

	@FXML
	private TableColumn<Klant, Integer> id;

	@FXML
	private TableColumn<Klant, Integer> adresid;

	@FXML
	private TableColumn<Klant, String> voornaamid;

	@FXML
	private TableColumn<Klant, String> achternaamid;

	@FXML
	private TableColumn<Klant, LocalDate> datumid;

	@FXML
	private TableColumn<Klant, String> nummerid;

	@FXML
	private TableColumn<Klant, String> commentaarid;

	@FXML
	private TextField voornaamText;

	@FXML
	private TextField naamText;

	@FXML
	private DatePicker datepicker;

	@FXML
	private TextField gsmText;

	@FXML
	private TextArea commentaarText;

	@FXML
	private TextField plaatsnaamText;

	@FXML
	private TextField postcodeText;

	@FXML
	private TextField straatText;

	@FXML
	private TextField huisnummerText;

	@FXML
	private TextField brievenbusText;

	@FXML
	private Button resetbutton;

	@FXML
	private Button updatebutton;

	@FXML
	private Label adresidHidden;

	@FXML
	private Label idHidden;

	private ObservableList<Klant> data = FXCollections.observableArrayList(KlantDAO.getAll());;

	ArrayList<String> list;

	@FXML
	void reset(ActionEvent event) {

		adresidHidden.setText("");
		idHidden.setText("");
		voornaamText.clear();
		naamText.clear();
		datepicker.setValue(null);
		gsmText.clear();
		commentaarText.clear();
		plaatsnaamText.clear();
		postcodeText.clear();
		straatText.clear();
		huisnummerText.clear();
		brievenbusText.clear();
		filter.clear();

	}

	@FXML
	void update(ActionEvent event) {

		if (voornaamText.getText().isEmpty() || naamText.getText().isEmpty() || datepicker.getValue() == null
				|| plaatsnaamText.getText().isEmpty() || postcodeText.getText().isEmpty()
				|| straatText.getText().isEmpty() || huisnummerText.getText().isEmpty()
				|| brievenbusText.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(null);
			alert.setContentText(language.getString("alertFieldsCorrect") + "\n" + language.getString("controleDate"));
			alert.showAndWait();

		} else {
			int klantAdresId = Integer.valueOf(adresidHidden.getText());
			int klantid = Integer.valueOf(idHidden.getText());
			Klant klant = new Klant(klantid, klantAdresId, datepicker.getValue(), gsmText.getText(),
					commentaarText.getText(), true, naamText.getText(), voornaamText.getText());

			KlantDAO.updateVoorwerp(klant);

			int huisnr = Integer.valueOf(huisnummerText.getText());
			int postcode = Integer.valueOf(postcodeText.getText());
			Adres adres = new Adres(klantAdresId, plaatsnaamText.getText(), straatText.getText(), huisnr,
					brievenbusText.getText(), postcode);
			AdresDAO.updateVoorwerp(adres);

			refresh();
			clear();
			
		}

	}

	@FXML
	void selectInformaties(MouseEvent event) {
		if (event.getClickCount() == 2 && tableview.getSelectionModel().getSelectedItem() != null) {

			Klant klant = tableview.getSelectionModel().getSelectedItem();
			voornaamText.setText(klant.getVoornaam());
			naamText.setText(klant.getNaam());
			datepicker.setValue(klant.getGeboortedatum());
			gsmText.setText(klant.getGsmnummer());
			commentaarText.setText(klant.getCommentaar());
			adresidHidden.setText(String.valueOf(klant.getAdresid()));
			idHidden.setText(String.valueOf(klant.getKlantid()));

			AdresDAO adresDAO = new AdresDAO();
			Adres adres = adresDAO.getAdres(klant.getAdresid());
			if (adres != null) {
				plaatsnaamText.setText(adres.getPlaatsnaam());
				String postcode = String.valueOf(adres.getPostcode());
				postcodeText.setText(postcode);
				straatText.setText(adres.getStraat());
				String huisnr = String.valueOf(adres.getHuisnr());
				huisnummerText.setText(huisnr);
				brievenbusText.setText(adres.getBrievenbus());

			}
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		language = resources;

		tableview.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

		refresh();

		commentaarText.setWrapText(true);
		adresidHidden.setText("");

		id.setCellValueFactory(new PropertyValueFactory<Klant, Integer>("klantid"));
		adresid.setCellValueFactory(new PropertyValueFactory<Klant, Integer>("adresid"));
		voornaamid.setCellValueFactory(new PropertyValueFactory<Klant, String>("voornaam"));
		achternaamid.setCellValueFactory(new PropertyValueFactory<Klant, String>("naam"));
		datumid.setCellValueFactory(new PropertyValueFactory<Klant, LocalDate>("geboortedatum"));
		nummerid.setCellValueFactory(new PropertyValueFactory<Klant, String>("gsmnummer"));
		commentaarid.setCellValueFactory(new PropertyValueFactory<Klant, String>("commentaar"));

		FilteredList<Klant> filteredData = new FilteredList<Klant>(data, p -> true);
		filter.textProperty().addListener(((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Klant -> {
				if ((newValue == null) || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (Klant.getNaam().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (Klant.getVoornaam().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}

				return false;
			});
		}));

		SortedList<Klant> sortedList = new SortedList<Klant>(filteredData);
		sortedList.comparatorProperty().bind(tableview.comparatorProperty());
		tableview.setItems(sortedList);

	}

	public void refresh() {
		data = FXCollections.observableArrayList(KlantDAO.getAll());
		tableview.setItems(data);
	}
	
	public void clear(){
		adresidHidden.setText("");
		idHidden.setText("");
		voornaamText.clear();
		naamText.clear();
		datepicker.setValue(null);
		gsmText.clear();
		commentaarText.clear();
		plaatsnaamText.clear();
		postcodeText.clear();
		straatText.clear();
		huisnummerText.clear();
		brievenbusText.clear();
		filter.clear();
	}

}
