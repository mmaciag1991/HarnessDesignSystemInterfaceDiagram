package _TryyClass;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketStart extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception {
        //System.out.println("Connesting....");

        boolean state = true;


        int line;                                           //2048
        Socket socket = new Socket("192.168.215.10", 2049);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        OutputStream out = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(out);
        pw.write("5");

        while ( state) {

            //System.out.println("reading....");

                    line  = input.read();
                    //System.out.println("Input: " + line);


        }
//
//        final Task t = new Task<Void>() {
//
//
//
//            @Override
//            protected Void call() throws Exception {
//                //System.out.println("Device: " + socket.getInetAddress());
//
//                while ( state) {
//                    //System.out.println("reading....");
//                    Thread.sleep(500);
////                    line  = input.readLine();
////                    //System.out.println("Input: " + line);
//
//                }
//                //System.out.println("Finish....");
//                return null;
//            }
//
//        };
//
//        final Thread th = new Thread(t);
//        th.setPriority(Thread.MAX_PRIORITY);
//        th.setDaemon(true);
//        th.start();
//        th.interrupt();
    }

}
