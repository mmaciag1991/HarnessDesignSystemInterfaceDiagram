package Graph_ModulesLeyautTable_Engine.fxgraph.graph;

import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Selected extends Pane {

    public Pane Selected(Cell cell){
        attachBoundingRectangle(cell);
        return this;
    }

    private void attachBoundingRectangle(Cell node) {
        Bounds bounds = node.getLayoutBounds();
        Rectangle boundary;
        boundary = new Rectangle();
        boundary.setCursor(Cursor.HAND);
        boundary.setStyle(
                "-fx-stroke: forestgreen; " +
                        "-fx-stroke-width: 2px; " +
                        "-fx-stroke-dash-array: 12 2 4 2; " +
                        "-fx-stroke-dash-offset: 6; " +
                        "-fx-stroke-line-cap: butt; " +
                        "-fx-fill: rgba(2, 109, 158, .3);"
        );

        boundary.setX(bounds.getMinX());
        boundary.setY(bounds.getMinY());
        boundary.setWidth(bounds.getWidth());
        boundary.setHeight(bounds.getHeight());


        this.getChildren().add(boundary);


        Anchor topLeft = new Anchor(Color.GOLD, true, true, (oldX, oldY, newX, newY) -> {
            double newWidth = boundary.getWidth() - (newX - oldX);
            if (newWidth > 0) {
                boundary.setX(newX);
                boundary.setWidth(newWidth);
            }
            double newHeight = boundary.getHeight() - (newY - oldY);
            if (newHeight > 0) {
                boundary.setY(newY);
                boundary.setHeight(newHeight);
            }


        });
        Anchor topCenter = new Anchor(Color.GOLD, false, true, (oldX, oldY, newX, newY) -> {
            double newHeight = boundary.getHeight() - (newY - oldY);
            if (newHeight > 0) {
                boundary.setY(newY);
                boundary.setHeight(newHeight);
            }


        });
        Anchor topRight = new Anchor(Color.GOLD, true, true, (oldX, oldY, newX, newY) -> {
            double newWidth = boundary.getWidth() + (newX - oldX);
            if (newWidth > 0) {
                boundary.setWidth(newWidth);
            }
            double newHeight = boundary.getHeight() - (newY - oldY);
            if (newHeight > 0) {
                boundary.setY(newY);
                boundary.setHeight(newHeight);
            }


        });
        Anchor rightCenter = new Anchor(Color.GOLD, true, false, (oldX, oldY, newX, newY) -> {
            double newWidth = boundary.getWidth() + (newX - oldX);
            if (newWidth > 0) {
                boundary.setWidth(newWidth);
            }


        });
        Anchor bottomRight = new Anchor(Color.GOLD, true, true, (oldX, oldY, newX, newY) -> {
            double newWidth = boundary.getWidth() + (newX - oldX);
            if (newWidth > 0) {
                boundary.setWidth(newWidth);
            }
            double newHeight = boundary.getHeight() + (newY - oldY);
            if (newHeight > 0) {
                boundary.setHeight(newHeight);
            }


        });
        Anchor bottomCenter = new Anchor(Color.GOLD, false, true, (oldX, oldY, newX, newY) -> {
            double newHeight = boundary.getHeight() + (newY - oldY);
            if (newHeight > 0) {
                boundary.setHeight(newHeight);
            }


        });
        Anchor bottomLeft = new Anchor(Color.GOLD, true, true, (oldX, oldY, newX, newY) -> {
            double newWidth = boundary.getWidth() - (newX - oldX);
            if (newWidth > 0) {
                boundary.setX(newX);
                boundary.setWidth(newWidth);
            }
            double newHeight = boundary.getHeight() + (newY - oldY);
            if (newHeight > 0) {
                boundary.setHeight(newHeight);
            }


        });
        Anchor leftCenter = new Anchor(Color.GOLD, true, false, (oldX, oldY, newX, newY) -> {
            double newWidth = boundary.getWidth() - (newX - oldX);
            if (newWidth > 0) {
                boundary.setX(newX);
                boundary.setWidth(newWidth);
            }


        });

        this.getChildren().addAll(
                topLeft,
                topCenter,
                topRight,
                rightCenter,
                bottomRight,
                bottomCenter,
                bottomLeft,
                leftCenter
        );

        topLeft.setCenterX(boundary.getX());
        topLeft.setCenterY(boundary.getY());
        topCenter.setCenterX(boundary.getX() + boundary.getWidth() / 2);
        topCenter.setCenterY(boundary.getY());
        topRight.setCenterX(boundary.getX() + boundary.getWidth());
        topRight.setCenterY(boundary.getY());
        rightCenter.setCenterX(boundary.getX() + boundary.getWidth());
        rightCenter.setCenterY(boundary.getY() + boundary.getHeight() / 2);
        bottomRight.setCenterX(boundary.getX() + boundary.getWidth());
        bottomRight.setCenterY(boundary.getY() + boundary.getHeight());
        bottomCenter.setCenterX(boundary.getX() + boundary.getWidth() / 2);
        bottomCenter.setCenterY(boundary.getY() + boundary.getHeight());
        bottomLeft.setCenterX(boundary.getX());
        bottomLeft.setCenterY(boundary.getY() + boundary.getHeight());
        leftCenter.setCenterX(boundary.getX());
        leftCenter.setCenterY(boundary.getY() + boundary.getHeight() / 2);


    }

}
