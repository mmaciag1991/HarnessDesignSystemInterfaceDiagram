/*
 * Copyright (c) 2014 by Gerrit Grunwald
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

package _TryyClass.map.heatmap;

import javafx.beans.property.*;
import javafx.geometry.Dimension2D;

import java.util.HashMap;


/**
 * User: hansolo
 * Date: 30.06.14
 * Time: 10:30
 */
public class HeatMapBuilder<B extends HeatMapBuilder<B>> {
    private HashMap<String, Property> properties = new HashMap<>();


    // ******************** Constructors **************************************
    protected HeatMapBuilder() {
    }


    // ******************** Methods *******************************************
    public final static HeatMapBuilder create() {
        return new HeatMapBuilder();
    }

    public final B prefSize(final double WIDTH, final double HEIGHT) {
        properties.put("prefSize", new SimpleObjectProperty<>(new Dimension2D(WIDTH, HEIGHT)));
        return (B)this;
    }

    public final B width(final double WIDTH) {
        properties.put("width", new SimpleDoubleProperty(WIDTH));
        return (B) this;
    }

    public final B height(final double HEIGHT) {
        properties.put("height", new SimpleDoubleProperty(HEIGHT));
        return (B) this;
    }

    public final B colorMapping(final ColorMapping COLOR_MAPPING) {
        properties.put("colorMapping", new SimpleObjectProperty<>(COLOR_MAPPING));
        return (B) this;
    }

    public final B eventRadius(final double EVENT_RADIUS) {
        properties.put("eventRadius", new SimpleDoubleProperty(EVENT_RADIUS));
        return (B) this;
    }

    public final B fadeColors(final boolean FADE_COLORS) {
        properties.put("fadeColors", new SimpleBooleanProperty(FADE_COLORS));
        return (B)this;
    }

    public final B heatMapOpacity(final double HEAT_MAP_OPACITY) {
        properties.put("heatMapOpacity", new SimpleDoubleProperty(HEAT_MAP_OPACITY));
        return (B) this;
    }

    public final B opacityDistribution(final OpacityDistribution OPACITY_DISTRIBUTION) {
        properties.put("opacityDistribution", new SimpleObjectProperty<>(OPACITY_DISTRIBUTION));
        return (B) this;
    }

    public final HeatMap build() {
        double              width               = 400;
        double              height              = 400;
        ColorMapping        colorMapping        = ColorMapping.LIME_YELLOW_RED;
        double              eventRadius         = 15.5;
        boolean             fadeColors          = false;
        double              heatMapOpacity      = 0.5;
        OpacityDistribution opacityDistribution = OpacityDistribution.CUSTOM;

        for (String key : properties.keySet()) {
            if ("prefSize".equals(key)) {
                Dimension2D dim = ((ObjectProperty<Dimension2D>) properties.get(key)).get();
                width  = dim.getWidth();
                height = dim.getHeight();
            } else if ("width".equals(key)) {
                width = ((DoubleProperty) properties.get(key)).get();
            } else if ("height".equals(key)) {
                height = ((DoubleProperty) properties.get(key)).get();
            } else if ("colorMapping".equals(key)) {
                colorMapping = ((ObjectProperty<ColorMapping>) properties.get(key)).get();
            } else if ("eventRadius".equals(key)) {
                eventRadius = ((DoubleProperty) properties.get(key)).get();
            } else if ("fadeColors".equals(key)) {
                fadeColors = ((BooleanProperty) properties.get(key)).get();
            } else if ("heatMapOpacity".equals(key)) {
                heatMapOpacity = ((DoubleProperty) properties.get(key)).get();
            } else if ("opacityDistribution".equals(key)) {
                opacityDistribution = ((ObjectProperty<OpacityDistribution>) properties.get(key)).get();
            }
        }

        return new HeatMap(width,  height, colorMapping, eventRadius, fadeColors, heatMapOpacity, opacityDistribution);
    }
}
