package Components.Preloader;

import Components.Preloader.ui.RingProgressIndicator;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.text.DecimalFormat;

public class LoadingIndicator extends Parent{

    private Timeline timeline = new Timeline();
    Text label = new Text("Loading...");
    String text = "";
    String current= "";

    RingProgressIndicator indicator = new RingProgressIndicator();

    public void setText(String text){
        this.text = text;
    }
    public void setCurrent(String text){
        this.current = text;
    }
    DecimalFormat df = new DecimalFormat("####0.00");
    public void setProgress(double progress) {
        indicator.setProgress(progress);
        label.setText(text+": "+df.format(progress) + "%" + current);

        if (progress==100){


        }
    }

    public LoadingIndicator(){
        super();


        VBox root = new VBox(3);
        root.setAlignment(Pos.CENTER);


        root.getChildren().add(indicator);
        root.getChildren().add(label);

        label.setFill(Color.AQUA);

        this.setOpacity(0.5);



        getChildren().add(root);

    }

}