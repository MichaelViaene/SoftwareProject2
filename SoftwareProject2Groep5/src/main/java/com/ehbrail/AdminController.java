package com.ehbrail;

import com.model.Login;
import com.model.Werknemer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    private static Login login;
    public static Login getLogin() {
        return login;
    }
    public static void setLogin(Login login) {
        AdminController.login = login;
    }

    Werknemer werknemer;

    @FXML Label usernameAdm;

    public Werknemer getWerknemer() {
        return werknemer;
    }
    public void setWerknemer(Werknemer werknemer) {
        this.werknemer = werknemer;
    }


    public void setTopBar(Login login, Werknemer werknemer){
        this.login = login;
        this.werknemer = werknemer;
        usernameAdm.setText("Welkom, " + werknemer.getVoornaam() +" " + werknemer.getNaam() +"! username: "+ login.getUsername() + " met bevoegdheid:"+ login.getBevoegdheid());
    }

    @FXML private Button logoutButton;
    @FXML private TabPane aTabPane;
    @FXML private Tab aEmployeeTab;
    @FXML private aEmployeeTabController aEmployeeTabPageController;
    @FXML private Tab aPasswordChangeTab;
    @FXML private aPasswordChangeTabController aPasswordChangeTabController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML private void onClickLogOut(ActionEvent event) throws IOException {
        logoutButton.getScene().getWindow().hide();
        //meld dat je graag een garbage collection wilt doen.
        System.gc();

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("EhB-Rail  |  Login");
        stage.getIcons().add(new Image("com/ehbrail/EHBRail.png"));
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }

}
