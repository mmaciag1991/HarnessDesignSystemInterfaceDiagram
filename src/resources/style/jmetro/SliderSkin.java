/*
 * Copyright (c) 2011-2020 JFXtras
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *      * Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      * Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *      * Neither the name of the organization nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package resources.style.jmetro;

import com.sun.javafx.scene.control.behavior.SliderBehavior;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.beans.property.BooleanProperty;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableBooleanProperty;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.css.converter.BooleanConverter;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.SkinBase;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SliderSkin extends BehaviorSkinBase<Slider, SliderBehavior> {

    /** Track if slider is vertical/horizontal and cause re layout */
//    private boolean horizontal;
    private NumberAxis tickLine = null;
    private double trackToTickGap = 2;

    private boolean showTickMarks;
    private double thumbWidth;
    private double thumbHeight;

    private double trackStart;
    private double trackLength;
    private double thumbTop;
    private double thumbLeft;
    private double preDragThumbPos;
    private Point2D dragStart; // in skin coordinates

    private StackPane thumb;
    private StackPane track;
    private StackPane fill;
    private boolean trackClicked = false;//    private double visibleAmount = 16;


    private SliderPopup popup = new SliderPopup();
    private static final int POPUP_DISTANCE_FROM_THUMB = 50;
    private static final Duration POPUP_FADE_DURATION = Duration.millis(200);

    public SliderSkin(Slider slider) {
        super(slider, new SliderBehavior(slider));

        initialize();
        slider.requestLayout();
        registerChangeListener(slider.minProperty(), "MIN");
        registerChangeListener(slider.maxProperty(), "MAX");
        registerChangeListener(slider.valueProperty(), "VALUE");
        registerChangeListener(slider.orientationProperty(), "ORIENTATION");
        registerChangeListener(slider.showTickMarksProperty(), "SHOW_TICK_MARKS");
        registerChangeListener(slider.showTickLabelsProperty(), "SHOW_TICK_LABELS");
        registerChangeListener(slider.majorTickUnitProperty(), "MAJOR_TICK_UNIT");
        registerChangeListener(slider.minorTickCountProperty(), "MINOR_TICK_COUNT");
        registerChangeListener(slider.labelFormatterProperty(), "TICK_LABEL_FORMATTER");
    }

    private void initialize() {
        thumb = new StackPane();
        thumb.getStyleClass().setAll("thumb");
        track = new StackPane();
        track.getStyleClass().setAll("track");
        fill = new StackPane();
        fill.getStyleClass().setAll("fill");

        getChildren().setAll(track, fill, thumb);
        setShowTickMarks(getSkinnable().isShowTickMarks(), getSkinnable().isShowTickLabels());

        track.setOnMousePressed(this::mousePressedOnTrack);
        track.setOnMouseDragged(this::mouseDraggedOnTrack);
        fill.setOnMousePressed(this::mousePressedOnTrack);
        fill.setOnMouseDragged(this::mouseDraggedOnTrack);
        fill.setOnMouseReleased(this::mouseReleasedFromTrack);

        thumb.setOnMousePressed(me -> {
            getBehavior().thumbPressed(me, 0.0f);
            dragStart = thumb.localToParent(me.getX(), me.getY());
            preDragThumbPos = (getSkinnable().getValue() - getSkinnable().getMin()) /
                    (getSkinnable().getMax() - getSkinnable().getMin());

            showValuePopup();
        });

        thumb.setOnMouseReleased(me -> {
            getBehavior().thumbReleased(me);

            hideValuePopup();
        });

        thumb.setOnMouseDragged(me -> {
            Point2D cur = thumb.localToParent(me.getX(), me.getY());
            double dragPos = (getSkinnable().getOrientation() == Orientation.HORIZONTAL)?
                    cur.getX() - dragStart.getX() : -(cur.getY() - dragStart.getY());
            getBehavior().thumbDragged(me, preDragThumbPos + dragPos / trackLength);

            displaceValuePopup();
        });

        track.setOnMouseReleased(this::mouseReleasedFromTrack);
    }

    private void showValuePopup() {
        if (!isShowValueOnInteraction()) {
            return;
        }


        popup.setValue(getSkinnable().getValue());

        Point2D thumbScreenPos = thumb.localToScreen(thumb.getBoundsInLocal().getMinX(), thumb.getBoundsInLocal().getMinY());

        Orientation orientation = getSkinnable().getOrientation();
        if (orientation.equals(Orientation.HORIZONTAL)) {
            popup.show(thumb, thumbScreenPos.getX() + thumb.getWidth() / 2, thumbScreenPos.getY() - POPUP_DISTANCE_FROM_THUMB);
            popup.setX(popup.getX() - popup.getWidth() / 2); /* We only know the Popup's bounds after we show it */
        } else if (orientation.equals(Orientation.VERTICAL)){
            popup.show(thumb, thumbScreenPos.getX() - POPUP_DISTANCE_FROM_THUMB, thumbScreenPos.getY() + thumb.getHeight() / 2);
            popup.setY(popup.getY() - popup.getHeight() / 2); /* We only know the Popup's bounds after we show it */
        }

        FadeTransition fadeInTransition = new FadeTransition(POPUP_FADE_DURATION, popup.getScene().getRoot());
        fadeInTransition.setFromValue(0);
        fadeInTransition.setToValue(1);
        fadeInTransition.play();
    }

    private void displaceValuePopup() {
        if (!isShowValueOnInteraction()) {
            return;
        }

        if (popup.isShowing()) {
            popup.setValue(getSkinnable().getValue());

            Point2D thumbScreenPos = thumb.localToScreen(thumb.getBoundsInLocal().getMinX(), thumb.getBoundsInLocal().getMinY());

            Orientation orientation = getSkinnable().getOrientation();
            if (orientation.equals(Orientation.HORIZONTAL)) {
                popup.setX(thumbScreenPos.getX() + thumb.getWidth() / 2 - popup.getWidth() / 2);
            } else if (orientation.equals(Orientation.VERTICAL)) {
                popup.setY(thumbScreenPos.getY() + thumb.getHeight() / 2 - popup.getHeight() / 2);
            }
        }
    }

    private void hideValuePopup() {
        if (!isShowValueOnInteraction()) {
            return;
        }

        FadeTransition fadeOutTransition = new FadeTransition(POPUP_FADE_DURATION, popup.getScene().getRoot());
        fadeOutTransition.setFromValue(1);
        fadeOutTransition.setToValue(0);
        fadeOutTransition.setOnFinished(actionEvent -> popup.hide());

        fadeOutTransition.play();
    }

    private void mousePressedOnTrack(MouseEvent mouseEvent)
    {
        if (!thumb.isPressed()) {
            if (getSkinnable().getOrientation() == Orientation.HORIZONTAL) {
                getBehavior().trackPress(mouseEvent, (mouseEvent.getX() / trackLength));
            } else {
                getBehavior().trackPress(mouseEvent, (mouseEvent.getY() / trackLength));
            }
        }

        showValuePopup();
    }

    private void mouseDraggedOnTrack(MouseEvent mouseEvent)
    {
        if (!thumb.isPressed()) {
            if (getSkinnable().getOrientation() == Orientation.HORIZONTAL) {
                getBehavior().trackPress(mouseEvent, (mouseEvent.getX() / trackLength));
            } else {
                getBehavior().trackPress(mouseEvent, (mouseEvent.getY() / trackLength));
            }
        }


        displaceValuePopup();
    }

    private void mouseReleasedFromTrack(MouseEvent mouseEvent) {
        hideValuePopup();
    }

    private StringConverter<Number> stringConverterWrapper = new StringConverter<Number>() {
        Slider slider = getSkinnable();
        @Override public String toString(Number object) {
            return(object != null) ? slider.getLabelFormatter().toString(object.doubleValue()) : "";
        }
        @Override public Number fromString(String string) {
            return slider.getLabelFormatter().fromString(string);
        }
    };

    private void setShowTickMarks(boolean ticksVisible, boolean labelsVisible) {
        showTickMarks = (ticksVisible || labelsVisible);
        Slider slider = getSkinnable();
        if (showTickMarks) {
            if (tickLine == null) {
                tickLine = new NumberAxis();
                tickLine.setAutoRanging(false);
                tickLine.setSide(slider.getOrientation() == Orientation.VERTICAL ? Side.RIGHT : (slider.getOrientation() == null) ? Side.RIGHT: Side.BOTTOM);
                tickLine.setUpperBound(slider.getMax());
                tickLine.setLowerBound(slider.getMin());
                tickLine.setTickUnit(slider.getMajorTickUnit());
                tickLine.setTickMarkVisible(ticksVisible);
                tickLine.setTickLabelsVisible(labelsVisible);
                tickLine.setMinorTickVisible(ticksVisible);
                // add 1 to the slider minor tick count since the axis draws one
                // less minor ticks than the number given.
                tickLine.setMinorTickCount(Math.max(slider.getMinorTickCount(),0) + 1);
                if (slider.getLabelFormatter() != null) {
                    tickLine.setTickLabelFormatter(stringConverterWrapper);
                }
                getChildren().clear();
                getChildren().addAll(tickLine, track, fill, thumb);
            } else {
                tickLine.setTickLabelsVisible(labelsVisible);
                tickLine.setTickMarkVisible(ticksVisible);
                tickLine.setMinorTickVisible(ticksVisible);
            }
        }
        else  {
            getChildren().clear();
            getChildren().addAll(track, fill, thumb);
//            tickLine = null;
        }

        getSkinnable().requestLayout();
    }

    @Override protected void handleControlPropertyChanged(String p) {
        super.handleControlPropertyChanged(p);
        Slider slider = getSkinnable();
        if ("ORIENTATION".equals(p)) {
            if (showTickMarks && tickLine != null) {
                tickLine.setSide(slider.getOrientation() == Orientation.VERTICAL ? Side.RIGHT : (slider.getOrientation() == null) ? Side.RIGHT: Side.BOTTOM);
            }
            getSkinnable().requestLayout();
        } else if ("VALUE".equals(p)) {
            // only animate thumb if the track was clicked - not if the thumb is dragged
            positionThumb(trackClicked);
        } else if ("MIN".equals(p) ) {
            if (showTickMarks && tickLine != null) {
                tickLine.setLowerBound(slider.getMin());
            }
            getSkinnable().requestLayout();
        } else if ("MAX".equals(p)) {
            if (showTickMarks && tickLine != null) {
                tickLine.setUpperBound(slider.getMax());
            }
            getSkinnable().requestLayout();
        } else if ("SHOW_TICK_MARKS".equals(p) || "SHOW_TICK_LABELS".equals(p)) {
            setShowTickMarks(slider.isShowTickMarks(), slider.isShowTickLabels());
        }  else if ("MAJOR_TICK_UNIT".equals(p)) {
            if (tickLine != null) {
                tickLine.setTickUnit(slider.getMajorTickUnit());
                getSkinnable().requestLayout();
            }
        } else if ("MINOR_TICK_COUNT".equals(p)) {
            if (tickLine != null) {
                tickLine.setMinorTickCount(Math.max(slider.getMinorTickCount(),0) + 1);
                getSkinnable().requestLayout();
            }
        } else if ("TICK_LABEL_FORMATTER".equals(p)) {
            if (tickLine != null) {
                if (slider.getLabelFormatter() == null) {
                    tickLine.setTickLabelFormatter(null);
                } else {
                    tickLine.setTickLabelFormatter(stringConverterWrapper);
                    tickLine.requestAxisLayout();
                }
            }
        }
    }

    /**
     * Called when ever either min, max or value changes, so thumb's layoutX, Y is recomputed.
     */
    private void positionThumb(final boolean animate) {
        Slider s = getSkinnable();
        if (s.getValue() > s.getMax()) return;// this can happen if we are bound to something
        boolean horizontal = s.getOrientation() == Orientation.HORIZONTAL;
        final double endX = (horizontal) ? trackStart + (((trackLength * ((s.getValue() - s.getMin()) /
                (s.getMax() - s.getMin()))) - thumbWidth/2)) : thumbLeft;
        final double endY = (horizontal) ? thumbTop :
                snappedTopInset() + trackLength - (trackLength * ((s.getValue() - s.getMin()) /
                        (s.getMax() - s.getMin()))); //  - thumbHeight/2

        if (animate) {
            // lets animate the thumb transition
            final double startX = thumb.getLayoutX();
            final double startY = thumb.getLayoutY();
            Transition transition = new Transition() {
                {
                    setCycleDuration(Duration.millis(200));
                }

                @Override protected void interpolate(double frac) {
                    if (!Double.isNaN(startX)) {
                        thumb.setLayoutX(startX + frac * (endX - startX));
                    }
                    if (!Double.isNaN(startY)) {
                        thumb.setLayoutY(startY + frac * (endY - startY));
                    }
                }
            };
            transition.play();
        } else {
            thumb.setLayoutX(endX);
            thumb.setLayoutY(endY);
        }



    }

    @Override protected void layoutChildren(final double x, final double y,
                                            final double w, final double h) {
        // calculate the available space
        // resize thumb to preferred size
        thumbWidth = snapSize(thumb.prefWidth(-1));
        thumbHeight = snapSize(thumb.prefHeight(-1));
        thumb.resize(thumbWidth, thumbHeight);
        // we are assuming the is common radius's for all corners on the track
        double trackRadius = track.getBackground() == null ? 0 : track.getBackground().getFills().size() > 0 ?
                track.getBackground().getFills().get(0).getRadii().getTopLeftHorizontalRadius() : 0;

        if (getSkinnable().getOrientation() == Orientation.HORIZONTAL) {
            double tickLineHeight =  (showTickMarks) ? tickLine.prefHeight(-1) : 0;
            double trackHeight = snapSize(track.prefHeight(-1));
            double trackAreaHeight = Math.max(trackHeight,thumbHeight);
            double totalHeightNeeded = trackAreaHeight  + ((showTickMarks) ? trackToTickGap+tickLineHeight : 0);
            double startY = y + ((h - totalHeightNeeded)/2); // center slider in available height vertically
            trackLength = snapSize(w - thumbWidth);
            trackStart = snapPosition(x + (thumbWidth/2));
            double trackTop = (int)(startY + ((trackAreaHeight-trackHeight)/2));
            thumbTop = (int)(startY + ((trackAreaHeight-thumbHeight)/2));

            positionThumb(false);
            // layout track
            track.resizeRelocate((int)(trackStart - trackRadius),
                    trackTop ,
                    (int)(trackLength + trackRadius + trackRadius),
                    trackHeight);
            fill.resizeRelocate((int)(trackStart - trackRadius),
                    trackTop ,
                    ((int)trackStart - trackRadius) + thumb.getLayoutX(),
                    trackHeight);
            // layout tick line
            if (showTickMarks) {
                tickLine.setLayoutX(trackStart);
                tickLine.setLayoutY(trackTop+trackHeight+trackToTickGap);
                tickLine.resize(trackLength, tickLineHeight);
                tickLine.requestAxisLayout();
            } else {
                if (tickLine != null) {
                    tickLine.resize(0,0);
                    tickLine.requestAxisLayout();
                }
                tickLine = null;
            }
        } else { // VERTICAL
            double tickLineWidth = (showTickMarks) ? tickLine.prefWidth(-1) : 0;
            double trackWidth = snapSize(track.prefWidth(-1));
            double trackAreaWidth = Math.max(trackWidth,thumbWidth);
            double totalWidthNeeded = trackAreaWidth  + ((showTickMarks) ? trackToTickGap+tickLineWidth : 0) ;
            double startX = x + ((w - totalWidthNeeded) / 2); // center slider in available width horizontally
            trackLength = snapSize(h - thumbHeight);
            trackStart = snapPosition(y + (thumbHeight / 2));
            double trackLeft = (int)(startX + ((trackAreaWidth-trackWidth) / 2));
            thumbLeft = (int)(startX + ((trackAreaWidth-thumbWidth) / 2));

            positionThumb(false);
            // layout track
            track.resizeRelocate(trackLeft,
                    (int)(trackStart - trackRadius),
                    trackWidth,
                    (int)(trackLength + trackRadius + trackRadius));
            fill.resizeRelocate(trackLeft,
                    ((int)trackStart - trackRadius) + thumb.getLayoutY(),
                    trackWidth,
                    trackLength - thumb.getLayoutY());
            // layout tick line
            if (showTickMarks) {
                tickLine.setLayoutX(trackLeft+trackWidth+trackToTickGap);
                tickLine.setLayoutY(trackStart);
                tickLine.resize(tickLineWidth, trackLength);
                tickLine.requestAxisLayout();
            } else {
                if (tickLine != null) {
                    tickLine.resize(0,0);
                    tickLine.requestAxisLayout();
                }
                tickLine = null;
            }
        }
    }

    private double minTrackLength() {
        return 2 * thumb.prefWidth(-1);
    }

    @Override protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        final Slider s = getSkinnable();
        if (s.getOrientation() == Orientation.HORIZONTAL) {
            return (leftInset + minTrackLength() + thumb.minWidth(-1) + rightInset);
        } else {
            return(leftInset + thumb.prefWidth(-1) + rightInset);
        }
    }

    @Override protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        final Slider s = getSkinnable();
        if (s.getOrientation() == Orientation.HORIZONTAL) {
            return(topInset + thumb.prefHeight(-1) + bottomInset);
        } else {
            return(topInset + minTrackLength() + thumb.prefHeight(-1) + bottomInset);
        }
    }

    @Override protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        final Slider s = getSkinnable();
        if (s.getOrientation() == Orientation.HORIZONTAL) {
            if(showTickMarks) {
                return Math.max(140, tickLine.prefWidth(-1));
            } else {
                return 140;
            }
        } else {
            return leftInset + Math.max(thumb.prefWidth(-1), track.prefWidth(-1)) +
                    ((showTickMarks) ? (trackToTickGap+tickLine.prefWidth(-1)) : 0) + rightInset;
        }
    }

    @Override protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        final Slider s = getSkinnable();
        if (s.getOrientation() == Orientation.HORIZONTAL) {
            return topInset + Math.max(thumb.prefHeight(-1), track.prefHeight(-1)) +
                    ((showTickMarks) ? (trackToTickGap+tickLine.prefHeight(-1)) : 0)  + bottomInset;
        } else {
            if(showTickMarks) {
                return Math.max(140, tickLine.prefHeight(-1));
            } else {
                return 140;
            }
        }
    }

    @Override protected double computeMaxWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        if (getSkinnable().getOrientation() == Orientation.HORIZONTAL) {
            return Double.MAX_VALUE;
        } else {
            return getSkinnable().prefWidth(-1);
        }
    }

    @Override protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        if (getSkinnable().getOrientation() == Orientation.HORIZONTAL) {
            return getSkinnable().prefHeight(width);
        } else {
            return Double.MAX_VALUE;
        }
    }

    /********** CSS Properties ****************/

    private static final CssMetaData<Slider, Boolean> SHOW_VALUE_ON_INTERACTION_META_DATA =
            new CssMetaData<Slider, Boolean>("-show-value-on-interaction",
                    BooleanConverter.getInstance(), true) {

                @Override
                public boolean isSettable(Slider slider) {
                    final SliderSkin skin = (SliderSkin) slider.getSkin();
                    return !skin.showValueOnInteraction.isBound();
                }

                @Override
                public StyleableProperty<Boolean> getStyleableProperty(Slider slider) {
                    final SliderSkin skin = (SliderSkin) slider.getSkin();
                    return (StyleableProperty<Boolean>) skin.showValueOnInteractionProperty();
                }
            };

    private BooleanProperty showValueOnInteraction = new SimpleStyleableBooleanProperty(SHOW_VALUE_ON_INTERACTION_META_DATA, true);

    private BooleanProperty showValueOnInteractionProperty() { return showValueOnInteraction; }

    private boolean isShowValueOnInteraction() { return showValueOnInteraction.get(); }


    /* Setup styleables for this Skin */
    private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

    static {
        final List<CssMetaData<? extends Styleable, ?>> styleables =
                new ArrayList<>(SkinBase.getClassCssMetaData());
        styleables.add(SHOW_VALUE_ON_INTERACTION_META_DATA);
        STYLEABLES = Collections.unmodifiableList(styleables);
    }

    /**
     * @return The CssMetaData associated with this class, which may include the
     * CssMetaData of its super classes.
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return STYLEABLES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return getClassCssMetaData();
    }

}

