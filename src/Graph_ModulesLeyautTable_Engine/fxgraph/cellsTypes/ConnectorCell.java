package Graph_ModulesLeyautTable_Engine.fxgraph.cellsTypes;

import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Cell;
import javafx.event.EventHandler;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class ConnectorCell extends Cell {
    double x;
    double y;

    public ConnectorCell(String id,double x, double y, String Xcode, String CustomerCode, String GIMCode, String Pins/*, TableView<Connectors> connectorsTableView*/) {
        super(id,"ConnectorCell",Xcode,CustomerCode,GIMCode,Pins,null);
        this.x = x;
        this.y = y;



        Rectangle view2 = new Rectangle( 100,100);
        view2.setStroke(Color.TRANSPARENT);

        TitledPane view = new TitledPane();
        view.setDisable(true);
       // view.setExpanded(false);
        view.setPrefSize(100, 100);
        view.setText(id);
       this.getChildren().addAll(view2, view);
       // this.setPrefSize(100,100);
        //setView(view);
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                view2.getParent().lookup(".title").setStyle("-fx-background-color:#00ff11;");
//System.out.println(id);
                event.consume();
            }
        });
        this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                view2.getParent().lookup(".title").setStyle("-fx-background-color:#1d1d1d;");

                event.consume();
            }
        });
//        this.setOnMouseClicked(event ->
//        {
//            if (event.getButton() == MouseButton.PRIMARY) {
//
//
//                //  modulesTable.getSelectionModel().select(0);
//
//
//
//                boolean search = true;
//                int oi = 0;
//                while (connectorsTableView.getItems().size() >= oi && search == true){
//                    if (connectorsTableView.getItems().get(oi).getXcode().equals(id)){
//                        connectorsTableView.requestFocus();
//                        connectorsTableView.getFocusModel().focus(oi);
//                        connectorsTableView.scrollTo(oi);
//                        search=false;
//                    }
//                    oi++;
//                }
//
//
//            }else
//            if (event.getButton() == MouseButton.SECONDARY) {
//            }
//            //image.setImage(img);
//            event.consume();
//        });

        this.relocate(x, y);


    }
    public double getX(){return this.x;}
    public double getY(){return this.y;}

}