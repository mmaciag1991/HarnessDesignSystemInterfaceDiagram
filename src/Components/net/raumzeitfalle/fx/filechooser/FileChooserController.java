/*-
 * #%L
 * FXFileChooser
 * %%
 * Copyright (C) 2017 - 2019 Oliver Loeffler, Raumzeitfalle.net
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package Components.net.raumzeitfalle.fx.filechooser;

import Components.net.raumzeitfalle.fx.filechooser.locations.Location;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.ResourceBundle;

final class FileChooserController implements Initializable {
    
    @FXML
    private SplitMenuButton chooser;
    
    @FXML
    private MenuItem usersHomeCommand;

    @FXML
    private MenuButton fileExtensionFilter;
    
    @FXML
    private MenuItem showAllFilesFilter;

    @FXML
    private FlowPane counterPane;

    @FXML
    private Label filteredPathsCount;

    @FXML
    private Label allPathsCount;
    
    @FXML
    private ProgressBar progressBar;

    @FXML
    private TextField fileNameFilter;

    @FXML
    private ListView<IndexedPath> listOfFiles;

    @FXML
    private TextField selectedFile;
    
    @FXML
    private Button refreshButton;
    
    @FXML
    private Button stopButton;
    
    @FXML
    private MenuButton sortMenu;
    
    @FXML
    private MenuItem buttonSortAz;

    @FXML
    private MenuItem buttonSortZa;
    
    @FXML
    private MenuItem buttonSortOldestFirst;

    @FXML
    private MenuItem buttonSortRecentFirst;
    
    @FXML
    private Button okButton;
    
    @FXML
    private Button cancelButton;
    @FXML
    private Label title;
    
    @FXML
    private VBox fileChooserView;
      
    private final FileChooserModel model;
    
    private final HideableView stage;
    
    private final BooleanProperty showOkayCancelButtons;
    
     final PathSupplier pathSupplier;

     final LocationMenuItemFactory menuItemFactory;

    /**
     * Creates a new {@link FileChooserController} which provides all logic and functionality
     * for the {@link FXFileChooserStage}, {@link FXFileChooserDialog} and {@link SwingFileChooser} components.
     *
     * @param fileChooserModel The data model.
     * @param pathSupplier Provides a path on demand, e.g. can be File Chooser or Directory Chooser component. This component is called when clicked on Choose Directory button.
     * @param window The parent window which shall be closable.
     * @param fileChooserViewOption The {@link FileChooserViewOption} decides if the view will have its own OKAY/CANCEL buttons or if OKAY/CANCEL buttons are provided e.g. by the parent container (e.g. Dialog).
     */
    public FileChooserController(final FileChooserModel fileChooserModel, final PathSupplier pathSupplier, final HideableView window, FileChooserViewOption fileChooserViewOption) {
       this.model = fileChooserModel;
       this.stage = window;
       this.showOkayCancelButtons = new SimpleBooleanProperty(FileChooserViewOption.STAGE.equals(fileChooserViewOption));
       this.pathSupplier = pathSupplier;
       this.menuItemFactory = new LocationMenuItemFactory(model::updateFilesIn);
    }

    public void stetTitle(String title){
        this.title.setText(title);
    }
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    	this.listOfFiles.setItems(this.model.getFilteredPaths());

        this.fileNameFilter.textProperty().addListener(l -> handleFileNameFilterChanges());

        StringBinding binding = Bindings.createStringBinding(
                ()->this.model.currentSearchPath().get().toAbsolutePath().toString(),
                this.model.currentSearchPath());

        this.selectedFile.promptTextProperty()
                .bind(binding);

        /*
         * TODO: Add Key listener to accept selected file when pressing ENTER
         * TODO: Add Key listener to perform CANCEL when pressing ESC
         */
        this.listOfFiles.setOnMouseClicked(this::handleDoubleClickInFilesList);

        this.listOfFiles.setCellFactory(e->new FilesListCell());
        this.listOfFiles.getSelectionModel()
        	.selectedItemProperty()
        	.addListener(l -> model.setSelectedFile(selectedItem()));

        this.selectedFile.textProperty().bind(model.selectedFileNameProperty());
        this.usersHomeCommand.setOnAction(e -> model.changeToUsersHome());

        this.showAllFilesFilter.setVisible(false);

        this.model.initializeFilter(fileNameFilter.getText());
        this.fileNameFilter.setOnKeyPressed(this::handleReturnKeyPressedInFilter);
        
        // initialize PathFilter menu
		this.model.getPathFilter()
		          .forEach(this::addNewPathFilterMenuItem);
        
        // permit to dynamically add or remove PathFilter menu items
        this.model.getPathFilter()
        	      .addListener(this::handlePathFilterModelChange);

        this.chooser.setOnAction(e -> changeDirectory());


        this.model.getLocations().forEach(l->chooser.getItems().add(menuItemFactory.apply(l)));

        this.model.getLocations().addListener(this::handleAddedLocation);

        refreshButton.setOnAction(e -> model.refreshFiles());
        stopButton.setOnAction(e -> model.getUpdateService().cancelUpdate());
 
        assignSortAction(buttonSortAz, PathComparator.byName());
        assignSortAction(buttonSortZa, PathComparator.byName().reversed());
        assignSortAction(buttonSortOldestFirst, PathComparator.byLastModified());
        assignSortAction(buttonSortRecentFirst, PathComparator.byLastModified().reversed());
                
        buttonSortRecentFirst.setVisible(true);
        buttonSortOldestFirst.setVisible(true);
        
        ReadOnlyBooleanProperty updateIsRunning = model.getUpdateService().runningProperty();
       
        /*
         *  TODO: replace progress indicator by progress bar which is updated in intervals only
         *  OR use indicator for small sets and bar for large data sets 
         */
        progressBar.progressProperty().bind(model.getUpdateService().progressProperty());
        
        //counterPane.visibleProperty().bind(updateIsRunning);
        counterPane.setVisible(true);
        stopButton.visibleProperty().bind(updateIsRunning);
        
        // TODO: update counts after refresh
        filteredPathsCount.textProperty().bind(model.filteredPathsSizeProperty().asString());
        allPathsCount.textProperty().bind(model.allPathsSizeProperty().asString());
                
        okButton.setOnAction(e -> okayAction());
        cancelButton.setOnAction(e -> cancelAction());
        
        okButton.disableProperty().bind(model.invalidSelectionProperty());
        okButton.visibleProperty().bind(showOkayCancelButtons);
        cancelButton.visibleProperty().bind(showOkayCancelButtons);
        this.model.updateFilterCriterion(this.model.getPathFilter().get(0), ""  );

    }

    private void handleReturnKeyPressedInFilter(KeyEvent keyEvent) {
        if (KeyCode.ENTER.equals(keyEvent.getCode())) {
            Path pastedPath = this.model.pastedPathProperty().get();
            if (null != pastedPath) {
                model.getUpdateService().restartIn(pastedPath);
                this.fileNameFilter.setText("");
            }
        }
    }

    private void handleFileNameFilterChanges() {
		this.listOfFiles.getSelectionModel().clearSelection();
		this.model.updateFilterCriterion(fileNameFilter.getText());

        evaluateIfPathWasEntered();

    }

    private void evaluateIfPathWasEntered() {
        try {
            Path possiblePath = Paths.get(fileNameFilter.getText());

            if (possiblePath.toFile().isFile()) {
                possiblePath = possiblePath.getParent();
            }

            if (null != possiblePath && possiblePath.toFile().exists()) {
                this.model.pastedPathProperty().set(possiblePath);
            } else {
                this.model.pastedPathProperty().set(null);
            }

        } catch (InvalidPathException ipe) {
            this.model.pastedPathProperty().set(null);
        }
    }

    private void handleDoubleClickInFilesList(MouseEvent event) {
		if (event.getClickCount() == 2) {
		    model.setSelectedFile(listOfFiles.getSelectionModel().getSelectedItem());
		    event.consume();
		    okayAction();
		}
	}
    
    private void okayAction() {
    	this.stage.closeView();
    }
    
    private void cancelAction() {
    	this.model.setSelectedFile(null);
        this.stage.closeView();
       // ((Stage)this.allPathsCount.getScene().getWindow()).close();
    }
    
    private void changeDirectory() {
    	Invoke.later(()->{
    		fileChooserView.setDisable(true);
            pathSupplier.getUpdate(value->model.getUpdateService().restartIn(value));
            fileChooserView.setDisable(false);
    	});
    }
    
    private void handlePathFilterModelChange(Change<? extends PathFilter> change) {
    	if (change.next()) {
    		change.getAddedSubList().forEach(this::addNewPathFilterMenuItem);
    		change.getRemoved().forEach(this::removePathFilterMenuItem);
    	}
    }

    private void handleAddedLocation(SetChangeListener.Change<? extends Location> change) {
        if (change.wasAdded()) {
            Location added = change.getElementAdded();
            LocationMenuItem locationMenuItem = menuItemFactory.apply(added);
            //Dysk lokalny | Dysk wymienny | Dysk sieciowy


            if (added.getName().contains("Dysk lokalny")) {
                Image image = new Image("resources/icon/hdd.png");
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(24);
                imageView.setFitHeight(24);
                locationMenuItem.setGraphic(imageView);
            }
            if (added.getName().contains("Dysk wymienny")) {
                Image image = new Image("resources/icon/hddusb.png");
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(24);
                imageView.setFitHeight(24);
                locationMenuItem.setGraphic(imageView);
            }
            if (added.getName().contains("Dysk sieciowy")) {
                Image image = new Image("resources/icon/hddnetwork.png");
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(24);
                imageView.setFitHeight(24);
                locationMenuItem.setGraphic(imageView);
            }
            chooser.getItems().add(locationMenuItem);
        }
    }

	private void addNewPathFilterMenuItem(PathFilter p) {
		Invoke.later(()->{
			MenuItem item = new MenuItem(p.getName());
			item.setOnAction(e -> this.model.updateFilterCriterion(p, fileNameFilter.getText()));
			this.fileExtensionFilter.getItems().add(item);

		});

	}
	
	private void removePathFilterMenuItem(PathFilter filterToRemove) {
		Invoke.later(()->this.fileExtensionFilter
			.getItems()
			.removeIf(mi->mi.getText().equalsIgnoreCase(filterToRemove.getName())));
	}

    private void assignSortAction(MenuItem menuItem, Comparator<IndexedPath> comparator) {
        menuItem.setOnAction(e -> 
            Invoke.later(()->{
                model.sort(comparator);
                SVGPath svgPath = new SVGPath();
                svgPath.getStyleClass().add("tool-bar-icon");
                svgPath.setContent(((SVGPath)menuItem.getGraphic()).getContent());
                sortMenu.setGraphic(svgPath);
                sortMenu.getGraphic().getStyleClass().add("tool-bar-icon");
            }));
    }

    private IndexedPath selectedItem() {
        return listOfFiles.getSelectionModel().selectedItemProperty().getValue();
    }


}
