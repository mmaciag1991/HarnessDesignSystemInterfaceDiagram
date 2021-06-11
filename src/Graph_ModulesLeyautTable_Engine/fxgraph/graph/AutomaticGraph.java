package Graph_ModulesLeyautTable_Engine.fxgraph.graph;

import Components.taskbar.TaskbarProgressbar;
import DSI_Graph_Main_Controlers.AutomaticGraphSettingsControler;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

import static DSI_Graph_Main_Controlers.DSI_Model.proccesRunning;
import static DSI_Graph_Main_Controlers.DSI_Model.taskbarProgressbar;
import static Graph_ModulesLeyautTable_Engine.fxgraph.graph.FxMath.attractiveForce;



public class AutomaticGraph {

    private final MouseGestures mouseGestures;

    Label labelFps;
    Label edgesLbl;
    ProgressBar edgesPbr;
    int edgesShortCount = 0;
    private boolean automaticIsRunning = false;

    /*
       * AUTOMATIC LAYOUT
       *     private void computeForces() {
          for (Cell v : cells) {
              for (Cell other : cells) {
                  if (v == other) {
                      continue; //NOP
                  }

                  //double k = Math.sqrt(getWidth() * getHeight() / graphVertexMap.size());
                  Point2D repellingForce = repellingForce(v.getUpdatedPosition(), other.getUpdatedPosition(), this.repulsionForce);

                  double deltaForceX = 0, deltaForceY = 0;

                  //compute attractive and reppeling forces
                  //opt to use internal areAdjacent check, because a vertex can be removed from
                  //the underlying graph before we have the chance to remove it from our
                  //internal data structure
                  for (Edge edge : edges) {
                      Cell v2 = (Cell) edge.getSource();
                      Cell other2 = (Cell) edge.getTarget();
                      if (v.equals(v2) || other.equals(other2)) {

                          Point2D attractiveForce = attractiveForce(v.getUpdatedPosition(), other.getUpdatedPosition(),
                                  cells.size(), this.attractionForce, this.attractionScale);

                          deltaForceX = attractiveForce.getX() + repellingForce.getX();
                          deltaForceY = attractiveForce.getY() + repellingForce.getY();
                      } else {
                          deltaForceX = repellingForce.getX();
                          deltaForceY = repellingForce.getY();
                      }

                      v.addForceVector(deltaForceX, deltaForceY);
                  }
              }
          }
      }
      *
      *  private void computeForces() {
          for (Cell v : cells) {
              for (Cell other : cells) {
                  if (v == other) {
                      continue; //NOP
                  }

                  //double k = Math.sqrt(getWidth() * getHeight() / graphVertexMap.size());
                  Point2D repellingForce = repellingForce(v.getUpdatedPosition(), other.getUpdatedPosition(), this.repulsionForce);

                  double deltaForceX = 0, deltaForceY = 0;

                  //compute attractive and reppeling forces
                  //opt to use internal areAdjacent check, because a vertex can be removed from
                  //the underlying graph before we have the chance to remove it from our
                  //internal data structure
  //                if (areAdjacent(v, other)) {

                      Point2D attractiveForce = attractiveForce(v.getUpdatedPosition(), other.getUpdatedPosition(),
                              cells.size(), this.attractionForce, this.attractionScale);

                      deltaForceX = attractiveForce.getX() + repellingForce.getX();
                      deltaForceY = attractiveForce.getY() + repellingForce.getY();
  //                } else {
  //                   deltaForceX = repellingForce.getX();
  //                    deltaForceY = repellingForce.getY();
  //                }

                  v.addForceVector(deltaForceX, deltaForceY);
              }
          }
      }
       */
    // boolean trg = true;
    Point2D repellingForceSource, attractiveForceSource, repellingForceTarget, attractiveForceTarget;

    double deltaForceXSource;
    double deltaForceYSource;
    double deltaForceXTarget;
    double deltaForceYTarget;

    Cell source = null;
    Cell target = null;


    //private  double repulsionForce;
    private  double attractionForce;
    //private  double attractionScale;


    //    final AnimationTimer timer;
    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0 ;
    private boolean arrayFilled = false ;
    double frameRatePropherty = 0;



    public SimpleBooleanProperty automaticLayoutProperty = new SimpleBooleanProperty(false);
    //public IntegerProperty fpsLimitProperty = new SimpleIntegerProperty();


    AutomaticGraph(MouseGestures mouseGestures){
        this.mouseGestures = mouseGestures;


        //this.repulsionForce  = 0;//Si³a odpychania
        this.attractionForce = 10;//Si³a przyci±gania
        //this.attractionScale = 1500;//Skala przyci±gania
        ////System.out.println(repulsionForce);

        //automatic layout initializations
//        timer = new AnimationTimer() {
//            private long lastUpdate = 0 ;
//            @Override
//            public void handle(long now) {
//
//
//                long oldFrameTime = frameTimes[frameTimeIndex] ;
//                frameTimes[frameTimeIndex] = now ;
//                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length ;
//                if (frameTimeIndex == 0) {
//                    arrayFilled = true ;
//                }
//                if (arrayFilled) {
//                    long elapsedNanos = now - oldFrameTime ;
//                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length ;
//                    frameRate = 1_000_000_000.0 / elapsedNanosPerFrame ;
//                    labelFps.setText(String.format("FPS: %.1f", frameRate));
//                    ////System.out.println(fpsLimitProperty.get());
//                    if (frameRate>fpsLimitProperty.get()||fpsLimitProperty.get()==-1){
//                    Platform.runLater(() ->  runLayoutIteration());;
//                    }
//                }
//
//
//            }
//
//        };


        AnimationTimer frameRateMeter = new AnimationTimer() {

            @Override
            public void handle(long now) {
                long oldFrameTime = frameTimes[frameTimeIndex] ;
                frameTimes[frameTimeIndex] = now ;
                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length ;
                if (frameTimeIndex == 0) {
                    arrayFilled = true ;
                }
                if (arrayFilled) {
                    long elapsedNanos = now - oldFrameTime ;
                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length ;
                    double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame ;
                    frameRatePropherty = frameRate;
                    Platform.runLater(() -> labelFps.setText(String.format("FPS: %.2f", frameRate)));
                }

            }
        };

        frameRateMeter.start();



        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis((mouseGestures.graph.getModel().addedEdges.size())), e -> {

                    if (frameRatePropherty>AutomaticGraphSettingsControler.fpsLimitPropherty.get()) {
                        runLayoutIteration();
                        edgesLbl.setText(edgesShortCount + "/" + mouseGestures.graph.getModel().allEdges.size());
                        edgesPbr.setProgress(1.0 - ((double) edgesShortCount / mouseGestures.graph.getModel().allEdges.size()));
                        //labelFps.setText("FPS:"+ FXUtils.getAverageFPS());
                    }
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);


        automaticLayoutProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                //timer.start();
                timeline.play();
                taskbarProgressbar.showCustomProgress(0, TaskbarProgressbar.Type.INDETERMINATE);
                proccesRunning = true;
                automaticIsRunning = true;
            } else {
                //timer.stop();
                timeline.stop();
                taskbarProgressbar.showCustomProgress(0, TaskbarProgressbar.Type.NO_PROGRESS);
                proccesRunning = false;
                automaticIsRunning = false;
            }
        });




    }

    /********************************************************  AUTOMATIC GRAPH **************************************************************************************/

    public double getTimerFPS() {
        return frameRatePropherty;
    }





    private /*synchronized*/ void runLayoutIteration() {

        edgesShortCount = 0;
        resetForces();

        /*for (Cell cell :  mouseGestures.graph.getModel().allCells){
            computeForces(cell.getEdges());
        }*/


            computeForces();



        updateForcesAndapplyForces();


    }

    @Deprecated
    private void updateForces() {
        mouseGestures.graph.getModel().allCells.forEach(Cell::updateDelta);
    }
    @Deprecated
    private void applyForces() {

        mouseGestures.graph.getModel().allCells.forEach(Cell::moveFromForces);
    }

    private void updateForcesAndapplyForces(){

        for (Cell cell :  mouseGestures.graph.getModel().allCells){
            cell.updateDelta();
            cell.moveFromForces();
        }

    }

    private void resetForces() {
        mouseGestures.graph.getModel().allCells.forEach(Cell::resetForces);
    }


    private /*synchronized*/ void computeForces(/*LinkedList<Edge> edges*/) {
        //this.mouseGestures.graph.getModel().getAllEdges().forEach(edge -> {
        for ( Edge edge : this.mouseGestures.graph.getModel().getAllEdges()) {

            {
            mouseGestures.calcutateThisEdge(edge);

//            if (source == target) {
//                continue; //NOP
//            }else
            if (edge.toShort) {

                source = edge.getSource();
                target = edge.getTarget();
                edgesShortCount++;


                //System.out.println("ComuteForces");
                this.attractionForce = edge.getDistance() * 5;

                if (!source.isDocked() && !source.isSelected()) {
                    //  repellingForceSource = repellingForce(source.getUpdatedPosition(), target.getUpdatedPosition(), this.repulsionForce+AutomaticGraphSettingsControler.sourceRepulsionForceSpProperty.get());

                    /** -> **/
                    attractiveForceSource = attractiveForce(source.getUpdatedPosition(),
                            target.getUpdatedPosition(),
                            mouseGestures.graph.getModel().allCells.size(),
                            this.attractionForce + AutomaticGraphSettingsControler.sourceAttractionForceSpProperty.get(),
                            AutomaticGraphSettingsControler.sourceAttractionScaleSpProperty.get()/*edge.lenght/*this.attractionScale*/,
                            edge.getDistance());
                    deltaForceXSource = attractiveForceSource.getX()/* + repellingForceSource.getX()*/;
                    deltaForceYSource = attractiveForceSource.getY()/* + repellingForceSource.getY()*/;
                    source.addForceVector(deltaForceXSource, deltaForceYSource);
                }


                if (!target.isDocked() && !target.isSelected()) {

                    //  repellingForceTarget = repellingForce(target.getUpdatedPosition(), source.getUpdatedPosition(), this.repulsionForce+AutomaticGraphSettingsControler.targetRepulsionForceSpProperty.get());

                    /** <- **/
                    attractiveForceTarget = attractiveForce(target.getUpdatedPosition(),
                            source.getUpdatedPosition(),
                            mouseGestures.graph.getModel().allCells.size(),
                            this.attractionForce + AutomaticGraphSettingsControler.targetAttractionForceSpProperty.get(),
                            AutomaticGraphSettingsControler.targetAttractionScaleSpProperty.get()/*edge.lenght/*this.attractionScale*/,
                            edge.getDistance());
                    deltaForceXTarget = attractiveForceTarget.getX()/* + repellingForceTarget.getX()*/;
                    deltaForceYTarget = attractiveForceTarget.getY()/* + repellingForceTarget.getY()*/;
                    target.addForceVector(deltaForceXTarget, deltaForceYTarget);
                }

            }

        }
        }
        //});

    }



    public void setFpsLabel(Label fps) {
        labelFps = fps;
    }

    public void setEdgesLbl(Label edgesLbl) {
        this.edgesLbl = edgesLbl;
    }

    public void setEdgesPbr(ProgressBar edgesPbr) {
        this.edgesPbr = edgesPbr;
    }

    public boolean isAutomaticIsRunning() {
        return automaticIsRunning;
    }
}
