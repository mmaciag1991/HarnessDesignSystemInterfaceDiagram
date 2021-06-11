package Graph_ModulesLeyautTable_Engine.fxgraph.cellsTypes;

import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Cell;
import javafx.scene.control.Label;

public class LabelCell extends Cell {

    public LabelCell(String id) {
        super(id,"LabelCell");

        Label view = new Label(id);

        //setView(view);

    }

}