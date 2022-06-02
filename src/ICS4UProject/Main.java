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

    ArrayList<GameObject> list = new ArrayList<GameObject>();
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

        GameObjectImage player = new GameObjectImage(new Vector(100,0), 50, 100,image);
        GameObjectRec ground = new GameObjectRec(new Vector(100,300), 1000, 1000);
        BodyRec rec = new BodyRec(200,0,30,30);
        GameObjectRec frictionLayer = new GameObjectRec(new Vector(100,300-3), 1000, 1000);
        list.add(player);
        list.add(ground);
        list.add(frictionLayer);
        list.add(rec);
        ground.getRectangle().setFill(Color.BLUE);
        rec.getRectangle().setFill(Color.RED);
        frictionLayer.getRectangle().setFill(Color.GREEN);
        Vector gravity = new Vector(0,2000);
        player.getForceList().add(gravity);
        Drag d = new Drag(player,0.001);
        player.getForceList().add(d);
        Vector normalForce = new Vector();
        player.getForceList().add(normalForce);
        Vector friction = new Vector();
        player.getForceList().add(friction);

        rec.setGravity(new Vector(0,2000));
        rec.setDragCoe(0.001);


        root.getChildren().addAll(player.getImage(),ground.getRectangle(),rec.getRectangle());
        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);

        final long[] lastUpdatedTime = {0};

        AnimationTimer t = new AnimationTimer() {
            @Override
            public void handle(long timestam) {
                if(lastUpdatedTime[0]>0){
                    long elapsedTime = timestam - lastUpdatedTime[0];
                    for(GameObject i:list){
                        i.update(elapsedTime);
                    }
                    if(DPressed)
                        if(player.isCollide(frictionLayer.getRectangle())){
                            player.setAppliedForce(new Vector(4000,player.getAppliedForce().getY()));
                        }else{
                            player.setAppliedForce(new Vector(1200,player.getAppliedForce().getY()));
                        }
                    else if(APressed)
                        if(player.isCollide(frictionLayer.getRectangle())){
                            player.setAppliedForce(new Vector(-4000,player.getAppliedForce().getY()));
                        }else{
                            player.setAppliedForce(new Vector(-1200,player.getAppliedForce().getY()));
                        }
                    else{
                        player.setAppliedForce(new Vector(0, player.getAppliedForce().getY()));
                    }
                    if(player.isCollide(ground.getRectangle())){
                        player.setVelocity(new Vector(player.getVelocity().getX(),0));
                        player.setPosition(new Vector(player.getPosition().getX(),ground.getPosition().getY()-player.getImage().getFitHeight()-2));
                    }
                    if(rec.isCollide(ground.getRectangle())){
                        rec.setVelocity(new Vector(rec.getVelocity().getX(),0));
                        rec.setPosition(new Vector(rec.getPosition().getX(),ground.getPosition().getY()-rec.getRectangle().getHeight()-2));
                    }
                    if(player.isCollide(frictionLayer.getRectangle())){
                        normalForce.set(new Vector(0,-gravity.getY()));
                        if(player.getVelocity().getX()>0.1){
                            friction.set(new Vector(-2000,0));
                        }else if(player.getVelocity().getX()<-0.1){
                            friction.set(new Vector(2000,0));
                        }else {
                            friction.set(new Vector(0,0));
                        }
                    }else{
                        normalForce.set(new Vector());
                        friction.set(new Vector());
                    }
                    if(rec.isCollide(frictionLayer.getRectangle())){
                        rec.setNormalForce(new Vector(0,-gravity.getY()));
                        if(rec.getVelocity().getX()>0.1){
                            rec.setFriction(new Vector(-2000,0));
                        }else if(rec.getVelocity().getX()<-0.1){
                            rec.setFriction(new Vector(2000,0));
                        }else {
                            rec.setFriction(new Vector(0,0));
                        }
                    }else{
                        rec.setNormalForce(new Vector());
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
                player.addAppliedForce(new Vector(0,-5000),160);
                normalForce.set(new Vector());
            }
            if (e.getCode().equals(KeyCode.L)){
                for(GameObject i:list){
                    i.addCameraPosition(new Vector(10,0));
                }
            }
            if (e.getCode().equals(KeyCode.K)){
                for(GameObject i:list){
                    i.addCameraPosition(new Vector(-10,0));
                }
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
