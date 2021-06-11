package Graph_ModulesLeyautTable_Engine.fxgraph.cellsTypes;

import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Module;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
/*import ControllersLayer.application.Testers.ChangeCoordController;
import ControllersLayer.application.sell.NewSellController;
import DataAccessLayer.SellCart;
import Getway.SellCartGerway;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;*/


public class ModuleCell extends Module {
//    ImageView success = new ImageView(new Image("/resources/images/success.png"));
//    ImageView error = new ImageView(new Image("/resources/images/error.png"));
//    ImageView virtual = new ImageView(new Image("/resources/images/virtual.png"));
//    ImageView warning = new ImageView(new Image("/resources/images/warning.png"));
//    ImageView fico = new ImageView(new Image("/resources/images/fico_64.png"));

    public ModuleCell(String Xcode, String id, String Connector, double width, double height, double xCoord, double yCoord, String XCOORD, String YCOORD, /*TreeTableView<SystemPintable> modulesTable,*/ String orderStatus, String moduleMounted/*, TreeItem<SystemPintable> itemOrder*/, int systemPintableId) {
        super(id,Xcode,Connector,XCOORD,YCOORD,width,height);
        super.SystemPintableId = systemPintableId;

  //      this.getStylesheets().clear();
//        this. getStylesheets().add(getClass().getResource("module.css").toExternalForm());

//
//        success.setFitWidth(16);
//        success.setFitHeight(16);
//
//        error.setFitWidth(16);
//        error.setFitHeight(16);
//
//        virtual.setFitWidth(16);
//        virtual.setFitHeight(16);
//
//        warning.setFitWidth(16);
//        warning.setFitHeight(16);
//
//        fico.setFitWidth(16);
//        fico.setFitHeight(16);

        Pane scrollPane = new Pane();
        scrollPane.setPrefSize(width, height);

        Text text = new Text();
        text.setFill(Color.BLACK);
        text.setText("\n Naz: "+Xcode+"\n Kon: "+Connector+"\n X: "+ XCOORD+"\n Y: "+YCOORD+"");
        text.setWrappingWidth(scrollPane.getWidth());

        scrollPane.getChildren().addAll(text);

        TitledPane view = super.getView();
        view.setPrefSize(width, height);
        if (id.length()>4){
        view.setText("Id: "+id.substring(0,5) );}
        else{
            view.setText("Id: "+id);}
        view.setContent(scrollPane);
        view.setCollapsible(false);



//        exited = new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
////                if (orderStatus.equals("virtual")) {
////                    scrollPane.setStyle("-fx-background-color: rgb(54, 113, 207);");
////                }else {
////                    if (!orderStatus.equals("finished")) {
////                        if (orderStatus.equals("started")) {
////                            scrollPane.setStyle("-fx-background-color: rgb(199, 138, 60);");
////                        } else {
////                            scrollPane.setStyle("-fx-background-color: rgb(147, 147, 147);");
////                        }
////                    } else {
////                        if (moduleMounted.equals("true")) {
////                            scrollPane.setStyle("-fx-background-color: rgb(0, 147, 0);");
////                        } else {
////                            scrollPane.setStyle("-fx-background-color: rgb(147, 0, 0);");
////                        }
////                    }
////                }
//view.setEffect(null);
//
//                //resources.image.setImage(img);
//                event.consume();
//            }
//        };
//        entered = new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
////                if (orderStatus.equals("virtual")) {
////                    scrollPane.setStyle("-fx-background-color: rgb(66, 135, 245);");
////                }else {
////                    if (!orderStatus.equals("finished")) {
////                        if (orderStatus.equals("started")) {
////                            scrollPane.setStyle("-fx-background-color: rgb(247, 171, 72);");
////                        } else {
////                            scrollPane.setStyle("-fx-background-color: rgb(207, 207, 207);");
////                        }
////                    } else {
////                        if (moduleMounted.equals("true")) {
////                            scrollPane.setStyle("-fx-background-color: rgb(0, 207, 0);");
////                        } else {
////                            scrollPane.setStyle("-fx-background-color: rgb(207, 0, 0);");
////                        }
////                    }
////                }
//
//                //   resources.image.setImage(img2);
//                event.consume();
//                view.setEffect(new Lighting());
//                view.toFront();
//            }
//        };
//
//        scrollPane.setOnMouseEntered(entered);
//        scrollPane.setOnMouseExited(exited);



//        view.setOnMouseClicked(event ->
//        {
//            if (event.getButton() == MouseButton.PRIMARY) {
//
//
//                //  modulesTable.getSelectionModel().select(0);
//
//
////
////                boolean search = true;
////                int oi = 0;
//////                    while (modulesTable.getItems().size() >= oi && search == true){
//////                        if (modulesTable.getItems().get(oi).getXcode().equals(Xcode)
//////                                &&modulesTable.getItems().get(oi).getCoordinateX().equals(XCOORD)
//////                                    &&modulesTable.getItems().get(oi).getCoordinateY().equals(YCOORD)){
//////                            modulesTable.requestFocus();
//////                            modulesTable.getFocusModel().focus(oi);
//////                            modulesTable.scrollTo(oi);
//////                            search=false;
//////                        }
//////                        oi++;
//////                    }
////                for (TreeItem<SystemPintable> x :itemOrder.getParent().getParent().getChildren()){
////                    if (x.isExpanded())
////                        x.setExpanded(false);
////                }
////                itemOrder.getParent().setExpanded(true);
////                modulesTable.getSelectionModel().select(itemOrder);
////                ////System.out.println(Xcode);
////                ////System.out.println(XCOORD);
////                ////System.out.println(YCOORD);
//
//            }else
//            if (event.getButton() == MouseButton.SECONDARY) {
//
//            }
//            //resources.image.setImage(img);
//            event.consume();
//        });

//        ContextMenu contextMenu = new ContextMenu();
//        //contextMenu.setOnShowing(event -> {table.getSelectionModel().select(itemOrder);});
//        ImageView iconOrder = new ImageView(new Image("/resources/icon/right-arrow.png"));
//        iconOrder.setFitWidth(16);
//        iconOrder.setFitHeight(16);
//        MenuItem item1 = new MenuItem("Pokaż zamówienie.",iconOrder);
//        item1.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                FXMLLoader fXMLLoader = new FXMLLoader();
//                fXMLLoader.setLocation(getClass().getResource("/view/application/sell/NewSell.fxml"));
//                try {
//
//                    fXMLLoader.load();
//                    Parent parent = fXMLLoader.getRoot();
//                    Scene scene = new Scene(parent);
//                    scene.setFill(new Color(0, 0, 0, 0));
//
//                    Stage OrderStage = new Stage();
//                    userNameMedia media = new userNameMedia();
//                    NewSellController newSellController = fXMLLoader.getController();
//                    newSellController.ProgressStage(new SellCart(),itemOrder.getValue().getSellId());
//                    newSellController.setEdit(true,itemOrder.getValue().getSellId());
//
//                    newSellController.setStage(OrderStage);
//                    newSellController.setScene(scene);
//                    media.setId(media.getId());
//                    newSellController.setNameMedia(media);
//                    newSellController.viewOrderCard(itemOrder.getValue().getSellId());
//                    //stage.show();
//
//                    OrderStage.initModality(Modality.APPLICATION_MODAL);
//                    OrderStage.initStyle(StageStyle.TRANSPARENT);
//
//                    OrderStage.setTitle("Edytor Zamówień");
//
//                    OrderStage.getIcons().add(new Image("/resources/image/icon.png"));
//
//
//
//
//                    OrderStage.setScene(scene);
//                    OrderStage.showAndWait();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });
//        ImageView iconCord = new ImageView(new Image("/resources/icon/PNGs/wireframe.png"));
//        iconCord.setFitWidth(16);
//        iconCord.setFitHeight(16);
//        MenuItem item2 = new MenuItem("Zmień koordynaty.",iconCord);
//        item2.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                FXMLLoader fXMLLoader = new FXMLLoader();
//                fXMLLoader.setLocation(getClass().getResource("/view/application/Testers/ChangeCoord.fxml"));
//                try {
//
//                    fXMLLoader.load();
//                    Parent parent = fXMLLoader.getRoot();
//                    Scene scene = new Scene(parent);
//                    scene.setFill(new Color(0, 0, 0, 0));
//
//                    Stage OrderStage = new Stage();
//                    userNameMedia media = new userNameMedia();
//                    ChangeCoordController changeCoordController = fXMLLoader.getController();
//                    media.setId(media.getId());
//
//                    changeCoordController.setUpdate(itemOrder.getValue());
//                    OrderStage.initModality(Modality.APPLICATION_MODAL);
//                    OrderStage.initStyle(StageStyle.TRANSPARENT);
//                    OrderStage.initOwner(scrollPane.getScene().getWindow());
//                    OrderStage.setTitle("Edytor Koordynatu");
//
//                    OrderStage.getIcons().add(new Image("/resources/image/icon.png"));
//
//
//                    OrderStage.setScene(scene);
//                    OrderStage.showAndWait();
//
//                }catch (Exception e){e.printStackTrace();}
//
//
//            }
//        });
//        final ImageView[] iconMounted = {null};
//        if (!orderStatus.equals("finished")) {
//        }else if(itemOrder.getValue().getSellId().equals("Brak")){
//
//        } else {
//            if (moduleMounted.equals("true")) {
//                moduleMountedText = "Oznacz Moduł jako wymontowany";
//                iconMounted[0] = new ImageView(new Image("/resources/images/warning.png"));
//                iconMounted[0].setFitWidth(16);
//                iconMounted[0].setFitHeight(16);
//            }else{
//                moduleMountedText = "Oznacz moduł jako zamontowany";
//                iconMounted[0] = new ImageView(new Image("/resources/images/success.png"));
//                iconMounted[0].setFitWidth(16);
//                iconMounted[0].setFitHeight(16);
//            }
//        }
//        if (!moduleMountedText.equals("")) {
//            MenuItem item3 = new MenuItem(moduleMountedText, iconMounted[0]);
//            item3.setOnAction(new EventHandler<ActionEvent>() {
//
//                @Override
//                public void handle(ActionEvent event) {
//                    if (moduleMountedText.equals( "Oznacz Moduł jako wymontowany")){
//                        con = dbCon.geConnectionDB();
//                        try {
//                            //System.out.println(itemOrder.getValue().getId());
//                            pst = con.prepareStatement("update Sell set ModuleMounted = 'truefalse' where id = "+itemOrder.getValue().getId()+"");
//
//                            pst.executeUpdate();
//                            pst.close();
//                            con.close();
//                            ModuleCell.super.setVisible(false);
//                            itemOrder.getParent().getChildren().remove(itemOrder);
//                            modulesTable.refresh();
//
//                        } catch (SQLException ex) {
//                            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
//                            Alert alert = new Alert(Alert.AlertType.ERROR);
//                            alert.setTitle("Błąd");
//                            alert.setHeaderText("SQL "+ex.getCause() );
//                            alert.setContentText("SQL Starte "+ex.getSQLState()+ "Error "+ex.getMessage());
//                            alert.initStyle(StageStyle.UNDECORATED);
//                            alert.showAndWait();
//                        }
//                    }else{
//                        con = dbCon.geConnectionDB();
//                        try {
//                            pst = con.prepareStatement("update Sell set ModuleMounted = 'true' where id = "+itemOrder.getValue().getId()+"");
//                            pst.executeUpdate();
//                            pst.close();
//                            con.close();
//                            moduleMountedText = "Oznacz Moduł jako wymontowany";
//                            iconMounted[0] = new ImageView(new Image("/resources/images/warning.png"));
//                            iconMounted[0].setFitWidth(16);
//                            iconMounted[0].setFitHeight(16);
//                            item3.setText(moduleMountedText);
//                            item3.setGraphic(iconMounted[0]);
//                            itemOrder.getValue().setModuleMounted("true");
//                            modulesTable.refresh();
//                            Platform.runLater(() -> {
//                                scrollPane.setOnMouseEntered(entered);
//                                scrollPane.setOnMouseExited(exited);
//                            });
//                            scrollPane.setStyle("-fx-background-color: rgb(0, 147, 0);");
//                            view.setContent(scrollPane);
//                        } catch (SQLException ex) {
//                            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
//                            Alert alert = new Alert(Alert.AlertType.ERROR);
//                            alert.setTitle("Błąd");
//                            alert.setHeaderText("SQL "+ex.getCause() );
//                            alert.setContentText("SQL Starte "+ex.getSQLState()+ "Error "+ex.getMessage());
//                            alert.initStyle(StageStyle.UNDECORATED);
//                            alert.showAndWait();
//                        }
//                    }
//
//                }
//            });
//            ImageView iconUtilized = new ImageView(new Image("/resources/images/virtual.png"));
//            iconUtilized.setFitWidth(16);
//            iconUtilized.setFitHeight(16);
//            MenuItem item4 = new MenuItem("Oznacz Maduł jako zutylizowany",iconUtilized);
//            item4.setOnAction(new EventHandler<ActionEvent>() {
//
//                @Override
//                public void handle(ActionEvent event) {
//                    if (orderStatus.equals("virtual")){
//                        con = dbCon.geConnectionDB();
//                        try {
//                            //System.out.println(itemOrder.getValue().getId());
//
//                            pst = con.prepareStatement("DELETE FROM Modules WHERE id='"+itemOrder.getValue().getId()+"';");
//                            pst.executeUpdate();
//                            pst.close();
//                            con.close();
//                            ModuleCell.super.setVisible(false);
//                            itemOrder.getParent().getChildren().remove(itemOrder);
//                            modulesTable.refresh();
//
//                        } catch (SQLException ex) {
//                            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
//                            Alert alert = new Alert(Alert.AlertType.ERROR);
//                            alert.setTitle("Błąd");
//                            alert.setHeaderText("SQL "+ex.getCause() );
//                            alert.setContentText("SQL Starte "+ex.getSQLState()+ "Error "+ex.getMessage());
//                            alert.initStyle(StageStyle.UNDECORATED);
//                            alert.showAndWait();
//                        }
//                    }else{
//                        con = dbCon.geConnectionDB();
//                        try {
//                            pst = con.prepareStatement("update Sell set ModuleMounted = 'truefalse' where id = "+itemOrder.getValue().getId()+"");
//                            pst.executeUpdate();
//                            pst.close();
//                            con.close();
//                            ModuleCell.super.setVisible(false);
//                            // modulesTable.getSelectionModel().getSelectedItem().getChildren().remove(itemOrder);
//                            itemOrder.getParent().getChildren().remove(itemOrder);
//                            modulesTable.refresh();
//
//                        } catch (SQLException ex) {
//                            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
//                            Alert alert = new Alert(Alert.AlertType.ERROR);
//                            alert.setTitle("Błąd");
//                            alert.setHeaderText("SQL "+ex.getCause() );
//                            alert.setContentText("SQL Starte "+ex.getSQLState()+ "Error "+ex.getMessage());
//                            alert.initStyle(StageStyle.UNDECORATED);
//                            alert.showAndWait();
//                        }
//                    }
//
//                }
//            });
//
//            // Add MenuItem to ContextMenu
//            contextMenu.getItems().addAll(item1, item2, item3, item4);
//        }else{
//            contextMenu.getItems().addAll(item1, item2);
//        }
//        // When user right-click on connector
//
//        this.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
//
//            @Override
//            public void handle(ContextMenuEvent event) {
//
//                contextMenu.show(view, event.getScreenX(), event.getScreenY());
//            }
//        });
//        // this.titledPane = view;
//        itemOrder.getValue().setNode2(view);

       // Platform.runLater(() -> {
            if (orderStatus.equals("virtual")) {
                scrollPane.setStyle("-fx-background-color: rgb(54, 113, 207);");
             //   view.setGraphic(virtual);
            }else {
                if (!orderStatus.equals("finished")) {
                    if (orderStatus.equals("started")) {
                    //    view.setGraphic(warning);
                        scrollPane.setStyle("-fx-background-color: rgb(247, 171, 72);");
                    } else {
                        scrollPane.setStyle("-fx-background-color: rgb(147, 147, 147);");
                 //       view.setGraphic(error);
                    }
                } else {
                    if (moduleMounted.equals("true")) {
                        scrollPane.setStyle("-fx-background-color: rgb(0, 147, 0);");
               //         view.setGraphic(success);
                    } else {
                        scrollPane.setStyle("-fx-background-color: rgb(147, 0, 0);");
                //        view.setGraphic(fico);
                    }
                }
            }
        //});
        used.addListener((observableValue, aBoolean, t1) -> {
            if (t1){
                scrollPane.setStyle("-fx-background-color: rgb(0, 147, 0);");
            }else{
                if (orderStatus.equals("virtual")) {
                    scrollPane.setStyle("-fx-background-color: rgb(54, 113, 207);");
                    //   view.setGraphic(virtual);
                }else {
                    if (!orderStatus.equals("finished")) {
                        if (orderStatus.equals("started")) {
                            //    view.setGraphic(warning);
                            scrollPane.setStyle("-fx-background-color: rgb(247, 171, 72);");
                        } else {
                            scrollPane.setStyle("-fx-background-color: rgb(147, 147, 147);");
                            //       view.setGraphic(error);
                        }
                    } else {
                        if (moduleMounted.equals("true")) {
                            scrollPane.setStyle("-fx-background-color: rgb(0, 147, 0);");
                            //         view.setGraphic(success);
                        } else {
                            scrollPane.setStyle("-fx-background-color: rgb(147, 0, 0);");
                            //        view.setGraphic(fico);
                        }
                    }
                }
            }
        });
        view.setContentDisplay(ContentDisplay.LEFT);
//        Tooltip.install(scrollPane, makeBubble( new Tooltip("GMCode: " + Xcode + "\nNazwa: " + Connector + "\nX: " + XCOORD + "\nY: " + YCOORD+"\n \n \n")));

       // this.addSystemPintableId(id);
        this.relocate(xCoord,yCoord);

    }
//    private static final String SQUARE_BUBBLE =
//            "M24 1h-24v16.981h4v5.019l7-5.019h13z";
//    private Tooltip makeBubble(Tooltip tooltip) {
//        tooltip.setStyle("-fx-font-size: 16px; -fx-shape: \"" + SQUARE_BUBBLE + "\";");
//        tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
//        return tooltip;
//    }

    public TitledPane titledPane(){
        return this;
    }




}