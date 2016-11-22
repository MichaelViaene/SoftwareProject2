package com.ehbrail;

/**
 * Created by Vik Mortier on 14/11/2016.
 */

import com.database.LoginDAO;
import com.database.WerknemerDAO;
import com.model.Login;
import com.model.Werknemer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class aEmployeeTabController implements Initializable {

    @FXML
    private TextField filterBox;

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
    private TableColumn<Werknemer, String> bevoegdheid;

    @FXML
    private TableColumn<Werknemer, Boolean> actief;

    @FXML
    private TextField addNameBox;

    @FXML
    private TextField addSurnameBox;

    @FXML
    private ChoiceBox<String> addAuthCBox;

    @FXML
    private Label removeIdBox;

    @FXML
    private Label resetIdBox;

    @FXML
    private CheckBox inactiveCheckBox;

    @FXML
    private TextField editNameBox;

    @FXML
    private TextField editSurnameBox;

    @FXML
    private ChoiceBox<String> editAuthCBox;

    @FXML
    private Label editIdLabel;

    @FXML
    private CheckBox actiefCheckBox;

    @FXML
    private Button resetButton;

    @FXML
    private Button deleteButton;


    private ObservableList<Werknemer> data;
    private List<Werknemer> dataArray;
    private Werknemer reset = new Werknemer();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        resetButton.setDisable(true);
        deleteButton.setDisable(true);

        refresh();

        Login.Bevoegdheid[] bevoegheden = Login.Bevoegdheid.values();

        int i = 0;
        for (Login.Bevoegdheid bev : bevoegheden) {
            addAuthCBox.getItems().add(i, bev.toString());
            editAuthCBox.getItems().add(i, bev.toString());
            i++;
        }


        medewerkerId.setCellValueFactory(new PropertyValueFactory<>("werknemerId"));
        naam.setCellValueFactory(new PropertyValueFactory<>("naam"));
        voornaam.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
        username.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLogin().getUsername()));
        bevoegdheid.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLogin().getBevoegdheid().toString()));
        actief.setCellValueFactory(new PropertyValueFactory<>("actief"));

        FilteredList<Werknemer> filteredData = new FilteredList<Werknemer>(data, p-> true );
        filterBox.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Werknemer -> {
                if ((newValue == null) || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (Werknemer.getNaam().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Werknemer.getVoornaam().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Werknemer.getLogin().getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (Werknemer.getLogin().getBevoegdheid().toString().contains(lowerCaseFilter)){
                    return true;
                }
                return false;
            });
        }));

        inactiveCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                filteredData.setPredicate(obj -> {
                    if(!inactiveCheckBox.isSelected() && !obj.isActief()){
                        return false;
                    }
                        return true;
                });

            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Werknemer>() {
            @Override
            public void changed(ObservableValue<? extends Werknemer> observable, Werknemer oldValue, Werknemer newValue) {
                if(tableView.getSelectionModel().getSelectedItem() != null) {

                    deleteButton.setDisable(false);
                    resetButton.setDisable(false);

                    Werknemer werknemer = observable.getValue();
                    editIdLabel.setText(Integer.toString(werknemer.getWerknemerId()));
                    removeIdBox.setText(Integer.toString(werknemer.getWerknemerId()));
                    resetIdBox.setText(Integer.toString(werknemer.getWerknemerId()));
                    reset = werknemer;
                }
            }
        });


        SortedList<Werknemer> sortedList = new SortedList<Werknemer>(filteredData);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);
    }


    private void refresh() {
        dataArray = WerknemerDAO.getAllWerknemers();
        data = FXCollections.observableArrayList(dataArray);
        tableView.setItems(data);
    }

    @FXML
    private void addWerknemer(ActionEvent actionEvent){

        boolean succesInsertLogin = false;

        if(!addNameBox.getText().trim().isEmpty()&& !addSurnameBox.getText().trim().isEmpty() && addAuthCBox.getValue() != null ){

            Werknemer werknemer = new Werknemer();
            werknemer.setNaam(addNameBox.getText());
            werknemer.setVoornaam(addSurnameBox.getText());
            werknemer.setActief(true);
            boolean succesInsertWerknemer = WerknemerDAO.insertWerknemer(werknemer);

            if(succesInsertWerknemer){
                werknemer.setLogin(new Login());
                werknemer.getLogin().setUsername(werknemer.getVoornaam() + werknemer.getWerknemerId());
                Login.Bevoegdheid convertedValueA = Login.Bevoegdheid.valueOf(addAuthCBox.getValue());
                werknemer.getLogin().setBevoegdheid(convertedValueA);

                werknemer.getLogin().setMedewerker_id(werknemer.getWerknemerId());
                werknemer.getLogin().setPassword(Login.createHash(werknemer.getVoornaam()+werknemer.getWerknemerId()));
                werknemer.getLogin().setMedewerker_id(werknemer.getWerknemerId());
                succesInsertLogin = LoginDAO.insertLogin(werknemer.getLogin());
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error: Could not insert Werknemer");
                alert.setHeaderText("Error: Could not insert Werknemer, please try again!");
                alert.showAndWait();
            }

            if(succesInsertWerknemer && succesInsertLogin){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Created");
                alert.setHeaderText("Please note down following user credentials ");
                alert.setContentText("Username: " + werknemer.getLogin().getUsername() + " Password: " + werknemer.getVoornaam()+werknemer.getWerknemerId());
                alert.showAndWait();
                refresh();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error: Could not insert Login");
                alert.setHeaderText("Error: Could not insert Login, please try again!");
                alert.showAndWait();
            }
        }

        else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: You need to enter all the fields correctly!");
            alert.setHeaderText("please try again!");
            alert.showAndWait();
        }

    }

    @FXML
    private void editWerknemer (ActionEvent actionEvent){

        if(!editNameBox.getText().trim().isEmpty() && !editSurnameBox.getText().trim().isEmpty() && editIdLabel.getText() != null && editAuthCBox.getValue() != null){

            Werknemer werknemer = new Werknemer();
            Login login = new Login();

            werknemer.setNaam(editNameBox.getText());
            werknemer.setVoornaam(editSurnameBox.getText());
            werknemer.setActief(actiefCheckBox.isSelected());
            werknemer.setWerknemerId(Integer.parseInt(editIdLabel.getText()));

            Login.Bevoegdheid convertedValueA = Login.Bevoegdheid.valueOf(editAuthCBox.getValue());
            login.setBevoegdheid(convertedValueA);
            login.setMedewerker_id(werknemer.getWerknemerId());

            boolean succesUpdateWerknemer = WerknemerDAO.updateWerknemer(werknemer);
            boolean succesUpdateLogin = LoginDAO.updateLoginBevoegdheid(login);

            if(succesUpdateWerknemer && succesUpdateLogin) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee Updated");
                alert.setHeaderText("The employee has been succesfully updated.");
                alert.setContentText("Name: " + werknemer.getNaam() + " Surname: " + werknemer.getVoornaam());
                alert.showAndWait();
                refresh();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error: Could not update Employee");
                alert.setHeaderText("Error: Could not update Employee, please try again!");
                alert.showAndWait();
            }

        }
        else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: You need to enter all the fields correctly!");
            alert.setHeaderText("please try again!");
            alert.showAndWait();
        }

    }

    @FXML
    private void deleteWerknemer (ActionEvent actionEvent){

        if(removeIdBox.getText() != null && removeIdBox.getText().contains("ID")){

        Werknemer werknemer = new Werknemer();
        werknemer.setActief(false);
        werknemer.setWerknemerId(Integer.parseInt(removeIdBox.getText()));

        boolean succesDeleteWerknemer = WerknemerDAO.deleteWerknemer(werknemer);

        if(succesDeleteWerknemer) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Employee 'Deleted'");
            alert.setHeaderText("The employee has been succesfully 'deleted'.");
            alert.showAndWait();
            refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: Could not delete Employee");
            alert.setHeaderText("Error: Could not delete Employee, please try again!");
            alert.showAndWait();
        }
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Please select a user in the table");
            alert.setHeaderText("please try again!");
            alert.showAndWait();
        }

    }

    @FXML
    private void resetWerknemer (ActionEvent actionEvent) {

        if(resetIdBox.getText() != null && resetIdBox.getText().contains("ID")) {

            reset.getLogin().setPassword(Login.createHash(reset.getVoornaam() + reset.getWerknemerId()));
            reset.getLogin().setMedewerker_id(reset.getWerknemerId());
            boolean succesResetWerknemer = LoginDAO.changePassbyMedewerker(reset.getLogin());

            if (succesResetWerknemer) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee Password Reset");
                alert.setHeaderText("The employee has been succesfully Reset.");
                alert.setContentText("Password: " + reset.getVoornaam() + reset.getWerknemerId());
                alert.showAndWait();
                refresh();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error: Could not reset Employee");
                alert.setHeaderText("Error: Could not reset Employee, please try again!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Please select a user in the table");
            alert.setHeaderText("please try again!");
            alert.showAndWait();
        }
    }
}