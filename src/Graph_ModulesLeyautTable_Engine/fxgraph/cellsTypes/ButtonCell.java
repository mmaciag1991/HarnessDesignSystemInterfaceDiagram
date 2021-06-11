package Graph_ModulesLeyautTable_Engine.fxgraph.cellsTypes;

import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Cell;
import javafx.scene.control.Button;

public class ButtonCell extends Cell {

    public ButtonCell(String id) {

        super(id,"ButtonCell");

        Button view = new Button(id);

        //setView(view);

    }

}

