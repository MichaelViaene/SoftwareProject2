package com.ehbrail;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;

/**
*
* @author Vik Mortier
* voor javaFX toe te voegen gebruik: https://www.eclipse.org/efxclipse/install.html#for-the-lazy
* Hier zit de void Main();
*/

public class SoftwareProject extends Application {
   
   @Override
   public void start(Stage stage) throws Exception {
       try {
           Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
           Scene scene = new Scene(root);
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

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
       launch(args);
   }
   
}