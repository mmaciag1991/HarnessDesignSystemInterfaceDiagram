/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2016-2020 Gerrit Grunwald.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Components.hansolo.tilesfx;

import Components.hansolo.tilesfx.Tile.SkinType;
import Components.hansolo.tilesfx.addons.YearChart;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;


/**
 * Just an internal class for testing the library
 * User: hansolo
 * Date: 13.10.17
 * Time: 14:52
 */
public class Test extends Application {
    private static final Random          RND       = new Random();
    private static final double          SIZE      = 400;
    private static final double          WIDTH     = 400;
    private static final double          HEIGHT    = 400;
    private static       int             noOfNodes = 0;
    private              Tile            tile1;
    private              Tile            tile2;
    private              VBox            yearBox;
    private              DoubleProperty  value;
    private              long            lastTimerCall;
    private AnimationTimer               timer;



    @Override public void init() {
        value = new SimpleDoubleProperty();

        tile1 = TileBuilder.create()
                           .skinType(SkinType.FIRE_SMOKE)
                           .prefSize(WIDTH, HEIGHT)
                           .title("Tile 1")
                           .threshold(40)
                           .animated(false)
                           .build();

        tile2 = TileBuilder.create()
                           .skinType(SkinType.FIRE_SMOKE)
                           .prefSize(WIDTH, HEIGHT)
                           .title("Tile 2")
                           .threshold(40)
                           .animated(false)
                           .build();

        yearBox = new VBox();
        for (int year = 2010 ; year < 2021 ; year++) {
            YearChart yearChart = new YearChart(Integer.toString(year), Color.WHITE, true, true);
            for (int i = 1 ; i < 13 ; i++) {
                yearChart.set(i, RND.nextDouble() * 2.18 - 0.8);
            }
            Label yearLabel = new Label(yearChart.getText());
            yearLabel.setAlignment(Pos.CENTER_LEFT);
            yearLabel.setPrefWidth(40);
            HBox hBox = new HBox(5, yearLabel, yearChart);
            hBox.setAlignment(Pos.CENTER_LEFT);
            yearBox.getChildren().add(hBox);
        }


        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(final long now) {
                if (now > lastTimerCall + 5_000_000_000l) {
                    tile1.setValue(RND.nextDouble() * tile1.getRange() + tile1.getMinValue());
                    tile2.setValue(RND.nextDouble() * tile2.getRange() + tile2.getMinValue());
                    lastTimerCall = now;
                }
            }
        };
    }

    @Override public void start(Stage stage) {
        //StackPane pane = new StackPane(yearBox);
        HBox pane = new HBox(20, tile1, tile2);
        pane.setPadding(new Insets(10));

        Scene scene = new Scene(pane);

        stage.setTitle("Test");
        stage.setScene(scene);
        stage.show();

        // Calculate number of nodes
        calcNoOfNodes(pane);
        //System.out.println(noOfNodes + " Nodes in SceneGraph");

        timer.start();
    }

    @Override public void stop() {
        Platform.exit();
        System.exit(0);
    }

    private static void calcNoOfNodes(Node node) {
        if (node instanceof Parent) {
            if (((Parent) node).getChildrenUnmodifiable().size() != 0) {
                ObservableList<Node> tempChildren = ((Parent) node).getChildrenUnmodifiable();
                noOfNodes += tempChildren.size();
                for (Node n : tempChildren) { calcNoOfNodes(n); }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
