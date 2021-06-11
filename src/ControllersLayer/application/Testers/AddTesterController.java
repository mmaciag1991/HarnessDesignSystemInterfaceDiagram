package ControllersLayer.application.Testers;

import Getway.TestersGetway;
import Model.TestersData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Mateusz Maciag on 8/1220.
 */
public class AddTesterController implements Initializable {
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfColumns;
    @FXML
    private TextField tfRows;
    @FXML
    public Button btnSave;
    @FXML
    public Label lblCustomerContent;
    @FXML
    private Button btnClose;
    @FXML
    public Button btnUpdate;
    
    public String customerId;
    
    private String userId;
    


    String id;
    


    


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void setBtnSave(ActionEvent event){

            new TestersGetway().save(new TestersData("1",tfName.getText(),tfColumns.getText(),tfRows.getText()) );

          btnCloseOnAction(null);

    }

    public void setUpdate(String id,String name, String cols, String rows){

       this.id = id;
        tfName.setText(name);
        tfColumns.setText(cols);
        tfRows.setText(rows);

    }
    @FXML
    private void setBtnUpdate(ActionEvent event){

        new TestersGetway().update(new TestersData(id,tfName.getText(),tfColumns.getText(),tfRows.getText()) );

        btnCloseOnAction(null);

    }


}
