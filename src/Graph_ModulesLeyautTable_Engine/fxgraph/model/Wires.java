package Graph_ModulesLeyautTable_Engine.fxgraph.model;

import DSI_Graph_Main_Controlers.DSI_Model;
import Model.PICtoGIM_Model;
import Model.Section10_Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static DSI_Graph_Main_Controlers.DSI_Model.section10_models;


public class Wires {

    private final StringProperty name;
    private final StringProperty color;
    private final StringProperty fromXcode;
    private final StringProperty fromPin;
    private final StringProperty toXcode;
    private final StringProperty toPin;
    private final StringProperty lenght;
    private final StringProperty area;
    private final StringProperty way = new SimpleStringProperty("");;
    private final StringProperty lp;
    private final StringProperty customerCode;

    private  StringProperty GIMCode;
    private  StringProperty description;

    private final StringProperty conneectorFrom;
    private final StringProperty conneectorTo;

    public List<String> asemblyVariants = new ArrayList<>();

    public Wires() {
        this(null, null, null, null, null, null, null,null,null,null,null);

    }

    public Wires(String name, String color, String fromXcode, String fromPin, String toXcode, String toPin, String lenght, String area, String lp, String customerCode, List<String> asemblyVariants) {
        this.name = new SimpleStringProperty(name);
        this.color = new SimpleStringProperty(color);
        this.fromXcode = new SimpleStringProperty(fromXcode);
        this.fromPin = new SimpleStringProperty(fromPin);
        this.toXcode = new SimpleStringProperty(toXcode);
        this.toPin = new SimpleStringProperty(toPin);
        this.lenght = new SimpleStringProperty(lenght);
        this.area = new SimpleStringProperty(area);
        this.lp = new SimpleStringProperty(lp);
        this.customerCode = new SimpleStringProperty(customerCode);



        this.description = new SimpleStringProperty("Brak Danych");
        this.GIMCode = new SimpleStringProperty("Brak Danych");

        this.conneectorFrom = new SimpleStringProperty(fromXcode);
        this.conneectorTo = new SimpleStringProperty(toXcode);;

        this.asemblyVariants = asemblyVariants;

        this.customerCode.addListener((observableValue, s, t1) -> {
            System.out
                    .println(t1);
            translateToGPN(t1);
        });

        translateToGPN(customerCode);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String Id) {
        this.name.set(Id);
    }

    public StringProperty NameProperty() {
        return name;
    }


    public String getColor() {
        return color.get();
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public StringProperty colorProperty() {
        return color;
    }


    public String getFromXcode() {
        return fromXcode.get();
    }

    public void setFromXcode(String fromXcode) {
        this.fromXcode.set(fromXcode);
    }

    public StringProperty fromXcodeProperty() {
        return fromXcode;
    }


    public String getFromPin() {
        return fromPin.get();
    }

    public void setFromPin(String fromPin) {
        this.fromPin.set(fromPin);
    }

    public StringProperty fromPinProperty() {
        return fromPin;
    }


    public String getToXcode() {
        return toXcode.get();
    }

    public void setToXcode(String Status) {
        this.toXcode.set(Status);
    }

    public StringProperty toXcodeProperty() {
        return toXcode;
    }


    public String getToPin() {
        return toPin.get();
    }

    public void setToPin(String toPin) {
        this.toPin.set(toPin);
    }

    public StringProperty toPinProperty() {
        return toPin;
    }


    public String getLenght() {
        return lenght.get();
    }

    public void setLenght(String lenght) {
        this.lenght.set(lenght);
    }

    public StringProperty lenghtProperty() {
        return lenght;
    }


    public String getArea() {
        return area.get();
    }

    public void setArea(String area) {
        this.area.set(area);
    }

    public StringProperty areaProperty() {
        return area;
    }


    public StringProperty wayProperty() {
        return way;
    }
    public String getWay() {
        return way.get();
    }

    public StringProperty lpProperty() {
        return lp;
    }

    public String getLp() {
        return lp.get();
    }

    public StringProperty getConneectorToBase(){
        return conneectorTo;
    }

    public void setConneectorToBase(String conneectorTo) {
        this.conneectorTo.set(conneectorTo);
    }

    public StringProperty getConneectorFromBase() {
        return conneectorFrom;
    }

    public void setConneectorFromBase(String conneectorFrom) {
        this.conneectorFrom.set(conneectorFrom);
    }

    public StringProperty customerCodeProperty() {
        return customerCode;
    }

    public String getCustomerCode() {
        return customerCode.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getGIMCode() {
        return GIMCode.get();
    }

    private final Image assemblyIn  = new Image("/resources/icon/pearl_red_24x24.png");
    private final Image assemblyThis  = new Image("/resources/icon/pearl_green_24x24.png");
    public ContextMenu cellContextMenu(){

        //translateToGPN(getCustomerCode());

       // System.out.println(getGIMCode());

        GridPane gridPaneCell = new GridPane();
        gridPaneCell.setGridLinesVisible(true);
        gridPaneCell.setAlignment(Pos.CENTER);

        Label idLbl1 = new Label(" Lp.  ");
        gridPaneCell.add(idLbl1,0,0);

        Label idLbl2 = new Label("  "+getLp()+"  ");
        gridPaneCell.add(idLbl2,1,0);

        Label xcodeLbl1 = new Label(" Identyfikator:  ");
        gridPaneCell.add(xcodeLbl1,0,1);

        Label xcodeLbl2 = new Label("  "+getName()+"  ");
        gridPaneCell.add(xcodeLbl2,1,1);

        Label customercodeLbl1 = new Label(" Kod Klienta:  ");
        gridPaneCell.add(customercodeLbl1,0,2);

        Label customercodeLbl2 = new Label("  "+getCustomerCode()+"  ");
        gridPaneCell.add(customercodeLbl2,1,2);

        Label gmcodeLbl1 = new Label(" Kod GPN:  ");
        gridPaneCell.add(gmcodeLbl1,0,3);

        Label gmcodeLbl2 = new Label("  "+getGIMCode()+"  ");
        gridPaneCell.add(gmcodeLbl2,1,3);

//        Button imageButton = new Button("Zaladuj zdjecie");
//
//        imageButton.setPrefSize(450,250);
//        gridPaneCell.add(imageButton,2,0,1,5);
//
//        System.out.println(getGIMCode());
//
//        imageButton.setOnAction(event -> {
//            imageButton.setGraphic(new ImageView(new Image("/resources/images/error.png", 150, 150, true, true)));
//            //System.out.println(DSI_Model.imagesListParts.size());
//            for (String imagePath : DSI_Model.imagesListParts) {
//                if (imagePath.contains(getGIMCode())) {
//                    System.out.println(getGIMCode());
//                    System.out.println(imagePath);
//                    ImageView connectorImg = new ImageView(new Image("file:" + imagePath, 150, 150, true, true));
//                    imageButton.setGraphic(connectorImg);
//                    break;
//                }
//            }
//            event.consume();
//        });



        VBox vBox =new VBox();
        Label title = new Label("Obiekt: "+getDescription());
        title.setStyle("-fx-font-size: 20;");

        Label title4 = new Label("Zawarty w: ");
        title4.setStyle("-fx-font-size: 20;");

        ObservableList<String> asemblyObsList = FXCollections.observableArrayList();


//        asemblyVariants.parallelStream().forEach(s -> {
//            section10_models.parallelStream().forEach(s2 -> {
//
//                if (s.equals(s2.getVariant_())){
//                    asemblyObsList.add("Wariant: "+s2.getVariant_()+"  Referencja: "+s2.getReference()+" "+s2.getVersion()+"  Opis:"+s2.getVariable());
//                }
//
//            });
//        });
        for (Section10_Model s2 : section10_models){
            for (String s : asemblyVariants){

                if (s.equals(s2.getVariant_())){
                    asemblyObsList.add(" "+s2.getVariant_()+"  : "+s2.getReference()+" "+s2.getVersion()+" : "+s2.getVariable());
                }

            }
        }

        ListView<String> asemlylist = new ListView<String>(asemblyObsList);
        asemlylist.setPrefHeight(200);
        asemlylist.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(assemblyIn);

                    for (Map.Entry entry : DSI_Model.listOpenedNumbers.entrySet()){
                        // System.out.println(entry.getValue().toString());
                        if (name.contains(entry.getKey().toString())/* && name.contains(entry.getValue().toString())*/){
                            imageView.setImage(assemblyThis);
                            name = "Otwarte w: "+name;
                        }
                        if (entry.getValue().toString().equals("_Full")){
                            imageView.setImage(assemblyThis);
                            name = "Otwarte w: "+name;
                        }
                    }



                    setText(name);
                    setGraphic(imageView);
                }
            }
        });

        vBox.getChildren().addAll(title, gridPaneCell, title4,asemlylist);
        vBox.setFillWidth(true);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem tableItem1 = new MenuItem("", vBox);


        //imageButton.setStyle("-fx-background-color:rgba("+colors.get(0).getRed()+","+colors.get(0).getGreen()+","+colors.get(0).getBlue()+",.5)");

        contextMenu.getItems().addAll(tableItem1);

        return contextMenu;
    }

    public void translateToGPN(String CustomerCode) {
        if (CustomerCode.startsWith("G")) {
            for (PICtoGIM_Model PICtoGIM_model : DSI_Model.getHousing_models()) {
                if (CustomerCode.equals(PICtoGIM_model.getGmCode()) && (PICtoGIM_model.getGmCode().startsWith("G"))) {
                    this.description = new SimpleStringProperty(PICtoGIM_model.getDescription());
                    this.GIMCode = new SimpleStringProperty(PICtoGIM_model.getGmCode());
                    break;
                }
            }
        } else if (CustomerCode.toLowerCase().contains("splice")) {
            this.description = new SimpleStringProperty(CustomerCode);
            this.GIMCode = new SimpleStringProperty("SPLICE");
        } else {
            for (PICtoGIM_Model PICtoGIM_model : DSI_Model.getHousing_models()) {
                if (CustomerCode.equals(PICtoGIM_model.getPartnerCode()) && (PICtoGIM_model.getGmCode().startsWith("G"))) {
                    this.description = new SimpleStringProperty(PICtoGIM_model.getDescription());
                    this.GIMCode = new SimpleStringProperty(PICtoGIM_model.getGmCode());
                    break;
                }
            }
        }
    }
}