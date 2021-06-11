package DSI_Graph_Main_Controlers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class AutomaticGraphSettingsControler {

    @FXML
    private Spinner<Integer> sourceAttractionForceSp;
    @FXML
    private Spinner<Integer> targetAttractionForceSp;

    @FXML
    private Spinner<Integer> sourceRepulsionForceSp;
    @FXML
    private Spinner<Integer> targetRepulsionForceSp;

    @FXML
    private Spinner<Double> sourceAttractionScaleSp;
    @FXML
    private Spinner<Double> targetAttractionScaleSp;

    @FXML
    private Slider fpsSlider;

    public static IntegerProperty sourceAttractionForceSpProperty = new SimpleIntegerProperty(0);
    public static IntegerProperty targetAttractionForceSpProperty = new SimpleIntegerProperty(0);
    public static IntegerProperty sourceRepulsionForceSpProperty  = new SimpleIntegerProperty(0);
    public static IntegerProperty targetRepulsionForceSpProperty  = new SimpleIntegerProperty(0);
    public static DoubleProperty  sourceAttractionScaleSpProperty = new SimpleDoubleProperty(.0);
    public static DoubleProperty  targetAttractionScaleSpProperty = new SimpleDoubleProperty(.0);

    public static IntegerProperty fpsLimitPropherty = new SimpleIntegerProperty(5);

    public AutomaticGraphSettingsControler(){}

    @FXML
    private void initialize(){
        ObservableList<Integer> itemsSaf = FXCollections.observableArrayList(0,10, 100, 500, 1000, 2000, 4000, 5000, 6000);
        SpinnerValueFactory<Integer> sourceAttractionForcevalueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(itemsSaf);
        sourceAttractionForceSp.setValueFactory(sourceAttractionForcevalueFactory);
        sourceAttractionForceSp.getValueFactory().setValue(10);

        ObservableList<Integer> itemsTaf = FXCollections.observableArrayList(0,10, 100, 500, 1000, 2000, 4000, 5000, 6000);
        SpinnerValueFactory<Integer> targetAttractionForcevalueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(itemsTaf);
        targetAttractionForceSp.setValueFactory(targetAttractionForcevalueFactory);
        targetAttractionForceSp.getValueFactory().setValue(0);


        ObservableList<Integer> itemsSrf = FXCollections.observableArrayList(0,1, 20, 50, 100, 200, 400, 1000);
        SpinnerValueFactory<Integer> sourceRepulsionForcevalueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(itemsSrf);
        sourceRepulsionForceSp.setValueFactory(sourceRepulsionForcevalueFactory);
        sourceRepulsionForceSp.getValueFactory().setValue(1);

        ObservableList<Integer> itemsTrf = FXCollections.observableArrayList(0,1, 20, 50, 100, 200, 400, 1000);
        SpinnerValueFactory<Integer> targetRepulsionForcevalueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(itemsTrf);
        targetRepulsionForceSp.setValueFactory(targetRepulsionForcevalueFactory);
        targetRepulsionForceSp.getValueFactory().setValue(1);


        ObservableList<Double> itemsSas = FXCollections.observableArrayList(.0,.1, .2, .3, .4, .5, .6, .7, .8, .9, 1.0, 1.5, 2.0, 3.0, 5.0, 10.0, 30.0, 60.0, 100.0, 300.0, 500.0, 1000.0);
        SpinnerValueFactory<Double> sourceAttractionScalevalueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(itemsSas);
        sourceAttractionScaleSp.setValueFactory(sourceAttractionScalevalueFactory);
        sourceAttractionScaleSp.getValueFactory().setValue(1.0);

        ObservableList<Double> itemsTas = FXCollections.observableArrayList(.0,.1, .2, .3, .4, .5, .6, .7, .8, .9, 1.0, 1.5, 2.0, 3.0, 5.0, 10.0, 30.0, 60.0, 100.0, 300.0, 500.0, 1000.0);
        SpinnerValueFactory<Double> targetAttractionScalevalueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(itemsTas);
        targetAttractionScaleSp.setValueFactory(targetAttractionScalevalueFactory);
        targetAttractionScaleSp.getValueFactory().setValue(1.0);


                sourceAttractionForceSpProperty.bind(sourceAttractionForceSp.valueProperty());
                targetAttractionForceSpProperty.bind(targetAttractionForceSp.valueProperty());
                 sourceRepulsionForceSpProperty.bind(sourceRepulsionForceSp.valueProperty());
                 targetRepulsionForceSpProperty.bind(targetRepulsionForceSp.valueProperty());
                sourceAttractionScaleSpProperty.bind(sourceAttractionScaleSp.valueProperty());
                targetAttractionScaleSpProperty.bind(targetAttractionScaleSp.valueProperty());

                fpsLimitPropherty.bind(fpsSlider.valueProperty());

    }

}
