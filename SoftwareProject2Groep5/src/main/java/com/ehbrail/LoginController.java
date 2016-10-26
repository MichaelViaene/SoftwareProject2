package com.ehbrail;

/**
 *
 * @author Vik Mortier
 */
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
import java.util.ResourceBundle;

/** Controls the login screen interactions **/
public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        LoginDAO logindao = new LoginDAO();
        Login login = logindao.getLoginByUsername(username.getText());

        if (login.getUsername() != null){
            if(username.getText().equals(login.getUsername()) && password.getText().equals(login.getPassword()))
            {
                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                Region root;
                stage.getIcons().add(new Image("com/ehbrail/EHBRail.png"));
                if (login.getBevoegdheid() == Login.Bevoegdheid.ADMIN) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
                    root = (Region) loader.load();
                    stage.setTitle("EhB-Rail  |  ADMIN");
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    AdminController adminController = loader.<AdminController>getController();
                    adminController.setUser(login.getUsername());
                    stage.show();
                }
                //TODO Create WERKNEMER fxml / controller etc.

            }
            else {message.setText("Username or Password invalid");}
        }
        else {message.setText("Unexpected error: Check DB conn and VPN");}
    }
}