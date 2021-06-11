/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Mateusz Maciag
 */
public class ServerController implements Initializable {

    @FXML
    private TextField tfHost;
    @FXML
    private TextField thPort;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Button btnConnect;
    @FXML
    private Button btnReset;
    @FXML
    private Label lablServerStatus;
    @FXML
    private TextField tfDBName;
    @FXML
    private TextField tfUserName;
    @FXML
    private CheckBox winautentathion;
    @FXML
    private ImageView serverstatus;
    Properties properties = new Properties();
    InputStream inputStream;
    OutputStream output = null;
    
    Connection con;
    
    String url;
    String user;
    String pass;
    String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
     String wina;
     String urldb;
     String db;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            checkSQLStatus();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getDataFromFile();

        winautentathion.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               if (newValue){
                   tfUserName.setDisable(true);
                   pfPassword.setDisable(true);
                   tfUserName.setText("");
                   pfPassword.setText("");
               }else{
                   tfUserName.setDisable(false);
                   pfPassword.setDisable(false);
                   tfUserName.setText(properties.getProperty("user"));
                   pfPassword.setText(properties.getProperty("password"));
               }
            }
        });
        
    }

    @FXML
    private void btnConnectOnAction(ActionEvent event) {
        mkDbProperties();

    }

    @FXML
    private void btnResetOnAction(ActionEvent event) throws SQLException {

        checkSQLStatus();
    }
    
    public void getDataFromFile(){
        try {
            String computername = InetAddress.getLocalHost().getHostName();
            if (computername.contains("DESKTOP-A3AGJ6G")){
                inputStream = new FileInputStream("database_DESKTOP_A3AGJ6G.properties");
            }else{
                inputStream = new FileInputStream("database.properties");
            }

            properties.load(inputStream);
            System.err.println("Host : "+ properties.getProperty("host"));
            tfHost.setText(properties.getProperty("host"));
            tfDBName.setText(properties.getProperty("db"));
            tfUserName.setText(properties.getProperty("user"));
            pfPassword.setText(properties.getProperty("password"));
            thPort.setText(properties.getProperty("port"));
            if(properties.getProperty("winautentation").equals("true")){
                winautentathion.setSelected(true);
            }else{
                winautentathion.setSelected(false);
            }

            inputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mkDbProperties() {
        try {
            String computername = InetAddress.getLocalHost().getHostName();
            if (computername.contains("DESKTOP-A3AGJ6G")){
                inputStream = new FileInputStream("database_DESKTOP_A3AGJ6G.properties");
            }else{
                inputStream = new FileInputStream("database.properties");
            }
            
            properties.setProperty("host", tfHost.getText().trim());
            properties.setProperty("port", thPort.getText().trim());
            properties.setProperty("db", tfDBName.getText().trim());
            properties.setProperty("user", tfUserName.getText().trim());
            properties.setProperty("password", pfPassword.getText().trim());
            properties.setProperty("winautentation", String.valueOf(winautentathion.isSelected()));
            properties.store(output, null);
            output.close();
            if (dbConnect()) {
                con.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Połączono pomyślnie");
                alert.setHeaderText("Zaloguj sie do systemu");
                alert.setContentText("Serwer "+tfHost.getText().trim()+" został podłączony naciśnij OK by przejść do logowania");
                alert.initStyle(StageStyle.UNDECORATED);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Stage stage = (Stage) thPort.getScene().getWindow();
                    stage.close();
                }
            }else{
                Alert error_alert = new Alert(Alert.AlertType.ERROR);
                error_alert.setTitle("Nie mogę połączyć z serverem "+tfHost.getText().trim());
                error_alert.setHeaderText("Nie mogę połączyć z serwerem.");
                error_alert.setContentText("Sprawdź wprowadzone dane i spróbuj ponownie.");
                error_alert.initStyle(StageStyle.UNDECORATED);
                error_alert.show();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void checkSQLStatus() throws SQLException {
        try {
            inputStream = new FileInputStream("database.properties");
            String host = properties.getProperty("host");
            int port = 1433;
            Socket socket = new Socket(host, port);
            if (dbConnect()){
                con.close();
                serverstatus.setImage(new Image("/resources/icon/conected.png"));
            lablServerStatus.setText("Serwer połączony");
            lablServerStatus.setStyle("-map-text-fill: green;");
            btnConnect.setDisable(true);
            tfHost.setDisable(true);
            tfDBName.setDisable(true);
            tfUserName.setDisable(true);
            pfPassword.setDisable(true);
            thPort.setDisable(true);
            winautentathion.setDisable(true);
            }else{
                lablServerStatus.setText("Serwer rozłączony");
                lablServerStatus.setStyle("-map-text-fill: red;");
                serverstatus.setImage(new Image("/resources/icon/disconected.png"));
                btnConnect.setDisable(false);
                tfHost.setDisable(false);
                tfDBName.setDisable(false);
                tfUserName.setDisable(false);
                pfPassword.setDisable(false);
                thPort.setDisable(false);
                winautentathion.setDisable(false);
            }

            //lablServerStatus.setTextFill(Color.GREEN);
        } catch (FileNotFoundException ex) {
            lablServerStatus.setText("Serwer rozłączony");
            lablServerStatus.setStyle("-map-text-fill: red;");
            btnConnect.setDisable(false);
            tfHost.setDisable(false);
            tfDBName.setDisable(false);
            tfUserName.setDisable(false);
            pfPassword.setDisable(false);
            thPort.setDisable(false);
            winautentathion.setDisable(false);
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            lablServerStatus.setText("Serwer rozłączony");
            lablServerStatus.setStyle("-map-text-fill: red;");
            btnConnect.setDisable(false);
            tfHost.setDisable(false);
            tfDBName.setDisable(false);
            tfUserName.setDisable(false);
            pfPassword.setDisable(false);
            thPort.setDisable(false);
            winautentathion.setDisable(false);
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadPropertiesFile(){
        try {
            String computername = InetAddress.getLocalHost().getHostName();
            if (computername.contains("DESKTOP-A3AGJ6G")){
                inputStream = new FileInputStream("database_DESKTOP_A3AGJ6G.properties");
            }else{
                inputStream = new FileInputStream("database.properties");
            }
            properties.load(inputStream);
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

        } catch (IOException e) {
            ////System.out.println("DDDD");
        }
    }

    private boolean dbConnect() {
        loadPropertiesFile();
        try {
            //con.close();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url /*+ unicode */ , user, pass);
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            ////System.out.println("Too Many Connection");

                con = null;

        }
        return false;
    }

}
