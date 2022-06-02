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

        File file = new File("image.jpg");
        Image image = new Image(file.toURI().toURL().toString(),false);

        GameObjectImage player = new GameObjectImage(new Vector(0,0), 50, 100,image);
        GameObjectRec ground = new GameObjectRec(new Vector(0,400), 1000, 1000);
        GameObjectRec frictionLayer = new GameObjectRec(new Vector(0,400-3), 1000, 1000);
        list.add(player);
        list.add(ground);
        list.add(frictionLayer);
        ground.getRectangle().setFill(Color.BLUE);
        frictionLayer.getRectangle().setFill(Color.GREEN);
        Vector gravity = new Vector(0,2000);
        player.getForceList().add(gravity);
        Drag d = new Drag(player,0.001);
        player.getForceList().add(d);
        Vector normalForce = new Vector();
        player.getForceList().add(normalForce);
        Vector friction = new Vector();
        player.getForceList().add(friction);


        root.getChildren().addAll(frictionLayer.getRectangle(),player.getImage(),ground.getRectangle());
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
                    if(player.isCollide(ground.getRectangle())){
                        player.setVelocity(new Vector(player.getVelocity().getX(),0));
                        player.setPosition(new Vector(player.getPosition().getX(),ground.getPosition().getY()-player.getImage().getFitHeight()-2));
                    }
                    if(player.isCollide(frictionLayer.getRectangle())){
                        normalForce.set(new Vector(0,-gravity.getY()));
                        if(player.getVelocity().getX()>0.1){
                            friction.set(new Vector(-800,0));
                        }else if(player.getVelocity().getX()<-0.1){
                            friction.set(new Vector(800,0));
                        }else {
                            friction.set(new Vector(0,0));
                        }
                    }else{
                        normalForce.set(new Vector());
                        friction.set(new Vector());
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
            if(e.getCode().equals(KeyCode.W)&&player.isCollide(frictionLayer.getRectangle())){
                player.addAppliedForce(new Vector(0,-7500),150);
                normalForce.set(new Vector());

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
