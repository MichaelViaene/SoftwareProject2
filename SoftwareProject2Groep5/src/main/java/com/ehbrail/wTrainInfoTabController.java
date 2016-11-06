package com.ehbrail;

import com.model.TrainInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import static com.ehbrail.WerknemerController.convertISO8601;
import static com.ehbrail.WerknemerController.getHTTPSResponseCode;

/**
 * Created by jorda on 28/10/2016.
 */
public class wTrainInfoTabController implements Initializable {

    @FXML
    Label errorRequestLabel;
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

    @FXML
    private void searchAction(ActionEvent event) throws IOException {
        try {
            //vb treinID = IC545    S23671
            errorRequestLabel.setText("");
            URL url = new URL("https://api.irail.be/vehicle/?id=BE.NMBS."+treinID.getText()+"&format=xml");
            int responseCode = getHTTPSResponseCode(url);
            if (responseCode == 200){
                SAXReader reader = new SAXReader();
                Document document = reader.read(url);

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
            else{
                errorRequestLabel.setText("Error occured, response code: "+responseCode + ". TreinID bestaat niet");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}