package Graph_ModulesLeyautTable_Engine.fxgraph.cellsTypes;

import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Cell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleCell extends Cell {

    public RectangleCell( String id) {
        super( id,"RectangleCell");

        Rectangle view = new Rectangle( 50,50);

        view.setStroke(Color.DODGERBLUE);
        view.setFill(Color.DODGERBLUE);

        //setView( view);

    }

}