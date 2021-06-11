package DSI_Graph_Main_Controlers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oshi.SystemInfo;

import java.io.IOException;

public class MainConfigurationControler {

    @FXML
    private Tab databaseview;
    @FXML
    private TabPane tabPane;
    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label cpuName1;
    @FXML
    private Label cpuModel1;
    @FXML
    private Label cpuPhisissCores1;
    @FXML
    private Label cpuLogicCores1;
    @FXML
    private Label cpuClock1;
    @FXML
    private Label cpuArch1;


    @FXML
    private Label gpuName1;
    @FXML
    private Label gpuId1;
    @FXML
    private Label gpuMemory1;


    @FXML
    private Label gpuName2;
    @FXML
    private Label gpuId2;
    @FXML
    private Label gpuMemory2;

    @FXML
    private Label gpuName3;
    @FXML
    private Label gpuId3;
    @FXML
    private Label gpuMemory3;


    @FXML
    private Label ramSlot1;
    @FXML
    private Label ramType1;
    @FXML
    private Label ramClock1;
    @FXML
    private Label ramMemory1;

    @FXML
    private Label ramSlot2;
    @FXML
    private Label ramType2;
    @FXML
    private Label ramClock2;
    @FXML
    private Label ramMemory2;

    @FXML
    private Label ramSlot3;
    @FXML
    private Label ramType3;
    @FXML
    private Label ramClock3;
    @FXML
    private Label ramMemory3;

    @FXML
    private Label ramSlot4;
    @FXML
    private Label ramType4;
    @FXML
    private Label ramClock4;
    @FXML
    private Label ramMemory4;


    public static SystemInfo systemInfo = new SystemInfo();

    public MainConfigurationControler(){}
    int all = 0;
    int allContPass = 0;
    @FXML
    private void initialize() throws IOException {
        Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/view/Server.fxml"));
            databaseview.setContent(root);

        cpuName1.setText(systemInfo.getHardware().getProcessor().getProcessorIdentifier().getName());
        cpuModel1.setText(systemInfo.getHardware().getProcessor().getProcessorIdentifier().getModel());

        all+=3;
        if (systemInfo.getHardware().getProcessor().getPhysicalProcessorCount()<2){
            cpuPhisissCores1.setTextFill(Color.RED);
            allContPass+=1;
        }else
        if (systemInfo.getHardware().getProcessor().getPhysicalProcessorCount()==2){
            cpuPhisissCores1.setTextFill(Color.ORANGE);
            allContPass+=2;
        }else
        if (systemInfo.getHardware().getProcessor().getPhysicalProcessorCount()>2){
            cpuPhisissCores1.setTextFill(Color.FORESTGREEN);
            allContPass+=3;
        }
        cpuPhisissCores1.setText(systemInfo.getHardware().getProcessor().getPhysicalProcessorCount()+"");

        all+=3;
        if (systemInfo.getHardware().getProcessor().getLogicalProcessorCount()<4){
            cpuLogicCores1.setTextFill(Color.RED);
            allContPass+=1;
        }else
        if (systemInfo.getHardware().getProcessor().getLogicalProcessorCount()==4){
            cpuLogicCores1.setTextFill(Color.ORANGE);
            allContPass+=2;
        }else
        if (systemInfo.getHardware().getProcessor().getLogicalProcessorCount()>4){
            cpuLogicCores1.setTextFill(Color.FORESTGREEN);
            allContPass+=3;
        }
        cpuLogicCores1.setText(systemInfo.getHardware().getProcessor().getLogicalProcessorCount()+"");

        all+=3;
        if (((double)systemInfo.getHardware().getProcessor().getMaxFreq()/(1000000000))<1.9){
            cpuClock1.setTextFill(Color.RED);
            allContPass+=1;
        }else
        if (((double)systemInfo.getHardware().getProcessor().getMaxFreq()/(1000000000))==1.9){
            cpuClock1.setTextFill(Color.ORANGE);
            allContPass+=2;
        }else
        if (((double)systemInfo.getHardware().getProcessor().getMaxFreq()/(1000000000))>1.9){
            cpuClock1.setTextFill(Color.FORESTGREEN);
            allContPass+=3;
        }
        cpuClock1.setText(((double)systemInfo.getHardware().getProcessor().getMaxFreq()/(1000000000))+"");

        cpuArch1.setText(systemInfo.getHardware().getProcessor().getProcessorIdentifier().isCpu64bit()+"");


        if (systemInfo.getHardware().getGraphicsCards().size()>=1){
            gpuName1.setText(systemInfo.getHardware().getGraphicsCards().get(0).getName());
            gpuId1.setText(systemInfo.getHardware().getGraphicsCards().get(0).getDeviceId());
            gpuMemory1.setText(systemInfo.getHardware().getGraphicsCards().get(0).getVRam()/(1024*1024) + "");
        }
        if (systemInfo.getHardware().getGraphicsCards().size()>=2){
            gpuName2.setText(systemInfo.getHardware().getGraphicsCards().get(1).getName());
            gpuId2.setText(systemInfo.getHardware().getGraphicsCards().get(1).getDeviceId());
            gpuMemory2.setText(systemInfo.getHardware().getGraphicsCards().get(1).getVRam()/(1024*1024) + "");
        }
        if (systemInfo.getHardware().getGraphicsCards().size()>=3){
            gpuName3.setText(systemInfo.getHardware().getGraphicsCards().get(1).getName());
            gpuId3.setText(systemInfo.getHardware().getGraphicsCards().get(1).getDeviceId());
            gpuMemory3.setText(systemInfo.getHardware().getGraphicsCards().get(1).getVRam()/(1024*1024) + "");
        }
        if (systemInfo.getHardware().getMemory().getPhysicalMemory().size()>=1){
            ramSlot1.setText(systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getBankLabel());
            ramType1.setText(systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getMemoryType());
            ramClock1.setText((double)systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getClockSpeed()/(1000000000) + "");
            ramMemory1.setText(systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getCapacity()/(1024*1024) + "");
        }
        if (systemInfo.getHardware().getMemory().getPhysicalMemory().size()>=2){
            ramSlot2.setText(systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getBankLabel());
            ramType2.setText(systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getMemoryType());
            ramClock2.setText((double)systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getClockSpeed()/(1000000000) + "");
            ramMemory2.setText(systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getCapacity()/(1024*1024) + "");
        }
        if (systemInfo.getHardware().getMemory().getPhysicalMemory().size()>=3){
            ramSlot3.setText(systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getBankLabel());
            ramType3.setText(systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getMemoryType());
            ramClock3.setText((double)systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getClockSpeed()/(1000000000) + "");
            ramMemory3.setText(systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getCapacity()/(1024*1024) + "");
        }
        if (systemInfo.getHardware().getMemory().getPhysicalMemory().size()>=4){
            ramSlot4.setText(systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getBankLabel());
            ramType4.setText(systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getMemoryType());
            ramClock4.setText((double)systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getClockSpeed()/(1000000000) + "");
            ramMemory4.setText(systemInfo.getHardware().getMemory().getPhysicalMemory().get(0).getCapacity()/(1024*1024) + "");
        }
        System.out.println((double) allContPass/all);

        progressBar.setProgress((double) allContPass/all);

    }
    @FXML
    private void close(){
        ((Stage)tabPane.getScene().getWindow()).close();
    }
}
