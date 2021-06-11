package Graph_ModulesLeyautTable_Engine.fxgraph.graph;


import DSI_Graph_Main_Controlers.TesterLayoutDSI;
import javafx.scene.Group;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.text.DecimalFormat;

public class Edge extends Group {

    protected Cell source;
    protected Cell target;

    boolean toShort = false;
    Line line;
    /**Label label;*/
  TitledPane titledPane;
    double lenght;
    double distance;
    public Color baseColor;
    double strokeWidth;



    public Edge(Cell source, Cell target, double lenght, double strokeWidth, Color fill) {

        this.baseColor = fill;
        this.source = source;
        this.target = target;
        this.lenght = lenght;
        source.addCellChild(target);
        target.addCellParent(source);
        source.addEdge(this);
        target.addEdge(this);


        line = new Line();
        setEffect(new DropShadow());
       // setStyle("-fx-effect: dropshadow(gaussian, rgb("+fill.getRed()+","+fill.getGreen()+","+fill.getBlue()+"), 3, 0, 0, 0);-fx-stroke-line-cap: round;");
        line.startXProperty().bind( source.layoutXProperty().add(source.getBoundsInParent().getWidth() / 2.0));
        line.startYProperty().bind( source.layoutYProperty().add(source.getBoundsInParent().getHeight() / 2.0));
        line.endXProperty().bind( target.layoutXProperty().add( target.getBoundsInParent().getWidth() / 2.0));
        line.endYProperty().bind( target.layoutYProperty().add( target.getBoundsInParent().getHeight() / 2.0));
        line.setStrokeWidth(strokeWidth);
        line.setStroke(fill);
        line.setFill(fill);

//        line.startXProperty().bind( source.translateXProperty().add(source.getBoundsInParent().getWidth() / 2.0));
//        line.startYProperty().bind( source.translateYProperty().add(source.getBoundsInParent().getHeight() / 2.0));
//        line.endXProperty().bind( target.translateXProperty().add( target.getBoundsInParent().getWidth() / 2.0));
//        line.endYProperty().bind( target.translateYProperty().add( target.getBoundsInParent().getHeight() / 2.0));

        double sourceLayoutX = source.layoutXProperty().add(source.getBoundsInParent().getWidth() / 2.0).get();
        double sourceLayoutY = source.layoutYProperty().add(source.getBoundsInParent().getHeight() / 2.0).get();
        double targetLayoutX = target.layoutXProperty().add( target.getBoundsInParent().getWidth() / 2.0).get();
        double targetLayoutY =  target.layoutYProperty().add( target.getBoundsInParent().getHeight() / 2.0).get();

        /*double distance = Math.sqrt((targetLayoutY - sourceLayoutY) * (targetLayoutY - sourceLayoutY) +
                (targetLayoutX - sourceLayoutX) * (targetLayoutX - sourceLayoutX));*/
        double distance = /*Math.sqrt((targetLayoutY - sourceLayoutY) * (targetLayoutY - sourceLayoutY) +
                (targetLayoutX - sourceLayoutX) * (targetLayoutX - sourceLayoutX));*/

                Math.sqrt(Math.pow(Math.abs(targetLayoutX - sourceLayoutX), 2)
                        + Math.pow(Math.abs(targetLayoutY - sourceLayoutY), 2));

        setDistance(distance);
//        label = new Label();
//
//        label.setText(String.valueOf(distance));
//        label.layoutXProperty().bind(line.endXProperty().subtract(line.endXProperty().subtract(line.startXProperty()).divide(2)));
//        label.layoutYProperty().bind(line.endYProperty().subtract(line.endYProperty().subtract(line.startYProperty()).divide(2)));

      titledPane = new TitledPane();
        titledPane.visibleProperty().bind(TesterLayoutDSI.edgeLabelsVisableProperty);
        titledPane.layoutXProperty().bind(line.endXProperty().subtract(line.endXProperty().subtract(line.startXProperty()).divide(2)));
        titledPane.layoutYProperty().bind(line.endYProperty().subtract(line.endYProperty().subtract(line.startYProperty()).divide(2)));
        titledPane.setOpacity(0.80);
        titledPane.setCollapsible(false);
        titledPane.setExpanded(false);
        titledPane.setPrefSize(100,45);

        DecimalFormat df = new DecimalFormat("#.##");
        titledPane.setText(df.format(distance / 2) +" mm \n/"+lenght+" mm");
        Tooltip.install(line,new Tooltip(df.format(distance / 2) +" mm \n/"+lenght+" mm"));
        titledPane.setStyle("-fx-background-color: yellow;");
        titledPane.setOnMouseClicked(event -> titledPane.toFront());

        //titledPane.setTooltip(new Tooltip(String.valueOf("Aktualny wymiar: " + df.format(distance/2))+" mm \nWymagany wymiar: "+lenght+" mm"));

//        setCacheHint(CacheHint.SPEED);
//        setCache(true);
//        setCacheShape(true);
//        setSnapToPixel(true);

//        Group object = new Group();
//        object.getChildren().addAll(line/*,titledPane*/);
        getChildren().addAll( line,titledPane);
//
//
//        Arrow arrow = new Arrow();
//        arrow.getStyleClass().add("arrow");
//
//        arrow.startXProperty().bind( source.layoutXProperty().add((source.getBoundsInParent().getWidth())));
//        arrow.startYProperty().bind( source.layoutYProperty().add((source.getBoundsInParent().getHeight())));
//        arrow.endXProperty().bind( target.layoutXProperty().add((target.getBoundsInParent().getWidth())));
//        arrow.endYProperty().bind( target.layoutYProperty().add((target.getBoundsInParent().getHeight())));
//
//
//        getChildren().add(arrow);

    }

    public Color getBaseColor() {
        return baseColor;
    }
    public void setBaseColor(Color baseColor) {
        this.baseColor = baseColor;
    }

    public double getLenght() {
        return lenght;
    }

    public void setLenght(double lenght) {
        this.lenght = lenght;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Cell getSource() {
        return source;
    }
    public Cell getTarget() {
        return target;
    }

    public void setSource(Cell source) {

        this.source = source;

        line.startXProperty().bind( source.layoutXProperty().add(source.getBoundsInParent().getWidth() / 2.0));
        line.startYProperty().bind( source.layoutYProperty().add(source.getBoundsInParent().getHeight() / 2.0));

    }

    public void setTarget(Cell target) {

        this.target = target;

        line.endXProperty().bind( target.layoutXProperty().add( target.getBoundsInParent().getWidth() / 2.0));
        line.endYProperty().bind( target.layoutYProperty().add( target.getBoundsInParent().getHeight() / 2.0));

    }

//    public Label getLabel(){
//        return  label;
//    }

    public Line getLine() {
        return line;
    }

    public TitledPane getTitledPane(){
        return  titledPane;
    }

    public boolean isToShort() {
        return toShort;
    }
    public void setToShort(boolean toShort){
        this.toShort = toShort;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }
}