package com.ehbrail;

import com.model.Login;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jorda on 25/10/2016.
 */

public class AdminController implements Initializable {
    //private Login login;

    Login login;




    @FXML Label usernameAdm;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
        usernameAdm.setText("Welcome, " + login.getUsername() + " met bevoegdheid:"+ login.getBevoegdheid());
    }


    @FXML private TabPane aTabPane;
    @FXML private Tab aEmployeeTab;
    @FXML private aEmployeeTabController aEmployeeTabPageController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
