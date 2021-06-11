package DSI_Graph_Main_Controlers;

import Components.controlsFX13.org.controlsfx.control.table.TableFilter;
import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Cell;
import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Graph;
import Graph_ModulesLeyautTable_Engine.fxgraph.model.Wires;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MergeConnectorsControler {
    @FXML
    private TableView<Cell> connectorsTableView;
    @FXML
    private TableColumn<Cell,String> connectorId;
    @FXML
    private TableColumn <Cell,String> connectorXcode;
    @FXML
    private TableColumn <Cell,String> connectorModule;
    @FXML
    private TableColumn <Cell,String> connectorCustomerCode;
    @FXML
    private TableColumn <Cell,String> connectorGimCode;
    @FXML
    private TableColumn <Cell,String> connectorX;
    @FXML
    private TableColumn <Cell,String> connectorY;
    @FXML
    private TableColumn <Cell,String> connectorPins;
    @FXML
    private TableColumn <Cell,String> connectorDescription;
    @FXML
    private TableColumn <Cell,String> connectorSource;
    @FXML
    private TableColumn <Cell,String> connectorTarget;
    @FXML
    private Button merge;

    private ObservableList<Cell> connectorsData;

    public MergeConnectorsControler(){

    }
    @FXML
    private void initialize(){
        connectorsTableView.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

    }
    @FXML
    private void close(){
       ((Stage)merge.getScene().getWindow()).close();
    }
    public void setConnectorsData(ObservableList<Cell> connectorsData, ObservableList<Cell> componentsData, Graph graph, TableView<Cell> connectorsTableView){
        this.connectorsData = connectorsData;
        /**tabela konektory */
        connectorId.setCellValueFactory(cellData -> cellData.getValue().getCellIdProperty());
        connectorXcode.setCellValueFactory(cellData -> cellData.getValue().xcodeProperty());
        //connectorModule.setCellValueFactory(cellData -> cellData.getValue().usedModuleProperty());
        connectorCustomerCode.setCellValueFactory(cellData -> cellData.getValue().customerCodeProperty());
        connectorGimCode.setCellValueFactory(cellData -> cellData.getValue().gimCodeProperty());
        connectorX.setCellValueFactory(cellData -> Bindings.format("%.1f", cellData.getValue().getPositionXProperty()));
        connectorY.setCellValueFactory(cellData -> Bindings.format("%.1f", cellData.getValue().getPositionYProperty()));
        connectorPins.setCellValueFactory(cellData -> cellData.getValue().pinsProperty());
        connectorDescription.setCellValueFactory(cellData -> cellData.getValue().getDescription());
        //connectorSource.setCellValueFactory(cellData -> cellData.getValue().getEdges().get(0).getSource().getCellIdProperty());
        //connectorTarget.setCellValueFactory(cellData -> cellData.getValue().getEdges().get(0).getTarget().getCellIdProperty());

        this.connectorsTableView.setItems(connectorsData);
        Platform.runLater(() -> {
            TableFilter<Cell> tableFilterConnectors = new TableFilter<>(this.connectorsTableView);
        });

        merge.setOnAction(event -> {
            String xCode = "";
            String customerCode = "";
            String gmCode = "";
            List<Cell> cellsToMerge = new LinkedList<>();
           mainLop: for (Cell cell : this.connectorsTableView.getSelectionModel().getSelectedItems()){
                boolean carrier = false;
                for (Cell pasive : cell.getPasives()){
                    if (pasive.getDescription().get().toLowerCase().contains("carrier")){
                        if (!xCode.equals("")){
                            if (!customerCode.equals(pasive.getCustomerCode())){
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.getDialogPane().getStylesheets().add("/resources/style/jmetro/combination.css");
                                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                stage.initStyle(StageStyle.TRANSPARENT);
                                stage.setAlwaysOnTop(true);
                                alert.setTitle("Ostrze¿enie!");
                                alert.setHeaderText("Wykryto niezgodnoœæ komponentów typu CARRIER (Koszyk). "+customerCode + " oraz " + pasive.getCustomerCode());

                            }
                        }
                        System.out.println(pasive.getCellId()+" "+pasive.getXcode()+" "+pasive.getGIMCode()+" "+pasive.getCustomerCode()+" "+pasive.getDescription());
                        carrier = true;
                        cellsToMerge.add(cell);
                        xCode = pasive.getXcode();
                        customerCode = pasive.getCustomerCode();
                        gmCode = pasive.getGIMCode();
                        break;
                    }
                }
                if (!carrier){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.getDialogPane().getStylesheets().add("/resources/style/jmetro/combination.css");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setAlwaysOnTop(true);
                    alert.setTitle("Ostrze¿enie!");
                    alert.setHeaderText("Konnektor "+cell.getXcode()+" nie zawiera komponentu typu CARRIER (Koszyk).");
                    alert.setContentText("Czy napewno czcesz dodac konektor do migracji.");

                    ButtonType buttonYes = new ButtonType("Tak");
                    ButtonType buttonCancel = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(buttonYes,buttonCancel);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonYes){
                        cellsToMerge.add(cell);
                    } else {
                        // ... user chose CANCEL or closed the dialog
                    }
                }
            }
            if (cellsToMerge.size()>0){
                // Create the custom dialog.
                Dialog dialog = new Dialog<>();
                dialog.setTitle("Nowy konnektor");
                dialog.setHeaderText("Wprowadz dane nowego konektora.");
                dialog.getDialogPane().getStylesheets().add("/resources/style/jmetro/combination.css");
                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setAlwaysOnTop(true);
// Set the button types.
                ButtonType procedType = new ButtonType("Wykonaj", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancelType = new ButtonType("Przerwij", ButtonBar.ButtonData.CANCEL_CLOSE);
                dialog.getDialogPane().getButtonTypes().addAll(procedType, cancelType);

// Create the username and password labels and fields.
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                TextField xcodeLbl = new TextField();
                xcodeLbl.setPromptText("Xcode");
                xcodeLbl.setText(xCode);
                TextField customerlbl = new TextField();
                customerlbl.setPromptText("Kod Klienta");
                customerlbl.setText(customerCode);
                TextField gmlbl = new TextField();
                gmlbl.setPromptText("Kod GPN");
                gmlbl.setText(gmCode);

                grid.add(new Label("Xcode:"), 0, 0);
                grid.add(xcodeLbl, 1, 0);
                grid.add(new Label("Kod Klienta:"), 0, 1);
                grid.add(customerlbl, 1, 1);
                grid.add(new Label("Kod GPN:"), 0, 2);
                grid.add(gmlbl, 1, 2);

                dialog.getDialogPane().setContent(grid);
                Optional<ButtonType> result = dialog.showAndWait();
                if (result.get() == procedType){
                    //String id, String name, String typeConnector, double x, double y, String Xcode, String CustomerCode, String GIMCode, String Pins
                   // ConnectorSmallCell carrierCell = new ConnectorSmallCell(xCode,xCode,xCode,cellsToMerge.get(0).positionXProperty.get(),cellsToMerge.get(0).positionYProperty.get(),xCode,customerCode,gmCode,"");
                    //String id, String name, String typeConnector, CellType type, double x, double y, String Xcode, String CustomerCode, String GIMCode, String Pins

                    int pins = 0;
                    for (Cell cellToMerge : cellsToMerge) {
                        //zamiana xcodow po³¹czen

                        pins = pins + Integer.parseInt(cellToMerge.getPins());
                        for (Wires wire : graph.getModel().getWiresData()) {
                            if (wire.getFromXcode().equals(cellToMerge.getXcode())) {
                                wire.fromXcodeProperty().set(xcodeLbl.getText());
                                wire.setConneectorFromBase(xcodeLbl.getText());
                            } else if (wire.getToXcode().equals(cellToMerge.getXcode())) {
                                wire.toXcodeProperty().set(xcodeLbl.getText());
                                wire.setConneectorToBase(xcodeLbl.getText());
                            }
                        }
                    }
                    Cell carrierCell = cellsToMerge.get(0);
                    carrierCell.clearPasives();
                    for (Cell cellToMerge : cellsToMerge) {
                        carrierCell.addPasives(cellToMerge.getCellId(),cellToMerge.getCustomerCode());
                        carrierCell.addPasives(cellToMerge);
                    }


                    carrierCell.setCellId(xcodeLbl.getText());
                    carrierCell.getCellIdProperty().setValue(xcodeLbl.getText());
                    carrierCell.setXcode(xcodeLbl.getText());
                    //carrierCell.setId(xcodeLbl.getText());
                    carrierCell.setCustomerCode(customerlbl.getText());
                    carrierCell.setCellType(customerlbl.getText());
                    carrierCell.setGIMCode(gmlbl.getText());


                    carrierCell.setPins(pins+"");




                        for (Cell cellToMerge : cellsToMerge){

                        //dzialania na grafie
                        if (cellToMerge.getEdges().size()>0)
                        for (int i = 0 ; i < cellToMerge.getEdges().size() ; i++)
                        /*for (Edge edge : cellToMerge.getEdges())*/{
                            if (cellToMerge.getEdges().get(i).getSource().getCellId().equals(cellToMerge.getCellId())){
//                               graph.getModel().addEdge(carrierCell.cellId, cellToMerge.getEdges().get(i).getTarget().cellId,cellToMerge.getEdges().get(i).getLenght(),2,cellToMerge.getEdges().get(i).getBaseColor());
//                                Edge edgeCarrier = new Edge(carrierCell, cellToMerge.getEdges().get(i).getTarget(),cellToMerge.getEdges().get(i).getLenght(),2,cellToMerge.getEdges().get(i).getBaseColor());
//                                Platform.runLater(() -> {
//                                //carrierCell.addEdge(edgeCarrier);
//                                graph.getCanvas().getChildren().add(edgeCarrier);
//                                });
                                cellToMerge.getEdges().get(i).setSource(carrierCell);
                                System.out.println("source "+cellToMerge.getEdges().get(i).getSource().getXcode());
                            }else  if (cellToMerge.getEdges().get(i).getTarget().getCellId().equals(cellToMerge.getCellId())){
//                              graph.getModel().addEdge(cellToMerge.getEdges().get(i).getSource().cellId, carrierCell.cellId,cellToMerge.getEdges().get(i).getLenght(),2,cellToMerge.getEdges().get(i).getBaseColor());
//                                Edge edgeCarrier = new Edge(cellToMerge.getEdges().get(i).getSource(), carrierCell,cellToMerge.getEdges().get(i).getLenght(),2,cellToMerge.getEdges().get(i).getBaseColor());
//                               Platform.runLater(() -> {
//                                graph.getCanvas().getChildren().add(edgeCarrier);
//                                //carrierCell.addEdge(edgeCarrier);
//                            });
                                cellToMerge.getEdges().get(i).setTarget(carrierCell);
                                System.out.println("target "+cellToMerge.getEdges().get(i).getTarget().getXcode());
                            }
                            int finalI = i;
                            Platform.runLater(() -> {
                                //graph.getCellLayer().getChildren().remove(cellToMerge.getEdges().get(finalI));
                            //graph.getModel().getAllEdges().remove(cellToMerge.getEdges().get(finalI));
                            });
                        }
                        if(!cellToMerge.equals(carrierCell)){
                            Platform.runLater(() -> {
                                graph.getCellLayer().getChildren().remove(cellToMerge);
                                connectorsData.remove(cellToMerge);
                            });
                        }




                       // graph.getModel().getConnectorsData().remove(cellToMerge);
                    }

//                    connectorsData.add(carrierCell);

                    carrierCell.setCellType("ConnectorSmallCell");
                   // connectorsTableView.refresh();

                } else if (result.get() == cancelType) {
                    // ... user chose "Two"
                }
            }


        });
    }



}
