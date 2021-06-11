package Components;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/** Animates a node on and off screen to the left. */
public class SideBar extends HBox {


    /** creates a sidebar containing a vertical alignment of the given nodes */
    public SideBar(Node... nodes) {
        // create a bar to hide and show.
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color:transparent;");
        getChildren().addAll(nodes);


    }

    Animation showSidebarProperthy = null;
    boolean lastStatus = false;

    public Animation getShowSidebarProperthy() {
        return showSidebarProperthy;
    }

    // apply the animations when the button is pressed.
            public void setShowSidebar(boolean show){
        //System.out.println("Show: "+show);
            // create an animation to hide sidebar.
            final double startHeight = getHeight();
            final Animation hideSidebar = new Transition() {
                { setCycleDuration(Duration.millis(550)); }
                protected void interpolate(double frac) {
                    final double curHeight = startHeight * (1.0 - frac);
                    setTranslateY(startHeight - curHeight);
                }
            };

                // create an animation to show a sidebar.
                final Animation showSidebar = new Transition() {
                    { setCycleDuration(Duration.millis(550)); }
                    protected void interpolate(double frac) {
                        final double curWidth = startHeight * frac;
                        setTranslateY(startHeight - curWidth);
                    }
                };

            hideSidebar.onFinishedProperty().set(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent actionEvent) {
                    if (showSidebarProperthy.statusProperty().get() != Animation.Status.RUNNING)
                    setVisible(false);

                }
            });


            showSidebar.onFinishedProperty().set(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent actionEvent) {
                    setVisible(true);
                }
            });

            if (showSidebar.statusProperty().get() != Animation.Status.RUNNING && hideSidebar.statusProperty().get() != Animation.Status.RUNNING) {
                if (!show && isVisible()) {
                    hideSidebar.play();
                } else {
                    if (!lastStatus) {
                        setVisible(true);
                        showSidebar.play();
                    }
                }
            }

            showSidebarProperthy = showSidebar;
            lastStatus = show;
        }
    }
