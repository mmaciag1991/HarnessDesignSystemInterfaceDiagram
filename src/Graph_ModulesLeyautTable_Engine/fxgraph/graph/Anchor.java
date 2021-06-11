package Graph_ModulesLeyautTable_Engine.fxgraph.graph;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

// a draggable anchor displayed around a point.
class Anchor extends Circle {
    Anchor(Color color, boolean canDragX, boolean canDragY, DragHandler dragHandler) {
        super(0, 0, 2);
        setFill(color.deriveColor(1, 1, 1, 0.5));
        setStroke(color);
        setStrokeWidth(1);
        setStrokeType(StrokeType.OUTSIDE);

    }

}


