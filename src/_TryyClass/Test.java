package _TryyClass;




import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Random;

public class Test extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    static String TSD = "jdbc:sqlserver://STARAPPS\\Testertrack:1433;";
    static TextField textField = new TextField("2020-12-14");
    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        primaryStage.setTitle("Testertrack EVENT!");
        Button btn = new Button();
        btn.setText("ADD");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                dbconnect2update();

            }
        });

        StackPane root = new StackPane();
        root.getChildren().addAll(textField,btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    static String[] TestersIDs = {"16","17","18","19","20","21","37","128"};
    static String[] Hours = {"12",};
    static Random random = new Random();
    static String day = "13";
    public static void dbconnect2update() {
        ResultSet rs2 = null;
        Connection conn2 = null;

        try {
            System.setProperty("java.net.preferIPv6Addresses", "true");
            String url = TSD;
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

            String user = "testing.dep";
            String pass = "Notika04star";

            conn2 = DriverManager.getConnection(url, user, pass);

            Statement sqlStatement2 = conn2.createStatement();

            if (conn2 != null) {
                for (String tester : TestersIDs) {
                    for (String hour : Hours){

                        int m = random.nextInt(10);

                    String cM = String.format("%02d", m);
                    String cM1 = String.format("%02d", m + 1);
                        String acM = String.format("%02d", m + 1);
                        String acM1 = String.format("%02d", m + 3);


                    String query = "INSERT INTO \"Testertrack\".\"dbo\".\"TesterEvents\" (\"EventStart\", \"EventEnd\", \"EventID\", \"TesterID\") VALUES ('"+textField.getText()+"T"+hour+":" + cM + ":14.900', '"+textField.getText()+"T"+hour+":" + cM1 + ":40.973', '4', '" + tester + "');\n";
                        //System.out.println(query);
                    PreparedStatement preparedStmt = conn2.prepareStatement(query);
                    preparedStmt.executeUpdate();

                    String query2 = "INSERT INTO \"Testertrack\".\"dbo\".\"TesterEvents\" (\"EventStart\", \"EventEnd\", \"EventID\", \"TesterID\") VALUES ('"+textField.getText()+"T"+hour+":" + acM + ":42.900', '"+textField.getText()+"T"+hour+":" + acM1 + ":40.973', '1', '" + tester + "');\n";
                        //System.out.println(query2+"\n ----------------");
                    PreparedStatement preparedStmt2 = conn2.prepareStatement(query2);
                    preparedStmt2.executeUpdate();
                    }
                }
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn2 != null && !conn2.isClosed()) {
                    conn2.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
