package Graph_ModulesLeyautTable_Engine.fxgraph.graph;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RubberBandSelectionCopy {

   final DragContext dragContext = new DragContext();
   Rectangle rect;
   SelectionModel selectionModel;
   Pane group;
   boolean enabled = false;
   Timeline timeline;
   Model model;

    double minX = 0;
    double maxX = 0;
    double minY = 0;
    double maxY = 0;


   public RubberBandSelectionCopy(Pane group, SelectionModel selectionModel, Model model) {
       this.model = model;
       this.selectionModel = selectionModel;
       this.group = group;
       rect = new Rectangle();
       rect.setFill(Color.rgb(29, 64, 57,.4));
       rect.setWidth(1);
       rect.setHeight(1);
       rect.setStrokeWidth(1);
       rect.setStroke(Color.WHITE);
       rect.getStrokeDashArray().addAll(3.0, 7.0, 3.0, 7.0);
       rect.setMouseTransparent(true);
       final double maxOffset =
               rect.getStrokeDashArray().stream()
                       .reduce(
                               0d,
                               (a, b) -> a + b
                       );

        timeline = new Timeline(
               new KeyFrame(
                       Duration.ZERO,
                       new KeyValue(
                               rect.strokeDashOffsetProperty(),
                               0,
                               Interpolator.LINEAR
                       )
               ),
               new KeyFrame(
                       Duration.seconds(2),
                       new KeyValue(
                               rect.strokeDashOffsetProperty(),
                               maxOffset,
                               Interpolator.LINEAR
                       )
               )
       );
       timeline.setCycleCount(Timeline.INDEFINITE);



   }

    public void setSelectionEnabled(boolean enabled) {
    if (enabled){
        group.addEventHandler(MouseEvent.MOUSE_PRESSED, onMousePressedEventHandler);
        group.addEventHandler(MouseEvent.MOUSE_DRAGGED, onMouseDraggedEventHandler);
        group.addEventHandler(MouseEvent.MOUSE_RELEASED, onMouseReleasedEventHandler);
        group.addEventHandler(MouseEvent.MOUSE_CLICKED, onMouseClickedEventHandler);

    }else {
        group.removeEventHandler(MouseEvent.MOUSE_PRESSED, onMousePressedEventHandler);
        group.removeEventHandler(MouseEvent.MOUSE_DRAGGED, onMouseDraggedEventHandler);
        group.removeEventHandler(MouseEvent.MOUSE_RELEASED, onMouseReleasedEventHandler);
        group.removeEventHandler(MouseEvent.MOUSE_CLICKED, onMouseClickedEventHandler);

    }
    }

    public EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

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

    public EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

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
               if (selectionModel.selection.size() > 0){

                   minX = selectionModel.getSelectionFirst().getLayoutX();
                   maxX = selectionModel.getSelectionFirst().getLayoutX();
                   minY = selectionModel.getSelectionFirst().getLayoutY();
                   maxY = selectionModel.getSelectionFirst().getLayoutY();

               for (Node node : selectionModel.selection) {
                   node.toFront();

                   if (node.getLayoutX() < minX)
                       minX = node.getLayoutX();
                   if (node.getLayoutX() > maxX)
                       maxX = node.getLayoutX();
                   if (node.getLayoutY() < minY)
                       minY = node.getLayoutY();
                   if (node.getLayoutY() > maxY) {
                       maxY = node.getLayoutY();
                   }
               }
               rect.setX(minX - 10);
               rect.setY(minY - 10);
               rect.setWidth((maxX - minX) + 110 + 10);
               rect.setHeight((maxY - minY) + 110 + 10);
           }
//
//                group.getChildren().remove(rect);

               ((Node) (event.getSource())).setCursor(Cursor.DEFAULT);
               enabled = false;
               event.consume();
           }

       }

   };
    public EventHandler<MouseEvent> onMouseClickedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            //System.out.println("Source: " + event.getSource());
            if (!enabled){
            rect.setX(0);
            rect.setY(0);
            rect.setWidth(0);
            rect.setHeight(0);

            group.getChildren().remove(rect);
            //   enabled = false;
            event.consume();
            timeline.stop();
        }
           }

    };

    public EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

       @Override
       public void handle(MouseEvent event) {
           if (event.getButton().equals(MouseButton.PRIMARY)) {
               double offsetX = event.getX() - dragContext.mouseAnchorX;
               double offsetY = event.getY() - dragContext.mouseAnchorY;

               timeline.play();
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