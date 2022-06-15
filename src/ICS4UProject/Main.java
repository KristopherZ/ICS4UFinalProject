package ICS4UProject;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class Main extends Application{

    private long lastUpdatedTime = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        File file = new File("image.jpg");
        Image image = new Image(file.toURI().toURL().toString(), false);
        Platform pt1 = new Platform(0,0,100,500);
        Platform pt2 = new Platform(900,0,100,500);
        Platform pt3 = new Platform(100,400,800,100);
        Platform pt4 = new Platform(700,300,400,400);

        InteractiveObjectRec rec1 = new InteractiveObjectRec(300,0,50,50);
        InteractiveObjectRec rec2 = new InteractiveObjectRec(500,0,50,50);
        rec1.getRectangle().setFill(Color.RED);
        rec2.getRectangle().setFill(Color.CYAN);
        pt1.addKinetic(rec1);
        pt1.addKinetic(rec2);
        pt2.addKinetic(rec1);
        pt2.addKinetic(rec2);
        pt3.addKinetic(rec1);
        pt3.addKinetic(rec2);
        pt4.addKinetic(rec1);
        pt4.addKinetic(rec2);
        rec1.addKinetic(rec2);
        rec2.addKinetic(rec1);

//        enemy.getPlatformList().add(pt1);
//        enemy.getPlatformList().add(pt2);
//        enemy.getPlatformList().add(pt3);
//        enemy.getPlatformList().add(pt4);
        rec1.setElasticity(new double[]{.3,.3,.3,.3});
        rec2.setElasticity(new double[]{.3,.3,.3,.3});
        pt1.setElasticity(new double[]{.5,.5,.5,.5});
        pt2.setElasticity(new double[]{.5,.5,.5,.5});
        pt3.setElasticity(new double[]{.5,.5,.5,.5});
        pt4.setElasticity(new double[]{.5,.5,.5,.5});
        pt1.setFrictionCoe(0.1);
        pt2.setFrictionCoe(0.1);
        pt3.setFrictionCoe(0.1);
        pt4.setFrictionCoe(0.1);
        rec1.setGravity(new Vector(0,2000));
        rec2.setGravity(new Vector(0,2000));
        rec1.setVelocity(new Vector(400,0));
        rec2.setVelocity(new Vector(-400,0));
        rec1.setDragCoe(0.001);
        rec2.setDragCoe(0.001);
        Group root = new Group(rec1.getRectangle(),rec2.getRectangle(),pt1.getRectangle(),pt2.getRectangle(),pt3.getRectangle(),pt4.getRectangle());
        primaryStage.setWidth(1000);
        primaryStage.setHeight(500);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        KeyInput k =new KeyInput(scene);
        AnimationTimer t = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if(lastUpdatedTime > 0){
                    if(k.iswPressed()){
                        rec1.setAppliedForce(new Vector(0,-3000));
                    }else if(k.isdPressed()){
                        rec1.setAppliedForce(new Vector(3000,0));
                    }else if(k.isaPressed()){
                        rec1.setAppliedForce(new Vector(-3000,0));
                    }else{
                        rec1.setAppliedForce(new Vector(0,0));
                    }
                    long elapsedTime = timestamp - lastUpdatedTime;
                    rec1.update(elapsedTime);
                    rec2.update(elapsedTime);
                    pt1.update(elapsedTime);
                    pt2.update(elapsedTime);
                    pt3.update(elapsedTime);
                    pt4.update(elapsedTime);
                    System.out.println(k.iswPressed());
                }
                lastUpdatedTime = timestamp;
            }
        };
        t.start();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}