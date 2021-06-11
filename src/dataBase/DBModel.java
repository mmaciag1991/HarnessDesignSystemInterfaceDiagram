
package dataBase;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateusz Maciag
 */
public class DBModel {
    
    Properties properties = new Properties();
    InputStream inputStream;
    String db;
    
    
    
    public void loadPropertiesFile(){
        try {
            properties = new DBProperties().loadAndGetPropertiesFile();
            db = properties.getProperty("db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    PreparedStatement pst;

    public void createDataBase() {
        loadPropertiesFile();
        DBConnection con = new DBConnection();

        try {
            pst = con.mkDataBase().prepareStatement("IF  NOT EXISTS (SELECT * FROM sys.databases WHERE name = N'"+db+"')\n" +
                    "    BEGIN\n" +
                    "        CREATE DATABASE ["+db+"]\n" +
                    "    END;");
            pst.execute();




            pst = con.mkDataBaseDB().prepareStatement("IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Testers' and xtype='U')  \n" +
                    "CREATE TABLE Testers (\n" +
                    "                      Id int NOT NULL,\n" +
                    "                      TesterName varchar(11) NOT NULL,\n" +
                    "                      Columns varchar(11) NOT NULL,\n" +
                    "                      Rows varchar(11) NOT NULL,\n" +
                    "                      Pins varchar(11) NOT NULL,\n" +
                    "                      PRIMARY KEY (Id)\n" +
                    "                    );");
            pst.execute();
            pst = con.mkDataBaseDB().prepareStatement("IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Modules' and xtype='U')  \n" +
                    "CREATE TABLE Modules (\n" +
                    "                      Id int NOT NULL,\n" +
                    "                      SellId varchar(10) NOT NULL,\n" +
                    "                      TesterId varchar(11) NOT NULL,\n" +
                    "                      Type varchar(11) NOT NULL,\n" +
                    "                      Connector varchar(11) NOT NULL,\n" +
                    "                      AssemblyType varchar(11) NOT NULL,\n" +
                    "                      Pins varchar(11) NOT NULL,\n" +
                    "                      CoordinateX varchar(11) NOT NULL,\n" +
                    "                      CoordinateY varchar(11) NOT NULL,\n" +
                    "                      Xcode varchar(11) NOT NULL,\n" +
                    "                      PRIMARY KEY (Id)\n" +
                    "                    );");
            pst.execute();
            
            ////System.out.println("Wszystkie Kwerendy Wykonane poprawnie");

        } catch (SQLException ex) {
            System.err.println(ex);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/Server.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Konfiguracja Serwera");
                stage.getIcons().add(new Image("/resources/image/database.png"));

                stage.showAndWait();
            } catch (IOException ex1) {
                Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

}
