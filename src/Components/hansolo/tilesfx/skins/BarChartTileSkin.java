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
package Components.hansolo.tilesfx.skins;

import Components.hansolo.tilesfx.Tile;
import Components.hansolo.tilesfx.events.ChartDataEvent;
import Components.hansolo.tilesfx.events.ChartDataEventListener;
import Components.hansolo.tilesfx.events.TileEvent;
import Components.hansolo.tilesfx.events.TileEvent.EventType;
import Components.hansolo.tilesfx.fonts.Fonts;
import Components.hansolo.tilesfx.tools.Helper;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.collections.WeakListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by hansolo on 19.12.16.
 */
public class BarChartTileSkin extends TileSkin {
    private Text                                        titleText;
    private Text                                        text;
    private Pane                                        barChartPane;
    private ChartDataEventListener                      updateHandler;
    private InvalidationListener                        paneSizeListener;
    private Map<BarChartItem, EventHandler<MouseEvent>> handlerMap;


    // ******************** Constructors **************************************
    public BarChartTileSkin(final Tile TILE) {
        super(TILE);
    }


    // ******************** Initialization ************************************
    @Override protected void initGraphics() {
        super.initGraphics();

        updateHandler    = e -> {
            final ChartDataEvent.EventType TYPE = e.getType();
            switch (TYPE) {
                case UPDATE  : updateChart(); break;
                case FINISHED: sortItems(); break;
            }
        };
        paneSizeListener = o -> resizeItems();
        handlerMap       = new HashMap<>();
        
        tile.getBarChartItems().forEach(item -> {
            item.addChartDataEventListener(updateHandler);
            EventHandler<MouseEvent> clickHandler = e -> tile.fireTileEvent(new TileEvent(EventType.SELECTED_CHART_DATA, item.getChartData()));
            handlerMap.put(item, clickHandler);
            item.addEventHandler(MouseEvent.MOUSE_PRESSED, clickHandler);
            item.setMaxValue(tile.getMaxValue());
            if (null == item.getFormatString() || item.getFormatString().isEmpty()) {
                item.setFormatString(formatString);
            }
        });
        barChartPane = new Pane();
        barChartPane.getChildren().addAll(tile.getBarChartItems());

        sortItems();

        titleText = new Text();
        titleText.setFill(tile.getTitleColor());
        Helper.enableNode(titleText, !tile.getTitle().isEmpty());

        text = new Text(tile.getText());
        text.setFill(tile.getUnitColor());
        Helper.enableNode(text, tile.isTextVisible());

        getPane().getChildren().addAll(titleText, text, barChartPane);
    }

    @Override protected void registerListeners() {
        super.registerListeners();
        tile.getBarChartItems().addListener(new WeakListChangeListener<>(change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    change.getAddedSubList().forEach(addedItem -> {
                        barChartPane.getChildren().add(addedItem);
                        addedItem.addChartDataEventListener(updateHandler);
                        EventHandler<MouseEvent> clickHandler = e -> tile.fireTileEvent(new TileEvent(EventType.SELECTED_CHART_DATA, addedItem.getChartData()));
                        handlerMap.put(addedItem, clickHandler);
                        addedItem.setOnMousePressed(clickHandler);
                    });
                    updateChart();
                } else if (change.wasRemoved()) {
                    change.getRemoved().forEach(removedItem -> {
                        removedItem.removeChartDataEventListener(updateHandler);
                        removedItem.removeEventHandler(MouseEvent.MOUSE_PRESSED, handlerMap.get(removedItem));
                        barChartPane.getChildren().remove(removedItem);
                    });
                    updateChart();
                }
            }
        }));

        pane.widthProperty().addListener(paneSizeListener);
        pane.heightProperty().addListener(paneSizeListener);
    }


    // ******************** Methods *******************************************
    @Override protected void handleEvents(final String EVENT_TYPE) {
        super.handleEvents(EVENT_TYPE);

        if (EventType.VISIBILITY.name().equals(EVENT_TYPE)) {
            Helper.enableNode(titleText, !tile.getTitle().isEmpty());
            Helper.enableNode(text, tile.isTextVisible());
        } else if (EventType.DATA.name().equals(EVENT_TYPE)) {
            sortItems();
        } else if (EventType.ANIMATED_ON.name().equals(EVENT_TYPE)) {
            tile.getBarChartItems().forEach(item -> item.getChartData().setAnimated(true));
        } else if (EventType.ANIMATED_OFF.name().equals(EVENT_TYPE)) {
            tile.getBarChartItems().forEach(item -> item.getChartData().setAnimated(false));
        }
    }

    private void sortItems() {
        switch (tile.getItemSorting()) {
            case ASCENDING : tile.getBarChartItems().sort(Comparator.comparing(BarChartItem::getValue)); break;
            case DESCENDING: tile.getBarChartItems().sort(Comparator.comparing(BarChartItem::getValue).reversed());break;
            case NONE:
            default:
                break;
        }
        updateChart();
    }

    @Override public void dispose() {
        pane.widthProperty().removeListener(paneSizeListener);
        pane.heightProperty().removeListener(paneSizeListener);
        tile.getBarChartItems().forEach(item -> {
            item.removeChartDataEventListener(updateHandler);
            item.removeEventHandler(MouseEvent.MOUSE_PRESSED, handlerMap.get(item));
        });
        handlerMap.clear();
        super.dispose();
    }


    // ******************** Resizing ******************************************
    private void updateChart() {
        Platform.runLater(() -> {
            List<BarChartItem> items     = tile.getBarChartItems();
            int                noOfItems = items.size();
            if (noOfItems == 0) { return; }
            double maxValue   = tile.getMaxValue();
            double maxY       = barChartPane.getLayoutBounds().getMaxY();
            double itemHeight = items.get(0).getPrefHeight();
            double factorY    = Helper.clamp(itemHeight, itemHeight * 1.1, 0.13 * size);
            for (int i = 0 ; i < noOfItems ; i++) {
                BarChartItem item = items.get(i);
                double y = i * factorY;
                if ((y + itemHeight) < maxY) {
                    item.setMaxValue(maxValue);
                    Helper.enableNode(item, true);
                    item.relocate(0, y);
                } else {
                    Helper.enableNode(item, false);
                }
            }
            long noOfVisibleItems = items.stream().filter(item -> item.isVisible()).count();
            tile.showLowerRightRegion(noOfVisibleItems != noOfItems);
        });
    }

    @Override protected void resizeStaticText() {
        double maxWidth = width - size * 0.1;
        double fontSize = size * textSize.factor;

        boolean customFontEnabled = tile.isCustomFontEnabled();
        Font    customFont        = tile.getCustomFont();
        Font    font              = (customFontEnabled && customFont != null) ? Font.font(customFont.getFamily(), fontSize) : Fonts.latoRegular(fontSize);

        titleText.setFont(font);
        if (titleText.getLayoutBounds().getWidth() > maxWidth) { Helper.adjustTextSize(titleText, maxWidth, fontSize); }
        switch(tile.getTitleAlignment()) {
            default    :
            case LEFT  : titleText.relocate(size * 0.05, size * 0.05); break;
            case CENTER: titleText.relocate((width - titleText.getLayoutBounds().getWidth()) * 0.5, size * 0.05); break;
            case RIGHT : titleText.relocate(width - (size * 0.05) - titleText.getLayoutBounds().getWidth(), size * 0.05); break;
        }

        text.setText(tile.getText());
        text.setFont(font);
        if (text.getLayoutBounds().getWidth() > maxWidth) { Helper.adjustTextSize(text, maxWidth, fontSize); }
        switch(tile.getTextAlignment()) {
            default    :
            case LEFT  : text.setX(size * 0.05); break;
            case CENTER: text.setX((width - text.getLayoutBounds().getWidth()) * 0.5); break;
            case RIGHT : text.setX(width - (size * 0.05) - text.getLayoutBounds().getWidth()); break;
        }
        text.setY(height - size * 0.05);
    }

    private void resizeItems() {
        double itemHeight = Helper.clamp(30, 72, height * 0.14);
        barChartPane.getChildren().forEach(node -> {
            BarChartItem item = (BarChartItem) node;
            item.setParentSize(width, height);
            item.setPrefSize(width, itemHeight);
            item.setMaxSize(width, itemHeight);
        });
    }

    @Override protected void resize() {
        super.resize();

        barChartPane.setPrefSize(width, contentBounds.getHeight());
        barChartPane.relocate(0, contentBounds.getY());
        resizeItems();
        updateChart();
    }

    @Override protected void redraw() {
        super.redraw();
        titleText.setText(tile.getTitle());
        text.setText(tile.getText());

        tile.getBarChartItems().forEach(item -> {
            item.setNameColor(tile.getTextColor());
            item.setValueColor(tile.getValueColor());
        });

        resizeDynamicText();
        resizeStaticText();

        titleText.setFill(tile.getTitleColor());
        text.setFill(tile.getTextColor());
    }
}
