/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom;

import ControllersLayer.popup.PopUpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateusz Maciag
 */
public class PopUp {
    
    
    public void sucessMessage(){
        PopUpController sc = new PopUpController();
        ////System.out.println("COmm");
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/popup/Sucess.fxml").openStream());
            fXMLLoader.load();
            PopUpController sucessController = fXMLLoader.getController();
            ////System.out.println("Come 2");
//            sucessController.message.setText("d");
            Parent parent = fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.setResizable(false);
            nStage.show();
        } catch (IOException ex) {
            Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
