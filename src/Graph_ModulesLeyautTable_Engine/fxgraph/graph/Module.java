package Graph_ModulesLeyautTable_Engine.fxgraph.graph;


//import dataBase.DBConnection;

import javafx.beans.property.*;
import javafx.event.EventHandler;
import javafx.scene.control.TitledPane;

public class Module extends TitledPane {

//    public final List<ModulesLeyautTable.fxgraph.graph.Module> children = new ArrayList<>();
//    public final List<ModulesLeyautTable.fxgraph.graph.Module> parents = new ArrayList<>();
    String moduleId;
//    public final DBConnection dbCon = new DBConnection();
//    public Connection con;
//    public PreparedStatement pst;

    public static EventHandler entered;
    public static EventHandler exited ;

//    public String moduleMountedText = "";
    public int SystemPintableId;
    public StringProperty moduleIdProperty = null;
    public StringProperty moduleXcode = null;
    public StringProperty moduleGMCode = null;
    public StringProperty moduleConnectorCustomerCode = null;
    public StringProperty moduleCordX = null;
    public StringProperty moduleCordY = null;
    public BooleanProperty used = new SimpleBooleanProperty(false);
    private DoubleProperty widthDoubleProperty = null;
    private DoubleProperty heightDoubleProperty = null;

   // ModulesLeyautTable.Main.ArrayListProperty<String> Xcodes = new ModulesLeyautTable.Main.ArrayListProperty<>();
    TitledPane view = this;


//    public ArrayListProperty<String> getXcodes() {
//        return Xcodes;
//    }
//
//    public void addXCode(String xCodes) {
//        Xcodes.add(xCodes);
//    }

    public void setVisable(Boolean visable){
        this.setVisable(false);
    }
    public Module(String moduleId) {
        this.moduleId = moduleId;
    }

    public Module(String moduleId, String xCode, String connectorType, String cordX, String cordY, double width, double height) {
        this.moduleId = moduleId;

        moduleIdProperty = new SimpleStringProperty(moduleId);
        moduleXcode = new SimpleStringProperty(xCode);
        moduleGMCode = new SimpleStringProperty("GM");
        moduleConnectorCustomerCode = new SimpleStringProperty(connectorType);
        moduleCordX = new SimpleStringProperty(cordX);
        moduleCordY = new SimpleStringProperty(cordY);
        widthDoubleProperty = new SimpleDoubleProperty(width);
        heightDoubleProperty = new SimpleDoubleProperty(height);
    }

//    public void addModuleChild(ModulesLeyautTable.fxgraph.graph.Module module) {
//        children.add(module);
//    }
//
//    public List<ModulesLeyautTable.fxgraph.graph.Module> getModuleChildren() {
//        return children;
//    }
//
//    public void addModuleParent(ModulesLeyautTable.fxgraph.graph.Module module) {
//        parents.add(module);
//    }
//
//    public List<ModulesLeyautTable.fxgraph.graph.Module> getModuleParents() {
//        return parents;
//    }
//
//    public void removeModuleChild(ModulesLeyautTable.fxgraph.graph.Module module) {
//        children.remove(module);
//    }
//


    public void setUsed(boolean used) {
        this.used.set(used);
    }

    public boolean isUsed() {
        return used.get();
    }

    public void addSystemPintableId(int Id) {
        SystemPintableId=Id;
    }

    public int getSystemPintableId() {
        return SystemPintableId;
    }


    public void setView(TitledPane view) {
        this.view = view;
        getChildren().add(view);

    }

    public TitledPane getView() {
        return this.view;
    }

    public String getModuleId() {
        return moduleId;
    }


    public void setModuleXcode(String moduleXcode) {
        this.moduleXcode.set(moduleXcode);
    }

    public String getModuleXcode() {
        return moduleXcode.get();
    }

    public String getModuleCordX() {
        return moduleCordX.get();
    }

    public String getModuleCordY() {
        return moduleCordY.get();
    }

    public String getModuleConnectorCustomerCode() {
        return moduleConnectorCustomerCode.get();
    }

    public String getModuleGMCode() {
        return moduleGMCode.get();
    }

    public double getWidthDouble() {
        return widthDoubleProperty.get();
    }

    public double getHeightDouble() {
        return heightDoubleProperty.get();
    }


    protected void setWidthDouble(double v) {
        super.setWidth(v);
    }


    protected void setHeightDouble(double v) {
        super.setHeight(v);
    }
}