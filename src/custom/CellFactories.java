package custom;

import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * Created by Mateusz Maciag on 8/720.
 */
public class CellFactories {

    public  Callback<TableColumn, TableCell> cellFactoryCheckBox = new Callback<TableColumn, TableCell>() {

        @Override
        public TableCell call(final TableColumn param) {
            final CheckBox checkBox = new CheckBox();
            TableView tableView = new TableView();
            final TableCell cell = new TableCell() {

                public void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
                    if(item == null){
                        checkBox.setVisible(false);

                    }else {
                        checkBox.setVisible(true);
                        checkBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                  

                            }
                        });


                    }
                }
            };
            cell.setGraphic(checkBox);
            return cell;
        }
    };


}
