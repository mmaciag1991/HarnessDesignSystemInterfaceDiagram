//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Model;

public class Section1_Model {
    private String Reference;
    private String Version;
    private String Date;
    private String Variant;
    private String Variable;

    public Section1_Model(String Reference, String Version, String Date, String Variant, String Variable) {
        this.Reference = Reference;
        this.Variant = Variant;
        this.Version = Version;
        this.Date = Date;
        this.Variable = Variable;
    }

    public String getReference() {
        return this.Reference;
    }

    public void setReference(String reference) {
        this.Reference = reference;
    }

    public String getVariant() {
        return this.Variant;
    }

    public void setVariant(String variant) {
        this.Variant = variant;
    }

    public String getVersion() {
        return this.Version;
    }

    public void setVersion(String version) {
        this.Version = version;
    }

    public String getDate() {
        return this.Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getVariable() {
        return this.Variable;
    }

    public void setVariable(String variable) {
        this.Variable = variable;
    }
}
