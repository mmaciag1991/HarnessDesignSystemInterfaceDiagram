package DSI_Graph_Main_Controlers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;



public class Preloader {
    @FXML
    private Label textLabel;
    @FXML
    private ProgressBar progressBar;

    public Preloader(){

    }
    public void setProgress(double progress){
            progressBar.setProgress(progress);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setText(String text){
        this.textLabel.setText("Trwa inicjalizacja danych: "+text);
    }
    @FXML
    private void initialize(){

    }
}
