package Graph_ModulesLeyautTable_Engine.Main;

import Model.SystemPintable;
import Model.TestersData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Data {
    public final static ObservableList<SystemPintable> systemPintable = FXCollections.observableArrayList();
    public static ObservableList<TestersData> testersData = FXCollections.observableArrayList();


    private void addSystemPintableData(String SellId, String Id, String Xcode, String Connector, String Pins, String CoordinateX, String CoordinateY, javafx.scene.Node node, String ModuleMounted, String OrderStatus) {

        systemPintable.add(new SystemPintable( SellId,  Id,  Xcode,  Connector,  Pins,  CoordinateX,  CoordinateY, node,  ModuleMounted,  OrderStatus));

    }

    private void addTestersData(String TesterID, String Tester, String TesterRows, String TesterColumns ) {

        testersData.add(new TestersData( TesterID,  Tester, TesterRows,  TesterColumns));

    }


    public ObservableList<SystemPintable> getSystemPintable() {

        return systemPintable;
    }

    public static ObservableList<TestersData> getTestersData() {

        return testersData;

    }

    public void clearSystemPintable(){
        systemPintable.clear();
    }


}
