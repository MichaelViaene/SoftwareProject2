package com.ehbrail;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.database.AdresDAO;
import com.database.KlantDAO;
import com.model.Adres;
import com.model.Klant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class EditKlantController implements Initializable {

	private ResourceBundle language;
	ObservableList<Klant> lijstKlanten = FXCollections.observableArrayList(KlantDAO.getAll());

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
	private TextField filter;

	@FXML
	private TextField voornaamText;

	@FXML
	private TextField naamText;

	@FXML
	private DatePicker datepicker;

	@FXML
	private TextField gsmText;

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
	private TextArea commentaarText;

	@FXML
	void selectInformaties(MouseEvent event) {
		if (event.getClickCount() == 2 && tableview.getSelectionModel().getSelectedItem() != null) {

			Klant klant = tableview.getSelectionModel().getSelectedItem();
			voornaamText.setText(klant.getVoornaam());
			naamText.setText(klant.getNaam());
			datepicker.setValue(klant.getGeboortedatum());
			gsmText.setText(klant.getGsmnummer());
			commentaarText.setText(klant.getCommentaar());

			
			
			AdresDAO adresDAO = new AdresDAO();
			Adres adres = adresDAO.getAdres(klant.getAdresid());
			if (adres!=null) {
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

		tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableview.setItems(lijstKlanten);

		id.setCellValueFactory(new PropertyValueFactory<Klant, Integer>("klantid"));
		adresid.setCellValueFactory(new PropertyValueFactory<Klant,Integer>("adresid"));
		voornaamid.setCellValueFactory(new PropertyValueFactory<Klant, String>("voornaam"));
		achternaamid.setCellValueFactory(new PropertyValueFactory<Klant, String>("naam"));
		datumid.setCellValueFactory(new PropertyValueFactory<Klant, LocalDate>("geboortedatum"));
		nummerid.setCellValueFactory(new PropertyValueFactory<Klant, String>("gsmnummer"));
		commentaarid.setCellValueFactory(new PropertyValueFactory<Klant, String>("commentaar"));

		FilteredList<Klant> filteredData = new FilteredList<Klant>(lijstKlanten, p -> true);
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

}
