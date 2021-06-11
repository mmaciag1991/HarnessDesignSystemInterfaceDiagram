/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;


public class ListModules {

    public String id;
    public String SellId;
    public String xCode;
    public String TesterId;
    public String Type;
    public String Connector;
    public String AssemblyType;
    public String Pins;
    public String CoordinateX;
    public String CoordinateY;

    public ListModules(String id, String SellId, String xCode, String TesterId, String Type, String Connector, String AssemblyType, String Pins, String CoordinateX, String CoordinateY) {
        this.id = id;
        this.SellId = SellId;
        this.xCode = xCode;
        this.TesterId = TesterId;
        this.Type = Type;
        this.Connector = Connector;
        this.AssemblyType = AssemblyType;
        this.Pins = Pins;
        this.CoordinateX = CoordinateX;
        this.CoordinateY = CoordinateY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellId() {
        return SellId;
    }

    public void setSellId(String SellId) {
        this.SellId = SellId;
    }

    public String getxCode() {
        return xCode;
    }

    public void setxCode(String xCode) {
        this.xCode = xCode;
    }

    public String getTesterId() {
        return TesterId;
    }

    public void setTesterId(String TesterId) {
        this.TesterId = TesterId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getTConnector() {
        return Connector;
    }

    public void setConnector(String Connector) {
        this.Connector = Connector;
    }

    public String getAssemblyType() {
        return AssemblyType;
    }

    public void setAssemblyType(String AssemblyType) {
        this.AssemblyType = AssemblyType;
    }

    public String getPins() {
        return Pins;
    }

    public void setPins(String Pins) {
        this.Pins = Pins;
    }

    public String getCoordinateX() {
        return CoordinateX;
    }

    public void setCoordinateX(String CoordinateX) {
        this.CoordinateX = CoordinateX;
    }

    public String getCoordinateY() {
        return CoordinateY;
    }

    public void setCoordinateY(String CoordinateY) {
        this.CoordinateY = CoordinateY;
    }


}
