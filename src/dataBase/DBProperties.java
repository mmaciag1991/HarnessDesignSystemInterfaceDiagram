/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;


import java.io.*;
import java.net.InetAddress;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateusz Maciag
 */
public class DBProperties {
    Properties properties = new Properties();
    InputStream inputStream;
    OutputStream output = null;

    public void mkDbProperties() {
        
        try {

            output = new FileOutputStream("database.properties");
            properties.setProperty("host", "localhost");
            properties.setProperty("port", "3306");
            properties.setProperty("db", "store");
            properties.setProperty("user", "root");
            properties.setProperty("password", "");
            properties.setProperty("winautentation", "");
            properties.store(output, null);
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String loadPropertiesFile() {
        try {
            String computername = InetAddress.getLocalHost().getHostName();
            if (computername.contains("DESKTOP-A3AGJ6G")){
                inputStream = new FileInputStream("database_DESKTOP_A3AGJ6G.properties");
            }else{
                inputStream = new FileInputStream("database.properties");
            }

            properties.load(inputStream);
            return properties.getProperty("db");
        } catch (IOException e) {
            ////System.out.println("DDDD");
        }
        return "";
    }

    public Properties loadAndGetPropertiesFile() {
        try {
            String computername = InetAddress.getLocalHost().getHostName();
            if (computername.contains("DESKTOP-A3AGJ6G")){
                inputStream = new FileInputStream("database_DESKTOP_A3AGJ6G.properties");
            }else{
                inputStream = new FileInputStream("database.properties");
            }
           // ApplicationController.setProgressLogo();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
          //  ApplicationController.setErrorLogo();
        }
        //ApplicationController.setNormalLogo();
        return properties;
    }
}
