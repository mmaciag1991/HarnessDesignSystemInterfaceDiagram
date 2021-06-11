package DSI_Graph_Main_Controlers.XML;

import java.io.File;
import java.nio.file.Path;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import Model.SystemPintable;
import Model.TestersData;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ReadIvisionSP {


    public  TestersData getPintable(Path path, String cols, String rows){
         TestersData testersData = new TestersData();
        try {


            File inputFile = (path.toFile());
            testersData.setTesterName(inputFile.getAbsolutePath());
            testersData.setTesterID(inputFile.getUsableSpace()+"");
            testersData.setTesterColumns(cols);
            testersData.setTesterRows(rows);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Pin");
            System.out.println("----------------------------");


            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    if (eElement.getElementsByTagName("Type").item(0).getTextContent().equals("Tp")){
                        String xcode;
                        String type;
                        String cordX;
                        String cordY;
                        //String pins;
                        String id;

                        final boolean[] exist = {false};



                        String[] comment = eElement.getElementsByTagName("Comment").item(0).getTextContent().split("\\|");

                        if (comment.length>1) {
                            type = comment[0].replace(" ", "");
                            cordX = comment[1].replace(" ", "");
                            cordY = comment[2].replace(" ", "");

                            xcode = eElement.getElementsByTagName("PinName").item(0).getTextContent().split(" ")[0];

                            id = eElement.getElementsByTagName("System").item(0).getTextContent();


                            testersData.getSystemPintable().stream().forEach(systemPintable -> {
                                if (systemPintable.getXcode().equals(xcode)) {
                                    systemPintable.setPins((Integer.valueOf(systemPintable.getPins()) + 1) + "");
                                    exist[0] = true;
                                } else {

                                }

                            });
                            if (!exist[0] && !xcode.equals(""))
                                testersData.getSystemPintable().add(new SystemPintable("IvisionStudio", id, xcode, type, "0", cordX, cordY, null, "false", "false"));

                        }

                }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testersData;
    }
}