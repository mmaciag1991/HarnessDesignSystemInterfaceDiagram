package Graph_ModulesLeyautTable_Engine.fxgraph.graph;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Scale;

import java.text.DecimalFormat;

public class ZoomableScrollPane extends ScrollPane {
    Group zoomGroup;
    public Scale scaleTransform;
    Node content;
    double scaleValue = 0.4;
    double delta = 0.1;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    //MenuItem item2 = new MenuItem("Skala: "+df2.format(scaleValue )+ " do wartości 1.0");
    public DoubleProperty scaleProperty = new SimpleDoubleProperty();

    public void setContextMenu(ScrollPane scrollPane){
  //      ContextMenu contextMenu = new ContextMenu();

//        MenuItem item1 = new MenuItem("Option");
//        item1.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//
//            }
//        });

//        item2.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                scaleValue = 1.0;
//                zoomTo(scaleValue);
//            }
//        });

        // Add MenuItem to ContextMenu
        //contextMenu.getItems().addAll(item1, item2);

        // When user right-click on connector
//
//        scrollPane.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
//
//            @Override
//            public void handle(ContextMenuEvent event) {
//
//                contextMenu.show(scrollPane, event.getScreenX(), event.getScreenY());
//            }
//        });

    }

    public ZoomableScrollPane(Node content ) {

        this.content = content;
        Group contentGroup = new Group();
        zoomGroup = new Group();
        contentGroup.getChildren().add(zoomGroup);
        zoomGroup.getChildren().add(content);
        setContent(contentGroup);
        scaleTransform = new Scale(scaleValue, scaleValue, 0, 0);
        zoomGroup.getTransforms().add(scaleTransform);


      zoomGroup.setOnScroll(new ZoomHandler());
        //this.setPannable(true);


    }


    public double getScaleValue() {
        return scaleValue;
    }

    public void zoomToActual() {
        zoomTo(1.0);
    }

    public void zoomTo(double scaleValue) {

        this.scaleValue = scaleValue;
        this.scaleProperty.set(scaleValue);

        scaleTransform.setX(scaleValue);
        scaleTransform.setY(scaleValue);

    }

    public void zoomActual() {

        scaleValue = 1;
        zoomTo(scaleValue);

    }

    public void zoomOut() {
        scaleValue -= delta;

        if (Double.compare(scaleValue, 0.1) < 0) {
            scaleValue = 0.1;
        }

        zoomTo(scaleValue);
    }

    public void zoomIn() {

        scaleValue += delta;

        if (Double.compare(scaleValue, 10) > 0) {
            scaleValue = 10;
        }
        zoomTo(scaleValue);

    }

    public void zoomToFit(boolean minimizeOnly) {

        double scaleX = getViewportBounds().getWidth() / getContent().getBoundsInLocal().getWidth();
        double scaleY = getViewportBounds().getHeight() / getContent().getBoundsInLocal().getHeight();

        // consider current scale (in content calculation)
        scaleX *= scaleValue;
        scaleY *= scaleValue;

        // distorted zoom: we don't want it => we search the minimum scale
        // factor and apply it
        double scale = Math.min(scaleX, scaleY);

        // check precondition
        if (minimizeOnly) {

            // check if zoom factor would be an enlargement and if so, just set
            // it to 1
            if (Double.compare(scale, 1) > 0) {
                scale = 1;
            }
        }

        // apply zoom
        zoomTo(scale);

    }

    private class ZoomHandler implements EventHandler<ScrollEvent> {

        @Override
        public void handle(ScrollEvent scrollEvent) {
            // if (scrollEvent.isControlDown())
            {

                    if (scrollEvent.getDeltaY() < 0 ) {
                        if(scaleValue >= 0.11 )
                        scaleValue -= delta;
                    } else {
                        if( scaleValue <= 2)
                        scaleValue += delta;
                    }
               // item2.setText("Skala: "+df2.format(scaleValue) + " do wartości 1.0");
                    zoomTo(scaleValue);

                scrollEvent.consume();
            }
        }
    }
}