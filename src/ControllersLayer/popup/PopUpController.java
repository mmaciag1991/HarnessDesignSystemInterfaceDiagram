/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersLayer.popup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Mateusz Maciag
 */
public class PopUpController implements Initializable {
   
    @FXML
    public Button btnClose;
    @FXML
    public TextArea message;
    @FXML
    private ImageView ivSucessImage;
    @FXML
    private Label header;
    @FXML
    private Pane fotter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setMessage(String message) {
        this.message.setText(message);
    }

    public void setIvSucessImage(String url) {
        this.ivSucessImage.setImage(new Image(url));
    }


    public void setFotterColor(Color fotterColor) {
        ////System.out.println(fotterColor.toString());
        this.fotter.setBackground(new Background(new BackgroundFill(fotterColor, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setHeader(String header) {
        this.header.setText(header);
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
}
