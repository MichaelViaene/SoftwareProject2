package com.ehbrail;

import com.model.Login;
import com.model.StationCSV;
import com.model.Werknemer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import okhttp3.Response;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.time.format.DateTimeFormatter;



import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;


import static com.ehbrail.ApiCalls.getStationsXML;

/**
 * Created by jorda on 26/10/2016.
 * Bij Errors over "Could not generate DH keypair"  moet je Java Cryptography Extension (JCE) Unlimited Strength downloaden
 * en in uw jre folder zetten.
 * voor de huidige versie Java8         http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
 *
 */
public class WerknemerController implements Initializable{

    private static Login login;
    public static Login getLogin() {
        return login;
    }
    public static void setLogin(Login login) {
        WerknemerController.login = login;
    }

    // Login login;
    private Werknemer werknemer;
    @FXML Label usernameWerknemer;

    public Werknemer getWerknemer() {
        return werknemer;
    }
    public void setWerknemer(Werknemer werknemer) {
        this.werknemer = werknemer;
    }

    public void setTopBar(Login login, Werknemer werknemer){
        this.login = login;
        //WerknemerController.login = login;
        this.werknemer = werknemer;
        usernameWerknemer.setText("Welkom, " + werknemer.getVoornaam() +" " + werknemer.getNaam() +"! username: "+ login.getUsername() + " met bevoegdheid:"+ login.getBevoegdheid());
    }

    @FXML private Button logoutButton;
    @FXML private TabPane wtabPane;
    
    @FXML private Tab wTrainInfoTab;
    @FXML private wTrainInfoTabController wTrainInfoTabPageController;
    
    @FXML private Tab wRouteInfoTab;
    @FXML private wRouteInfoTabController wRouteInfoTabPageController;

    @FXML private Tab wLiveboardTab;
    @FXML private wLiveboardTabController wLiveboardTabPageController;
    
    @FXML private Tab wVerlorenVoorwerpTab;
    @FXML private VerlorenVoorwerpTabController wVerlorenVoorwerpTabPageController;

    @FXML private Tab wPasswordChangeTab;
    @FXML private wPasswordChangeTabController wPasswordChangeTabController;
    
    @FXML private Tab wAbonnementTab;
    @FXML private wAbonnementTabController wAbonnementTabController;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML private void onClickLogOut(ActionEvent event) throws IOException {
        logoutButton.getScene().getWindow().hide();
        //meld dat je graag een garbage collection wilt doen.
        System.gc();


        Stage stage = new Stage();
        ResourceBundle language = ResourceBundle.getBundle("Language", new Locale("fr"));
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"),language);
        Scene scene = new Scene(root);
        stage.setTitle("EhB-Rail  |  Login");
        stage.getIcons().add(new Image("com/ehbrail/EHBRail.png"));
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }


    public static LocalDateTime convertISO8601 (String time){;
        //String s = "2016-10-26T22:22:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        return dateTime;
    }

    public static LocalDateTime toLocalDateTime(String time){
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(time) * 1000), ZoneId.systemDefault());
        return localDateTime;
    }

    public static ArrayList<String> getAllStationsXMLtoList(){
        ArrayList<String> list = new ArrayList<>();
        try {
            Response response =  getStationsXML();
            if (response.isSuccessful()) {
                String xmlString = response.body().string();
                SAXReader reader = new SAXReader();
                Document document = reader.read(new InputSource(new StringReader(xmlString)));
                List<Node> nodes = document.selectNodes("stations/station");
                for (Node node: nodes){
                    list.add(node.valueOf("@standardname"));
                }
            } else {
                System.out.println("Error code: " + response.networkResponse().code() + " With errormessage: " +response.message());
                list.add(" ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static ArrayList<String> fillListAllStations(){
        ArrayList<String> list = new ArrayList<>();
        CSVReader csvReader = null;
        try {
            /**
             * Reading the CSV File
             * Delimiter is comma
             * Default Quote character is double quote
             * Start reading from line 1
             */
            csvReader = new CSVReader(new FileReader("src/main/resources/com/ehbrail/stations.csv"),',','"',1);
            //mapping of columns with their positions
            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            //Set mappingStrategy type to StationsCSV Type
            mappingStrategy.setType(StationCSV.class);
            //Fields in StationCSV.java Bean
            String[] columns = new String[]{"Uri","name","nameFR","nameNL","nameDE","nameENG","countryCode","longitude","latitude"};
            //Setting the colums for mappingStrategy
            mappingStrategy.setColumnMapping(columns);
            //create instance for CsvToBean class
            CsvToBean ctb = new CsvToBean();
            //parsing csvReader(stations.csv) with mappingStrategy
            List<StationCSV> stationList = ctb.parse(mappingStrategy,csvReader);
            //Print the station Details
            for(StationCSV station: stationList)
            {
                //System.out.println(station.toString());
                //System.out.println(station.getName());
                list.add(station.getName());
            }

            }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try
            {
                //closing the reader
                csvReader.close();
            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }
        }
        return list;
    }
}
