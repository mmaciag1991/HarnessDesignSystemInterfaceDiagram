/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Getway;

import ControllersLayer.popup.PopUpController;
import Graph_ModulesLeyautTable_Engine.Main.Data;
import Model.TestersData;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import javafx.collections.ObservableList;
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
public class TestersGetway {

    SQL sql = new SQL();

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public void save(TestersData testersData) {
        con = dbCon.geConnectionDB();

        try {
            pst = con.prepareStatement("insert into Testers values(?,?,?,?,'0')");
            PreparedStatement pst1 = con.prepareStatement("select Id from Testers");
            ResultSet rs = pst1.executeQuery();
            int id = 0 ;
            while(rs.next())
            {
                id = rs.getInt(1)+1;
            }
            pst.setInt(1, id);
            pst.setString(2, testersData.getTesterName());
            pst.setString(3, testersData.getTesterColumns());
            pst.setString(4, testersData.getTesterRows());
            pst.executeUpdate();
            con.close();
            pst.close();
            Data.testersData.add(new TestersData(String.valueOf(id),testersData.getTesterName(),testersData.getTesterColumns(),testersData.getTesterRows()));
            try {
                FXMLLoader fXMLLoader = new FXMLLoader();
                fXMLLoader.load(getClass().getResource("/view/popup/PopUp.fxml").openStream());
                PopUpController controller = fXMLLoader.getController();
                controller.setMessage("Tester " + testersData.getTesterName() + " dodany poprawnie.");
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

    public void view(ObservableList<TestersData> testersData) {
        con = dbCon.geConnectionDB();

        try {
            pst = con.prepareCall("select * from Testers");
            rs = pst.executeQuery();
            while (rs.next()) {
                testersData.addAll(new TestersData(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void searchView(TestersData testersData) {
        con = dbCon.geConnectionDB();


        Data.testersData.removeAll(Data.testersData);
        try {
            con = dbCon.geConnectionDB();
            pst = con.prepareCall("select * from Testers where TesterName like ? ORDER BY TesterName");
            ////System.out.println("Brand name in Brand Object");
            pst.setString(1, "%" + testersData.getTesterName() + "%");

            rs = pst.executeQuery();

            while (rs.next()) {

                Data.testersData.addAll(new TestersData(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ));


                }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TestersData searchTester(TestersData testersData) {
        con = dbCon.geConnectionDB();



        try {
            con = dbCon.geConnectionDB();
            pst = con.prepareCall("select * from Testers where TesterName like ? ORDER BY TesterName");
            ////System.out.println("Brand name in Brand Object");
            pst.setString(1, "%" + testersData.getTesterName() + "%");

            rs = pst.executeQuery();

            while (rs.next()) {

                testersData = new TestersData(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );


            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return testersData;
    }


    public void delete(TestersData testersData) {
        con = dbCon.geConnectionDB();

        try {
            pst = con.prepareStatement("delete from Testers where Id=?");
            pst.setString(1, testersData.getTesterID());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(TestersData testersData) {
        con = dbCon.geConnectionDB();

        ////System.out.println(testersData.getTesterName());
        try {
            pst = con.prepareStatement("update Testers set TesterName=? , Columns=?,Rows=? where Id=?");
            pst.setInt(4, Integer.valueOf(testersData.getTesterID()));
            pst.setString(1, testersData.getTesterName());
            pst.setString(2, testersData.getTesterColumns());
            pst.setString(3, testersData.getTesterRows());
            pst.executeUpdate();
            con.close();
            pst.close();
            try {
                FXMLLoader fXMLLoader = new FXMLLoader();
                fXMLLoader.load(getClass().getResource("/view/popup/PopUp.fxml").openStream());
                PopUpController controller = fXMLLoader.getController();
                controller.setMessage("Tester " + testersData.getTesterName() + " zaktualizowany poprawnie.");
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
