package DSI_Graph_Main_Controlers;

import Components.AutoCompleteTextField;
import Components.BidirectionalSlider;
import Components.ExportTableToExcel;
import Components.Preloader.ui.RingProgressIndicator;
import Components.animatefx.animation.Bounce;
import Components.animatefx.animation.RubberBand;
import Components.controlsFX13.org.controlsfx.control.table.TableFilter;
import Components.fr.jojal.widget.radial.menu.RadialMenu;
import Components.fr.jojal.widget.radial.menu.RadialMenuContainer;
import Components.fr.jojal.widget.radial.menu.RadialMenuItem;
import Components.taskbar.TaskbarProgressbar;
import DSI_Graph_Main_Controlers.Editors.TSLEditor;
import DSI_Graph_Main_Controlers.XML.SaveLayoutXML_DOM;
import DataAccessLayer.Modules;
import Getway.ModulesGetway;
import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Cell;
import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Module;
import Graph_ModulesLeyautTable_Engine.fxgraph.graph.*;
import Graph_ModulesLeyautTable_Engine.fxgraph.layout.TableLayout;
import Graph_ModulesLeyautTable_Engine.fxgraph.model.Wires;
import List.ListModules;
import Model.*;
import dataBase.DBConnection;
import dataBase.SQL;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.print.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import static DSI_Graph_Main_Controlers.DSI_Model.*;

public class TesterLayoutDSI {

    @FXML
    StackPane layoutStack;
    @FXML
    private Tab tslEditorPane;
    @FXML
    private SplitPane toggleLeftTable;
    @FXML
    private ToggleButton btnToggleLeftTable;
    @FXML
    private TableView<Module> modulesTable;
    @FXML
    private TableColumn<Module,String> orderId;
    @FXML
    private TableColumn<Module,String> moduleName;
    @FXML
    private TableColumn <Module,String> moduleType;
    @FXML
    private TableColumn <Module,String> moduleX;
    @FXML
    private TableColumn <Module,String> moduleY;
//    @FXML
//    private TableColumn <Module,String> OrderStatus;
//    @FXML
//    private TableColumn <Module,String> moduleMounted;

    @FXML
    private TableView<Cell> connectorsTableView;
    @FXML
    private TableColumn <Cell,String> connectorId;
    @FXML
    private TableColumn <Cell,String> connectorXcode;

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
    private TableColumn <Cell,Boolean> connectorDocked;
    @FXML
    private TableColumn <Cell,String> connectorDescription;
    @FXML
    private TableColumn <Cell,String> connectorModule;
    @FXML
    private TableColumn <Cell,String> connectorModuleX;
    @FXML
    private TableColumn <Cell,String> connectorModuleY;

    @FXML
    private TableView<Wires> wiresTableView;
    @FXML
    private TableColumn <Wires,String> wireLp;
    @FXML
    private TableColumn <Wires,String> wireName;
    @FXML
    private TableColumn <Wires,String> wireColor;
    @FXML
    private TableColumn <Wires,String> wireXcodeFrom;
    @FXML
    private TableColumn <Wires,String> wirePinFrom;
    @FXML
    private TableColumn <Wires,String> wireXcodeTo;
    @FXML
    private TableColumn <Wires,String> wirePinTo;
    @FXML
    private TableColumn <Wires,String> wireLenght;
    @FXML
    private TableColumn <Wires,String> wireArea;

    @FXML
    private TableColumn <Wires,String> wireWay;


    @FXML
    private TableView<Cell> componentTableView;
    @FXML
    private TableColumn <Cell,String> componentXcode;
    @FXML
    private TableColumn <Cell,String> componentCustomerCode;
    @FXML
    private TableColumn <Cell,String> componentGimCode;
    @FXML
    private TableColumn <Cell,String> componentX;
    @FXML
    private TableColumn <Cell,String> componentY;
    @FXML
    private TableColumn <Cell,String> componentId;
    @FXML
    private TableColumn <Cell,String> componentDescription;


    @FXML
    private StackPane zoomSliderContiner;
    @FXML
    private VBox preloaderBox;

    //    @FXML
//    private ChoiceBox<String> shchoiceBox;
//    @FXML
//    private TextField shtextField;
//    @FXML
//    private Button search;
    @FXML
    private Button print;
    @FXML
    private Label position;

    @FXML
    private Label fps;
    @FXML
    private Label edgeProgressLbl;
    @FXML
    private ProgressBar edgeProgressPbr;

    //    @FXML
//    private Pane mapPane;
    @FXML
    private AnchorPane main;

//    @FXML
//    private CheckMenuItem automatic;
//    @FXML
//    private CheckMenuItem selection;
//    @FXML
//    private CheckMenuItem move;
//    @FXML
//    private CheckMenuItem otherview;
//    @FXML
//    private CheckMenuItem layoutShowed;
//    //@FXML
//    //private CheckMenuItem diagramShowed;
//    @FXML
//    private CheckMenuItem splicesShowed;
//    @FXML
//    private CheckMenuItem componentsShowed;
//    @FXML
//    private CheckMenuItem anothersShowed;
//
//    @FXML
//    private MenuButton mainMenuButton;
//

    @FXML
    private ImageView miniMapVisableBtn;
    @FXML
    private ImageView layoutVisableBtn;
    @FXML
    private ImageView spliceVisableBtn;
    @FXML
    private ImageView labelsVisableBtn;
    @FXML
    private ImageView componentVisableBtn;
    @FXML
    private ImageView anotherVisableBtn;
    @FXML
    private ImageView automaticBtn;
    @FXML
    private ImageView selectableBtn;
    @FXML
    private ImageView movableBtn;

    @FXML
    private ImageView analyzeBtn;

    @FXML
    private ImageView tooShortImg;

    @FXML
    private ImageView reloadToStartPoints;
    @FXML
    private ImageView saveToStartPoints;

    /**c0nverter buttons*/
    @FXML
    private ImageView convertTCLimg;
    @FXML
    private ImageView convertTSLimg;
    @FXML
    private ImageView convertCSWIN16img;
    @FXML
    private ImageView convertCSWIN32img;
    @FXML
    private ImageView convertCSVimg;

    @FXML
    private Button convertTCLbtn;
    @FXML
    private Button convertTSLbtn;
    @FXML
    private Button convertCSWIN16btn;
    @FXML
    private Button convertCSWIN32btn;
    @FXML
    private Button convertCSVbtn;


    @FXML
    private ToggleButton AutomGraphSettingsBtn;

    @FXML
    private CheckBox similarXcodeAnalyze;
    @FXML
    private CheckBox anotherXcodeAnalyze;
    @FXML
    private HBox searchBox;

    @FXML
    private ToggleButton clearSearchCell;

    @FXML
    private ToggleButton showHideBottomHud;

    @FXML
    private Group mainHud;

    AddVirtualModuleControler addVirtualModuleControler;

   // TSLEditor tslEditor = new TSLEditor();

    List<String> modulesNames;

    public  BooleanProperty automaticProperty = new SimpleBooleanProperty();
    public  BooleanProperty selectionProperty = new SimpleBooleanProperty();
    public  BooleanProperty movedProperty = new SimpleBooleanProperty();
    public  BooleanProperty otherviewProperty = new SimpleBooleanProperty();
    public  BooleanProperty layoutVisableProperty = new SimpleBooleanProperty();
    // public BooleanProperty diagramVisableProperty = new SimpleBooleanProperty();

    public  BooleanProperty splicesVisableProperty = new SimpleBooleanProperty();
    public  BooleanProperty componentsVisableProperty = new SimpleBooleanProperty();
    public  BooleanProperty anothersVisableProperty = new SimpleBooleanProperty();

    public static  BooleanProperty edgeLabelsVisableProperty = new SimpleBooleanProperty();
//
//    public  BooleanProperty similarXcodeAnalyze = new SimpleBooleanProperty();
//    public  BooleanProperty anotherXcodeAnalyze = new SimpleBooleanProperty();

    private  RingProgressIndicator indicator;
    private ProgressBar mainProgressBar;

    //public  ObservableList<MenuItem> mainMenuItems = FXCollections.observableArrayList();

    public  TilePane noCordBoxContent;
    public  TitledPane noCordBox;

    public  TableLayout tableLayout;

    public  DecimalFormat df = new DecimalFormat("#.##");

    public  Graph graph = new Graph();
    public  MiniMap miniMap = new MiniMap(graph);
    //public  Data data = new Data();
//    double orgSceneX, orgSceneY;
//    double orgTranslateX, orgTranslateY;

    //dPane testerTableGrid = tableLayout.getGridPane();

    public  ObservableList<SystemPintable> systemPintable = FXCollections.observableArrayList()/*= data.getSystemPintable()*/;

//    public  TreeItem<SystemPintable> itemRoot =  new TreeItem<>(new SystemPintable("Zamówienia","","","","","","",null,"",""));
//    public  TreeItem<SystemPintable> itemOrder = null;
//    public  TreeItem<SystemPintable> itemOrderRoot = null;

    public  DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    public  MouseGestures mouseGestures = null;

    public  Label processname = new Label();

    public  WireColorTranslator wireColorTranslator = new WireColorTranslator();

    Scale scale;

    public  BidirectionalSlider bidirectionalSlider = new BidirectionalSlider(0.4,0.4,0,2,0.2,2);
    public  Slider zoomSlider = null;

    String stylePopup =
            """
                    -fx-background-color: rgba(40, 43, 48, 0.8);
                    -fx-background-insets: 0;
                    -fx-padding: 5px;
                    -fx-background-radius: 5px;
                    -fx-border-radius: 5px;
                    -fx-border-color: rgba(245, 245, 245, 0.1);
                    -fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.5) , 5.0, 5.0 , 5.0 , 5.0);""";

    //public  SideBarEntry sideBar2 = null;

    Stage virtualModuleStage = new Stage(StageStyle.TRANSPARENT);

    private final ContextMenu AutomaticGraphSettingsCm = new ContextMenu();


    String TesterName;
    int Columns;
    int Rows;

    double mouseX = 0;
    double mouseY = 0;

    int threadPause = 0;

    String reference = "";




    public void ImgBtnOpacity(boolean b,Node node){
        if (b) node.setOpacity(.5);
        if (!b)node.setOpacity(1);
    }

    public TesterLayoutDSI(){
        //Stack Listner test  System.out.println(2/0);
    }

    @FXML
    private void initialize() {


        FXMLLoader fxmlLoaderAutomaticGraphSettings = new FXMLLoader();
        try {
            fxmlLoaderAutomaticGraphSettings.load(getClass().getResource("/view/application/AutomaticGraphSettings.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane AutomaticGraphSettingsView = fxmlLoaderAutomaticGraphSettings.getRoot();

        //AutomaticGraphSettingsControler automaticGraphSettingsControler = fxmlLoaderAutomaticGraphSettings.getController();

        AutomaticGraphSettingsCm.getItems().add(new MenuItem("",AutomaticGraphSettingsView));

        AutomGraphSettingsBtn.setContextMenu(AutomaticGraphSettingsCm);

        indicator = new RingProgressIndicator();
        mainProgressBar = new ProgressBar();
        mainProgressBar.setMaxWidth(200);

        noCordBoxContent = new TilePane();
        noCordBox = new TitledPane();



//        df = new DecimalFormat("#.##");

//        graph = new Graph();
//        miniMap = new MiniMap(graph);
        //data = new Data();
        tableLayout  = new TableLayout();





        modulesNames = new ArrayList<>();


        mouseGestures = new MouseGestures(graph);


        layoutStack.getChildren().add(graph.scrollPane);

        preloaderBox.getChildren().addAll(indicator,mainProgressBar,processname);

/**przyciski menu i bindowanie*/
/***/
        // sideBar2 = new SideBarEntry();

        zoomSlider = bidirectionalSlider.getSlider();

//        zoomSlider.valueProperty().addListener((observableValue, number, t1) -> {
//            if (t1.doubleValue() < 0.8){
//                edgeLabelsVisableProperty.set(false);
//            }else {
//                edgeLabelsVisableProperty.set(true);
//            }
//        });


        {
//            layoutStack.setStyle("""
//                                -fx-background-color: #D3D3D333,
//                                        linear-gradient(from 0.5px 0.0px to 10.5px  0.0px, repeat, black 5%, transparent 5%),
//                                        linear-gradient(from 0.0px 0.5px to  0.0px 10.5px, repeat, black 5%, transparent 5%);
//                                """);



            noCordBox.setStyle(
                    "    -fx-background-color: rgba(189, 189, 189, 0.0);");
            noCordBox.setText("Modu³y bez koordynatów.");
            noCordBox.setContent(noCordBoxContent);
            noCordBox.setMaxWidth(800);
            mouseGestures.makeDraggable(noCordBox);
            noCordBox.setCollapsible(false);


        }


        /**dodawanie modu³u*/
        {
            FXMLLoader fxmlLoaderAddVirtualModule = new FXMLLoader();
            try {
                fxmlLoaderAddVirtualModule.load(getClass().getResource("/view/application/AddVirtualModule.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            AnchorPane virtualModuleVbox = fxmlLoaderAddVirtualModule.getRoot();

            addVirtualModuleControler = fxmlLoaderAddVirtualModule.getController();

            Scene virtualModuleScene = new Scene(virtualModuleVbox, 700, 440, true, SceneAntialiasing.BALANCED);
            virtualModuleScene.getStylesheets().add("/resources/style/jmetro/JMetroDarkTheme.css");
            virtualModuleStage.setScene(virtualModuleScene);
            addVirtualModuleControler.getVirtualModuleCancelbtn().setOnAction(event -> virtualModuleStage.close());
        }



        /***  Alert tooshort**/
        Tooltip tooShortNameTooltip = new Tooltip();
        Tooltip.install(tooShortImg, tooShortNameTooltip);
        DSI_Model.oneSecondBln.addListener((observableValue, aBoolean, t1) -> {
            if (graph.getMouseGestures().tooShort.get()){
                tooShortImg.setVisible(true);
                tooShortNameTooltip.setText(graph.getMouseGestures().tooShortEdgeName );
                if (t1) {
                    tooShortImg.setOpacity(1);
                }else {
                    tooShortImg.setOpacity(.2);
                }
            }else{
                tooShortImg.setVisible(false);
            }
        });



        miniMapVisableBtn.setOnMouseEntered(mouseEvent -> new RubberBand((Node) mouseEvent.getSource()).play());

        layoutVisableBtn.setOnMouseEntered(mouseEvent -> new RubberBand((Node) mouseEvent.getSource()).play());

        spliceVisableBtn.setOnMouseEntered(mouseEvent -> new RubberBand((Node) mouseEvent.getSource()).play());

        labelsVisableBtn.setOnMouseEntered(mouseEvent -> new RubberBand((Node) mouseEvent.getSource()).play());

        componentVisableBtn.setOnMouseEntered(mouseEvent -> new RubberBand((Node) mouseEvent.getSource()).play());

        anotherVisableBtn.setOnMouseEntered(mouseEvent -> new RubberBand((Node) mouseEvent.getSource()).play());

        automaticBtn.setOnMouseEntered(mouseEvent -> new RubberBand((Node) mouseEvent.getSource()).play());

        selectableBtn.setOnMouseEntered(mouseEvent -> new RubberBand((Node) mouseEvent.getSource()).play());

        movableBtn.setOnMouseEntered(mouseEvent -> new RubberBand((Node) mouseEvent.getSource()).play());

        analyzeBtn.setOnMouseEntered(mouseEvent -> new RubberBand((Node) mouseEvent.getSource()).play());

        tooShortImg.setOnMouseEntered(mouseEvent -> new RubberBand((Node) mouseEvent.getSource()).play());

        clearSearchCell.setOnMouseEntered(mouseEvent -> new RubberBand((Node) mouseEvent.getSource()).play());

        //showHideBottomHud.setOnMouseEntered(mouseEvent -> { new Pulse((Node) mouseEvent.getSource()).play();});

        convertTCLbtn.setOnMouseEntered(mouseEvent -> new RubberBand(convertTCLimg).play());

        convertTSLbtn.setOnMouseEntered(mouseEvent -> new RubberBand(convertTSLimg).play());

        convertCSWIN16btn.setOnMouseEntered(mouseEvent -> new RubberBand(convertCSWIN16img).play());

        convertCSWIN32btn.setOnMouseEntered(mouseEvent -> new RubberBand(convertCSWIN32img).play());

        convertCSVbtn.setOnMouseEntered(mouseEvent -> new RubberBand(convertCSVimg).play());

        reloadToStartPoints.setOnMouseEntered(mouseEvent -> new RubberBand((Node) mouseEvent.getSource()).play());
        Tooltip.install(reloadToStartPoints,new Tooltip("Przywroc do ostatniej pozycji."));
        reloadToStartPoints.setOnMouseClicked(mouseEvent -> {
            graph.getModel().getAllCells().forEach(cell -> {
                System.out.println(cell.getCellId());
                cell.relocate(cell.getStartPointX(),cell.getStartPointY());
            });
        });

        saveToStartPoints.setOnMouseEntered(mouseEvent -> new RubberBand((Node) mouseEvent.getSource()).play());
        Tooltip.install(saveToStartPoints,new Tooltip("Zapisz pozycje."));
        saveToStartPoints.setOnMouseClicked(mouseEvent -> {
            graph.getModel().getAllCells().forEach(cell -> {
                System.out.println(cell.getCellId());
                cell.setStartPointX(cell.getPointXY().getX());
                cell.setStartPointY(cell.getPointXY().getY());

            });
        });

    }

    public StackPane getLayoutStack() {
        return layoutStack;
    }

    public AnchorPane getMain() {
        return main;
    }

//    private final String SQUARE_BUBBLE =
//            "M24 1h-24v16.981h4v5.019l7-5.019h13z";
//    private Tooltip makeBubble(Tooltip tooltip) {
//        tooltip.setStyle("-fx-font-size: 16px; -fx-shape: \"" + SQUARE_BUBBLE + "\";");
//        tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
//        return tooltip;
//    }



    public TesterLayoutDSI RunThread(String variant, String reference, String version, String TesterName, String TesterID, int Columns, int Rows, ObservableList<SystemPintable> systemPintableXML) {
//Platform.runLater(() -> tablesDecorationsAndDatas());

        this.reference = reference;
        preloaderBox.toFront();

//        processname.setStyle("-fx-background-color: rgba(40, 43, 48, 0.9);\n" +
//                "-fx-background-insets: 0;\n" +
//                "-fx-padding: 5px;\n" +
//                "/*-fx-background-radius: 5px;*/\n" +
//                "-fx-border-radius: 5px;\n" +
//                "-fx-border-color: rgba(245, 245, 245, 0.1);\n" +
//                "-fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.6) , 6, 0.0 , 0 , 0);\n" +
//                "        -fx-font-size: 10;");
        this.Columns = Columns;
        this.Rows = Rows;

        AtomicReference<String> Asembly = new AtomicReference<>("");

        if (!variant.equals("_Full")) {
            for (Section10_Model section10_model : DSI_Model.getSection10_models()) {
//               //System.out.println("------------------------------------------Current variant------------"+section10_model.getVariant_());
//               //System.out.println("------------------------------------------Current reference------------"+section10_model.getReference());
//               //System.out.println("------------------------------------------Current version------------"+section10_model.getVersion());
                if (section10_model.getReference().equals(reference) && version.contains(section10_model.getVersion())){
                    Asembly.set(section10_model.getVariant_());
//                   //System.out.println("------------------------------------------d------------"+section10_model.getVariant_());
                }
            }
        }else{
            Asembly.set(variant);
        }

        Model model = graph.getModel();


        graph.scrollPane.setDisable(true);
        graph.scrollPane.setEffect(new MotionBlur());
        graph.scrollPane.setDisable(true);
        // sideBar2.setLayoutX((main.getWidth() / 2) - (sideBar2.getWidth() / 2));


        /**zaznaczanie */
        graph.getRubberBandSelection().setSelectionEnabled(true);
        selectionProperty.addListener((observableValue, aBoolean, t1) -> graph.getRubberBandSelection().setSelectionEnabled(t1));

        final Map<Character, Integer> map = new HashMap<>();
        graph.beginUpdate();

        Task task_start = new Task(){


            @Override
            protected Object call() {

                try {
                    Platform.runLater(()->main.setDisable(true));


/**drukowanie layautu*/

                    print.setOnAction(event -> printJob());

//
///**wyszukiwanie*/
//                    {
//                    Platform.runLater(() -> {
//                        shchoiceBox.getItems().addAll(orderId.getText(), moduleName.getText(), moduleType.getText());
//                        shchoiceBox.getSelectionModel().select(0);
//                    });
//                    search.setOnAction(event -> {
//                        if (shchoiceBox.getSelectionModel().getSelectedItem().equals(orderId.getText())) {
//                            for (TreeItem<SystemPintable> x : modulesTable.getRoot().getChildren()) {
//                                if (x.getValue().getSellId().contains(shtextField.getText())) {
//                                    modulesTable.getSelectionModel().select(x);
//                                    modulesTable.scrollTo(modulesTable.getSelectionModel().getFocusedIndex());
//                                    x.setExpanded(true);
//                                }
//                            }
//                        }
//                        if (shchoiceBox.getSelectionModel().getSelectedItem().equals(moduleName.getText())) {
//                            for (TreeItem<SystemPintable> x : modulesTable.getRoot().getChildren()) {
//                                for (TreeItem<SystemPintable> x2 : x.getChildren()) {
//                                    if (x2.getValue().getXcode().contains(shtextField.getText())) {
//                                        modulesTable.getSelectionModel().select(x2);
//                                        modulesTable.scrollTo(modulesTable.getSelectionModel().getFocusedIndex());
//                                        x.setExpanded(true);
//                                    }
//                                }
//                            }
//                        }
//                        if (shchoiceBox.getSelectionModel().getSelectedItem().equals(moduleType.getText())) {
//                            for (TreeItem<SystemPintable> x : modulesTable.getRoot().getChildren()) {
//                                for (TreeItem<SystemPintable> x2 : x.getChildren()) {
//                                    if (x2.getValue().getConnector().contains(shtextField.getText())) {
//                                        modulesTable.getSelectionModel().select(x2);
//                                        modulesTable.scrollTo(modulesTable.getSelectionModel().getFocusedIndex());
//                                        x.setExpanded(true);
//                                    }
//                                }
//                            }
//                        }
//
//                    });
//                }




                    Platform.runLater(()-> {
                        zoomSlider.setOrientation(Orientation.VERTICAL);
                        zoomSliderContiner.getChildren().add(zoomSlider);
                        zoomSlider.setMajorTickUnit(0.1);
                        zoomSlider.setShowTickMarks(true);
                        zoomSlider.setShowTickLabels(true);

                    });

                    zoomSlider.valueProperty().addListener((observable, oldValue, newValue) -> graph.scrollPane.zoomTo(newValue.doubleValue()));

                    graph.scrollPane.scaleTransform.xProperty().addListener((observable, oldValue, newValue) -> zoomSlider.setValue(newValue.doubleValue()));
//        graph.scrollPane.scaleTransform.xProperty().bind(zoomSlider.valueProperty());
//        graph.scrollPane.scaleTransform.yProperty().bind(zoomSlider.valueProperty());





                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        };
        // task_start.run();
        mainProgressBar.setProgress(0.1);
        Task task_1 = new Task(){

            @Override
            protected Object call() {/**update(ii,"    Rysowanie Siatki Leyautu ");*/
                {
                    /**update(ii,"    Rysowanie Siatki Leyautu ");*/
                    Platform.runLater(() -> processname.setText("Rysowanie Siatki Leyautu"));

                    /*GridPane TesterTable = */
                    Platform.runLater(() -> {
                        graph.getCellLayer().getChildren().addAll(tableLayout.getGridPane(), noCordBox);
                    });


                    try {
                        tableLayout.drawTestTableGrid(Columns, Rows, graph,indicator,mainProgressBar,addVirtualModuleControler);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //tableLayout.drawTestTableGrid(Columns, Rows, graph);

//                    Platform.runLater(() -> processname.setText("Stosowanie Ustawieñ Siatki"));
//                    double i2 = 0;
//                    double progress = (double) 100 / tableLayout.getGridPane().getChildren().size();
//                for (Node nStPa : tableLayout.getGridPane().getChildren()) {
//
//                    StackPane stackPane = (StackPane) nStPa;
//
//                    if (stackPane.getId().length() > 3) {
//                      //  Tooltip.install(stackPane, makeBubble(new Tooltip("Koordynat " + stackPane.getId() + "\n   ")));
//                        graph.addDropHandlingModule(stackPane);
//                        stackPane.setOnMouseEntered(event -> {
//                            // stackPane.setEffect(new Lighting());
//                            stackPane.setStyle("-fx-background-color: rgba( 187, 187, 187, 0.7);");
//
//                        });
//                        stackPane.setOnMouseExited(event -> {
//                            // stackPane.setEffect(null);
//                            stackPane.setStyle("-fx-background-color: rgba( 187, 187, 187, 1);");
//                        });
//
//                        stackPane.setOnMouseClicked(event -> {
//
//                            if (event.getClickCount() == 2 && event.getButton().equals(MouseButton.PRIMARY)) {
//                                StackPane place = (StackPane) event.getSource();
//                                Text text = (Text) place.getChildren().get(0);
//
//                                    addVirtualModuleControler.getxCode().clear();
//                                    addVirtualModuleControler.getCustomerCode().clear();
//                                    addVirtualModuleControler.getGmCode().clear();
//
//                                    addVirtualModuleControler.getEndX().getSelectionModel().clearSelection();
//                                    addVirtualModuleControler.getEndY().getSelectionModel().clearSelection();
//                                    virtualModuleStage.show();
//                                    addVirtualModuleControler.setStartX(text.getText().split(" - ")[0]);
//                                    addVirtualModuleControler.setStartY(text.getText().split(" - ")[1]);
//
//                            }
//                        });
//                        i2 = i2 + progress;
//                        double finalI = i2;
//                        Platform.runLater(() -> { indicator.setProgress(finalI);});
//                    }
//                }

                }
                System.out.println("stepf");
                return null;
            }
        };
        task_start.setOnSucceeded(workerStateEvent -> {
            //task_1.run();

            Thread th = new Thread(task_1);
            th.setName("1");
            th.setPriority(Thread.MAX_PRIORITY);
            th.setDaemon(true);
            th.start();
        });
        //task_start.run();
        Thread th = new Thread(task_start);
        th.setName("start");
        th.setPriority(Thread.MAX_PRIORITY);
        th.setDaemon(true);
        th.start();

        Task task_2 = new Task(){

            @Override
            protected Object call() { /** update(ii,"    Ladowanie Modu³ów ");*/
                try{


                    if (systemPintableXML.isEmpty()){
                        Platform.runLater(() -> {
                            //  indicator.setProgress(finalIi1);
                            processname.setText("Ladowanie Modu³ów ");
                        });
                        /** update(ii,"    Ladowanie Modu³ów ");*/
                        con = dbCon.geConnectionDB();
                        SQL sql = new SQL();
                        try {

                            int size = 1;
                            double i2 = 0;

//                            pst = con.prepareStatement("SELECT count(*) from Sell");
//                            rs = pst.executeQuery();
//                            rs.next();
//                            size = rs.getInt(1);


                            pst.close();
                            rs.close();

                            double progress = (double) 100 / size;

                            pst = con.prepareStatement("select * from Sell");
                            rs = pst.executeQuery();
                            String mounted;
                            while (rs.next()) {
                                mounted = rs.getString("ModuleMounted");
//                            //System.out.println(mounted);
                                if (!mounted.equals("truefalse")) {
                                    if (rs.getString(27).equals(TesterName)) {
                                        //String SellId, String Id, String Xcode, String Connector, String Pins, String CoordinateX, String CoordinateY, javafx.scene.Node node, String ModuleMounted, String OrderStatus
                                        String xcode = sql.getName(rs.getString(4), rs.getString(4), "Products");
                                        systemPintable.add(new SystemPintable("" + rs.getString(2), "" + rs.getString(1), "" + xcode/*rs.getString(4)*/, "" + sql.getName(rs.getString(4), "", "Products"), "" + rs.getString(5), "" + rs.getString(28), "" + rs.getString(29), null, "" + mounted, "" + rs.getString(30)));
                                        i2 = i2 + progress;

                                        try {
                                            Thread.sleep(threadPause);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        double finalI = i2;
                                        Platform.runLater(() -> {
                                            indicator.setProgress((finalI));
                                            mainProgressBar.setProgress(0.3+(finalI/1000));
                                            taskbarProgressbar.showCustomProgress(finalI/100, TaskbarProgressbar.Type.NORMAL);
                                        });
                                    }
                                }
                            }

                            pst.close();
                            con.close();
                            rs.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                        }

                        Modules modules = new Modules();
                        ModulesGetway modulesGetway = new ModulesGetway();
                        modules.TesterId = TesterID;
                        modulesGetway.searchViewTester(modules);

                        if (modules.modulesList.size() != 0) {
                            double i2 = 0;
                            double progress2 = (double) 100 / modules.modulesList.size();
                            for (ListModules listModules : modules.modulesList) {
                                if (listModules.getTesterId().equals(TesterID)) {
                                    //String SellId, String Id, String Xcode, String Connector, String Pins, String CoordinateX, String CoordinateY, javafx.scene.Node node, String ModuleMounted, String OrderStatus

                                    systemPintable.add(new SystemPintable(listModules.getSellId(), listModules.getId(), listModules.getxCode(), listModules.getTConnector(), listModules.getPins(), listModules.getCoordinateX(), listModules.getCoordinateY(), null, "true", "virtual"));

                                }
                                i2 = i2 + progress2;


                                try {
                                    Thread.sleep(threadPause);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                double finalI = i2;
                                Platform.runLater(() -> {
                                    indicator.setProgress((finalI));
                                    mainProgressBar.setProgress(0.3+(finalI/1000));
                                    taskbarProgressbar.showCustomProgress(finalI/100, TaskbarProgressbar.Type.NORMAL);
                                });
                            }
                        }
                    }else{
                        systemPintable.addAll(systemPintableXML);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task_1.setOnSucceeded(workerStateEvent -> {
            // task_1.run();
            // mainProgressBar.setProgress(0.3);
            Thread th2 = new Thread(task_2);
            th2.setName("1");
            th2.setPriority(Thread.MAX_PRIORITY);
            th2.setDaemon(true);
            th2.start();
        });
        //task_2.run();

        Task task_3 = new Task(){
            @Override
            protected Object call() {/***        Rysowanie Modu³ów Na Leyaucie          *********/
                try {
                    map.put('z', 1);
                    map.put('y', 2);
                    map.put('x', 3);
                    map.put('w', 4);
                    map.put('v', 5);
                    map.put('u', 6);
                    map.put('t', 7);
                    map.put('s', 8);
                    map.put('r', 9);
                    map.put('q', 10);
                    map.put('p', 11);
                    map.put('o', 12);
                    map.put('n', 13);
                    map.put('m', 14);
                    map.put('l', 15);
                    map.put('k', 16);
                    map.put('j', 17);
                    map.put('i', 18);
                    map.put('h', 19);
                    map.put('g', 20);
                    map.put('f', 21);
                    map.put('e', 22);
                    map.put('d', 23);
                    map.put('c', 24);
                    map.put('b', 25);
                    map.put('a', 26);

                    //


                    addVirtualModuleControler.getVirtualModuleOKbtn().setOnAction(event -> {


                        if (!addVirtualModuleControler.checkAll()){
                            SystemPintable xSP = new SystemPintable();
                            xSP.setId(addVirtualModuleControler.getxCode().getText());
                            xSP.setXcode(addVirtualModuleControler.getxCode().getText());

                            xSP.setConnector(addVirtualModuleControler.getGmCode().getText());
                            xSP.setPins(addVirtualModuleControler.getPins().getValue().toString());
                            Object endX = addVirtualModuleControler.getEndX().getValue();
                            Object endY = addVirtualModuleControler.getEndY().getValue();
                            if (endX!=null){
                                xSP.setCoordinateX(addVirtualModuleControler.getStartX().getValue() + " - " + endX);
                            }else {
                                xSP.setCoordinateX(addVirtualModuleControler.getStartX().getValue().toString());
                            }

                            if (endY!=null){
                                xSP.setCoordinateY(addVirtualModuleControler.getStartY().getValue() + " - " + endY);
                            }else {
                                xSP.setCoordinateY(addVirtualModuleControler.getStartY().getValue().toString());
                            }

                            xSP.setSellId(xSP.getXcode()+" || "+xSP.getConnector()+" || "+xSP.getCoordinateX()+" | "+xSP.getCoordinateY());

                            xSP.setModuleMounted("true");
                            xSP.setOrderStatus("virtual");

                            //addDataAndModule(xSP);
                            //modulesTable.refresh();

                            try {
//
                                String[] x;
                                String[] y;
                                double width;
                                double height;

                                x = xSP.getCoordinateX().replace(" ", "").split("-");
                                y = xSP.getCoordinateY().replace(" ", "").split("-");
                                ////System.out.println("-1----------------------------------------------------"+x.length + "----" + y.length);
//                            if (x[0].isEmpty() || y[0].isEmpty()){
//                                point2D = null;
//                            } else {
//                                //System.out.println("-1----------------------------------------------------"+x[0] + "----" + y[0]);
//                                point2D = tableLayout.getCellPositionCoord(x[0], y[0]);
//                            }

                                ////System.out.println("-2-----------------");
                                if (x.length < 2) {
                                    width = 100;
                                } else {
                                    width = (Integer.parseInt(x[1]) - Integer.parseInt(x[0]) + 1) * 100;
                                    //  point2D = tableLayout.getCellPositionCoord(x[0], y[0]);
                                }
                                ////System.out.println("-3-----------------"+y.length );
                                if (y.length < 2) {
                                    height = 100;
                                } else {
                                    int h;
                                    // //System.out.println("-4-----------------");
                                    if (map.get(y[1].toLowerCase().toCharArray()[0]) > map.get(y[0].toLowerCase().toCharArray()[0])) {
                                        h = ((map.get(y[1].toLowerCase().toCharArray()[0])) - map.get(y[0].toLowerCase().toCharArray()[0]));
                                        // //System.out.println("-4a----------------");
                                        //    point2D = tableLayout.getCellPositionCoord(x[0], y[1]);
                                        ////System.out.println("-4a2-----------------");
                                    } else {
                                        h = ((map.get(y[0].toLowerCase().toCharArray()[0])) - map.get(y[1].toLowerCase().toCharArray()[0]));
                                        // //System.out.println("-4b----------------");
                                        //     point2D = tableLayout.getCellPositionCoord(x[0], y[0]);
                                        // //System.out.println("-4b2-----------------");
                                    }

                                    // if(h>2)h=h-1;
                                    // //System.out.println("-5----------------------------------------------------"+h);
                                    height = (h + 1) * 100;


                                }
                                // //System.out.println("-6----------------------------------------------------");


                                if (x[0].isEmpty() || y[0].isEmpty()) {
                                    model.addModule(
                                            xSP.getXcode(),
                                            xSP.getId(),
                                            xSP.getConnector(),
                                            CellType.MODULE,
                                            width,
                                            height,
                                            0,//point2D.getX(),
                                            0,//point2D.getY(),
                                            xSP.getCoordinateX(),
                                            xSP.getCoordinateY(),
                                            /* modulesTable,*/
                                            xSP.getOrderStatus(),
                                            xSP.getModuleMounted(),
                                            /*itemOrder,*/
                                            -1
                                    );
                                    //graph.dragButton(moduleCell);
                                    Module draggingButton = model.getModulesData().get(model.getModulesData().size() - 1);
                                    Platform.runLater(() -> noCordBoxContent.getChildren().add(draggingButton));
//                                    draggingButton.setOnMouseClicked(event1 -> {
//                                        if (event1.getButton().equals(MouseButton.PRIMARY)) {
//                                            orderDetalisEntryController.newValue(xSP.getId());
//                                            sideBar2.setShowSidebar(true);
//                                        }
//                                    });
                                    // draggingButton.setManaged(true);

                                } else {
                                    model.addModule(
                                            xSP.getXcode(),
                                            xSP.getId(),
                                            xSP.getConnector(),
                                            CellType.MODULE,
                                            width,
                                            height,
                                            0,//point2D.getX(),
                                            0,//point2D.getY(),
                                            xSP.getCoordinateX(),
                                            xSP.getCoordinateY(),
                                            /*modulesTable,*/
                                            xSP.getOrderStatus(),
                                            xSP.getModuleMounted(),
                                            /*itemOrder,*/
                                            -1
                                    );


                                    for (Node st50_50 : tableLayout.getGridPane().getChildren()) {
                                        if (st50_50.getClass().getSimpleName().equals("StackPane")) {
                                            StackPane stackPane = (StackPane) st50_50;

                                            String[] cordXList = xSP.getCoordinateX().replace(" ", "").split("-");
                                            String[] cordYList = xSP.getCoordinateY().replace(" ", "").split("-");
                                            String cordX;
                                            String cordY;
                                            if (cordXList.length>1){
                                                cordX = cordXList[0];
                                            }else{
                                                cordX = cordXList[0];
                                            }
                                            if (cordYList.length>1){
                                                cordY = cordYList[1];
                                            }else{
                                                cordY = cordYList[0];
                                            }

                                            if (stackPane.getId().equals(cordX + " - " + cordY)) {
                                                Module draggingButton = model.getModulesData().get(model.getModulesData().size() - 1);
                                                graph.makeDragableModule(draggingButton);
//                                                draggingButton.setOnMouseClicked(event1 -> {
//                                                    if (event1.getButton().equals(MouseButton.PRIMARY)) {
//                                                        orderDetalisEntryController.newValue(xSP.getId());
//                                                        sideBar2.setShowSidebar(true);
//                                                    }
//                                                });
                                                //   //System.out.println(xSP.getCoordinateX()+" - "+xSP.getCoordinateY());
                                                Platform.runLater(() -> {tableLayout.getGridPane().add(draggingButton, GridPane.getColumnIndex(stackPane), GridPane.getRowIndex(stackPane), ((int) width / 100), ((int) height / 100));miniMap.addModule(draggingButton);});
                                                break;
                                            }
                                        }
                                    }
                                }
                                //  //System.out.println("---------------------------");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            xSP = null;

                            virtualModuleStage.close();
                        }
                    });

                    //  model.addModule("", "", CellType.MODULE, 0, 0, 0,0);
                    ////System.out.println(model.getAddedModules().size());

                    //  Platform.runLater(() -> indicator.setProgress(finalIi));
                    /** update(ii,"    Rysowanie Modu³ów Na Leyaucie ");*/
                    Platform.runLater(() -> processname.setText("Rysowanie Modu³ów Na Leyaucie "));
                    // Thread.sleep(2000);

                    double i = 0;

                    //Point2D point2D;
                    String[] x;
                    String[] y;

                    //  while(data.getSystemPintable().size() > i) {//
                    int iSP = 0;
                    double progressSp = (double) 100 / /*data.getSystemPintable()*/systemPintable.size();
                    for (SystemPintable xSP : systemPintable/*data.getSystemPintable()*/) {
                        //addDataAndModule(xSP);
                        //modulesTable.refresh();

                        try {
//

                            double width;
                            double height;

                            x = xSP.getCoordinateX().replace(" ", "").split("-");
                            y = xSP.getCoordinateY().replace(" ", "").split("-");
                            ////System.out.println("-1----------------------------------------------------"+x.length + "----" + y.length);
//                            if (x[0].isEmpty() || y[0].isEmpty()){
//                                point2D = null;
//                            } else {
//                                //System.out.println("-1----------------------------------------------------"+x[0] + "----" + y[0]);
//                                point2D = tableLayout.getCellPositionCoord(x[0], y[0]);
//                            }

                            ////System.out.println("-2-----------------");
                            if (x.length < 2) {
                                width = 100;
                            } else {
                                width = (Integer.parseInt(x[1]) - Integer.parseInt(x[0]) + 1) * 100;
                                //  point2D = tableLayout.getCellPositionCoord(x[0], y[0]);
                            }
                            ////System.out.println("-3-----------------"+y.length );
                            if (y.length < 2) {
                                height = 100;
                            } else {
                                int h;
                                // //System.out.println("-4-----------------");
                                if (map.get(y[1].toLowerCase().toCharArray()[0]) > map.get(y[0].toLowerCase().toCharArray()[0])) {
                                    h = ((map.get(y[1].toLowerCase().toCharArray()[0])) - map.get(y[0].toLowerCase().toCharArray()[0]));
                                    // //System.out.println("-4a----------------");
                                    //    point2D = tableLayout.getCellPositionCoord(x[0], y[1]);
                                    ////System.out.println("-4a2-----------------");
                                } else {
                                    h = ((map.get(y[0].toLowerCase().toCharArray()[0])) - map.get(y[1].toLowerCase().toCharArray()[0]));
                                    // //System.out.println("-4b----------------");
                                    //     point2D = tableLayout.getCellPositionCoord(x[0], y[0]);
                                    // //System.out.println("-4b2-----------------");
                                }

                                // if(h>2)h=h-1;
                                // //System.out.println("-5----------------------------------------------------"+h);
                                height = (h + 1) * 100;


                            }
                            // //System.out.println("-6----------------------------------------------------");


                            if (x[0].isEmpty() || y[0].isEmpty()) {
                                model.addModule(
                                        xSP.getXcode(),
                                        xSP.getId(),
                                        xSP.getConnector(),
                                        CellType.MODULE,
                                        width,
                                        height,
                                        0,//point2D.getX(),
                                        0,//point2D.getY(),
                                        xSP.getCoordinateX(),
                                        xSP.getCoordinateY(),
                                        /*modulesTable,*/
                                        xSP.getOrderStatus(),
                                        xSP.getModuleMounted(),
                                        /*itemOrder,*/
                                        iSP
                                );
                                //graph.dragButton(moduleCell);
                                Module draggingButton = model.getModulesData().get(model.getModulesData().size() - 1);
                                Platform.runLater(() -> noCordBoxContent.getChildren().add(draggingButton));
//                                draggingButton.setOnMouseClicked(event -> {
//                                    if (event.getButton().equals(MouseButton.PRIMARY)) {
//                                        orderDetalisEntryController.newValue(xSP.getId());
//                                        sideBar2.setShowSidebar(true);
//                                    }
//                                });
                                // draggingButton.setManaged(true);

                            } else {
                                model.addModule(
                                        xSP.getXcode(),
                                        xSP.getId(),
                                        xSP.getConnector(),
                                        CellType.MODULE,
                                        width,
                                        height,
                                        0,//point2D.getX(),
                                        0,//point2D.getY(),
                                        xSP.getCoordinateX(),
                                        xSP.getCoordinateY(),
                                        /*modulesTable,*/
                                        xSP.getOrderStatus(),
                                        xSP.getModuleMounted(),
                                        /*itemOrder,*/
                                        iSP
                                );


                              //  for (Node st50_50 : tableLayout.getGridPane().getChildren()) {
                                    tableLayout.getPath50PanesList().forEach(st50_50 -> {
                                    if (st50_50.getClass().getSimpleName().equals("StackPane")) {
                                        StackPane stackPane = (StackPane) st50_50;

                                        String[] cordXList = xSP.getCoordinateX().replace(" ", "").split("-");
                                        String[] cordYList = xSP.getCoordinateY().replace(" ", "").split("-");
                                        String cordX;
                                        String cordY;
                                        if (cordXList.length>1){
                                            cordX = cordXList[0];
                                        }else{
                                            cordX = cordXList[0];
                                        }
                                        if (cordYList.length>1){
                                            cordY = cordYList[1];
                                        }else{
                                            cordY = cordYList[0];
                                        }

                                        if (stackPane.getId().equals(cordX + " - " + cordY)) {
                                            Module draggingButton = model.getModulesData().get(model.getModulesData().size() - 1);
//                                            draggingButton.setOnMouseClicked(event -> {
//                                                if (event.getButton().equals(MouseButton.PRIMARY)) {
//                                                    orderDetalisEntryController.newValue(xSP.getId());
//                                                    sideBar2.setShowSidebar(true);
//                                                }
//                                            });
                                            //   //System.out.println(xSP.getCoordinateX()+" - "+xSP.getCoordinateY());

                                            Platform.runLater(() -> tableLayout.getGridPane().add(draggingButton, GridPane.getColumnIndex(stackPane), GridPane.getRowIndex(stackPane), ((int) draggingButton.getWidthDouble() / 100), ((int) draggingButton.getHeightDouble() / 100)));


                                        }
                                    }
                                    });
                               // }

                            }
                            //  //System.out.println("---------------------------");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        //model.getAddedModules().get(model.getAddedModules().size()).getView().;
                        i = i + progressSp;

                        try {
                            Thread.sleep(threadPause);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        double finalI = i;
                        Platform.runLater(() -> {
                            indicator.setProgress((finalI));
                            mainProgressBar.setProgress(0.4+(finalI/1000));
                            taskbarProgressbar.showCustomProgress(finalI/100, TaskbarProgressbar.Type.NORMAL);
                        });
                        iSP++;
                    }

//                    for (StackPane st50_50 : tableLayout.getPath50PanesList()) {
//                        if (st50_50.getClass().getSimpleName().equals("StackPane")) {
//                        graph.getModel().getModulesData().parallelStream().forEach(module -> {
//
//                            StackPane stackPane = (StackPane) st50_50;
//
//                            String[] cordXList = module.getModuleCordX().replace(" ", "").split("-");
//                            String[] cordYList = module.getModuleCordY().replace(" ", "").split("-");
//                            String cordX;
//                            String cordY;
//                            if (cordXList.length>1){
//                                cordX = cordXList[0];
//                            }else{
//                                cordX = cordXList[0];
//                            }
//                            if (cordYList.length>1){
//                                cordY = cordYList[1];
//                            }else{
//                                cordY = cordYList[0];
//                            }
//
//                            if (stackPane.getId().equals(cordX + " - " + cordY)) {
//                               // System.out.println(cordX + " - " + cordY);
//                                //Module draggingButton = model.getModulesData().get(model.getModulesData().size() - 1);
////                                            draggingButton.setOnMouseClicked(event -> {
////                                                if (event.getButton().equals(MouseButton.PRIMARY)) {
////                                                    orderDetalisEntryController.newValue(xSP.getId());
////                                                    sideBar2.setShowSidebar(true);
////                                                }
////                                            });
//                                //   //System.out.println(xSP.getCoordinateX()+" - "+xSP.getCoordinateY());
//
//                                Platform.runLater(() ->
//                                        tableLayout.getGridPane().add(module, GridPane.getColumnIndex(stackPane), GridPane.getRowIndex(stackPane), ((int) module.getWidthDouble() / 100), ((int) module.getHeightDouble() / 100))
//                                );
//
//
//                            }
//
//                        });
//                        }
//                    }

                    //   for (SystemPintable xSP : systemPintable/*data.getSystemPintable()*/)


                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task_2.setOnSucceeded(workerStateEvent -> {
            // task_3.run();
            // mainProgressBar.setProgress(0.4);
            Thread th1 = new Thread(task_3);
            th1.setName("1");
            th1.setPriority(Thread.MAX_PRIORITY);
            th1.setDaemon(true);
            th1.start();
        });
        Task task_4 = new Task(){

            @Override
            protected Object call() {/**        wyszukiwanie najmniejszych wspó³¿ednych,         Rysowanie Konektorów            ********/
                try{
                    Platform.runLater(() -> processname.setText("Sprawdzanie Wspó³¿êdnych"));
                    final double[] sc4inc1 = {0};
                    double sc4progress1 = (double) 100 / DSI_Model.getSection4_models().size();
                    model.setMinusPionts(new Point2D(0, 0));
                    /** wyszukiwanie najmniejszych wspó³¿ednych*/
                    /**Double.parseDouble(section4_model.getCoordinates1().split("y")[0].replace("x", "")), Double.parseDouble(section4_model.getCoordinates1().split("y")[1])*/

                    //DSI_Model.getSection4_models().parallelStream().forEach(section4_model1 -> {
                    for (Section4_Model section4_model1 : DSI_Model.getSection4_models()) {
                        if (section4_model1.getVariant().contains(Asembly.get()) || Asembly.get().equals("_Full")) {
                            double xx;
                            double yy;

                            double xx2;
                            double yy2;
                            try {
                                xx = Double.parseDouble(section4_model1.getCoordinates1().split("y")[0].replace("x", ""));
                            }catch (Exception e){
                                xx = Double.parseDouble(section4_model1.getCoordinates1().split("Y")[0].replace("X", ""));
                            }

                            try{
                                xx2 = Double.parseDouble(section4_model1.getCoordinates2().split("y")[0].replace("x", ""));
                            }catch (Exception e){
                                xx2 = Double.parseDouble(section4_model1.getCoordinates2().split("Y")[0].replace("X", ""));
                            }




                            if (xx>xx2)
                                xx = xx2;
                            if (xx < 0)
                                if (model.getMinusPionts().getX() > xx) {
                                    model.setMinusPionts(new Point2D(xx, model.getMinusPionts().getY()));
                                }

                            try {
                                yy = Double.parseDouble(section4_model1.getCoordinates1().split("y")[1].split("z")[0]);
                            }catch (Exception e){
                                yy = Double.parseDouble(section4_model1.getCoordinates1().split("Y")[1].split("Z")[0]);
                            }
                            try {
                                yy2 = Double.parseDouble(section4_model1.getCoordinates2().split("y")[1].split("z")[0]);
                            }catch (Exception e){
                                yy2 = Double.parseDouble(section4_model1.getCoordinates2().split("Y")[1].split("Z")[0]);
                            }

                            if (yy > yy2)
                                yy = yy2;
                            if (yy < 0)
                                if (model.getMinusPionts().getY() > yy) {
                                    model.setMinusPionts(new Point2D(model.getMinusPionts().getX(), yy));
                                }
                            //System.out.println(xx+" - "+yy);
                            sc4inc1[0] = sc4inc1[0] + sc4progress1;
                            try {
                                Thread.sleep(threadPause);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            double finalSc4inc = sc4inc1[0];
                            Platform.runLater(() -> {
                                indicator.setProgress((finalSc4inc));
                                mainProgressBar.setProgress(0.5+(finalSc4inc/1000));
                                taskbarProgressbar.showCustomProgress(finalSc4inc/100, TaskbarProgressbar.Type.NORMAL);
                            });
                        }

                    }

                    // });
                    //System.out.println(model.getMinusPionts());
                    Platform.runLater(() -> processname.setText("Rysowanie Konektorów"));
                    final double[] sc4inc = {0};
                    double sc4progress = (double) 100 / DSI_Model.getSection4_models().size();
                     DSI_Model.getSection4_models().forEach(section4_model -> {
                   // for (Section4_Model section4_model : DSI_Model.getSection4_models()) {
                        {
                            final boolean[] firstPointAnother = {true};
                            final boolean[] secondPointAnother = {true};
                            // //System.out.println("Asembly: " + selection);
                            String[] variants;
                            variants = section4_model.getVariant().replace(" ", "").split("/");
                            if (variants.length < 2) {
                                variants = section4_model.getVariant().replace(" ", "").split("\\|");
                            }

                            ////System.out.println("Model: "+variants"\n");
                           /** for (String variableSplited : variants){*/
                            String[] finalVariants2 = variants;
                            Arrays.stream(variants).forEach(variableSplited -> {
                                // //System.out.println(Asembly.get()+"\n "+variableSplited+"\n ");
                                if (Asembly.get().contains(variableSplited) || Asembly.get().equals("_Full")) {


//                            model.addCell(section4_model.getFirst_Point(), CellType. CONNECTOR, Double.parseDouble(section4_model.getCoordinates1().split("y")[0].replace("x","")),Double.parseDouble(section4_model.getCoordinates1().split("y")[1]));
//                            model.addCell(section4_model.getSecond_Point(), CellType. CONNECTOR, Double.parseDouble(section4_model.getCoordinates2().split("y")[0].replace("x","")),Double.parseDouble(section4_model.getCoordinates2().split("y")[1]));
                                    try {
                                        //  //System.out.println(section4_model.getFirst_Point() + ",   " + Double.parseDouble(section4_model.getCoordinates1().split("y")[0].replace("x", "")) + "     ," + Double.parseDouble(section4_model.getCoordinates1().split("y")[1]));

                                        DSI_Model.getSection6_models().forEach(section6_model_DrwConn -> {
                                        //for (Section6_Model section6_model_DrwConn : DSI_Model.getSection6_models()) {
                                            final boolean[] detectedCell = new boolean[1];
                                            if (section4_model.getFirst_Point().equals(section6_model_DrwConn.getRef_BSJ())) {
                                                if (section6_model_DrwConn.getComp_Type().equals("CONNECTOR")) {
                                                    detectedCell[0] = false;
                                                    /**for (Cell cell : model.getAddedCells()) {*/
                                                    String[] finalVariants = finalVariants2;
                                                    model.getAddedCells().forEach(cell -> {
                                                        if (cell.getCellId().equals(section6_model_DrwConn.getRef_BSJ())) {
                                                            Cell pasiveCell = new Cell(section6_model_DrwConn.getRef_BSJ(), CellType.COMPONENT.name(), section6_model_DrwConn.getName(), section6_model_DrwConn.getPart_Nb(), "", "0", Arrays.asList(finalVariants.clone()));
                                                            pasiveCell.getPositionXProperty().bind(cell.getPositionXProperty());
                                                            pasiveCell.getPositionYProperty().bind(cell.getPositionYProperty());

                                                            boolean exist = false;
                                                            for (Cell cellPasiveCompare : model.getPasivesData()) {
                                                                if (cellPasiveCompare.getCellId().equals(pasiveCell.getCellId()) && cellPasiveCompare.getXcode().equals(pasiveCell.getXcode()) && cellPasiveCompare.getCellType().equals(pasiveCell.getCellType()))
                                                                    exist = true;
                                                            }
                                                            if (!exist) {
                                                                model.getPasivesData().add(pasiveCell);
                                                                cell.addPasives(section6_model_DrwConn.getName(), section6_model_DrwConn.getPart_Nb());
                                                                cell.addPasives(pasiveCell);
                                                            }

                                                            detectedCell[0] = true;
                                                        }
                                                    });
                                                    /**}*/
                                                    if (section6_model_DrwConn.getName().equals("")) {
                                                        section6_model_DrwConn.setName(section6_model_DrwConn.getRef_BSJ());
                                                    }
                                                    if (!detectedCell[0])
                                                        model.addCell(section4_model.getFirst_Point(),
                                                                section6_model_DrwConn.getName(),
                                                                section6_model_DrwConn.getPart_Nb(),
                                                                CellType.CONNECTORSMALL,
                                                                Double.parseDouble(section4_model.getCoordinates1().toLowerCase().split("y")[0].replace("x", "")),
                                                                Double.parseDouble(section4_model.getCoordinates1().toLowerCase().split("y")[1].split("z")[0]),
                                                                section6_model_DrwConn.getName(),
                                                                section6_model_DrwConn.getPart_Nb(),
                                                                "",
                                                                section6_model_DrwConn.getPins(),
                                                                Arrays.asList(finalVariants2.clone())
                                                        );
                                                } else if (section6_model_DrwConn.getComp_Type().equals("SPLICE")) {
                                                    detectedCell[0] = false;
                                                    /**for (Cell cell : model.getAddedCells()) {*/
                                                    String[] finalVariants = finalVariants2;
                                                    model.getAddedCells().forEach(cell -> {
                                                        if (cell.getCellId().equals(section6_model_DrwConn.getRef_BSJ())) {
                                                            Cell pasiveCell = new Cell(section6_model_DrwConn.getRef_BSJ(), CellType.COMPONENT.name(), section6_model_DrwConn.getName(), section6_model_DrwConn.getPart_Nb(), "", "0", Arrays.asList(finalVariants.clone()));
                                                            pasiveCell.getPositionXProperty().bind(cell.getPositionXProperty());
                                                            pasiveCell.getPositionYProperty().bind(cell.getPositionYProperty());

                                                            boolean exist = false;
                                                            for (Cell cellPasiveCompare : model.getPasivesData()) {
                                                                if (cellPasiveCompare.getCellId().equals(pasiveCell.getCellId()) && cellPasiveCompare.getXcode().equals(pasiveCell.getXcode()) && cellPasiveCompare.getCellType().equals(pasiveCell.getCellType()))
                                                                    exist = true;
                                                            }
                                                            if (!exist) {
                                                                model.getPasivesData().add(pasiveCell);
                                                                cell.addPasives(section6_model_DrwConn.getName(), section6_model_DrwConn.getPart_Nb());
                                                                cell.addPasives(pasiveCell);
                                                            }

                                                            detectedCell[0] = true;
                                                        }
                                                    });
                                                    /**}*/
                                                    if (section6_model_DrwConn.getName().equals("")) {
                                                        section6_model_DrwConn.setName(section6_model_DrwConn.getRef_BSJ());
                                                    }
                                                    if (!detectedCell[0])
                                                        model.addCell(section6_model_DrwConn.getRef_BSJ(),
                                                                section6_model_DrwConn.getName(),
                                                                section6_model_DrwConn.getPart_Nb(),
                                                                CellType.SPLICECELL,
                                                                Double.parseDouble(section4_model.getCoordinates2().toLowerCase().split("y")[0].replace("x", "")),
                                                                Double.parseDouble(section4_model.getCoordinates2().toLowerCase().split("y")[1].split("z")[0]),
                                                                section6_model_DrwConn.getName(),
                                                                section6_model_DrwConn.getPart_Nb(),
                                                                "",
                                                                "0",
                                                                Arrays.asList(finalVariants2.clone())
                                                        );
                                                } else if (section6_model_DrwConn.getComp_Type().equals("SEAL") || section6_model_DrwConn.getComp_Type().equals("TERM") || section6_model_DrwConn.getComp_Type().equals("PLUG")) {
                                                    /**for (Cell cell : model.getAddedCells()) {*/
                                                    String[] finalVariants1 = finalVariants2;
                                                    model.getAddedCells().forEach(cell -> {
                                                        if (cell.getCellId().equals(section6_model_DrwConn.getRef_BSJ())) {
                                                            Cell pasiveCell = new Cell("Cav: " + section6_model_DrwConn.getCavity() + " / " + section6_model_DrwConn.getComp_Type(), CellType.COMPONENT.name(), section6_model_DrwConn.getName() + " " + section6_model_DrwConn.getCavity(), section6_model_DrwConn.getPart_Nb(), "", section6_model_DrwConn.getComp_Type(), Arrays.asList(finalVariants1.clone()));
                                                            pasiveCell.getPositionXProperty().bind(cell.getPositionXProperty());
                                                            pasiveCell.getPositionYProperty().bind(cell.getPositionYProperty());
                                                            cell.addPasives(pasiveCell);
                                                        }
                                                    });
                                                    /**}*/
                                                } else {
                                                    if (!section6_model_DrwConn.getRef_BSJ().equals(section6_model_DrwConn.getName())) {
                                                        detectedCell[0] = false;
                                                        /**for (Cell cell : model.getAddedCells()) {*/
                                                        String[] finalVariants = finalVariants2;
                                                        model.getAddedCells().forEach(cell -> {
                                                            if (cell.getCellId().equals(section6_model_DrwConn.getRef_BSJ())) {
                                                                Cell pasiveCell = new Cell(section6_model_DrwConn.getRef_BSJ(), CellType.COMPONENT.name(), section6_model_DrwConn.getName(), section6_model_DrwConn.getPart_Nb(), "", "0", Arrays.asList(finalVariants.clone()));
                                                                pasiveCell.getPositionXProperty().bind(cell.getPositionXProperty());
                                                                pasiveCell.getPositionYProperty().bind(cell.getPositionYProperty());

                                                                boolean exist = false;
                                                                for (Cell cellPasiveCompare : model.getPasivesData()) {
                                                                    if (cellPasiveCompare.getCellId().equals(pasiveCell.getCellId()) && cellPasiveCompare.getXcode().equals(pasiveCell.getXcode()) && cellPasiveCompare.getCellType().equals(pasiveCell.getCellType()))
                                                                        exist = true;
                                                                }
                                                                if (!exist) {
                                                                    model.getPasivesData().add(pasiveCell);
                                                                    cell.addPasives(section6_model_DrwConn.getName(), section6_model_DrwConn.getPart_Nb());
                                                                    cell.addPasives(pasiveCell);
                                                                }

                                                                detectedCell[0] = true;
                                                            }
                                                        });
                                                        /**}*/
                                                        if (section6_model_DrwConn.getName().equals("")) {
                                                            section6_model_DrwConn.setName(section6_model_DrwConn.getRef_BSJ());
                                                        }
                                                        if (!detectedCell[0])
                                                            model.addCell(section6_model_DrwConn.getRef_BSJ(),
                                                                    section6_model_DrwConn.getName(),
                                                                    section6_model_DrwConn.getPart_Nb(),
                                                                    CellType.COMPONENT,
                                                                    Double.parseDouble(section4_model.getCoordinates1().toLowerCase().split("y")[0].replace("x", "")),
                                                                    Double.parseDouble(section4_model.getCoordinates1().toLowerCase().split("y")[1].split("z")[0]),
                                                                    section6_model_DrwConn.getName(),
                                                                    section6_model_DrwConn.getPart_Nb(),
                                                                    "",
                                                                    "0",
                                                                    Arrays.asList(finalVariants2.clone())
                                                            );
                                                    }
                                                }
                                                firstPointAnother[0] = false;
                                            } else {
                                                /**for (Cell cell : model.getAddedCells()) {*/
                                                model.getAddedCells().forEach(cell -> {
                                                    if (cell.getCellId().equals(section4_model.getFirst_Point())) {
                                                        firstPointAnother[0] = false;
                                                        //break;
                                                    }
                                                });
                                                /**}*/
                                            }
                                            /***second point*/
                                            if (section4_model.getSecond_Point().equals(section6_model_DrwConn.getRef_BSJ())) {
                                                if (section6_model_DrwConn.getComp_Type().equals("CONNECTOR")) {
                                                    detectedCell[0] = false;
                                                    /**for (Cell cell : model.getAddedCells()) {*/
                                                    String[] finalVariants = finalVariants2;
                                                    model.getAddedCells().forEach(cell -> {
                                                        if (cell.getCellId().equals(section6_model_DrwConn.getRef_BSJ())) {
                                                            Cell pasiveCell = new Cell(section6_model_DrwConn.getRef_BSJ(), CellType.COMPONENT.name(), section6_model_DrwConn.getName(), section6_model_DrwConn.getPart_Nb(), "", "0", Arrays.asList(finalVariants.clone()));
                                                            pasiveCell.getPositionXProperty().bind(cell.getPositionXProperty());
                                                            pasiveCell.getPositionYProperty().bind(cell.getPositionYProperty());

                                                            boolean exist = false;
                                                            for (Cell cellPasiveCompare : model.getPasivesData()) {
                                                                if (cellPasiveCompare.getCellId().equals(pasiveCell.getCellId()) && cellPasiveCompare.getXcode().equals(pasiveCell.getXcode()) && cellPasiveCompare.getCellType().equals(pasiveCell.getCellType()))
                                                                    exist = true;
                                                            }
                                                            if (!exist) {
                                                                model.getPasivesData().add(pasiveCell);
                                                                cell.addPasives(section6_model_DrwConn.getName(), section6_model_DrwConn.getPart_Nb());
                                                                cell.addPasives(pasiveCell);
                                                            }

                                                            detectedCell[0] = true;
                                                        }
                                                    });
                                                    /**}*/
                                                    if (section6_model_DrwConn.getName().equals("")) {
                                                        section6_model_DrwConn.setName(section6_model_DrwConn.getRef_BSJ());
                                                    }
                                                    if (!detectedCell[0])
                                                        model.addCell(section4_model.getSecond_Point(),
                                                                section6_model_DrwConn.getName(),
                                                                section6_model_DrwConn.getPart_Nb(),
                                                                CellType.CONNECTORSMALL,
                                                                Double.parseDouble(section4_model.getCoordinates2().toLowerCase().split("y")[0].replace("x", "")),
                                                                Double.parseDouble(section4_model.getCoordinates2().toLowerCase().split("y")[1].split("z")[0]),
                                                                section6_model_DrwConn.getName(),
                                                                section6_model_DrwConn.getPart_Nb(),
                                                                "",
                                                                section6_model_DrwConn.getPins(),
                                                                Arrays.asList(finalVariants2.clone())
                                                        );
                                                } else if (section6_model_DrwConn.getComp_Type().equals("SPLICE")) {
                                                    detectedCell[0] = false;
                                                    /**for (Cell cell : model.getAddedCells()) {*/
                                                    String[] finalVariants = finalVariants2;
                                                    model.getAddedCells().forEach(cell -> {
                                                        if (cell.getCellId().equals(section6_model_DrwConn.getRef_BSJ())) {
                                                            Cell pasiveCell = new Cell(section6_model_DrwConn.getRef_BSJ(), CellType.COMPONENT.name(), section6_model_DrwConn.getName(), section6_model_DrwConn.getPart_Nb(), "", "0", Arrays.asList(finalVariants.clone()));
                                                            pasiveCell.getPositionXProperty().bind(cell.getPositionXProperty());
                                                            pasiveCell.getPositionYProperty().bind(cell.getPositionYProperty());

                                                            boolean exist = false;
                                                            for (Cell cellPasiveCompare : model.getPasivesData()) {
                                                                if (cellPasiveCompare.getCellId().equals(pasiveCell.getCellId()) && cellPasiveCompare.getXcode().equals(pasiveCell.getXcode()) && cellPasiveCompare.getCellType().equals(pasiveCell.getCellType()))
                                                                    exist = true;
                                                            }
                                                            if (!exist) {
                                                                model.getPasivesData().add(pasiveCell);
                                                                cell.addPasives(section6_model_DrwConn.getName(), section6_model_DrwConn.getPart_Nb());
                                                                cell.addPasives(pasiveCell);
                                                            }

                                                            detectedCell[0] = true;
                                                        }
                                                    });
                                                    /**}*/
                                                    if (section6_model_DrwConn.getName().equals("")) {
                                                        section6_model_DrwConn.setName(section6_model_DrwConn.getRef_BSJ());
                                                    }
                                                    if (!detectedCell[0])
                                                        model.addCell(section6_model_DrwConn.getRef_BSJ(), section6_model_DrwConn.getName(),
                                                                section6_model_DrwConn.getPart_Nb(),
                                                                CellType.SPLICECELL,
                                                                Double.parseDouble(section4_model.getCoordinates2().toLowerCase().split("y")[0].replace("x", "")),
                                                                Double.parseDouble(section4_model.getCoordinates2().toLowerCase().split("y")[1].split("z")[0]),
                                                                section6_model_DrwConn.getName(),
                                                                section6_model_DrwConn.getPart_Nb(),
                                                                "",
                                                                "0",
                                                                Arrays.asList(finalVariants2.clone())
                                                        );
                                                } else if (section6_model_DrwConn.getComp_Type().equals("SEAL") || section6_model_DrwConn.getComp_Type().equals("TERM") || section6_model_DrwConn.getComp_Type().equals("PLUG")) {
                                                    /**for (Cell cell : model.getAddedCells()) {*/
                                                    String[] finalVariants = finalVariants2;
                                                    model.getAddedCells().forEach(cell -> {
                                                        if (cell.getCellId().equals(section6_model_DrwConn.getRef_BSJ())) {
                                                            Cell pasiveCell = new Cell("Cav: " + section6_model_DrwConn.getCavity() + " / " + section6_model_DrwConn.getComp_Type(), CellType.COMPONENT.name(), section6_model_DrwConn.getName() + " " + section6_model_DrwConn.getCavity(), section6_model_DrwConn.getPart_Nb(), "", section6_model_DrwConn.getComp_Type(), Arrays.asList(finalVariants.clone()));
                                                            pasiveCell.getPositionXProperty().bind(cell.getPositionXProperty());
                                                            pasiveCell.getPositionYProperty().bind(cell.getPositionYProperty());
                                                            cell.addPasives(pasiveCell);
                                                        }
                                                    });
                                                    /**}*/
                                                } else {
                                                    if (!section6_model_DrwConn.getRef_BSJ().equals(section6_model_DrwConn.getName())) {
                                                        detectedCell[0] = false;
                                                        /**for (Cell cell : model.getAddedCells()) {*/
                                                        String[] finalVariants = finalVariants2;
                                                        model.getAddedCells().forEach(cell -> {
                                                            if (cell.getCellId().equals(section6_model_DrwConn.getRef_BSJ())) {
                                                                Cell pasiveCell = new Cell(section6_model_DrwConn.getRef_BSJ(), CellType.COMPONENT.name(), section6_model_DrwConn.getName(), section6_model_DrwConn.getPart_Nb(), "", "0", Arrays.asList(finalVariants.clone()));
                                                                pasiveCell.getPositionXProperty().bind(cell.getPositionXProperty());
                                                                pasiveCell.getPositionYProperty().bind(cell.getPositionYProperty());

                                                                boolean exist = false;
                                                                for (Cell cellPasiveCompare : model.getPasivesData()) {
                                                                    if (cellPasiveCompare.getCellId().equals(pasiveCell.getCellId()) && cellPasiveCompare.getXcode().equals(pasiveCell.getXcode()) && cellPasiveCompare.getCellType().equals(pasiveCell.getCellType()))
                                                                        exist = true;
                                                                }
                                                                if (!exist) {
                                                                    model.getPasivesData().add(pasiveCell);
                                                                    cell.addPasives(section6_model_DrwConn.getName(), section6_model_DrwConn.getPart_Nb());
                                                                    cell.addPasives(pasiveCell);
                                                                }

                                                                detectedCell[0] = true;
                                                            }
                                                        });
                                                        /**}*/
                                                        if (section6_model_DrwConn.getName().equals("")) {
                                                            section6_model_DrwConn.setName(section6_model_DrwConn.getRef_BSJ());
                                                        }
                                                        if (!detectedCell[0])
                                                            model.addCell(section6_model_DrwConn.getRef_BSJ(),
                                                                    section6_model_DrwConn.getName(),
                                                                    section6_model_DrwConn.getPart_Nb(),
                                                                    CellType.COMPONENT,
                                                                    Double.parseDouble(section4_model.getCoordinates2().toLowerCase().split("y")[0].replace("x", "")),
                                                                    Double.parseDouble(section4_model.getCoordinates2().toLowerCase().split("y")[1].split("z")[0]),
                                                                    section6_model_DrwConn.getName(),
                                                                    section6_model_DrwConn.getPart_Nb(),
                                                                    "",
                                                                    "0",
                                                                    Arrays.asList(finalVariants2.clone())
                                                            );
                                                    }
                                                }
                                                secondPointAnother[0] = false;
                                            } else {
                                                /**for (Cell cell : model.getAddedCells()) {*/
                                                model.getAddedCells().forEach(cell -> {
                                                    if (cell.getCellId().equals(section4_model.getSecond_Point())) {
                                                        secondPointAnother[0] = false;
                                                        //break;
                                                    }
                                                });
                                                /**}*/
                                            }
                                            // }
                                        //}
                                    });

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    /** dodawanie konektorów które nie wystepuj¹ w sekcji 6*/
                                    if (firstPointAnother[0]) {
                                        model.addCell(section4_model.getFirst_Point(),
                                                section4_model.getFirst_Point(),
                                                section4_model.getVariable(),
                                                CellType.ANOTHERCELL,
                                                Double.parseDouble(section4_model.getCoordinates1().toLowerCase().split("y")[0].replace("x", "")),
                                                Double.parseDouble(section4_model.getCoordinates1().toLowerCase().split("y")[1].split("z")[0]),
                                                section4_model.getFirst_Point(),
                                                "",
                                                "",
                                                "",
                                                Arrays.asList(finalVariants2.clone())
                                        );
                                    }
                                    if (secondPointAnother[0]) {
                                        model.addCell(section4_model.getSecond_Point(),
                                                section4_model.getSecond_Point(),
                                                section4_model.getVariable(),
                                                CellType.ANOTHERCELL,
                                                Double.parseDouble(section4_model.getCoordinates2().toLowerCase().split("y")[0].replace("x", "")),
                                                Double.parseDouble(section4_model.getCoordinates2().toLowerCase().split("y")[1].split("z")[0]),
                                                section4_model.getFirst_Point(),
                                                "",
                                                "",
                                                "",
                                                Arrays.asList(finalVariants2.clone())
                                        );
                                    }
                                    /*************************************************************/
                                }
                            });
                            /**}*/


                            sc4inc[0] = sc4inc[0] + sc4progress;
                            try {
                                Thread.sleep(threadPause);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            double finalSc4inc = sc4inc[0];
                            Platform.runLater(() -> {
                                indicator.setProgress((finalSc4inc));
                                mainProgressBar.setProgress(0.6 + (finalSc4inc / 1000));
                                taskbarProgressbar.showCustomProgress(finalSc4inc/100, TaskbarProgressbar.Type.NORMAL);
                            });
                        }
                   // }
                     });
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task_3.setOnSucceeded(workerStateEvent -> {
            // task_4.run();
            //mainProgressBar.setProgress(0.5);
            Thread th1 = new Thread(task_4);
            th1.setName("1");
            th1.setPriority(Thread.MAX_PRIORITY);
            th1.setDaemon(true);
            th1.start();
        });
        Task task_5 = new Task(){
            @Override
            protected Object call() {/***        Wczytywanie Po³±czeñ    ***/
                try {
                    Platform.runLater(() -> processname.setText("Wczytywanie Po³±czeñ"));
                    double sc4inc = 0;
                    int lp = 0;
                    double sc4progress = (double) 100 / DSI_Model.getSection5_models().size();
                    for (Section5_Model section5_model : DSI_Model.getSection5_models()) {
                        String[] variants;
                        boolean variableSplitedIsEmpty = false;
                        final boolean[] existed = {false};

/**volvo*/
                        variants = section5_model.getAssembly().replace(" ", "").split("/");
                        if (variants.length < 2) {
                            variants = section5_model.getAssembly().replace(" ", "").split("\\|");
                        }


                        graph.getModel().getWiresData().parallelStream().forEach(wires -> {

                            if (wires.getName().equals(section5_model.getWire_Name()) //&&
                                // wires.getFromXcode().equals(section5_model.getStart_Node()) &&
                                // wires.getFromPin().equals(section5_model.getCav1()) &&

                                //  wires.getToXcode().equals(section5_model.getEnd_Node()) &&
                                //  wires.getToPin().equals(section5_model.getCav2())
                            ) {
                                // System.out.println(wires.getName());
                                System.out.println(wires.getName() + " ____> " + section5_model.getWire_Name());
                                existed[0] = true;
                            }
                        });

                        if (!existed[0]){
                            for (String variableSplited : variants) {
                                //  //System.out.println("Model: "+variableSplited+"\n");
                                if (!variableSplited.isEmpty()) {
                                    if (Asembly.get().contains(variableSplited) || Asembly.get().equals("_Full")) {
                                        graph.getModel().addToWiresData(new Wires(section5_model.getWire_Name(), section5_model.getColor(), section5_model.getStart_Node(), section5_model.getCav1(), section5_model.getEnd_Node(), section5_model.getCav2(), section5_model.getLenght(), section5_model.getArea(), String.valueOf(lp), section5_model.getPart_Number(), Arrays.asList(variants)));
                                        lp++;
                                    }
                                } else {
                                    variableSplitedIsEmpty = true;
                                    break;
                                }

                                sc4inc = sc4inc + sc4progress;
                                double finalSc4inc = sc4inc;
                                try {
                                    Thread.sleep(threadPause);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Platform.runLater(() -> {
                                    indicator.setProgress((finalSc4inc));
                                    mainProgressBar.setProgress(0.7 + (finalSc4inc / 1000));
                                    taskbarProgressbar.showCustomProgress(finalSc4inc/100, TaskbarProgressbar.Type.NORMAL);
                                });
                            }

/**daf*/
                            if (variableSplitedIsEmpty) {
                                variants = section5_model.getVariant_().replace(" ", "").split("/");
                                if (variants.length < 2) {
                                    variants = section5_model.getVariant_().replace(" ", "").split("\\|");
                                }

                                for (String variableSplited : variants) {
                                    //  //System.out.println("Model: "+variableSplited+"\n");
                                    //  if (!variableSplited.isEmpty()) {
                                    if (Asembly.get().contains(variableSplited) || Asembly.get().equals("_Full")) {
                                        Wires wire = new Wires(section5_model.getWire_Name(), section5_model.getColor(), section5_model.getStart_Node(), section5_model.getCav1(), section5_model.getEnd_Node(), section5_model.getCav2(), section5_model.getLenght(), section5_model.getArea(), String.valueOf(lp), section5_model.getPart_Number(), Arrays.asList(variants));

                                        graph.getModel().addToWiresData(wire);//     variableSplitedIsEmpty = true;


                                        //      break;
                                        //     }
                                    }

                                    sc4inc = sc4inc + sc4progress;

                                    try {
                                        Thread.sleep(threadPause);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    double finalSc4inc1 = sc4inc;
                                    Platform.runLater(() -> {
                                        indicator.setProgress((finalSc4inc1));
                                        mainProgressBar.setProgress(0.8 + (finalSc4inc1 / 1000));
                                        taskbarProgressbar.showCustomProgress(finalSc4inc1/100, TaskbarProgressbar.Type.NORMAL);
                                    });
                                }
                            }
                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;
            }
        };
        task_4.setOnSucceeded(workerStateEvent -> {
            // task_5.run();
            //mainProgressBar.setProgress(0.6);
            System.out.println(duration);
            Thread th1 = new Thread(task_5);
            th1.setName("1");
            th1.setPriority(Thread.MAX_PRIORITY);
            th1.setDaemon(true);
            th1.start();
        });
        Task task_6 = new Task(){
            @Override
            protected Object call() {/***         Rysowanie Po³±czeñ     ****/
                try {
                    Platform.runLater(() -> processname.setText("Rysowanie Po³±czeñ"));
                    double sc4inc = 0;
                    double sc4progress = (double) 100 / DSI_Model.getSection4_models().size();
                    for (Section4_Model section4_model : DSI_Model.getSection4_models()) {
                        String[] variants;
                        variants = section4_model.getVariant().replace(" ","").split("/");
                        if (variants.length<2){
                            variants = section4_model.getVariant().replace(" ","").split("\\|");
                        }

                        ////System.out.println("Model: "+variants"\n");
                        for (String variableSplited : variants) {
                            try {
//                                if (section4_model.getVariant().contains(Asembly.get()) || Asembly.get().equals("_Full")) {
                                if (Asembly.get().contains(variableSplited) || Asembly.get().equals("_Full")) {

                                    /**  //System.out.println("Rysowanie po³±czeñ"+ Asembly.get()+ "  " +section4_model.getVariant());*/

                                    boolean detectedCell = false;
                                    for (Edge edge : model.getAddedEdges()) {
                                        if (edge.getSource().getCellId().equals(section4_model.getFirst_Point()) && edge.getTarget().getCellId().equals(section4_model.getSecond_Point())) {
                                            detectedCell = true;
                                            break;
                                        }
                                    }
                                    if (!detectedCell) {
                                        boolean branchInsulated = false;
                                        for (Section7_Model section7_model : DSI_Model.getSection7_models()) {
                                            /***        branch insulated               ****/
                                            if ((section4_model.getFirst_Point().equals(section7_model.getStart()) && section4_model.getSecond_Point().equals(section7_model.getEnd())) || (section4_model.getFirst_Point().equals(section7_model.getEnd()) && section4_model.getSecond_Point().equals(section7_model.getStart()))) {
//                                            model.addEdge(section4_model.getFirst_Point(), section4_model.getSecond_Point(), Double.parseDouble(section4_model.getLenght()), 5, Color.TEAL);
                                                branchInsulated = true;
                                                break;
                                            }/*else{
                                           // model.addEdge(section4_model.getFirst_Point(), section4_model.getSecond_Point(), Double.parseDouble(section4_model.getLenght()), 2, Color.WHITE);
                                        }*/
                                        }
                                        if (!branchInsulated){
                                            ////System.out.println("!branch insulated ");
                                            model.addEdge(section4_model.getFirst_Point(), section4_model.getSecond_Point(), Double.parseDouble(section4_model.getLenght()), 2, Color.WHITE);
                                        }else{
                                            ////System.out.println("branch insulated ");
                                            model.addEdge(section4_model.getFirst_Point(), section4_model.getSecond_Point(), Double.parseDouble(section4_model.getLenght()), 4, Color.YELLOWGREEN);
                                        }

                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            sc4inc = sc4inc + sc4progress;

                            try {
                                Thread.sleep(threadPause);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            double finalSc4inc = sc4inc;
                            Platform.runLater(() -> {
                                indicator.setProgress((finalSc4inc));
                                mainProgressBar.setProgress(0.9+(finalSc4inc/1000));
                                taskbarProgressbar.showCustomProgress(finalSc4inc/100, TaskbarProgressbar.Type.NORMAL);
                            });
                        }
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }




                return null;
            }
        };
        task_5.setOnSucceeded(workerStateEvent -> {
            // task_6.run();
            //mainProgressBar.setProgress(0.7);
            Thread th1 = new Thread(task_6);
            th1.setName("1");
            th1.setPriority(Thread.MAX_PRIORITY);
            th1.setDaemon(true);
            th1.start();
        });
        Task task_finall = new Task(){


            @Override
            protected Object call() {
                try{

                    /*** tasmowania *//**volvo*/
                   /* for (Section7_Model section7_model : DSI_Model.getSection7_models()) {
                        String[] variants;
                        boolean variableSplitedIsEmpty = false;


                        variants = section7_model.getAssembly().replace(" ", "").split("/");
                        if (variants.length < 2) {
                            variants = section7_model.getAssembly().replace(" ", "").split("\\|");
                        }


                        for (String variableSplited : variants) {
                            //  //System.out.println("Model: "+variableSplited+"\n");
                            if (!variableSplited.isEmpty()) {
                                if (Asembly.get().contains(variableSplited) || Asembly.get().equals("_Full")) {
                                    //Cell(String cellId, String cellType, String Xcode, String CustomerCode, String GIMCode, String Pins)
                                    variableSplitedIsEmpty = true;
                                   }
                            }
                        }
                        if (variableSplitedIsEmpty)
                            graph.getModel().getPasivesData().add(new Cell(section7_model.getName(),CellType.LABEL.name(),"Tasmowanie od " + section7_model.getStart() + " do " +section7_model.getEnd(),section7_model.getPart_Nb(),"","0"));


                    }*/
/**
 /*** pasives bez celi i polaczen*//**volvo*/
                   /* for (Section6_Model section6_model : DSI_Model.getSection6_models()) {
                        String[] variants;
                        boolean variableSplitedIsEmpty = false;
                        boolean existed = false;


                        variants = section6_model.getAssembly().replace(" ", "").split("/");
                        if (variants.length < 2) {
                            variants = section6_model.getAssembly().replace(" ", "").split("\\|");
                        }


                        for (String variableSplited : variants) {
                            //  //System.out.println("Model: "+variableSplited+"\n");
                            if (!variableSplited.isEmpty()) {
                                if (Asembly.get().contains(variableSplited) || Asembly.get().equals("_Full")) {
                                    //Cell(String cellId, String cellType, String Xcode, String CustomerCode, String GIMCode, String Pins)
                                    variableSplitedIsEmpty = true;
                                }
                            }
                        }
                        for (Cell pasive : graph.getModel().getPasivesData()){
                            if(pasive.getCellId().equals(section6_model.getRef_BSJ()) && pasive.getCustomerCode().equals(section6_model.getPart_Nb())){
                                existed = true;
                            }
                        }
                        if (variableSplitedIsEmpty && !existed)
                        graph.getModel().getPasivesData().add(new Cell(section6_model.getRef_BSJ(),CellType.LABEL.name(),section6_model.getName(),section6_model.getPart_Nb(),section6_model.getComp_Type(),"0"));


                    }*/

//                    Text info = new Text("Tester: "+TesterName);
//                    info.setFill(Color.DARKGRAY);
//                    info.setX(TesterTable.getLayoutX());
//                    info.setY(TesterTable.getLayoutY()+TesterTable.getHeight()+90);
//                    info.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
//                    Platform.runLater(() -> {
//                        graph.getCellLayer().getChildren().add(info);
//                    });

                    /**bind testertable visablity**/
                    tableLayout.getGridPane().visibleProperty().bind(layoutVisableProperty);


                    edgeProgressPbr.progressProperty().addListener((observableValue, number, t1) -> {
                        if (number.doubleValue()>.8){
                            edgeProgressPbr.setStyle("-fx-accent: green;");
                        }else if (number.doubleValue()>=.6 && number.doubleValue()<=.8){
                            edgeProgressPbr.setStyle("-fx-accent: yellow;");
                        }else if (number.doubleValue()>=.3 && number.doubleValue()<.6){
                            edgeProgressPbr.setStyle("-fx-accent: orange;");
                        } else if (number.doubleValue()<.3){
                            edgeProgressPbr.setStyle("-fx-accent: red;");
                        }

                    });

                    noCordBox.relocate(0,tableLayout.getGridPane().getHeight()+110);
                    Platform.runLater(()->{
                        graph.endUpdate();
                        graph.getAutomaticGraph().setFpsLabel(fps);
                        edgeProgressLbl.setText("0/"+graph.getModel().getAllEdges().size());
                        edgeProgressPbr.setProgress(1);
                        edgeProgressPbr.setStyle("-fx-accent: green; ");
                        graph.getAutomaticGraph().setEdgesLbl(edgeProgressLbl);
                        graph.getAutomaticGraph().setEdgesPbr(edgeProgressPbr);
                    });





                    position.setTextFill(Color.GOLD);
                    graph.getCellLayer().setOnMouseMoved(mouseEvent -> position.setText("X:"+df.format(mouseEvent.getX()/1)+"  Y:"+df.format(mouseEvent.getY()/2)));

//                    graph.setFPS(fps);

                    wiresTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

/** listner detekcja trasy*/

                        StringBuilder wayListStr = new StringBuilder();
                        final List<Edge> edgeList = new ArrayList<>();

                        final List<Cell> cellList = new ArrayList<>(graph.printAllPaths(graph.getNode(newValue.getToXcode()), graph.getNode(newValue.getFromXcode()), newValue.getFromPin(), newValue.getToPin(), wireColorTranslator.getColor(newValue.getColor())));

                        for (int interator = 0 ; interator < cellList.size() ; interator++){
                            wayListStr.append(cellList.get(interator).getCellId()).append(" <- ");

                            for (Edge edge : graph.getModel().getAllEdges()){
                                if (interator+1<cellList.size())
                                    if ((edge.getSource().getCellId().equals(cellList.get(interator).getCellId()) && edge.getTarget().getCellId().equals(cellList.get(interator+1).getCellId())) ||
                                            (edge.getTarget().getCellId().equals(cellList.get(interator).getCellId()) && edge.getSource().getCellId().equals(cellList.get(interator+1).getCellId()))){
                                        edgeList.add(edge);
                                    }
                            }
                        }


                        double lenght = 0;
                        for (Edge edge : edgeList){
                            lenght += edge.getLenght();
                        }
                        newValue.lenghtProperty().set(df.format(lenght) + " mm");
                        newValue.wayProperty().set( wayListStr.substring(0,wayListStr.length()-3));

                        //  });
                    });
                    /**** linie wykresu*/
//                        Axes axes = new Axes(
//                                8000, 8000,
//                                0, 4000, 50,
//                                -4000, 0, 50
//                        );
//                        axes.setLayoutX(0);
//                        axes.setLayoutY(0);
//                        graph.getCanvas().getChildren().add(axes);






                    //toggleLeftTable.getDividers().get(0).setPosition(.4);
                    radialMenu();
                    //toggleLeftTable.getDividers().get(0).setPosition(.5);
                    main.setDisable(false);



                    Platform.runLater(()->{

                        /**Minimap declare objects and bind visablity cel edges*/
                        for (Cell cell : graph.getModel().getAllCells()) {
                            if (cell.getCellType().equals("SpliceCell")){
                                cell.visibleProperty().bind(splicesVisableProperty);
                                //splicesVisableProperty.setValue(false);
                            }
                            if (cell.getCellType().equals("ComponentCell")){
                                cell.visibleProperty().bind(componentsVisableProperty);
                                //componentsVisableProperty.setValue(false);
                            }
                            if (cell.getCellType().equals("AnotherCell")){
                                cell.visibleProperty().bind(anothersVisableProperty);
                                //anothersVisableProperty.setValue(false);
                            }
                            miniMap.addCell(cell);
                        }

                        tableLayout.getGridPane().toBack();
                        miniMap.drawTableLayout(tableLayout.getGridPane());
                        Pane minimapTPane = new Pane();
                        minimapTPane.setOpacity(.7);
                        minimapTPane.getChildren().add(miniMap.getPane());
                        miniMap.updateMap();
                        minimapTPane.setVisible(false);
                        otherviewProperty.addListener((observableValue, aBoolean, t1) -> minimapTPane.setVisible(otherviewProperty.get()));


                        main.getChildren().add(minimapTPane);
                        minimapTPane.setLayoutX(0);
                        minimapTPane.setLayoutX(minimapTPane.getScene().getWidth()-miniMap.getGroup().getLayoutBounds().getWidth());

                        miniMap.getGroup().layoutBoundsProperty().addListener((observableValue, bounds, t1) -> minimapTPane.setLayoutX(minimapTPane.getScene().getWidth()-bounds.getWidth()));



//
//                        Line verticleLine = new Line();
//                        verticleLine.setStrokeWidth(2);
//                        verticleLine.getStrokeDashArray().addAll(25d, 10d);
//                        verticleLine.setStyle("-fx-stroke: gold;");
//                        verticleLine.setOpacity(.5);
//                        Line horizontalLine = new Line();
//                        horizontalLine.setStrokeWidth(2);
//                        horizontalLine.setStyle("-fx-stroke: gold;");
//                        horizontalLine.setOpacity(.5);
//                        horizontalLine.getStrokeDashArray().addAll(25d, 10d);
//
//                        graph.getCellLayer().getChildren().addAll(verticleLine,horizontalLine);
//
//
//
//                        layoutStack.getChildren().remove(position);
//                        graph.getCellLayer().getChildren().add(position);
//                        graph.getCellLayer().addEventFilter(MouseEvent.ANY, event -> {
//                            mouseX = event.getX()+4;
//                            mouseY = event.getY()+4;
//                            verticleLine.setStartY(0);
//                            verticleLine.setEndY(mouseY+graph.getCellLayer().getHeight());
//                            verticleLine.setEndX(mouseX);
//                            verticleLine.setStartX(mouseX);
//
//                            horizontalLine.setStartX(0);
//                            horizontalLine.setEndX(mouseX+graph.getCellLayer().getWidth());
//                            horizontalLine.setEndY(mouseY);
//                            horizontalLine.setStartY(mouseY);
//                        });
//
//
//                        graph.getCellLayer().addEventFilter(MouseEvent.ANY, event -> {
//
//                            position.setLayoutX(event.getX()+20);
//                            position.setLayoutY(event.getY()+20);
//
//                        });


                    });

                    for(Cell cell : graph.getModel().getConnectorsData()){
                        for (Wires wire : graph.getModel().getWiresData()) {
                            if (cell.getXcode().equals(wire.getFromXcode()) || cell.getXcode().equals(wire.getToXcode())) {
                                cell.getWires().add(wire);
                            }
                        }
                    }





                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task_6.setOnSucceeded(workerStateEvent -> {
            // task_finall.run();
            mainProgressBar.setProgress(1);
            indicator.setProgress(-1);
            taskbarProgressbar.showCustomProgress(0, TaskbarProgressbar.Type.INDETERMINATE);
            processname.setText("Finalizacja, Proszê czekaæ...........");
            Thread th1 = new Thread(task_finall);
            th1.setName("1");
            th1.setPriority(Thread.MAX_PRIORITY);
            th1.setDaemon(true);
            th1.start();
        });
//        task_6.setOnSucceeded(workerStateEvent -> {
//            indicator.setProgress(-1);
//            processname.setText("Finalizacja, Proszê czekaæ...........");
//            task_finall.run();
//
//        });

//        Task task_analyze = new Task() {
//            @Override
//            protected Object call() {
//                Platform.runLater(()-> {
//                    analyzeBtn.setDisable(true);
//                    processname.setText("Analiza...........");
//                    preloaderBox.setVisible(true);
//                    preloaderBox.toFront();
//                });
//                System.out.println("Analyze 1");
//                for (Module modul : graph.getModel().getModulesData()){
//                    modul.setUsed(false);
//                }
//                System.out.println("Analyze 2");
//                for (Cell cel : graph.getModel().getConnectorsData()){
//                    cel.dockedProperty.set(false);
//                }
//                try {
//                    System.out.println("Analyze 3");
//                   // automaticProperty.set(true);
//                    /**  dokowanie z xcodem */
//                    for (Cell cell : graph.getModel().getConnectorsData()){
//                        //System.out.println("Analizujê mo¿liwy modu³ dla: "+cell.getXcode());
//                        if (!cell.getCustomerCode().toLowerCase().toLowerCase().contains("splice") || !cell.getCustomerCode().toLowerCase().toLowerCase().contains("spaw"))
//                        for (Module module : graph.getModel().getModulesData()){
//                        //    //System.out.println("-Modu³: "+module.getModuleConnectorCustomerCode());
//                            if (!cell.isDocked() && !module.isUsed() && cell.getXcode().equals(module.getModuleXcode()))
//                            if (
//                                    cell.getCustomerCode().equals(module.getModuleConnectorCustomerCode()) ||
//                                    cell.getGIMCode().equals(module.getModuleConnectorCustomerCode())||
//                            cell.getCustomerCode().equals(module.getModuleGMCode()) ||
//                                    cell.getGIMCode().equals(module.getModuleGMCode())
//                            ){
//                                //System.out.println("Analyze detected: " + cell+" -> "+module.getModuleXcode());
//                              //  cell.relocate(module.getLayoutX(),module.getLayoutY());
//                                connectorToModuleDock(cell,module,false);
//                               // cell.usedModuleProperty().set(module.getModuleXcode());
//                                Platform.runLater(() -> module.setUsed(true));
//
//                            }
//                        }
//                    }
//                    System.out.println("Analyze 4");
//                    /**  dokowanie z podobnym xcodem */
//                    if (similarXcodeAnalyze.isSelected())
//                    for (Cell cell : graph.getModel().getConnectorsData()){
//                        //System.out.println("Analizujê mo¿liwy modu³ dla: "+cell.getXcode());
//                        if (!cell.getCustomerCode().toLowerCase().toLowerCase().contains("splice") || !cell.getCustomerCode().toLowerCase().toLowerCase().contains("spaw"))
//                        for (Module module : graph.getModel().getModulesData()){
//                            //    //System.out.println("-Modu³: "+module.getModuleConnectorCustomerCode());
//                            if (!cell.isDocked() && !module.isUsed() && cell.getXcode().contains(module.getModuleXcode()))
//                                if (
//                                        cell.getCustomerCode().equals(module.getModuleConnectorCustomerCode()) ||
//                                                cell.getGIMCode().equals(module.getModuleConnectorCustomerCode())||
//                                                cell.getCustomerCode().equals(module.getModuleGMCode()) ||
//                                                cell.getGIMCode().equals(module.getModuleGMCode())
//                                ){
//                                    //System.out.println("Analyze detected: " + cell+" -> "+module.getModuleXcode());
//                                    //  cell.relocate(module.getLayoutX(),module.getLayoutY());
//                                    connectorToModuleDock(cell,module,false);
//                                    // cell.usedModuleProperty().set(module.getModuleXcode());
//                                    Platform.runLater(() -> module.setUsed(true));
//                                }
//                        }
//                    }
//                    System.out.println("Analyze 5");
//                    /**  dokowanie z innym xcodem */
//                    if (anotherXcodeAnalyze.isSelected())
//                        for (Cell cell : graph.getModel().getConnectorsData()){
//                            //System.out.println("Analizujê mo¿liwy modu³ dla: "+cell.getXcode());
//                            if (!cell.getCustomerCode().toLowerCase().toLowerCase().contains("splice") || !cell.getCustomerCode().toLowerCase().toLowerCase().contains("spaw"))
//                            for (Module module : graph.getModel().getModulesData()){
//                                //    //System.out.println("-Modu³: "+module.getModuleConnectorCustomerCode());
//                                if (!cell.isDocked() && !module.isUsed() /*&& cell.getXcode().contains(module.getModuleXcode())*/)
//                                    if (
//                                            cell.getCustomerCode().equals(module.getModuleConnectorCustomerCode()) ||
//                                                    cell.getGIMCode().equals(module.getModuleConnectorCustomerCode())||
//                                                    cell.getCustomerCode().equals(module.getModuleGMCode()) ||
//                                                    cell.getGIMCode().equals(module.getModuleGMCode())
//                                    ){
//                                        //System.out.println("Analyze detected: " + cell+" -> "+module.getModuleXcode());
//                                        //  cell.relocate(module.getLayoutX(),module.getLayoutY());
//                                        cell.dockedProperty.set(true);
//                                        connectorToModuleDock(cell,module,false);
//                                        // cell.usedModuleProperty().set(module.getModuleXcode());
//                                        Platform.runLater(() -> module.setUsed(true));
//                                    }
//                            }
//                        }
//                    System.out.println("Analyze end");
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//                return null;
//            }
//        };
//        Thread th1 = new Thread(task_analyze);
//        th1.setPriority(Thread.MAX_PRIORITY);
//        th1.setDaemon(true);
//        if (model.getModulesData().size() == 0 && model.getConnectorsData().size() == 0){
//            analyzeBtn.setDisable(true);
//        }
        Tooltip.install(analyzeBtn,new Tooltip("Dopasuj konektory do modu³ów."));


        analyzeBtn.setOnMouseClicked(mouseEvent -> {
            proccesRunning = true;
//            task_analyze.workDoneProperty().addListener((observableValue, number, t1) -> {
//                indicator.setProgress(t1.doubleValue());
//            });
            if (model.getModulesData().size() == 0 && model.getConnectorsData().size() == 0) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.getDialogPane().getStylesheets().add("/resources/style/jmetro/combination.css");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.initStyle(StageStyle.TRANSPARENT);
                alert.setTitle("Nie mo¿na przeprowadziæ ¿¹danej operacji..");
                alert.setHeaderText("Nie mo¿na przeprowadziæ ¿¹danej operacji.");
                if (model.getModulesData().size() == 0 && model.getConnectorsData().size() == 0){
                    alert.setContentText("Poniewa¿ nie wykry³em ¿adnych modu³ów oraz konektorów.");
                }else
                if (model.getModulesData().size() == 0){
                    alert.setContentText("Poniewa¿ nie wykry³em ¿adnych modu³ów.");
                }else
                if (model.getConnectorsData().size() == 0){
                    alert.setContentText("Poniewa¿ nie wykry³em ¿adnych konektorów.");
                }
                alert.showAndWait();
            } else{

                try {
//                    System.out.println(th1.getState());
//                    if (th1.getState().equals(Thread.State.TERMINATED)){
//                        th1.interrupt();
//                        th1.join();
//                        th1.start();
//                    }else{
//                        th1.start();
//                    }
                    Platform.runLater(()-> {
                        analyzeBtn.setDisable(true);
                        processname.setText("Analiza...........");
                        preloaderBox.setVisible(true);
                        preloaderBox.toFront();
                    });
                    System.out.println("Analyze 1");
                    for (Module modul : graph.getModel().getModulesData()){
                        modul.setUsed(false);
                    }
                    System.out.println("Analyze 2");
                    for (Cell cel : graph.getModel().getConnectorsData()){
                        cel.dockedProperty.set(false);
                    }
                    try {
                        System.out.println("Analyze 3");
                        // automaticProperty.set(true);
                        /**  dokowanie z xcodem */
                        for (Cell cell : graph.getModel().getConnectorsData()){
                            Thread.sleep(10);
                            //System.out.println("Analizujê mo¿liwy modu³ dla: "+cell.getXcode());
                            if (!cell.getCustomerCode().toLowerCase().toLowerCase().contains("splice") || !cell.getCustomerCode().toLowerCase().toLowerCase().contains("spaw"))
                                for (Module module : graph.getModel().getModulesData()){
                                    //    //System.out.println("-Modu³: "+module.getModuleConnectorCustomerCode());
                                    if (!cell.isDocked() && !module.isUsed() && cell.getXcode().equals(module.getModuleXcode()))
                                        if (
                                                cell.getCustomerCode().equals(module.getModuleConnectorCustomerCode()) ||
                                                        cell.getGIMCode().equals(module.getModuleConnectorCustomerCode())||
                                                        cell.getCustomerCode().equals(module.getModuleGMCode()) ||
                                                        cell.getGIMCode().equals(module.getModuleGMCode())
                                        ){
                                            //System.out.println("Analyze detected: " + cell+" -> "+module.getModuleXcode());
                                            //  cell.relocate(module.getLayoutX(),module.getLayoutY());
                                            connectorToModuleDock(cell,module,false);
                                            // cell.usedModuleProperty().set(module.getModuleXcode());
                                            /*Platform.runLater(() -> module.setUsed(true)/*)*/;

                                        }
                                }
                        }
                        System.out.println("Analyze 4");
                        /**  dokowanie z podobnym xcodem */
                        if (similarXcodeAnalyze.isSelected())
                            for (Cell cell : graph.getModel().getConnectorsData()){
                                Thread.sleep(10);
                                //System.out.println("Analizujê mo¿liwy modu³ dla: "+cell.getXcode());
                                if (!cell.getCustomerCode().toLowerCase().toLowerCase().contains("splice") || !cell.getCustomerCode().toLowerCase().toLowerCase().contains("spaw"))
                                    for (Module module : graph.getModel().getModulesData()){
                                        //    //System.out.println("-Modu³: "+module.getModuleConnectorCustomerCode());
                                        if (!cell.isDocked() && !module.isUsed() && cell.getXcode().contains(module.getModuleXcode()))
                                            if (
                                                    cell.getCustomerCode().equals(module.getModuleConnectorCustomerCode()) ||
                                                            cell.getGIMCode().equals(module.getModuleConnectorCustomerCode())||
                                                            cell.getCustomerCode().equals(module.getModuleGMCode()) ||
                                                            cell.getGIMCode().equals(module.getModuleGMCode())
                                            ){
                                                //System.out.println("Analyze detected: " + cell+" -> "+module.getModuleXcode());
                                                //  cell.relocate(module.getLayoutX(),module.getLayoutY());
                                                connectorToModuleDock(cell,module,false);
                                                // cell.usedModuleProperty().set(module.getModuleXcode());
                                                /*Platform.runLater(() -> */module.setUsed(true)/*)*/;
                                            }
                                    }
                            }
                        System.out.println("Analyze 5");
                        /**  dokowanie z innym xcodem */
                        if (anotherXcodeAnalyze.isSelected())
                            for (Cell cell : graph.getModel().getConnectorsData()){
                                Thread.sleep(10);
                                //System.out.println("Analizujê mo¿liwy modu³ dla: "+cell.getXcode());
                                if (!cell.getCustomerCode().toLowerCase().toLowerCase().contains("splice") || !cell.getCustomerCode().toLowerCase().toLowerCase().contains("spaw"))
                                    for (Module module : graph.getModel().getModulesData()){
                                        //    //System.out.println("-Modu³: "+module.getModuleConnectorCustomerCode());
                                        if (!cell.isDocked() && !module.isUsed() /*&& cell.getXcode().contains(module.getModuleXcode())*/)
                                            if (
                                                    cell.getCustomerCode().equals(module.getModuleConnectorCustomerCode()) ||
                                                            cell.getGIMCode().equals(module.getModuleConnectorCustomerCode())||
                                                            cell.getCustomerCode().equals(module.getModuleGMCode()) ||
                                                            cell.getGIMCode().equals(module.getModuleGMCode())
                                            ){
                                                //System.out.println("Analyze detected: " + cell+" -> "+module.getModuleXcode());
                                                //  cell.relocate(module.getLayoutX(),module.getLayoutY());
                                                cell.dockedProperty.set(true);
                                                connectorToModuleDock(cell,module,false);
                                                // cell.usedModuleProperty().set(module.getModuleXcode());
                                                /*Platform.runLater(() ->*/ module.setUsed(true)/*)*/;
                                            }
                                    }
                            }
                        System.out.println("Analyze end");
                        Platform.runLater(()->
                                {preloaderBox.setVisible(false);
                                    analyzeBtn.setDisable(false);}
                        );
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    // task_analyze.run();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            proccesRunning = false;
        });

//        task_analyze.setOnSucceeded(workerStateEvent -> {
//            preloaderBox.setVisible(false);
//            analyzeBtn.setDisable(false);
//        });

//        Task t = new Task(){
//
//
//            @Override
//            protected Object call() {
//
//                try {
//
//                    /** context menu*/
////                    ContextMenu contextMenu = new ContextMenu();
////
////                    mainMenuItems.addAll(mainMenuButton.getItems());
////
////                    graph.getCellLayer().setOnMouseClicked(mouseEvent -> contextMenu.getItems().setAll(mainMenuItems));
////                    mainMenuButton.setOnMouseClicked(mouseEvent -> mainMenuButton.getItems().setAll(mainMenuItems));
////
////                    graph.getScrollPane().setContextMenu(contextMenu);
//
////                    tableLayout.hRuler.visibleProperty().bind(layoutVisableProperty);
////                    tableLayout.vRuler.visibleProperty().bind(layoutVisableProperty);
//
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//                ;
//                return null;
//            }
//        };
//        Thread th = new Thread(t);
//        th.setName("LoadData");
//        th.setPriority(Thread.MAX_PRIORITY);
//        th.setDaemon(true);
//        th.start();
//        th.interrupt();
//
//        t.setOnSucceeded(workerStateEvent -> {
//           // Platform.runLater(() -> {
//                indicator.setProgress(-1);
//                processname.setText("Finalizacja, Proszê czekaæ...........");
//           // });




        task_finall.setOnSucceeded(workerStateEvent1 -> {

/**wype³nianie  tabel  danymi i dekoracje*/
            Platform.runLater(this::tablesDecorationsAndDatas);
            systemPintable.clear();
            //systemPintableXML.clear();


            dbCon = null;
            graph.scrollPane.setStyle(" -fx-background-color: transparent;");
            getLayoutStack().setStyle(" -fx-background-color: transparent;");
            Node vp = graph.getScrollPane().lookup(".viewport");
            vp.setStyle(" -fx-background-color: transparent,\n" +
                    "        linear-gradient(from 0.0px 0.0px to 20px  0.0px, repeat, derive(DARKSLATEGREY, -50%) 5%, transparent 5%),\n" +
                    "        linear-gradient(from 0.0px 0.0px to  0.0px 20px, repeat, derive(DARKSLATEGREY, -50%) 5%, transparent 5%);");


            preloaderBox.setVisible(false);

            graph.scrollPane.setDisable(false);
            graph.scrollPane.setEffect(null);
            proccesRunning = false;
            taskbarProgressbar.showCustomProgress(0, TaskbarProgressbar.Type.NO_PROGRESS);

        });

        /****  mainHud */
        final double startWidth = mainHud.getBoundsInLocal().getWidth()-showHideBottomHud.getWidth()+25;

        Tooltip showHideBottomHudTooltip = new Tooltip();
        showHideBottomHud.setTooltip(showHideBottomHudTooltip);
        showHideBottomHud.setOnAction(event -> {
            if (showHideBottomHud.isSelected()){
                mainHud.setTranslateX(startWidth);
                showHideBottomHud.setText("<");
                showHideBottomHudTooltip.setText("Poka¿ ToolBar");
            }else{
                mainHud.setTranslateX(mainHud.getTranslateX()-startWidth);
                showHideBottomHud.setText(">");
                showHideBottomHudTooltip.setText("Ukryj ToolBar");
            }
        });




        return this;
    }



    private void printJob() {
        PrinterJob job = PrinterJob.createPrinterJob();
        Printer printer = job.getPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
        job.getJobSettings().setPageLayout(pageLayout);

        final GridPane root = tableLayout.getGridPane();
        try {


            if (job.showPrintDialog(root.getScene().getWindow())) {
                pageLayout = job.getJobSettings().getPageLayout();
                double width = root.getWidth();
                double height = root.getHeight();

                PrintResolution resolution = job.getJobSettings().getPrintResolution();

                width /= resolution.getFeedResolution();

                height /= resolution.getCrossFeedResolution();

                double scaleX = pageLayout.getPrintableWidth() / width / 600;
                double scaleY = pageLayout.getPrintableHeight() / height / 600;

                scale = new Scale(scaleX, scaleY);

                root.getTransforms().add(scale);

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Podgl±d wydruku");
                alert.setHeaderText("");
                Text text = new Text("Czy drukowaæ?\n" +
                        "Papier: " + pageLayout.getPaper() + "\n" +
                        "Orientacja: " + pageLayout.getPageOrientation() + "\n" +
                        "Drukarka: " + printer + "\n" +
                        "Rozdzielczo¶æ: " + job.getJobSettings().getPrintResolution() + "\n" +
                        "Jako¶æ: " + job.getJobSettings().getPrintQuality() + "\n" +
                        "Druk: " + job.getJobSettings().getPrintSides() + "\n" +
                        "Kopie: " + job.getJobSettings().getCopies() + "\n"


                );


                alert.getDialogPane().getStylesheets().add("/resources/style/jmetro/JMetroDarkTheme.css");
                alert.getDialogPane().getStyleClass().addAll("table-view", "background");
                alert.getDialogPane().setStyle("-fx-background-color: #1d1d1d;");
                ImageView graphic = new ImageView(root.snapshot(null, null));

                alert.initOwner(root.getScene().getWindow());

                alert.setOnCloseRequest(event2 -> Platform.runLater(() -> toggleLeftTable.setEffect(null)));


                Button setings = new Button("Zmieñ Ustawienia");
                StackPane pane = new StackPane(graphic, setings);
                setings.setOnAction(event1 -> {
                    // Platform.runLater(() ->  graph.getScrollPane().setOpacity(0));
                    if (job.showPrintDialog(root.getScene().getWindow())) {

                        root.getTransforms().clear();
                        PageLayout pageLayout2 = job.getJobSettings().getPageLayout();
                        double width2 = root.getWidth();
                        double height2 = root.getHeight();

                        //PrintResolution resolution2 = job.getJobSettings().getPrintResolution();

                        width2 /= resolution.getFeedResolution();

                        height2 /= resolution.getCrossFeedResolution();

                        double scaleX2 = pageLayout2.getPrintableWidth() / width2 / 600;
                        double scaleY2 = pageLayout2.getPrintableHeight() / height2 / 600;

                        scale = new Scale(scaleX2, scaleY2);

                        root.getTransforms().add(scale);


                        pane.getChildren().clear();
                        pane.getChildren().addAll(new ImageView(root.snapshot(null, null)), setings);


                    } else {
                        alert.close();
                    }

                });

                pane.setAlignment(Pos.CENTER);
                pane.setStyle(
                        "   -fx-border-color: orange;\n" +

                                "-fx-border-width: 2;\n" +

                                "-fx-background-color: white;\n" +

                                "-fx-padding: " + job.getJobSettings().getPageLayout().getTopMargin() + " " + job.getJobSettings().getPageLayout().getRightMargin() + " " + job.getJobSettings().getPageLayout().getBottomMargin() + " " + job.getJobSettings().getPageLayout().getLeftMargin() + ";\n"
                );
                alert.getDialogPane().setContent(new ScrollPane(new HBox(pane, text)));
                alert.setResizable(true);

                Platform.runLater(() -> toggleLeftTable.setEffect(new MotionBlur()));

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    boolean success = job.printPage(pageLayout, root);
                    if (success) {
                        job.endJob();
                    }
                }  // ... user chose CANCEL or closed the dialog


                root.getTransforms().remove(scale);

            }
        } catch (Exception e) {
            Platform.runLater(() -> toggleLeftTable.setEffect(null));

            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }

    }


//    String lastitem = "";
//    private void addDataAndModule(SystemPintable systempintable){
////        boolean itemOrderRootFinished = true;
////        boolean itemOrderRootMounted = true;
////        if (!systempintable.getSellId().equals(lastitem)) {
////            itemOrderRoot = new TreeItem<>(new SystemPintable(systempintable.getSellId(), "", "", "", "", "", "", null, "", "notstarted"));
////            itemOrder = new TreeItem<>(systempintable);
////            itemOrderRoot.getChildren().addAll(itemOrder);
////            itemRoot.getChildren().addAll(itemOrderRoot);
////            if (!systempintable.getOrderStatus().equals("finished")){itemOrderRootFinished = false;}
////            if (!systempintable.getModuleMounted().equals("true")){itemOrderRootMounted = false;}
////
////            if (systempintable.getOrderStatus().equals("virtual")){
////                itemOrderRoot.getValue().setOrderStatus("virtual");
////                itemOrderRoot.setValue(itemOrderRoot.getValue());
////            }else
////            if (itemOrderRootFinished && itemOrderRoot != null){
////                itemOrderRoot.getValue().setOrderStatus("finished");
////                itemOrderRoot.setValue(itemOrderRoot.getValue());
////            }
////            if (itemOrderRootMounted && itemOrderRoot != null){
////                itemOrderRoot.getValue().setModuleMounted("true");
////                itemOrderRoot.setValue(itemOrderRoot.getValue());
////            }
////
////        }else{
////            itemOrder = new TreeItem<>(systempintable);
////            itemOrderRoot.getChildren().addAll(itemOrder);
////
////        }
////
////
////        assert itemOrderRoot != null;
////        itemOrderRoot.expandedProperty().addListener(observable -> modulesTable.refresh());
////        itemRoot.expandedProperty().addListener(observable -> modulesTable.refresh());
////
////        lastitem=systempintable.getSellId();
//    }



    private void tablesDecorationsAndDatas(){

        {
//            orderId.setCellValueFactory(new TreeItemPropertyValueFactory<>("SellId"));
//            moduleName.setCellValueFactory(new TreeItemPropertyValueFactory<>("Xcode"));
//            moduleType.setCellValueFactory(new TreeItemPropertyValueFactory<>("Connector"));
//            moduleX.setCellValueFactory(new TreeItemPropertyValueFactory<>("CoordinateX"));
//            moduleY.setCellValueFactory(new TreeItemPropertyValueFactory<>("CoordinateY"));
//            moduleMounted.setCellValueFactory(new TreeItemPropertyValueFactory<>("ModuleMounted"));
//            OrderStatus.setCellValueFactory(new TreeItemPropertyValueFactory<>("OrderStatus"));
//            moduleMounted.setCellFactory(param -> new TreeTableCell<>() {
//
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    if (item != null) {
//                        ImageView mark;
//                        if (item.equals("true")) {
//                            mark = new ImageView(new Image("/resources/images/success.png"));
//                        } else {
//                            mark = new ImageView(new Image("/resources/images/fico_64.png"));
//                        }
//                        mark.setFitHeight(16);
//                        mark.setFitWidth(16);
//                        setGraphic(mark);
//                    }
//                }
//            });
//            OrderStatus.setCellFactory(param -> new TreeTableCell<>() {
//
//                @Override
//                protected void updateItem(String item, boolean empty) {
//                    ////System.out.println(item);
//                    if (item != null) {
//
//                        switch (item) {
//                            case "finished" -> {
////                                ImageView mark = new ImageView(new Image("/resources/images/success.png"));
////                                mark.setFitHeight(16);
////                                mark.setFitWidth(16);
////                                setGraphic(mark);
//                            }
//                            case "notstarted" -> {
////                                ImageView mark = new ImageView(new Image("/resources/images/error.png"));
////                                mark.setFitHeight(16);
////                                mark.setFitWidth(16);
////                                setGraphic(mark);
//                            }
//                            case "started" -> {
////                                ImageView mark = new ImageView(new Image("/resources/images/warning.png"));
////                                mark.setFitHeight(16);
////                                mark.setFitWidth(16);
////                                setGraphic(mark);
//                            }
//                        }
//                        if (item.equals("virtual")) {
//                            ImageView mark = new ImageView(new Image("/resources/images/virtual.png"));
//                            mark.setFitHeight(16);
//                            mark.setFitWidth(16);
//                            setTooltip(new Tooltip("Modu³ wirtualny"));
//                            setGraphic(mark);
//                        }
//                    }
//                }
//            });

//        this.modulesTable.getColumns().addAll(
//                orderId,
//                moduleName,
//                moduleType,
//                moduleX,
//                moduleY,
//                OrderStatus,
//                moduleMounted
//        );

//            itemRoot.setExpanded(true);
//            Platform.runLater(() -> modulesTable.setRoot(itemRoot));
        }
        {
            /**tabela modu³y */

            orderId.setCellValueFactory(cellData -> cellData.getValue().moduleIdProperty);
            moduleName.setCellValueFactory(cellData -> cellData.getValue().moduleXcode);
            moduleType.setCellValueFactory(cellData -> cellData.getValue().moduleConnectorCustomerCode);
            moduleX.setCellValueFactory(cellData -> cellData.getValue().moduleCordX);
            moduleY.setCellValueFactory(cellData -> cellData.getValue().moduleCordY);



            modulesTable.setItems(graph.getModel().getModulesData());
            Platform.runLater(() -> {
                TableFilter<Module> tableFilterConnectors = new TableFilter<>(modulesTable);
            });
        }
        {
            /**tabela konektory */
            connectorId.setCellValueFactory(cellData -> cellData.getValue().getCellIdProperty());
            connectorXcode.setCellValueFactory(cellData -> cellData.getValue().xcodeProperty());
            connectorModule.setCellValueFactory(cellData -> cellData.getValue().usedModuleProperty());
            connectorCustomerCode.setCellValueFactory(cellData -> cellData.getValue().customerCodeProperty());
            connectorGimCode.setCellValueFactory(cellData -> cellData.getValue().gimCodeProperty());
            connectorX.setCellValueFactory(cellData -> Bindings.format("%.1f", cellData.getValue().getPositionXProperty()));
            connectorY.setCellValueFactory(cellData -> Bindings.format("%.1f", cellData.getValue().getPositionYProperty()));
            connectorPins.setCellValueFactory(cellData -> cellData.getValue().pinsProperty());
            connectorDocked.setCellValueFactory(cellData -> cellData.getValue().dockedProperty());
            connectorDescription.setCellValueFactory(cellData -> cellData.getValue().getDescription());
            connectorModuleX.setCellValueFactory(cellData -> cellData.getValue().moduleXProperty());
            connectorModuleY.setCellValueFactory(cellData -> cellData.getValue().moduleYProperty());

            connectorsTableView.setItems(graph.getModel().getConnectorsData());
            Platform.runLater(() -> {
                TableFilter<Cell> tableFilterConnectors = new TableFilter<>(connectorsTableView);
            });
            connectorsTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, cell, t1) -> {
                if (cell != null) {
                    graph.getSelectionModel().clear();
                    //cell.nodeProperty.get().setEffect(new Blend());

                    cell.toFront();
                }
                //t1.nodeProperty.get().setEffect(null);
                graph.getSelectionModel().add(t1);
                graph.getScrollPane().setHvalue(t1.positionXProperty.doubleValue() / graph.getCellLayer().getWidth());
                graph.getScrollPane().setVvalue(t1.positionYProperty.doubleValue() / graph.getCellLayer().getHeight());
                new Bounce(t1).setResetOnFinished(true).play();
                t1.toFront();
            });

        }
        {

            /**tabela po³±czenia */
            wireLp.setCellValueFactory(cellData -> cellData.getValue().lpProperty());
            wireName.setCellValueFactory(cellData -> cellData.getValue().NameProperty());
            wireColor.setCellValueFactory(cellData -> cellData.getValue().colorProperty());
            wireXcodeFrom.setCellValueFactory(cellData -> cellData.getValue().fromXcodeProperty());
            wirePinFrom.setCellValueFactory(cellData -> cellData.getValue().fromPinProperty());
            wireXcodeTo.setCellValueFactory(cellData -> cellData.getValue().toXcodeProperty());
            wirePinTo.setCellValueFactory(cellData -> cellData.getValue().toPinProperty());
            wireLenght.setCellValueFactory(cellData -> cellData.getValue().lenghtProperty());
            wireWay.setCellValueFactory(cellData -> cellData.getValue().wayProperty());
            wireArea.setCellValueFactory(cellData -> cellData.getValue().areaProperty());

            wiresTableView.setItems(graph.getModel().getWiresData());
            Platform.runLater(() -> {
                TableFilter<Wires> tableFilterWires = new TableFilter<>(wiresTableView);
            });
            /*** podhlad tsleditor*/
//            Platform.runLater(() -> {
//                tslEditorPane.setContent(tslEditor.getEditor());
//                tslEditor.getCodeArea().clear();
//                System.out.println("asdasdsadas");
//                tslEditor.getCodeArea().appendText("This document was created by HarnessDesignSystemInterfaceDiagram.\n");
//                tslEditor.getCodeArea().appendText(createTslDoc());
//
//                connectorsTableView.getItems().addListener((ListChangeListener<Cell>) c -> {
//                    System.out.println("Changed on " + c);
//                    if(c.next()){
//                        System.out.println(c.getFrom());
//                    }
//
//                });
//
////                     tslEditor.getCodeArea().selectedTextProperty().addListener((observableValue, s, t1) -> {
////
////                     });
//            });

        }
        {
            /**tabela component */
            componentXcode.setCellValueFactory(cellData -> cellData.getValue().xcodeProperty());
            componentCustomerCode.setCellValueFactory(cellData -> cellData.getValue().customerCodeProperty());
            componentGimCode.setCellValueFactory(cellData -> cellData.getValue().gimCodeProperty());
            componentX.setCellValueFactory(cellData -> Bindings.format("%.1f", cellData.getValue().getPositionXProperty().get()));
            componentY.setCellValueFactory(cellData -> Bindings.format("%.1f", cellData.getValue().getPositionYProperty().get()));
            componentId.setCellValueFactory(cellData -> cellData.getValue().getCellIdProperty());
            componentDescription.setCellValueFactory(cellData -> cellData.getValue().getDescription());

            componentTableView.setItems(graph.getModel().getPasivesData());
            Platform.runLater(() -> {
                TableFilter<Cell> tableFilterConnectors = new TableFilter<>(componentTableView);
            });
            componentTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, cell, t1) -> {
                Cell cell_ = null;

                for (Cell cell1 : graph.getModel().getComponentsData()){
                    if (t1.getCellId().equals(cell1.getCellId())){
                        cell_ = cell1;
                    }
                }
                for (Cell cell1 : graph.getModel().getConnectorsData()){
                    if (t1.getCellId().equals(cell1.getCellId())){
                        cell_ = cell1;
                    }
                }
                if (cell_ != null) {
                    graph.getSelectionModel().clear();
                    //cell.nodeProperty.get().setEffect(new Blend());

                    cell_.toFront();
                }
                //t1.nodeProperty.get().setEffect(null);

                if (cell_ != null) {

                    graph.getScrollPane().setHvalue(cell_.getPositionXProperty().get() / graph.getCellLayer().getWidth());
                }
                if (cell_ != null) {
                    graph.getScrollPane().setVvalue(cell_.getPositionYProperty().get() / graph.getCellLayer().getHeight());
                }

                if (cell_ != null) {
                    graph.getSelectionModel().add(cell_);
                    new Bounce(cell_).setResetOnFinished(true).play();
                    cell_.toFront();
                }
            });

        }
        {
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
        }




//        List<String> connectorsNames = new ArrayList<>();
//        for (Cell cell : graph.getModel().getConnectorsData()){
//            connectorsNames.add(cell.getXcode());
//        }








        wireXcodeFrom.setCellFactory(column -> new TableCell<>() {


            final TableView<Cell> tblSortView = new TableView<>();
            final TableColumn<Cell, String> tblClmID = new TableColumn<>("Id");
            final TableColumn<Cell, String> tblClmName = new TableColumn<>("Nazwa");
            final TableColumn<Cell, String> tblClmCode = new TableColumn<>("Kod");

            private final Button button = new Button("Wybierz");
            final VBox vBox = new VBox(tblSortView, button);
            final MenuButton mbtn = new MenuButton();
            final MenuItem item = new MenuItem();

            {
                tblClmID.setCellValueFactory(cellData -> cellData.getValue().getCellIdProperty());
                tblClmName.setCellValueFactory(cellData -> cellData.getValue().xcodeProperty());
                tblClmCode.setCellValueFactory(cellData -> cellData.getValue().customerCodeProperty());
                tblSortView.getColumns().addAll(tblClmID, tblClmName, tblClmCode);
                tblSortView.setItems(graph.getModel().getConnectorsData());
                button.setOnAction(event -> {
                    if (tblSortView.getSelectionModel().getSelectedItem() != null) {
                        setText(tblSortView.getSelectionModel().getSelectedItem().getCellId());
                        getTableRow().getItem().setFromXcode(tblSortView.getSelectionModel().getSelectedItem().getXcode());
//                        reloadTslDoc();
                    }
                });
                item.setGraphic(vBox);
                mbtn.getItems().add(item);
            }

            @Override
            protected void updateItem(String specie, boolean empty) {
                super.updateItem(specie, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    setText(specie);
                    setGraphic(mbtn);
                    mbtn.textProperty().bind(textProperty());
                    //textProperty().set(specie);

                }
            }
        });




        wirePinFrom.setCellFactory(column -> new TableCell<>() {

            private String newval = "";

            final TextField wiresStringTextFieldTableCell = new TextField();

            {
                wiresStringTextFieldTableCell.setOnKeyPressed(t -> {
                    if (t.getCode() == KeyCode.ENTER) {
                        updateItem(newval, false);
//                        reloadTslDoc();
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                });

                wiresStringTextFieldTableCell.textProperty().addListener((observable, oldValue, newValue) -> {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                    // commitEdit(newValue);
                    // //System.out.println("" + newValue);
                    newval = newValue;

                });

                wiresStringTextFieldTableCell.focusedProperty().addListener((observable, oldValue, newValue) -> {

                    if (!newValue) {
                        updateItem(newval, false);
//                        reloadTslDoc();
                    }

                });
//                                wiresStringTextFieldTableCell.textProperty().addListener((observableValue, s, t1) -> {
//                                    updateItem(t1,false);
//                                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setStyle("");
                } else if (item.equals("") || item.toLowerCase().equals("spaw") || item.toLowerCase().equals("splice")) {
                    wiresStringTextFieldTableCell.setText("Spaw");
                    setGraphic(wiresStringTextFieldTableCell);
                    //setText("Spaw");
                    //setTextFill(Color.WHITE);
                    wiresStringTextFieldTableCell.setStyle("-fx-background-color: firebrick;-fx-text-fill: white;");
                    if (!super.getTableRow().isEmpty())
                        super.getTableRow().getItem().setFromPin(item);
                } else {
                    wiresStringTextFieldTableCell.setText(item);
                    setGraphic(wiresStringTextFieldTableCell);

                    //setText(item);
                    wiresStringTextFieldTableCell.setStyle("");
                    if (!super.getTableRow().isEmpty())
                        super.getTableRow().getItem().setFromPin(item);
                }
            }
        });


        wireXcodeTo.setCellFactory(column -> new TableCell<>() {


            final TableView<Cell> tblSortView = new TableView<>();
            final TableColumn<Cell, String> tblClmID = new TableColumn<>("Id");
            final TableColumn<Cell, String> tblClmName = new TableColumn<>("Nazwa");
            final TableColumn<Cell, String> tblClmCode = new TableColumn<>("Kod");

            private final Button button = new Button("Wybierz");
            final VBox vBox = new VBox(tblSortView, button);
            final MenuButton mbtn = new MenuButton();
            final MenuItem item = new MenuItem();

            {
                tblClmID.setCellValueFactory(cellData -> cellData.getValue().getCellIdProperty());
                tblClmName.setCellValueFactory(cellData -> cellData.getValue().xcodeProperty());
                tblClmCode.setCellValueFactory(cellData -> cellData.getValue().customerCodeProperty());
                tblSortView.getColumns().addAll(tblClmID, tblClmName, tblClmCode);
                tblSortView.setItems(graph.getModel().getConnectorsData());
                button.setOnAction(event -> {
                    if (tblSortView.getSelectionModel().getSelectedItem() != null) {
                        setText(tblSortView.getSelectionModel().getSelectedItem().getCellId());
                        getTableRow().getItem().setToXcode(tblSortView.getSelectionModel().getSelectedItem().getCellId());
                        //reloadTslDoc();
                    }
                });
                item.setGraphic(vBox);
                mbtn.getItems().add(item);
            }

            @Override
            protected void updateItem(String specie, boolean empty) {
                super.updateItem(specie, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    setText(specie);
                    setGraphic(mbtn);
                    mbtn.textProperty().bind(textProperty());
                }
            }
        });



        wirePinTo.setCellFactory(column -> new TableCell<>() {

            private String newval = "";

            final TextField wiresStringTextFieldTableCell = new TextField();

            {
                wiresStringTextFieldTableCell.setOnKeyPressed(t -> {
                    if (t.getCode() == KeyCode.ENTER) {
                        updateItem(newval, false);
                        //reloadTslDoc();
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                });

                wiresStringTextFieldTableCell.textProperty().addListener((observable, oldValue, newValue) -> {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                    // commitEdit(newValue);
                    // //System.out.println("" + newValue);
                    newval = newValue;
                });

                wiresStringTextFieldTableCell.focusedProperty().addListener((observable, oldValue, newValue) -> {

                    if (!newValue) {
                        updateItem(newval, false);
                        //reloadTslDoc();
                    }

                });
//                                wiresStringTextFieldTableCell.textProperty().addListener((observableValue, s, t1) -> {
//                                    updateItem(t1,false);
//                                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setStyle("");
                } else if (item.equals("") || item.toLowerCase().equals("spaw") || item.toLowerCase().equals("splice")) {
                    wiresStringTextFieldTableCell.setText("Spaw");
                    setGraphic(wiresStringTextFieldTableCell);

                    //setText("Spaw");
                    //setTextFill(Color.WHITE);
                    wiresStringTextFieldTableCell.setStyle("-fx-background-color: firebrick;-fx-text-fill: white;");
                    if (!super.getTableRow().isEmpty())
                        super.getTableRow().getItem().setToPin(item);
                } else {
                    wiresStringTextFieldTableCell.setText(item);
                    setGraphic(wiresStringTextFieldTableCell);

                    //setText(item);
                    wiresStringTextFieldTableCell.setStyle("");
                    if (!super.getTableRow().isEmpty())
                        super.getTableRow().getItem().setToPin(item);
                }
            }
        });


        wireLenght.setCellFactory(column -> new TableCell<>() {


            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setStyle("");
                } else if (item.contains("mm")) {
                    setText(item);
                    setTextFill(Color.WHITE);
                    setStyle("-fx-background-color: BLUEVIOLET;" +
                            " -fx-background-radius: 4;\n" +
                            "    -fx-background-insets: 4;" +
                            "    -fx-alignment: center;");
                } else {
                    setText(item);
                    setStyle("");
                }
            }
        });

        /**checkboxy i texfliedy tabeli konektory*/



        connectorXcode.setCellFactory(column -> new TableCell<>() {
            private String newval = "";

            final TextField wiresStringTextFieldTableCell = new TextField();

            {
                wiresStringTextFieldTableCell.setOnKeyPressed(new EventHandler<>() {
                    @Override
                    public void handle(KeyEvent t) {
                        if (t.getCode() == KeyCode.ENTER) {
                            updateItem(newval, false);
                            wiresTableView.refresh();
                            //System.out.println(oldval);
                        } else if (t.getCode() == KeyCode.ESCAPE) {
                            cancelEdit();
                        }
                    }
                });

                wiresStringTextFieldTableCell.textProperty().addListener((observable, oldValue, newValue) -> {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                    // commitEdit(newValue);
                    // //System.out.println("" + newValue);
                    newval = newValue;
                });

                wiresStringTextFieldTableCell.focusedProperty().addListener(new ChangeListener<>() {

                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                        if (!newValue) {
                            updateItem(newval, false);
                            wiresTableView.refresh();
                            //System.out.println(oldval);
                        }


                    }
                });
//                                wiresStringTextFieldTableCell.textProperty().addListener((observableValue, s, t1) -> {
//                                    updateItem(t1,false);
//                                });

            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setStyle("");
                    setText("...");
                } else if (item.equals("")) {
                    wiresStringTextFieldTableCell.setText("");
                    setGraphic(wiresStringTextFieldTableCell);
                    //setText("Spaw");
                    //setTextFill(Color.WHITE);

                    wiresStringTextFieldTableCell.setStyle("-fx-background-color: firebrick;-fx-text-fill: white;");


                    for (Wires wires : graph.getModel().getWiresData()) {
                        /*if (wires.fromXcodeProperty().get().equals(super.getTableRow().getItem().getXcode()))*/
                        if (wires.fromXcodeProperty().get().equals(getText())) {
                            wires.fromXcodeProperty().set(item);
                            //System.out.println(item);
                        }
                        if (wires.toXcodeProperty().get().equals(getText())) {
                            wires.toXcodeProperty().set(item);
                            //System.out.println(item);
                        }
                    }

                    if (!super.getTableRow().isEmpty())
                        super.getTableRow().getItem().setXcode(item);
                } else {
                    wiresStringTextFieldTableCell.setText(item);
                    setGraphic(wiresStringTextFieldTableCell);

                    //setText(item);
                    wiresStringTextFieldTableCell.setStyle("");

                    for (Wires wires : graph.getModel().getWiresData()) {
                        /*if (wires.fromXcodeProperty().get().equals(super.getTableRow().getItem().getXcode()))*/
                        if (wires.fromXcodeProperty().get().equals(getText())) {
                            wires.fromXcodeProperty().set(item);
                            //System.out.println(item);
                        }
                        if (wires.toXcodeProperty().get().equals(getText())) {
                            wires.toXcodeProperty().set(item);
                            //System.out.println(item);
                        }
                    }

                    if (!super.getTableRow().isEmpty())
                        super.getTableRow().getItem().setXcode(item);
                }
            }
        });


        connectorCustomerCode.setCellFactory(column -> new TableCell<>() {

            private String newval = "";

            final TextField wiresStringTextFieldTableCell = new TextField();

            {
                wiresStringTextFieldTableCell.setOnKeyPressed(t -> {
                    if (t.getCode() == KeyCode.ENTER) {
                        updateItem(newval, false);

                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                });

                wiresStringTextFieldTableCell.textProperty().addListener((observable, oldValue, newValue) -> {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                    // commitEdit(newValue);
                    // //System.out.println("" + newValue);
                    newval = newValue;
                });

                wiresStringTextFieldTableCell.focusedProperty().addListener((observable, oldValue, newValue) -> {

                    if (!newValue) {
                        updateItem(newval, false);
                    }

                });
//                                wiresStringTextFieldTableCell.textProperty().addListener((observableValue, s, t1) -> {
//                                    updateItem(t1,false);
//                                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setStyle("");
                } else if (item.toLowerCase().contains("splice") || item.toLowerCase().contains("spaw")) {
                    wiresStringTextFieldTableCell.setText("");
                    setGraphic(wiresStringTextFieldTableCell);
                    setText("Spaw");
                    wiresStringTextFieldTableCell.setText("Spaw");
                    setTextFill(Color.WHITE);
                    wiresStringTextFieldTableCell.setStyle("-fx-background-color: firebrick;-fx-text-fill: white;");
                    if (!super.getTableRow().isEmpty()) {
                        super.getTableRow().getItem().setCustomerCode(item);
                        super.getTableRow().getItem().setCellTypeProperty(CellType.SPLICECELL.toString());
                    }
                } else {
                    wiresStringTextFieldTableCell.setText(item);
                    setGraphic(wiresStringTextFieldTableCell);

                    //setText(item);
                    wiresStringTextFieldTableCell.setStyle("");
                    if (!super.getTableRow().isEmpty()) {
                        super.getTableRow().getItem().setCustomerCode(item);
                        super.getTableRow().getItem().setCellTypeProperty(CellType.CONNECTORSMALL.toString());
                    }
                }
            }
        });
        connectorGimCode.setCellFactory(column -> new TableCell<>() {

            private String newval = "";

            final TextField wiresStringTextFieldTableCell = new TextField();

            {
                wiresStringTextFieldTableCell.setOnKeyPressed(t -> {
                    if (t.getCode() == KeyCode.ENTER) {
                        updateItem(newval, false);

                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                });

                wiresStringTextFieldTableCell.textProperty().addListener((observable, oldValue, newValue) -> {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                    // commitEdit(newValue);
                    // //System.out.println("" + newValue);
                    newval = newValue;
                });

                wiresStringTextFieldTableCell.focusedProperty().addListener((observable, oldValue, newValue) -> {

                    if (!newValue) {
                        updateItem(newval, false);
                    }

                });
//                                wiresStringTextFieldTableCell.textProperty().addListener((observableValue, s, t1) -> {
//                                    updateItem(t1,false);
//                                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {

                if (item == null || empty) {
                    setStyle("");
                } else if (item.equals("")) {
                    wiresStringTextFieldTableCell.setText("");
                    setGraphic(wiresStringTextFieldTableCell);
                    //setText("Spaw");
                    //setTextFill(Color.WHITE);
                    wiresStringTextFieldTableCell.setStyle("-fx-background-color: darkred;-fx-text-fill: white;");
                    /**super.getTableRow().getItem().setGIMCode(item);*/
                } else if (item.equals("SPLICE")) {
                    wiresStringTextFieldTableCell.setText(item);
                    setGraphic(wiresStringTextFieldTableCell);
                    //setText("Spaw");
                    //setTextFill(Color.WHITE);
                    wiresStringTextFieldTableCell.setStyle("-fx-background-color: BLUEVIOLET;-fx-text-fill: white;");
                    /**super.getTableRow().getItem().setGIMCode(item);*/
                } else {
                    wiresStringTextFieldTableCell.setText(item);
                    setGraphic(wiresStringTextFieldTableCell);

                    //setText(item);
                    wiresStringTextFieldTableCell.setStyle("");
                    super.updateItem(item, empty);
                    /**super.getTableRow().getItem().setGIMCode(item);*/
                }
            }
        });
        connectorDocked.setCellValueFactory(
                c -> {

                    ColorAdjust colorAdjust = new ColorAdjust();

                    ImageView lock = new ImageView(new Image("/resources/icons/gemicon/PNG/16x16/row 3/6.png"));
                    ImageView unlock = new ImageView(new Image("/resources/icons/gemicon/PNG/16x16/row 3/4.png"));
                    Cell candidate = c.getValue();
                    CheckBox checkBox = new CheckBox();
                    checkBox.getStyleClass().add("check-box-n");
                    //checkBox.setGraphic(unlock);
                    if (candidate.dockedProperty().get()){
                        colorAdjust.setSaturation(1);
                        colorAdjust.setHue(.31);
                        colorAdjust.setBrightness(.22);
                        colorAdjust.setContrast(-1);
                        checkBox.setGraphic(lock);
                        checkBox.setEffect(colorAdjust);
                    }else{
                        colorAdjust.setSaturation(1);
                        colorAdjust.setHue(.62);
                        colorAdjust.setBrightness(.24);
                        colorAdjust.setContrast(-1);
                        checkBox.setGraphic(unlock);
                        checkBox.setEffect(colorAdjust);
                    }
//                    colorAdjust.setSaturation(1);
//                    colorAdjust.setHue(.62);
//                    colorAdjust.setBrightness(.24);
//                    colorAdjust.setContrast(-1);
//                    checkBox.setEffect(colorAdjust);
                    candidate.dockedProperty().addListener((observableValue, aBoolean, t1) -> {
                        if (t1){
                            colorAdjust.setSaturation(1);
                            colorAdjust.setHue(.31);
                            colorAdjust.setBrightness(.22);
                            colorAdjust.setContrast(-1);
                            Platform.runLater(() -> checkBox.setGraphic(lock));

                        }else{
                            colorAdjust.setSaturation(1);
                            colorAdjust.setHue(.62);
                            colorAdjust.setBrightness(.24);
                            colorAdjust.setContrast(-1);
                            Platform.runLater(() -> checkBox.setGraphic(unlock));

                        }
                        checkBox.setEffect(colorAdjust);
                        checkBox.setSelected(t1);
                    });
                    // checkBox.selectedProperty().bind(candidate.dockedProperty());
                    checkBox.selectedProperty().setValue(candidate.dockedProperty().getValue());
                    checkBox
                            .selectedProperty()
                            .addListener((ov, old_val, new_val) -> candidate.dockedProperty().set(new_val));

                    return new SimpleObjectProperty(checkBox);
                });

        connectorModule.setCellFactory(column -> new TableCell<>() {


            final TableView<Module> tblSortView = new TableView<>();
            final TableColumn<Module, String> tblClmName = new TableColumn<>("Nazwa");
            final TableColumn<Module, String> tblClmCode = new TableColumn<>("Typ");
            final TableColumn<Module, String> tblClmGM = new TableColumn<>("GM");
            final TableColumn<Module, String> tblClmX = new TableColumn<>("cordX");
            final TableColumn<Module, String> tblClmY = new TableColumn<>("cordY");

            private final Button button = new Button("Wybierz");
            final VBox vBox = new VBox(tblSortView, button);
            final MenuButton mbtn = new MenuButton();
            final MenuItem item = new MenuItem();

            {
                tblClmName.setCellValueFactory(cellData -> cellData.getValue().moduleXcode);
                tblClmCode.setCellValueFactory(cellData -> cellData.getValue().moduleConnectorCustomerCode);
                tblClmGM.setCellValueFactory(cellData -> cellData.getValue().moduleGMCode);
                tblClmX.setCellValueFactory(cellData -> cellData.getValue().moduleCordX);
                tblClmY.setCellValueFactory(cellData -> cellData.getValue().moduleCordY);
                tblSortView.getColumns().addAll(tblClmName, tblClmCode, tblClmGM, tblClmX, tblClmY);
                tblSortView.setItems(graph.getModel().getModulesData());
//                Platform.runLater(() -> {
//                   TableFilter<Module> tableFilterConnectors = new TableFilter<>(tblSortView);
//                   tableFilterConnectors.unSelectAllValues(tblClmCode);
//                    if (getTableRow()!=null)
//                        tableFilterConnectors.selectValue(tblClmCode,getTableRow().getItem().getGIMCode());
//                        tableFilterConnectors.executeFilter();
//                });


                button.setOnAction(event -> {
                    if (tblSortView.getSelectionModel().getSelectedItem() != null) {
                        setText(tblSortView.getSelectionModel().getSelectedItem().moduleXcode.get());
                        //getTableRow().getItem().setXcode(tblSortView.getSelectionModel().getSelectedItem().moduleName.get());
                        /**    relocate    *********************************************************/
                        connectorToModuleDock(getTableRow().getItem(), tblSortView.getSelectionModel().getSelectedItem(), true);
                        // mbtn.setText(tblSortView.getSelectionModel().getSelectedItem().moduleXcode.get());
//                        getTableRow().getItem().setModuleX(tblSortView.getSelectionModel().getSelectedItem().getModuleCordX());
//                        getTableRow().getItem().setModuleY(tblSortView.getSelectionModel().getSelectedItem().getModuleCordY());
                        getTableRow().getItem().xcodeProperty().set(mbtn.getText());
                    }
                });

//                tblSortView.getSelectionModel().selectedItemProperty().addListener((observableValue, module, t1) -> {
//                    if (tblSortView.getSelectionModel().getSelectedItem() != null){
//                        Platform.runLater(() -> setText(tblSortView.getSelectionModel().getSelectedItem().moduleXcode.get()));;
//                        //getTableRow().getItem().setXcode(tblSortView.getSelectionModel().getSelectedItem().moduleName.get());
//                        /**    relocate    *********************************************************/
//                        connectorToModuleDock(getTableRow().getItem(),tblSortView.getSelectionModel().getSelectedItem(),true);
//                        mbtn.setText(tblSortView.getSelectionModel().getSelectedItem().moduleXcode.get());
//
////                        getTableRow().getItem().setModuleX(tblSortView.getSelectionModel().getSelectedItem().getModuleCordX());
////                        getTableRow().getItem().setModuleY(tblSortView.getSelectionModel().getSelectedItem().getModuleCordY());
//                    }
//                });

                item.setGraphic(vBox);
                mbtn.getItems().add(item);
                TableFilter<Module> tableFilterConnectors = new TableFilter<>(tblSortView);
//                tableFilterConnectors.unSelectAllValues(tblClmCode);
//                if (getTableRow()!=null)
//                    tableFilterConnectors.selectValue(tblClmCode,getTableRow().getItem().getGIMCode());
//                tableFilterConnectors.executeFilter();

            }
//            private final ComboBox<String> comboBox = new ComboBox<>();
//
//            {
//                comboBox.setItems(FXCollections.observableList(modulesNames));
//                comboBox.setOnAction(e -> commitEdit(comboBox.getValue()));
//                comboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
//                    updateItem(t1,false);
//                });
//            }

            @Override
            protected void updateItem(String specie, boolean empty) {
                super.updateItem(specie, empty);
                if (empty || specie.equals("")) {
                    setText("...");
                    setGraphic(null);
                } else {
                    if (getTableRow().getItem() != null)
                        if (getTableRow().getItem().getCellType().equals("ConnectorSmallCell")) {
                            mbtn.textProperty().bind(getTableRow().getItem().usedModuleProperty());
                            if (mbtn.getText().equals("brak")) {
                                mbtn.setStyle("-fx-border-color:red;");
                            } else if (mbtn.getText().equals(getTableRow().getItem().getCellId())) {
                                mbtn.setStyle("-fx-border-color:green;");
                            } else if (!mbtn.getText().equals(getTableRow().getItem().getCellId())) {
                                mbtn.setStyle("-fx-border-color:orange;");
                            }
                            setGraphic(mbtn);
                            //mbtn.setText(getTableRow().getItem().usedModuleProperty().get());
                            setText(specie);
                            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);


                            //
                            getTableRow().getItem().setTblModule(tblSortView);
                            super.getTableRow().getItem().getCellTypeProperty().addListener((observableValue, s, newType) -> {
                                if (newType.equals("CONNECTORSMALL")) {
                                } else {
                                    setGraphic(null);
                                }
                            });
                        } else {
                            setText("Nie Dostêpny");
                            setGraphic(null);
                        }

//                    if (!super.getTableRow().isEmpty())
//                    super.getTableRow().getItem().moduleProperty().set(comboBox.getValue());
                }
            }
        });


        wireLp.setCellFactory(column -> new TableCell<>() {

            final ImageView delete = new ImageView(new Image("/resources/images/error.png"));
            final Button action = new Button("", delete);


            {
                action.getStyleClass().clear();
                action.getStyleClass().add("table-view-small");
                action.setOnAction(e -> {
                    //  //System.out.println(getTableRow().getItem().getLp());
                    //getTableView().getItems().remove(getTableRow().getItem());
                    Alert alert = new Alert(AlertType.CONFIRMATION);
//                    alert.getDialogPane().getStylesheets().add("/resources/style/jmetro/JMetroDarkTheme.css");
//                    alert.getDialogPane().getStyleClass().addAll("table-view","background");
//                    alert.getDialogPane().setStyle("-fx-background-color: #1d1d1d;");
                    alert.getDialogPane().getStylesheets().add("/resources/style/jmetro/combination.css");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    alert.setGraphic(null);
                    alert.setTitle("Usuwanie po³¹czenia.");
                    alert.setHeaderText("Czy usun±æ po³±czenie " + getTableRow().getItem().getName());
                    alert.setContentText("Po³±czenie: " + getTableRow().getItem().getName() + " z " + getTableRow().getItem().getFromXcode() + "/" + getTableRow().getItem().getFromPin() + " do " + getTableRow().getItem().getToXcode() + "/ " + getTableRow().getItem().getToPin());

                    ButtonType buttonTypeOne = new ButtonType("Usuñ");

                    ButtonType buttonTypeCancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == buttonTypeOne) {
                        graph.getModel().removeFromWiresData(getTableRow().getItem());
                        getTableView().refresh();
                    }  // ... user chose CANCEL or closed the dialog


                });
                action.setTooltip(new Tooltip("Usuñ po³±czenie"));
                delete.setFitHeight(8);
                delete.setFitWidth(8);
                //   updateItem((String.valueOf(getTableRow().getIndex()))+".",false);

            }

            @Override
            protected void updateItem(String specie, boolean empty) {
                super.updateItem(specie, empty);
                if (!super.getTableRow().isEmpty())
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setText(specie);
                        setGraphic(action);
                    }
            }
        });

        connectorDescription.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String specie, boolean empty) {
                super.updateItem(specie, empty);
                if (!super.getTableRow().isEmpty())
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setText(specie);
                        try {
                            for (Map.Entry<String, Color> entry : allColorsWithName().entrySet()) {
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
        componentDescription.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String specie, boolean empty) {
                super.updateItem(specie, empty);
                if (!super.getTableRow().isEmpty())
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setText(specie);
                        try {
                            for (Map.Entry<String, Color> entry : allColorsWithName().entrySet()) {
                                if (getTableRow().getItem().getDescription().getValue().replace(" ", "").toLowerCase().contains(entry.getKey().toLowerCase())) {
                                    //System.out.println(entry.getKey());
                                    //setBackground(new Background(new BackgroundFill(entry.getValue(),null,new Insets(2,2,2,2))));
                                    setStyle("-fx-background-radius: 0px;-fx-background-color:" + entry.getKey() + ";-fx-background-insets: 2, 2, 2, 2;-fx-alignment: left;");
                                    setTextFill(entry.getValue().invert().brighter());
                                    break;
                                }
                            }
                            if (specie.toLowerCase().contains("splice")) {
                                //setBackground(new Background(new BackgroundFill(entry.getValue(),null,new Insets(2,2,2,2))));
                                setStyle("-fx-background-radius: 0px;-fx-background-color:BLUEVIOLET;-fx-background-insets: 2, 2, 2, 2;-fx-alignment: left;");
                                setTextFill(Color.WHITE);
                                setGraphic(new ImageView(new Image("/resources/componentIcons/x.png")));
                            } else if (specie.toLowerCase().contains("natural")) {
                                //setBackground(new Background(new BackgroundFill(entry.getValue(),null,new Insets(2,2,2,2))));
                                setStyle("-fx-background-radius: 0px;-fx-background-color:WHEAT;-fx-background-insets: 2, 2, 2, 2;-fx-alignment: left;");
                                setTextFill(Color.BLACK);
                            } else if (specie.toLowerCase().contains("label")) {
                                //setBackground(new Background(new BackgroundFill(entry.getValue(),null,new Insets(2,2,2,2))));
                                setStyle("-fx-background-radius: 0px;-fx-background-color:ROYALBLUE;-fx-background-insets: 2, 2, 2, 2;-fx-alignment: left;");
                                setTextFill(Color.BLACK);
                                setGraphic(new ImageView(new Image("/resources/componentIcons/BcodeIcon.png")));
                            } else if (specie.equals("Brak Danych")) {
                                // setTextFill(Color.WHITE);
                            } else if (specie.toLowerCase().contains("tape")) {
                                setGraphic(new ImageView(new Image("/resources/componentIcons/Tape.png")));
                                if (getTextFill().toString().equals("0xffffffff")) {
                                    ColorAdjust colorAdjust = new ColorAdjust();
                                    colorAdjust.setSaturation(1);
                                    colorAdjust.setHue(.31);
                                    colorAdjust.setBrightness(.22);
                                    colorAdjust.setContrast(-1);
                                    getGraphic().setEffect(colorAdjust);
                                }
                            } else if (specie.toLowerCase().contains("cable tie") || specie.toLowerCase().contains("fixing tie")) {
                                setGraphic(new ImageView(new Image("/resources/componentIcons/CableTie.png")));
                                if (getTextFill().toString().equals("0xffffffff")) {
                                    ColorAdjust colorAdjust = new ColorAdjust();
                                    colorAdjust.setSaturation(1);
                                    colorAdjust.setHue(.31);
                                    colorAdjust.setBrightness(.22);
                                    colorAdjust.setContrast(-1);
                                    getGraphic().setEffect(colorAdjust);
                                }
                            } else if (specie.toLowerCase().contains("carrier")) {
                                setGraphic(new ImageView(new Image("/resources/componentIcons/carrier.png")));
                                if (getTextFill().toString().equals("0xffffffff")) {
                                    ColorAdjust colorAdjust = new ColorAdjust();
                                    colorAdjust.setSaturation(1);
                                    colorAdjust.setHue(.31);
                                    colorAdjust.setBrightness(.22);
                                    colorAdjust.setContrast(-1);
                                    getGraphic().setEffect(colorAdjust);
                                }
                            } else if (specie.toLowerCase().contains("cover")) {
                                setGraphic(new ImageView(new Image("/resources/componentIcons/cover.png")));
                                if (getTextFill().toString().equals("0xffffffff")) {
                                    ColorAdjust colorAdjust = new ColorAdjust();
                                    colorAdjust.setSaturation(1);
                                    colorAdjust.setHue(.31);
                                    colorAdjust.setBrightness(.22);
                                    colorAdjust.setContrast(-1);
                                    getGraphic().setEffect(colorAdjust);
                                }
                            } else if (specie.toLowerCase().contains("adapter")) {
                                setGraphic(new ImageView(new Image("/resources/componentIcons/adapter.png")));
                                if (getTextFill().toString().equals("0xffffffff")) {
                                    ColorAdjust colorAdjust = new ColorAdjust();
                                    colorAdjust.setSaturation(1);
                                    colorAdjust.setHue(.31);
                                    colorAdjust.setBrightness(.22);
                                    colorAdjust.setContrast(-1);
                                    getGraphic().setEffect(colorAdjust);
                                }
                            } else if (specie.toLowerCase().contains("seal")) {
                                setGraphic(new ImageView(new Image("/resources/componentIcons/seal.png")));
                                if (getTextFill().toString().equals("0xffffffff")) {
                                    ColorAdjust colorAdjust = new ColorAdjust();
                                    colorAdjust.setSaturation(1);
                                    colorAdjust.setHue(.31);
                                    colorAdjust.setBrightness(.22);
                                    colorAdjust.setContrast(-1);
                                    getGraphic().setEffect(colorAdjust);
                                }
                            } else if (specie.toLowerCase().contains("duct")) {
                                setGraphic(new ImageView(new Image("/resources/componentIcons/duct.png")));
                                if (getTextFill().toString().equals("0xffffffff")) {
                                    ColorAdjust colorAdjust = new ColorAdjust();
                                    colorAdjust.setSaturation(1);
                                    colorAdjust.setHue(.31);
                                    colorAdjust.setBrightness(.22);
                                    colorAdjust.setContrast(-1);
                                    getGraphic().setEffect(colorAdjust);
                                }
                            } else if (specie.toLowerCase().contains("locking")) {
                                setGraphic(new ImageView(new Image("/resources/componentIcons/locking.png")));
                                if (getTextFill().toString().equals("0xffffffff")) {
                                    ColorAdjust colorAdjust = new ColorAdjust();
                                    colorAdjust.setSaturation(1);
                                    colorAdjust.setHue(.31);
                                    colorAdjust.setBrightness(.22);
                                    colorAdjust.setContrast(-1);
                                    getGraphic().setEffect(colorAdjust);
                                }
                            } else if (specie.toLowerCase().contains("tube")) {
                                setGraphic(new ImageView(new Image("/resources/componentIcons/tube.png")));
                            } else {
                                setGraphic(null);
                            }

                        } catch (ClassNotFoundException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        //setGraphic(action);
                    }
            }
        });
        componentGimCode.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String specie, boolean empty) {
                super.updateItem(specie, empty);
                if (!super.getTableRow().isEmpty())
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setText(specie);

                        if (specie.toLowerCase().contains("splice")) {
                            //setBackground(new Background(new BackgroundFill(entry.getValue(),null,new Insets(2,2,2,2))));
                            setStyle("-fx-background-radius: 5px;-fx-background-color:BLUEVIOLET;-fx-background-insets: 2, 2, 2, 2;-fx-alignment: center;");
                            // setTextFill(Color.BLUEVIOLET);
                        }

                        //setGraphic(action);
                        else {
                            setStyle("-fx-background-radius: 0px;-fx-background-color:transparent;-fx-background-insets: 0, 0, 0, 0;-fx-alignment: center;");
                        }
                    }
            }
        });
        modulesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //  //System.out.println(newValue.getView().getParent());
            if (oldValue!=null && oldValue.getView() != null){
                Platform.runLater(()->{
                    oldValue.getView().setStyle("-fx-effect: dropshadow(gaussian, green, 0, 0, 0, 0);-fx-text-fill: white;-fx-animated: true;");
                    oldValue.getView().lookup(".title").setStyle("" +
                            "-fx-background-color: #1d1d1d;\n" +
                            "    -fx-text-fill: white");
                });
            }
            if(newValue != null && newValue != oldValue /*&& newValue.getView() != null*/){
                Platform.runLater(()->{
                    newValue.getView().setStyle("-fx-effect: dropshadow(gaussian, #008287, 20, 0, 0, 0);-fx-text-fill: black;-fx-animated: true;");
                    newValue.getView().lookup(".title").setStyle("" +
                            "-fx-background-color: gold;\n" +
                            "    -fx-text-fill: #ffffff;");
                    newValue.getView().toFront();


                    if (newValue.getModuleCordX().equals("") || newValue.getModuleCordY().equals("")){
                        graph.getScrollPane().setHvalue((newValue.getView().getParent().getLayoutX() + newValue.getView().getBoundsInLocal().getWidth()) / tableLayout.getGridPane().getWidth() * graph.getScrollPane().getScaleX());
                        graph.getScrollPane().setVvalue((newValue.getView().getParent().getLayoutY() + newValue.getView().getBoundsInLocal().getHeight()) / tableLayout.getGridPane().getHeight() * graph.getScrollPane().getScaleY());
                        new Bounce(newValue).setResetOnFinished(true).play();
                        newValue.toFront();
                    }else {
                        graph.getScrollPane().setHvalue((tableLayout.getCellPositionCoord(newValue.moduleCordX.getValue(), newValue.moduleCordY.getValue()).getX() + newValue.getView().getBoundsInLocal().getWidth()) / tableLayout.getGridPane().getWidth() * graph.getScrollPane().getScaleX());
                        graph.getScrollPane().setVvalue((tableLayout.getCellPositionCoord(newValue.moduleCordX.getValue(), newValue.moduleCordY.getValue()).getY() + newValue.getView().getBoundsInLocal().getHeight()) / tableLayout.getGridPane().getHeight() * graph.getScrollPane().getScaleY());
                        new Bounce(newValue).setResetOnFinished(true).play();
                        newValue.toFront();
                    }
                });
            }

        });




//        toggleLeftTable.getDividers().get(0).positionProperty().addListener((observable, oldValue, newValue) -> {
//
//            if (newValue.doubleValue()<0.1){
//                btnToggleLeftTable.setText(">");
//                btnToggleLeftTable.setTooltip(new Tooltip("Rozwiñ"));
//                btnToggleLeftTable.setOnAction(event -> {
//                    toggleLeftTable.getDividers().get(0).setPosition(0.4);
//                    Platform.runLater(miniMap::updateMainArea);
//                });
//            }else{
//                btnToggleLeftTable.setText("<");
//                btnToggleLeftTable.setTooltip(new Tooltip("Zwiñ"));
//                btnToggleLeftTable.setOnAction(event -> {
//                    toggleLeftTable.getDividers().get(0).setPosition(0.01);
//                    Platform.runLater(miniMap::updateMainArea);
//                });
//            }
//
//        });
        BooleanProperty collapsed = new SimpleBooleanProperty();
        collapsed.bind(toggleLeftTable.getDividers().get(0).positionProperty().isEqualTo(0, 0.01));


        btnToggleLeftTable.textProperty().bind(Bindings.when(collapsed).then(">").otherwise("<"));

        btnToggleLeftTable.setOnAction(e -> {
            double target = collapsed.get() ? 0.4 : 0.0 ;
            KeyValue keyValue = new KeyValue(toggleLeftTable.getDividers().get(0).positionProperty(), target);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), keyValue));
            timeline.play();
        });



        graph.getModel().getModulesData().addListener((ListChangeListener<? super Module>) c -> {
            if(c.next()){
                modulesTable.refresh();
            }
        });


/*** podgl¹d zdjec tooltip */

        connectorsTableView.setRowFactory(tableView -> {
            TableRow<Cell> row = new TableRow<>();
//            AtomicReference<ImageView> graphic = new AtomicReference<>();
//            AtomicReference<Tooltip> tooltip = new AtomicReference<>();
/*            row.setOnMouseClicked(mouseEvent -> {
                        //Platform.runLater(() -> {
//                        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)){
//                            graphic.set(new ImageView());
//                            tooltip.set(new Tooltip());
//                            tooltip.get().setGraphic(graphic.get());
//                            row.setCursor(Cursor.WAIT);
//                            for (String imagePath : DSI_Model.imagesListParts) {
//                                if (imagePath.contains(row.getItem().getGIMCode())) {
//
//                                    System.out.println(imagePath);
//                                    graphic.get().setImage(new Image("file:" + imagePath.toString(), 150, 150, true, true));
//                                    break;
//                                } else {
//                                    graphic.get().setImage(new Image("/resources/images/error.png", 150, 150, true, true));
//                                }
//                            }
//                        row.setTooltip(tooltip.get());
//                            row.setCursor(DSI_Model.imageCursor);
//                    }
                //});

            });*/
            row.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {


                if (event.isSecondaryButtonDown() && event.getClickCount() == 2) {
                    if (row.getItem() != null)
                        row.getItem().cellContextMenu().show(row, event.getScreenX(), event.getScreenY());
                } else
                if (row.getItem() != null)
                    row.getItem().cellContextMenu().hide();
            });
//            row.setOnMouseExited(mouseEvent -> {
////                graphic.set(null);
////                tooltip.set(null);
////                tooltip.get().setGraphic(null);
////                row.setTooltip(null);
//            });

            return row;
        });

        componentTableView.setRowFactory(tableView -> {
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

        wiresTableView.setRowFactory(tableView -> {
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

        SortedSet<String> entries = new TreeSet<>(Comparator.comparing(String::toString));
        for (Cell cell : graph.getModel().getAllCells())
            entries.add("Id: "+cell.getCellId()+", Kod Klienta: "+cell.getCustomerCode()+", Kod GPN:"+cell.getGIMCode()+", Opis:"+cell.getDescription().get());

        AutoCompleteTextField<String> searchCell = new AutoCompleteTextField<>(entries);
        searchCell.setMinWidth(318);
        searchCell.setMaxEntries(30);
        searchCell.setPromptText("Wyszukaj");

//        ImageView clearImageView = new ImageView(new Image("resources/icon/unmark.png"));
//        clearImageView.setFitWidth(16);
//        clearImageView.setFitHeight(16);
//        ToggleButton clearSearchCell =new ToggleButton("",clearImageView);
        clearSearchCell.setOnAction(event -> searchCell.setText(""));
        searchBox.getChildren().addAll(searchCell);
        searchCell.getEntryMenu().setOnAction(e ->
                ((MenuItem) e.getTarget()).addEventHandler(Event.ANY, event ->
                {
                    if (searchCell.getLastSelectedObject() != null)
                    {
                        searchCell.setText(searchCell.getLastSelectedObject()/*.split(", ")[0]*/);
                        for (Cell cell : graph.getModel().getAllCells()){
                            if (searchCell.getLastSelectedObject().split(", ")[0].contains(cell.getCellId())){
                                graph.getSelectionModel().add(cell);
                                graph.getScrollPane().setHvalue(cell.getPositionXProperty().get() / graph.getCellLayer().getWidth());
                                graph.getScrollPane().setVvalue(cell.getPositionYProperty().get() / graph.getCellLayer().getHeight());
                                new Bounce(cell).setResetOnFinished(true).play();
                                cell.toFront();
                                break;
                            }
                        }
                    }
                }));

        //layoutStack.getChildren().add(searchCell);

    }
    RadialMenuItem automaticRadialItem = new RadialMenuItem(true);
    private void radialMenu(){


//    public  BooleanProperty automaticProperty = new SimpleBooleanProperty();
//    public  BooleanProperty selectionProperty = new SimpleBooleanProperty();
//    public  BooleanProperty movedProperty = new SimpleBooleanProperty();
//    public  BooleanProperty otherviewProperty = new SimpleBooleanProperty();
//    public  BooleanProperty layoutVisableProperty = new SimpleBooleanProperty();
//    // public BooleanProperty diagramVisableProperty = new SimpleBooleanProperty();
//
//    public  BooleanProperty splicesVisableProperty = new SimpleBooleanProperty();
//    public  BooleanProperty componentsVisableProperty = new SimpleBooleanProperty();
//    public  BooleanProperty anothersVisableProperty = new SimpleBooleanProperty();
        ImageView logo = new ImageView(new Image ("/resources/image/ico.png"));
        logo.setFitWidth(24);
        logo.setFitHeight(24);
        logo.setOpacity(.8);
        RadialMenu radialMenu = new RadialMenu(20, 180, 180, 0, new ImageView());


        RadialMenuContainer toolsContainer = new RadialMenuContainer();
        toolsContainer.setText("Narzêdzia");
        toolsContainer.setChildrenCenterOnParent(true);
        RadialMenuContainer componentsLayerContainer = new RadialMenuContainer();
        componentsLayerContainer.setText("Warstwa komponentów");
        componentsLayerContainer.setChildrenCenterOnParent(true);
        RadialMenuContainer mainLayerContainer = new RadialMenuContainer();
        mainLayerContainer.setText("G³ówna Warstwa");
        RadialMenuItem printItem = new RadialMenuItem();
        printItem.setText("Drukuj Layout");
        printItem.setOnMousePressed(mouseEvent -> printJob());
        RadialMenuItem sixthItem = new RadialMenuItem();
        sixthItem.setText("Zapisz Layout");
        sixthItem.setOnMousePressed(mouseEvent -> {
//        new SaveLayout().save(TesterName, String.valueOf(Columns), String.valueOf(Rows), graph.getModel().getAllModules(), graph.getModel().modulesData);
            new SaveLayoutXML_DOM().save(TesterName, String.valueOf(Columns), String.valueOf(Rows), graph.getModel().getModulesData()/*, graph.getModel().getModulesData()*/);


        });


        automaticRadialItem.setText("AutoModpe");
        RadialMenuItem selection = new RadialMenuItem(true);
        selection.setText("Zaznacz Kilka");
        RadialMenuItem move = new RadialMenuItem(true);
        move.setText("Przesówanie Kursorem");

        toolsContainer.addItem(automaticRadialItem);
        toolsContainer.addItem(selection);
        toolsContainer.addItem(move);


        RadialMenuItem splicesShowed = new RadialMenuItem(true);
        splicesShowed.setText("Warstwa Spawy");
        RadialMenuItem componentsShowed = new RadialMenuItem(true);
        componentsShowed.setText("Warstwa Komponenty");
        RadialMenuItem anothersShowed = new RadialMenuItem(true);
        anothersShowed.setText("Warstwa Inne");
        RadialMenuItem edgeLabelsShowed = new RadialMenuItem(true);
        edgeLabelsShowed.setText("Warstwa Etykiety d³ugoœci");

        componentsLayerContainer.addItem(splicesShowed);
        componentsLayerContainer.addItem(componentsShowed);
        componentsLayerContainer.addItem(anothersShowed);
        componentsLayerContainer.addItem(edgeLabelsShowed);

        RadialMenuItem otherview = new RadialMenuItem(true);
        otherview.setText("Mini Mapa Widoczna");
//    RadialMenuItem thirdContainerSecondStage2 = new RadialMenuItem(true);
//    thirdContainerSecondStage2.setText("Warstwa Diagram");
        RadialMenuItem layoutShowed = new RadialMenuItem(true);
        layoutShowed.setText("Warstwa Siatki");

        mainLayerContainer.addItem(otherview);
//    mainLayerContainer.addItem(thirdContainerSecondStage2);
        mainLayerContainer.addItem(layoutShowed);

        radialMenu.addRootItem(printItem);
        radialMenu.addRootItem(toolsContainer);
        radialMenu.addRootItem(componentsLayerContainer);
        radialMenu.addRootItem(mainLayerContainer);

        radialMenu.addRootItem(sixthItem);


        radialMenu.getStylesheets().add("/resources/style/radialmenu.css");


        ToggleButton togleRadialButton = new ToggleButton("",logo);
        togleRadialButton.setStyle("-fx-background-radius: 7em;");
        radialMenu.setVisible(false);
        togleRadialButton.selectedProperty().addListener((observableValue, aBoolean, t1) -> radialMenu.setVisible(t1));
//    logo.setOnMouseClicked(mouseEvent -> {
//        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)){
//            radialMenu.setVisible(!radialMenu.isVisible());
//        }
//    });

        Platform.runLater(() -> main.getChildren().addAll(radialMenu,togleRadialButton));
        radialMenu.setLayoutX(42);
        radialMenu.setLayoutY(400);
        togleRadialButton.setLayoutX(0);
        togleRadialButton.setLayoutY(400-15);
//    logo.layoutXProperty().bind(btnToggleLeftTable.layoutXProperty());
//    logo.layoutYProperty().bind(btnToggleLeftTable.layoutYProperty());
        toggleLeftTable.getDividers().get(0).positionProperty().addListener((observableValue, number, t1) -> {
            //System.out.println(t1.doubleValue());
            togleRadialButton.setLayoutX((main.getWidth()*t1.doubleValue())+10);
            radialMenu.setLayoutX((main.getWidth()*t1.doubleValue())+32);
        });



        {
            automaticProperty.bind(automaticRadialItem.selectedProperty());
            automaticProperty.addListener((observableValue, aBoolean, t1) -> ImgBtnOpacity(aBoolean, automaticBtn));
            selectionProperty.bind(selection.selectedProperty());
            selectionProperty.addListener((observableValue, aBoolean, t1) -> ImgBtnOpacity(aBoolean, selectableBtn));
            otherviewProperty.bind(otherview.selectedProperty());
            otherviewProperty.addListener((observableValue, aBoolean, t1) -> ImgBtnOpacity(aBoolean, miniMapVisableBtn));
            Platform.runLater(() -> otherview.selectedProperty().setValue(false));

            movedProperty.bind(move.selectedProperty());
            movedProperty.addListener((observableValue, aBoolean, t1) -> ImgBtnOpacity(aBoolean, movableBtn));
            layoutVisableProperty.bind(layoutShowed.selectedProperty());
            layoutVisableProperty.addListener((observableValue, aBoolean, t1) -> ImgBtnOpacity(aBoolean, layoutVisableBtn));
            // diagramVisableProperty.bind(diagramShowed.selectedProperty());

            splicesVisableProperty.bind(splicesShowed.selectedProperty());
            splicesVisableProperty.addListener((observableValue, aBoolean, t1) -> ImgBtnOpacity(aBoolean, spliceVisableBtn));
            Platform.runLater(() -> splicesShowed.selectedProperty().setValue(false));
            edgeLabelsVisableProperty.bind(edgeLabelsShowed.selectedProperty());
            edgeLabelsVisableProperty.addListener((observableValue, aBoolean, t1) -> ImgBtnOpacity(aBoolean, labelsVisableBtn));
            Platform.runLater(() -> edgeLabelsShowed.selectedProperty().setValue(false));
            componentsVisableProperty.bind(componentsShowed.selectedProperty());
            componentsVisableProperty.addListener((observableValue, aBoolean, t1) -> ImgBtnOpacity(aBoolean, componentVisableBtn));
            Platform.runLater(() -> componentsShowed.selectedProperty().setValue(false));

            anothersVisableProperty.bind(anothersShowed.selectedProperty());
            anothersVisableProperty.addListener((observableValue, aBoolean, t1) -> ImgBtnOpacity(aBoolean, anotherVisableBtn));
            Platform.runLater(() -> anothersShowed.selectedProperty().setValue(false));

            Platform.runLater(() -> graph.getAutomaticGraph().automaticLayoutProperty.bind(automaticProperty));

            graph.scrollPane.setOnMouseClicked(mouseEvent -> graph.scrollPane.setPannable(false));
            graph.scrollPane.setOnMousePressed(mouseEvent -> {

                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    graph.scrollPane.setPannable(movedProperty.get());
                } else {
                    graph.scrollPane.setPannable(false);
                }
            });
            Tooltip.install(miniMapVisableBtn,new Tooltip("Widocznoœæ MiniMapy."));
            //otherviewProperty.set(false);


            miniMapVisableBtn.setOnMousePressed(mouseEvent -> {
                ImageView source = (ImageView) mouseEvent.getSource();
                if (otherview.isSelected()) {
                    source.setOpacity(.5);
                    otherview.setSelected(false);
                } else {
                    source.setOpacity(1);
                    otherview.setSelected(true);
                }
            });
            otherview.setSelected(false);
            Tooltip.install(layoutVisableBtn,new Tooltip("Widocznoœæ Layoutu."));
            layoutVisableBtn.setOnMousePressed(mouseEvent -> {
                ImageView source = (ImageView) mouseEvent.getSource();
                if (layoutShowed.isSelected()) {
                    source.setOpacity(.5);
                    layoutShowed.setSelected(false);
                } else {
                    source.setOpacity(1);
                    layoutShowed.setSelected(true);
                }
            });
            Tooltip.install(spliceVisableBtn,new Tooltip("Widocznoœæ Spawów."));
            spliceVisableBtn.setOnMousePressed(mouseEvent -> {
                ImageView source = (ImageView) mouseEvent.getSource();
                if (splicesShowed.isSelected()) {
                    source.setOpacity(.5);
                    splicesShowed.setSelected(false);
                } else {
                    source.setOpacity(1);
                    splicesShowed.setSelected(true);
                }
            });


            labelsVisableBtn.setOnMousePressed(mouseEvent -> {
                ImageView source = (ImageView) mouseEvent.getSource();
                if (edgeLabelsShowed.isSelected()) {
                    source.setOpacity(.5);
                    edgeLabelsShowed.setSelected(false);
                } else {
                    source.setOpacity(1);
                    edgeLabelsShowed.setSelected(true);
                }
            });
            Tooltip.install(componentVisableBtn,new Tooltip("Widocznoœæ Komponentów."));
            componentVisableBtn.setOnMousePressed(mouseEvent -> {
                ImageView source = (ImageView) mouseEvent.getSource();
                if (componentsShowed.isSelected()) {
                    source.setOpacity(.5);
                    componentsShowed.setSelected(false);
                } else {
                    source.setOpacity(1);
                    componentsShowed.setSelected(true);
                }
            });
            Tooltip.install(anotherVisableBtn,new Tooltip("Widocznoœæ Innych Komponentów."));
            anotherVisableBtn.setOnMousePressed(mouseEvent -> {
                ImageView source = (ImageView) mouseEvent.getSource();
                if (anothersShowed.isSelected()) {
                    source.setOpacity(.5);
                    anothersShowed.setSelected(false);
                } else {
                    source.setOpacity(1);
                    anothersShowed.setSelected(true);
                }
            });
            automaticBtn.setOpacity(.5);
            Tooltip.install(automaticBtn,new Tooltip("Automatyczne dostosowywanie do d³ugoœci nominalnych.."));
            automaticBtn.setOnMousePressed(mouseEvent -> {
                ImageView source = (ImageView) mouseEvent.getSource();
                if (automaticRadialItem.isSelected()) {
                    source.setOpacity(.5);
                    automaticRadialItem.setSelected(false);
                } else {
                    source.setOpacity(1);
                    automaticRadialItem.setSelected(true);
                }
            });
            Tooltip.install(selectableBtn,new Tooltip("Zaznaczanie obiektów."));
            selectableBtn.setOnMousePressed(mouseEvent -> {
                ImageView source = (ImageView) mouseEvent.getSource();
                if (selection.isSelected()) {
                    source.setOpacity(.5);
                    selection.setSelected(false);
                } else {
                    source.setOpacity(1);
                    selection.setSelected(true);
                }
            });
            Tooltip.install(movableBtn,new Tooltip("Przesówanie warstwy prawym przyciskiem myszy."));
            movableBtn.setOnMousePressed(mouseEvent -> {
                ImageView source = (ImageView) mouseEvent.getSource();
                if (move.isSelected()) {
                    source.setOpacity(.5);
                    move.setSelected(false);
                } else {
                    source.setOpacity(1);
                    move.setSelected(true);
                }
            });





            automaticRadialItem.setSelected(false);
            selection.setSelected(true);
            move.setSelected(true);
            otherview.setSelected(true);
            layoutShowed.setSelected(true);
            splicesShowed.setSelected(true);
            edgeLabelsShowed.setSelected(true);
            componentsShowed.setSelected(true);
            anothersShowed.setSelected(true);


        }
    }

    private static List<Color> allColors() throws ClassNotFoundException, IllegalAccessException {
        List<Color> colors = new ArrayList<>();
        Class clazz = Class.forName("javafx.scene.paint.Color");
        Field[] field = clazz.getFields();
        for (Field f : field) {
            Object obj = f.get(null);
            if (obj instanceof Color) {
                colors.add((Color) obj);
            }

        }
        return colors;
    }

    public static Map<String, Color> allColorsWithName() throws ClassNotFoundException, IllegalAccessException {
        Map<String, Color> map = new HashMap<>();
        Class clazz = Class.forName("javafx.scene.paint.Color");
        Field[] field = clazz.getFields();
        for (Field f : field) {
            Object obj = f.get(null);
            if (obj instanceof Color) {
                map.put(f.getName(), (Color) obj);
            }

        }
        return map;
    }

    public void connectorToModuleDock(Cell cell, Module module, boolean screenTo){
        /**    relocate    *********************************************************/
        Platform.runLater(() -> {
            cell.relocate(
                    module.getBoundsInParent().getCenterX()-cell.getWidth()/2,
                    module.getBoundsInParent().getCenterY()-cell.getHeight()/2
            );
            cell.setModuleX(module.getModuleCordX());
            cell.setModuleY(module.getModuleCordY());
        });

        for (Wires wires : graph.getModel().getWiresData()){
            if (wires.getConneectorFromBase().get().equals(cell.getCellId())){
                wires.setFromXcode(module.getModuleXcode());
                System.out.println(wires.getName());
            }
            if (wires.getConneectorToBase().get().equals(cell.getCellId())){
                wires.setToXcode(module.getModuleXcode());
                System.out.println(wires.getName());
            }
        }

        //reloadTslDoc();

        cell.usedModuleProperty().set(module.getModuleXcode());
        cell.setXcode(module.getModuleXcode());

        if (screenTo) {
            graph.getScrollPane().setHvalue(cell.positionXProperty.doubleValue() / graph.getCellLayer().getWidth());
            graph.getScrollPane().setVvalue(cell.positionYProperty.doubleValue() / graph.getCellLayer().getHeight());
        }
        cell.dockedProperty.set(true);
    }

    @FXML
    public void mergeConnectors(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/view/application/MergeConnectors.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        MergeConnectorsControler mergeConnectorsControler = fxmlLoader.getController();
        mergeConnectorsControler.setConnectorsData(graph.getModel().getConnectorsData(),graph.getModel().getComponentsData(),graph,connectorsTableView);
        AnchorPane root = fxmlLoader.getRoot();
        root.getStylesheets().add("/resources/style/jmetro/JMetroDarkTheme.css");
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.showAndWait();
        //reloadTslDoc();

    }
    private String createTslDoc(){
        String tslDoc = "";
        for (Wires wire :  graph.getModel().getWiresData()){

            tslDoc = tslDoc + "TestConnection('"+wire.getName()+" C:" + wire.getColor()+" A:"+wire.getArea()+" L:"+wire.getLenght()+"', \""+wire.getFromXcode()+" "+wire.getFromPin()+"\", \""+wire.getToXcode()+" "+wire.getToPin()+"\");\n";
        }
        tslDoc = tslDoc + ".end";
        return tslDoc;
    }
//    public void reloadTslDoc(){
//        System.out.println("TSL reloaded.");
//        tslEditor.getCodeArea().clear();
//        tslEditor.getCodeArea().appendText("This document was created by HarnessDesignSystemInterfaceDiagram.\n");
//        tslEditor.getCodeArea().appendText(createTslDoc());
//    }

    @FXML
    private void refreshTables(){
        connectorsTableView.refresh();
        wiresTableView.refresh();
        modulesTable.refresh();
        componentTableView.refresh();
    }

    public Graph getGraph(){
        return graph;
    }

    public BooleanProperty getAutomaticProperty(){
        return automaticProperty;
    }

    @FXML
    public void tableToFileAction() throws IOException {
        ExportTableToExcel.ExportTableToExcelStatic.exportToExcel(connectorsTableView,"Konektory");
    }

}

