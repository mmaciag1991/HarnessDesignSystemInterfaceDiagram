/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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



            pst = con.mkDataBaseDB().prepareStatement("IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Brands' and xtype='U')  \n" +
                    "CREATE TABLE Brands (\n" +
                    "                    Id int NOT NULL,\n" +
                    "                    BrandName varchar(70) DEFAULT NULL,\n" +
                    "                    Description text DEFAULT NULL,\n" +
                    "                    SupplyerId varchar(20)  DEFAULT NULL,\n" +
                    "                    CreatorId int DEFAULT NULL,\n" +
                    "                    Date date,\n" +
                    "                    PRIMARY KEY (Id),\n" +
                    "                    UNIQUE (Id ASC));");

            pst.execute();
            /**pst = con.mkDataBase().prepareStatement("CREATE TABLE IF not exists "+db+".`Brands` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `BrandName` varchar(70) DEFAULT NULL,\n"
                    + "  `Description` text DEFAULT NULL,\n"
                    + "  `SupplyerId` varchar(20)  DEFAULT NULL,\n"
                    + "  `CreatorId` int DEFAULT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");

            pst.execute();**/

            pst = con.mkDataBaseDB().prepareStatement("IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Catagory' and xtype='U')  \n" +
                    "CREATE TABLE Catagory (\n" +
                    "                    Id int NOT NULL,\n" +
                    "                    CatagoryName varchar(70) DEFAULT NULL,\n" +
                    "                    CatagoryDescription text DEFAULT NULL,\n" +
                    "                    BrandId varchar(20) DEFAULT NULL,\n" +
                    "                    SupplyerId int DEFAULT NULL,\n" +
                    "                    CreatorId int DEFAULT NULL,\n" +
                    "                    Date date,\n" +
                    "                    PRIMARY KEY (Id),\n" +
                    "                    UNIQUE (Id ASC));");

            pst.execute();
            /**pst = con.mkDataBase().prepareStatement("CREATE TABLE IF not exists "+db+".`Catagory` (\n"
             + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
             + "  `CatagoryName` varchar(70) DEFAULT NULL,\n"
             + "  `CatagoryDescription` text DEFAULT NULL,\n"
             + "  `BrandId` varchar(20) DEFAULT NULL,\n"
             + "  `SupplyerId` int(11) DEFAULT NULL,\n"
             + "  `CreatorId` int(11) DEFAULT NULL,\n"
             + "  `Date` date,\n"
             + "  PRIMARY KEY (`Id`),\n"
             + "  UNIQUE INDEX `Id` (`Id` ASC));");

             pst.execute();**/
            pst = con.mkDataBaseDB().prepareStatement("IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Unit' and xtype='U')  \n" +
                    "CREATE TABLE Unit (\n" +
                    "                    Id int NOT NULL,\n" +
                    "                    UnitName varchar(50) DEFAULT NULL,\n" +
                    "                    UnitDescription text DEFAULT NULL,\n" +
                    "                    CreatorId int DEFAULT NULL,\n" +
                    "                    Date date,\n" +
                    "                    PRIMARY KEY (Id),\n" +
                    "                    UNIQUE (Id ASC));");

            pst.execute();
/**
            pst = con.mkDataBase().prepareStatement("CREATE TABLE IF not exists "+db+".`Unit` (\n"
                    + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `UnitName` varchar(50) DEFAULT NULL,\n"
                    + "  `UnitDescription` text DEFAULT NULL,\n"
                    + "  `CreatorId` int(11) DEFAULT NULL,\n"
                    + "  `Date` date,\n"
                    + "  PRIMARY KEY (`Id`),\n"
                    + "  UNIQUE INDEX `Id` (`Id` ASC));");

            pst.execute();
*/
            pst = con.mkDataBaseDB().prepareStatement("IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='RMA' and xtype='U')  \n" +
                    "CREATE TABLE RMA (\n" +
                    "                      Id int NOT NULL,\n" +
                    "                      RMAName varchar(100) DEFAULT NULL,\n" +
                    "                      RMADays varchar(11) NOT NULL,\n" +
                    "                      Comment text DEFAULT NULL,\n" +
                    "                      CreatorId int DEFAULT NULL,\n" +
                    "                      Date date,\n" +
                    "                      PRIMARY KEY (Id),\n" +
                    "                      UNIQUE (Id ASC));");

            pst.execute();
            /** pst = con.mkDataBase().prepareStatement("CREATE TABLE IF not exists "+db+".`RMA` (\n"
             + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
             + "  `RMAName` varchar(100) DEFAULT NULL,\n"
             + "  `RMADays` varchar(11) NOT NULL,\n"
             + "  `Comment` text DEFAULT NULL,\n"
             + "  `CreatorId` int(11) DEFAULT NULL,\n"
             + "  `Date` date,\n"
             + "  PRIMARY KEY (`Id`),\n"
             + "  UNIQUE INDEX `Id` (`Id` ASC));");

             pst.execute();**/
            pst = con.mkDataBaseDB().prepareStatement("IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Products' and xtype='U')  \n" +
                    "CREATE TABLE Products (\n" +
                    "                      Id int NOT NULL,\n" +
                    "                      ProductId varchar(20) NOT NULL,\n" +
                    "                      ProductName varchar(150) NOT NULL,\n" +
                    "                      Quantity varchar(11) NOT NULL DEFAULT '0', \n" +
                    "                      Description text ,\n" +
                    "                      SupplyerId varchar(11) NOT NULL,\n" +
                    "                      BrandId varchar(11) NOT NULL,\n" +
                    "                      CatagoryId varchar(11) NOT NULL,\n" +
                    "                      UnitId varchar(11) NOT NULL,\n" +
                    "                      PursesPrice varchar(100) NOT NULL,\n" +
                    "                      SellPrice varchar(100) NOT NULL,\n" +
                    "                      RMAId varchar(11) NOT NULL,\n" +
                    "                      UserId varchar(11) NOT NULL,\n" +
                    "                      Date date NOT NULL,\n" +
                    "                      PRIMARY KEY (Id),\n" +
                    "                      UNIQUE (Id ASC));");
            pst.execute();
            /** pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Products` (\n"
             + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
             + "  `ProductId` varchar(20) NOT NULL,\n"
             + "  `ProductName` varchar(150) NOT NULL,\n"
             + "  `Quantity` varchar(11) NOT NULL DEFAULT '0', \n"
             + "  `Description` text ,\n"
             + "  `SupplyerId` varchar(11) NOT NULL,\n"
             + "  `BrandId` varchar(11) NOT NULL,\n"
             + "  `CatagoryId` varchar(11) NOT NULL,\n"
             + "  `UnitId` varchar(11) NOT NULL,\n"
             + "  `PursesPrice` varchar(100) NOT NULL,\n"
             + "  `SellPrice` varchar(100) NOT NULL,\n"
             + "  `RMAId` varchar(11) NOT NULL,\n"
             + "  `UserId` varchar(11) NOT NULL,\n"
             + "  `Date` date NOT NULL,\n"
             + "  PRIMARY KEY (`Id`),\n"
             + "  UNIQUE INDEX `Id` (`Id` ASC));");
             pst.execute();
             */


            pst = con.mkDataBaseDB().prepareStatement("IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Customer' and xtype='U')  \n" +
                    "CREATE TABLE Customer (\n" +
                    "               Id int NOT NULL,\n" +
                    "               CustomerName varchar(200) NOT NULL,\n" +
                    "               CustomerContNo varchar(200) DEFAULT NULL,\n" +
                    "               CustomerAddress text,\n" +
                    "               TotalBuy varchar(50) DEFAULT NULL,\n" +
                    "               CreatorId varchar(11) DEFAULT NULL,\n" +
                    "               Date datetime NOT NULL,\n" +
                    "               PRIMARY KEY (Id),\n" +
                    "               UNIQUE (Id ASC));");
            pst.execute();

            /** pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Customer` (\n"
             + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
             + "  `CustomerName` varchar(200) NOT NULL,\n"
             + "  `CustomerContNo` varchar(200) DEFAULT NULL,\n"
             + "  `CustomerAddress` text,\n"
             + "  `TotalBuy` varchar(50) DEFAULT NULL,\n"
             + "  `CreatorId` varchar(11) DEFAULT NULL,\n"
             + "  `Date` datetime NOT NULL,\n"
             + "  PRIMARY KEY (`Id`),\n"
             + "  UNIQUE INDEX `Id` (`Id` ASC));");
             pst.execute();**/

            pst = con.mkDataBaseDB().prepareStatement("IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Sell' and xtype='U')  \n" +
                    "CREATE TABLE Sell (\n" +
                            "                      Id int NOT NULL,\n" +
                            "                      SellId varchar(10) NOT NULL,\n" +
                            "                      CustomerId varchar(11) NOT NULL,\n" +
                            "                      ProductId varchar(11) NOT NULL,\n" +
                            "                      PursesPrice REAL NOT NULL,\n" +
                            "                      SellPrice REAL NOT NULL,\n" +
                            "                      Quantity int NOT NULL,\n" +
                            "                      TotalPrice REAL NOT NULL,\n" +
                            "                      WarrentyVoidDate varchar(20) NOT NULL,\n" +
                            "                      SellerId int NOT NULL,\n" +
                            "                      SellDate datetime NOT NULL,\n" +
                            "                      EndDate datetime NOT NULL,\n" +
                    "                      montage varchar(21) NOT NULL,\n" +
                    "                      grip varchar(21) NOT NULL,\n" +
                    "                      lockingopenclose varchar(21) NOT NULL,\n" +
                    "                      housingCode varchar(11) NOT NULL,\n" +
                    "                      pintype varchar(11) NOT NULL,\n" +
                    "                      locking varchar(11) NOT NULL,\n" +
                    "                      adapter varchar(11) NOT NULL,\n" +
                    "                      raid varchar(11) NOT NULL,\n" +
                    "                      pushback varchar(11) NOT NULL,\n" +
                    "                      sealertest varchar(11) NOT NULL,\n" +
                    "                      vacumtest varchar(11) NOT NULL,\n" +
                    "                      shrink varchar(11) NOT NULL,\n" +
                    "                      colorDetection varchar(11) NOT NULL,\n" +
                    "                      reference varchar(21) NULL,\n" +
                    "                      machine varchar(21) NULL,\n" +
                    "                      cordX varchar(21) NULL,\n" +
                    "                      cordY varchar(21) NULL,\n" +
                    "                      status varchar(21) NULL,\n" +
                    "                      description varchar(21) NULL,\n" +
                    "                      ModuleMounted varchar(21) NULL,\n" +
                    "                      PRIMARY KEY (Id)\n" +
                            "                    );");
            pst.execute();
            /**  pst = con.mkDataBase().prepareStatement("CREATE TABLE IF NOT EXISTS "+db+".`Sell` (\n"
             + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
             + "  `SellId` varchar(10) NOT NULL,\n"
             + "  `CustomerId` varchar(11) NOT NULL,\n"
             + "  `ProductId` varchar(11) NOT NULL,\n"
             + "  `PursesPrice` double NOT NULL,\n"
             + "  `SellPrice` double NOT NULL,\n"
             + "  `Quantity` int(10) NOT NULL,\n"
             + "  `TotalPrice` double NOT NULL,\n"
             + "  `WarrentyVoidDate` varchar(20) NOT NULL,\n"
             + "  `SellerId` int(11) NOT NULL,\n"
             + "  `SellDate` datetime NOT NULL,\n"
             + "  PRIMARY KEY (`Id`)\n"
             + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
             pst.execute();*/

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
