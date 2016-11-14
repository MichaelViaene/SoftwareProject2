package com.ehbrail;

import com.model.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.database.LoginDAO.changePassbyLogin;
import static com.database.LoginDAO.getLoginByID;


/**
 * Created by jorda on 10/11/2016.
 */
public class wPasswordChangeTabController implements Initializable {
    @FXML Label accountLabel;
    @FXML PasswordField oldPasswordField;
    @FXML PasswordField newPasswordField;
    @FXML PasswordField copyNewPasswordField;
    @FXML Button        changePassButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountLabel.setText(WerknemerController.getLogin().getUsername());
    }

    private void createAlertBox(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    @FXML
    private void changePassAction(ActionEvent event) throws IOException {
        Image img = new Image("/com/ehbrail/checkmark.png");
        Login login = getLoginByID(WerknemerController.getLogin().getLogin_id());
        if (!oldPasswordField.getText().isEmpty() && !newPasswordField.getText().isEmpty() && !copyNewPasswordField.getText().isEmpty()) {
            if ( Login.verifyPassword(oldPasswordField.getText(),login.getPassword())){
                if (newPasswordField.getText().equals(copyNewPasswordField.getText())){
                    String newHashPass = Login.createHash(newPasswordField.getText());
                    if (changePassbyLogin(login,newHashPass)){
                        oldPasswordField.clear();
                        newPasswordField.clear();
                        copyNewPasswordField.clear();
                        Notifications.create()
                                .title("Succes")
                                .text("Succesfully set a new password!")
                                .darkStyle()
                                .position(Pos.TOP_CENTER)
                                .graphic(new ImageView(img))
                                .show();
                    }
                    else createAlertBox("Oops, Something went wrong!",null,"Failed to set new password!");
                }
                else createAlertBox("Wrong Password",null,"Beide nieuwe wachtwoorden zijn niet aan elkaar gelijk!");
            }
           else createAlertBox("Wrong Password",null,"Wachtwoord is niet correct!");
        }
        else createAlertBox("Ongeldige velden",null,"Alle velden moeten ingevuld worden.");
    }
}
