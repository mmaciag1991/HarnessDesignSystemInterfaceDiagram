/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Mateusz Maciag
 */
public class ListEmployee {
    
    public String employeeId;
    public String employeeName;
    public ImageView employeeImage;
    public String employeeID;

    public ListEmployee(String employeeId, String employeeName, Image employeeImage, String employeeID) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeImage = new ImageView(employeeImage);
        this.employeeImage.setFitHeight(64);
        this.employeeImage.setFitWidth(64);
        this.employeeID = employeeID;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public ImageView getEmployeeImage() {
        return employeeImage;
    }

    public void setEmployeeImage(Image employeeImage) {
        this.employeeImage = new ImageView(employeeImage);
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}
