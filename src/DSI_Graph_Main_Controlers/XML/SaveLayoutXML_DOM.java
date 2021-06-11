package DSI_Graph_Main_Controlers.XML;


import Graph_ModulesLeyautTable_Engine.fxgraph.graph.Module;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class SaveLayoutXML_DOM {

    public void save(String testerN, String testerCols, String testersRows, List<Module> moduleList1/*, List<Module> moduleList2*/){
        //String moduleId, String xCode, String connectorType, String cordX, String cordY
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Podaj nazwê Siatki Layautu");
        dialog.setHeaderText("");
        dialog.setContentText("Wprowadz nazwê:");

        dialog.getDialogPane().getStylesheets().add("/resources/style/jmetro/combination.css");
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.initStyle(StageStyle.TRANSPARENT);
        dialog.setGraphic(null);

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(testerName ->{

            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Plik Siatki Layoutu (*.xmlLayout)", "*.xmlLayout");
            fileChooser.setTitle("Zapisywanie siatki layoutu");
            fileChooser.setInitialFileName(testerName);
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File fileXml = fileChooser.showSaveDialog(new Stage());

        try {
            // create new `Document`
            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document dom = builder.newDocument();

            // first create root element
            Element rootLayout = dom.createElement("Layout");
            dom.appendChild(rootLayout);
            Element rootTester = dom.createElement("tester");
            rootLayout.appendChild(rootTester);


            // set `id` attribute to root element
            Attr attr = dom.createAttribute("id");
            attr.setValue(LocalDateTime.now().toString());
            rootTester.setAttributeNode(attr);

            // now create child elements (name, email, phone)


            Element name = dom.createElement("testerName");
            name.setTextContent(testerName);
            Element cols = dom.createElement("cols");
            cols.setTextContent(testerCols);
            Element rows = dom.createElement("rows");
            rows.setTextContent(testersRows);

            // add child nodes to root node
            rootTester.appendChild(name);
            rootTester.appendChild(cols);
            rootTester.appendChild(rows);

            for (Module moduleItem : moduleList1) {
                // first create root element
                Element rootModule1 = dom.createElement("module");
                rootLayout.appendChild(rootModule1);


                // set `id` attribute to root element
                Attr attrModule1 = dom.createAttribute("id");
                attrModule1.setValue(moduleItem.getModuleId());
                rootModule1.setAttributeNode(attrModule1);

                // now create child elements (xCode1, email, phone)
                Element xCode1 = dom.createElement("xCode");
                xCode1.setTextContent(moduleItem.getModuleXcode());
                Element connector1 = dom.createElement("connector");
                connector1.setTextContent(moduleItem.getModuleConnectorCustomerCode());
                Element cordX1 = dom.createElement("cordX");
                cordX1.setTextContent(moduleItem.getModuleCordX());
                Element cordY1 = dom.createElement("cordY");
                cordY1.setTextContent(moduleItem.getModuleCordY());

                // add child nodes to root node
                rootModule1.appendChild(xCode1);
                rootModule1.appendChild(connector1);
                rootModule1.appendChild(cordX1);
                rootModule1.appendChild(cordY1);
            }
//            for (Module moduleItem : moduleList2) {
//                // first create root element
//                Element rootModule1 = dom.createElement("module");
//                rootLayout.appendChild(rootModule1);
//
//
//                // set `id` attribute to root element
//                Attr attrModule1 = dom.createAttribute("id");
//                attrModule1.setValue(moduleItem.getModuleId());
//                rootModule1.setAttributeNode(attrModule1);
//
//                // now create child elements (xCode1, email, phone)
//                Element xCode1 = dom.createElement("xCode");
//                xCode1.setTextContent(moduleItem.getModuleXcode());
//                Element connector1 = dom.createElement("connector");
//                connector1.setTextContent(moduleItem.getModuleConnectorCustomerCode());
//                Element cordX1 = dom.createElement("cordX");
//                cordX1.setTextContent(moduleItem.getModuleCordX());
//                Element cordY1 = dom.createElement("cordY");
//                cordY1.setTextContent(moduleItem.getModuleCordY());
//
//                // add child nodes to root node
//                rootModule1.appendChild(xCode1);
//                rootModule1.appendChild(connector1);
//                rootModule1.appendChild(cordX1);
//                rootModule1.appendChild(cordY1);
//            }

            // write DOM to XML file
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.transform(new DOMSource(dom), new StreamResult(fileXml));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        });
    }
}
//https://attacomsian.com/blog/java-read-write-xml