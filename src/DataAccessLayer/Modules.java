package DataAccessLayer;


import List.ListModules;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Modules {

    public String id;
    public String SellId;
    public String Xcode;
    public String TesterId;
    public String Type;
    public String Connector;
    public String AssemblyType;
    public String Pins;
    public String CoordinateX;
    public String CoordinateY;

    public ObservableList<ListModules> modulesList = FXCollections.observableArrayList();

}