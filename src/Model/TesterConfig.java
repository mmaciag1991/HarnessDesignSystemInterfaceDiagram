package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a RFQ.
 *
 * @author Marco Jakob
 */
public class TesterConfig {

    private final StringProperty TesterID;
    private final StringProperty Tester;
    private final StringProperty TestingSystem;
    private final StringProperty Place;
    private final StringProperty TesterRows;
    private final StringProperty TesterColumns;
    private final StringProperty TesterPins;
    private final StringProperty TesterCards;
    private final StringProperty ModuleGroupName;
    private final StringProperty SelectedModuleGroupName;


    public TesterConfig() {
        this(null, null, null,null, null,null,null,null,null,null);

    }

    /**
     * Constructor with some initial data.
     */

    public TesterConfig(String TesterID, String Tester, String TestingSystem, String Place, String TesterRows, String TesterColumns, String TesterPins, String TesterCards, String ModuleGroupName, String SelectedModuleGroupName) {
        this.TesterID = new SimpleStringProperty(TesterID);
        this.Tester = new SimpleStringProperty(Tester);
        this.TestingSystem = new SimpleStringProperty(TestingSystem);
        this.Place = new SimpleStringProperty(Place);
        this.TesterRows = new SimpleStringProperty(TesterRows);
        this.TesterColumns = new SimpleStringProperty(TesterColumns);
        this.TesterPins = new SimpleStringProperty(TesterPins);
        this.TesterCards = new SimpleStringProperty(TesterCards);
        this.ModuleGroupName = new SimpleStringProperty(ModuleGroupName);
        this.SelectedModuleGroupName = new SimpleStringProperty(SelectedModuleGroupName);

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
    public String getTester() {
        return Tester.get();
    }

    public void setTester(String tester) {
        this.Tester.set(tester);
    }

    public StringProperty testerProperty() {
        return Tester;
    }

//////////////////////////////////////////////
    public String getTestingSystem() {
        return TestingSystem.get();
    }

    public void setTestingSystem(String testingSystem) {
        this.TestingSystem.set(testingSystem);
    }

    public StringProperty testingSystemProperty() {
        return TestingSystem;
    }


//////////////////////////////////////////////
    public String getPlace() {
        return Place.get();
    }

    public void setPlace(String Place) {
        this.Place.set(Place);
    }

    public StringProperty placeProperty() {
        return Place;
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

    public String getTesterPins() {
        return TesterPins.get();
    }

    public void setTesterPins(String TesterPins) {
        this.TesterPins.set(TesterPins);
    }

    public StringProperty TesterPinsProperty() {
        return TesterPins;
    }


    public String getTesterCards() {
        return TesterCards.get();
    }

    public void setTesterCards(String testerCards) {
        this.TesterCards.set(testerCards);
    }

    public StringProperty testerCardsProperty() {
        return TesterCards;
    }


    public String getModuleGroupName() {
        return ModuleGroupName.get();
    }

    public void setModuleGroupName(String ModuleGroupName) {
        this.ModuleGroupName.set(ModuleGroupName);
    }

    public StringProperty ModuleGroupNameProperty() {
        return ModuleGroupName;
    }

    public String getSelectedModuleGroupName() {
        return SelectedModuleGroupName.get();
    }

    public void setSelectedModuleGroupName(String SelectedModuleGroupName) {
        this.SelectedModuleGroupName.set(SelectedModuleGroupName);
    }

    public StringProperty SelectedModuleGroupNameProperty() {
        return SelectedModuleGroupName;
    }
}