//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Graph_ModulesLeyautTable_Engine.fxgraph.cellsTypes;

import DSI_Graph_Main_Controlers.TesterLayoutDSI;
import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Cell;
import Components.animatefx.animation.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Map;

public class ConnectorSmallCell extends Cell {
    double x;
    double y;

//    Image image = new Image(NodeCell.class.getResource("") + "connector.png");
//    Image image2 = new Image(NodeCell.class.getResource("") + "splice.png");
    public ConnectorSmallCell(String id, String name, String typeConnector, double x, double y, String Xcode, String CustomerCode, String GIMCode, String Pins, List<String> asemblyVariants) {
        super(id, "ConnectorSmallCell",Xcode,CustomerCode,GIMCode,Pins,asemblyVariants);
        this.x = x;
        this.y = y;



        //label.setText("Id: "+id);


        GridPane vBox = new GridPane();
        Text label = new Text("Id: "+id);
        super.getCellIdProperty().addListener((observableValue, s, t1) -> {
            label.setText("Id: "+t1);
        });
        label.setFill(Color.WHITE);
        Text nameLabel = new Text(name);
//        if (nameLabel.getText().isEmpty()){
//            nameLabel.setText(id);
//        }
        nameLabel.textProperty().bind(super.xcodeProperty());
        nameLabel.setFill(Color.WHITE);
        Text typeLabel = new Text(typeConnector);
        typeLabel.textProperty().bind(super.customerCodeProperty());
        typeLabel.setFill(Color.AQUA);
        Text positionLabel = new Text();
        positionLabel.textProperty().bind(super.layoutXProperty().asString("%.1f").concat(" : ").concat(super.layoutYProperty().asString("%.1f")));
        positionLabel.setFill(Color.GOLD);
        Text view = new Text("Cn.");
        view.setFill(Color.GOLD);
        view.setFont(Font.font ("Helvetica", FontWeight.BOLD, 32));;
//        ImageView view = new ImageView(image);
        super.getCellTypeProperty().addListener((observableValue, s, newType) -> {
            if (newType.equals("CONNECTORSMALL")){
                view.setText("Cn.");
                view.setFill(Color.GOLD);
//                view.setImage(image);
//                view.setFitWidth(50.0D);
//                view.setFitHeight(50.0D);
                
            }else if (newType.equals("SPLICECELL")){
                view.setText("Sp.");
                view.setFill(Color.FIREBRICK);
//                view.setImage(image2);
//                view.setFitWidth(24.0D);
//                view.setFitHeight(24.0D);
            }
        });
       // setStyle("-fx-border-color: GRAY;");
//        view.setFitWidth(50.0D);
//        view.setFitHeight(50.0D);
        try {
            for (Map.Entry<String,Color> entry : TesterLayoutDSI.allColorsWithName().entrySet()){
                if (getDescription().getValue().toLowerCase().contains(entry.getKey().toLowerCase())){
//                    Rectangle rectangle = new Rectangle();
//                    rectangle.setWidth(25);
//                    rectangle.setHeight(40);
//                    rectangle.setFill(entry.getValue());
//                    rectangle.setOpacity(.9);
                    colors.add(entry.getValue());

                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        vBox.setGridLinesVisible(true);

        vBox.add(super.colorsBox, 0, 0,2,1);
        vBox.add(view, 0, 1);vBox.add(label, 1, 1);
        vBox.add(nameLabel, 0, 2, 2, 1);
        vBox.add(typeLabel, 0, 3, 2, 1);
        vBox.add(positionLabel, 0, 4, 2, 1);
        vBox.setStyle("-fx-background-color: rgba(40, 43, 48, 0.5);");






        this.getChildren().addAll(vBox);
       // this.addEventHandler(MouseEvent.MOUSE_ENTERED, new 1(this));
       // this.addEventHandler(MouseEvent.MOUSE_EXITED, new 2(this));

//        this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
//            public void handle(MouseEvent event) {
//                vBox.setStyle("-fx-background-color: rgba(70, 73, 78, 0.5);");
//                event.consume();
//            }
//        });
//        this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
//            public void handle(MouseEvent event) {
//                vBox.setStyle("-fx-background-color: rgba(40, 43, 48, 0.5);");
//                event.consume();
//            }
//        });
//        this.setOnMouseClicked((event) -> {
//            if (event.getButton() != MouseButton.PRIMARY && event.getButton() == MouseButton.SECONDARY) {
//
//            }
//            event.consume();
//        });
        //super.nodeProperty.set(this);
        this.relocate(x, y);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }


}
