package _TryyClass;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class werwert extends Application {
    // update circle position to be centered in the viewport
    private void update() {
        Bounds viewportBounds = scrollPane.getViewportBounds();
        Bounds contentBounds = content.getBoundsInLocal();

        double hRel = scrollPane.getHvalue() / scrollPane.getHmax();
        double vRel = scrollPane.getVvalue() / scrollPane.getVmax();

        double x = Math.max(0, (contentBounds.getWidth() - viewportBounds.getWidth()) * hRel) + viewportBounds.getWidth() / 2;
        double y = Math.max(0, (contentBounds.getHeight() - viewportBounds.getHeight()) * vRel) + viewportBounds.getHeight() / 2;

        Point2D localCoordinates = content.parentToLocal(x, y);
        circle.setCenterX(localCoordinates.getX());
        circle.setCenterY(localCoordinates.getY());
    }

    private Circle circle;
    private Pane content;
    private ScrollPane scrollPane;

    @Override
    public void start(Stage primaryStage) {


            try {
                List<String> allLines = Files.readAllLines(Paths.get("C:\\Users\\mmaciag.PKC\\Desktop\\1.txt"), Charset.forName("windows-1250"));
                List<String> allLinescsv = Files.readAllLines(Paths.get("C:\\Users\\mmaciag.PKC\\Desktop\\1.csv"), Charset.forName("windows-1250"));
                for (String line : allLines) {
                    if (line.contains("TestConnection") && !line.contains("//")) {

                    String[] Comment = line.split("'");
                    String[] Conections = line.split("\"");

//                        String Fp = "";
//                        String Sp = "";
//                        String Color = "";
//                        String Phi = "";
//                        String Name = "";
                        String NewConnection = "";
                    //if (Comment.length > 1)// {
                        // System.out.println(Comment[1]);
                    //}
                    //if (Conections.length > 2) //{
                       // System.out.println(Conections[1].split(" ")[1] + " " + Conections[3].split(" ")[1]);
                    //}

                    for (String linecsv : allLinescsv) {
                        String[] strings = linecsv.split(";");
                        String Fp = strings[1];
                        String Sp = strings[7];
                        String Color = strings[13];
                        String Phi = strings[14];
                        String Name = strings[16];
                        //  System.out.println(Fp+";"+Sp+";"+Color+";"+Phi+";"+Name+";");
                        if ( Comment[1].contains(Name) && Fp.equals(Conections[1].split(" ")[1]) && Sp.equals(Conections[3].split(" ")[1]) ){
                            if (Color.equals("SW")){
                                Color = "BK";
                            }else
                            if (Color.equals("BR")){
                                Color = "BN";
                            }else
                            if (Color.equals("BL")){
                                Color = "BU";
                            }else
                            if (Color.equals("RT")){
                                Color = "RD";
                            }else
                            if (Color.equals("VL")){
                                Color = "VT";
                            }else
                            if (Color.equals("WS")){
                                Color = "WH";
                            }else
                            if (Color.equals("GE")){
                                Color = "YE";
                            }
                            //String NewComment = Comment[1].substring(0,Comment[1].length()-1) + " /"+Color+" P:"+Phi+"'";
                            NewConnection = "TestConnection('"+Comment[1].substring(0,Comment[1].length())+" /"+Color+" P:"+Phi+"', \""+Conections[1]+"\", \""+Conections[3]+"\");";
                        }else
                        {
                           // NewConnection = line;
                        }

                    }
                   // if (!NewConnection.equals(""))
                       System.out.println(NewConnection);

                }
                    else {System.out.println(line);}
            }
            } catch (IOException e) {
                e.printStackTrace();
            }



        // create ui
        circle = new Circle(10);
        content = new Pane(circle);
        content.setPrefSize(4000, 4000);
        scrollPane = new ScrollPane(content);
        Scene scene = new Scene(scrollPane, 400, 400);

        // add listener to properties that may change
        InvalidationListener l = o -> update();
        content.layoutBoundsProperty().addListener(l);
        scrollPane.viewportBoundsProperty().addListener(l);
        scrollPane.hvalueProperty().addListener(l);
        scrollPane.vvalueProperty().addListener(l);
        scrollPane.hmaxProperty().addListener(l);
        scrollPane.vmaxProperty().addListener(l);
        scrollPane.hminProperty().addListener(l);
        scrollPane.vminProperty().addListener(l);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
