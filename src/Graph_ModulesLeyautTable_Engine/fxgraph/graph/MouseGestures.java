package Graph_ModulesLeyautTable_Engine.fxgraph.graph;


import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.apache.commons.math3.util.FastMath;

import java.text.DecimalFormat;

public class MouseGestures {

    final DragContext dragContext = new DragContext();
//
   public Graph graph;
   public Edge edge;

   public BooleanProperty tooShort = new SimpleBooleanProperty(false);
   public String tooShortEdgeName = "";


    public MouseGestures(Graph_ModulesLeyautTable_Engine.fxgraph.graph.Graph graph) {

        this.graph = graph;
    }

    public void makeDraggable( final Node node) {

//
        node.setOnMousePressed(onMousePressedEventHandler);
        node.setOnMouseDragged(onMouseDraggedEventHandler);
        node.setOnMouseReleased(onMouseReleasedEventHandler);


    }

    EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

            Node node = (Node) event.getSource();
            double scale = graph.getScale();

            dragContext.x = node.getBoundsInParent().getMinX() * scale - event.getScreenX();
            dragContext.y = node.getBoundsInParent().getMinY()  * scale - event.getScreenY();


        }
    };

    EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {


            Node node = (Node) event.getSource();

            double offsetX = event.getScreenX() + dragContext.x;
            double offsetY = event.getScreenY() + dragContext.y;

            // adjust the offset in case we are zoomed
            double scale = graph.getScale();

            offsetX /= scale;
            offsetY /= scale;

            node.relocate(offsetX, offsetY);



        }    };

    EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

        }
    };

    public synchronized void calculateEdges(Node node){

        Cell cell = (Cell) node;
        tooShort.set(false);
        tooShortEdgeName = "";


            for (Edge edge : graph.getModel().getAllEdges()){

                if (edge.getSource().equals(cell) || edge.getTarget().equals(cell)) {

            calcutateThisEdge(edge);

                }

        }
    }
    static DecimalFormat df = new DecimalFormat("#.##");
//    double sourceLayoutX;
//    double sourceLayoutY;
//    double targetLayoutX;
//    double targetLayoutY;


    public synchronized void calcutateThisEdge(Edge edge){
        //double lenght = edge.getLenght();
        /// //System.out.println(lenght);//
//        Cell source = edge.getSource();
//        Cell target = edge.getTarget();
//         ;
//        sourceLayoutX = source.layoutXProperty().add(source.getBoundsInParent().getWidth() / 2.0).get();
//        sourceLayoutY = source.layoutYProperty().add(source.getBoundsInParent().getHeight() / 2.0).get();
//        targetLayoutX = target.layoutXProperty().add(target.getBoundsInParent().getWidth() / 2.0).get();
//        targetLayoutY = target.layoutYProperty().add(target.getBoundsInParent().getHeight() / 2.0).get();



        double distance = /*Math.sqrt((targetLayoutY - sourceLayoutY) * (targetLayoutY - sourceLayoutY) +
                (targetLayoutX - sourceLayoutX) * (targetLayoutX - sourceLayoutX));*/

                FastMath.sqrt(FastMath.pow(FastMath.abs(edge.getTarget().layoutXProperty().add(edge.getTarget().getBoundsInParent().getWidth() / 2.0).get() - edge.getSource().layoutXProperty().add(edge.getSource().getBoundsInParent().getWidth() / 2.0).get()), 2)
                + FastMath.pow(FastMath.abs(edge.getTarget().layoutYProperty().add(edge.getTarget().getBoundsInParent().getHeight() / 2.0).get() - edge.getSource().layoutYProperty().add(edge.getSource().getBoundsInParent().getHeight() / 2.0).get()), 2));



        /** dostosowanie skali podzielone na 2  */
        edge.getTitledPane().setText(String.valueOf(df.format(distance / 2)) + " mm \n/" + edge.getLenght() + " mm");
        //edge.getTitledPane().setPrefSize(100, 45);
        edge.setDistance(distance);
        /** dostosowanie skali pomno¿one na 2  */
        if (distance > edge.getLenght() * 2) {
            Platform.runLater(() -> {
            edge.getTitledPane().setStyle("-fx-background-color: red;");
//            edge.getTitledPane().getTooltip().setStyle(" -fx-background-color:linear-gradient(rgba(143, 28, 20, .5)  , rgba(120, 20, 13, .5)  );");
            edge.getLine().setStroke(Color.RED);
            edge.getLine().setFill(Color.RED);
            //edge.getLine().setStyle("-fx-effect: dropshadow(gaussian, red, 20, 0, 0, 0);");
        });
            tooShort.set(true);
            //tooShortEdgeName = tooShortEdgeName + "Odleg³oœæ pomiêdzy " +  edge.source.getCellId() + " -> " + edge.target.getCellId() + " zosta³¹ przekroczona!\n";

            edge.setToShort(true);
        } else {
            Platform.runLater(() -> {
            edge.getTitledPane().setStyle("-fx-background-color: green;");
//            edge.getTitledPane().getTooltip().setStyle(" -fx-background-color:linear-gradient(rgba(20, 94, 8, .5)   , rgba(14, 64, 6, .5)   );");
            edge.getLine().setStroke(edge.getBaseColor());
            edge.getLine().setFill(edge.getBaseColor());
           // edge.getLine().setStyle("-fx-effect: dropshadow(gaussian, rgb("+edge.getBaseColor().getRed()+","+edge.getBaseColor().getGreen()+","+edge.getBaseColor().getBlue()+"), 3, 0, 0, 0);");
        });
            edge.setToShort(false);
        }
    }

    class DragContext {

        double x;
        double y;

    }
}