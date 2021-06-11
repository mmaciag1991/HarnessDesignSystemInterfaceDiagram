package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;



public class Connectors {

    private final StringProperty Id;
    private final StringProperty Xcode;
    private final StringProperty Connector;
    private final StringProperty Pins;
    private final StringProperty CoordinateX;
    private final StringProperty CoordinateY;
    private final Node node;




    public Connectors() {
        this(null, null, null,null, null,null,null);

    }


    public Connectors(String Id, String Xcode, String Connector, String Pins, String CoordinateX, String CoordinateY, Node node ) {
        this.Id = new SimpleStringProperty(Id);
        this.Xcode = new SimpleStringProperty(Xcode);
        this.Connector = new SimpleStringProperty(Connector);
        this.Pins = new SimpleStringProperty(Pins);
        this.CoordinateX = new SimpleStringProperty(CoordinateX);
        this.CoordinateY = new SimpleStringProperty(CoordinateY);
        this.node = node;

    }

    public String getId() {
        return Id.get();
    }

    public void setId(String Id) {
        this.Id.set(Id);
    }

    public StringProperty IdProperty() {
        return Id;
    }


    public String getXcode() {
        return Xcode.get();
    }

    public void setXcode(String xcode) {
        this.Xcode.set(xcode);
    }

    public StringProperty xcodeProperty() {
        return Xcode;
    }


    public String getConnector() {
        return Connector.get();
    }

    public void setConnector(String connector) {
        this.Connector.set(connector);
    }

    public StringProperty connectorProperty() {
        return Connector;
    }



    public String getPins() {
        return Pins.get();
    }

    public void setPins(String Status) {
        this.Pins.set(Status);
    }

    public StringProperty pinsProperty() {
        return Pins;
    }


    public String getCoordinateX() {
        return CoordinateX.get();
    }

    public void setCoordinateX(String coordinateX) {
        this.CoordinateX.set(coordinateX);
    }

    public StringProperty CoordinateXProperty() {
        return CoordinateX;
    }


    public String getCoordinateY() {
        return CoordinateY.get();
    }

    public void setCoordinateY(String coordinateY) {
        this.CoordinateY.set(coordinateY);
    }

    public StringProperty CoordinateYProperty() {
        return CoordinateY;
    }

    public Node getCell2() {
        return node;
    }
}