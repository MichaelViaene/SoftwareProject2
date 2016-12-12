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
public class aPasswordChangeTabController implements Initializable {
	private ResourceBundle language;
    @FXML private Label accountLabel;
    @FXML private PasswordField oldPasswordField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField copyNewPasswordField;
    @FXML private Button        changePassButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	language = resources;
        accountLabel.setText(AdminController.getLogin().getUsername());
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
        Login login = getLoginByID(AdminController.getLogin().getLogin_id());
        if (!oldPasswordField.getText().isEmpty() && !newPasswordField.getText().isEmpty() && !copyNewPasswordField.getText().isEmpty()) {
            if ( Login.verifyPassword(oldPasswordField.getText(),login.getPassword())){
                if (newPasswordField.getText().equals(copyNewPasswordField.getText())){
                    String newHashPass = Login.createHash(newPasswordField.getText());
                    if (changePassbyLogin(login,newHashPass)){
                        oldPasswordField.clear();
                        newPasswordField.clear();
                        copyNewPasswordField.clear();
                        Notifications.create()
                                .title(language.getString("succes"))
                                .text(language.getString("notifSucces"))
                                .darkStyle()
                                .position(Pos.TOP_CENTER)
                                .graphic(new ImageView(img))
                                .show();
                    }
                    else createAlertBox(language.getString("oops"),null,language.getString("failNewPassword"));
                }
                else createAlertBox(language.getString("wrongPassword"),null,language.getString("passwordNotEqual"));
            }
           else createAlertBox(language.getString("wrongPassword"),null,language.getString("passwordNotCorrect"));
        }
        else createAlertBox(language.getString("ongeldigeVelden"),null,language.getString("ongeldigeVelden"));
    }
}
