package com.ehbrail;

import com.model.Login;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import okhttp3.Response;
import org.boon.core.Sys;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.net.ssl.HttpsURLConnection;
import java.time.format.DateTimeFormatter;

import static com.ehbrail.ApiCalls.getStationsXML;

/**
 * Created by jorda on 26/10/2016.
 * Bij Errors over "Could not generate DH keypair"  moet je Java Cryptography Extension (JCE) Unlimited Strength downloaden
 * en in uw jre folder zetten.
 * voor de huidige versie Java8         http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
 *
 */
public class WerknemerController implements Initializable{

    public static LocalDateTime convertISO8601 (String time){;
        //String s = "2016-10-26T22:22:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        return dateTime;
    }

    Login login;
    @FXML Label usernameWerknemer;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
        usernameWerknemer.setText("Welcome, " + login.getUsername() + " met bevoegdheid:"+ login.getBevoegdheid());
    }


    @FXML private TabPane wtabPane;
    
    @FXML private Tab wTrainInfoTab;
    @FXML private wTrainInfoTabController wTrainInfoTabPageController;
    
    @FXML private Tab wRouteInfoTab;
    @FXML private wRouteInfoTabController wRouteInfoTabPageController;
    
    @FXML private Tab wVerlorenVoorwerpTab;
    @FXML private VerlorenVoorwerpTabController wVerlorenVoorwerpTabPageController;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static ArrayList<String> getAllStationsXMLtoList(){
        ArrayList<String> list = new ArrayList<>();
        try {
            Response response =  getStationsXML();
            if (response.isSuccessful()) {
                String xmlString = response.body().string();
                SAXReader reader = new SAXReader();
                Document document = reader.read(new InputSource(new StringReader(xmlString)));
                List<Node> nodes = document.selectNodes("stations/station");
                for (Node node: nodes){
                    list.add(node.valueOf("@standardname"));
                }
            } else {
                System.out.println("Error code: " + response.networkResponse().code() + " With errormessage: " +response.message());
                list.add(" ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

}
