//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package DSI_Graph_Main_Controlers;

import Components.controlsFX13.org.controlsfx.control.table.TableFilter;
import Components.gn.decorator.GNDecorator;
import Components.gn.decorator.options.ButtonType;
import Components.hansolo.tilesfx.Section;
import Components.hansolo.tilesfx.Tile;
import Components.hansolo.tilesfx.TileBuilder;
import Components.net.raumzeitfalle.fx.filechooser.FXFileChooserStage;
import Components.net.raumzeitfalle.fx.filechooser.PathFilter;
import Components.net.raumzeitfalle.fx.filechooser.Skin;
import Components.net.raumzeitfalle.fx.filechooser.locations.Location;
import Components.net.raumzeitfalle.fx.filechooser.locations.Locations;
import Components.taskbar.TaskbarProgressbar;
import Components.taskbar.TaskbarProgressbarFactory;
import Components.trayNotification.notification.Notifications;
import Components.trayNotification.notification.TrayNotification;
import DSI_Graph_Main_Controlers.TableEdges.TableEdge;
import DSI_Graph_Main_Controlers.XML.ReadIvisionSP;
import DSI_Graph_Main_Controlers.XML.ReadLayoutXML_DOM;
import DSI_Graph_Main_Controlers.XML.SaveLayoutXML_DOM;
import Getway.TestersGetway;
import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Module;
import Model.*;
import WiresAnimation.WiresAnimation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Effect;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.Pair;
import org.apache.commons.lang3.time.DurationFormatUtils;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HWDiskStore;
import oshi.hardware.PhysicalMemory;
import oshi.software.os.OSSession;
import oshi.software.os.OperatingSystem;

import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class DSI_Model extends Application {

    private static final ObservableList<PICtoGIM_Model> PICtoGIM_models = FXCollections.observableArrayList();

    public static final ObservableList<Section1_Model> section1_models = FXCollections.observableArrayList();
    public static final ObservableList<Section3_Model> section3_models = FXCollections.observableArrayList();
    public static final ObservableList<Section4_Model> section4_models = FXCollections.observableArrayList();
    public static final ObservableList<Section5_Model> section5_models = FXCollections.observableArrayList();
    public static final ObservableList<Section6_Model> section6_models = FXCollections.observableArrayList();
    public static final ObservableList<Section7_Model> section7_models = FXCollections.observableArrayList();
    public static final ObservableList<Section9_Model> section9_models = FXCollections.observableArrayList();
    public static final ObservableList<Section10_Model> section10_models = FXCollections.observableArrayList();

    private static final ObservableList<TestersData> testersData = FXCollections.observableArrayList();

    public static BooleanProperty oneSecondBln = new SimpleBooleanProperty(false);

    static Image image = new Image("resources/image/cursors/1.png");
    static ImageCursor imageCursor = new ImageCursor(image, image.getWidth() /4, image.getHeight() /20);

    public static List<String> imagesListParts = new ArrayList<>();
    public static  String duration = null;

    Path local = Paths.get("F:\\HarnessTestersData\\TestPacket01");
    Path local2 = Paths.get("F:\\HarnessTestersData\\Pintable\\Ivision");


    @FXML
    private TableView<Section3_Model> ReferencesTable;
    @FXML
    private TableColumn<Section3_Model,String> ReferenceColumn;
    @FXML
    private TableColumn<Section3_Model,String> VersionColumn;
    @FXML
    private TableColumn<Section3_Model,String> DateColumn;
    @FXML
    private TableColumn<Section3_Model,String> VariantColumn;
    @FXML
    private TableColumn<Section3_Model,String> VariableColumn;

    @FXML
    private TableView<TestersData> TestersTable;
    @FXML
    private TableColumn<TestersData,String> IdColumn;
    @FXML
    private TableColumn<TestersData,String> NameColumn;
    @FXML
    private TableColumn<TestersData,String> ColumnsColumn;
    @FXML
    private TableColumn<TestersData,String> RowsColumn;



    @FXML
    private TabPane tabPane;
    @FXML
    private Tab mainTab;

    @FXML
    private Label cpu;
    @FXML
    private Label ram;
    @FXML
    private Pane cpuPane;
    @FXML
    private TitledPane cepuMemPane;


    @FXML
    private AnchorPane tabAnchorPane;

    @FXML
    private Button start;

    @FXML
    private AnchorPane mainTabAnhorPane;

    double width = 0;
    double height = 0;

    private static final Effect motionBlur = new MotionBlur();

    public static final HashMap<String,String> listOpenedNumbers = new HashMap<>();


    Runtime instance = Runtime.getRuntime();
    public final DecimalFormat df = new DecimalFormat("#.##");
    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    long lastTime = System.nanoTime();
    long lastThreadTime = threadMXBean.getCurrentThreadCpuTime();

    AnchorPane layoutView = null;
    public static TaskbarProgressbar taskbarProgressbar;

    public ObservableList<TestersData> getTestersData() {
        return testersData;
    }


    //public static SimpleDoubleProperty roundCross = new SimpleDoubleProperty(0);


    public static ObservableList<Section1_Model> getSection1_models() {
        return section1_models;
    }

    public static ObservableList<Section3_Model> getSection3_models() {
        return section3_models;
    }

    public static ObservableList<Section4_Model> getSection4_models() {
        return section4_models;
    }

    public static ObservableList<Section5_Model> getSection5_models() {
        return section5_models;
    }

    public static ObservableList<Section6_Model> getSection6_models() {
        return section6_models;
    }

    public static ObservableList<Section7_Model> getSection7_models() {
        return section7_models;
    }

    public static ObservableList<Section9_Model> getSection9_models() {
        return section9_models;
    }

    public static ObservableList<Section10_Model> getSection10_models() {
        return section10_models;
    }

    public static ObservableList<PICtoGIM_Model> getHousing_models() {
        return PICtoGIM_models;
    }

    public static SystemInfo systemInfo = new SystemInfo();

    public DSI_Model() {


        /** Enable OpenGL Acceleration and options         */
        System.setProperty("sun.java2d.opengl", "True");
        System.setProperty("sun.java2d.d3d", "True");
        System.setProperty("sun.java2d.ddoffscreen", "False");
        System.setProperty("sun.java2d.noddraw", "True");
        System.setProperty("sun.java2d.opengl.fbobject", "False");
        System.setProperty("sun.java2d.ddforcevram", "True");



        /** Enable FullSpeed Animate Acceleration and options         */
        System.setProperty("javafx.animation.fullspeed", "True");
/*
                -Dsun.java2d.d3d=false
                -Dsun.java2d.ddoffscreen=false
                -Dsun.java2d.noddraw=true
                -Dsun.java2d.opengl=true
                -Dsun.java2d.opengl.fbobject=false
                -Dsun.java2d.ddforcevram=true
*/
        /** Enable GPU Acceleration and options         */
        // System.setProperty("prism.verbose", "True");
        System.setProperty("prism.vsync", "True");
        System.setProperty("prism.hsync", "True");
        System.setProperty("prism.showoverdraw", "True");
//        System.setProperty("prism.forceGPU", "True");
//        System.setProperty("prism.order", "SW");
        /*
-Dprism.verbose=true
-Dprism.vsync=false
-Dprism.hsync=false
-Dprism.forceGPU=true
-Dprism.order=sw
-Dsun.java2d.opengl=true
-Dprism.showoverdraw=false
-Djavafx.animation.fullspeed=true
        * */


        System.out.println("PROCESORY CPU-----------------------------------------------------");
        System.out.println("Name: "+systemInfo.getHardware().getProcessor().getProcessorIdentifier().getName());
        System.out.println("Model: "+systemInfo.getHardware().getProcessor().getProcessorIdentifier().getModel());
        System.out.println("Physical Cores: "+systemInfo.getHardware().getProcessor().getPhysicalProcessorCount());
        System.out.println("Logical Cores: "+systemInfo.getHardware().getProcessor().getLogicalProcessorCount());
        System.out.println("VendorFreq(GHz): "+((double)systemInfo.getHardware().getProcessor().getMaxFreq()/(1000000000)));
        System.out.println("is 64bit: "+systemInfo.getHardware().getProcessor().getProcessorIdentifier().isCpu64bit());
        System.out.println("");
        System.out.println("PROCESORY GPU-----------------------------------------------------");
        for (GraphicsCard graphicsCard : systemInfo.getHardware().getGraphicsCards()){
            System.out.println("-Slot GPU------");
            System.out.println("Name: "+graphicsCard.getName());
            System.out.println("Device id: "+graphicsCard.getDeviceId());
            System.out.println("Video Memory(Mb): "+graphicsCard.getVRam()/(1024*1024) + " MB RAM");
        }
        System.out.println("");
        System.out.println("PAMIEC RAM-----------------------------------------------------");
        for (PhysicalMemory physicalMemory : systemInfo.getHardware().getMemory().getPhysicalMemory()){
            System.out.println("-Slot PAMIEC------");
            System.out.println("Bank: "+physicalMemory.getBankLabel());
            System.out.println("Type(DDR#): "+physicalMemory.getMemoryType());
            System.out.println("Clock Speed(GHZ): "+((double)physicalMemory.getClockSpeed()/(1000000000)));
            System.out.println("Memory(Mb): "+physicalMemory.getCapacity()/(1024*1024) + " MB RAM");
        }

        System.out.println("");
        System.out.println("PAMIEC DYSKOWA-----------------------------------------------------");
        for (HWDiskStore diskStore : systemInfo.getHardware().getDiskStores()){
            System.out.println("-Dysk/PAMIEC------ "+diskStore.getName());
            System.out.println(diskStore.getSize()/(1024*1024) + " MB Store");
        }

        //System.setProperty("sun.java2d.opengl", "True");
        //System.setProperty("prism.lcdtext", "False");

//        File adapter_file = new File("F:\\ADAPTERS.csv");
//        List<String> lines_a = Files.readAllLines(Paths.get(adapter_file.getAbsolutePath()),Charset.forName("windows-1250"));
//        String[] variables_adapter;
//        for (String line : lines_a){
//            variables_adapter = line.replace("\"","").split(";");
//            housing_models.add(new Housing_Model(variables_adapter[1],variables_adapter[2],variables_adapter[3]));
//        }

//        File housing_file = new File("F:\\HOUSING.csv");
//        List<String> lines_h = Files.readAllLines(Paths.get(housing_file.getAbsolutePath()),Charset.forName("windows-1250"));
//        String[] variables_housing;
//        for (String line : lines_h){
//            variables_housing = line.replace("\"","").split(";");
//            housing_models.add(new Housing_Model(variables_housing[1],variables_housing[2],variables_housing[3]));
//        }



//        DBModel dbModel = new DBModel();
//        dbModel.createDataBase();
    }



    GNDecorator window = new GNDecorator();
    Stage primaryStage;
    Image isRunningImgGrey = new Image("/resources/icon/pearl_grey_32x32.png");
    Image isRunningImgGreen = new Image("/resources/icon/pearl_green_32x32.png");
    Image isRunningImgYellow = new Image("/resources/icon/pearl_yellow_32x32.png");
    public static boolean proccesRunning = false;
    @FXML
    public void start(Stage dprimaryStage) {

//        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
//            Alert.AlertType alertAlertType = AlertType.ERROR;
//
//
//            Alert alert = new Alert(alertAlertType);
//            alert.setTitle("Exception Stack Trace Inspector");
//
//
//            alert.setHeaderText("Detected: "+e.getMessage()+" in Class: "+e.getStackTrace()[0].getClassName()+" in Method: "+e.getStackTrace()[0].getMethodName());
//
//            StringWriter sw = new StringWriter();
//            PrintWriter pw = new PrintWriter(sw);
//            e.printStackTrace(pw);
//
//            String exceptionText = sw.toString();
//
//
//
//            Label label = new Label("The exception stacktrace was:");
//
//            TextArea textArea = new TextArea(exceptionText);
//            textArea.setEditable(false);
//            textArea.setWrapText(true);
//
//            textArea.setMaxWidth(Double.MAX_VALUE);
//            textArea.setMaxHeight(Double.MAX_VALUE);
//            GridPane.setVgrow(textArea, Priority.ALWAYS);
//            GridPane.setHgrow(textArea, Priority.ALWAYS);
//
//            GridPane expContent = new GridPane();
//            expContent.setMaxWidth(Double.MAX_VALUE);
//            expContent.add(label, 0, 0);
//            expContent.add(textArea, 0, 1);
//
//
//            alert.getDialogPane().setExpandableContent(expContent);
//
//            alert.getDialogPane().setStyle("-fx-base:black;");
//            Stage alertStage = (Stage)alert.getDialogPane().getScene().getWindow();
//            alertStage.setAlwaysOnTop(true);
//            alertStage.getIcons().add(new Image("/resources/images/fico_64.png"));
//            taskbarProgressbar.showCustomProgress(1, TaskbarProgressbar.Type.ERROR);
//            alert.showAndWait();
//            taskbarProgressbar.showCustomProgress(0, TaskbarProgressbar.Type.NO_PROGRESS);
//
//        });





        WiresAnimation gui; gui = new WiresAnimation();
        try {
            gui.buildComponents();
        } catch (IOException ex) {
            Logger.getLogger(DSI_Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        gui.animate();

        Stage loadingstage = new Stage();
        loadingstage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader loader_p = new FXMLLoader();
        loader_p.setLocation(DSI_Model.class.getResource("/view/application/Preloader.fxml"));
        AnchorPane loadingView;
        try {
            loadingView = loader_p.load();
            Scene scene = new Scene(loadingView);
            scene.setFill(Color.TRANSPARENT);
            loadingstage.setScene(scene);
            loadingView.getChildren().add(gui.getScene());

        } catch (IOException var7) {
            var7.printStackTrace();
        }
        Preloader preloader = loader_p.getController();
        loadingstage.getIcons().add(new Image("/resources/icon/today.png"));
        loadingstage.show();
        loadingstage.setAlwaysOnTop(true);
        Task task_Loading = new Task() {
            @Override
            protected Object call() throws IOException {


                Platform.runLater(() -> preloader.setText("Ladowanie GPN."));
                File all_file = new File("F:\\HarnessTestersData\\ALL.csv");
                List<String> lines_all = Files.readAllLines(Paths.get(all_file.getAbsolutePath()), Charset.forName("windows-1250"));
                String[] variables_all;

                double unit = 1.0/lines_all.size();
                double progress = 0.0;
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (String line : lines_all) {
                    variables_all = line.replace("\"", "").split(";");
                    PICtoGIM_models.add(new PICtoGIM_Model(variables_all[1], variables_all[2], variables_all[3]));

                    progress=progress+unit;

                    if (progress == .1 || progress == .2 || progress == .3 || progress == .4 || progress == .5 || progress == .6 || progress == .7 || progress == .8 || progress == .9 || progress == 1)
                    {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("modulo");
                    }
                    double finalProgress = progress;
                    preloader.getProgressBar().setProgress(finalProgress);
                }

                try {
                    File root = new File("W:\\WiseDok\\Komponenty\\Foto");

                    //  boolean recursive = true;
                    // String[] extensions = {"JPG", "jpg", "PNG", "png"};
                    // Collection files = FileUtils.listFiles(root, extensions, recursive);
                    System.out.println("progress2");
                    double unit2 = 100.0/ Objects.requireNonNull(root.list()).length;
                    double progress2 = 0.0;
                    for (String file : Objects.requireNonNull(root.list())) {
                        //File file = (File) iterator.next();
                        Platform.runLater(() -> preloader.setText("Ladowanie Obrazów GPN."));
                        imagesListParts.add(root+"\\"+file/*((File)file).getAbsolutePath()*/);

                        progress2=progress2+unit2;

                        if (progress2 < .1 || progress2 < .2 || progress2 < .3 || progress2 < .4 || progress2 < .5 || progress2 < .6 || progress2 < .7 || progress2 < .8 || progress2 < .9 || progress2 < 1)
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        System.out.println(progress2);
                        double finalProgress = progress2;
                        preloader.getProgressBar().setProgress(finalProgress);
                    }
                    //files.clear();
                }catch (Exception ignored){}

                return null;
            }

        };

        task_Loading.setOnSucceeded(workerStateEvent -> {
            loadingstage.close();
            initializeWindow();
        });
        Thread th = new Thread(task_Loading);
        th.setName("LoadData");
        th.setPriority(Thread.MAX_PRIORITY);
        th.setDaemon(true);
        th.start();
        //th.interrupt();




    }

    public void initializeWindow(){

        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DSI_Model.class.getResource("/view/application/MainPageView.fxml"));
            AnchorPane mainView = null;

            try {
                mainView = loader.load();
            } catch (IOException var7) {
                var7.printStackTrace();
            }
            window.setContent(mainView);


            Image iconStage = new Image("/resources/image/ico.png");


            ImageView iconImg = new ImageView(iconStage);
            iconImg.setFitWidth(16);
            iconImg.setFitHeight(16);



            window.addButton(ButtonType.FULL_EFFECT);



            Menu menu1 = new Menu("Ustawienia");
            CheckMenuItem dualDesktopMode = new CheckMenuItem("Tryb DualDesktop");
            dualDesktopMode.selectedProperty().addListener((observableValue, aBoolean, t1) -> {

                try {
                    Platform.runLater(() -> {

                        Stage stage = (Stage) window.getStage().getScene().getWindow();




                        Screen display1 = null;
                        Screen display2 = null;

                        int dispInc = 0;
                        for (Screen screen : Screen.getScreens()) {

                            if (dispInc == 0) {
                                display1 = screen;
                            }
                            if (dispInc == 1) {
                                display2 = screen;
                            }
                            dispInc++;
                        }

                        //System.out.println("Display1: "+display1);
                        //System.out.println("Display2: "+display2);

                        if (display2 != null) {
                            if (t1) {


                                stage.setMaximized(false);
                                stage.setResizable(false);

                                stage.setWidth(display1.getBounds().getWidth() + display2.getBounds().getWidth());

                                stage.setHeight(Math.min(display1.getVisualBounds().getMaxY(), display2.getVisualBounds().getMaxY()));
                                stage.setX(-1 * display2.getBounds().getWidth());
                                stage.setY(display2.getVisualBounds().getMinY());
                            } else {
                                stage.setWidth(width);
                                stage.setHeight(height);

                                stage.setMaximized(true);
                                stage.setResizable(true);
                            }
                        }else{
                            TrayNotification trayNotification = new TrayNotification();
                            trayNotification.setNotification(Notifications.WARNING);
                            //trayNotification.setMessage("Wykryto tylko jeden monitor.");
                            trayNotification.setMessage2("Pod??cz drugi monitor by skorzysta? z tej funkcji.");
                            trayNotification.setTitle("Wykryto tylko jeden monitor.");
                            trayNotification.showAndDismiss(Duration.seconds(9));
                            dualDesktopMode.selectedProperty().set(false);
                        }

                    });

                }catch (Exception e){e.printStackTrace();}

            });
            dualDesktopMode.setAccelerator(KeyCombination.keyCombination("Ctrl+M"));
            menu1.getItems().add(dualDesktopMode);

            CheckMenuItem mainConfiguration = new CheckMenuItem("Konfiguracja");
            mainConfiguration.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
                FXMLLoader loader_p = new FXMLLoader();
                loader_p.setLocation(DSI_Model.class.getResource("/view/application/MainConfiguration.fxml"));
                AnchorPane loadingView;
                try {
                    loadingView = loader_p.load();
                    Scene scene = new Scene(loadingView);
                    scene.setFill(Color.TRANSPARENT);
                    Stage configStage = new Stage();
                    configStage.initStyle(StageStyle.UNDECORATED);
                    configStage.setScene(scene);
                    configStage.show();


                } catch (IOException var7) {
                    var7.printStackTrace();
                }


            });
            mainConfiguration.setAccelerator(KeyCombination.keyCombination("Ctrl+K"));
            menu1.getItems().add(mainConfiguration);

            Menu menu2 = new Menu("Informacje");
            MenuItem info = new MenuItem("O programie, Licencja");
            info.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.getDialogPane().getStylesheets().add("/resources/style/jmetro/combination.css");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.initStyle(StageStyle.TRANSPARENT);
                alert.setTitle("NInformacje..");
                alert.setHeaderText("Informacje oraz licencja.");

                alert.setContentText("""
                                 Ten program s³u¿y do projektowania layoutów urz¹dzeñ testuj¹cych, analizy pokrycia konektorów z modu³ami, generowania uk³¹dów po³¹czeñ, wizualizacji wi¹zki elektrycznej na p³¹szczyŸnie 2D.
                                Wszelkie u¿ycie oprogramowania, rozpowszechnianie, przechowywanie na noœnikach pamiêci, modyfikacja kodu bez zgody pisemnej autora* prowadzi do podjêcia kroków prawnych.
                                *Autor: Mateusz Maci¹g
                                Email: mateusz.macig@gmail.com"""
                );

                alert.showAndWait();
            });
            menu2.getItems().add(info);
            MenuItem jconsole = new MenuItem("jConsole 11.0.1+13-LTS");
            jconsole.setOnAction(event -> {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop desktop = Desktop.getDesktop();
                        File myFile = new File("F:\\Tools\\jconsole.jar");
                        desktop.open(myFile);
                    } catch (IOException ex) {}
                }


            });
            menu2.getItems().add(jconsole);



            Menu btn_ico = new Menu();

            MenuItem openDSI = new MenuItem("Otw?rz plik DSI");
            openDSI.setOnAction(event -> loadDsiFile());
            openDSI.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));

            Menu paste = new Menu("Wklej");
            MenuItem pasteDSI = new MenuItem("");
            VBox dsiPastePane = new VBox();
            TextArea dsiPasteTextArea = new TextArea();
            dsiPasteTextArea.getStyleClass().add("table-view-small");
            Button dsiPasteButton = new Button("Za?aduj");
            dsiPastePane.getChildren().addAll(new Label("Wklej dsi:"),dsiPasteTextArea,dsiPasteButton);
            pasteDSI.setGraphic(dsiPastePane);

            final int[] standard = new int[1];
            dsiPasteTextArea.textProperty().addListener((observableValue, s, t1) -> {

                if (
                        t1.contains("Bundle Drawing Name")
                                && t1.contains("Section 1:")
                                && t1.contains("%Section 2:")
                                && t1.contains("%Section 3:")
                                && t1.contains("%Section 4:")
                                && t1.contains("%Section 5:")
                                && t1.contains("%Section 6:")
                                && t1.contains("%Section 7:")
                                && t1.contains("%Section 8:")
                                && t1.contains("%Section 9:")
                                && t1.contains("%Section 10:")
                                && t1.contains("%Section 11:")
                                && t1.contains("%Section 12:")
                                && t1.contains("%Section 13:")
                                && t1.contains("%Section 14:")
                                && t1.contains("%Section 15:")
                                && t1.contains("%Section 16:")
                                && t1.contains("%Section 17:")
                                && t1.contains("%Section 18:")
                ) {
                    dsiPasteTextArea.setStyle("-fx-border-color: green;");
                    dsiPasteButton.setText("Za?aduj");
                    dsiPasteButton.setDisable(false);
                    standard[0] = 1;
                }else if (
                        t1.contains("!***  Created by:  VeSys Harness")
                                && t1.contains("!   Harness description information")
                                && t1.contains("%   Harness notes")
                                && t1.contains("%   Harness circuit information")
                                && t1.contains("%   Harness branch configuration")
                                && t1.contains("%   Harness wire specification")
                                && t1.contains("%   Harness node components")
                                && t1.contains("!   Harness cavity components")
                                && t1.contains("!   Harness wire components")
                                && t1.contains("!   Harness branch components")
                                && t1.contains("%   Harness branch insulations")
                                && t1.contains("%   Harness centre strips")
                                && t1.contains("%   Harness multicore specification")
                                && t1.contains("%   Module Children")
                                && t1.contains("%   Module Compatibility Table")
                                && t1.contains("%   Manual BOM Quantities")
                                && t1.contains("%   Composite Options")
                                && t1.contains("%   Wire Through Nodes")
                                && t1.contains("%   Branch Insulation Through Nodes")
                                && t1.contains("%   Mid Wire Components")
                                && t1.contains("%   Wire / Multicore Markers")
                                && t1.contains("%   Pin Mappings")
                                && t1.contains("%   End of file marker")
                ){
                    dsiPasteTextArea.setStyle("-fx-border-color: green;");
                    dsiPasteButton.setText("Za?aduj");
                    dsiPasteButton.setDisable(false);
                    standard[0] = 2;

                }else if (t1.equals("")){
                    dsiPasteTextArea.setStyle("-fx-border-color: transparent;");
                    dsiPasteButton.setText("Za?aduj");
                    dsiPasteButton.setDisable(true);
                } else {
                    dsiPasteTextArea.setStyle("-fx-border-color: firebrick;");
                    dsiPasteButton.setText("Za?aduj (DSI mo?e by? niekompatybilne)");
                    dsiPasteButton.setDisable(false);
                    standard[0] = 3;
                }

            });

            dsiPasteButton.setOnAction(event -> {
                pasteDsi(dsiPasteTextArea.getText(),standard[0]);
                dsiPasteTextArea.setText("");
            });



            MenuItem openLDDesign = new MenuItem("Otw?rz plik LDDesign");
            openLDDesign.setOnAction(event -> loadLDDesignFile());
            openLDDesign.setAccelerator(KeyCombination.keyCombination("Ctrl+L"));
            paste.getItems().addAll(pasteDSI);

            MenuItem loadLayout = new MenuItem("Za?aduj Layout");
            loadLayout.setAccelerator(KeyCombination.keyCombination("Ctrl+T"));
            loadLayout.setOnAction(event -> addTesterData());

            MenuItem loadLayoutIvision = new MenuItem("Za³aduj Layout IvisionStudio");
            loadLayoutIvision.setAccelerator(KeyCombination.keyCombination("Ctrl+I"));
            loadLayoutIvision.setOnAction(event -> addTesterDataIvision());


            Menu openMain = new Menu("Plik");
            openMain.getItems().addAll(openDSI,openLDDesign,paste,loadLayout,loadLayoutIvision);
            btn_ico.getItems().add(openMain);




            //btn_ico.getItems().add();


            MenuItem convertToLayoutFile = new MenuItem("Konwertuj do Pliku Siatki Layoutu");
            convertToLayoutFile.setOnAction(event -> convertTxtToXmlLayout());
            convertToLayoutFile.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
            Menu tools = new Menu("Narz?dzia");
            tools.getItems().add(convertToLayoutFile);

            btn_ico.getItems().add(tools);

            MenuItem close = new MenuItem("WyjdŸ");
            close.setOnAction(event -> System.exit(0));
            btn_ico.getItems().add(close);
            btn_ico.setGraphic(iconImg);

            window.addMenu(0,btn_ico);
            window.addMenu(1,menu1);
            window.addMenu(2,menu2);
            window.setLogo(isRunningImgGreen);
            window.setMaximized(true);
            //window.initTheme(GNDecorator.Theme.DARK);
            //window.getStage().getScene().getStylesheets().add("/resources/style/jmetro/dark_theme.css");
            window.getStage().getScene().getStylesheets().add("/resources/style/jmetro/JMetroDarkTheme.css");

            window.setColor(Color.web("#2E2E2E"));

            window.setTitle("   Harness Design System Interface Diagram (v.0.01.02.2020 Alpha) ");

            window.getContent().setCursor(imageCursor);



            window.maximizedProperty().addListener((observableValue, aBoolean, t1) -> {
                if (aBoolean){
                    window.getContent().setStyle("-fx-border-color:#2E2E2E;-fx-border-width: 0 2 2 2;");
                }else{
                    window.getContent().setStyle("-fx-border-color:#2E2E2E;-fx-border-width: 0 0 0 0;");
                }
            });

            window.show();
            taskbarProgressbar = TaskbarProgressbarFactory.getTaskbarProgressbar(window.getStage());
            taskbarProgressbar.showCustomProgress(0, TaskbarProgressbar.Type.NO_PROGRESS);
//            getTestersData().addListener((InvalidationListener) change -> {
//                //System.out.println("Added");
//                Platform.runLater(() -> {});
//
//            });


            width = window.getStage().getWidth();
            height = window.getStage().getHeight();
            this.primaryStage = window.getStage();
            primaryStage.getIcons().addAll(iconStage);
        });



    }

    public void addTesterData(){
        PathFilter all = PathFilter.acceptAllFiles("Wszystkie pliki");

        PathFilter txt = PathFilter.forFileExtension("Plik Siatki Layoutu (*.xmlLayout)", "xmllayout");


        FXFileChooserStage fc = null;
        try {
            fc = FXFileChooserStage
                    .create(Skin.MODENA, local, txt, all);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Location> locations = new ArrayList<>();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] drives = File.listRoots();
        if (drives != null && drives.length > 0) {
            for (File aDrive : drives) {
//                //System.out.println("Drive Letter: " + aDrive);
//                //System.out.println("\tType: " + fsv.getSystemTypeDescription(aDrive));
//                //System.out.println("\tTotal space: " + aDrive.getTotalSpace());
//                //System.out.println("\tFree space: " + aDrive.getFreeSpace());
//                //System.out.println();
                locations.add(Locations.withName(fsv.getSystemTypeDescription(aDrive) + " '" + aDrive + "'  Rozmiar: " + aDrive.getTotalSpace()/(1024*1024*1024) + " Gb   Wolne miejsce: " + aDrive.getFreeSpace()/(1024*1024*1024)+" Gb", Paths.get(String.valueOf(aDrive))));
            }
        }


        if (fc != null) {
            fc.addLocations(locations);
        }
        fc.setTitleText("Wybierz plik Siatki Layoutu");
        Platform.runLater(() -> window.getContent().setEffect(motionBlur));
        Optional<Path> selection = fc.showOpenDialog(primaryStage);
        Platform.runLater(() -> window.getContent().setEffect(null));
        selection.ifPresent(path -> {
            TestersData layoutsData = new ReadLayoutXML_DOM().readLayoutXML(path);
            getTestersData().add(layoutsData);
        });
    }
    public void addTesterDataIvision(){
        PathFilter all = PathFilter.acceptAllFiles("Wszystkie pliki");

        PathFilter txt = PathFilter.forFileExtension("Plik Pintabeli Programu IvisionStudio (*.pintable)", "pintable");


        FXFileChooserStage fc = null;
        try {
            fc = FXFileChooserStage
                    .create(Skin.MODENA, local2, txt, all);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Location> locations = new ArrayList<>();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] drives = File.listRoots();
        if (drives != null && drives.length > 0) {
            for (File aDrive : drives) {
//                //System.out.println("Drive Letter: " + aDrive);
//                //System.out.println("\tType: " + fsv.getSystemTypeDescription(aDrive));
//                //System.out.println("\tTotal space: " + aDrive.getTotalSpace());
//                //System.out.println("\tFree space: " + aDrive.getFreeSpace());
//                //System.out.println();
                locations.add(Locations.withName(fsv.getSystemTypeDescription(aDrive) + " '" + aDrive + "'  Rozmiar: " + aDrive.getTotalSpace()/(1024*1024*1024) + " Gb   Wolne miejsce: " + aDrive.getFreeSpace()/(1024*1024*1024)+" Gb", Paths.get(String.valueOf(aDrive))));
            }
        }


        if (fc != null) {
            fc.addLocations(locations);
        }
        fc.setTitleText("Wybierz plik SP");
        Platform.runLater(() -> window.getContent().setEffect(motionBlur));
        Optional<Path> selection = fc.showOpenDialog(primaryStage);
        Platform.runLater(() -> window.getContent().setEffect(null));
        selection.ifPresent(path -> {
            ReadIvisionSP readIvisionSP = new ReadIvisionSP();
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("");
            dialog.setHeaderText("Wprowad? ilo?? kolumn oraz wierszy");
            Stage stage2 = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage2.initStyle(StageStyle.TRANSPARENT);
            dialog.getDialogPane().getStylesheets().add("/resources/style/jmetro/combination.css");


            javafx.scene.control.ButtonType saveButtonType = new javafx.scene.control.ButtonType("Zatwierdz", ButtonBar.ButtonData.OK_DONE);
            javafx.scene.control.ButtonType cancelButtonType = new javafx.scene.control.ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField cols = new TextField("72");
            cols.setPromptText("Ilo?? Kolumn");
            TextField rows = new TextField("24");
            rows.setPromptText("Ilo?? Wierszy");
//                                TextField rows = new TextField();
//                                rows.setPromptText("Ilo?? Wierszy");

            grid.add(new Label("Ilo?? Kolumn:"), 0, 0);
            grid.add(cols, 1, 0);
            grid.add(new Label("Ilo?? Wierszy:"), 0, 1);
            grid.add(rows, 1, 1);
//                                grid.add(new Label("Ilo?? Wierszy:"), 0, 2);
//                                grid.add(rows, 1, 2);

            Node loginButton = dialog.getDialogPane().lookupButton(saveButtonType);
            loginButton.setDisable(true);


            cols.textProperty().addListener((observable, oldValue, newValue) -> loginButton.setDisable(newValue.trim().isEmpty()));

            dialog.getDialogPane().setContent(grid);

            Platform.runLater(cols::requestFocus);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButtonType) {
                    return new Pair<>(cols.getText(), rows.getText());
                }
                return null;
            });

            Platform.runLater(() -> window.getContent().setEffect(motionBlur));
            Optional<Pair<String, String>>  result = dialog.showAndWait();
            Platform.runLater(() -> window.getContent().setEffect(null));
            if (result.isPresent()) {
                TestersData layoutsData = readIvisionSP.getPintable(path,cols.getText(),rows.getText());
                getTestersData().add(layoutsData);
            }

        });
    }
    @Deprecated
    public double cpuLoad(){


        float smoothLoad = 0;

        // Calculate coarse CPU usage:
        long time = System.nanoTime();
        long threadTime = threadMXBean.getCurrentThreadCpuTime();
        double load = (threadTime - lastThreadTime) / (double)(time - lastTime);
        // Smooth it.
        smoothLoad += (load - smoothLoad)*100; // damping factor, lower means less responsive, 1 means no smoothing.
        ////System.out.println(smoothLoad);

        // For next iteration.
        lastTime = time;
        lastThreadTime = threadTime;
        return smoothLoad;

    }
    static long[] prevTicks = new long[CentralProcessor.TickType.values().length];
    public static double getCPUSystem()
    {
        double cpuLoad = systemInfo.getHardware().getProcessor().getSystemCpuLoadBetweenTicks( prevTicks ) * 100;
        prevTicks = systemInfo.getHardware().getProcessor().getSystemCpuLoadTicks();
        //System.out.println("cpuLoad : " + cpuLoad);
        return cpuLoad;
    }

    @FXML
    public void initialize() {


        Platform.runLater(()-> {
            TableFilter<Section3_Model> tableFilterReferences = new TableFilter<>(ReferencesTable);
        });
        ReferencesTable.setOnMouseMoved(mouseEvent -> start.setLayoutY(mouseEvent.getY()));
        TestersTable.setOnMouseMoved(mouseEvent -> start.setLayoutY(mouseEvent.getY()));
        tabAnchorPane.setStyle("-fx-background-color:transparent;");

        int mb = 1024 * 1024;
        Tile CPUgaugeSparkLineTile = TileBuilder.
                create()
                .skinType(Tile.SkinType.GAUGE_SPARK_LINE)
                .prefSize(150.0D, 150.0D)
                .title("U¿ycie Procesora ")
                .animated(true)
                .textVisible(false)
                .averagingPeriod(25)
                .autoReferenceValue(true)
                .barColor(Tile.YELLOW_ORANGE)
                .barBackgroundColor(Color.rgb(255, 255, 255, 0.1D))
                .sections(new Section(0.0D, 33.0D, Tile.FORESTGREEN), new Section(33.0D, 67.0D, Tile.YELLOW_ORANGE), new Section(67.0D, 100.0D, Tile.LIGHT_RED))
                .sectionsVisible(true).highlightSections(true).strokeWithGradient(true).gradientStops(new Stop(0.0D, Tile.FORESTGREEN), new Stop(0.33D, Tile.BLUE), new Stop(0.33D, Tile.YELLOW), new Stop(0.67D, Tile.YELLOW), new Stop(0.67D, Tile.LIGHT_RED), new Stop(1.0D, Tile.LIGHT_RED))
                .build();


        Tile RAMsparkLineTile = TileBuilder.create()
                .skinType(Tile.SkinType.STOCK)
                .prefSize(200, 200)
                .minWidth(250)
                .title("RAM")
                .unit("mb")
                .minValue(0)
                .barColor(Color.FORESTGREEN)
                .maxValue(((double) systemInfo.getHardware().getMemory().getTotal()/mb))
                .oldValueVisible(true)
                .tickLabelsYVisible(true)
                .thresholdVisible(true)
                .averagingPeriod(100)
                .build();

        cpuPane.getChildren().addAll(CPUgaugeSparkLineTile,RAMsparkLineTile);
        cepuMemPane.setText("CPU: "+systemInfo.getHardware().getProcessor().getProcessorIdentifier().getName()+" RAM:"+systemInfo.getHardware().getMemory().getTotal()/mb+" Mb");



        final int[] i1 = {0};
        final int[] i2 = {0};
        final int[] i3 = {0};
        final int[] i4 = {0};
//        final int coresCPU = Runtime.getRuntime().availableProcessors();
        final double[] cpuUsage = {0};
        final double[] memUsage = {0};
        final double[] maxMem = {0};
        final LocalDateTime startTime = LocalDateTime.now();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(10), e -> {

//                    if (roundCross.get() == 360)
//                        roundCross.set(0);

                    if (i4[0] == 35) {
                        cpuUsage[0] = cpuLoad();
                        i4[0] = 0;
                    }
                    if (i1[0] == 10) {

                        // window.getTitle()   Harness Design System Interface Diagram (v.0.01.02.2020 Alpha) "+System.currentTimeMillis()

//                            if (!Thread.getAllStackTraces().isEmpty()){
//                                Alert.AlertType alertAlertType = AlertType.ERROR;
//                                Alert alert = new Alert(alertAlertType);
//                                alert.setContentText(Thread.getAllStackTraces().values().stream().toString());
//                                alert.show();
//                            }
                        memUsage[0] = ((double)(instance.totalMemory() - instance.freeMemory())/mb);
                        maxMem[0] = ((double) instance.totalMemory() / mb);
                        CPUgaugeSparkLineTile.setValue(cpuUsage[0]);
                        CPUgaugeSparkLineTile.setTitle("Uzycie Procesora ("+systemInfo.getHardware().getProcessor().getPhysicalProcessorCount()+"rdzenie): "+(int)CPUgaugeSparkLineTile.getValue()+"%");
                        RAMsparkLineTile.setMaxValue(maxMem[0]);

                        RAMsparkLineTile.setValue(memUsage[0]);

                        if (RAMsparkLineTile.getOldValue()-RAMsparkLineTile.getValue()>0){
                            RAMsparkLineTile.setBarColor(Color.FORESTGREEN);
                        }if (RAMsparkLineTile.getOldValue()-RAMsparkLineTile.getValue()==0){
                            RAMsparkLineTile.setBarColor(Color.ORANGERED);
                        }if (RAMsparkLineTile.getOldValue()-RAMsparkLineTile.getValue()<0){
                            RAMsparkLineTile.setBarColor(Color.FIREBRICK);
                        }
                        //Platform.runLater(() -> {
                        //ram.setText("RAM: "+((instance.totalMemory() - instance.freeMemory()) / mb)+"/"+instance.totalMemory() / mb + "mb");
                        //cpu.setText("CPU: "+ df.format(cpuLoad()) + "%");
                        // });
                        i1[0] = 0;
                    }
//                        if (i2[0] == 10) {
//                            System.gc();
//                            System.runFinalization();
//                            i2[0] = 0;
//                        }
                    if (i3[0] == 50 ){
                        if (!proccesRunning)
                            window.getViewLogo().setImage(isRunningImgYellow);

                        java.time.Duration duration = java.time.Duration.between(startTime,LocalDateTime.now());
                        this.duration = DurationFormatUtils.formatDuration(duration.toMillis(), "HH:mm:ss", true);
                        window.getLogoBtn().setText("Sesja: "+DurationFormatUtils.formatDuration(duration.toMillis(), "HH:mm:ss", true));

                    }
                    if (i3[0] == 100){
                        if (!proccesRunning) {
                            window.getViewLogo().setImage(isRunningImgGrey);
                        }else {
                            window.getViewLogo().setImage(isRunningImgGreen);
                        }
                        oneSecondBln.set(!oneSecondBln.get());
                        i3[0] = 0;
                    }

                    i1[0]++;
                    //   i2[0]++;
                    i3[0]++;
                    i4[0]++;

                    // roundCross.set(roundCross.get()+10);
                }, new javafx.animation.KeyValue[]{})
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

//        Task t = new Task(){
//
//            @Override
//            protected Object call() {
//
//                while (true){
//
//                }
//                // return null;
//            }
//        };
//
//        final Thread th = new Thread(t);
//        th.setPriority(Thread.MIN_PRIORITY);
//        th.setDaemon(true);
//        th.start();

        TestersTable.setItems(getTestersData());
        IdColumn.setCellValueFactory(cellData -> cellData.getValue().TesterIDProperty());
        NameColumn.setCellValueFactory(cellData -> cellData.getValue().testerNameProperty());
        ColumnsColumn.setCellValueFactory(cellData -> cellData.getValue().getTesterColumnsProperty());
        RowsColumn.setCellValueFactory(cellData -> cellData.getValue().getTesterRowsProperty());
        getTestersData().add(new TestersData("Pusta_Warstwa", "Pusty_0x0", "0", "0"));
        for (int i = 12;i<=72;i+=12) {
            getTestersData().add(new TestersData("Wirtualny_"+i+"", "Pusty_"+i+"x12", ""+i, "12"));
            getTestersData().add(new TestersData("Wirtualny_"+i+"_L", "Pusty_"+i+"x24", ""+i, "24"));
        }
        new TestersGetway().view(getTestersData());
        Platform.runLater(() -> {
            TableFilter<TestersData> tableFilterTesters = new TableFilter<>(TestersTable);
        });

        ReferencesTable.setItems(section3_models);
        ReferenceColumn.setCellValueFactory(new PropertyValueFactory<>("Reference"));
        VersionColumn.setCellValueFactory(new PropertyValueFactory<>("Version"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        VariantColumn.setCellValueFactory(new PropertyValueFactory<>("Variant"));
        VariableColumn.setCellValueFactory(new PropertyValueFactory<>("Variable"));

        for (int i = 0; i <= 38; i++){
            section3_models.add(new Section3_Model("-","-","-","-","-     "+i));
        }



        Platform.runLater(() -> {
            this.ReferencesTable.getScene().setOnDragOver(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    if (db.getFiles().get(0).getName().contains(".txt") || db.getFiles().get(0).getName().contains(".xlsx")){
                        event.acceptTransferModes(TransferMode.COPY);
                        tabPane.getSelectionModel().select(mainTab);
                    }
                } else {
                    event.consume();
                }
            });

            // Dropping over surface
            this.ReferencesTable.getScene().setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    String filePath = null;
                    for (File file:db.getFiles()) {
                        filePath = file.getAbsolutePath();
                        //System.out.println(filePath);
                        if (db.getFiles().get(0).getName().contains(".txt")) {
                            try {
                                CheckDsiFile(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            });
        });


        TableEdge tableEdge = new TableEdge(new SimpleDoubleProperty(0),new SimpleDoubleProperty(0),new SimpleDoubleProperty(110),new SimpleDoubleProperty(110),100,3,Color.YELLOWGREEN);
        Button start2 = new Button("Start");
        start2.setOnAction(event -> startAnalise());
        start2.setDefaultButton(true);
        tableEdge.setStart(start2);
        mainTabAnhorPane.getChildren().add(tableEdge);
        tableEdge.setVisible(false);

        AtomicInteger visableTableEdge1 = new AtomicInteger();
        AtomicInteger visableTableEdge2 = new AtomicInteger();
        ReferencesTable.setRowFactory(tv -> {
            TableRow<Section3_Model> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    tableEdge.setSourceX(row.getBoundsInParent().getMaxX());
                    tableEdge.setSourceY(row.getLayoutY()+(2*row.getHeight()));
                    visableTableEdge1.getAndIncrement();
                    if (visableTableEdge1.get() >=1 && visableTableEdge2.get() >=1)
                        tableEdge.setVisible(true);
                }
            });
            return row;
        });
        TestersTable.setRowFactory(tv -> {
            TableRow<TestersData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    tableEdge.setTargetX(TestersTable.getParent().getLayoutX());
                    tableEdge.setTargetY(row.getLayoutY()+(2*row.getHeight()));
                    visableTableEdge2.getAndIncrement();
                    if (visableTableEdge1.get() >=1 && visableTableEdge2.get() >=1)
                        tableEdge.setVisible(true);
                }
            });
            return row;
        });



    }

    public void pasteDsi(String dsiText, int standard){
        File dsi = new File("dsi");

        try {
            FileWriter myWriter = new FileWriter(dsi);
            myWriter.write(dsiText);
            myWriter.close();

            if (standard == 1)
                this.ReadDSIStartStandard1(dsi);
            if (standard == 2)
                this.ReadDSIStartStandard1(dsi);
            if (standard == 3){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Plik dsi jest prawdopodobnie niekompatybilny i mo?e zdestabilizowa? aplikacj?.");
                alert.setContentText("Czy chcesz kontynuowa??");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.initStyle(StageStyle.TRANSPARENT);
                alert.getDialogPane().getStylesheets().add("/resources/style/jmetro/combination.css");
//                alert.setGraphic(null);

                javafx.scene.control.ButtonType buttonTypeOne = new javafx.scene.control.ButtonType("Kontynuuj");
                javafx.scene.control.ButtonType buttonTypeCancel = new javafx.scene.control.ButtonType("Przerwij", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

                Platform.runLater(() -> window.getContent().setEffect(motionBlur));
                Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
                Platform.runLater(() -> window.getContent().setEffect(null));
                if (result.get() == buttonTypeOne) {


                    alert.setTitle("Standard DSI");
                    alert.setHeaderText("Design System Interface");
                    alert.setContentText("Wybierz model dtandardu DSI");

                    ObservableList<String> standardsList = FXCollections.observableArrayList();
                    standardsList.add("Standard DSI");
                    standardsList.add("VeSys Harness");
                    ListView listView = new ListView<>(standardsList);

                    alert.getDialogPane().setExpandableContent(listView);
                    alert.getDialogPane().setExpanded(true);

                    listView.getSelectionModel().select(0);
                    Platform.runLater(() -> window.getContent().setEffect(motionBlur));
                    Optional<javafx.scene.control.ButtonType> result2 = alert.showAndWait();
                    Platform.runLater(() -> window.getContent().setEffect(null));
                    if (result2.get() == buttonTypeOne) {
                        if (listView.getSelectionModel().getSelectedItem().equals("Standard DSI"))
                            this.ReadDSIStartStandard1(dsi);
                        if (listView.getSelectionModel().getSelectedItem().equals("VeSys Harness"))
                            this.ReadDSIStartStandard2(dsi);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertTxtToXmlLayout(){

        PathFilter all = PathFilter.acceptAllFiles("Wszystkie pliki");

        PathFilter txt = PathFilter.forFileExtension("Plik Exportu TesterTrack *.txt", "txt");


        FXFileChooserStage fc = null;
        try {
            fc = FXFileChooserStage
                    .create(Skin.MODENA, local, txt, all);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Location> locations = new ArrayList<>();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] drives = File.listRoots();
        if (drives != null && drives.length > 0) {
            for (File aDrive : drives) {
                locations.add(Locations.withName(fsv.getSystemTypeDescription(aDrive) + " '" + aDrive + "'  Rozmiar: " + aDrive.getTotalSpace()/(1024*1024*1024) + " Gb   Wolne miejsce: " + aDrive.getFreeSpace()/(1024*1024*1024)+" Gb", Paths.get(String.valueOf(aDrive))));
            }
        }


        Objects.requireNonNull(fc).addLocations(locations);
        fc.setTitleText("Wybierz Exportu TesterTrack");
        Platform.runLater(() -> window.getContent().setEffect(motionBlur));
        Optional<Path> selection = fc.showOpenDialog(primaryStage);
        Platform.runLater(() -> window.getContent().setEffect(null));


        selection.ifPresent(path -> {
            Task task_1 = new Task() {
                @Override
                protected Object call() {/**update(ii,"    Rysowanie Siatki Leyautu ");*/
                    try{



                        local = Paths.get(String.valueOf(path.getParent()));

                        Charset charset = StandardCharsets.UTF_8;
                        String line;
                        StringBuilder skippedMpdules = new StringBuilder();
                        String e2 = "";
                        List<Module> modules1 = new ArrayList<>();
                        try (BufferedReader reader = Files.newBufferedReader(path , charset)) {

                            ProgressBar progressBar = new ProgressBar();
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("Przetwarzanie");
                            alert1.setHeaderText(null);
                            alert1.setContentText("Prosz? czeka?!");
                            alert1.setGraphic(progressBar);
                            alert1.getButtonTypes().clear();
                            Stage stage1 = (Stage) alert1.getDialogPane().getScene().getWindow();
                            stage1.initStyle(StageStyle.TRANSPARENT);
                            alert1.getDialogPane().getStylesheets().add("/resources/style/jmetro/combination.css");
                            alert1.show();
                            while ((line = reader.readLine()) != null ) {
                                //separate all csv fields into string array
                                String[] lineVariables = line.split("\t");

                                try {
                                    if (lineVariables.length>7) {
                                        // //System.out.println("No: " + lineVariables[0] + "\nModuleID: " + lineVariables[1] + "\nXCode: " + lineVariables[2] + "\nConnector: " + lineVariables[3] + "\nPins: " + lineVariables[4] + "\nSwitchPins: " + lineVariables[5] + "\nCoordX: " + lineVariables[6] + "\nCoordY: " + lineVariables[7] + "\n");
                                        Module module = new Module(lineVariables[1],lineVariables[2],lineVariables[3],lineVariables[6],lineVariables[7],0,0);
                                        boolean exist = false;
                                        for (Module module1 : modules1){
                                            //System.out.println(exist);
                                            if (module.getModuleId().equals(module1.getModuleId())){
                                                exist = true;
                                            }
                                        }
                                        if (!exist){
                                            modules1.add(module);
                                        }else{
                                            skippedMpdules.append("\n---------Modu³----------\n" + "No: ").append(lineVariables[0]).append("\nModuleID: ").append(lineVariables[1]).append("\nXCode: ").append(lineVariables[2]);
                                        }

                                    }else{
                                        skippedMpdules.append("\n---------Modu³---------\n" + "No: ").append(lineVariables[0]).append("\nModuleID: ").append(lineVariables[1]).append("\nXCode: ").append(lineVariables[2]);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    e2 = e.getMessage();
                                }

                            }

                            stage1.close();
                            if (!e2.equals("")){
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("B??d");
                                alert.setHeaderText("Wyst?pi?y nieznane problemy podczas ?adowania pliku.");
                                alert.setContentText(e2);
                                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                stage.initStyle(StageStyle.TRANSPARENT);
                                alert.getDialogPane().getStylesheets().add("/resources/style/jmetro/combination.css");
                                Platform.runLater(() -> window.getContent().setEffect(motionBlur));
                                alert.showAndWait();
                                Platform.runLater(() -> window.getContent().setEffect(null));
                            }else {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Pomini?te Modu?y");
                                alert.setHeaderText("Modu?y zosta?y pomini?te poniewa? dane na ich temat s? niekompletne lub wyst?pi?o ju? takie Id.");
                                alert.setContentText("Rozwi? by zobaczy? list?.");
                                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                stage.initStyle(StageStyle.TRANSPARENT);
                                alert.getDialogPane().getStylesheets().add("/resources/style/jmetro/combination.css");

                                Label label = new Label("Pomini?te Modu?y:");

                                TextArea textArea = new TextArea(skippedMpdules.toString());
                                textArea.setEditable(false);
                                textArea.setWrapText(true);
                                textArea.getStyleClass().add("dark-text-area");

                                textArea.setMaxWidth(Double.MAX_VALUE);
                                textArea.setMaxHeight(Double.MAX_VALUE);
                                GridPane.setVgrow(textArea, Priority.ALWAYS);
                                GridPane.setHgrow(textArea, Priority.ALWAYS);

                                GridPane expContent = new GridPane();
                                expContent.setMaxWidth(Double.MAX_VALUE);
                                expContent.add(label, 0, 0);
                                expContent.add(textArea, 0, 1);
                                alert.getDialogPane().setExpanded(true);

                                alert.getDialogPane().setExpandableContent(expContent);
                                Platform.runLater(() -> window.getContent().setEffect(motionBlur));
                                alert.showAndWait();
                                Platform.runLater(() -> window.getContent().setEffect(null));
                                // zapisywanie
                                Dialog<Pair<String, String>> dialog = new Dialog<>();
                                dialog.setTitle("");
                                dialog.setHeaderText("Wprowad? ilo?? kolumn oraz wierszy");
                                Stage stage2 = (Stage) dialog.getDialogPane().getScene().getWindow();
                                stage2.initStyle(StageStyle.TRANSPARENT);
                                dialog.getDialogPane().getStylesheets().add("/resources/style/jmetro/combination.css");


                                javafx.scene.control.ButtonType saveButtonType = new javafx.scene.control.ButtonType("Zapisz", ButtonBar.ButtonData.OK_DONE);
                                javafx.scene.control.ButtonType cancelButtonType = new javafx.scene.control.ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
                                dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);

                                GridPane grid = new GridPane();
                                grid.setHgap(10);
                                grid.setVgap(10);
                                grid.setPadding(new Insets(20, 150, 10, 10));

                                TextField cols = new TextField();
                                cols.setPromptText("Ilo?? Kolumn");
                                TextField rows = new TextField();
                                rows.setPromptText("Ilo?? Wierszy");
//                                TextField rows = new TextField();
//                                rows.setPromptText("Ilo?? Wierszy");

                                grid.add(new Label("Ilo?? Kolumn:"), 0, 0);
                                grid.add(cols, 1, 0);
                                grid.add(new Label("Ilo?? Wierszy:"), 0, 1);
                                grid.add(rows, 1, 1);
//                                grid.add(new Label("Ilo?? Wierszy:"), 0, 2);
//                                grid.add(rows, 1, 2);

                                Node loginButton = dialog.getDialogPane().lookupButton(saveButtonType);
                                loginButton.setDisable(true);


                                cols.textProperty().addListener((observable, oldValue, newValue) -> loginButton.setDisable(newValue.trim().isEmpty()));

                                dialog.getDialogPane().setContent(grid);

                                Platform.runLater(cols::requestFocus);

                                dialog.setResultConverter(dialogButton -> {
                                    if (dialogButton == saveButtonType) {
                                        return new Pair<>(cols.getText(), rows.getText());
                                    }
                                    return null;
                                });

                                Platform.runLater(() -> window.getContent().setEffect(motionBlur));
                                Optional<Pair<String, String>>  result = dialog.showAndWait();
                                Platform.runLater(() -> window.getContent().setEffect(null));
                                if (result.isPresent()) {
                                    new SaveLayoutXML_DOM().save("",cols.getText(),rows.getText(),modules1/*,modules2*/);
                                }
                            }
                        } catch (IOException e) {
                            System.err.println(e);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };


            task_1.run();
        });
    }

    public void loadDsiFile(){


        PathFilter all = PathFilter.acceptAllFiles("Wszystkie pliki");

        PathFilter txt = PathFilter.forFileExtension("Plik Tekstowy DSI *.text", "txt");
        PathFilter dsi = PathFilter.forFileExtension("Plik DSI *.dsi", "dsi");


        FXFileChooserStage fc = null;
        try {
            fc = FXFileChooserStage
                    .create(Skin.MODENA, local, txt,dsi, all);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Location> locations = new ArrayList<>();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] drives = File.listRoots();
        if (drives != null && drives.length > 0) {
            for (File aDrive : drives) {
//                //System.out.println("Drive Letter: " + aDrive);
//                //System.out.println("\tType: " + fsv.getSystemTypeDescription(aDrive));
//                //System.out.println("\tTotal space: " + aDrive.getTotalSpace());
//                //System.out.println("\tFree space: " + aDrive.getFreeSpace());
//                //System.out.println();
                locations.add(Locations.withName(fsv.getSystemTypeDescription(aDrive) + " '" + aDrive + "'  Rozmiar: " + aDrive.getTotalSpace()/(1024*1024*1024) + " Gb   Wolne miejsce: " + aDrive.getFreeSpace()/(1024*1024*1024)+" Gb", Paths.get(String.valueOf(aDrive))));
            }
        }


        Objects.requireNonNull(fc).addLocations(locations);
        fc.setTitleText("Wybierz plik DSI");
        Platform.runLater(() -> window.getContent().setEffect(motionBlur));
        Optional<Path> selection = fc.showOpenDialog(primaryStage);
        Platform.runLater(() -> window.getContent().setEffect(null));
        selection.ifPresent(path -> {

            local = Paths.get(String.valueOf(path.getParent()));
            try {
                this.CheckDsiFile(path.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }

//                ReferencesTable.refresh();
        });
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Desktop"));
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Pliki Tekstowe", "*.txt"),
//                new FileChooser.ExtensionFilter("Wszystkie Pliki", "*.*"));
//        fileChooser.setTitle("WYBIERZ PLIK DSI TXT");
//        fileChooser.setInitialDirectory(new File("F:\\Nowy folder\\DSI"));
//        try {
//            File file = fileChooser.showOpenDialog(primaryStage);
//            if (file != null) {
//
//                this.ReadDSIStart(file);
//                ReferencesTable.refresh();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void loadLDDesignFile(){

        PathFilter all = PathFilter.acceptAllFiles("Wszystkie pliki");

        PathFilter txt = PathFilter.forFileExtension("Plik LDDesign *.xml", "xml");





        FXFileChooserStage fc = null;
        try {
            fc = FXFileChooserStage
                    .create(Skin.MODENA, local, txt, all);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Location> locations = new ArrayList<>();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] drives = File.listRoots();
        if (drives != null && drives.length > 0) {
            for (File aDrive : drives) {
                locations.add(Locations.withName(fsv.getSystemTypeDescription(aDrive) + " '" + aDrive + "'  Rozmiar: " + aDrive.getTotalSpace()/(1024*1024*1024) + " Gb   Wolne miejsce: " + aDrive.getFreeSpace()/(1024*1024*1024)+" Gb", Paths.get(String.valueOf(aDrive))));
            }
        }


        Objects.requireNonNull(fc).addLocations(locations);
        fc.setTitleText("Wybierz plik LDDesign XML");
        Platform.runLater(() -> window.getContent().setEffect(motionBlur));
        Optional<Path> selection = fc.showOpenDialog(primaryStage);
        Platform.runLater(() -> window.getContent().setEffect(null));
        selection.ifPresent(path -> {

            local = Paths.get(String.valueOf(path.getParent()));
            try {
                this.ReadXMLLDDesign(path.toFile());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    final public void CheckDsiFile(File file) throws IOException {

        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
        }catch (Exception e){
            lines = Files.readAllLines(Paths.get(file.getAbsolutePath()),Charset.forName("windows-1250"));
        }


        String dsiStr = lines.stream().collect(Collectors.joining(""));
        if (
                dsiStr.contains("Bundle Drawing Name")
                        && dsiStr.contains("Section 1:")
                        && dsiStr.contains("%Section 2:")
                        && dsiStr.contains("%Section 3:")
                        && dsiStr.contains("%Section 4:")
                        && dsiStr.contains("%Section 5:")
                        && dsiStr.contains("%Section 6:")
                        && dsiStr.contains("%Section 7:")
                        && dsiStr.contains("%Section 8:")
                        && dsiStr.contains("%Section 9:")
                        && dsiStr.contains("%Section 10:")
                        && dsiStr.contains("%Section 11:")
                        && dsiStr.contains("%Section 12:")
                        && dsiStr.contains("%Section 13:")
                        && dsiStr.contains("%Section 14:")
                        && dsiStr.contains("%Section 15:")
                        && dsiStr.contains("%Section 16:")
                        && dsiStr.contains("%Section 17:")
                        && dsiStr.contains("%Section 18:")
        ) {
            this.ReadDSIStartStandard1(file);
        }else if (
                dsiStr.contains("!***  Created by:  VeSys Harness")
                        && dsiStr.contains("!   Harness description information")
                        && dsiStr.contains("%   Harness notes")
                        && dsiStr.contains("%   Harness circuit information")
                        && dsiStr.contains("%   Harness branch configuration")
                        && dsiStr.contains("%   Harness wire specification")
                        && dsiStr.contains("%   Harness node components")
                        && dsiStr.contains("!   Harness cavity components")
                        && dsiStr.contains("!   Harness wire components")
                        && dsiStr.contains("!   Harness branch components")
                        && dsiStr.contains("%   Harness branch insulations")
                        && dsiStr.contains("%   Harness centre strips")
                        && dsiStr.contains("%   Harness multicore specification")
                        && dsiStr.contains("%   Module Children")
                        && dsiStr.contains("%   Module Compatibility Table")
                        && dsiStr.contains("%   Manual BOM Quantities")
                        && dsiStr.contains("%   Composite Options")
                        && dsiStr.contains("%   Wire Through Nodes")
                        && dsiStr.contains("%   Branch Insulation Through Nodes")
                        && dsiStr.contains("%   Mid Wire Components")
                        && dsiStr.contains("%   Wire / Multicore Markers")
                        && dsiStr.contains("%   Pin Mappings")
                        && dsiStr.contains("%   End of file marker")
        ){
            this.ReadDSIStartStandard2(file);
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Plik dsi jest prawdopodobnie niekompatybilny i mo?e zdestabilizowa? aplikacj?.");
            alert.setContentText("Czy chcesz kontynuowa??");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.getDialogPane().getStylesheets().add("/resources/style/jmetro/combination.css");
//                alert.setGraphic(null);

            javafx.scene.control.ButtonType buttonTypeOne = new javafx.scene.control.ButtonType("Kontynuuj");
            javafx.scene.control.ButtonType buttonTypeCancel = new javafx.scene.control.ButtonType("Przerwij", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

            Platform.runLater(() -> window.getContent().setEffect(motionBlur));
            Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            Platform.runLater(() -> window.getContent().setEffect(null));
            if (result.get() == buttonTypeOne) {


                alert.setTitle("Standard DSI");
                alert.setHeaderText("Design System Interface");
                alert.setContentText("Wybierz model dtandardu DSI");

                ObservableList<String> standardsList = FXCollections.observableArrayList();
                standardsList.add("Standard DSI");
                standardsList.add("VeSys Harness");
                ListView listView = new ListView<>(standardsList);

                alert.getDialogPane().setExpandableContent(listView);
                alert.getDialogPane().setExpanded(true);

                listView.getSelectionModel().select(0);
                Platform.runLater(() -> window.getContent().setEffect(motionBlur));
                Optional<javafx.scene.control.ButtonType> result2 = alert.showAndWait();
                Platform.runLater(() -> window.getContent().setEffect(null));
                if (result2.get() == buttonTypeOne) {
                    if (listView.getSelectionModel().getSelectedItem().equals("Standard DSI"))
                        this.ReadDSIStartStandard1(file);
                    if (listView.getSelectionModel().getSelectedItem().equals("VeSys Harness"))
                        this.ReadDSIStartStandard2(file);
                }
            }
        }
        lines = null;
    }

    public void ReadDSIStartStandard1(File file){

        try {



//        for (Tab tab :tabPane.getTabs()){
//            if (!tab.getText().equals("G??wna")){
//                 tabPane.getTabs().remove(tab);
//            }
//        }
            //testersData.clear();
            section1_models.clear();
            section3_models.clear();
            section4_models.clear();
            section5_models.clear();
            section6_models.clear();
            section7_models.clear();
            section9_models.clear();
            section10_models.clear();
            layoutView = null;
            // Section10_Model.add(new Section10_Model("_Full","_Full","_Full","N","","_Full"));

            List<String> lines;
            try {
                lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            }catch (Exception e){
                lines = Files.readAllLines(Paths.get(file.getAbsolutePath()),Charset.forName("windows-1250"));
            }




            /**Section 1 read  Harness Name And Issue Level*/
            boolean Section1 = false;
            Section1:
            while (true) {
                for (String line : lines) {
                    if (line.contains("%Section 2:")) {
                        Section1 = false;
                        break Section1;
                    }


                    if (Section1) {
                        //    //System.out.println(line);
                        String[] sectionTableLine = line.split(":", 41);
                        if (sectionTableLine.length > 1) {
                            section1_models.add(new Section1_Model(sectionTableLine[0], sectionTableLine[1], sectionTableLine[2], "", ""));
                        }
                    }
                    if (line.contains("Section 1:")) {
                        Section1 = true;
                    }
                }
            }
            /**Section 1 read koniec  Harness Name And Issue Level*/

            section3_models.add(new Section3_Model("Pe?na wersja: " + section1_models.get(0).getReference(), section1_models.get(0).getVersion(), section1_models.get(0).getDate(), "_Full", "_Full"));

            /**Section 3 read  Composite Details*/
            boolean Section3 = false;
            Section3:
            while (true) {
                for (String line : lines) {
                    if (line.contains("%Section 4:")) {
                        Section3 = false;
                        break Section3;
                    }


                    if (Section3) {
                        String[] sectionTableLine = line.split(":", 37);
                        if (sectionTableLine.length > 1) {
                            int start = 36;
                            String Variable = "";
                            while (start < sectionTableLine.length) {
                                Variable = Variable + sectionTableLine[start] + ":";
                                start++;
                            }
                            section3_models.add(new Section3_Model(sectionTableLine[0], sectionTableLine[1], sectionTableLine[2], sectionTableLine[16], Variable));
                        }
                    }
                    if (line.contains("%Section 3:")) {
                        Section3 = true;
                    }
                }
            }
            /**Section 3 read koniec  */

            /**Section 4 read Branch Configuration*/
            boolean Section4 = false;
            Section4:
            while (true) {
                for (String line : lines) {
                    if (line.contains("%Section 5:")) {
                        Section4 = false;
                        break Section4;
                    }

                    if (Section4) {
                        String[] sectionTableLine = line.split(":", 24);
                        if (sectionTableLine.length > 1) {
                            String variant = "0";
                            if (!sectionTableLine[9].isEmpty()) {
                                variant = sectionTableLine[9];
                            } else if (!sectionTableLine[8].isEmpty()) {
                                variant = sectionTableLine[8];
                            } else if (!sectionTableLine[7].isEmpty()) {
                                variant = sectionTableLine[7];
                            }

                            //    //System.out.println(line);
                            // if (sectionTableLine.length==10){
                            // //System.out.println("lm10");
                            section4_models.add(new Section4_Model(sectionTableLine[0], sectionTableLine[2], sectionTableLine[3], sectionTableLine[5], sectionTableLine[6], sectionTableLine[7], variant));
//                    }else if (sectionTableLine.length==9){
//                       // //System.out.println("lm9");
//                        Section4_Model.add(new Section4_Model(sectionTableLine[0],sectionTableLine[2],sectionTableLine[3],sectionTableLine[5],sectionTableLine[6],sectionTableLine[7],sectionTableLine[8]));
//                    }else  if (sectionTableLine.length==8){
//                      //  //System.out.println("lm8");
//                        Section4_Model.add(new Section4_Model(sectionTableLine[0],sectionTableLine[2],sectionTableLine[3],sectionTableLine[5],sectionTableLine[6],"LL=0",sectionTableLine[7]));
//                    }
                        }
                    }
                    if (line.contains("%Section 4:")) {
                        Section4 = true;
                    }
                }
            }
            /**Section 4 read koniec*/

            /**Section 5 read Wire Specifications*/
            boolean Section5 = false;
            Section5:
            while (true) {
                for (String line : lines) {
                    if (line.contains("%Section 6:") || line.contains("%Section 6: Components")) {
                        Section5 = false;
                        break Section5;
                    }

                    if (Section5) {
                        String[] sectionTableLine = line.split(":", 24);
                        // //System.out.println(line);
                        if (sectionTableLine.length > 1) {
                            String[] Sec19 = sectionTableLine[19].split(",");
                            //String Eqpot = "";
                            String part_no = "";
                            //String criticity = "";
                            String length = "";

                            for (String s : Sec19) {
//                       if (s.contains("Eqpot"))
//                           Eqpot = s.toLowerCase().replace("eqpot=","");
                                if (s.contains("part_no"))
                                    part_no = s.toLowerCase().replace("part_no=", "");
//                       if (s.contains("criticity"))
//                           criticity = s.toLowerCase().replace("criticity=","");
                                if (s.contains("length"))
                                    length = s.toLowerCase().replace("length=", "");
                            }


                            section5_models.add(new Section5_Model(sectionTableLine[0], sectionTableLine[1], sectionTableLine[2], sectionTableLine[3], sectionTableLine[4], sectionTableLine[6], sectionTableLine[7], sectionTableLine[8], sectionTableLine[10], sectionTableLine[12], sectionTableLine[14], length, part_no));
                        }
                    }
                    if (line.contains("%Section 5:") || line.contains("%Section 5: Wire Specifications")) {
                        Section5 = true;
                    }
                }
            }
            /**Section 5 read koniec*/

            /**Section 6 read  Components*/
            boolean Section6 = false;
            Section6:
            while (true) {
                for (String line : lines) {
                    if (line.contains("%Section 7:")) {
                        Section6 = false;
                        break Section6;
                    }

                    if (Section6) {
                        String[] sectionTableLine = line.split(":", 30);
                        if (sectionTableLine.length > 1) {
                            String pins = "0";
                            String partnumber = "n/a";
                            if (!sectionTableLine[28].isEmpty()) {
                                pins = sectionTableLine[28];
                            } else {
                                pins = sectionTableLine[27].replace("p", "");
                            }

                            if (!sectionTableLine[11].isEmpty()) {
                                partnumber = sectionTableLine[11];
                            } else {
                                partnumber = sectionTableLine[8];
                            }
                            ////System.out.println(line);
                            section6_models.add(new Section6_Model(sectionTableLine[0], sectionTableLine[1], sectionTableLine[4], sectionTableLine[5], sectionTableLine[6], sectionTableLine[9], partnumber, sectionTableLine[12], sectionTableLine[21], pins));
                        }
                    }
                    if (line.contains("%Section 6:")) {
                        Section6 = true;
                    }
                }
            }
            /**Section 6 read koniec*/

            /**Section 7 read Branch Insulations*/
            boolean Section7 = false;
            Section7:
            while (true) {
                for (String line : lines) {
                    if (line.contains("%Section 8:")) {
                        Section7 = false;
                        break Section7;
                    }

                    if (Section7) {
                        String[] sectionTableLine = line.split(":", 18);
                        if (sectionTableLine.length > 1) {
                            section7_models.add(new Section7_Model(sectionTableLine[0], sectionTableLine[2], sectionTableLine[4], sectionTableLine[5], sectionTableLine[7], sectionTableLine[8], sectionTableLine[9]));
                        }
                    }
                    if (line.contains("%Section 7:")) {
                        Section7 = true;
                    }
                }
            }
            /**Section 7 read koniec*/

            /**Section 9 read Multicore Specifications*/
            boolean Section9 = false;
            Section9:
            while (true) {
                for (String line : lines) {
                    if (line.contains("%Section 10:")) {
                        Section9 = false;
                        break Section9;
                    }

                    if (Section9) {
                        String[] sectionTableLine = line.split(":");
                        if (sectionTableLine.length > 1) {
//                    if (sectionTableLine.length > 4){
//                  //  //System.out.println(line);
//                    Section9_Model.add(new Section9_Model(sectionTableLine[0],sectionTableLine[3],sectionTableLine[15]));
//                    }else{
                            section9_models.add(new Section9_Model(sectionTableLine[0], sectionTableLine[3], sectionTableLine[3]));
                        }
//                }
                    }
                    if (line.contains("%Section 9:")) {
                        Section9 = true;
                    }
                }
            }
            /**Section 9 read koniec*/

            /**Section 10 read  Module Child*/
            boolean Section10 = false;
            Section10:
            while (true) {
                for (String line : lines) {
                    if (line.contains("%Section 11:")) {
                        Section10 = false;
                        break Section10;
                    }

                    if (Section10) {
                        String[] sectionTableLine = line.split(":");
                        if (sectionTableLine.length > 1) {
                            if (sectionTableLine.length > 2) {
                                section10_models.add(new Section10_Model(sectionTableLine[0], sectionTableLine[1], sectionTableLine[2], sectionTableLine[3], sectionTableLine[4], sectionTableLine[28]));

                            } else {
                                section10_models.add(new Section10_Model(sectionTableLine[0], sectionTableLine[1], sectionTableLine[1], sectionTableLine[1], sectionTableLine[1], sectionTableLine[1]));

                            }
                        }
                    }
                    if (line.contains("%Section 10:")) {
                        Section10 = true;
                    }
                }
            }
            /**Section 10 read koniec*/

            /**DAF dsi*/
            boolean Daf_dsi = false;
            for (Section10_Model section10_model : section10_models) {
                // //System.out.println(section10_model.getVariant_());
                if (section10_model.getVariant_().contains("!Please see Section 3")) {
                    //Section10_Model.clear();
                    Daf_dsi = true;
                }
            }
            if (Daf_dsi) {
                section10_models.clear();
                for (Section3_Model section3_model : section3_models) {
                    section10_models.add(new Section10_Model(section3_model.getVariant(), "0", section3_model.getReference(), section3_model.getVersion(), section3_model.getDate(), section3_model.getVariable()));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void ReadDSIStartStandard2(File file) {

        try {

            section1_models.clear();
            section3_models.clear();
            section4_models.clear();
            section5_models.clear();
            section6_models.clear();
            section7_models.clear();
            section9_models.clear();
            section10_models.clear();
            layoutView = null;
            // Section10_Model.add(new Section10_Model("_Full","_Full","_Full","N","","_Full"));

            List<String> lines;
            try {
                lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            }catch (Exception e){
                lines = Files.readAllLines(Paths.get(file.getAbsolutePath()),Charset.forName("windows-1250"));
            }




            /**Section 1 read  Harness Name And Issue Level*/
            boolean Section1 = false;
            Section1:
            while (true) {
                for (String line : lines) {
                    if (line.contains("%   Harness notes")) {
                        Section1 = false;
                        break Section1;
                    }


                    if (Section1) {
                        //    //System.out.println(line);
                        String[] sectionTableLine = line.split(":", 41);
                        if (sectionTableLine.length > 1) {
                            section1_models.add(new Section1_Model(sectionTableLine[0], sectionTableLine[1], sectionTableLine[2], sectionTableLine[27], ""));
                            // //System.out.println(sectionTableLine[0] + sectionTableLine[1] + sectionTableLine[2]);
                        }
                    }
                    if (line.contains("!   Harness description information")) {
                        Section1 = true;
                    }
                }
            }
            /**Section 1 read koniec  Harness Name And Issue Level*/

            section3_models.add(new Section3_Model("Pe?na wersja: " + section1_models.get(0).getReference(), section1_models.get(0).getVersion(), section1_models.get(0).getDate(),"_Full" , section1_models.get(0).getVariant()));

//            /**Section 3 read  Composite Details*/
//            boolean Section3 = false;
//            Section3:
//            while (true) {
//                for (String line : lines) {
//                    if (line.contains("%Section 4:")) {
//                        Section3 = false;
//                        break Section3;
//                    }
//
//
//                    if (Section3) {
//                        String[] sectionTableLine = line.split(":", 37);
//                        int start = 36;
//                        String Variable = "";
//                        while (start < sectionTableLine.length) {
//                            Variable = Variable + sectionTableLine[start] + ":";
//                            start++;
//                        }
//                        section3_models.add(new Section3_Model(sectionTableLine[0], sectionTableLine[1], sectionTableLine[2], sectionTableLine[16], Variable));
//                    }
//
//                    if (line.contains("%Section 3:")) {
//                        Section3 = true;
//                    }
//                }
//            }
//            /**Section 3 read koniec  */

            /**Section 4 read Branch Configuration*/
            boolean Section4 = false;
            Section4:
            while (true) {
                for (String line : lines) {
                    if (line.contains("%   Harness wire specification")) {
                        Section4 = false;
                        break Section4;
                    }

                    if (Section4) {
                        String[] sectionTableLine = line.split(":", 24);
                        if (sectionTableLine.length > 1) {
                            String variant = "0";
                            if (!sectionTableLine[9].isEmpty()) {
                                variant = sectionTableLine[9];
                            } else if (!sectionTableLine[8].isEmpty()) {
                                variant = sectionTableLine[8];
                            } else if (!sectionTableLine[7].isEmpty()) {
                                variant = sectionTableLine[7];
                            }

                            //    //System.out.println(line);
                            // if (sectionTableLine.length==10){
                            // //System.out.println("lm10");
                            section4_models.add(new Section4_Model(sectionTableLine[0], sectionTableLine[2], sectionTableLine[3], sectionTableLine[5], sectionTableLine[6], sectionTableLine[7], variant));
//                    }else if (sectionTableLine.length==9){
//                       // //System.out.println("lm9");
//                        Section4_Model.add(new Section4_Model(sectionTableLine[0],sectionTableLine[2],sectionTableLine[3],sectionTableLine[5],sectionTableLine[6],sectionTableLine[7],sectionTableLine[8]));
//                    }else  if (sectionTableLine.length==8){
//                      //  //System.out.println("lm8");
//                        Section4_Model.add(new Section4_Model(sectionTableLine[0],sectionTableLine[2],sectionTableLine[3],sectionTableLine[5],sectionTableLine[6],"LL=0",sectionTableLine[7]));
//                    }
                        }
                    }
                    if (line.contains("%   Harness branch configuration")) {
                        Section4 = true;
                    }
                }
            }
            /**Section 4 read koniec*/

            /**Section 5 read Wire Specifications*/
            boolean Section5 = false;
            Section5:
            while (true) {
                for (String line : lines) {
                    if (line.contains("%   Harness node components")) {
                        Section5 = false;
                        break Section5;
                    }

                    if (Section5) {
                        String[] sectionTableLine = line.split(":", 24);
                        // //System.out.println(line);
                        if (sectionTableLine.length > 1) {
                            String[] Sec19 = sectionTableLine[19].split(",");
                            //String Eqpot = "";
                            String part_no = "";
                            //String criticity = "";
                            String length = "";

                            for (String s : Sec19) {
//                       if (s.contains("Eqpot"))
//                           Eqpot = s.toLowerCase().replace("eqpot=","");
                                if (s.contains("part_no"))
                                    part_no = s.toLowerCase().replace("part_no=", "");
//                       if (s.contains("criticity"))
//                           criticity = s.toLowerCase().replace("criticity=","");
                                if (s.contains("length"))
                                    length = s.toLowerCase().replace("length=", "");
                            }


                            section5_models.add(new Section5_Model(sectionTableLine[0], sectionTableLine[1], sectionTableLine[2], sectionTableLine[3], sectionTableLine[4], sectionTableLine[6], sectionTableLine[7], sectionTableLine[8], sectionTableLine[10], sectionTableLine[12], sectionTableLine[14], length, part_no));
                        }
                    }

                    if (line.contains("%   Harness wire specification")) {
                        Section5 = true;
                    }
                }
            }
            /**Section 5 read koniec*/

            /**Section 6 read  Components*/
            boolean Section6 = false;
            Section6:
            while (true) {
                for (String line : lines) {
                    if (line.contains("!   Harness cavity components")) {
                        Section6 = false;
                        break Section6;
                    }

                    if (Section6) {
                        String[] sectionTableLine = line.split(":", 30);
                        if (sectionTableLine.length > 1) {
                            String pins = "0";
                            if (!sectionTableLine[28].isEmpty()) {
                                pins = sectionTableLine[28];
                            } else {
                                pins = sectionTableLine[27].replace("p", "");
                            }
                            ////System.out.println(line);
                            section6_models.add(new Section6_Model(sectionTableLine[0], sectionTableLine[1], sectionTableLine[4], sectionTableLine[5], sectionTableLine[6], sectionTableLine[9], sectionTableLine[8], sectionTableLine[12], sectionTableLine[21], pins));
                        }
                    }
                    if (line.contains("%   Harness node components")) {
                        Section6 = true;
                    }
                }
            }
            /**Section 6 read koniec*/

            /**Section 7 read Branch Insulations*/
            boolean Section7 = false;
            Section7:
            while (true) {
                for (String line : lines) {
                    if (line.contains("%   Harness centre strips")) {
                        Section7 = false;
                        break Section7;
                    }

                    if (Section7) {
                        String[] sectionTableLine = line.split(":", 18);

                        if (sectionTableLine.length > 1) {
                            section7_models.add(new Section7_Model(sectionTableLine[0], sectionTableLine[2], sectionTableLine[4], sectionTableLine[5], sectionTableLine[7], sectionTableLine[8], sectionTableLine[9]));
                        }
                    }
                    if (line.contains("%   Harness branch insulations")) {
                        Section7 = true;
                    }
                }
            }
            /**Section 7 read koniec*/

//            /**Section 9 read Multicore Specifications*/
//            boolean Section9 = false;
//            Section9:
//            while (true) {
//                for (String line : lines) {
//                    if (line.contains("%Section 10:")) {
//                        Section9 = false;
//                        break Section9;
//                    }
//
//                    if (Section9) {
//                        String[] sectionTableLine = line.split(":");
////                    if (sectionTableLine.length > 4){
////                  //  //System.out.println(line);
////                    Section9_Model.add(new Section9_Model(sectionTableLine[0],sectionTableLine[3],sectionTableLine[15]));
////                    }else{
//                        section9_models.add(new Section9_Model(sectionTableLine[0], sectionTableLine[3], sectionTableLine[3]));
//                    }
////                }
//
//                    if (line.contains("%Section 9:")) {
//                        Section9 = true;
//                    }
//                }
//            }
//            /**Section 9 read koniec*/
//
//            /**Section 10 read  Module Child*/
//            boolean Section10 = false;
//            Section10:
//            while (true) {
//                for (String line : lines) {
//                    if (line.contains("%Section 11:")) {
//                        Section10 = false;
//                        break Section10;
//                    }
//
//                    if (Section10) {
//                        String[] sectionTableLine = line.split(":");
//                        if (sectionTableLine.length > 2) {
//                            section10_models.add(new Section10_Model(sectionTableLine[0], sectionTableLine[1], sectionTableLine[2], sectionTableLine[3], sectionTableLine[4], sectionTableLine[28]));
//
//                        } else {
//                            section10_models.add(new Section10_Model(sectionTableLine[0], sectionTableLine[1], sectionTableLine[1], sectionTableLine[1], sectionTableLine[1], sectionTableLine[1]));
//
//                        }
//                    }
//
//                    if (line.contains("%Section 10:")) {
//                        Section10 = true;
//                    }
//                }
//            }
//            /**Section 10 read koniec*/
//
//            /**DAF dsi*/
//            boolean Daf_dsi = false;
//            for (Section10_Model section10_model : section10_models) {
//                // //System.out.println(section10_model.getVariant_());
//                if (section10_model.getVariant_().contains("!Please see Section 3")) {
//                    //Section10_Model.clear();
//                    Daf_dsi = true;
//                }
//            }
//            if (Daf_dsi) {
//                section10_models.clear();
//                for (Section3_Model section3_model : section3_models) {
//                    section10_models.add(new Section10_Model(section3_model.getVariant(), "0", section3_model.getReference(), section3_model.getVersion(), section3_model.getDate(), section3_model.getVariable()));
//                }
//            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void ReadXMLLDDesign(File file){
        {

        }
    }


    @FXML
    public void startAnalise(){
        if (!ReferencesTable.getSelectionModel().isEmpty() && !TestersTable.getSelectionModel().isEmpty()){
            if (!ReferencesTable.getSelectionModel().getSelectedItem().getReference().equals("Za??duj plik DSI")) {
                drawLayout(
                        ReferencesTable.getSelectionModel().getSelectedItem().getVariant()
                        , ReferencesTable.getSelectionModel().getSelectedItem().getReference()
                        , ReferencesTable.getSelectionModel().getSelectedItem().getVersion()
                        , TestersTable.getSelectionModel().getSelectedItem().getTesterName()
                        , TestersTable.getSelectionModel().getSelectedItem().getTesterID()
                        , TestersTable.getSelectionModel().getSelectedItem().getTesterColumns()
                        , TestersTable.getSelectionModel().getSelectedItem().getTesterRows()
                        , TestersTable.getSelectionModel().getSelectedItem().getSystemPintable());
            }else {
                loadDsiFile();
            }
        }else if (!ReferencesTable.getSelectionModel().isEmpty() && ReferencesTable.getSelectionModel().getSelectedItem().getReference().equals("Za??duj plik DSI")){
            loadDsiFile();
        }

    }

    private void drawLayout(String variant, String reference, String version, String tester, String id , String cols, String rows, ObservableList<SystemPintable> systemPintable){
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TesterLayoutDSI.class.getResource("/view/application/TesterLayoutView.fxml"));

        listOpenedNumbers.put(reference,variant);


        try {
            layoutView = loader.load();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        final TesterLayoutDSI testerLayout = loader.getController();
        final Tab[] tab = {new Tab("DSI: " + reference + "_" + version + " / " + tester)};
        proccesRunning = true;
        final TesterLayoutDSI[] testerLayoutDSIS = {testerLayout.RunThread(variant, reference, version, tester, id, Integer.parseInt(cols), Integer.parseInt(rows),systemPintable)};

        // Platform.runLater(() -> {  });


        tab[0].setContent(layoutView);
        tabPane.getTabs().add(tab[0]);
        tabPane.getSelectionModel().select(tab[0]);

        tab[0].setStyle("-fx-background-color:transparent;");
        //testerLayout.layoutStack.getChildren().add(testerLayout.graph.scrollPane);
        //layoutView.setMinSize(tab.getContent().getBoundsInParent().getWidth(),tab.getContent().getBoundsInParent().getHeight());



        Image closeimg = new Image("/resources/image/close_red.png");
        ImageView close = new ImageView(closeimg);
        close.setFitWidth(16);
        close.setFitHeight(16);
        Button button = new Button("",close);
        button.getStyleClass().clear();



        button.setOnMouseClicked(mouseEvent -> {
            testerLayoutDSIS[0].getMain().setEffect(motionBlur);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setX(tab[0].getContent().getLayoutX()+100);
            alert.setY(tab[0].getContent().getLayoutX()+100);

            alert.setTitle("Zamykanie karty "+ tab[0].getText());

            if (testerLayoutDSIS[0].getGraph().getAutomaticGraph().isAutomaticIsRunning()){
                alert.setHeaderText("Zamkniêcie karty spowoduje utratê wszystkich niezapisanyc danych w niej zawartych. Proces automatyzacji jest w toku.");
            }else {
                alert.setHeaderText("Zamkniêcie karty spowoduje utratê wszystkich niezapisanyc danych w niej zawartych.");
            }

            alert.setContentText("Czy napewno chcesz to zrobiæ");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.initStyle(StageStyle.TRANSPARENT);
            alert.getDialogPane().getStylesheets().add("/resources/style/jmetro/combination.css");
            alert.setGraphic(null);
//            alert.getDialogPane().getStyleClass().add("context-menu");
//            alert.getDialogPane().getStyleClass().add("table-view-small");
//            alert.getDialogPane().getStyleClass().add("background");

            javafx.scene.control.ButtonType buttonTypeOne = new javafx.scene.control.ButtonType("Zamknij");
            javafx.scene.control.ButtonType buttonTypeCancel = new javafx.scene.control.ButtonType("Przywr?c", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

            Platform.runLater(() -> window.getContent().setEffect(motionBlur));
            Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            Platform.runLater(() -> window.getContent().setEffect(null));
            if (result.get() == buttonTypeOne){
                try {
//                    testerLayoutDSIS[0].graph.getModel().getAllCells().clear();
//                    testerLayoutDSIS[0].graph.getModel().getAllEdges().clear();
//                    testerLayoutDSIS[0].graph.getModel().getAllModules().clear();
//                    testerLayoutDSIS[0].graph.getModel().getComponentsData().clear();
//                    testerLayoutDSIS[0].graph.getModel().getConnectorsData().clear();
//                    testerLayoutDSIS[0].graph.getModel().getWiresData().clear();
//                    testerLayoutDSIS[0].graph.getModel().cellMap.clear();
//                    testerLayoutDSIS[0].graph.getModel().moduleMap.clear();
//                    testerLayoutDSIS[0].graph.getModel().clear();
//                    testerLayoutDSIS[0].graph.getCellLayer().getChildren().clear();
//                    testerLayoutDSIS[0].graph.getScrollPane().setContent(null);
//                    testerLayoutDSIS[0].miniMap.iw.clear();
//                    //testerLayoutDSIS[0].miniMap.iwc.clear();
//                    testerLayoutDSIS[0].miniMap.getPane().setContent(null);
//                    testerLayoutDSIS[0].getLayoutStack().getChildren().clear();
//                    testerLayoutDSIS[0].getMain().getChildren().clear();
//                    testerLayoutDSIS[0].tableLayout.getGridPane().getChildren().clear();
//                    //testerLayoutDSIS[0].con.close();
//                    testerLayoutDSIS[0] =null;
                    if ( testerLayoutDSIS[0].getGraph().getAutomaticGraph().isAutomaticIsRunning() ){
                        testerLayoutDSIS[0].automaticRadialItem.selectedProperty().set(false);
                    }
                    listOpenedNumbers.remove(testerLayoutDSIS[0].reference);

                    tabPane.getTabs().remove(tab[0]);
                    taskbarProgressbar.showCustomProgress(0, TaskbarProgressbar.Type.NO_PROGRESS);

                    tab[0] = null;

                    System.runFinalization();
                    System.gc();

                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }            } else {
                testerLayoutDSIS[0].getMain().setEffect(null);
            }


        });
        button.setCursor(Cursor.HAND);


        Image newWindowImg = new Image("/resources/icon/diagonal-arrow.png");
        ImageView newWindowImgV = new ImageView(newWindowImg);
        newWindowImgV.setFitWidth(16);
        newWindowImgV.setFitHeight(16);
        Button newWindowBtn = new Button("",newWindowImgV);
        newWindowBtn.getStyleClass().clear();
        newWindowBtn.setOnAction(event -> {
            Stage stage = new Stage();
            Image iconStage = new Image("/resources/image/ico.png");
            stage.getIcons().add(iconStage);
            stage.setTitle(tab[0].getText());
            tab[0].setContent(null);
            tabPane.getTabs().remove(tab[0]);
            Scene scene = new Scene(layoutView);
            stage.setScene(scene);
            Platform.runLater(() -> layoutView.getStylesheets().addAll("/resources/style/jmetro/JMetroDarkTheme.css"));
            layoutView.getStyleClass().add("table-view-small");
            scene.setFill(Color.BLACK);
            stage.show();
            stage.setOnCloseRequest(windowEvent -> {
                //scene.setRoot(null);
                tab[0].setContent(layoutView);
                tabPane.getTabs().add(tab[0]);
                tabPane.getSelectionModel().select(tab[0]);
            });
        });
        newWindowBtn.setCursor(Cursor.HAND);

        tab[0].setGraphic(new HBox(newWindowBtn,new Separator(),button));
        tab[0].setStyle("-fx-content-display: right ;");
        tab[0].setClosable(false);
        tab[0].setClosable(true);
//        Scene scene = new Scene(layoutView, 1024.0D, 768.0D);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Module diagram");
//        Image iconStage = new Image("/resources/icon/layout.png");
//        primaryStage.getIcons().addAll(new Image[]{iconStage});
//        primaryStage.show();



    }





    public static void main(String[] args) {
        launch(args);
    }
}
