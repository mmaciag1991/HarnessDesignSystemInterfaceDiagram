//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//%Section 4: Branch Configuration

package Model;

public class Section10_Model {
    private String Variant_;
    private String No;
    private String Reference;
    private String Version;
    private String Date;
    private String Variable;

    public Section10_Model(String Variant_, String No, String Reference, String Version, String Date, String Variable) {
        this.Variant_ = Variant_;
        this.Version = Version;
        this.No = No;
        this.Reference = Reference;
        this.Date = Date;
        this.Variable = Variable;
    }

    public String getVariant_() {
        return this.Variant_;
    }

    public void setVariant_(String variant_) {
        this.Variant_ = variant_;
    }

    public String getVersion() {
        return this.Version;
    }

    public void setVersion(String version) {
        this.Version = version;
    }

    public String getNo() {
        return this.No;
    }

    public void setNo(String no) {
        this.No = no;
    }

    public String getReference() {
        return this.Reference;
    }

    public void setReference(String reference) {
        this.Reference = reference;
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
