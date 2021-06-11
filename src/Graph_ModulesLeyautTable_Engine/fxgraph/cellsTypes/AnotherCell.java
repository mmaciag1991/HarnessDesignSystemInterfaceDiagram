package Graph_ModulesLeyautTable_Engine.fxgraph.cellsTypes;

import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Cell;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;

public class AnotherCell extends Cell {
    double x;
    double y;

    public AnotherCell(String id, String name, String typeConnector, double x, double y, String Xcode, String CustomerCode, String GIMCode, String Pins, List<String> asemblyVariants) {
        super(id,"AnotherCell",Xcode,CustomerCode,GIMCode,Pins,asemblyVariants);
        this.x = x;
        this.y = y;



        Text label = new Text("Id: "+id);
        label.setFill(Color.WHITE);
        Text nameLabel = new Text(name);
        nameLabel.setFill(Color.WHITE);
        Text typeLabel = new Text(typeConnector);
        typeLabel.setFill(Color.BLUEVIOLET);
        Text positionLabel = new Text();
        positionLabel.textProperty().bind(super.layoutXProperty().asString("%.1f").concat(" : ").concat(super.layoutYProperty().asString("%.1f")));
        positionLabel.setFill(Color.GOLD);

//        Image image = new Image(AnotherCell.class.getResource("")+"plus15.png");
//        ImageView view = new ImageView(image);
//
//        view.setFitWidth(24);
//        view.setFitHeight(24);
//        view.setOpacity(.5);
        Text view = new Text("An.");
        view.setFill(Color.GREENYELLOW);
        view.setFont(Font.font ("Helvetica", FontWeight.BOLD, 32));;

        VBox vBox = new VBox(view,label,nameLabel,typeLabel,positionLabel);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: rgba(40, 43, 48, 0.3);");
        this.getChildren().addAll(vBox);
       // this.setPrefSize(100,100);
        //setView(view);
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                event.consume();
            }
        });
        this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                event.consume();
            }
        });
        this.setOnMouseClicked(event ->
        {
            if (event.getButton() == MouseButton.PRIMARY) {


            }else
            if (event.getButton() == MouseButton.SECONDARY) {
            }

            event.consume();
        });

        this.relocate(x, y);
    }
    public double getX(){return this.x;}
    public double getY(){return this.y;}

}