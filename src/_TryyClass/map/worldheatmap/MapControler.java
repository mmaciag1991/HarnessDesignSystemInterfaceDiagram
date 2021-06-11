package _TryyClass.map.worldheatmap;

import Components.hansolo.tilesfx.Tile;
import Components.hansolo.tilesfx.TileBuilder;
import Components.hansolo.tilesfx.icons.Flag;
import _TryyClass.map.heatmap.ColorMapping;
import _TryyClass.map.heatmap.OpacityDistribution;
import _TryyClass.map.world.*;
import _TryyClass.map.world.World.Resolution;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.mapicons.Mapicons;

import java.io.IOException;
import java.util.Locale;


/**
 * User: hansolo
 * Date: 12.01.17
 * Time: 05:54
 */
public class MapControler extends Application {

    private World         worldMap;


    @FXML
    private Label lblCity;
    @FXML
    private StackPane pane;
    @FXML
            private ScrollPane list;

    Location Starachowice = new Location();

    private Tile            customFlagChartTile;

    public void initialize(){

        worldMap = WorldBuilder.create()
                .resolution(Resolution.HI_RES)
                .zoomEnabled(false)
                .hoverEnabled(false)
                .selectionEnabled(false)
                .colorMapping(ColorMapping.RAINBOW)
                .fadeColors(true)
                .opacityDistribution(OpacityDistribution.EXPONENTIAL)
                .fillColor(Color.BISQUE)
                .pressedColor(Color.GREEN)
                .mousePressHandler(evt -> {
                    CountryPath countryPath = (CountryPath) evt.getSource();
                    Locale locale      = countryPath.getLocale();
                    //System.out.println(locale.getDisplayCountry() + " (" + locale.getISO3Country() + ")");
                    //System.out.println(Country.valueOf(countryPath.getName()).getValue() + " million people");
                })
                .build();

        BusinessRegion.MOTHERSON.setColor(Color.rgb(222, 22, 22));



        Starachowice.setName("Starachowice");
        Starachowice.setInfo("dfgdfgdfg");


        Starachowice.setIconCode(Mapicons.MAP_PIN);
        Starachowice.setIconSize(1);
        Starachowice.setColor(Color.LIGHTGRAY);
        Starachowice.setLatitude(51.03803728514121);
        Starachowice.setLongitude(21.073315302808734);


        Starachowice.setMouseEnterHandler(event -> {
            FontIcon fontIcon = (FontIcon) event.getSource();
            fontIcon.setIconColor(Color.GREEN);
        });
        Starachowice.setMouseExitHandler(event -> {
            FontIcon fontIcon = (FontIcon) event.getSource();
            fontIcon.setIconColor(Color.LIGHTGRAY);
        });
        Starachowice.setMousePressHandler(event -> {
            this.lblCity.setText(Starachowice.getName());
            this.lblCity.setTextFill(Color.GOLD);
            worldMap.zoomToCountry(Country.valueOf("PL"));
        });

       //System.out.println( Starachowice.getDistanceTo(Starachowice));

        worldMap.addLocation(Starachowice);


        Platform.runLater(() -> {
            worldMap.setZoomEnabled(true);
        });
        pane.getChildren().add(worldMap);


        Label     name      = new Label("Name");
        name.setTextFill(Tile.FOREGROUND);
        name.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(name, Priority.NEVER);

        Region spacer = new Region();
        spacer.setPrefSize(5, 5);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label views = new Label("Cases / Deaths");
        views.setTextFill(Tile.FOREGROUND);
        views.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(views, Priority.NEVER);

        HBox header = new HBox(5, name, spacer, views);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setFillHeight(true);

        HBox usa     = getCountryItem(Flag.UNITED_STATES_OF_AMERICA, "USA", "1.618.757 / 96.909");
        HBox brazil  = getCountryItem(Flag.BRAZIL, "Brazil", "363.211 / 22.666");
        HBox uk      = getCountryItem(Flag.UNITED_KINGDOM, "UK", "259.563 / 36.793");
        HBox spain   = getCountryItem(Flag.SPAIN, "Spain", "235.772 / 28.752");
        HBox italy   = getCountryItem(Flag.ITALY, "Italy", "229.585 / 32.785");
        HBox germany = getCountryItem(Flag.GERMANY, "Germany", "178.570 / 8.257");
        HBox france  = getCountryItem(Flag.FRANCE, "France", "142.204 / 28.315");

        VBox dataTable = new VBox(0, header, usa, brazil, uk, spain, italy, germany, france);
        dataTable.setFillWidth(true);

        customFlagChartTile = TileBuilder.create()
                .skinType(Tile.SkinType.CUSTOM)
                .title("Custom Tile Covid-19")
                .text("Data from 26.05.2020")
                .graphic(dataTable)
                .build();


        list.setContent(customFlagChartTile);


    }

    @Override public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        ////System.out.println(id);
        loader.setLocation(getClass().getResource("MapView.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);


        stage.setTitle("Select Plant");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }


    private HBox getCountryItem(final Flag flag, final String text, final String data) {
        ImageView imageView = new ImageView(flag.getImage(22));
        HBox.setHgrow(imageView, Priority.NEVER);

        Label name = new Label(text);
        name.setTextFill(Tile.FOREGROUND);
        name.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(name, Priority.NEVER);

        Region spacer = new Region();
        spacer.setPrefSize(5, 5);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label views = new Label(data);
        views.setTextFill(Tile.FOREGROUND);
        views.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(views, Priority.NEVER);

        HBox hBox = new HBox(5, imageView, name, spacer, views);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setFillHeight(true);

        return hBox;
    }


}