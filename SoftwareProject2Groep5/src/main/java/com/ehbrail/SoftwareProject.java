package com.ehbrail;


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
*
* @author Vik Mortier
* voor javaFX toe te voegen gebruik: https://www.eclipse.org/efxclipse/install.html#for-the-lazy
*/

public class SoftwareProject extends Application {
   
   @Override
   public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));       
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.setTitle("TreinSoftware User Auth");
       stage.setResizable(false);
       stage.show();            
   }

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
       launch(args);
   }
   
}