package com.ehbrail;

import com.model.Login;
import com.model.Werknemer;
import com.sun.istack.internal.logging.Logger;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
*
* @author Vik Mortier
* voor javaFX toe te voegen gebruik: https://www.eclipse.org/efxclipse/install.html#for-the-lazy
* Hier zit de void Main();
*/

public class SoftwareProject extends Application {
	final static Logger logger = Logger.getLogger(SoftwareProject.class);
	
    private BorderPane borderPane = new BorderPane();
   @Override
   public void start(Stage stage) throws Exception {
      createLoginScreen(stage);
   }

   public void createLoginScreen(Stage stage){
       try {
           loadView(new Locale("NL"));
           borderPane.setTop(createComboBox());
           Scene scene = new Scene(borderPane);
           stage.setTitle("EhB-Rail  |  Login");
           stage.getIcons().add(new Image("com/ehbrail/EHBRail.png"));
           scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
           stage.setScene(scene);
           stage.setResizable(false);
           stage.show();
       }
       catch (Exception e){
           e.printStackTrace();
       }
   }


    private ComboBox<String> createComboBox() {
        ComboBox<String> comboBox = new ComboBox<>();
        ObservableList<String> options = FXCollections.observableArrayList("NL","FR","EN");
        comboBox.setItems(options);
        comboBox.setValue(options.get(0));
        comboBox.getSelectionModel().selectFirst();
        comboBox.setOnAction(event -> loadView(new Locale(comboBox.getSelectionModel().getSelectedItem())));
        return comboBox;
    }

   private void loadView(Locale locale){
       try {
           FXMLLoader fxmlLoader = new FXMLLoader();
           fxmlLoader.setResources(ResourceBundle.getBundle("Language", locale));
           Pane pane = (AnchorPane) fxmlLoader.load(this.getClass().getResource("Login.fxml").openStream());
           borderPane.setCenter(pane);
       }
       catch (IOException e){e.printStackTrace();}
   }


   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
	   logger.info("Test");
       launch(args);
   }
   
}