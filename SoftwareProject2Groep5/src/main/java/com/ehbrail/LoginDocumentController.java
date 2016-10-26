package com.ehbrail;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Vik Mortier
 */
public class LoginDocumentController implements Initializable {
    
    @FXML
    private Label loginConfirm;
    
    @FXML
    private TextField userField;
     
    @FXML
    private TextField passField;
    
    @FXML
    private Button loginButton;
     
    @FXML
    private void login (ActionEvent event) throws IOException {
        
        String user = userField.getText();
        String pass = passField.getText();
        
        //hashing logic (Converten naar de juiste encryptie).
        //connection logic (Vergelijken met de DB).
        
        Boolean succes = false;
        if(succes == true) {
                loginConfirm.setText("Username or Password Incorrect");
                loginConfirm.setTextFill(Color.RED);
        } else {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
             
            Stage mainStage = new Stage();
            Parent root;
          
            root = FXMLLoader.load(getClass().getResource("User.fxml"));
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
