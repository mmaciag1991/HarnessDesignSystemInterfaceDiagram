package Components.org.reactfx.inhibeans.demo;

import Components.org.reactfx.EventStreams;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.time.Duration;

public class DeferredHtmlRendering extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TextArea textArea = new TextArea();
        WebView webView = new WebView();
        WebEngine engine = webView.getEngine();

        EventStreams.valuesOf(textArea.textProperty())
                .successionEnds(Duration.ofMillis(500))
                .subscribe(html -> engine.loadContent(html));

        SplitPane root = new SplitPane();
        root.getItems().addAll(textArea, webView);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}