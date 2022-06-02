package ICS4UProject;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application{

    ArrayList<PhysicsUpdate> list = new ArrayList<PhysicsUpdate>();
    boolean DPressed, APressed, WPressed;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setResizable(false);
        primaryStage.setTitle("JavaFX");
//        primaryStage.setFullScreen(true);

        Group root = new Group();
        Scene scene = new Scene(root);

        GameObject player = new GameObject(new Vector(0,0), 50, 50);
        GameObject ground = new GameObject(new Vector(0,400), 1000, 1000);
        GameObject frictionLayer = new GameObject(new Vector(0,400-3), 1000, 1000);
        list.add(player);
        list.add(ground);
        list.add(frictionLayer);
        player.setFill(Color.RED);
        ground.setFill(Color.BLUE);
        frictionLayer.setFill(Color.GREEN);
        player.setGravity(2000);
        player.setDrag(0.001);
//        player.addAppliedForce(new Vector(0,-20),150);

        root.getChildren().addAll(frictionLayer,player,ground);
        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);

        final long[] lastUpdatedTime = {0};

        AnimationTimer t = new AnimationTimer() {
            @Override
            public void handle(long timestam) {
                if(lastUpdatedTime[0]>0){
                    long elapsedTime = timestam - lastUpdatedTime[0];
                    for(int i=0;i<list.size();i++){
                        list.get(i).update(elapsedTime);
                    }
                    if(DPressed)
                        player.setAppliedForce(new Vector(2000,player.getAppliedForce().getY()));
                    else if(APressed)
                        player.setAppliedForce(new Vector(-2000,player.getAppliedForce().getY()));
                    else{
                        player.setAppliedForce(new Vector(0, player.getAppliedForce().getY()));
                    }
                    if(player.isCollide(ground)){
                        player.setVelocity(new Vector(player.getVelocity().getX(),0));
                        player.setPosition(new Vector(player.getPosition().getX(),ground.getPosition().getY()-player.getHeight()-2));
                    }
                    if(player.isCollide(frictionLayer)){
                        player.setNormalForce(new Vector(0,-player.getG().getY()));
                        if(player.getVelocity().getX()>0.1){
                            player.setFriction(new Vector(-800,0));
                        }else if(player.getVelocity().getX()<-0.1){
                            player.setFriction(new Vector(800,0));
                        }else {
                            player.setFriction(new Vector(0,0));
                        }
                    }else{
                        player.setNormalForce(new Vector());
                        player.setFriction(new Vector());
                    }
                }
                lastUpdatedTime[0] = timestam;
            }
        };

        t.start();

        scene.setOnKeyPressed(e ->{
            if(e.getCode().equals(KeyCode.D)){
                DPressed = true;
            }
            if(e.getCode().equals(KeyCode.A)){
                APressed = true;
            }
            if(e.getCode().equals(KeyCode.W)&&player.isCollide(frictionLayer)){
                player.addAppliedForce(new Vector(0,-7500),150);
                player.setNormalForce(new Vector());

            }
        });
        scene.setOnKeyReleased(e ->{
            if(e.getCode().equals(KeyCode.D)){
                DPressed = false;
            }
            if(e.getCode().equals(KeyCode.A)){
                APressed = false;
            }

        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
