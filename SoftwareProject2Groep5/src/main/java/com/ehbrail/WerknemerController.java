package com.ehbrail;

import com.model.TrainInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.List;

import javafx.scene.control.cell.PropertyValueFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import java.time.format.DateTimeFormatter;


/**
 * Created by jorda on 26/10/2016.
 * Bij Errors over "Could not generate DH keypair"  moet je Java Cryptography Extension (JCE) Unlimited Strength downloaden
 * en in uw jre folder zetten.
 * voor de huidige versie Java8         http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
 *
 */
public class WerknemerController implements Initializable{

    String user;

    public void setUser(String user) {
        this.user = user;
        usernameWerknemer.setText("Welcome, " + user);
    }
    public void setUsernameAdm(Label username) {
        this.usernameWerknemer = username;
    }

    @FXML
    Label usernameWerknemer;
    @FXML
    private Label totalStops;
    @FXML
    private Button search;
    @FXML
    private TextField treinID;

    @FXML private TableView<TrainInfo> tableView;
    @FXML private TableColumn<TrainInfo,Integer> stopID;
    @FXML private TableColumn<TrainInfo, String> station;
    @FXML private TableColumn<TrainInfo, LocalDateTime> time;
    @FXML private TableColumn<TrainInfo, String> platform;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stopID.setCellValueFactory(new PropertyValueFactory<TrainInfo, Integer>("stopID"));
        station.setCellValueFactory(new PropertyValueFactory<TrainInfo, String>("station"));
        time.setCellValueFactory(new PropertyValueFactory<TrainInfo, LocalDateTime>("time"));
        platform.setCellValueFactory(new PropertyValueFactory<TrainInfo, String>("platform"));

    }

    private LocalDateTime convertISO8601 (String time){;
        //String s = "2016-10-26T22:22:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        return dateTime;
    }

    @FXML
    private void searchAction(ActionEvent event) throws IOException {
        try {
            treinID.getText();
            SAXReader reader = new SAXReader();
            //vb treinID = IC545
            Document document = reader.read("https://api.irail.be/vehicle/?id=BE.NMBS."+treinID.getText()+"&format=xml");
            //Element classElement = document.getRootElement();
            List<Node> nodes = document.selectNodes("vehicleinformation/stops/stop[@id]");
            Node totalStopNode = document.selectSingleNode("vehicleinformation/stops[@number]");
            totalStops.setText("Total stops: " + totalStopNode.valueOf("@number"));
            ObservableList<TrainInfo> data = FXCollections.observableArrayList();
            for (Node node: nodes){
                data.add(new TrainInfo(Integer.parseInt(node.valueOf("@id")),
                        node.selectSingleNode("station").getText(),
                        convertISO8601(node.selectSingleNode("time").valueOf("@formatted")),
                        node.selectSingleNode("platform").getText()
                ));
            }
            tableView.setItems(data);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
