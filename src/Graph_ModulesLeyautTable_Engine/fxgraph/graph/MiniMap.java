package Graph_ModulesLeyautTable_Engine.fxgraph.graph;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class MiniMap {
    static private double SCALE = 0.07;
    private ScrollPane pane;
    private Group group = new Group();
    private double baseX;
    private double baseY;
    private ArrayList<Cell> cells;
    private double minX = 0;
    private double minY = 0;
//    private ArrayList<Edge> ctl;
    private Rectangle mainArea;
//    private Rectangle rectangle;
//    private Rectangle border;
//    private Label label;
    public ArrayList<Rectangle> iw;
    public ArrayList<Rectangle> iwc;
    Graph graph = null;
    public MiniMap(Graph graph) {
        this.graph = graph;

        this.pane = new ScrollPane();

        this.pane.setMinWidth(graph.getScrollPane().getWidth() * MiniMap.SCALE);
        this.pane.setMinHeight(graph.getScrollPane().getHeight() * MiniMap.SCALE + 10);
        this.pane.setMaxWidth(500);
        this.pane.setMaxHeight(300);

//            rectangle = new Rectangle(0, 0, pane.getWidth(), pane.getHeight() + 10);
//            rectangle.setFill(Color.LIGHTGREY);
//            border = new Rectangle(0, 0, pane.getWidth(), pane.getHeight() + 10);
//            border.setFill(Color.TRANSPARENT);
//            border.setStrokeWidth(2);
//            border.setStroke(Color.WHITE);
//            label = new Label("Map");
//            label.setFont(new Font("Times New Roman", 16));
//            label.setLayoutX(300);

            mainArea = new Rectangle(0, 0, 0, 0);
            mainArea.getStrokeDashArray().addAll(3.0, 7.0, 3.0, 7.0);
            mainArea.setFill(Color.TRANSPARENT);
            mainArea.setStrokeWidth(2);
            mainArea.setStroke(Color.VIOLET);
            group.getChildren().add(mainArea);
            this.pane.setContent(group);;//().addAll(/*rectangle, border,*/ /*label,*/ mainArea);
        pane.setPickOnBounds(true);

        this.graph.scrollPane.scaleProperty.addListener((observableValue, number, t1) -> {
                mainArea.setScaleX((2-t1.doubleValue()));;
                mainArea.setScaleY((2-t1.doubleValue()));;
//                    mainArea.setWidth(graph.getCanvas().getScene().getWidth()*getSCALE()/*/this.graph.scrollPane.scaleProperty.get()*/);
//                    mainArea.setHeight(graph.getCanvas().getScene().getHeight()*getSCALE()/*/this.graph.scrollPane.scaleProperty.get()*/);

            });

        cells = new ArrayList<>();
        iw = new ArrayList<>();

        this.pane.setOnMousePressed(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY))
            this.moveTo(event.getX(), event.getY());
        });

//        this.graph.getCellLayer().setOnMouseClicked(mouseEvent -> {
//
//            updateMapGraph(mouseEvent.getX(),mouseEvent.getY());
//        });

        graph.getScrollPane().vvalueProperty().addListener((observableValue, number, t1) -> {
            update();
        });
        graph.getScrollPane().hvalueProperty().addListener((observableValue, number, t1) -> {
            update();
        });
    }

    public Group getGroup() {
        return group;
    }

    private void update() {
        Bounds viewportBounds = graph.getScrollPane().getViewportBounds();
        Bounds contentBounds = graph.getCellLayer().getBoundsInLocal();

        double hRel = graph.getScrollPane().getHvalue() / graph.getScrollPane().getHmax();
        double vRel = graph.getScrollPane().getVvalue() / graph.getScrollPane().getVmax();

        double x = Math.max(0, (contentBounds.getWidth() - viewportBounds.getWidth()) * hRel) -/*+*/ viewportBounds.getWidth()/* / 2*/;
        double y = Math.max(0, (contentBounds.getHeight() - viewportBounds.getHeight()) * vRel) -/*+*/ viewportBounds.getHeight()/* / 2*/;

        Point2D localCoordinates = graph.getCellLayer().parentToLocal(x, y);

        mainArea.setWidth(((AnchorPane)graph.getScrollPane().getParent().getParent()).getWidth()*getSCALE());
        mainArea.setHeight(((AnchorPane)graph.getScrollPane().getParent().getParent()).getHeight()*getSCALE());
        mainArea.setX((localCoordinates.getX()*getSCALE())+(/*(graph.getCanvas().getScene().getWidth()*getSCALE())-*/mainArea.getWidth()));
        mainArea.setY((localCoordinates.getY()*getSCALE())+(/*(graph.getCanvas().getScene().getHeight()*getSCALE())-*/mainArea.getHeight()));
//

    }

    public void moveTo(double x, double y) {
        updateMap(x, y);
        if (x < mainArea.getWidth() / 2) {
            graph.getScrollPane().setHvalue(0);
        } else if (x > pane.getWidth() - mainArea.getWidth() / 2) {
            graph.getScrollPane().setHvalue(1);
        } else graph.getScrollPane().setHvalue(x / pane.getWidth());

        if (y < mainArea.getHeight() / 2) {
            graph.getScrollPane().setVvalue(0);
        } else if (y > pane.getHeight() - mainArea.getHeight() / 2) {
            graph.getScrollPane().setVvalue(1);
        } else graph.getScrollPane().setVvalue(y / pane.getHeight());
    }


   /* public Rectangle getBorder() {
        return border;
    }*/

    public void setPane(ScrollPane pane) {
        this.pane = pane;
    }

    public double getBaseX() {
        return baseX;
    }

    public void setBaseX(double baseX) {
        this.baseX = baseX;
    }

    public double getBaseY() {
        return baseY;
    }

    public void setBaseY(double baseY) {
        this.baseY = baseY;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    /*public ArrayList<Edge> getCtl() {
        return ctl;
    }

    public void setCtl(ArrayList<Edge> ctl) {
        this.ctl = ctl;
    }*/

    public void setMainArea(Rectangle mainArea) {
        this.mainArea = mainArea;
    }

    /*public Label getLabel() {
        return label;
    }*/

    /*public void setLabel(Label label) {
        this.label = label;
    }*/

    /*public void setBorder(Rectangle border) {
        this.border = border;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }*/

    public ScrollPane getPane() {
        return pane;
    }

    public Rectangle getMainArea() {
        return mainArea;
    }

    public void setbaseX(double baseX) {
        this.baseX = baseX;
    }

    public void setbaseY(double baseY) {
        this.baseY = baseY;
    }

    public static double getSCALE() {
        return SCALE;
    }

    public void addCell(Cell w) {
      //  //System.out.println(w.getCellType());
        Rectangle rectangle;
        switch (w.getCellType().toUpperCase()) {
            case "CONNECTORSMALLCELL":
                //imageView = new ImageView(new Image("ModulesLeyautTable/fxgraph/cells/node.png"));
                rectangle = new Rectangle();
                rectangle.setFill(Color.GOLD);
                break;
            case "COMPONENTCELL":
                //imageView = new ImageView(new Image("ModulesLeyautTable/fxgraph/cells/plus.png"));
                rectangle = new Rectangle();
                rectangle.setFill(Color.TURQUOISE);
                break;
            case "ANOTHERCELL":
                //imageView = new ImageView(new Image("ModulesLeyautTable/fxgraph/cells/splice.png"));
                rectangle = new Rectangle();
                rectangle.setFill(Color.BLUEVIOLET);
                break;
            case "SPLICECELL":
                //imageView = new ImageView(new Image("ModulesLeyautTable/fxgraph/cells/splice.png"));
                rectangle = new Rectangle();
                rectangle.setFill(Color.FIREBRICK);
                break;
            default:
                //imageView = new ImageView(new Image("ModulesLeyautTable/fxgraph/cells/node.png"));
                rectangle = new Rectangle();
                rectangle.setFill(Color.GOLD);
                break;
        }
        rectangle.visibleProperty().bind(w.visibleProperty());
        rectangle.setLayoutX(w.getLayoutX() * MiniMap.SCALE);
        rectangle.setLayoutY(w.getLayoutY() * MiniMap.SCALE);
        //imageView.setPreserveRatio(true);
        rectangle.setHeight(70 * MiniMap.SCALE);
        rectangle.setWidth(70 * MiniMap.SCALE);
        iw.add(rectangle);
        cells.add(w);
        Platform.runLater(() -> group.getChildren().add(rectangle) );

    }

    public void deleteCell(Cell w) {
        group.getChildren().remove(w);
        cells.remove(w);
    }

//    public void addCastle(Edge c) {
//        ImageView imageVie;
//        imageVie = new ImageView(new Image("ModulesLeyautTable/fxgraph/cells/plusPicker.png"));
//       // imageVie.setLayoutX(c.getImgCastle().getX() * MiniMap.SCALE);
//       // imageVie.setLayoutY(c.getImgCastle().getY() * MiniMap.SCALE);
//        imageVie.setPreserveRatio(true);
//        imageVie.setFitHeight(250 * MiniMap.SCALE);
//        /*iwc.add(imageVie);*/
//        ctl.add(c);
//        pane.getChildren().add(imageVie);
//    }

    public void updateMap(){
        pane.toFront();
        for(int i = 0; i< cells.size(); i++){
            iw.get(i).setLayoutX(this.cells.get(i).getLayoutX()* MiniMap.SCALE);
            iw.get(i).setLayoutY(this.cells.get(i).getLayoutY()* MiniMap.SCALE);
            int finalI = i;
            this.cells.get(i).positionXProperty.addListener((observableValue, number, t1) -> {
                iw.get(finalI).setLayoutX(this.cells.get(finalI).positionXProperty.doubleValue() * MiniMap.SCALE);
            });
            this.cells.get(i).positionYProperty.addListener((observableValue, number, t1) -> {
                iw.get(finalI).setLayoutY(this.cells.get(finalI).positionYProperty.doubleValue() * MiniMap.SCALE);
            });

        }
       // Platform.runLater(() -> {
            if (graph.getScrollPane().getParent() != null) {
            mainArea.setWidth(((StackPane) graph.getScrollPane().getParent()).getWidth() * getSCALE());
            mainArea.setHeight(((StackPane) graph.getScrollPane().getParent()).getHeight() * getSCALE());
        }
       // });
    }

    public void updateMainArea(){
        mainArea.setWidth(((StackPane)graph.getScrollPane().getParent()).getWidth()*getSCALE());
        mainArea.setHeight(((StackPane)graph.getScrollPane().getParent()).getHeight()*getSCALE());
    }

    public void updateMap(double x, double y) {
        this.setbaseX(x);
        this.setbaseY(y);

//        this.pane.setLayoutX(baseX + 1252);
//        this.pane.setLayoutY(baseY);
//        this.rectangle.setX(0);
//        this.rectangle.setY(0);
//        this.border.setX(0);
//        this.border.setY(0);
//        this.label.setLayoutX(300);
//        this.label.setLayoutY(0);

        this.mainArea.setX(x-(mainArea.getWidth()/2*graph.getScale()));
        this.mainArea.setY(y-(mainArea.getHeight()/2*graph.getScale()));
//        this.mainArea.setX(x);
//        this.mainArea.setY(y);

//        for (WarriorFirst w : Main.arr) {
//            ImageView imageView = w.getImgpas();
//            imageView.setLayoutX(w.getBaseX() * MiniMap.SCALE);
//            imageView.setLayoutY(w.getBaseY() * MiniMap.SCALE);
//            if(!(Main.groupWar.getChildren().contains(w))){
//                this.getPane().getChildren().add(imageView);
//            }
//        }
    }
    public void updateMapGraph(double x, double y) {

        this.mainArea.setX((x*getSCALE())-this.getMainArea().getWidth());
        this.mainArea.setY((y*getSCALE())-this.getMainArea().getHeight());

//
    }

    public void addModule(Module module) {
        try {


                ////System.out.println(node.getClass().getName());
                Rectangle rectangle = new Rectangle();
                rectangle.setStroke(Color.SILVER);
                rectangle.setOpacity(.2);
                if (module.getClass().getSimpleName().equals("ModuleCell")) {
                    rectangle.setFill(Color.YELLOW);
                } else {
                    rectangle.setFill(Color.TRANSPARENT);
                }

                rectangle.setLayoutX(module.view.getLayoutX() * MiniMap.SCALE);
                rectangle.setLayoutY(module.view.getLayoutY() * MiniMap.SCALE);
                this.group.getChildren().add(rectangle);
            rectangle.setWidth(module.getBoundsInLocal().getWidth() * SCALE);
            rectangle.setHeight(module.getBoundsInLocal().getHeight() * SCALE);
                rectangle.toBack();


        }catch (Exception e){}
    }

    public void drawTableLayout(GridPane gridPane) {
        try {
            for (Node node : gridPane.getChildren()) {

                ////System.out.println(node.getClass().getName());
                Rectangle rectangle = new Rectangle();
                rectangle.setStroke(Color.SILVER);
                rectangle.setOpacity(.2);
                if (node.getClass().getSimpleName().equals("ModuleCell")) {
                    rectangle.setFill(Color.YELLOW);
                } else {
                    rectangle.setFill(Color.TRANSPARENT);
                }
                rectangle.setWidth(100 * SCALE);
                rectangle.setHeight(100 * SCALE);
                rectangle.setLayoutX(node.getLayoutX() * MiniMap.SCALE);
                rectangle.setLayoutY(node.getLayoutY() * MiniMap.SCALE);
                this.group.getChildren().add(rectangle);
                rectangle.toBack();

            }
        }catch (Exception e){}
    }
}