/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WiresAnimation;


import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;


public class WiresAnimation {
    Group paths;
    Pane scene;

    DropShadow glow;
    DropShadow shadow;
    BoxBlur boxBlur;
    SVGPath path,path2,path3,path4,path5;   
    Circle particle1,particle2,particle3,particle4,particle5,neonLoad;
    
    ArrayList<Animation> animations = new ArrayList<Animation>();
    
    
    public Double getRandom(){
        double rand = Math.random()*15;
        return rand>5?rand:getRandom();
    }
    

    public void animateParticle(Node particle,Shape path){       
            

        PathTransition transition = new PathTransition();
        transition.setPath(path);
        transition.setNode(particle);
        
        transition.setDuration(Duration.seconds(getRandom()));        
        transition.setCycleCount(-1);
        animations.add(transition); 
        transition.play();
        
     
        
        
    }
    

    public void animate(){
        Timeline mainAnimation = new Timeline();
        mainAnimation.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(0),new KeyValue(glow.radiusProperty(),1d)),
                new KeyFrame(Duration.millis(5000),new KeyValue(glow.radiusProperty(),13d)),
                new KeyFrame(Duration.millis(10000),new KeyValue(glow.radiusProperty(),0d)),
                
                
                new KeyFrame(Duration.millis(1000),new KeyValue(shadow.radiusProperty(),5d)),                
                new KeyFrame(Duration.millis(1000),new KeyValue(boxBlur.iterationsProperty(),10d)),
                
                new KeyFrame(Duration.millis(0),new KeyValue(paths.opacityProperty(),0.6d)),
                new KeyFrame(Duration.millis(5000),new KeyValue(paths.opacityProperty(),1d)),
                new KeyFrame(Duration.millis(10000),new KeyValue(paths.opacityProperty(),0.6d))
               
        );
        
        mainAnimation.setCycleCount(-1);
        animations.add(mainAnimation); 
        
        mainAnimation.play();
        
        
        animateParticle(particle1, path);
        animateParticle(particle2, path2);
        animateParticle(particle3, path3);
        animateParticle(particle4, path4);
        animateParticle(particle5, path5);
        
        
        
    }
    

    public void makePaths() throws IOException{
        PathLoader loader = new PathLoader();
         path = new SVGPath();
         path2 = new SVGPath();
         path3 = new SVGPath();
         path4 = new SVGPath();
         path5 = new SVGPath();
         
        path.setContent(loader.getPath(1)); 
        path2.setContent(loader.getPath(2)); 
        path3.setContent(loader.getPath(3)); 
        path4.setContent(loader.getPath(4)); 
        path5.setContent(loader.getPath(5)); 
        
        
        
        path.setStroke(Color.YELLOW);
        path2.setStroke(Color.RED);
        path3.setStroke(Color.ORANGE);
        path4.setStroke(Color.WHEAT);
        path5.setStroke(Color.AQUA);
        
        path.setFill(Color.TRANSPARENT);
        path2.setFill(Color.TRANSPARENT);        
        path3.setFill(Color.TRANSPARENT);
        path4.setFill(Color.TRANSPARENT);
        path5.setFill(Color.TRANSPARENT);
        
        path.setEffect(boxBlur);
        path2.setEffect(boxBlur);
        path3.setEffect(boxBlur);
        path4.setEffect(boxBlur);
        path5.setEffect(boxBlur);
    }
    

    public void makeParticle(){
        particle1 = new Circle(0,0,2,Color.YELLOW);
        particle2 = new Circle(0,0,2,Color.RED);
        particle3 = new Circle(0,0,2,Color.ORANGE);
        particle4 = new Circle(0,0,2,Color.WHEAT);
        particle5= new Circle(0,0,2,Color.AQUA);

        
        particle1.setEffect(glow);
        particle2.setEffect(glow);
        particle3.setEffect(glow);
        particle4.setEffect(glow);
        particle5.setEffect(glow);

        
    }
    

    public void initEffects(){
         boxBlur = new BoxBlur();
         boxBlur.setWidth(10);
         boxBlur.setHeight(3);
         boxBlur.setIterations(1);

         glow =new DropShadow();
         glow.setColor(Color.YELLOW);
         glow.setOffsetX(0);
         glow.setOffsetY(0);
         glow.setRadius(12);
         
         shadow = new DropShadow();
         shadow.setOffsetX(4);
         shadow.setOffsetY(4);
         shadow.setRadius(1);
         
    }

    public void buildComponents() throws IOException{
        initEffects();
        makePaths();
        makeParticle();
        
        paths = new Group(path,path2,path3,path4,path5);
        paths.setOpacity(0.6); 

        
        Group particles = new Group(particle1,particle2,particle3,particle4,particle5);

        Group g = new Group(paths,particles);

        scene = new Pane(g);
    }

    public void dispose(){
        for(Animation ani: animations){
            ani.stop();
        }
    }
    

    public Pane getScene(){
        return scene;
    }
    


    


    
}
