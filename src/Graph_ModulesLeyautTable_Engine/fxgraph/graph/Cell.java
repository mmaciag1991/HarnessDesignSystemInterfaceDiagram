package Graph_ModulesLeyautTable_Engine.fxgraph.graph;


import Components.animatefx.animation.Bounce;
import DSI_Graph_Main_Controlers.DSI_Model;
import DSI_Graph_Main_Controlers.TesterLayoutDSI;
import Graph_ModulesLeyautTable_Engine.fxgraph.model.Wires;
import Model.PICtoGIM_Model;
import Model.Section10_Model;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static DSI_Graph_Main_Controlers.DSI_Model.section10_models;

public class Cell extends Pane {

    private String cellId;
    private String cellType;

    private Point2D pointXY;
    private double startPointX;
    private double startPointY;

    private boolean visited;
    private LinkedList<Edge> edges = new LinkedList<>();




    public DoubleProperty positionXProperty = new SimpleDoubleProperty();
    public DoubleProperty positionYProperty = new SimpleDoubleProperty();

    //public ObjectProperty<Node> nodeProperty;
    private  StringProperty Xcode;
    private  StringProperty CustomerCode;
    private  StringProperty GIMCode;
    private  StringProperty Pins;
    private  StringProperty description;
    private  StringProperty cellIdProperty;
    private final StringProperty usedModule = new SimpleStringProperty("brak");
    private final StringProperty ModuleX = new SimpleStringProperty(" ");
    private final StringProperty ModuleY = new SimpleStringProperty(" ");

    public BooleanProperty dockedProperty = new SimpleBooleanProperty();
    public BooleanProperty selectedProperty = new SimpleBooleanProperty();

    private TableView<Module> moduleTableView;

    /*
Automatic layout functionality members
 */
    private final PointVector forceVector = new PointVector(getLayoutX(), getLayoutY());
    private final PointVector updatedPosition = new PointVector(getLayoutX(), getLayoutY());


    public List<Cell> children = new ArrayList<>();
    public List<Cell> parents = new ArrayList<>();
    public List<Color> colors = new ArrayList<>();
    public ObservableList<Cell> pasives = FXCollections.observableArrayList();
    public ObservableList<Wires> wires = FXCollections.observableArrayList();
    public List<String> asemblyVariants = new ArrayList<>();
    private final Tooltip tooltip = new Tooltip("Komponenty:\n");
    private StringProperty cellTypeProperty;


    public HBox colorsBox = new HBox();
    public Cell(){
        setCacheHint(CacheHint.SPEED);
        setCache(true);
        setCacheShape(true);
        setSnapToPixel(true);
    }

    //Node view;
//    public Cell() {
//        this(null, null, null, null, null, null, null);
//    }
    public Cell(String cellId,String cellType) {

        visited = false;
        this.cellId = cellId;
        this.cellIdProperty = new SimpleStringProperty(this.cellId);
        this.cellType = cellType;
        this.cellTypeProperty = new SimpleStringProperty(cellType);

        this.dockedProperty.set(false);

        positionXProperty.bind(this.layoutXProperty());
        positionYProperty.bind(this.layoutYProperty());

        pointXY = new Point2D(positionXProperty.get(),positionYProperty.get());

       // startPointXY = new Point2D(positionXProperty.get(),positionYProperty.get());

        Tooltip.install(this, tooltip );

        colorsBox.prefWidth(70);
        colorsBox.prefHeight(10);
        colorsBox.setLayoutX(1);
        colorsBox.setLayoutY(1);
    }

    public Cell(String cellId, String cellType, String Xcode, String CustomerCode, String GIMCode, String Pins, List<String> asemblyVariants) {

        this.asemblyVariants = asemblyVariants;
        visited = false;
        this.Xcode = new SimpleStringProperty(Xcode);
        this.CustomerCode = new SimpleStringProperty(CustomerCode);

        this.description = new SimpleStringProperty("Brak Danych");
        this.GIMCode = new SimpleStringProperty("Brak Danych");
        this.CustomerCode.addListener((observableValue, s, t1) -> {
            System.out
                    .println(t1);
            translateToGPN(t1);
        });
        translateToGPN(CustomerCode);
//        if (CustomerCode.startsWith("G")){
//            for (PICtoGIM_Model PICtoGIM_model : DSI_Model.getHousing_models()) {
//                if (CustomerCode.equals(PICtoGIM_model.getGmCode()) && (PICtoGIM_model.getGmCode().startsWith("G"))) {
//                    this.description = new SimpleStringProperty(PICtoGIM_model.getDescription());
//                    this.GIMCode = new SimpleStringProperty(PICtoGIM_model.getGmCode());
//                    break;
//                } else {
//                }
//            }
//        }else if(CustomerCode.toLowerCase().contains("splice")){
//            this.description = new SimpleStringProperty(CustomerCode);
//            this.GIMCode = new SimpleStringProperty("SPLICE");
//        } else {
//            for (PICtoGIM_Model PICtoGIM_model : DSI_Model.getHousing_models()) {
//                if (CustomerCode.equals(PICtoGIM_model.getPartnerCode()) && (PICtoGIM_model.getGmCode().startsWith("G"))) {
//                    this.description = new SimpleStringProperty(PICtoGIM_model.getDescription());
//                    this.GIMCode = new SimpleStringProperty(PICtoGIM_model.getGmCode());
//                    break;
//                } else {
//                }
//            }
//    }

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {


            if (event.isSecondaryButtonDown() && event.getClickCount() == 2) {
                cellContextMenu().show(this, event.getScreenX(), event.getScreenY());
            } else
                cellContextMenu().hide();
        });


        this.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            new Bounce((Node)event.getSource()).setResetOnFinished(true).play();
            toFront();
           // colorsBox.setEffect(new Lighting());
            //setStyle("-fx-border-color: LIGHTGRAY;");
            event.consume();
        });
        this.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
           // setStyle("-fx-border-color: transparent;");
           // colorsBox.setEffect(null);
            event.consume();
        });

        this.Pins = new SimpleStringProperty(Pins);


        this.cellId = cellId;
        this.cellIdProperty = new SimpleStringProperty(this.cellId);
        this.cellType = cellType;
        this.cellTypeProperty = new SimpleStringProperty(cellType);

        this.dockedProperty.set(false);

        positionXProperty.bind(this.layoutXProperty());
        positionYProperty.bind(this.layoutYProperty());

        pointXY = new Point2D(positionXProperty.get(),positionYProperty.get());



        Tooltip.install(this, tooltip );

        Platform.runLater(() -> {
        colorsBox.prefWidth(getWidth()+2);
        colorsBox.maxWidth(getWidth()+2);
        colorsBox.prefHeight(getWidth()+2);
        startPointX = positionXProperty.get();
        startPointY = positionYProperty.get();

        });
       // colorsBox.setStyle("-fx-border-color:gray;");
    }

    public void translateToGPN(String CustomerCode) {
        if (CustomerCode.startsWith("G")) {
            for (PICtoGIM_Model PICtoGIM_model : DSI_Model.getHousing_models()) {
                if (CustomerCode.equals(PICtoGIM_model.getGmCode()) && (PICtoGIM_model.getGmCode().startsWith("G"))) {
                    this.description = new SimpleStringProperty(PICtoGIM_model.getDescription());
                    this.GIMCode = new SimpleStringProperty(PICtoGIM_model.getGmCode());
                    break;
                }
            }
        } else if (CustomerCode.toLowerCase().contains("splice")) {
            this.description = new SimpleStringProperty(CustomerCode);
            this.GIMCode = new SimpleStringProperty("SPLICE");
        } else {
            for (PICtoGIM_Model PICtoGIM_model : DSI_Model.getHousing_models()) {
                if (CustomerCode.equals(PICtoGIM_model.getPartnerCode()) && (PICtoGIM_model.getGmCode().startsWith("G"))) {
                    this.description = new SimpleStringProperty(PICtoGIM_model.getDescription());
                    this.GIMCode = new SimpleStringProperty(PICtoGIM_model.getGmCode());
                    break;
                }
            }
        }
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }
    public LinkedList<Edge> getEdges() {
        return edges;
    }

    public /*synchronized*/ void addPasives(String name, String pNuber){
        tooltip.setText(tooltip.getText()+name+" - "+pNuber+"\n");
        for (PICtoGIM_Model PICtoGIM_model : DSI_Model.getHousing_models()) {
            if (pNuber.equals(PICtoGIM_model.getPartnerCode()) && (PICtoGIM_model.getGmCode().startsWith("G"))) {
                tooltip.setText(tooltip.getText()+ PICtoGIM_model.getGmCode() + " - "+ PICtoGIM_model.getDescription()+"\n\n");
                try {
                    for (Map.Entry<String,Color> entry : TesterLayoutDSI.allColorsWithName().entrySet()) {
                        if (PICtoGIM_model.getDescription().toLowerCase().contains(entry.getKey().toLowerCase())) {

                            colors.add(entry.getValue());

                            // super.getColorsStops().add(new Stop(0,entry.getValue()));

                        }
                    }
                } catch (ClassNotFoundException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }

        }


    }

    public void clearPasives(){
        pasives.clear();
        colors.clear();
        tooltip.setText("");
    }

    public /*synchronized*/ void addPasives(Cell cell){
        pasives.add(cell);
    }

    private final Image assemblyIn  = new Image("/resources/icon/pearl_red_24x24.png");
    private final Image assemblyThis  = new Image("/resources/icon/pearl_green_24x24.png");

    public ContextMenu cellContextMenu(){


        TableView<Cell> pasiveTable = new TableView<>();
        pasiveTable.getStylesheets().add("/resources/style/jmetro/JMetroDarkTheme.css");
        pasiveTable.getStyleClass().add("table-view-small");
        TableColumn<Cell, String> idCol = new TableColumn<>("Id");
        TableColumn<Cell, String> nameCol = new TableColumn<>("Nazwa");
        TableColumn<Cell, String> customerCodeCol = new TableColumn<>("Kod Klienta");
        TableColumn<Cell, String> gpnCodeCol = new TableColumn<>("Kod GPN");
        TableColumn<Cell, String> descriptionCol = new TableColumn<>("Opis");
        descriptionCol.setMinWidth(200);
        pasiveTable.setPrefHeight(100);
        pasiveTable.getColumns().addAll(idCol, nameCol, customerCodeCol, gpnCodeCol, descriptionCol);

        idCol.setCellValueFactory(cellData -> cellData.getValue().getCellIdProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().xcodeProperty());
        customerCodeCol.setCellValueFactory(cellData -> cellData.getValue().customerCodeProperty());
        gpnCodeCol.setCellValueFactory(cellData -> cellData.getValue().gimCodeProperty());
        descriptionCol.setCellValueFactory(cellData -> cellData.getValue().getDescription());

        pasiveTable.setItems(pasives);

        pasiveTable.setRowFactory(tableView -> {
            TableRow<Cell> row = new TableRow<>();
            row.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {


                if (event.isSecondaryButtonDown() && event.getClickCount() == 2) {
                    if (row.getItem() != null)
                        row.getItem().cellContextMenu().show(row, event.getScreenX(), event.getScreenY());
                } else
                if (row.getItem() != null)
                    row.getItem().cellContextMenu().hide();
            });

            return row;
        });

        descriptionCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String specie, boolean empty) {
                super.updateItem(specie, empty);
                if (!super.getTableRow().isEmpty())
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setText(specie);
                        try {
                            for (Map.Entry<String, Color> entry : TesterLayoutDSI.allColorsWithName().entrySet()) {
                                if (getTableRow().getItem().getDescription().getValue().replace(" ", "").toLowerCase().contains(entry.getKey().toLowerCase())) {
                                    //System.out.println(entry.getKey());
                                    //setBackground(new Background(new BackgroundFill(entry.getValue(),null,new Insets(2,2,2,2))));
                                    setStyle("-fx-background-radius: 0px;-fx-background-color:" + entry.getKey() + ";-fx-background-insets: 2, 2, 2, 2;-fx-alignment: center;");
                                    //if (entry.getValue().equals(Color.WHITE))
                                    setTextFill(entry.getValue().invert().brighter());
                                    break;
                                } else if (specie.toLowerCase().contains("splice")) {
                                    //setBackground(new Background(new BackgroundFill(entry.getValue(),null,new Insets(2,2,2,2))));
                                    setStyle("-fx-background-radius: 0px;-fx-background-color:BLUEVIOLET;-fx-background-insets: 2, 2, 2, 2;-fx-alignment: center;");
                                    // setTextFill(Color.BLUEVIOLET);
                                } else if (specie.toLowerCase().contains("natural")) {
                                    //setBackground(new Background(new BackgroundFill(entry.getValue(),null,new Insets(2,2,2,2))));
                                    setStyle("-fx-background-radius: 0px;-fx-background-color:WHEAT;-fx-background-insets: 2, 2, 2, 2;-fx-alignment: center;");
                                    setTextFill(Color.BLACK);
                                } else if (specie.equals("Brak Danych")) {
                                    // setTextFill(Color.WHITE);
                                }
                            }
                        } catch (ClassNotFoundException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        //setGraphic(action);
                    }
            }
        });


        TableView<Wires> wiresTable = new TableView<>();
        wiresTable.getStylesheets().add("/resources/style/jmetro/JMetroDarkTheme.css");
        wiresTable.getStyleClass().add("table-view-small");
        TableColumn<Wires, String> wireLp = new TableColumn<>("Id");
        TableColumn<Wires, String> wireName = new TableColumn<>("Nazwa");
        TableColumn<Wires, String> wireColor = new TableColumn<>("Kolor");
        TableColumn<Wires, String> wireXcodeFrom = new TableColumn<>("Xcode z");
        TableColumn<Wires, String> wirePinFrom = new TableColumn<>("Pin z");
        TableColumn<Wires, String> wireXcodeTo = new TableColumn<>("Xcode do");
        TableColumn<Wires, String> wirePinTo = new TableColumn<>("Pin do");
        descriptionCol.setMinWidth(200);
        wiresTable.setPrefHeight(100);
        wiresTable.getColumns().addAll(wireLp, wireName, wireColor, wireXcodeFrom, wirePinFrom, wireXcodeTo, wirePinTo);

        wireLp.setCellValueFactory(cellData -> cellData.getValue().lpProperty());
        wireName.setCellValueFactory(cellData -> cellData.getValue().NameProperty());
        wireColor.setCellValueFactory(cellData -> cellData.getValue().colorProperty());
        wireXcodeFrom.setCellValueFactory(cellData -> cellData.getValue().fromXcodeProperty());
        wirePinFrom.setCellValueFactory(cellData -> cellData.getValue().fromPinProperty());
        wireXcodeTo.setCellValueFactory(cellData -> cellData.getValue().toXcodeProperty());
        wirePinTo.setCellValueFactory(cellData -> cellData.getValue().toPinProperty());
//        wireLenght.setCellValueFactory(cellData -> cellData.getValue().lenghtProperty());
//        wireWay.setCellValueFactory(cellData -> cellData.getValue().wayProperty());
//        wireArea.setCellValueFactory(cellData -> cellData.getValue().areaProperty());

        wiresTable.setItems(wires);

        wiresTable.setRowFactory(tableView -> {
            TableRow<Wires> row = new TableRow<>();
            row.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {


                if (event.isSecondaryButtonDown() && event.getClickCount() == 2) {
                    if (row.getItem() != null)
                        row.getItem().cellContextMenu().show(row, event.getScreenX(), event.getScreenY());
                } else
                if (row.getItem() != null)
                    row.getItem().cellContextMenu().hide();
            });

            return row;
        });

        wireColor.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    switch (item) {
                        case "BK", "SW" -> {
                            setTextFill(Color.WHITE);
                            setStyle("-fx-background-color: black;" +
                                    " -fx-background-radius: 4;\n" +
                                    "    -fx-background-insets: 4;" +
                                    "    -fx-alignment: center;");
                        }
                        case "BN", "BR", "B" -> {
                            setTextFill(Color.WHITE);
                            setStyle("-fx-background-color: SANDYBROWN;" +
                                    " -fx-background-radius: 4;\n" +
                                    "    -fx-background-insets: 4;" +
                                    "    -fx-alignment: center;");
                        }
                        case "N" -> {
                            setTextFill(Color.WHITE);
                            setStyle("-fx-background-color: ROSYBROWN;" +
                                    " -fx-background-radius: 4;\n" +
                                    "    -fx-background-insets: 4;" +
                                    "    -fx-alignment: center;");
                        }
                        case "RD", "RT", "R" -> {
                            setTextFill(Color.BLACK);
                            setStyle("-fx-background-color: red;" +
                                    " -fx-background-radius: 4;\n" +
                                    "    -fx-background-insets: 4;" +
                                    "    -fx-alignment: center;");
                        }
                        case "OG", "OR", "O" -> {
                            setTextFill(Color.BLACK);
                            setStyle("-fx-background-color: orange;" +
                                    " -fx-background-radius: 4;\n" +
                                    "    -fx-background-insets: 4;" +
                                    "    -fx-alignment: center;");
                        }
                        case "YE", "GE", "Y" -> {
                            setTextFill(Color.BLACK);
                            setStyle("-fx-background-color: gold;" +
                                    " -fx-background-radius: 4;\n" +
                                    "    -fx-background-insets: 4;" +
                                    "    -fx-alignment: center;");
                        }
                        case "BU", "BL", "U" -> {
                            setTextFill(Color.WHITE);
                            setStyle("-fx-background-color: ROYALBLUE;" +
                                    " -fx-background-radius: 4;\n" +
                                    "    -fx-background-insets: 4;" +
                                    "    -fx-alignment: center;");
                        }
                        case "VT", "VL" -> {
                            setTextFill(Color.WHITE);
                            setStyle("-fx-background-color: violet;" +
                                    " -fx-background-radius: 4;\n" +
                                    "    -fx-background-insets: 4;" +
                                    "    -fx-alignment: center;");
                        }
                        case "GY", "GR" -> {
                            setTextFill(Color.BLACK);
                            setStyle("-fx-background-color: gray;" +
                                    " -fx-background-radius: 4;\n" +
                                    "    -fx-background-insets: 4;" +
                                    "    -fx-alignment: center;");
                        }
                        case "WH", "WS", "W" -> {
                            setTextFill(Color.BLACK);
                            setStyle("-fx-background-color: white;" +
                                    " -fx-background-radius: 4;\n" +
                                    "    -fx-background-insets: 4;" +
                                    "    -fx-alignment: center;");
                        }
                        case "PK", "RS" -> {
                            setTextFill(Color.BLACK);
                            setStyle("-fx-background-color: pink;" +
                                    " -fx-background-radius: 4;\n" +
                                    "    -fx-background-insets: 4;" +
                                    "    -fx-alignment: center;");
                        }
                        case "GN" -> {
                            setTextFill(Color.WHITE);
                            setStyle("-fx-background-color: green;" +
                                    " -fx-background-radius: 4;\n" +
                                    "    -fx-background-insets: 4;" +
                                    "    -fx-alignment: center;");
                        }
                        default -> {

                        }
                    }
                }
            }
        });





        GridPane gridPaneCell = new GridPane();
        gridPaneCell.setGridLinesVisible(true);
        gridPaneCell.setAlignment(Pos.CENTER);

        Label idLbl1 = new Label(" Id:  ");
        gridPaneCell.add(idLbl1,0,0);

        Label idLbl2 = new Label("  "+getCellId()+"  ");
        gridPaneCell.add(idLbl2,1,0);

        Label xcodeLbl1 = new Label(" Xcode:  ");
        gridPaneCell.add(xcodeLbl1,0,1);

        Label xcodeLbl2 = new Label("  "+getXcode()+"  ");
        gridPaneCell.add(xcodeLbl2,1,1);

        Label customercodeLbl1 = new Label(" Kod Klienta:  ");
        gridPaneCell.add(customercodeLbl1,0,2);

        Label customercodeLbl2 = new Label("  "+getCustomerCode()+"  ");
        gridPaneCell.add(customercodeLbl2,1,2);

        Label gmcodeLbl1 = new Label(" Kod GPN:  ");
        gridPaneCell.add(gmcodeLbl1,0,3);

        Label gmcodeLbl2 = new Label("  "+getGIMCode()+"  ");
        gridPaneCell.add(gmcodeLbl2,1,3);

        Label pincodeLbl1 = new Label(" Liczba pinow:  ");
        gridPaneCell.add(pincodeLbl1,0,4);

        Label pincodeLbl2 = new Label("  "+getPins()+"  ");
        gridPaneCell.add(pincodeLbl2,1,4);

        Button imageButton = new Button("Zaladuj zdjecie");

        imageButton.setPrefSize(350,150);
        gridPaneCell.add(imageButton,2,0,1,5);



        VBox vBox =new VBox();
        Label title = new Label("Obiekt: "+getDescription().get());
        title.setStyle("-fx-font-size: 20;");
        Label title2 = new Label("Komponenty obiektu: ");
        title2.setStyle("-fx-font-size: 20;");
        Label title3 = new Label("Polaczenia: ");
        title3.setStyle("-fx-font-size: 20;");
        Label title4 = new Label("Zawarty w: ");
        title4.setStyle("-fx-font-size: 20;");

        ObservableList<String> asemblyObsList = FXCollections.observableArrayList();


//        asemblyVariants.parallelStream().forEach(s -> {
//            section10_models.parallelStream().forEach(s2 -> {
//
//                if (s.equals(s2.getVariant_())){
//                    asemblyObsList.add("Wariant: "+s2.getVariant_()+"  Referencja: "+s2.getReference()+" "+s2.getVersion()+"  Opis:"+s2.getVariable());
//                }
//
//            });
//        });
        for (Section10_Model s2 : section10_models){
            for (String s : asemblyVariants){

                if (s.equals(s2.getVariant_())){
                    asemblyObsList.add(" "+s2.getVariant_()+"  : "+s2.getReference()+" "+s2.getVersion()+" : "+s2.getVariable());
                }

            }
        }

        ListView<String> asemlylist = new ListView<String>(asemblyObsList);
        asemlylist.setPrefHeight(200);
        asemlylist.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(assemblyIn);

                    for (Map.Entry entry : DSI_Model.listOpenedNumbers.entrySet()){
                       // System.out.println(entry.getValue().toString());
                        if (name.contains(entry.getKey().toString())/* && name.contains(entry.getValue().toString())*/){
                            imageView.setImage(assemblyThis);
                            name = "Otwarte w: "+name;
                        }
                        if (entry.getValue().toString().equals("_Full")){
                            imageView.setImage(assemblyThis);
                            name = "Otwarte w: "+name;
                        }
                    }



                    setText(name);
                    setGraphic(imageView);
                }
            }
        });

        vBox.getChildren().addAll(title, gridPaneCell, title2, pasiveTable, title3, wiresTable, title4,asemlylist);
        vBox.setFillWidth(true);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem tableItem1 = new MenuItem("", vBox);
        imageButton.setOnAction(event -> {
            imageButton.setGraphic(new ImageView(new Image("/resources/images/error.png", 150, 150, true, true)));
            System.out.println(DSI_Model.imagesListParts.size());
            for (String imagePath : DSI_Model.imagesListParts) {
                if (imagePath.contains(getGIMCode())) {
                    System.out.println(imagePath);
                    ImageView connectorImg = new ImageView(new Image("file:" + imagePath, 150, 150, true, true));
                    imageButton.setGraphic(connectorImg);
                    break;
                }
            }
            event.consume();
        });

        //imageButton.setStyle("-fx-background-color:rgba("+colors.get(0).getRed()+","+colors.get(0).getGreen()+","+colors.get(0).getBlue()+",.5)");

        contextMenu.getItems().addAll(tableItem1);

        return contextMenu;
    }

    public void drawColorBox(){

        if (colors.size()>0) {

            for (Color color : colors) {
                Platform.runLater(() -> {
                Rectangle rectangle = new Rectangle();
                rectangle.setWidth(getWidth() / colors.size());
                rectangle.setHeight(10);
                rectangle.setOpacity(.9);
                rectangle.setFill(color);
                rectangle.setStroke(Color.DARKGREY);
               colorsBox.getChildren().add(rectangle);
                });
            }

        }


    }

    public boolean isDocked() {
        return dockedProperty.get();
    }

    public StringProperty getCellTypeProperty() {
        return cellTypeProperty;
    }

    public void setCellTypeProperty(String cellTypeProperty) {
        this.cellTypeProperty.set(cellTypeProperty);
    }

    public /*synchronized*/ void addCellChild(Cell cell) {
        children.add(cell);
    }

    public /*synchronized*/ List<Cell> getCellChildren() {
        return children;
    }

    public /*synchronized*/ void addCellParent(Cell cell) {
        parents.add(cell);
    }

    public /*synchronized*/ List<Cell> getCellParents() {
        return parents;
    }

    public /*synchronized*/ void removeCellChild(Cell cell) {
        children.remove(cell);
    }

    public /*synchronized*/ List<Cell> getPasives(){
        return pasives;
    }

//    public void setView(Node view) {
//
//        this.view = view;
//        getChildren().add(view);
//    }

//    public Node getView() {
//        return this.view;
//    }

    public /*synchronized*/ String getCellType() {
        return cellType;
    }

    public /*synchronized*/ String getCellId() {
        return cellId;
    }
//    double positionX;
//    double positionY;
//    public void translatePosition(double aMovementX, double aMovementY, double aZoomLevel) {
//        setLayoutX(getLayoutX() + aMovementX);
//        setLayoutY(getLayoutY() + aMovementY);
//
//        positionX += aMovementX / aZoomLevel;
//        positionY += aMovementY / aZoomLevel;
//
//    }

    public /*synchronized*/ Point2D getUpdatedPosition() {

        //pointXY.add(updatedPosition.x, updatedPosition.y);
        pointXY = null;
        return pointXY = new Point2D(updatedPosition.x, updatedPosition.y);
    }

    public /*synchronized*/ void updateDelta() {
        updatedPosition.x = updatedPosition.x /* + speed*/ + forceVector.x;
        updatedPosition.y = updatedPosition.y + forceVector.y;
    }
    public /*synchronized*/ void moveFromForces() {

        //limit movement to parent bounds
        double height = getParent().getLayoutBounds().getHeight();
        double width = getParent().getLayoutBounds().getWidth();

        updatedPosition.x = boundCenterCoordinate(updatedPosition.x, 0, width);
        updatedPosition.y = boundCenterCoordinate(updatedPosition.y, 0, height);

//        setLayoutX(updatedPosition.x);
//        setLayoutY(updatedPosition.y);
        Platform.runLater(() -> relocate(updatedPosition.x,updatedPosition.y));
    }
    public /*synchronized*/ void resetForces() {
        forceVector.x = 0;
        forceVector.y = 0;
        updatedPosition.x = getLayoutX();
        updatedPosition.y = getLayoutY();
    }

    public /*synchronized*/ void addForceVector(double x, double y) {
        forceVector.x += x;
        forceVector.y += y;
        //setLayoutX(forceVector.x);
        //setLayoutY(forceVector.y);
    }

    private /*synchronized*/ double boundCenterCoordinate(double value, double min, double max) {
        double radius = 50;

        if (value < min + radius) {
            return min + radius;
        } else if (value > max - radius) {
            return max - radius;
        } else {
            return value;
        }
    }

    public void setTblModule(TableView<Module> mbtn) {
        this.moduleTableView = mbtn;
    }
    public TableView<Module> getModuleTableView(){
        return this.moduleTableView;
    }

    private static class PointVector {

        double x, y;

        public PointVector(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    boolean isVisited() {
        return visited;
    }

    void visit() {
        visited = true;
    }

    void unvisited() {
        visited = false;
    }


    public String getXcode() {
        return Xcode.get();
    }

    public void setXcode(String xcode) {
        this.Xcode.set(xcode);
    }

    public StringProperty xcodeProperty() {
        return Xcode;
    }


    public String getCustomerCode() {
        return CustomerCode.get();
    }

    public void setCustomerCode(String customerCode) {
        this.CustomerCode.set(customerCode);
    }

    public StringProperty customerCodeProperty() {
        return CustomerCode;
    }


    public String getGIMCode() {
        return GIMCode.get();
    }

    public void setGIMCode(String GIMCode) {
        this.GIMCode.set(GIMCode);
    }

    public StringProperty gimCodeProperty() {
        return GIMCode;
    }


    public String getPins() {
        return Pins.get();
    }

    public void setPins(String Status) {
        this.Pins.set(Status);
    }

    public StringProperty pinsProperty() {
        return Pins;
    }

    public DoubleProperty getPositionXProperty() {
        return positionXProperty;
    }

    public DoubleProperty getPositionYProperty() {
        return positionYProperty;
    }

    public BooleanProperty dockedProperty() {
        return dockedProperty;
    }

    public StringProperty usedModuleProperty() {
        return usedModule;
    }
    public StringProperty getCellIdProperty() {
        return cellIdProperty;
    }

    public StringProperty getDescription() {
        return description;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

    public BooleanProperty selectedPropertyProperty() {
        return selectedProperty;
    }

    public boolean isSelected() {
        return selectedProperty.get();
    }

    public void setSelectedProperty(boolean selectedProperty) {
        this.selectedProperty.set(selectedProperty);
    }

    public String getModuleX() {
        return ModuleX.get();
    }

    public String getModuleY() {
        return ModuleY.get();
    }

    public void setModuleX(String moduleX) {
        this.ModuleX.set(moduleX);
    }

    public void setModuleY(String moduleY) {
        this.ModuleY.set(moduleY);
    }

    public StringProperty moduleXProperty() {
        return ModuleX;
    }

    public StringProperty moduleYProperty() {
        return ModuleY;
    }

    public ObservableList<Wires> getWires() {
        return wires;
    }

    public List<String> getAsemblyVariants() {
        return asemblyVariants;
    }

    public void setAsemblyVariants(List<String> asemblyVariants) {
        this.asemblyVariants = asemblyVariants;
    }

    public Point2D getPointXY() { return pointXY; }

    public double getStartPointX() {
        return startPointX;
    }

    public double getStartPointY() {
        return startPointY;
    }

    public void setStartPointX(double startPointX) {
        this.startPointX = startPointX;
    }

    public void setStartPointY(double startPointY) {
        this.startPointY = startPointY;
    }
}