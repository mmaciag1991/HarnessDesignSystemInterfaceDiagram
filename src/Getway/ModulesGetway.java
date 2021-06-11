/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Getway;

import ControllersLayer.popup.PopUpController;
import DataAccessLayer.Modules;
import List.ListModules;
import Model.TestersData;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mateusz Maciag
 */
public class ModulesGetway {

    SQL sql = new SQL();

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public void save(Modules modules) {
        con = dbCon.geConnectionDB();

        try {
            pst = con.prepareStatement("insert into Modules values(?,?,?,?,?,?,?,?,?)");
            PreparedStatement pst1 = con.prepareStatement("select Id from Modules");
            ResultSet rs = pst1.executeQuery();
            int id = 0 ;
            while(rs.next())
            {
                id = rs.getInt(1)+1;
            }
            pst.setInt(1, id);
            pst.setString(2, modules.SellId);
            pst.setString(3, modules.TesterId);
            pst.setString(4, modules.Type);
            pst.setString(5, modules.Connector);
            pst.setString(6, modules.AssemblyType);
            pst.setString(7, modules.Pins);
            pst.setString(8, modules.CoordinateX);
            pst.setString(9, modules.CoordinateY);
            pst.executeUpdate();
            con.close();
            pst.close();
           // Data.testersdata.add(new TestersData(String.valueOf(id),testersData.getTesterName(),testersData.getTesterColumns(),testersData.getTesterRows()));

            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void view(Modules modules) {
        con = dbCon.geConnectionDB();

        try {
            pst = con.prepareCall("select * from Modules");
            rs = pst.executeQuery();
            while (rs.next()) {
              modules.id = rs.getString(1);
                modules.SellId = rs.getString(2);
                modules.TesterId = rs.getString(3);
                modules.Type = rs.getString(4);
                modules.Connector = rs.getString(5);
                modules.AssemblyType = rs.getString(6);
                modules.Pins = rs.getString(7);
                modules.CoordinateX = rs.getString(8);
                modules.CoordinateY = rs.getString(9);
                modules.Xcode = rs.getString(10);

                modules.modulesList.addAll(new ListModules(modules.id,modules.SellId, modules.Xcode, modules.TesterId,modules.Type,modules.Connector,modules.AssemblyType,modules.Pins,modules.CoordinateX,modules.CoordinateY));

            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void searchView(Modules modules) {
        con = dbCon.geConnectionDB();


        //Data.testersdata.removeAll(Data.testersdata);
        try {
            con = dbCon.geConnectionDB();
            pst = con.prepareCall("select * from Modules where Connector like ? ORDER BY Connector");
            ////System.out.println("Brand name in Brand Object");
            pst.setString(1, "%" + modules.Connector + "%");

            rs = pst.executeQuery();

            while (rs.next()) {

                modules.id = rs.getString(1);
                modules.SellId = rs.getString(2);
                modules.TesterId = rs.getString(3);
                modules.Type = rs.getString(4);
                modules.Connector = rs.getString(5);
                modules.AssemblyType = rs.getString(6);
                modules.Pins = rs.getString(7);
                modules.CoordinateX = rs.getString(8);
                modules.CoordinateY = rs.getString(9);
                modules.Xcode = rs.getString(10);

                modules.modulesList.addAll(new ListModules(modules.id,modules.SellId, modules.Xcode, modules.TesterId,modules.Type,modules.Connector,modules.AssemblyType,modules.Pins,modules.CoordinateX,modules.CoordinateY));

                }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchViewTester(Modules modules) {
        con = dbCon.geConnectionDB();


        //Data.testersdata.removeAll(Data.testersdata);
        try {
            con = dbCon.geConnectionDB();
            pst = con.prepareCall("select * from Modules where TesterID like ? ORDER BY TesterID");
            ////System.out.println("Brand name in Brand Object");
            //System.out.println("TiD "+modules.TesterId);
            pst.setString(1, "%" + modules.TesterId + "%");

            rs = pst.executeQuery();

            while (rs.next()) {

                modules.id = rs.getString(1);
                modules.SellId = rs.getString(2);
                modules.TesterId = rs.getString(3);
                modules.Type = rs.getString(4);
                modules.Connector = rs.getString(5);
                modules.AssemblyType = "Board";//rs.getString(6);
                modules.Pins = rs.getString(7);
                modules.CoordinateX = rs.getString(8);
                modules.CoordinateY = rs.getString(9);
                modules.Xcode = rs.getString(10);
                modules.modulesList.addAll(new ListModules(modules.id,modules.SellId, modules.Xcode, modules.TesterId,modules.Type,modules.Connector,modules.AssemblyType,modules.Pins,modules.CoordinateX,modules.CoordinateY));

            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(TestersData testersData) {
        con = dbCon.geConnectionDB();

        try {
            pst = con.prepareStatement("delete from Brands where Id=?");
            pst.setString(1, testersData.getTesterID());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Modules modules) {
        con = dbCon.geConnectionDB();

        ////System.out.println(testersData.getTesterName());
        try {
            pst = con.prepareStatement("update Modules set SellId=? , TesterId=?, Type=?, Connector=?, AssemblyType=?, Pins=?, CoordinateX=?, CoordinateY=? where Id=?");

            pst.setString(1, modules.SellId);
            pst.setString(2, modules.TesterId);
            pst.setString(3, modules.Type);
            pst.setString(4, modules.Connector);
            pst.setString(5, modules.AssemblyType);
            pst.setString(6, modules.Pins);
            pst.setString(7, modules.CoordinateX);
            pst.setString(8, modules.CoordinateY);
            pst.setString(9, modules.id);
            pst.executeUpdate();
            con.close();
            pst.close();
            try {
                FXMLLoader fXMLLoader = new FXMLLoader();
                fXMLLoader.load(getClass().getResource("/view/popup/PopUp.fxml").openStream());
                PopUpController controller = fXMLLoader.getController();
                controller.setMessage("Moduł " + modules.Connector + " zaktualizowany poprawnie.");
                controller.setHeader("Sukces");
                controller.setFotterColor(Color.GREEN);
                controller.setIvSucessImage("/resources/icon/checked.png");

                AnchorPane anchorPane = fXMLLoader.getRoot();
                anchorPane.getStylesheets().add("/resources/style/Application.css");
                Stage stage = new Stage();
                stage.setScene(new Scene(anchorPane));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    


}
