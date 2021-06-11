package DSI_Graph_Main_Controlers.XML;

import Model.SystemPintable;
import Model.TestersData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadLayoutXML_DOM/*_Test extends Application*/ {

    Path local = Paths.get("F:\\Nowy folder\\DSI");
    public TestersData testersData = new TestersData();

//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//
//        PathFilter all = PathFilter.acceptAllFiles("Wszystkie pliki");
//
//        PathFilter txt = PathFilter.forFileExtension("Plik Siatki Layoutu (*.xmlLayout)", "xmllayout");
//        PathFilter xlsx = PathFilter.forFileExtension("Excel 2007", "xlsx");
//
//
//
//        FXFileChooserStage fc = null;
//        try {
//            fc = FXFileChooserStage
//                    .create(Skin.MODENA, local, txt, all);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        List<Location> locations = new ArrayList<>();
//        FileSystemView fsv = FileSystemView.getFileSystemView();
//        File[] drives = File.listRoots();
//        if (drives != null && drives.length > 0) {
//            for (File aDrive : drives) {
////                //System.out.println("Drive Letter: " + aDrive);
////                //System.out.println("\tType: " + fsv.getSystemTypeDescription(aDrive));
////                //System.out.println("\tTotal space: " + aDrive.getTotalSpace());
////                //System.out.println("\tFree space: " + aDrive.getFreeSpace());
////                //System.out.println();
//                locations.add(Locations.withName(fsv.getSystemTypeDescription(aDrive) + " '" + aDrive + "'  Rozmiar: " + aDrive.getTotalSpace()/(1024*1024*1024) + " Gb   Wolne miejsce: " + aDrive.getFreeSpace()/(1024*1024*1024)+" Gb", Paths.get(String.valueOf(aDrive))));
//            }
//        }
//
//
//        fc.addLocations(locations);
//        Optional<Path> selection = fc.showOpenDialog(primaryStage);
//        selection.ifPresent(path -> {
//        readLayoutXML(path.toFile());
///***********************************************/
//        });
//
//    }
//


    public TestersData readLayoutXML(Path path){





            try {
                // parse XML file to build DOM
                DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder();
                Document dom = builder.parse(path.toFile());

                // normalize XML structure
                dom.normalizeDocument();

                // get root element
                Element root = dom.getDocumentElement();

                NodeList tester = root.getElementsByTagName("tester");
                // print attributes

//
                //System.out.println("-------------------------  Tester  -----------------------------");
                //System.out.println("ID: " + tester.item(0).getAttributes().getNamedItem("id").getNodeValue());
                //System.out.println("Name: " + tester.item(0).getChildNodes().item(1).getTextContent());
                //System.out.println("Cols: " + tester.item(0).getChildNodes().item(3).getTextContent());
                //System.out.println("Rows: " + tester.item(0).getChildNodes().item(5).getTextContent());

                testersData.setTesterID(path + " / " + tester.item(0).getAttributes().getNamedItem("id").getNodeValue());
                testersData.setTesterName(tester.item(0).getChildNodes().item(1).getTextContent());
                testersData.setTesterColumns(tester.item(0).getChildNodes().item(3).getTextContent());
                testersData.setTesterRows(tester.item(0).getChildNodes().item(5).getTextContent());

                //System.out.println("-------------------------  Modu³y  -----------------------------");
                NodeList modules = root.getElementsByTagName("module");
                // print elements
                for (int i = 0 ; i < modules.getLength() ; i++){
                    //System.out.println("ID: " + modules.item(i).getAttributes().getNamedItem("id").getNodeValue());
                    //System.out.println("xCode: " + modules.item(i).getChildNodes().item(1).getTextContent());
                    //System.out.println("Connector: " + modules.item(i).getChildNodes().item(3).getTextContent());
                    //System.out.println("cordX: " + modules.item(i).getChildNodes().item(5).getTextContent());
                    //System.out.println("cordY: " + modules.item(i).getChildNodes().item(7).getTextContent());
                    testersData.getSystemPintable().add(new SystemPintable("Modu³y Virtualne",
                            modules.item(i).getAttributes().getNamedItem("id").getNodeValue(),
                            modules.item(i).getChildNodes().item(1).getTextContent(),
                            modules.item(i).getChildNodes().item(3).getTextContent(),
                            "",
                            modules.item(i).getChildNodes().item(5).getTextContent(),
                            modules.item(i).getChildNodes().item(7).getTextContent(),
                            null,
                            "true",
                            "virtual"));

                }




            } catch (Exception ex) {
                ex.printStackTrace();
            }


        return testersData;
    }

}
