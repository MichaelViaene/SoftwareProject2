package com.ehbrail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jorda on 26/10/2016.
 */
public class WerknemerController implements Initializable{

    String user;

    @FXML
    Label usernameWerknemer;

    @FXML
    private Button search;

    @FXML
    private TextField treinID;

    public void setUser(String user) {
        this.user = user;
        usernameWerknemer.setText("Welcome, " + user);
    }

    public void setUsernameAdm(Label username) {
        this.usernameWerknemer = username;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void searchAction(ActionEvent event) throws IOException {
        treinID.getText();

    }


}
