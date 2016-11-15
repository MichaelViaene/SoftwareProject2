package com.ehbrail;

/**
 * Created by Vik Mortier on 14/11/2016.
 */

import com.database.WerknemerDAO;
import com.model.Login;
import com.model.Werknemer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ResourceBundle;

public class aEmployeeTabController implements Initializable {

    @FXML
    private Label errorRequestLabel;

    @FXML
    private TextField filterBox;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Werknemer> tableView;

    @FXML
    private TableColumn<Werknemer, Integer> medewerkerId;

    @FXML
    private TableColumn<Werknemer, String> naam;

    @FXML
    private TableColumn<Werknemer, String> voornaam;

    @FXML
    private TableColumn<Werknemer, String> username;

    @FXML
    private TableColumn<Werknemer, Integer> bevoegdheid;

    @FXML
    private TableColumn<Werknemer, Boolean> actief;

    @FXML
    private TextField addNameBox;

    @FXML
    private TextField addSurnameBox;

    @FXML
    private ChoiceBox<String> addAuthCBox;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField removeIdBox;

    @FXML
    private Button resetButton;

    @FXML
    private TextField resetIdBox;

    @FXML
    private CheckBox inactiveCheckBox;

    @FXML
    private TextField editNameBox;

    @FXML
    private TextField editSurnameBox;

    @FXML
    private ChoiceBox<String> editAuthCBox;

    @FXML
    private Button editButton;

    @FXML
    private Label editIdLabel;

    @FXML
    private CheckBox activateCheckBox;


    private ObservableList<Werknemer> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        refresh();
        medewerkerId.setCellValueFactory(new PropertyValueFactory<>("werknemerId"));
        naam.setCellValueFactory(new PropertyValueFactory<>("naam"));
        voornaam.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
        //TODO FILL VALUES
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        bevoegdheid.setCellValueFactory(new PropertyValueFactory<>("bevoegdheid"));
        actief.setCellValueFactory(new PropertyValueFactory<>("actief"));
    }


    public void refresh() {
        data = FXCollections.observableArrayList(WerknemerDAO.getAllWerknemers());
        tableView.setItems(data);
    }


    @FXML
    void searchAction(ActionEvent event) {

    }
}
