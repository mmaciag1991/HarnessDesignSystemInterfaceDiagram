/*
 * Copyright (c) 2018 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package Components.hansolo.medusa;

import Components.hansolo.medusa.Gauge.SkinType;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * User: hansolo
 * Date: 07.06.18
 * Time: 09:07
 */
public class MedusaTest extends Application {

    private Gauge          gauge;
    private int            count;
    private long           lastTimerCall;
    private AnimationTimer timer;

    @Override public void init() {
        gauge = GaugeBuilder.create()
                            .skinType(SkinType.GAUGE)
                            //.value(40.0)
                            //.valueVisible(false)
                            //.animated(false)
                            .build();

        count = 0;
        lastTimerCall = System.nanoTime() + 1_000_000_000l;
        timer = new AnimationTimer() {
            @Override public void handle(final long now) {
                if (now > lastTimerCall + 1_500_000_000l) {
                    if (count == 0) {
                        gauge.setValue(1303.1762704840842);
                    } else if (count == 0) {
                        gauge.setValue(901.0279253926457);
                    } else if (count == 1) {
                        gauge.setValue(304.7297527629474);
                    } else if (count == 2) {
                        gauge.setValue(-1.0859123151603925E15);
                    } else if (count == 3) {
                        gauge.setValue(-1.0859123151606368E15);
                    } else if (count == 4) {
                        gauge.setValue(1674.082843354779);
                    } else if (count == 5) {
                        gauge.setValue(-1.0859123151619634E15);
                    } else if (count == 6) {
                        gauge.setValue(858.203589043986);
                    } else if (count == 7) {
                        gauge.setValue(1495.4797707702455);
                    } else if (count == 8) {
                        gauge.setValue(5.387517262147369);
                    } else if (count == 9) {
                        gauge.setValue(187.5197189495579);
                    } else if (count == 10) {
                        gauge.setValue(1225.2107000621193);
                    } else if (count == 11) {
                        gauge.setValue(281.00658981834385);
                    } else if (count == 12) {
                        gauge.setValue(-1.0859123151624769E15);
                    } else if (count == 13) {
                        gauge.setValue(2091.5190513029193);
                    } else if (count == 14) {
                        gauge.setValue(748.2662023170526);
                    } else if (count == 15) {
                        gauge.setValue(-1.0859123151612928E15);
                    } else if (count == 16) {
                        gauge.setValue(1850.4501956466806);
                    } else if (count == 17) {
                        gauge.setValue(-1.0859123151618832E15);
                    } else if (count == 18) {
                        gauge.setValue(1595.6434090479158);
                    } else if (count == 19) {
                        gauge.setValue(-1.0859123151620781E15);
                    } else if (count == 20) {
                        gauge.setValue(1774.4972164417966);
                    } else if (count == 21) {
                        gauge.setValue(1468.5796625829053);
                    } else if (count == 22) {
                        gauge.setValue(116.07600419054035);
                    } else if (count == 23) {
                        gauge.setValue(-1.0859123151601659E15);
                    } else if (count == 24) {
                        gauge.setValue(1227.4038484404773);
                    } else if (count == 25) {
                        gauge.setValue(1034.8424890538386);
                    }
                    count++;
                    lastTimerCall = now;
                }
            }
        };

        //Platform.setImplicitExit(true);
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane(gauge);

        Scene scene = new Scene(pane, 650, 650);

        stage.setTitle("GaugeTest App");
        stage.setScene(scene);
        stage.show();

        //timer.start();
    }

    @Override public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
