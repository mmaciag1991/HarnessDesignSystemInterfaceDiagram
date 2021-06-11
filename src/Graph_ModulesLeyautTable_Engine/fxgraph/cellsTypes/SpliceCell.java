package Graph_ModulesLeyautTable_Engine.fxgraph.cellsTypes;

import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Cell;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;


public class SpliceCell extends Cell {
    double x;
    double y;
//    Image image2 = new Image(NodeCell.class.getResource("") + "connector.png");
//    Image image = new Image(NodeCell.class.getResource("") + "splice.png");
    public SpliceCell(String id,String name,String typeConnector,double x, double y, String Xcode, String CustomerCode, String GIMCode, String Pins, List<String> asemblyVariants) {
        super(id,"SpliceCell",Xcode,CustomerCode,GIMCode,Pins,asemblyVariants);
        this.x = x;
        this.y = y;



        Text label = new Text("Id: "+id);
        label.setFill(Color.WHITE);
        Text nameLabel = new Text(name);
        nameLabel.setFill(Color.WHITE);
        Text typeLabel = new Text(typeConnector);
        typeLabel.setFill(Color.FIREBRICK);
        Text positionLabel = new Text();
        positionLabel.textProperty().bind(super.layoutXProperty().asString("%.1f").concat(" : ").concat(super.layoutYProperty().asString("%.1f")));
        positionLabel.setFill(Color.GOLD);

        Text view = new Text("Sp.");
        view.setFill(Color.FIREBRICK);
        view.setFont(Font.font ("Helvetica", FontWeight.BOLD, 32));;
//        ImageView view = new ImageView(image);
        super.getCellTypeProperty().addListener((observableValue, s, newType) -> {
            if (newType.equals("CONNECTORSMALL")){
                view.setText("Cn.");
                view.setFill(Color.GOLD);
//                view.setImage(image2);
//                view.setFitWidth(50.0D);
//                view.setFitHeight(50.0D);

            }else if (newType.equals("SPLICECELL")){
                view.setText("Sp.");
                view.setFill(Color.FIREBRICK);
//                view.setImage(image);
//                view.setFitWidth(24.0D);
//                view.setFitHeight(24.0D);
            }
        });

//        view.setFitWidth(24.0D);
//        view.setFitHeight(24.0D);

        VBox vBox = new VBox(view,label,nameLabel,typeLabel,positionLabel);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: rgba(40, 43, 48, 0.5);");
        this.getChildren().addAll(vBox);
       // this.setPrefSize(100,100);
        //setView(view);
//        this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                event.consume();
//            }
//        });
//        this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                event.consume();
//            }
//        });
//        this.setOnMouseClicked(event ->
//        {
//            if (event.getButton() == MouseButton.PRIMARY) {
//
//
//            }else
//            if (event.getButton() == MouseButton.SECONDARY) {
//            }
//
//            event.consume();
//        });
//        Platform.runLater(() -> {
//        super.nodeProperty.set(this);
//        });
        this.relocate(x, y);
    }
    public double getX(){return this.x;}
    public double getY(){return this.y;}

}