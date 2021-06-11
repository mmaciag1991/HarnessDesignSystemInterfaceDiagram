package DSI_Graph_Main_Controlers.TableEdges;



import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.text.DecimalFormat;

public class TableEdge extends Group {


    private final Line lineA = new Line();
    private final Line lineB = new Line();
    private final Line lineC = new Line();



    Button start;
    double lenght;
    double distance;
    public Color baseColor;
    double strokeWidth;

    DoubleProperty sourceX;
    DoubleProperty sourceY;
    DoubleProperty targetX;
    DoubleProperty targetY;

    public TableEdge(DoubleProperty sourceX, DoubleProperty sourceY, DoubleProperty targetX, DoubleProperty targetY, double lenght, double strokeWidth, Color fill) {
        this.baseColor = fill;

        this.lenght = lenght;

       this.sourceX = sourceX;
       this.sourceY = sourceY;
       this.targetX = targetX;
       this.targetY = targetY;

        DoubleBinding centerX = sourceX.add(targetX).divide(2);


         lineA.startXProperty().bind(sourceX.subtract(-3));
         lineA.startYProperty().bind(sourceY);
         lineA.endXProperty().bind(centerX);
         lineA.endYProperty().bind(sourceY);
         this.getChildren().add(lineA);

         lineB.startXProperty().bind(centerX);
         lineB.startYProperty().bind(sourceY);
         lineB.endXProperty().bind(centerX);
         lineB.endYProperty().bind(targetY);
        this.getChildren().add(lineB);

         lineC.startXProperty().bind(centerX);
         lineC.startYProperty().bind(targetY);
         lineC.endXProperty().bind(targetX);
         lineC.endYProperty().bind(targetY);
        this.getChildren().add(lineC);


        lineA.setStyle("-fx-effect: dropshadow(gaussian, rgb("+fill.getRed()+","+fill.getGreen()+","+fill.getBlue()+"), 3, 0, 0, 0);-fx-stroke-line-cap: round;");

        lineA.setStrokeWidth(strokeWidth);
        lineA.setStroke(fill);
        lineA.setFill(fill);

        lineB.setStyle("-fx-effect: dropshadow(gaussian, rgb("+fill.getRed()+","+fill.getGreen()+","+fill.getBlue()+"), 3, 0, 0, 0);-fx-stroke-line-cap: round;");

        lineB.setStrokeWidth(strokeWidth);
        lineB.setStroke(fill);
        lineB.setFill(fill);

        lineC.setStyle("-fx-effect: dropshadow(gaussian, rgb("+fill.getRed()+","+fill.getGreen()+","+fill.getBlue()+"), 3, 0, 0, 0);-fx-stroke-line-cap: round;");

        lineC.setStrokeWidth(strokeWidth);
        lineC.setStroke(fill);
        lineC.setFill(fill);



    }

    public Color getBaseColor() {
        return baseColor;
    }
    public void setBaseColor(Color baseColor) {
        this.baseColor = baseColor;
    }

    public void setStart(Button start) {
        this.start = start;
        start.layoutXProperty().bind(lineB.endXProperty().subtract(lineB.endXProperty().subtract(lineB.startXProperty()).divide(2).subtract(-22)));
        start.layoutYProperty().bind(lineB.endYProperty().subtract(lineB.endYProperty().subtract(lineB.startYProperty()).divide(2).subtract(-12)));
        this.getChildren().add(start);
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


    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setSourceX(double sourceX) {
        this.sourceX.set(sourceX);
    }

    public void setSourceY(double sourceY) {
        this.sourceY.set(sourceY);
    }

    public void setTargetX(double targetX) {
        this.targetX.set(targetX);
    }

    public void setTargetY(double targetY) {
        this.targetY.set(targetY);
    }
}