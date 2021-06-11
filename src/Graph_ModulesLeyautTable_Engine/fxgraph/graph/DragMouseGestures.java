package Graph_ModulesLeyautTable_Engine.fxgraph.graph;


import DSI_Graph_Main_Controlers.AutomaticGraphSettingsControler;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;




public class DragMouseGestures {

    final DragContext dragContext = new DragContext();
    SelectionModel selectionModel;



    private boolean enabled = false;
    private MouseGestures mouseGestures;

    public DragMouseGestures(SelectionModel selectionModel, MouseGestures mouseGestures) {
        this.selectionModel = selectionModel;
        this.mouseGestures = mouseGestures;
}



    public void makeDraggable(final Node node) {

        node.setOnMousePressed(onMousePressedEventHandler);
        node.setOnMouseDragged(onMouseDraggedEventHandler);
        node.setOnMouseReleased(onMouseReleasedEventHandler);


    }



    public EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

            if (event.getButton().equals(MouseButton.PRIMARY)){
                // don't do anything if the user is in the process of adding to the selection model
                if (event.isControlDown() || event.isShiftDown())
                    return;

            Node node = (Node) event.getSource();
            node.setCursor(Cursor.MOVE);

            dragContext.x = node.getTranslateX() - event.getSceneX();
            dragContext.y = node.getTranslateY() - event.getSceneY();

            // clear the model if the current node isn't in the selection => new selection
            if (!selectionModel.contains(node)) {
                selectionModel.clear();
                selectionModel.add(node);
            }

            // flag that the mouse released handler should consume the event, so it won't bubble up to the pane which has a rubberband selection mouse released handler
            enabled = true;

            // prevent rubberband selection handler
        }
            event.consume();
        }
    };

    EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

            if( !enabled)
                return;

            // all in selection
            for( Node node: selectionModel.selection) {
                node.setTranslateX(event.getX());
                node.setTranslateY(event.getY());
                fixPosition(node);
            }

        }
    };

    EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

            // prevent rubberband selection handler
//            if( enabled) {
//
//                // set node's layout position to current position,remove translate coordinates
//                for( Node node: selectionModel.selection) {
//                    fixPosition(node);
//                }
//
//                enabled = false;
//
//                event.consume();
//            }
        }
    };

    class DragContext {

        double x;
        double y;

    }




    private void fixPosition( Node node) {

        double x = node.getTranslateX();
        double y = node.getTranslateY();

        node.relocate(node.getLayoutX() + x, node.getLayoutY() + y);

        node.setTranslateX(0);
        node.setTranslateY(0);
        mouseGestures.calculateEdges(node);
    }






}