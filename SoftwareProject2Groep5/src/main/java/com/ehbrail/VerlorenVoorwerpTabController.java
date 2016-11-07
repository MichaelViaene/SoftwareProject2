package com.ehbrail;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
	        TextFields.bindAutoCompletion(textButton,list);	 
	        TextFields.bindAutoCompletion(stationtext,list);
		
		voorwerpid.setCellValueFactory(new PropertyValueFactory<VerlorenVoorwerp, Integer>("voorwerpid"));
		naam.setCellValueFactory(new PropertyValueFactory<VerlorenVoorwerp, String>("naam"));
		omschrijving.setCellValueFactory(new PropertyValueFactory<VerlorenVoorwerp, String>("omschrijving"));
		datum.setCellValueFactory(new PropertyValueFactory<VerlorenVoorwerp, String>("datum"));
		station.setCellValueFactory(new PropertyValueFactory<VerlorenVoorwerp, String>("station"));
		
		refresh();
		
	}

	public void loadDatabase(ActionEvent event) {
		refresh();
	}
	
	public void refresh(){
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
		LocalDate localDate = datumtext.getValue();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedString = localDate.format(formatter);
		VerlorenVoorwerp voorwerp = new VerlorenVoorwerp(naamtext.getText(), text, formattedString,
				stationtext.getText());
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
	}

	@FXML
	void deleteVoorwerp(ActionEvent event) {
		try {
			int id = Integer.parseInt(idtext.getText());
			VerlorenVoorwerpDAO.deleteVoorwerp(id);
			idtext.clear();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Information Alert");
			alert.setContentText("Verloren voorwerp werd verwijdert");
			alert.show();
			
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

	}
	
	@FXML
    void selectInformaties(MouseEvent event) {

    }
	
	
}