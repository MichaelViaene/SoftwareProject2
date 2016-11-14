package com.ehbrail;

/**
 *
 * @author Vik Mortier
 **/
import com.database.LoginDAO;
import com.model.Login;
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
import static com.ehbrail.WerknemerController.getXML;

import static com.model.Login.verifyPassword;

/** Controls the login screen interactions **/
public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;

    private static ArrayList<String> list;

    public static ArrayList<String> getList() {
        return list;
    }
    public static void setList(ArrayList<String> list) {
        LoginController.list = list;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(() -> {
            list = getXML();
            setList(list);
            //list.forEach(System.out::println);
        }).start();

    }

    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        LoginDAO logindao = new LoginDAO();
        Login login = logindao.getLoginByUsername(username.getText());

        if (login.getUsername() != null){
            //TODO check of medewerker op medewerker_id actief is, Anders Label output dat het offline is.
            if(username.getText().equals(login.getUsername()) && verifyPassword(password.getText(),login.getPassword()))
            {
                ((Node)event.getSource()).getScene().getWindow().hide();
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
                    adminController.setUser(login.getUsername());
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
                    werknemerController.setLogin(login);
                    stage.show();
                }
            }
            else {message.setText("Username or Password invalid");}
        }
        else {message.setText("Unexpected error: Check DB conn and VPN");}
    }
}