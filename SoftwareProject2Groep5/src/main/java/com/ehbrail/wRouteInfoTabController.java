package com.ehbrail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jorda on 28/10/2016.
 */
public class wRouteInfoTabController implements Initializable {

    @FXML private Button switchStationsButton;
    @FXML private TextField vanField;
    @FXML private TextField naarField;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
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










}
