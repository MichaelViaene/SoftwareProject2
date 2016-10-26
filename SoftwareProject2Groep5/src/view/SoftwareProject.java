package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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