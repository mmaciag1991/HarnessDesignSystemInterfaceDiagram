package Graph_ModulesLeyautTable_Engine.fxgraph.cellsTypes;

import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Cell;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class NodeCell extends Cell {

    double x;
    double y;

    public NodeCell(String id, double x, double y) {
        super(id,"NodeCell");
        this.x = x;
        this.y = y;

        Text label = new Text(id);
        label.setFill(Color.LIGHTGRAY);


        Image image = new Image(NodeCell.class.getResource("")+"node.png");
        ImageView view = new ImageView();

        view.setImage(image);
        view.setFitWidth(50);
        view.setFitHeight(50);


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(view, 0, 0);
        gridPane.add(label, 0, 1);

        getChildren().addAll(gridPane);
        setFocusTraversable(true);

        this.relocate(x, y);

    }


    public double getX(){return this.x;}
    public double getY(){return this.y;}

}