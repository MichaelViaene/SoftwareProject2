package com.ehbrail;

import com.model.Login;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

    String user;

    @FXML Label usernameAdm;

    public void setUser(String user) {
        this.user = user;
        usernameAdm.setText("Welcome, " + user);
    }

    public void setUsernameAdm(Label usernameAdm) {
        this.usernameAdm = usernameAdm;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
