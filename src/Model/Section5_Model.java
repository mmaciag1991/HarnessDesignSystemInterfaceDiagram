//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//%Section 4: Branch Configuration

package Model;

public class Section5_Model {
    private String Wire_Name;
    private String Variant_;
    private String Wire_Type;
    private String Color;
    private String Area;
    private String Assembly;
    private String Multi_Core;

    private String Start_Node;
    private String Cav1;
    private String End_Node;
    private String Cav2;
    private String Lenght;
    private String Part_Number;


    public Section5_Model(String Wire_Name, String Variant_, String Wire_Type, String Color, String Area, String Assembly, String Multi_Core, String Start_Node, String Cav1, String End_Node, String Cav2, String Lenght, String Part_Number) {
        this.Wire_Name = Wire_Name;
        this.Color = Color;
        this.Variant_ = Variant_;
        this.Wire_Type = Wire_Type;
        this.Area = Area;
        this.Assembly = Assembly;
        this.Multi_Core = Multi_Core;

        this.Start_Node = Start_Node;
        this.Cav2 = Cav2;
        this.Cav1 = Cav1;
        this.End_Node = End_Node;
        this.Lenght = Lenght;
        this.Part_Number = Part_Number;
    }

    public String getWire_Name() {
        return this.Wire_Name;
    }

    public void setWire_Name(String wire_Name) {
        this.Wire_Name = wire_Name;
    }

    public String getColor() {
        return this.Color;
    }

    public void setColor(String color) {
        this.Color = color;
    }

    public String getVariant_() {
        return this.Variant_;
    }

    public void setVariant_(String variant_) {
        this.Variant_ = variant_;
    }

    public String getWire_Type() {
        return this.Wire_Type;
    }

    public void setWire_Type(String Wire_Type) {
        this.Wire_Type = Wire_Type;
    }

    public String getArea() {
        return this.Area;
    }

    public void setArea(String area) {
        this.Area = area;
    }

    public String getAssembly() {
        return this.Assembly;
    }

    public void setAssembly(String assembly) {
        this.Assembly = assembly;
    }

    public String getMulti_Core() {
        return this.Multi_Core;
    }

    public void setMulti_Core(String multi_Core) {
        this.Multi_Core = multi_Core;
    }


    public String getStart_Node() {
        return this.Start_Node;
    }

    public void setStart_Node(String start_Node) {
        this.Start_Node = start_Node;
    }

    public String getCav2() {
        return this.Cav2;
    }

    public void setCav2(String cav2) {
        this.Cav2 = cav2;
    }

    public String getCav1() {
        return this.Cav1;
    }

    public void setCav1(String cav1) {
        this.Cav1 = cav1;
    }

    public String getEnd_Node() {
        return this.End_Node;
    }

    public void setEnd_Node(String end_Node) {
        this.End_Node = end_Node;
    }

    public String getLenght() {
        return this.Lenght;
    }

    public void setLenght(String lenght) {
        this.Lenght = lenght;
    }

    public String getPart_Number() {
        return this.Part_Number;
    }

    public void setPart_Number(String part_Number) {
        this.Part_Number = part_Number;
    }

}
