package Graph_ModulesLeyautTable_Engine.fxgraph.graph;

import javafx.scene.Node;

import java.util.HashSet;
import java.util.Set;

public class SelectionModel {


    Set<Node> selection = new HashSet<>();

    public void add( Node node) {


        if( !node.getStyleClass().contains("highlight")) {
            node.getStyleClass().add( "highlight");
            Cell cell = (Cell) node;
            cell.selectedProperty.set(true);
            //cell.dockedProperty.set(true);
            Selected selected = new Selected();
            cell.getChildren().add(selected.Selected(cell));

        }

        selection.add( node);
    }

    public void remove( Node node) {
        node.getStyleClass().remove( "highlight");
        selection.remove( node);
        Cell cell = (Cell) node;
        cell.selectedProperty.set(false);
        //cell.dockedProperty.set(false);
        cell.getChildren().removeAll(cell.getChildren().get(cell.getChildren().size()-1));
    }

    public Node getSelectionFirst() {
        return (Node) selection.toArray()[0];
    }

    public void clear() {

        while( !selection.isEmpty()) {
            remove( selection.iterator().next());
        }

    }

    public boolean contains( Node node) {
        return selection.contains(node);
    }

    public int size() {
        return selection.size();
    }

    public void log() {
        //System.out.println( "Items in model: " + Arrays.asList( selection.toArray()));
    }

}


