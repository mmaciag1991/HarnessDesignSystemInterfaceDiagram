package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a RFQ.
 *
 * @author Marco Jakob
 */
public class SystemPintable {

    private final StringProperty Id;
    private final StringProperty SellId;
    private final StringProperty Xcode;
    private final StringProperty Connector;
    private final StringProperty Pins;
    private final StringProperty CoordinateX;
    private final StringProperty CoordinateY;
    private final StringProperty ModuleMounted;
    private final StringProperty OrderStatus;

    private javafx.scene.Node node;

    /**
     * Default constructor.
     */
    public SystemPintable() {
        this(null,null, null, null,null, null,null,null,null,null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param Xcode
     * @param Connector
     */
    public SystemPintable(String SellId, String Id, String Xcode, String Connector, String Pins, String CoordinateX, String CoordinateY, javafx.scene.Node node, String ModuleMounted, String OrderStatus) {
        this.SellId = new SimpleStringProperty(SellId);
        this.Id = new SimpleStringProperty(Id);
        this.Xcode = new SimpleStringProperty(Xcode);
        this.Connector = new SimpleStringProperty(Connector);
        this.Pins = new SimpleStringProperty(Pins);
        this.CoordinateX = new SimpleStringProperty(CoordinateX);
        this.CoordinateY = new SimpleStringProperty(CoordinateY);
        this.node = node;
        this.ModuleMounted = new SimpleStringProperty(ModuleMounted);
        this.OrderStatus = new SimpleStringProperty(OrderStatus);

    }
    public String getSellId() {
        return SellId.get();
    }

    public void setSellId(String SellId) {
        this.SellId.set(SellId);
    }

    public StringProperty SellIdProperty() {
        return SellId;
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

    public javafx.scene.Node getNode2() {
        return node;
    }
    public void setNode2(javafx.scene.Node node) {
        this.node = node;
    }

    public String getModuleMounted() {
        return ModuleMounted.get();
    }

    public void setModuleMounted(String ModuleMounted){this.ModuleMounted.setValue(ModuleMounted);}

    public StringProperty moduleMountedProperty() {
        return ModuleMounted;
    }

    public String getOrderStatus() {
        return OrderStatus.get();
    }

    public void setOrderStatus(String OrderStatus){this.OrderStatus.setValue(OrderStatus);}

    public StringProperty OrderStatusProperty() {
        return OrderStatus;
    }

}