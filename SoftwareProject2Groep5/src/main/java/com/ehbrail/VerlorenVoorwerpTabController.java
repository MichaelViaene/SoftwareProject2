package com.ehbrail;

/**
*
* @author Ilias El Mesaoudi
**/
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.database.VerlorenVoorwerpDAO;
import com.model.VerlorenVoorwerp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class VerlorenVoorwerpTabController implements Initializable {

	// tableview
	@FXML
	private TableView<VerlorenVoorwerp> tableview;

	@FXML
	private TableColumn<VerlorenVoorwerp, Integer> voorwerpid;

	@FXML
	private TableColumn<VerlorenVoorwerp, String> naam;

	@FXML
	private TableColumn<VerlorenVoorwerp, String> omschrijving;

	@FXML
	private TableColumn<VerlorenVoorwerp, String> datum;

	@FXML
	private TableColumn<VerlorenVoorwerp, String> station;

	// veld om station te zoeken
	@FXML
	private TextField textButton;

	@FXML
	private Button searchButton;

	// load alle gegevens uit databse
	@FXML
	private Button loadButton;

	// insert velden
	@FXML
	private TextField naamtext;

	@FXML
	private TextField stationtext;

	@FXML
	private DatePicker datumtext;

	@FXML
	private TextArea omschrijvingtext;

	@FXML
	private Button savebutton;

	// delete velden
	@FXML
	private TextField idtext;

	@FXML
	private Button deletebutton;

	// list verlorenvoorwerpen
	private ObservableList<VerlorenVoorwerp> data;

	ArrayList<String> list;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		list = LoginController.getList();
		TextFields.bindAutoCompletion(textButton, list);
		TextFields.bindAutoCompletion(stationtext, list);
		
		refresh();

		voorwerpid.setCellValueFactory(new PropertyValueFactory<VerlorenVoorwerp, Integer>("voorwerpid"));
		naam.setCellValueFactory(new PropertyValueFactory<VerlorenVoorwerp, String>("naam"));
		omschrijving.setCellValueFactory(new PropertyValueFactory<VerlorenVoorwerp, String>("omschrijving"));
		datum.setCellValueFactory(new PropertyValueFactory<VerlorenVoorwerp, String>("datum"));
		station.setCellValueFactory(new PropertyValueFactory<VerlorenVoorwerp, String>("station"));
	}

	public void loadDatabase(ActionEvent event) {
		refresh();
	}

	public void refresh() {
		data = FXCollections.observableArrayList(VerlorenVoorwerpDAO.getAll());
		tableview.setItems(data);
	}

	public void searchAction(ActionEvent event) {
		data = FXCollections.observableArrayList(VerlorenVoorwerpDAO.getVoorwerpByStation(textButton.getText()));
		tableview.setItems(null);
		tableview.setItems(data);
		textButton.clear();

	}

	@FXML
	void insertVoorwerp(ActionEvent event) {
		String text = omschrijvingtext.getText();
		// formattedString met "" en if om te controleren of die niet null om te
		// formatten wordt gedaan om de datepicker veld te kunnen controleren.
		String formattedString = "";
		if (datumtext.getValue() != null) {
			LocalDate localDate = datumtext.getValue();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			formattedString = localDate.format(formatter);
		}
		VerlorenVoorwerp voorwerp = new VerlorenVoorwerp(naamtext.getText(), text, formattedString,
				stationtext.getText());
		if (!(voorwerp.getNaam().isEmpty() || voorwerp.getOmschrijving().isEmpty() || voorwerp.getStation().isEmpty()
				|| voorwerp.getDatum().isEmpty())) {
			VerlorenVoorwerpDAO.insertVoorwerp(voorwerp);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Information Alert");
			alert.setContentText("Verloren voorwerp werd toegevoegd");
			alert.show();
			naamtext.clear();
			datumtext.getEditor().clear();
			omschrijvingtext.clear();
			stationtext.clear();
			refresh();
		} else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Ongeldige Velden");
			alert.setHeaderText(null);
			alert.setContentText("Alle velden moeten ingevuld worden.");
			alert.show();
		}
	}

	@FXML
	void deleteVoorwerp(ActionEvent event) {

		try {
			int id = Integer.parseInt(idtext.getText());
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Ben je zeker dat je " + id + " wilt deleten?");
			Optional<ButtonType> action = alert.showAndWait();
			if (action.get() == ButtonType.OK) {
				VerlorenVoorwerpDAO.deleteVoorwerp(id);
			}
			idtext.clear();

		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error");
			alert.setContentText("textveld moet ingevuld worden\nid moet een cijfer zijn");
			alert.show();
		}
		refresh();

	}

	@FXML
	void updateVoorwerp(ActionEvent event) {

		String text = omschrijvingtext.getText();
		// formattedString met "" en if om te controleren of die niet null is om
		// te
		// formatten (wordt gedaan om de datepicker veld te kunnen controleren).
		String formattedString = "";
		if (datumtext.getValue() != null) {
			LocalDate localDate = datumtext.getValue();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			formattedString = localDate.format(formatter);
		}
		int id = Integer.parseInt(idtext.getText());
		VerlorenVoorwerp voorwerp = new VerlorenVoorwerp(id, naamtext.getText(), text, formattedString,
				stationtext.getText());
		if (!(voorwerp.getNaam().isEmpty() || voorwerp.getOmschrijving().isEmpty() || voorwerp.getStation().isEmpty()
				|| voorwerp.getDatum().isEmpty())) {

			VerlorenVoorwerpDAO.updateVoorwerp(voorwerp);
			naamtext.clear();
			datumtext.getEditor().clear();
			omschrijvingtext.clear();
			stationtext.clear();
			refresh();
		} else if ((voorwerp.getNaam().isEmpty() || voorwerp.getOmschrijving().isEmpty()
				|| voorwerp.getStation().isEmpty() || voorwerp.getDatum().isEmpty())) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Ongeldige Velden");
			alert.setHeaderText(null);
			alert.setContentText("Alle velden moeten ingevuld worden.");
			alert.show();
		}

	}

	@FXML
	void selectInformaties(MouseEvent event) {
		if (event.getClickCount() == 2 && tableview.getSelectionModel().getSelectedItem() != null) {

			VerlorenVoorwerp v = tableview.getSelectionModel().getSelectedItem();
			naamtext.setText(v.getNaam());
			stationtext.setText(v.getStation());
			omschrijvingtext.setText(v.getOmschrijving());
			String str = Integer.toString(v.getVoorwerpid());
			idtext.setText(str);

		}
	}

}