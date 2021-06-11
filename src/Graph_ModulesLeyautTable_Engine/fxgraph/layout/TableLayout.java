package Graph_ModulesLeyautTable_Engine.fxgraph.layout;

import Components.Preloader.ui.RingProgressIndicator;
import Components.taskbar.TaskbarProgressbar;
import DSI_Graph_Main_Controlers.AddVirtualModuleControler;
import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Graph;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static DSI_Graph_Main_Controlers.DSI_Model.taskbarProgressbar;

public class TableLayout {

   //public List<Character> Ycoord = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X');
public static Background gray = new Background(new BackgroundFill(Color.rgb(187, 187, 187), CornerRadii.EMPTY, Insets.EMPTY));
public static Background black = new Background(new BackgroundFill(Color.rgb(0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY));
public static Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
public  GridPane gridPane =  new GridPane();
public  List<Character> Ycoord;
    public  List<StackPane> path50PanesList = new LinkedList<>();
public  boolean piano = false;
//public Slider vRuler;
//public Slider hRuler;
    public GridPane getGridPane() {
        return gridPane;
    }

    public GridPane drawTestTableGrid(int Columns, int Rows, Graph graph)   {

        if(Rows == 24 ) {
            Ycoord = Arrays.asList('1', 'X', 'W', 'V', 'U', 'T', 'S', 'R', 'Q', 'P', 'O', 'N', 'M', 'L', 'K', 'J', 'I', '.','H', 'G', 'F', 'E', 'D', 'C', 'B', 'A');
            Rows = Rows+1;
            piano = true;
        }else{
            Ycoord = Arrays.asList('1', 'X', 'W', 'V', 'U', 'T', 'S', 'R', 'Q', 'P', 'O', 'N', 'M', 'L', 'K', 'J', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A');
        }
        for( int i2 = 1; i2 <= Rows; i2++ ){
            for (int i = 1; i <= Columns; i++) {
                ////System.out.println(i + " - " + alphabet.get(alphabet.size()-i2)+" inc ");

                    if(piano==true && i2 == 17){
                        Text text = new Text(String.valueOf(i));
                        text.setFill(Color.DARKGOLDENROD);
                        text.setStyle("-fx-font: 16 arial;");

                        StackPane pane = new StackPane(text);
                        pane.setBackground(black);

                        pane.setId(text.getText());
                        pane.setAlignment(text, Pos.CENTER);
                        pane.setMinSize(100,80);
                        pane.setBorder(border);
                        gridPane.add(pane, i, i2);

                }else {
                       // if (i2==17)
                       // i2=i2-1;
                    Text text = new Text(i + " - " + Ycoord.get(i2 + Ycoord.size() - Rows - 1));
                    text.setFill(Color.BLACK);
                    text.setStyle("-fx-font: 14 arial;");
                    StackPane pane = new StackPane(text);
                    pane.setBackground(gray);


                    pane.setAlignment(text, Pos.CENTER);
                    pane.setMinSize(100, 100);
                    pane.setBorder(border);
                    pane.setId(text.getText());
                    gridPane.add(pane, i, i2);
                }
               // c++;
                //model.addCell(alphabet+" - "+i, CellType.MODULE,200,100);

            }
        }

        this.gridPane = gridPane;
/**linijka**/
//        int finalRows = Rows;
//        Platform.runLater(
//                () -> {
//
//         hRuler = new Slider(0, Columns*50, 10);
//                    hRuler.getStylesheets().add("/resources/style/jmetro/JMetroDarkTheme.css");
//        hRuler.getStyleClass().add("table-view");
//                    hRuler.setStyle("-fx-background-color: rgb(207, 207, 207);" +
//                            "    -fx-text-fill: #d8d8d8;");
//        hRuler.showTickMarksProperty().setValue(true);
//        hRuler.showTickLabelsProperty().setValue(true);
//                    if (finalRows==25){
//                        hRuler.setLayoutY((finalRows *100)-20);
//                    }else{
//                        hRuler.setLayoutY((finalRows *100));
//                    }
//
//        hRuler.setPrefWidth(Columns*100);
//        //hRuler.setRotate(180);
//
//         vRuler = new Slider(0, finalRows *50, 100);
//        vRuler.getStylesheets().add("/resources/style/jmetro/JMetroDarkTheme.css");
//                    vRuler.getStyleClass().add("table-view");
//                    vRuler.setStyle("-fx-background-color: rgb(207, 207, 207);" +
//                            "    -fx-text-fill: #d8d8d8;");
//        vRuler.setOrientation(Orientation.VERTICAL);
//        vRuler.showTickMarksProperty().setValue(true);
//        vRuler.showTickLabelsProperty().setValue(true);
//        if (finalRows==25){
//        vRuler.setPrefHeight((finalRows *100)-20);
//        }else{
//            vRuler.setPrefHeight(finalRows *100);
//        }
//        //  vRuler.setRotate(180);
//                    vRuler.setPrefWidth(60);
//        vRuler.setLayoutX(vRuler.getLayoutX()-50);
//
//        graph.getScrollPane().vvalueProperty().addListener((observable, oldValue, newValue) -> {
//            vRuler.setValue(((finalRows *100)-20)-(newValue.doubleValue()*((finalRows *100)-20)));
//
//        });
//
//        vRuler.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> source, Boolean oldValue, Boolean newValue) {
//                graph.getScrollPane().setVvalue(vRuler.getValue()/((finalRows *100)-20));
//
//            } });
//                    graph.getScrollPane().hvalueProperty().addListener((observable, oldValue, newValue) -> {
//                        hRuler.setValue(newValue.doubleValue()*(Columns*100));
//
//                    });
//
//                    hRuler.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
//                        @Override
//                        public void changed(ObservableValue<? extends Boolean> source, Boolean oldValue, Boolean newValue) {
//                            graph.getScrollPane().setHvalue(hRuler.getValue()/(Columns*100));
//
//                        } });
//
//
//
//                    graph.getCellLayer().getChildren().addAll(vRuler,hRuler);
//                }
//        );


        return  gridPane;
    }
    public GridPane drawTestTableGrid(int Columns, int Rows, Graph graph, RingProgressIndicator indicator, ProgressBar mainProgressBar, AddVirtualModuleControler addVirtualModuleControler) throws InterruptedException {

        if(Rows == 24 ) {
            Ycoord = Arrays.asList('1', 'X', 'W', 'V', 'U', 'T', 'S', 'R', 'Q', 'P', 'O', 'N', 'M', 'L', 'K', 'J', 'I', '.','H', 'G', 'F', 'E', 'D', 'C', 'B', 'A');
            Rows = Rows+1;
            piano = true;
        }else{
            Ycoord = Arrays.asList('1', 'X', 'W', 'V', 'U', 'T', 'S', 'R', 'Q', 'P', 'O', 'N', 'M', 'L', 'K', 'J', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A');
        }
        double increment = (double) 100 / (Rows*Columns);
        double progress = 0.0;
        for( int i2 = 1; i2 <= Rows; i2++ ) {
            for (int i = 1; i <= Columns; i++) {
                ////System.out.println(i + " - " + alphabet.get(alphabet.size()-i2)+" inc ");

                if (piano == true && i2 == 17) {
                    Text text = new Text(String.valueOf(i));
                    text.setFill(Color.DARKGOLDENROD);
                    text.setStyle("-fx-font: 16 arial;");

                    StackPane pane = new StackPane(text);
                    pane.setBackground(black);

                    pane.setId(text.getText());
                    path50PanesList.add(pane);
                    pane.setAlignment(text, Pos.CENTER);
                    pane.setMinSize(100, 80);
                    pane.setBorder(border);
                    int finalI = i;
                    int finalI1 = i2;
                    Thread.sleep(1);
                    Platform.runLater(() -> {
                        gridPane.add(pane, finalI, finalI1);

                    });
                    progress = progress + increment;
                    double finalProgress = progress;
                    Platform.runLater(() -> {
                        indicator.setProgress(finalProgress);
                        mainProgressBar.setProgress(0.2+(finalProgress/1000));
                        taskbarProgressbar.showCustomProgress(finalProgress/100, TaskbarProgressbar.Type.NORMAL);
                    });

                } else {
                    // if (i2==17)
                    // i2=i2-1;
                    Text text = new Text(i + " - " + Ycoord.get(i2 + Ycoord.size() - Rows - 1));
                    text.setFill(Color.BLACK);
                    text.setStyle("-fx-font: 14 arial;");
                    StackPane pane = new StackPane(text);
                    pane.setBackground(gray);


                    pane.setAlignment(text, Pos.CENTER);
                    pane.setMinSize(100, 100);
                    pane.setBorder(border);
                    pane.setId(text.getText());
                    path50PanesList.add(pane);
                    int finalI = i;
                    int finalI1 = i2;
                    Thread.sleep(1);
                    Platform.runLater(() -> {
                        gridPane.add(pane, finalI, finalI1);

                    });
                    if (pane.getId().length() > 3) {
                        graph.addDropHandlingModule(pane);
                        //  Tooltip.install(stackPane, makeBubble(new Tooltip("Koordynat " + stackPane.getId() + "\n   ")));
                        pane.setOnMouseEntered(event -> {
                            // stackPane.setEffect(new Lighting());
                            pane.setStyle("-fx-background-color: rgba( 187, 187, 187, 0.7);");

                        });
                        pane.setOnMouseExited(event -> {
                            // stackPane.setEffect(null);
                            pane.setStyle("-fx-background-color: rgba( 187, 187, 187, 1);");
                        });

                        pane.setOnMouseClicked(event -> {
//                            if (sideBar2.lastStatus)
//                                sideBar2.setShowSidebar(false);

                            if (event.getClickCount() == 2 && event.getButton().equals(MouseButton.PRIMARY)) {
                                StackPane place = (StackPane) event.getSource();
                                //Text text = (Text) place.getChildren().get(0);
                                Platform.runLater(() -> {
                                    addVirtualModuleControler.getxCode().clear();
                                    addVirtualModuleControler.getCustomerCode().clear();
                                    addVirtualModuleControler.getGmCode().clear();

                                    addVirtualModuleControler.getEndX().getSelectionModel().clearSelection();
                                    addVirtualModuleControler.getEndY().getSelectionModel().clearSelection();
                                    ((Stage)addVirtualModuleControler.getCustomerCode().getScene().getWindow()).show();
                                    addVirtualModuleControler.setStartX(text.getText().split(" - ")[0]);
                                    addVirtualModuleControler.setStartY(text.getText().split(" - ")[1]);
                                });
                            }
                        });
                    }
                    progress = progress + increment;
                    double finalProgress = progress;

                    Platform.runLater(() -> {
                        indicator.setProgress(finalProgress);
                        mainProgressBar.setProgress(0.2+(finalProgress/1000));
                        taskbarProgressbar.showCustomProgress(finalProgress/100, TaskbarProgressbar.Type.NORMAL);
                    });

                }
                // c++;
                //model.addCell(alphabet+" - "+i, CellType.MODULE,200,100);

            }
        }


        this.gridPane = gridPane;
/**linijka**/
//        int finalRows = Rows;
//        Platform.runLater(
//                () -> {
//
//         hRuler = new Slider(0, Columns*50, 10);
//                    hRuler.getStylesheets().add("/resources/style/jmetro/JMetroDarkTheme.css");
//        hRuler.getStyleClass().add("table-view");
//                    hRuler.setStyle("-fx-background-color: rgb(207, 207, 207);" +
//                            "    -fx-text-fill: #d8d8d8;");
//        hRuler.showTickMarksProperty().setValue(true);
//        hRuler.showTickLabelsProperty().setValue(true);
//                    if (finalRows==25){
//                        hRuler.setLayoutY((finalRows *100)-20);
//                    }else{
//                        hRuler.setLayoutY((finalRows *100));
//                    }
//
//        hRuler.setPrefWidth(Columns*100);
//        //hRuler.setRotate(180);
//
//         vRuler = new Slider(0, finalRows *50, 100);
//        vRuler.getStylesheets().add("/resources/style/jmetro/JMetroDarkTheme.css");
//                    vRuler.getStyleClass().add("table-view");
//                    vRuler.setStyle("-fx-background-color: rgb(207, 207, 207);" +
//                            "    -fx-text-fill: #d8d8d8;");
//        vRuler.setOrientation(Orientation.VERTICAL);
//        vRuler.showTickMarksProperty().setValue(true);
//        vRuler.showTickLabelsProperty().setValue(true);
//        if (finalRows==25){
//        vRuler.setPrefHeight((finalRows *100)-20);
//        }else{
//            vRuler.setPrefHeight(finalRows *100);
//        }
//        //  vRuler.setRotate(180);
//                    vRuler.setPrefWidth(60);
//        vRuler.setLayoutX(vRuler.getLayoutX()-50);
//
//        graph.getScrollPane().vvalueProperty().addListener((observable, oldValue, newValue) -> {
//            vRuler.setValue(((finalRows *100)-20)-(newValue.doubleValue()*((finalRows *100)-20)));
//
//        });
//
//        vRuler.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> source, Boolean oldValue, Boolean newValue) {
//                graph.getScrollPane().setVvalue(vRuler.getValue()/((finalRows *100)-20));
//
//            } });
//                    graph.getScrollPane().hvalueProperty().addListener((observable, oldValue, newValue) -> {
//                        hRuler.setValue(newValue.doubleValue()*(Columns*100));
//
//                    });
//
//                    hRuler.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
//                        @Override
//                        public void changed(ObservableValue<? extends Boolean> source, Boolean oldValue, Boolean newValue) {
//                            graph.getScrollPane().setHvalue(hRuler.getValue()/(Columns*100));
//
//                        } });
//
//
//
//                    graph.getCellLayer().getChildren().addAll(vRuler,hRuler);
//                }
//        );


        return  gridPane;
    }


    static Point2D point2D = null;
    static int i = 0;
    static Node node = null;
    static double xpos = 0;
    static double ypos = 0;
    public Point2D getCellPositionCoord(String coordX, String coordY ) {



       try {

            if (coordX.isEmpty() || coordY.isEmpty()){
                point2D = new Point2D(-800,0);
            }else
           for (Node x : gridPane.getChildren()){
               node = x;
               if (node.getId()!=null) {
                   if (node.getId().equals(coordX.split(" - ")[0] + " - " + coordY.split(" - ")[0])) {

                       xpos = node.getLayoutX();
                       ypos = node.getLayoutY();
                       ////System.out.println(xpos + " - " + ypos + " - " + node.getId());
                       point2D = new Point2D(xpos, ypos);
                       break;
                   }
               }else{
                   point2D = new Point2D(-800,0);
               }

           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return point2D;
    }

    public List<StackPane> getPath50PanesList() {
        return path50PanesList;
    }
}
