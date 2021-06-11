package Components.net.raumzeitfalle.fx;

import Components.net.raumzeitfalle.fx.dirchooser.DirectoryChooserView;
import Components.net.raumzeitfalle.fx.filechooser.Skin;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DemoDirectoryChooser extends Application  {

	public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	DirectoryChooserView view = new DirectoryChooserView(Skin.DARK, null);
    	Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Demo");
        primaryStage.show();
    }

   
}
