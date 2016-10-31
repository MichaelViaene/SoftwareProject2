package com.ehbrail;

import com.model.Login;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javax.net.ssl.HttpsURLConnection;
import java.time.format.DateTimeFormatter;

/**
 * Created by jorda on 26/10/2016.
 * Bij Errors over "Could not generate DH keypair"  moet je Java Cryptography Extension (JCE) Unlimited Strength downloaden
 * en in uw jre folder zetten.
 * voor de huidige versie Java8         http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
 *
 */
public class WerknemerController implements Initializable{

    public static int getHTTPSResponseCode(URL url) throws IOException {
        HttpsURLConnection https = (HttpsURLConnection)url.openConnection();
        https.setRequestMethod("GET");
        int statusCode = https.getResponseCode();
        System.out.println(statusCode);
        return statusCode;
    }

    public static LocalDateTime convertISO8601 (String time){;
        //String s = "2016-10-26T22:22:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        return dateTime;
    }

    Login login;
    String user;
    @FXML Label usernameWerknemer;


    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
        usernameWerknemer.setText("Welcome, " + login.getUsername() + " met bevoegdheid:"+ login.getBevoegdheid());
    }

    public void setUser(String user) {
        this.user = user;
        usernameWerknemer.setText("Welcome, " + user);
    }
    public void setUsernameAdm(Label username) {
        this.usernameWerknemer = username;
    }

    @FXML private TabPane wtabPane;
    @FXML private Tab wTrainInfoTab;
    @FXML private wTrainInfoTabController wTrainInfoTabPageController;
    @FXML private Tab wRouteInfoTab;
    @FXML private wRouteInfoTabController wRouteInfoTabPageController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
