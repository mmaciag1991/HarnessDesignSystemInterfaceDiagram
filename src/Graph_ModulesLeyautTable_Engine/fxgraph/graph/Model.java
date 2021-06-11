package Graph_ModulesLeyautTable_Engine.fxgraph.graph;

import Model.Connectors;
import Graph_ModulesLeyautTable_Engine.fxgraph.cellsTypes.*;
import Graph_ModulesLeyautTable_Engine.fxgraph.model.Wires;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    Cell graphParent;
    //Module graphParent2;

    private ObservableList<Cell> connectorsData = FXCollections.observableArrayList();
    private ObservableList<Wires> wiresData = FXCollections.observableArrayList();
    private ObservableList<Cell> componentsData = FXCollections.observableArrayList();
    private ObservableList<Cell> PasivesData = FXCollections.observableArrayList();
    private final ObservableList<Module> modulesData = FXCollections.observableArrayList();

    List<Cell> allCells;
    List<Cell> addedCells;
    List<Cell> removedCells;

//    List<Module> allModules;
//    List<Module> addedModules;
//    List<Module> removedModules;

    List<Edge> allEdges;
    List<Edge> addedEdges;
    List<Edge> removedEdges;

    CellLayer cellLayer;

    public Map<String,Cell> cellMap; // <id,cell>
//    public Map<String,Module> moduleMap; // <id,module,SystemPintableId>
    public TableView<Connectors> connectorsTableView;
    double scaledistance = 4;

    private Point2D minusPionts = new Point2D(0,0);

    public void setCellLayer(CellLayer cellLayer) {
        this.cellLayer = cellLayer;
    }

    public Model() {

        graphParent = new Cell( "_ROOT_","_ROOT_");

        //graphParent2 = new Module( "_ROOT_");

        // clear model, create lists
        clear();
    }

    public ObservableList<Module> getModulesData() {
        return modulesData;
    }
    public ObservableList<Cell> getConnectorsData() { return connectorsData; }
    public ObservableList<Cell> getComponentsData() {
        return componentsData;
    }
    public ObservableList<Wires> getWiresData() {
        return wiresData;
    }

    public void setComponentsData(ObservableList<Cell> componentsData) {
        this.componentsData = componentsData;
    }
    public void addToComponentsData(Cell components){
        this.componentsData.add(components);
    }

    public void setWiresData(ObservableList<Wires> wiresData) {
        this.wiresData = wiresData;
    }
    public void clearWiresData(){
        this.wiresData.clear();
    }
    public void addToWiresData(Wires wires){
        this.wiresData.add(wires);
    }
    public void removeFromWiresData(Wires wires){
        this.wiresData.remove(wires);
    }

    public Point2D getMinusPionts() { return minusPionts; }
    public void setMinusPionts(Point2D minusPionts) {
        this.minusPionts = minusPionts;
    }

    public void setConnectorsTableView(TableView<Connectors> connectorsTableView){ this.connectorsTableView = connectorsTableView; }

    public double getScaledistance() {
        return scaledistance;
    }

    public ObservableList<Cell> getPasivesData() {
        return PasivesData;
    }

    public void clear() {

        allCells = new ArrayList<>();
        addedCells = new ArrayList<>();
        removedCells = new ArrayList<>();

//        allModules = new ArrayList<>();
//        addedModules = new ArrayList<>();
//        removedModules = new ArrayList<>();

        allEdges = new ArrayList<>();
        addedEdges = new ArrayList<>();
        removedEdges = new ArrayList<>();

        cellMap = new HashMap<>(); // <id,cell>

//        moduleMap = new HashMap<>(); // <id,module>

    }

    public void clearAddedLists() {
        addedCells.clear();
//        addedModules.clear();
        addedEdges.clear();
    }

//    public Map<String, Module> getModuleMap() {
//        return moduleMap;
//    }

    public List<Cell> getAddedCells() {
        return addedCells;
    }

    public List<Cell> getRemovedCells() {
        return removedCells;
    }

    public List<Cell> getAllCells() {
        return allCells;
    }


//    public List<Module> getAllModules() {
//        return allModules;
//    }

//    public List<Module> getAddedModules() {
//        return addedModules;
//    }

//    public List<Module> getRemovedModules() {
//        return removedModules;
//    }

    public List<Edge> getAddedEdges() {
        return addedEdges;
    }

    public List<Edge> getRemovedEdges() {
        return removedEdges;
    }

    public List<Edge> getAllEdges() {
        return allEdges;
    }

    public Cell addCell(String id, String name, String typeConnector, CellType type, double x, double y, String Xcode, String CustomerCode, String GIMCode, String Pins, List<String> asemblyVariants) {

        Cell cell;

        switch (type) {
            case RECTANGLE:
                RectangleCell rectangleCell = new RectangleCell(id);
                cell = rectangleCell;
                addCell(rectangleCell);
                break;

            case TRIANGLE:
                TriangleCell circleCell = new TriangleCell(id, x*scaledistance, y*scaledistance);
                cell = circleCell;
                addCell(circleCell);
                break;

            case LABEL:
                LabelCell labelCell = new LabelCell(id);
                cell = labelCell;
                addCell(labelCell);
                break;

            case NODE:
                NodeCell imageCell = new NodeCell(id, ( 10 + x + ( minusPionts.getX() * - 1 ) ) * scaledistance, ( 10 + y + ( minusPionts.getY() * - 1) ) * scaledistance);
                cell = imageCell;
                addCell(imageCell);
                break;

            case BUTTON:
                ButtonCell buttonCell = new ButtonCell(id);
                cell = buttonCell;
                addCell(buttonCell);
                break;

            case CONNECTOR:
                ConnectorCell connectorCell = new ConnectorCell(id, 10 + (x + ( minusPionts.getX() * - 1 ) ) * scaledistance, ( 10 + y + ( minusPionts.getY() * - 1) ) * scaledistance, Xcode, CustomerCode, GIMCode, Pins/*,connectorsTableView*/);
                cell = connectorCell;
                addCell(connectorCell);
                break;

            case CONNECTORSMALL:
                ConnectorSmallCell connectorSmallCell = new ConnectorSmallCell(id, name, typeConnector, ( 10 + x + ( minusPionts.getX() * - 1 ) ) * scaledistance, ( 10 + y + ( minusPionts.getY() * - 1) ) * scaledistance, Xcode, CustomerCode, GIMCode, Pins, asemblyVariants);
                cell = connectorSmallCell;
                addCell(connectorSmallCell);//System.out.println(( 10 + x + ( minusPionts.getX() * - 1 ) ) * scaledistance + " - " + ( 10 + y + ( minusPionts.getY() * - 1) ) * scaledistance);
                break;

            case SPLICECELL:
                SpliceCell spliceCell = new SpliceCell(id, name, typeConnector,( 10 + x + ( minusPionts.getX() * - 1 ) ) * scaledistance, ( 10 + y + ( minusPionts.getY() * - 1) ) * scaledistance, Xcode, CustomerCode, GIMCode, Pins, asemblyVariants);
                cell = spliceCell;
                addCell(spliceCell);
                break;

            case COMPONENT:
                ComponentCell componentCell = new ComponentCell(id, name, typeConnector,( 10 + x + ( minusPionts.getX() * - 1 ) ) * scaledistance, ( 10 + y + ( minusPionts.getY() * - 1) ) * scaledistance, Xcode, CustomerCode, GIMCode, Pins, asemblyVariants);
                cell = componentCell;
                addCell(componentCell);
                break;

            case ANOTHERCELL:
                AnotherCell anotherCell = new AnotherCell(id, name, typeConnector,( 10 + x + ( minusPionts.getX() * - 1 ) ) * scaledistance, ( 10 + y + ( minusPionts.getY() * - 1) ) * scaledistance, Xcode, CustomerCode, GIMCode, Pins, asemblyVariants);
                cell = anotherCell;
                addCell(anotherCell);
                break;

            default:
                cell = null;
                throw new UnsupportedOperationException("Unsupported Cell type: " + type);
        }

        return cell;
    }
    public  void addModule(String Xcode, String systemPintableId, String id, CellType type, double width, double height, double xCoord, double yCoord, String XCOORD, String YCOORD, /*TreeTableView<SystemPintable> modulesTable,*/ String orderStatus, String moduleMounted, /*TreeItem<SystemPintable> itemOrder,*/int iSP) {

        switch (type) {


            case MODULE:
                ModuleCell moduleCell = new ModuleCell(Xcode, systemPintableId, id, width, height, xCoord, yCoord, XCOORD, YCOORD, /*modulesTable,*/orderStatus,moduleMounted/*,itemOrder*/, iSP);

                addModule(moduleCell);
                break;


            default:
                throw new UnsupportedOperationException("Unsupported type: " + type);
        }
    }

    private void addCell( Cell cell) {

        addedCells.add(cell);
        cellMap.put( cell.getCellId(), cell);
        Platform.runLater(() -> cellLayer.getChildren().add(cell));
        switch (cell.getCellType()) {

            case "ConnectorSmallCell":
                connectorsData.add(cell);
                break;

            case "SpliceCell":
                connectorsData.add(cell);
                break;

            case "AnotherCell":
                componentsData.add(cell);
                break;

            case "ComponentCell":
                componentsData.add(cell);
                break;

            default:
                break;
               // throw new UnsupportedOperationException("Unsupported Cell type: " + cell.getCellType());

        }

    }

    private  void addModule( Module module) {

//        addedModules.add(module);
        modulesData.add(module);
//        moduleMap.put( module.getModuleId(), module);

    }



    public  void addEdge( String sourceId, String targetId, double lenght, double strokeWidth, Color fill) {

        Cell sourceCell = cellMap.get( sourceId);
        Cell targetCell = cellMap.get( targetId);
        Edge edge = new Edge( sourceCell, targetCell, lenght, strokeWidth, fill);
        addedEdges.add( edge);
        Platform.runLater(() -> cellLayer.getChildren().add(edge));

    }

    /**
     * Attach all cells which don't have a parent to graphParent
     * @param cellList
     */
    public  void attachOrphansToGraphParent( List<Cell> cellList) {

        for( Cell cell: cellList) {
            if( cell.getCellParents().size() == 0) {
                graphParent.addCellChild(cell);
            }
        }

    }



//    public  void attachModulesOrphansToGraphParent( List<Module> moduleList) {
//
//        for( Module module: moduleList) {
//            if( module.getModuleParents().size() == 0) {
//                graphParent2.addModuleChild( module);
//            }
//        }
//
//    }

    /**
     * Remove the graphParent reference if it is set
     * @param cellList
     */
    public  void disconnectFromGraphParent( List<Cell> cellList) {

        for( Cell cell: cellList) {
            graphParent.removeCellChild( cell);

        }
    }
//    public  void disconnectModulesFromGraphParent( List<Module> moduleList) {
//
//        for( Module module: moduleList) {
//            graphParent2.removeModuleChild( module);
//
//        }
//    }


    public void merge() {

        // cells
        allCells.addAll( addedCells);
        allCells.removeAll( removedCells);
//        for (Cell cell : addedCells){
//            //System.out.println("-"+cell.cellId);
//        }

        addedCells.clear();
        removedCells.clear();
        // modules
//        allModules.addAll( allModules);
//        allModules.removeAll( removedModules);

//        addedModules.clear();
//        removedModules.clear();
        // edges
        allEdges.addAll( addedEdges);
        allEdges.removeAll( removedEdges);

        addedEdges.clear();
        removedEdges.clear();
        cellMap.clear();
//
//        allCells.clear();
//        allEdges.clear();
//        allModules.clear();
//        moduleMap.clear();
//        cellMap.clear();


        //System.out.println("Complided ()-> Model Class");

    }
}