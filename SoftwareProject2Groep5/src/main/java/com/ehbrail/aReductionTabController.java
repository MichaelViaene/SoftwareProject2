package com.ehbrail;

import com.database.FormuleDAO;
import com.database.KortingDAO;
import com.model.Formule;
import com.model.Korting;
import com.model.Liveboard;
import com.model.Login;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.database.LoginDAO.changePassbyLogin;
import static com.database.LoginDAO.getLoginByID;
import static com.database.FormuleDAO.getFormuleActive;
import static com.database.FormuleDAO.updateFormule;
import static com.database.FormuleDAO.insertFormule;


/**
 * @author Michael
 */
public class aReductionTabController implements Initializable {
    @FXML private TextField newKortingNaam;
    @FXML private TextArea newKortingBeschrijving;
    @FXML private TextField newKortingPercentage;
    @FXML private Button changeReductionButton;
    @FXML private TableView<Korting> tableKorting;
    @FXML private TableColumn<Korting, String> kortingNaam;
    @FXML private TableColumn<Korting, String> kortingBeschrijving;
    @FXML private TableColumn<Korting,Integer> kortingPercentage;
    @FXML private TableColumn<Korting, Boolean> kortingActief;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	kortingNaam.setCellValueFactory(new PropertyValueFactory<Korting, String>("naam"));
    	kortingBeschrijving.setCellValueFactory(new PropertyValueFactory<Korting, String>("beschrijving"));
    	kortingPercentage.setCellValueFactory(new PropertyValueFactory<Korting, Integer>("percentage"));
    	kortingActief.setCellValueFactory(new PropertyValueFactory<Korting, Boolean>("actief"));
    	ObservableList<Korting> data = FXCollections.observableArrayList();
    	List<Korting> tussen=KortingDAO.getKortingen();
    	for(Korting k:tussen){
    		data.add(new Korting(k.getKorting(),
    				k.getNaam(),
    				k.getBeschrijving(),
    				k.getPercentage(),
    				k.isActief()));
    	}
    	tableKorting.setItems(data);    	
    }

    private void createAlertBox(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    @FXML
    private void changeReductionAction(ActionEvent event) throws IOException {
        Image img = new Image("/com/ehbrail/checkmark.png");
        Login login = getLoginByID(AdminController.getLogin().getLogin_id());
    }
    
}
