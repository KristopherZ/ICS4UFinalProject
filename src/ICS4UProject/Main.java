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

        Platform pt1 = new Platform(0,0,100,500);
        Platform pt2 = new Platform(900,0,100,500);
        Platform pt3 = new Platform(100,400,800,100);
        Platform pt4 = new Platform(700,300,400,400);
        File file = new File("image.jpg");
        Image image = new Image(file.toURI().toURL().toString(), false);
        BodyRec enemy = new BodyRec(500,0,50,50);
        enemy.getRectangle().setFill(Color.RED);
        pt1.addKinetic(enemy);
        pt2.addKinetic(enemy);
        pt3.addKinetic(enemy);
        pt4.addKinetic(enemy);
//        enemy.getPlatformList().add(pt1);
//        enemy.getPlatformList().add(pt2);
//        enemy.getPlatformList().add(pt3);
//        enemy.getPlatformList().add(pt4);
        enemy.setElasticity(new double[]{1,.2,.1,.1});
        enemy.setFrictionCoe(0.1);
        pt1.setFrictionCoe(0.1);
        pt2.setFrictionCoe(0.1);
        pt3.setFrictionCoe(0.1);
        pt4.setFrictionCoe(0.1);
        enemy.setGravity(new Vector(0,2000));
        enemy.setVelocity(new Vector(400,0));
        Group root = new Group(enemy.getRectangle(),pt1.getRectangle(),pt2.getRectangle(),pt3.getRectangle(),pt4.getRectangle());
        primaryStage.setWidth(1000);
        primaryStage.setHeight(500);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        KeyInput k =new KeyInput(scene);
        AnimationTimer t = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if(lastUpdatedTime > 0){
                    long elapsedTime = timestamp - lastUpdatedTime;
                    enemy.update(elapsedTime);
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