//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//%Section 4: Branch Configuration

package Model;

public class Section7_Model {
    private String Start;
    private String End;
    private String Name;
    private String Part_Nb;
    private String Variant_;
    private String Note;
    private String Assembly;

    public Section7_Model(String Start, String End, String Name, String Part_Nb, String Variant_, String Note, String Assembly) {
        this.Start = Start;
        this.Part_Nb = Part_Nb;
        this.End = End;
        this.Name = Name;
        this.Variant_ = Variant_;
        this.Note = Note;
        this.Assembly = Assembly;
    }

    public String getStart() {
        return this.Start;
    }

    public void setStart(String start) {
        this.Start = start;
    }

    public String getPart_Nb() {
        return this.Part_Nb;
    }

    public void setPart_Nb(String part_Nb) {
        this.Part_Nb = part_Nb;
    }

    public String getEnd() {
        return this.End;
    }

    public void setEnd(String end) {
        this.End = end;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getVariant_() {
        return this.Variant_;
    }

    public void setVariant_(String variant_) {
        this.Variant_ = variant_;
    }

    public String getNote() {
        return this.Note;
    }

    public void setNote(String note) {
        this.Note = note;
    }

    public String getAssembly() {
        return this.Assembly;
    }

    public void setAssembly(String assembly) {
        this.Assembly = assembly;
    }
}
