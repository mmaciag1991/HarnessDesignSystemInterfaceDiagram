/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateusz Maciag
 */
public class DBConnection {

    Properties properties = new Properties();
    InputStream inputStream;

    public Connection con;

    String url;
    String urldb;
    String user;
    String pass;
    String wina;
    String db;
    //String unicode = "?useUnicode=yes&characterEncoding=UTF-8";

    public void loadPropertiesFile() {
        try {
            properties = new DBProperties().loadAndGetPropertiesFile();
            wina = properties.getProperty("winautentation");
            db = properties.getProperty("db");
            if (wina.equals("true")){
                url = "jdbc:sqlserver://" + properties.getProperty("host") + ":" + properties.getProperty("port") + ";integratedSecurity=true";
                urldb = "jdbc:sqlserver://" + properties.getProperty("host")+"/"+ db + ":" + properties.getProperty("port") + ";integratedSecurity=true";
                user = "";
                pass = "";
            }else{
                url = "jdbc:sqlserver://" + properties.getProperty("host") + ":" + properties.getProperty("port") + "";
                urldb = "jdbc:sqlserver://" + properties.getProperty("host")+"/"+ db + ":" + properties.getProperty("port") + "";
                user = properties.getProperty("user");
                pass = properties.getProperty("password");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection mkDataBase() throws SQLException {
        loadPropertiesFile();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
        return con;
    }
    final Image red = new Image("resources/image/icon_red.png");
    final Image green = new Image("resources/image/icon_green.png");
    final Image blue = new Image("resources/image/icon.png");

    public Connection geConnection() {
        loadPropertiesFile();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url /*+ unicode*/, user, pass);
//            Platform.runLater(() -> {  ApplicationController.logo.setImage(green);});
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            Platform.runLater(() -> {  ApplicationController.logo.setImage(blue);});
        } catch (ClassNotFoundException | SQLException ex) {
//            Platform.runLater(() -> {  ApplicationController.logo.setImage(red);});
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }
    public Connection mkDataBaseDB() throws SQLException {
        loadPropertiesFile();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(urldb, user, pass);
            PreparedStatement pst = con.prepareStatement("USE "+db);
            pst.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
        return con;
    }

    public Connection geConnectionDB() {
        loadPropertiesFile();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(urldb /*+ unicode*/, user, pass);
            PreparedStatement pst = con.prepareStatement("USE "+db);
            pst.execute();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);

//            Platform.runLater(()-> ApplicationController.logo.setImage(new Image("resources/image/icon_red.png")));

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd");
            alert.setHeaderText("Sesja wygasła lub połączenie bazy danych jest nie skonfigurowane.");
            alert.setContentText(ex.getMessage());
            alert.getDialogPane().getStylesheets().add("/resources/style/jmetro/JMetroDarkTheme.css");
            alert.getDialogPane().getStyleClass().add("table-view");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/resources/image/database.png"));
            alert.initStyle(StageStyle.UNDECORATED);


            ButtonType btconf = new ButtonType("Otwórz Konfigurację Bazy Danych");
            ButtonType buttonTypeCancel = new ButtonType("Wyjdż z programu", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll( btconf, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == btconf){
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/view/Server.fxml"));
                    Scene scene = new Scene(root);
                    Stage nStage = new Stage();
                    nStage.setScene(scene);
                    nStage.setMaximized(false);
                    nStage.setTitle("Konfiguracja serwera");
                    nStage.getIcons().add(new Image("/resources/image/database.png"));
                    nStage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.exit(1);
            }
        }
        return con;
    }
}
