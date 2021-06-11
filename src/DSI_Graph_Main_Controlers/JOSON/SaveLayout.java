package DSI_Graph_Main_Controlers.JOSON;


import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Module;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


public class SaveLayout {

    public void save(String testerName, String testerCols, String testersRows, List<Module> moduleList1, List<Module> moduleList2){
        //String moduleId, String xCode, String connectorType, String cordX, String cordY
        try {

            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Plik Layoutu (*.layout)", "*.layout");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(new Stage());
            // create a writer
            BufferedWriter writer = Files.newBufferedWriter(file.toPath());

            // create customer object
            JsonObject tester = new JsonObject();
            tester.put("name", testerName);
            tester.put("cols", testerCols);
            tester.put("rows", testersRows);
            Jsoner.serialize(tester, writer);
            for (Module moduleItem : moduleList1) {
                //System.out.println(moduleItem);
                JsonObject module = new JsonObject();
                module.put("moduleId", moduleItem.getModuleId());
                module.put("xCode", moduleItem.getModuleXcode());
                module.put("connectorType", moduleItem.getModuleConnectorCustomerCode());
                module.put("cordX", moduleItem.getModuleCordX());
                module.put("cordY", moduleItem.getModuleCordY());

                // write JSON to file
                Jsoner.serialize(module, writer);
            }
            for (Module moduleItem : moduleList2) {
                //System.out.println(moduleItem);
                JsonObject module = new JsonObject();
                module.put("moduleId", moduleItem.getModuleId());
                module.put("xCode", moduleItem.getModuleXcode());
                module.put("connectorType", moduleItem.getModuleConnectorCustomerCode());
                module.put("cordX", moduleItem.getModuleCordX());
                module.put("cordY", moduleItem.getModuleCordY());

                // write JSON to file
                Jsoner.serialize(module, writer);
            }


            //close the writer
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
