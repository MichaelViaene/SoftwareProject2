package com.ehbrail;

/**
 * Created by Vik Mortier on 14/11/2016.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class aEmployeeTabController {

    @FXML
    private Label errorRequestLabel;

    @FXML
    private TextField filterBox;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> stopID;

    @FXML
    private TableColumn<?, ?> station;

    @FXML
    private TableColumn<?, ?> time;

    @FXML
    private TableColumn<?, ?> platform;

    @FXML
    private TextField addNameBox;

    @FXML
    private TextField addSurnameBox;

    @FXML
    private ChoiceBox<?> addAuthCBox;

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
    private ChoiceBox<?> editAuthCBox;

    @FXML
    private Button editButton;

    @FXML
    private Label editIdLabel;

    @FXML
    private CheckBox activateCheckBox;

    @FXML
    void searchAction(ActionEvent event) {

    }
}
