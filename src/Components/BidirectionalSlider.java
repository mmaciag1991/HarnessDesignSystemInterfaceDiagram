package Components;


/** author Mateusz Maci±g */

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Orientation;
import javafx.scene.control.Slider;


public class BidirectionalSlider extends Slider {
    Slider slider;
    String[] colors =  {"red","green"};
    double minLock;
    double maxLock;
    double criticalValue;

    public BidirectionalSlider(double value ,double criticalValue, double min, double max){

            this.BidirectionalSlider(value,criticalValue,min,max,0,0);

    }

    public BidirectionalSlider(double value ,double criticalValue, double min, double max, double minLock, double maxLock){

        this.BidirectionalSlider(value,criticalValue,min,max,minLock,maxLock);

    }

    public void BidirectionalSlider(double value ,double criticalValue, double min, double max, double minLock, double maxLock){
        if (maxLock!=0) {
            this.minLock = minLock;
            this.maxLock = maxLock;
        }
        this.criticalValue = criticalValue;

         slider = new Slider(min, max, value);



        //System.out.println("/"+getClass().getPackage().getName()+"/BidirectionalSliderStyle.css");
        slider.getStylesheets().add("/"+getClass().getPackage().getName()+"/BidirectionalSliderStyle.css");

        slider.styleProperty().bind(Bindings.createStringBinding(() -> {

            double valueS = slider.getValue() ;
            double recurs = 1/max;
            if (maxLock!=0) {
                if (valueS > this.maxLock)
                    slider.setValue(this.maxLock);
                if (valueS < this.minLock)
                    slider.setValue(this.minLock);
            }

            return createSliderStyle(criticalValue*recurs, min*recurs, max*recurs, valueS*recurs);

        }, slider.valueProperty()));


        Platform.runLater(()->{
            slider.setValue(criticalValue);
            slider.setValue(value);
        });

    }

    public Slider getSlider() {
        return slider;
    }

    public void createValueThumb(){

//                try {
//
//                    Label l = new Label();
//                    Platform.runLater(() ->  {
//                        AtomicReference<Pane> p = new AtomicReference<>(new Pane());
//                    p.set((Pane) slider.lookup(".thumb"));
//                    if (slider.getOrientation().equals(Orientation.VERTICAL)) {
//                        p.get().setTranslateX(-18);
//                        p.get().setMinWidth(160);
//                        p.get().minHeight(160);
//                        p.get().setStyle("-fx-shape: \"M17 5h-17v14h17l7-7z\";");
//                    }else {
//                        p.get().setTranslateY(-15);
//                    }
//                        p.get().getChildren().add(l);
//                    });
//
//                    l.textProperty().bind(slider.valueProperty().asString("%.2f").concat(""));
//                    slider.valueProperty().addListener(new ChangeListener<Number>() {
//                        @Override
//                        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//
//
//                            if (newValue.doubleValue() >= criticalValue) {
//                                l.setStyle("-fx-text-fill:"+ colors[1] +";");
//                            }else{
//                                l.setStyle("-fx-text-fill:"+ colors[0] +";");
//                            }
//
//                        }
//                    });
//
//                    if (slider.getValue() >= criticalValue) {
//                        l.setStyle("-fx-text-fill:"+ colors[1] +";");
//                    }else{
//                        l.setStyle("-fx-text-fill:"+ colors[0] +";");
//                    }
//                }catch (Exception e){
//                    System.err.println("Use this method when Slider is visable. \n Try use Platform.runLater(()-> bidirectionalSlider.createValueThumb() )");
//                    e.printStackTrace();
//                }


    }

    public void setColors(String color_LB, String color_RT) {
        colors[0] = color_LB;
        colors[1] = color_RT;
    }

    public void setMinLock(double minLock) {
        this.minLock = minLock;
    }

    public void setMaxLock(double maxLock) {
        this.maxLock = maxLock;
    }

    private String createSliderStyle(double startingValue, double min, double max, double value) {
        StringBuilder gradient = new StringBuilder("-slider-track-color: ");
        String defaultBG = "derive(-fx-control-inner-background, -5%) " ;
        if (slider.getOrientation().equals(Orientation.VERTICAL)) {
            gradient.append("linear-gradient(to top, ").append(defaultBG).append("0%, ");
        }else {
            gradient.append("linear-gradient(to right, ").append(defaultBG).append("0%, ");
        }


        double valuePercent = 100.0 * (value - min) / (max - min);

        double startingValuePercent = startingValue * 100.0;


        if (valuePercent > startingValuePercent) {
            gradient.append(defaultBG).append(startingValuePercent).append("%, ");
            gradient.append(colors[1] + " ").append(startingValuePercent).append("%, ");
            gradient.append(colors[1] + " ").append(valuePercent).append("%, ");
            gradient.append(defaultBG).append(valuePercent).append("%, ");
            gradient.append(defaultBG).append("100%); ");
        } else {
            gradient.append(defaultBG).append(valuePercent).append("%, ");
            gradient.append(colors[0] + " ").append(valuePercent).append("%, ");
            gradient.append(colors[0] + " ").append(startingValuePercent).append("%, ");
            gradient.append(defaultBG).append(startingValuePercent).append("%, ");
            gradient.append(defaultBG).append("100%); ");
        }
        return gradient.toString() ;
    }


}