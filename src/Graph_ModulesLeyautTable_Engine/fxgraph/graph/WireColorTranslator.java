package Graph_ModulesLeyautTable_Engine.fxgraph.graph;

import javafx.scene.paint.Color;

public class WireColorTranslator {
    public Color getColor(String colorName) {

        Color returnedColor = null;
        switch (colorName) {
            case "BK", "SW": {
                returnedColor = (Color.BLACK);

                break;
            }
            case "BN", "BR": {
                returnedColor = (Color.SANDYBROWN);

                break;
            }
            case "RD", "RT": {
                returnedColor = (Color.RED);

                break;
            }
            case "OG", "OR": {
                returnedColor = (Color.ORANGE);

                break;
            }
            case "YE", "GE": {
                returnedColor = (Color.GOLD);

                break;
            }
            case "BU", "BL": {
                returnedColor = (Color.ROYALBLUE);

                break;
            }
            case "VT", "VL": {
                returnedColor = (Color.VIOLET);

                break;
            }
            case "GY", "GR": {
                returnedColor = (Color.GRAY);

                break;
            }
            case "WH", "WS": {
                returnedColor = (Color.WHITE);

                break;
            }
            case "PK", "RS": {
                returnedColor = (Color.PINK);

                break;
            }
            case "GN": {
                returnedColor = (Color.GREEN);

                break;
            }
            default: {
                returnedColor = Color.NAVY;

                break;
            }

        }
        return returnedColor;
    }

}

