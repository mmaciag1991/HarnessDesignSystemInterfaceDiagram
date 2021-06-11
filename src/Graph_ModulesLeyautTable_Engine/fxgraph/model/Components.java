package Graph_ModulesLeyautTable_Engine.fxgraph.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;


public class Components {

    private final StringProperty Id;
    private final StringProperty Xcode;
    private final StringProperty CustomerCode;
    private final StringProperty GIMCode;
    private final StringProperty Pins;
    private final StringProperty CoordinateX;
    private final StringProperty CoordinateY;
    private final Node node;




    public Components() {
        this(null, null, null, null, null, null, null, null);

    }

    public Components(String Id, String Xcode, String CustomerCode, String GIMCode, String Pins, String CoordinateX, String CoordinateY, Node node) {
        this.Id = new SimpleStringProperty(Id);
        this.Xcode = new SimpleStringProperty(Xcode);
        this.CustomerCode = new SimpleStringProperty(CustomerCode);
        this.GIMCode = new SimpleStringProperty(GIMCode);
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


    public String getCustomerCode() {
        return CustomerCode.get();
    }

    public void setCustomerCode(String customerCode) {
        this.CustomerCode.set(customerCode);
    }

    public StringProperty customerCodeProperty() {
        return CustomerCode;
    }


    public String getGIMCode() {
        return GIMCode.get();
    }

    public void setGIMCode(String GIMCode) {
        this.GIMCode.set(GIMCode);
    }

    public StringProperty gimCodeProperty() {
        return GIMCode;
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

    public Node getNode() {
        return node;
    }
//   // public void setNode(Node node) {
//        this.node = node;
//    }

}