package com.ehbrail;

/**
 *
 * @author Vik Mortier
 **/

import com.database.LoginDAO;
import com.model.Login;

import com.model.Werknemer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.database.Database.testConn;
import static com.database.WerknemerDAO.getWerkById;
import static com.ehbrail.WerknemerController.fillListAllStations;
import static com.ehbrail.WerknemerController.getAllStationsXMLtoList;
import static com.model.Login.verifyPassword;

/** Controls the login screen interactions **/
public class LoginController implements Initializable {

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Label message;

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
    private void loginAction(ActionEvent event) throws IOException {
        if (!(username.getText().isEmpty() || password.getText().isEmpty())) {
            boolean testConn = testConn();
            //System.out.println(testConn);
            if (testConn) {
                LoginDAO logindao = new LoginDAO();
                Login login = logindao.getLoginByUsername(username.getText());
            //TODO check of medewerker op medewerker_id actief is, Anders Label output dat de login niet actief is.
            if (username.getText().equals(login.getUsername()) && verifyPassword(password.getText(), login.getPassword())) {
                Werknemer werknemer = getWerkById(login.getMedewerker_id());
                if (werknemer.isActief()){
                ((Node) event.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                Region root;
                stage.getIcons().add(new Image("com/ehbrail/EHBRail.png"));
                if (login.getBevoegdheid() == Login.Bevoegdheid.ADMIN) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
                    root = (Region) loader.load();
                    stage.setMaximized(true);
                    stage.setTitle("EhB-Rail  |  ADMIN");
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    AdminController adminController = loader.<AdminController>getController();
                    adminController.setTopBar(login,werknemer);
                    stage.show();
                }
                if (login.getBevoegdheid() == Login.Bevoegdheid.WERKNEMER) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Werknemer.fxml"));
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
                } else message.setText("Account:"+login.getUsername()+" is niet actief");
            } else {message.setText("Username or Password invalid");}
        } else {message.setText("Unexpected error: Check DB conn and VPN");}
      }
      else message.setText("Vul gebruikersnaam en wachtwoord in");
    }
}