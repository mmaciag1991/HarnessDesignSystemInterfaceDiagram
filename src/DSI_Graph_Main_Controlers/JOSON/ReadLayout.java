package DSI_Graph_Main_Controlers.JOSON;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadLayout {

    public void readLayout() {
        try {
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("customer.json"));

            // create parser
            JsonObject parser = (JsonObject) Jsoner.deserialize(reader);

            // read customer details
            String moduleId = (String) parser.get("moduleId");
            String xCode = (String) parser.get("xCode");
            String connectorType = (String) parser.get("connectorType");
            String cordX = (String) parser.get("cordX");
            String cordY = (String) parser.get("cordY");

            //System.out.println(moduleId);
            //System.out.println(xCode);
            //System.out.println(connectorType);
            //System.out.println(cordX);
            //System.out.println(cordY);


            //close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
