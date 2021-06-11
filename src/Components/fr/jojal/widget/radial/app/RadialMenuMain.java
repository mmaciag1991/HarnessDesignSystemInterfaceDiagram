package Components.fr.jojal.widget.radial.app;

import Components.fr.jojal.widget.radial.menu.RadialMenu;
import Components.fr.jojal.widget.radial.menu.RadialMenuContainer;
import Components.fr.jojal.widget.radial.menu.RadialMenuItem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class RadialMenuMain extends Application {

    public static void main(String... args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        AnchorPane group = new AnchorPane();
        Scene scene = new Scene(group, 800, 600);
        scene.setFill(Color.LIGHTPINK);

        ImageView logo = new ImageView(new Image ("/resources/image/ico.png"));
        logo.setFitWidth(64);
        logo.setFitHeight(64);
        logo.setOpacity(.8);
        RadialMenu radialMenu = new RadialMenu(40, 220, 180, 0, new ImageView());


        RadialMenuContainer firstContainer = new RadialMenuContainer();
        firstContainer.setText("Narzêdzia");
        firstContainer.setChildrenCenterOnParent(true);
        RadialMenuContainer secondContainer = new RadialMenuContainer();
        secondContainer.setText("Widok komponentów");
        secondContainer.setChildrenCenterOnParent(true);
        RadialMenuContainer thirdContainer = new RadialMenuContainer();
        thirdContainer.setText("Widok");
        RadialMenuItem fourthItem = new RadialMenuItem();
        fourthItem.setText("Drukuj Layout");
        RadialMenuItem sixthItem = new RadialMenuItem();
        sixthItem.setText("I6");

        RadialMenuItem firstContainerSecondStage = new RadialMenuItem(true);
        firstContainerSecondStage.setText("AutoMode");
        RadialMenuItem firstContainerSecondStage2 = new RadialMenuItem(true);
        firstContainerSecondStage2.setText("Zaznacz Kilka");
        RadialMenuItem firstContainerSecondStage3 = new RadialMenuItem(true);
        firstContainerSecondStage3.setText("Przesówanie Kursorem");

        firstContainer.addItem(firstContainerSecondStage);
        firstContainer.addItem(firstContainerSecondStage2);
        firstContainer.addItem(firstContainerSecondStage3);


        RadialMenuItem secondContainerSecondStage = new RadialMenuItem(true);
        secondContainerSecondStage.setText("Spawy");
        RadialMenuItem secondContainerSecondStage2 = new RadialMenuItem(true);
        secondContainerSecondStage2.setText("Komponenty");
        RadialMenuItem secondContainerSecondStage3 = new RadialMenuItem(true);
        secondContainerSecondStage3.setText("Inne");

        secondContainer.addItem(secondContainerSecondStage);
        secondContainer.addItem(secondContainerSecondStage2);
        secondContainer.addItem(secondContainerSecondStage3);

        RadialMenuItem thirdContainerSecondStage = new RadialMenuItem(true);
        thirdContainerSecondStage.setText("Podgl¹d Widoczny");
        RadialMenuItem thirdContainerSecondStage2 = new RadialMenuItem(true);
        thirdContainerSecondStage2.setText("Diagram Widoczny");
        RadialMenuItem thirdContainerSecondStage3 = new RadialMenuItem(true);
        thirdContainerSecondStage3.setText("Layout Widoczny");

        thirdContainer.addItem(thirdContainerSecondStage);
        thirdContainer.addItem(thirdContainerSecondStage2);
        thirdContainer.addItem(thirdContainerSecondStage3);

        radialMenu.addRootItem(fourthItem);
        radialMenu.addRootItem(firstContainer);
        radialMenu.addRootItem(secondContainer);
        radialMenu.addRootItem(thirdContainer);

        radialMenu.addRootItem(sixthItem);


        group.getStylesheets().add(getClass().getResource("radialmenu.css").toExternalForm());


        radialMenu.setVisible(false);
        logo.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                radialMenu.setVisible(!radialMenu.isVisible());
            }
        });

        group.getChildren().addAll(radialMenu,logo);
        radialMenu.setLayoutX(32);
        radialMenu.setLayoutY(500);
        logo.setLayoutX(0);
        logo.setLayoutY(500-32);


        stage.setScene(scene);
        stage.show();
    }
}
