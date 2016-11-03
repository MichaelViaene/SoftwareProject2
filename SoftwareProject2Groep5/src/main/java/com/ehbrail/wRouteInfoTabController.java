package com.ehbrail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.model.Routes.*;
import static com.ehbrail.ApiCalls.getIRailRoute;

/**
 * Created by jorda on 28/10/2016.
 */
public class wRouteInfoTabController implements Initializable {

    @FXML private Button switchStationsButton;
    @FXML private Button planRouteButton;
    @FXML private TextField vanField;
    @FXML private TextField naarField;
    @FXML TreeView<String> treeRoute;
    @FXML Label errorLabel;
    ArrayList<String> list;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = LoginController.getList();
        TextFields.bindAutoCompletion(vanField,list);
        TextFields.bindAutoCompletion(naarField,list);
    }

   @FXML private void switchStations(ActionEvent event) throws IOException {
        try {
            String temp = vanField.getText();
            vanField.setText(naarField.getText());
            naarField.setText(temp);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


    @FXML
    private void onClickplanRoute(ActionEvent event) throws IOException {
        try {
            String jsonString = getIRailRoute(vanField.getText(), naarField.getText());
            if ("BadResponse".equals(jsonString)) {
                errorLabel.setText("Error, response = " + jsonString);
                TreeItem<String> rootItem = new TreeItem<String>("Routes");
                treeRoute.setRoot(rootItem);
            } else {
                ObjectMapper mapper = JsonFactory.create();
                IrailRoute irailRoute = mapper.readValue(jsonString, IrailRoute.class);
                TreeItem<String> rootItem = new TreeItem<String>("Routes");
                createTreeItems(rootItem, irailRoute);
                rootItem.setExpanded(true);
                treeRoute.setRoot(rootItem);
                errorLabel.setText(" ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void createTreeItems(TreeItem<String> rootItem, IrailRoute irailRoute) throws IOException {
        TreeItem<String> rootItem1 = null;
        for (Connection connection : irailRoute.getConnection()) {
            String departureTimeString = connection.getDeparture().getTime();
            String arrivalTimeString = connection.getArrival().getTime();
            //LocalDateTime departureTime = toLocalDateTime(departureTimeString);
            //LocalDateTime arrivalTime = toLocalDateTime(arrivalTimeString);
            String duration = connection.getDuration();
            String departureStation = connection.getDeparture().getStation();
            String arrivalStation = connection.getArrival().getStation();
            String transfers = " ";

            if (connection.getVias() != null) {
                transfers = "  -  "+connection.getVias().getNumber()+"  transfers";
            }

            rootItem1 = new TreeItem<String>(toLocalDateTime(departureTimeString).toLocalTime() + " > " + toLocalDateTime(arrivalTimeString).toLocalTime() + "\n" + (Integer.parseInt(duration) / 60) + " minuten"+transfers);
            TreeItem<String> line1 = new TreeItem<String>(toLocalDateTime(departureTimeString).toLocalTime() + "\t" + departureStation + "\t Delay: " + (Integer.parseInt(connection.getDeparture().getDelay()) / 60) + " minuten\t " + connection.getDeparture().getPlatform());
            TreeItem<String> line2 = new TreeItem<String>(connection.getDeparture().getDirection().getName() + " " + connection.getDeparture().getVehicle());
            rootItem1.getChildren().add(line1);
            rootItem1.getChildren().add(line2);

            if (connection.getVias() != null) {
                for (Via via : connection.getVias().getVia()) {
                    TreeItem<String> line4 = new TreeItem<String>(toLocalDateTime(via.getArrival().getTime()).toLocalTime() + " " + via.getStationinfo().getName() + " " + via.getArrival().getPlatform());
                    TreeItem<String> line5 = new TreeItem<String>(Integer.parseInt(via.getTimeBetween()) / 60 + " minutes before departure from platform  " + via.getArrival().getPlatform() + " to platform " + via.getDeparture().getPlatform());
                    TreeItem<String> line6 = new TreeItem<String>(toLocalDateTime(via.getDeparture().getTime()).toLocalTime() + " " + via.getStationinfo().getName() + " " + via.getDeparture().getPlatform());
                    rootItem1.getChildren().add(line4);
                    rootItem1.getChildren().add(line5);
                    rootItem1.getChildren().add(line6);
                }
                TreeItem<String> line7 = new TreeItem<String>(connection.getArrival().getDirection().getName() + " " + connection.getArrival().getVehicle());
                rootItem1.getChildren().add(line7);
            }
            TreeItem<String> line8 = new TreeItem<String>(toLocalDateTime(arrivalTimeString).toLocalTime() + "\t" + arrivalStation +"\t Delay: " + (Integer.parseInt(connection.getArrival().getDelay()) / 60) + " minuten\t " + connection.getArrival().getPlatform());
            rootItem1.getChildren().add(line8);
            rootItem.getChildren().add(rootItem1);
        }
    }

    public static LocalDateTime toLocalDateTime(String time){
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(time) * 1000), ZoneId.systemDefault());
        return localDateTime;
    }


}
