package Model;

import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Module;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class LayoutsData {

    private  StringProperty TesterID;
    private  StringProperty TesterName;

    private  StringProperty TesterRows;
    private  StringProperty TesterColumns;
    private  ObservableList<Module> systemPintable = FXCollections.observableArrayList();

    public LayoutsData() {
        this(null, null, null, null);

    }



    public LayoutsData(String TesterID, String Tester, String TesterColumns, String TesterRows) {
        this.TesterID = new SimpleStringProperty(TesterID);
        this.TesterName = new SimpleStringProperty(Tester);

        this.TesterRows = new SimpleStringProperty(TesterRows);
        this.TesterColumns = new SimpleStringProperty(TesterColumns);


    }

    public ObservableList<Module> getSystemPintable() {
        return systemPintable;
    }

    /////////////////////////////////////////////////////////
    public String getTesterID() {
        return TesterID.get();
    }

    public void setTesterID(String TesterID) {
        this.TesterID.set(TesterID);
    }

    public StringProperty TesterIDProperty() {
        return TesterID;
    }


    ///////////////////////////////////////////////////////
    public String getTesterName() {
        return TesterName.get();
    }

    public void setTesterName(String testerName) {
        this.TesterName.set(testerName);
    }

    public StringProperty testerNameProperty() {
        return TesterName;
    }

    ////////////////////////////////////////////////
    public String getTesterRows() {
        return TesterRows.get();
    }

    public void setTesterRows(String TesterRows) {
        this.TesterRows.set(TesterRows);
    }

    public StringProperty getTesterRowsProperty() {
        return TesterRows;
    }


    public String getTesterColumns() {
        return TesterColumns.get();
    }

    public void setTesterColumns(String testerColumns) {
        this.TesterColumns.set(testerColumns);
    }

    public StringProperty getTesterColumnsProperty() {
        return TesterColumns;
    }
}
