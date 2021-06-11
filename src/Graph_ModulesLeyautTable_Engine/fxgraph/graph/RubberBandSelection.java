package Graph_ModulesLeyautTable_Engine.fxgraph.graph;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

 public class RubberBandSelection {

    final DragContext dragContext = new DragContext();
    Rectangle rect;
    SelectionModel selectionModel;
    Pane group;
    boolean enabled = false;

    public RubberBandSelection(Pane group , SelectionModel selectionModel) {
        this.selectionModel = selectionModel;
        this.group = group;
        rect = new Rectangle();
        rect.setFill(Color.rgb(2, 109, 158,.3));
        rect.setWidth(1);
        rect.setHeight(1);
        rect.setStrokeWidth(3);
        rect.setStroke(Color.ROYALBLUE);
        rect.getStrokeDashArray().addAll(3.0, 7.0, 3.0, 7.0);
        rect.setMouseTransparent(true);




    }

     public void setSelectionEnabled(boolean enabled) {
     if (enabled){
         group.addEventHandler(MouseEvent.MOUSE_PRESSED, onMousePressedEventHandler);
         group.addEventHandler(MouseEvent.MOUSE_DRAGGED, onMouseDraggedEventHandler);
         group.addEventHandler(MouseEvent.MOUSE_RELEASED, onMouseReleasedEventHandler);
     }else {
         group.removeEventHandler(MouseEvent.MOUSE_PRESSED, onMousePressedEventHandler);
         group.removeEventHandler(MouseEvent.MOUSE_DRAGGED, onMouseDraggedEventHandler);
         group.removeEventHandler(MouseEvent.MOUSE_RELEASED, onMouseReleasedEventHandler);
     }
     }

     public

    EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

            if (event.getButton().equals(MouseButton.PRIMARY)) {
                // simple flag to prevent multiple handling of this event or we'd get an exception because rect is already on the scene
                // eg if you drag with left mouse button and while doing that click the right mouse button
                if (enabled)
                    return;

                dragContext.mouseAnchorX = event.getX();
                dragContext.mouseAnchorY = event.getY();

                rect.setX(dragContext.mouseAnchorX);
                rect.setY(dragContext.mouseAnchorY);
                rect.setWidth(0);
                rect.setHeight(0);

                group.getChildren().add(rect);

                event.consume();

                enabled = true;
                ((Node) (event.getSource())).setCursor(Cursor.CROSSHAIR);
            }
        }
    };

    EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (!event.isShiftDown() && !event.isControlDown()) {
                    selectionModel.clear();
                }

                for (Node node : group.getChildren()) {

                    if (node instanceof Cell) {
                        if (node.getBoundsInParent().intersects(rect.getBoundsInParent())) {

                            if (event.isShiftDown()) {

                                selectionModel.add(node);

                            } else if (event.isControlDown()) {

                                if (selectionModel.contains(node)) {
                                    selectionModel.remove(node);
                                } else {
                                    selectionModel.add(node);
                                }
                            } else {
                                selectionModel.add(node);
                            }

                        }
                    }

                }

                selectionModel.log();

                rect.setX(0);
                rect.setY(0);
                rect.setWidth(0);
                rect.setHeight(0);

                group.getChildren().remove(rect);

                event.consume();

                enabled = false;
                ((Node) (event.getSource())).setCursor(Cursor.DEFAULT);
            }
        }

    };

    EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                double offsetX = event.getX() - dragContext.mouseAnchorX;
                double offsetY = event.getY() - dragContext.mouseAnchorY;

                if (offsetX > 0)
                    rect.setWidth(offsetX);
                else {
                    rect.setX(event.getX());
                    rect.setWidth(dragContext.mouseAnchorX - rect.getX());
                }

                if (offsetY > 0) {
                    rect.setHeight(offsetY);
                } else {
                    rect.setY(event.getY());
                    rect.setHeight(dragContext.mouseAnchorY - rect.getY());
                }
                ((Node) (event.getSource())).setCursor(Cursor.CROSSHAIR);
                event.consume();
            }
        }
    };



     private final class DragContext {

        public double mouseAnchorX;
        public double mouseAnchorY;


    }
}