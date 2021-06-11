package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class ModuleGroupName {

    private final StringProperty ModuleGroupId;
    private final StringProperty TesterId;
    private final StringProperty GroupName;





    public ModuleGroupName() {
        this(null, null, null);

    }


    public ModuleGroupName(String Id, String TesterId, String GroupName ) {
        this.ModuleGroupId = new SimpleStringProperty(Id);
        this.TesterId = new SimpleStringProperty(TesterId);
        this.GroupName = new SimpleStringProperty(GroupName);




    }

    public String getModuleGroupId() {
        return ModuleGroupId.get();
    }

    public void setModuleGroupId(String Id) {
        this.ModuleGroupId.set(Id);
    }

    public StringProperty IdProperty() {
        return ModuleGroupId;
    }


    public String getTesterId() {
        return TesterId.get();
    }

    public void setTesterId(String testerId) {
        this.TesterId.set(testerId);
    }

    public StringProperty testerIdProperty() {
        return TesterId;
    }


    public String getGroupName() {
        return GroupName.get();
    }

    public void setGroupName(String groupName) {
        this.GroupName.set(groupName);
    }

    public StringProperty groupNameProperty() {
        return GroupName;
    }



}