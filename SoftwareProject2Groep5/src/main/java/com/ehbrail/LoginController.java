package com.ehbrail;

/**
 *
 * @author Vik Mortier
 **/

import com.database.LoginDAO;
import com.model.Login;

import com.model.Werknemer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.database.DataSource.testConn;
import static com.database.WerknemerDAO.getWerkById;
import static com.ehbrail.WerknemerController.fillListAllStations;
import static com.ehbrail.WerknemerController.getAllStationsXMLtoList;
import static com.model.Login.verifyPassword;

/** Controls the login screen interactions **/
public class LoginController implements Initializable {
    private ResourceBundle language;
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Label message;
    @FXML private Button login;
    private static ArrayList<String> list;

    public static ArrayList<String> getList() {
        return list;
    }
    public static void setList(ArrayList<String> list) {
        LoginController.list = list;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = fillListAllStations();
        language = resources;

        /**
         * NIET VERWIJDEREN!!
        new Thread(() -> {
            list = getAllStationsXMLtoList();
            setList(list);
            //list.forEach(System.out::println);
        }).start();
         **/
    }

    @FXML
    private void goToPasswordField(KeyEvent ke){
    	if(ke.getCode() == KeyCode.ENTER){
    		password.requestFocus();
    	}
    }
    
    @FXML
    private void goToLogin(KeyEvent ke){
    	if(ke.getCode() == KeyCode.ENTER){
    		login.fire();    	}
    }

    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        if (!(username.getText().isEmpty() || password.getText().isEmpty())) {
            if (testConn()) {
                LoginDAO logindao = new LoginDAO();
                Login login = logindao.getLoginByUsername(username.getText());
            if (username.getText().equals(login.getUsername()) && verifyPassword(password.getText(), login.getPassword())) {
                Werknemer werknemer = getWerkById(login.getMedewerker_id());
                if (werknemer.isActief()){
                ((Node) event.getSource()).getScene().getWindow().hide();
                WerknemerController.setLogin(login);
                Stage stage = new Stage();
                Region root;
                stage.getIcons().add(new Image("com/ehbrail/EHBRail.png"));
                if (login.getBevoegdheid() == Login.Bevoegdheid.ADMIN) {
                    AdminController.setLogin(login);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"),language);
                    root = (Region) loader.load();
                    stage.setMaximized(true);
                    stage.setTitle("EhB-Rail  |  ADMIN");
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    AdminController adminController = loader.<AdminController>getController();
                    adminController.setTopBar(werknemer);
                    stage.show();
                }
                if (login.getBevoegdheid() == Login.Bevoegdheid.WERKNEMER) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Werknemer.fxml"),language);
                    root = (Region) loader.load();
                    stage.setMaximized(true);
                    //stage.setFullScreen(true);
                    stage.setTitle("EhB-Rail  |  WERKNEMER");
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    WerknemerController werknemerController = loader.getController();
                    werknemerController.setTopBar(login, werknemer);
                    stage.show();
                }
                } else message.setText(language.getString("PasswordChange.account") + " " +login.getUsername()+ language.getString("nietActief"));
            } else {message.setText(language.getString("usernamePasswordInvalid"));}
        } else {message.setText(language.getString("unexpectedErrorDb"));}
      }
      else message.setText(language.getString("usernamePassword"));
    }
}