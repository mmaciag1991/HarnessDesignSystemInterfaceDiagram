//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//%Section 4: Branch Configuration

package Model;

public class Section6_Model {
    private String Ref_BSJ;
    private String Cavity;
    private String Comp_Type;
    private String Variant;
    private String Name;
    private String Prop_Count;
    private String Part_Nb;

    private String Insul_Type;
    private String Assembly;

    private String Pins;

    public Section6_Model(String Ref_BSJ, String Cavity, String Comp_Type, String Variant, String Name, String Prop_Count, String Part_Nb, String Insul_Type, String Assembly,String Pins) {
        this.Ref_BSJ = Ref_BSJ;
        this.Variant = Variant;
        this.Cavity = Cavity;
        this.Comp_Type = Comp_Type;
        this.Name = Name;
        this.Prop_Count = Prop_Count;
        this.Part_Nb = Part_Nb;

        this.Insul_Type = Insul_Type;
        this.Assembly = Assembly;
        this.Pins = Pins;
    }

    public String getRef_BSJ() {
        return this.Ref_BSJ;
    }

    public void setRef_BSJ(String ref_BSJ) {
        this.Ref_BSJ = ref_BSJ;
    }

    public String getVariant() {
        return this.Variant;
    }

    public void setVariant(String variant) {
        this.Variant = variant;
    }

    public String getCavity() {
        return this.Cavity;
    }

    public void setCavity(String cavity) {
        this.Cavity = cavity;
    }

    public String getComp_Type() {
        return this.Comp_Type;
    }

    public void setComp_Type(String Wire_Type) {
        this.Comp_Type = Wire_Type;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getProp_Count() {
        return this.Prop_Count;
    }

    public void setProp_Count(String prop_Count) {
        this.Prop_Count = prop_Count;
    }

    public String getPart_Nb() {
        return this.Part_Nb;
    }

    public void setPart_Nb(String part_Nb) {
        this.Part_Nb = part_Nb;
    }


    public String getInsul_Type() {
        return this.Insul_Type;
    }

    public void setInsul_Type(String insul_Type) {
        this.Insul_Type = insul_Type;
    }

    public String getAssembly() {
        return this.Assembly;
    }

    public void setAssembly(String assembly) {
        this.Assembly = assembly;
    }

    public String getPins() {
        return this.Pins;
    }

    public void setPins(String Pins) {
        this.Pins = Pins;
    }
}
