/*-
 * #%L
 * FXFileChooser
 * %%
 * Copyright (C) 2017 - 2020 Oliver Loeffler, Raumzeitfalle.net
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

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

final class FileChooserView extends AnchorPane {
     private FileChooserController controller;
    public FileChooserView(PathSupplier pathSupplier, final HideableView window, FileChooserModel model, Skin skin, FileChooserViewOption fileChooserViewOption) throws IOException {

    	 Class<?> thisClass = getClass();
         String fileName = thisClass.getSimpleName() + ".fxml";
         URL resource = thisClass.getResource(fileName);
         FXMLLoader loader = new FXMLLoader(resource);
         controller = new FileChooserController(model, pathSupplier, window, fileChooserViewOption);
         loader.setController(controller);
         Parent view = loader.load();
         this.getChildren().add(view);
         AnchorPane.setLeftAnchor(view, 0.0);
         AnchorPane.setRightAnchor(view, 0.0);
         AnchorPane.setTopAnchor(view, 0.0);
         AnchorPane.setBottomAnchor(view, 0.0);
         Skin.applyTo(this,skin);

    }

     public FileChooserController getController() {
          return controller;
     }
}
