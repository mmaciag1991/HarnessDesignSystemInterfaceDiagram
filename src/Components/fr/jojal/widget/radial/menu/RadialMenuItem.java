package Components.fr.jojal.widget.radial.menu;

import Components.fr.jojal.widget.radial.item.RadialItem;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.effect.InnerShadow;


public class RadialMenuItem extends RadialItem {

	private ObjectProperty<RadialItem> parentItem;

	
	public RadialMenuItem() {
        this.setOnMouseEntered(mouseEvent -> {
            setEffect(new InnerShadow());
        });
        this.setOnMouseExited(mouseEvent -> {
            setEffect(null);
        });
    }
    public RadialMenuItem(boolean checkItem) {
	    if (checkItem){
	        checkItemInitialize();
        }

        this.setOnMouseEntered(mouseEvent -> {
            setEffect(new InnerShadow());
        });
        this.setOnMouseExited(mouseEvent -> {
            setEffect(null);
        });

    }

    public boolean isSelected(){
	    return selected.get();
    }
    public void setSelected(boolean selected){
        Platform.runLater(() -> {
	    this.selected.set(selected);
    });
    }
    public BooleanProperty selectedProperty(){
	    return selected;
    }


	public final ObjectProperty<RadialItem> parentItemProperty() {
        if (parentItem == null) {
        	parentItem = new SimpleObjectProperty<>(this, "parentItem");
        }
        return parentItem;
    }
    
    public final RadialItem getParentItem() {
    	return parentItemProperty().get();
    }
    
    public final void setParentItem(RadialMenuContainer radialItem) {
    	this.parentItemProperty().set(radialItem);
    } 
}
