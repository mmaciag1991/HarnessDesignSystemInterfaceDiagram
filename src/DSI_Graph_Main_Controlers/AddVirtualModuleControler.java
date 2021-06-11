package DSI_Graph_Main_Controlers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.util.ArrayList;

public class AddVirtualModuleControler {
    /**Stage virtualModuleStage FXML*/

    @FXML
    private TextField xCode;
    @FXML
    private TextField  customerCode;
    @FXML
    private TextField  gmCode;


    @FXML
    private ComboBox  startX;
    @FXML
    private ComboBox  startY;
    @FXML
    private ComboBox  endX;
    @FXML
    private ComboBox  endY;

    @FXML
    private Spinner<Integer> pins;
    @FXML
    private Spinner<Integer>  switches;

    @FXML
    private Button virtualModuleOKbtn;
    @FXML
    private Button  virtualModuleCancelbtn;

    boolean empty = false;

    public AddVirtualModuleControler(){

    }

    @FXML
    private void initialize(){


        ArrayList<Character> mapY = new ArrayList<>();
        ArrayList<Integer> mapX = new ArrayList<>();

        mapY.add('A');
        mapY.add('B');
        mapY.add('C');
        mapY.add('D');
        mapY.add('E');
        mapY.add('F');
        mapY.add('G');
        mapY.add('H');
        mapY.add('I');
        mapY.add('J');
        mapY.add('K');
        mapY.add('L');
        mapY.add('M');
        mapY.add('N');
        mapY.add('O');
        mapY.add('P');
        mapY.add('Q');
        mapY.add('R');
        mapY.add('S');
        mapY.add('T');
        mapY.add('U');
        mapY.add('V');
        mapY.add('W');
        mapY.add('X');
        mapY.add('Y');
        mapY.add('Z');

        for (int i = 0 ; i <= 72 ; i++){
            mapX.add(i);
        }

        startY.setItems(FXCollections.observableArrayList(mapY));

        endY.setItems(FXCollections.observableArrayList(mapY));

        startX.setItems(FXCollections.observableArrayList(mapX));

        endX.setItems(FXCollections.observableArrayList(mapX));



        // Value factory.

        pins.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 250, 1));
        switches.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 40, 0));


        xCode.textProperty().addListener((observableValue, s, t1) -> {
            chceckEmpty(t1,xCode);
        });
        customerCode.textProperty().addListener((observableValue, s, t1) -> {
            chceckEmpty(t1,customerCode);
        });
        gmCode.textProperty().addListener((observableValue, s, t1) -> {
           chceckEmpty(t1,gmCode);
        });


    }
    public boolean checkAll(){
        chceckEmpty(xCode.getText(),xCode);
        chceckEmpty(customerCode.getText(),customerCode);
        chceckEmpty(gmCode.getText(),gmCode);

        if (startX.getSelectionModel().getSelectedItem()==null){
            empty = true;
            startX.setStyle("-fx-border-color:firebrick;");
        }else {
            empty = false;
            startX.setStyle("-fx-border-color:darkgrey;");
        }
        if (startY.getSelectionModel().getSelectedItem()==null){
            empty = true;
            startY.setStyle("-fx-border-color:firebrick;");
        }else {
            empty = false;
            startY.setStyle("-fx-border-color:darkgrey;");
        }

//        if (endX.getSelectionModel().getSelectedItem()==null){
//            empty = true;
//            endX.setStyle("-fx-border-color:firebrick;");
//        }else {
//            empty = false;
//            endX.setStyle("-fx-border-color:darkgrey;");
//        }
//        if (endY.getSelectionModel().getSelectedItem()==null){
//            empty = true;
//            endY.setStyle("-fx-border-color:firebrick;");
//        }else {
//            empty = false;
//            endY.setStyle("-fx-border-color:darkgrey;");
//        }


      return empty;
    }
    public void chceckEmpty(String t1, Node node){
        if (t1.equals("")){
            empty = true;
            node.setStyle("-fx-border-color:firebrick;");
        }else {
            empty = false;
            node.setStyle("-fx-border-color:transparent;");
        }
    }

    public void setStartX(String startX) {
        this.startX.setValue(startX) ;
    }

    public void setStartY(String startY) {
        this.startY.setValue(startY) ;
    }

    public Button getVirtualModuleCancelbtn() {
        return virtualModuleCancelbtn;
    }

    public Button getVirtualModuleOKbtn() {
        return virtualModuleOKbtn;
    }

    public ComboBox getStartX() {
        return startX;
    }

    public ComboBox getStartY() {
        return startY;
    }

    public ComboBox getEndX() {
        return endX;
    }

    public ComboBox getEndY() {
        return endY;
    }

    public Spinner<Integer> getPins() {
        return pins;
    }

    public Spinner<Integer> getSwitches() {
        return switches;
    }

    public TextField getxCode() {
        return xCode;
    }

    public TextField getGmCode() {
        return gmCode;
    }

    public TextField getCustomerCode() {
        return customerCode;
    }
}
