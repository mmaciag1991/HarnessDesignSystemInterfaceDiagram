package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

public class CompositeDetails {

    private final StringProperty HarnessNumber;
    private final StringProperty Revision;
    private final StringProperty Date;
    private final StringProperty Variant;
    private final StringProperty Lenght;
    private final StringProperty Lenght2;
    private final Node node;




    public CompositeDetails() {
        this(null, null, null,null, null,null,null);

    }

    public CompositeDetails(String HarnessNumber, String Revision, String Date, String Variant, String Lenght1, String Lenght2, Node node ) {
        this.HarnessNumber = new SimpleStringProperty(HarnessNumber);
        this.Revision = new SimpleStringProperty(Revision);
        this.Date = new SimpleStringProperty(Date);
        this.Variant = new SimpleStringProperty(Variant);
        this.Lenght = new SimpleStringProperty(Lenght1);
        this.Lenght2 = new SimpleStringProperty(Lenght2);
        this.node = node;



    }

    public String getHarnessNumber() {
        return HarnessNumber.get();
    }

    public void setHarnessNumber(String HarnessNumber) {
        this.HarnessNumber.set(HarnessNumber);
    }

    public StringProperty HarnessNumberProperty() {
        return HarnessNumber;
    }


    public String getRevision() {
        return Revision.get();
    }

    public void setRevision(String revision) {
        this.Revision.set(revision);
    }

    public StringProperty revisionProperty() {
        return Revision;
    }


    public String getDate() {
        return Date.get();
    }

    public void setDate(String date) {
        this.Date.set(date);
    }

    public StringProperty dateProperty() {
        return Date;
    }



    public String getVariant() {
        return Variant.get();
    }

    public void setVariant(String Status) {
        this.Variant.set(Status);
    }

    public StringProperty variantProperty() {
        return Variant;
    }


    public String getLenght1() {
        return Lenght.get();
    }

    public void setLenght1(String lenght) {
        this.Lenght.set(lenght);
    }

    public StringProperty getLenght1Property() {
        return Lenght;
    }


    public String getLenght2() {
        return Lenght2.get();
    }

    public void setLenght2(String lenght2) {
        this.Lenght2.set(lenght2);
    }

    public StringProperty getLenght2Property() {
        return Lenght2;
    }

    public Node getCell2() {
        return node;
    }
}