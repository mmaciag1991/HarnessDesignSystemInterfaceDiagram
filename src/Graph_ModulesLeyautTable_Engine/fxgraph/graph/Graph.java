package Graph_ModulesLeyautTable_Engine.fxgraph.graph;


import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.effect.Blend;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.MotionBlur;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.*;

public class Graph {

    //private ArrayList<Integer>[] adjListStr;
    private HashMap<String, Cell>[] adjListStr;


    private Model model;

    private Group canvas;

    public ZoomableScrollPane scrollPane;

//    private Label fps = null;

    MouseGestures mouseGestures;


    SelectionModel selectionModel = new SelectionModel();

    DragMouseGestures dragMouseGestures;

    /* = new DragMouseGestures(selectionModel,mouseGestures)*/;

    public RubberBandSelection rubberBandSelection;


    private DataFormat buttonFormatModule;
    private Module draggingButtonModule;


    private DataFormat buttonFormatCell;
    private Cell draggingButtonCell;

    static DropShadow dropShadow2 = new DropShadow();
    private AutomaticGraph automaticGraph;

//    public void setFPS(Label fps) {
//        this.fps = fps;
//    }

    /**
     * the pane wrapper is necessary or else the scrollpane would always align
     * the top-most and left-most child to the top and left eg when you drag the
     * top child down, the entire scrollpane would move down
     */
    CellLayer cellLayer;
    private DropShadow glow;

    public Graph() {
        this.model = new Model();
        canvas = new Group();
        cellLayer = new CellLayer();

        model.setCellLayer(cellLayer);

        canvas.getChildren().addAll(cellLayer);
        mouseGestures = new MouseGestures(this);
        scrollPane = new ZoomableScrollPane(canvas);
        //scrollPane.setContextMenu(scrollPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        //scrollPane.getStylesheets().add(this.getClass().getResource("Graph.css").toExternalForm());
        //scrollPane.getStyleClass().add("table-view");
        cellLayer.setStyle("""
                    -fx-background-color: rgba(155,155,155,.02);
                -fx-border-width: 0;
                 -fx-border-color:gray;
                                """);


        rubberBandSelection = new RubberBandSelection(cellLayer,selectionModel);
    }

    public double getScaleValue() {
        return scrollPane.getScaleValue();
    }

    public ScrollPane getScrollPane() {
        return this.scrollPane;
    }

    public Pane getCellLayer() {
        return this.cellLayer;
    }

    public Group getCanvas() {
        return canvas;
    }

    public Model getModel() {
        return model;
    }



    public RubberBandSelection getRubberBandSelection() {
        return rubberBandSelection;
    }

    public SelectionModel getSelectionModel() {
        return selectionModel;
    }

    public void beginUpdate() {
    }

    public void endUpdate() {


        dropShadow2.setOffsetX(7.0);
        dropShadow2.setOffsetY(10.0);
        dropShadow2.setRadius(2);
        dropShadow2.setHeight(10);

        // add components to graph pane

        //getCellLayer().getChildren().addAll(model.getAddedModules());
     //   Platform.runLater(() -> {
                    /****dodawanie lini potem celi do layera***/
       //             getCellLayer().getChildren().addAll(model.getAllEdges());
       //              getCellLayer().getChildren().addAll(model.getAllCells());
               // });
//        Image image = new Image(SpliceCell.class.getResource("")+"x.png");
//        ImageView cellLayerBlocker = new ImageView(image);
//        cellLayerBlocker.setLayoutX(-3000);
//        cellLayerBlocker.setLayoutY(-1000);
//        cellLayerBlocker.setFitWidth(64);
//        cellLayerBlocker.setFitHeight(64);
//        getCellLayer().getChildren().add(cellLayerBlocker);


        // remove components from graph pane
        //getCellLayer().getChildren().removeAll(model.getRemovedModules());
        getCellLayer().getChildren().removeAll(model.getRemovedCells());
        getCellLayer().getChildren().removeAll(model.getRemovedEdges());


//        Platform.runLater(() ->{
//            Circle circleLT = new Circle();
//            circleLT.setCenterX(model.getMinusPionts().getX()-200);
//            circleLT.setCenterY(model.getMinusPionts().getY()-200);
//            circleLT.setRadius(30);
//            circleLT.setFill(Color.FORESTGREEN);
//
//            Circle circleRT = new Circle();
//            circleRT.setCenterX(this.getCellLayer().getBoundsInParent().getWidth()+600);
//            circleRT.setCenterY(model.getMinusPionts().getY()-200);
//            circleRT.setRadius(30);
//            circleRT.setFill(Color.FIREBRICK);
//
//            Circle circleLB = new Circle();
//            circleLB.setCenterX(model.getMinusPionts().getX()-200);
//            circleLB.setCenterY(this.getCellLayer().getBoundsInParent().getHeight()+600);
//            circleLB.setRadius(30);
//            circleLB.setFill(Color.ROYALBLUE);
//
//            Circle circleRB = new Circle();
//            circleRB.setCenterX(this.getCellLayer().getBoundsInParent().getWidth()+600);
//            circleRB.setCenterY(this.getCellLayer().getBoundsInParent().getHeight()+600);
//            circleRB.setRadius(30);
//            circleRB.setFill(Color.GOLD);
//
//            this.getCellLayer().getChildren().addAll(circleLT, circleRT, circleLB, circleRB);
//        });

        // every cell must have a parent, if it doesn't, then the graphParent is
        // the parent

       // getModel().attachModulesOrphansToGraphParent(model.getModulesData());
//        Platform.runLater(() ->{
//            Circle circleLT = new Circle();
//            circleLT.setCenterX(model.getMinusPionts().getX()-200);
//            circleLT.setCenterY(model.getMinusPionts().getY()-200);
//            circleLT.setRadius(30);
//            circleLT.setFill(Color.FORESTGREEN);
//
//            Circle circleRT = new Circle();
//            circleRT.setCenterX(this.getCellLayer().getBoundsInParent().getWidth()+600);
//            circleRT.setCenterY(model.getMinusPionts().getY()-200);
//            circleRT.setRadius(30);
//            circleRT.setFill(Color.FIREBRICK);
//
//            Circle circleLB = new Circle();
//            circleLB.setCenterX(model.getMinusPionts().getX()-200);
//            circleLB.setCenterY(this.getCellLayer().getBoundsInParent().getHeight()+600);
//            circleLB.setRadius(30);
//            circleLB.setFill(Color.ROYALBLUE);
//
//            Circle circleRB = new Circle();
//            circleRB.setCenterX(this.getCellLayer().getBoundsInParent().getWidth()+600);
//            circleRB.setCenterY(this.getCellLayer().getBoundsInParent().getHeight()+600);
//            circleRB.setRadius(30);
//            circleRB.setFill(Color.GOLD);
//
//            this.getCellLayer().getChildren().addAll(circleLT, circleRT, circleLB, circleRB);
//        });

        // every cell must have a parent, if it doesn't, then the graphParent is
        // the parent

       // getModel().attachModulesOrphansToGraphParent(model.getModulesData());
        getModel().attachOrphansToGraphParent(model.getAddedCells());

        // remove reference to graphParent
//        getModel().disconnectModulesFromGraphParent(model.getRemovedModules());
        getModel().disconnectFromGraphParent(model.getRemovedCells());

//        for (Cell cell : model.getAddedCells()) {
//
//            if (cell.getClass().equals(ConnectorCell.class)) {
//                buttonFormat = new DataFormat(cell.cellId);
//                dragButton(cell);
//            }
//
//        }
        for (Module module : model.getModulesData()) {
            try {
                buttonFormatModule = null;
                buttonFormatModule = new DataFormat(module.moduleId);
                dragButtonModule(module);
            } catch (Exception e) {
               // e.printStackTrace();
            }
            addDropHandlingCell(module);
            //  addDropHandling(module);

        }

        // enable dragging of cells
        dragMouseGestures = new DragMouseGestures(selectionModel,mouseGestures);
        automaticGraph = new AutomaticGraph(mouseGestures);

//        for (Edge edge : model.getAddedEdges()) {
//            dragMouseGesturesAndAutomaticGraph.addEdge(edge);
//
//            edge.setEffect(dropShadow2);
//        }

        for (Cell cell : model.getConnectorsData()) {
            try {
                buttonFormatCell = new DataFormat(cell.getCellId());
                dragButtonCell(cell);
            }catch (Exception e){}
            dragMouseGestures.makeDraggable(cell);
            cell.setEffect(dropShadow2);

            cell.drawColorBox();
            if (cell.colors.size()>0)
                cell.setBorder(new Border(new BorderStroke(cell.colors.get(cell.colors.size()-1), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
        for (Cell cell : model.getComponentsData()) {
            try {
                buttonFormatCell = new DataFormat(cell.getCellId());
                dragButtonCell(cell);
            }catch (Exception e){}
            dragMouseGestures.makeDraggable(cell);
            cell.setEffect(dropShadow2);

            cell.drawColorBox();
            if (cell.colors.size()>0)
                cell.setBorder(new Border(new BorderStroke(cell.colors.get(cell.colors.size()-1), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }

        // merge added & removed cells with all cells
        getModel().merge();


    }

    public AutomaticGraph getAutomaticGraph() {
        return automaticGraph;
    }

    public void makeDragableModule(Module module){
        buttonFormatModule = new DataFormat(module.moduleId);
        dragButtonModule(module);
        addDropHandlingCell(module);
    }

    public void dragButtonModule(Module b) {
        b.setOnDragDetected(e -> {
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                Dragboard db = b.startDragAndDrop(TransferMode.MOVE);
                db.setDragView(b.snapshot(null, null));

                ClipboardContent cc = new ClipboardContent();
                cc.put(buttonFormatModule, " ");
                db.setContent(cc);
                draggingButtonModule = b;

               // //System.out.println("decected");
            }
        });
    }

    public void addDropHandlingModule(StackPane pane) {

        GridPane gridPane = (GridPane) pane.getParent();

        pane.setOnDragOver(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormatModule) && draggingButtonModule != null) {
                e.acceptTransferModes(TransferMode.MOVE);
               // //System.out.println(gridPane.getChildren().size());
               // //System.out.println("over");
            }
        });

        pane.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();

            if (db.hasContent(buttonFormatModule)) {

                //((Pane)draggingButton.getParent()).getChildren().remove(draggingButton);
                // pane.getChildren().removeAll(pane.getChildren());
                //draggingButton.relocate(pane.getLayoutX() + ((pane.getWidth() - draggingButton.getWidth()) / 2), pane.getLayoutY() + ((pane.getHeight() - draggingButton.getHeight()) / 2));
                Platform.runLater(() -> getScrollPane().setEffect(new MotionBlur()));

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Przenoszenie Modu³u.");
                alert.setHeaderText("Czy napewno chcesz przenie¶æ modu³ do lokalizacji " + pane.getId());
                alert.setContentText("");
                alert.getDialogPane().getStylesheets().add(this.getClass().getResource("Graph.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("table-view");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.initOwner(this.scrollPane.getScene().getWindow());

                ButtonType buttonTypeOne = new ButtonType("Tak");
                ButtonType buttonTypeCancel = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
                Optional<ButtonType> result = alert.showAndWait();
                Platform.runLater(() -> getScrollPane().setEffect(null));
                if (result.get() == buttonTypeOne) {
                    gridPane.getChildren().remove(draggingButtonModule);
                    gridPane.add(draggingButtonModule, GridPane.getColumnIndex(pane), GridPane.getRowIndex(pane), ((int) draggingButtonModule.getWidth() / 100), ((int) draggingButtonModule.getHeight() / 100));
                    String[] coordinate = pane.getId().replace(" ", "").split("-");
                    getModel().getModulesData().get(draggingButtonModule.getSystemPintableId()).moduleCordX.set(coordinate[0]);
                    getModel().getModulesData().get(draggingButtonModule.getSystemPintableId()).moduleCordY.set(coordinate[0]);
                    e.setDropCompleted(true);
                    Platform.runLater(() -> getScrollPane().setEffect(null));
                    draggingButtonModule = null;
                    /** dorobic usuniecie duplikatu */

                } else {
                    e.setDropCompleted(true);
                    Platform.runLater(() -> getScrollPane().setEffect(null));
                }


            }
        });

    }

    private void dragButtonCell(Cell b) {
        b.setOnDragDetected(e -> {
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                Dragboard db = b.startDragAndDrop(TransferMode.MOVE);
                db.setDragView(b.snapshot(null, null));
                ClipboardContent cc = new ClipboardContent();
                cc.put(buttonFormatCell, " ");
                db.setContent(cc);
                draggingButtonCell = b;
                draggingButtonCell.getModuleTableView().getSelectionModel().clearSelection();
                //System.out.println("decected");
            }
        });
    }

    private void addDropHandlingCell(Module pane) {


        pane.setOnDragOver(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormatCell) && draggingButtonCell != null) {
                e.acceptTransferModes(TransferMode.MOVE);


            }
        });

        pane.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();

            if (db.hasContent(buttonFormatCell)) {
                //((Pane)draggingButton.getParent()).getChildren().remove(draggingButton);
                // pane.getChildren().removeAll(pane.getChildren());
                //draggingButtonCell.relocate(pane.getLayoutX() + ((pane.getWidth() - draggingButtonCell.getWidth()) / 2), pane.getLayoutY() + ((pane.getHeight() - draggingButtonCell.getHeight()) / 2));
                //  pane.getChildren().addAll(draggingButton);
                e.setDropCompleted(true);

                draggingButtonCell.getModuleTableView().getSelectionModel().select(pane);
                draggingButtonCell = null;

            }
        });

    }


    public DragMouseGestures getDragMouseGestures() {
        return dragMouseGestures;
    }

    public MouseGestures getMouseGestures() {
        return mouseGestures;
    }

    public double getScale() {
        return this.scrollPane.getScaleValue();
    }


/** sledzenie scie¿ki*/



    public void animatePath(Pane pane, Cell from,  double scale, String shape, List<Cell> list,String pinFromStr, String pinToStr,Color color){
    try {
        Path path = new Path();
        Stack<Cell> nodeStack = new Stack<>();
        Shape shape1 = null, shape2, area = null;
        PathTransition pathTransition;
        pane.getChildren().remove(shape1);
        path.getElements().clear();
        nodeStack.clear();
        pathTransition = new PathTransition();

        MoveTo moveTo = new MoveTo(from.positionXProperty.get() + (list.get(0).getWidth()/2) * scale,from.positionYProperty.getValue() + (list.get(0).getHeight()/2)-25 * scale);
        moveTo.xProperty().bind(from.layoutXProperty().add(from.getBoundsInParent().getWidth() / 2.0));
        moveTo.yProperty().bind(from.layoutYProperty().add((from.getBoundsInParent().getHeight() / 2.0)-25));
        path.getElements().add(moveTo);
        nodeStack.addAll(list);
/************************************************************************/
            while (!nodeStack.isEmpty()) {
                Cell node = nodeStack.pop();
                LineTo lineTo = new LineTo(node.positionXProperty.get() + (node.getWidth()/2) * scale, node.positionYProperty.get()  + (node.getHeight()/2)-25 * scale);
                lineTo.xProperty().bind(node.layoutXProperty().add(node.getBoundsInParent().getWidth() / 2.0));
                lineTo.yProperty().bind(node.layoutYProperty().add((node.getBoundsInParent().getHeight() / 2.0)-10));
                path.getElements().add(lineTo);
            }


            switch (shape) {
                case "Circle":
                    shape1 = new Circle(0,0,11,color);
                    glow =new DropShadow();
                    glow.setColor(color);
                    glow.setOffsetX(0);
                    glow.setOffsetY(0);
                    glow.setRadius(25);
                    //shape1 = new Circle(list.get(0).positionXProperty.get() + (list.get(0).getWidth()/2)  * scale, list.get(0).positionYProperty.get()  + (list.get(0).getHeight()/2)  * scale, 6, Color.BEIGE);

                    area = Shape.union(shape1,shape1);
                    area.setEffect(glow);

                    area.setFill(color);
                    pane.getChildren().add(area);
                    pathTransition.setNode(area);
                    break;
                case "Square":
                    shape1 = new Rectangle(0, 0, 20, 20);
                    shape1.setFill(Color.RED);
                    pane.getChildren().add(shape1);
                    pathTransition.setNode(shape1);
                    break;
                case "Plus":
                    shape1 = new Line(5, 15, 25, 15);
                    shape2 = new Line(15, 5, 15, 25);
                    shape1.setStrokeWidth(8);
                    shape2.setStrokeWidth(8);
                    area = Shape.union(shape1, shape2);
                    area.setStroke(Color.WHITE);
                    area.setEffect(new Blend());
                    area.setFill(color);
                    pane.getChildren().add(area);
                    pathTransition.setNode(area);
                    break;
                case "Cross":
                    shape1 = new Line(5, 5, 25, 25);
                    shape2 = new Line(5, 25, 25, 5);
                    shape1.setStrokeWidth(10);
                    shape2.setStrokeWidth(10);
                    area = Shape.union(shape1, shape2);
                    area.setFill(Color.GOLD);

                    pane.getChildren().add(area);
                    pathTransition.setNode(area);
                    break;
                case "Triangle":
                    shape1 = new Polygon();
                    ((Polygon) shape1).getPoints().addAll(0.0, 10.0, 20.0, 10.0, 10.0, 20.0);
                    shape1.setFill(Color.GOLD);
                    pane.getChildren().add(shape1);
                    pathTransition.setNode(shape1);
                    break;
            }
            pathTransition.setDuration(Duration.seconds(list.size()));
            pathTransition.setPath(path);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(false);

            pathTransition.setInterpolator(Interpolator.EASE_OUT);
//        try {
//            pathTransition.getNode().rotateProperty().bind(DSI_Model.roundCross);
//        }catch (Exception e){}

//        pathTransition.getNode().translateXProperty().addListener((observableValue, bounds, t1) -> {
//            pathTransition.getNode().setRotate(DSI_Model.roundCross.get());
//           /// //System.out.println(DSI_Model.roundCross);
//        });
//        pathTransition.getNode().translateYProperty().addListener((observableValue, bounds, t1) -> {
//            pathTransition.getNode().setRotate(DSI_Model.roundCross.get());
//            /// //System.out.println(DSI_Model.roundCross);
//        });

        final Shape finalArea = area;
        pathTransition.setOnFinished(event -> {
                pane.getChildren().remove(finalArea);
            Text pinTo = new Text(pinToStr);
            pinTo.setFont(Font.font ("Verdana", 0));
            pinTo.setFill(color);
            pinTo.setEffect(new Bloom());
            pinTo.setLayoutX(list.get(0).positionXProperty.get() + (list.get(0).getWidth()/2) * scale);
            pinTo.setLayoutY(list.get(0).positionYProperty.get() + (list.get(0).getHeight()/2) * scale);
            pane.getChildren().add(pinTo);
            Timer animTimer = new Timer();
            animTimer.scheduleAtFixedRate(new TimerTask() {
                int i=0;

                @Override
                public void run() {
                    if (i<100) {
                        Platform.runLater(() ->pinTo.setFont(Font.font ("Verdana", i)));

                    } else {
                        Platform.runLater(() -> pane.getChildren().remove(pinTo));
                        this.cancel();
                    }
                    i++;
                }

            }, 0, 20);
             });



        Text pinFrom = new Text(pinFromStr);
        pinFrom.setFont(Font.font ("Verdana", 0));
        pinFrom.setFill(color);
        pinFrom.setEffect(new Bloom());
        pinFrom.setLayoutX(from.positionXProperty.get() + (from.getWidth()/2) * scale);
        pinFrom.setLayoutY(from.positionYProperty.get() + (from.getHeight()/2) * scale);
        pane.getChildren().add(pinFrom);
        Timer animTimer = new Timer();
        animTimer.scheduleAtFixedRate(new TimerTask() {
            int i=0;

            @Override
            public void run() {
                if (i<100) {
                    Platform.runLater(() -> pinFrom.setFont(Font.font ("Verdana", i)));
                } else {
                    Platform.runLater(() -> pane.getChildren().remove(pinFrom));
                    this.cancel();
                }
                i++;
            }

        }, 0, 20);



        pathTransition.play();
//        pathTransition.currentTimeProperty().addListener((observableValue, number, t1) -> {
//            fps.setText("FPS: "+pathTransition.getTargetFramerate());
//        });


    }

    catch (Exception e){
        e.printStackTrace();
    }

    }


   public Cell getNode(String from){
        for(Cell i: this.getModel().getAllCells()){
            if(i.getCellId().equals(from)){
//                //System.out.println("[getNode();] "+from);
                return i;
            }
        }
        return null;
    }

    List<Cell> pathListWay = new ArrayList<>();
    public List<Cell> printAllPaths(Cell s, Cell d,String pinFrom, String pinTo,Color color)
    {
        pathListWay.clear();
        int v = this.getModel().getAllCells().size();
        boolean[] isVisited = new boolean[v];
        ArrayList<Cell> pathList = new ArrayList<>();


        pathList.add(s);

        printAllPathsUtil(s, d, isVisited, pathList,pinFrom,pinTo,color);
        return pathListWay;
       // printAllPathsUtil(d, s, isVisited, pathList);

    }
    // A recursive function to print
    // all paths from 'u' to 'd'.
    // isVisited[] keeps track of
    // vertices in current path.
    // localPathList<> stores actual
    // vertices in the current path
    private List<Cell> printAllPathsUtil(Cell u, Cell d, boolean[] isVisited, List<Cell> localPathList,String pinFrom, String pinTo, Color color) {

        if (u.equals(d)) {

            for (Cell cell : localPathList){
                ////System.out.println(cell.cellId);
                pathListWay.add(cell);
            }

            animatePath(getCellLayer(), d,  1, "Circle", localPathList, pinFrom, pinTo, color);


            // if match found then no need to traverse more till depth
            return localPathList;
        }

        // Mark the current node
        u.visit();

        // Recur for all the vertices
        // adjacent to current vertex
        for (Cell i : u.children) {

            if (!i.isVisited()) {
                // store current node
                // in path[]
                localPathList.add(i);
                printAllPathsUtil(i, d, isVisited, localPathList, pinFrom, pinTo, color);

                // remove current node
                // in path[]
                localPathList.remove(i);
            }
        }
        for (Cell i2 : u.parents) {
            if (!i2.isVisited()) {
                boolean exist = false;
                // store current node
                // in path[]
                for (Cell cell : localPathList){
                    if (cell.getXcode().equals(i2.getXcode())){
                        exist = true;
                    }
                }
                    if (!exist)
                localPathList.add(i2);
                printAllPathsUtil(i2, d, isVisited, localPathList, pinFrom, pinTo, color);

                // remove current node
                // in path[]
                localPathList.remove(i2);
            }
        }

        // Mark the current node
        u.unvisited();
        return localPathList;
    }

}