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

    ArrayList<GameObject> list = new ArrayList<GameObject>();
    boolean DPressed, APressed, WPressed;
    Camera camera;


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setResizable(false);
        primaryStage.setTitle("JavaFX");
//        primaryStage.setFullScreen(true);

        Group root = new Group();
        Scene scene = new Scene(root);

        File file = new File("image.jpg");
        Image image = new Image(file.toURI().toURL().toString(),false);
        Vector AppliedForce2 = new Vector();

        BodyImage player = new BodyImage(new Vector(0,0), 50, 100,image);
        Platform ground = new Platform(0,350, 1000, 100);
        ground.addKinetic(player);
        BodyRec rec = new BodyRec(70,0,30,30);
        ground.addKinetic(rec);
        rec.setMass(.2);
        player.setMass(1);
        Vector appliedForce2 = new Vector();
        player.getForceList().add(appliedForce2);
//        GameObjectRec frictionLayer = new GameObjectRec(new Vector(0,350-3), 1000, 1000);
        list.add(player);
        list.add(ground);
//        list.add(frictionLayer);
        list.add(rec);
        camera = new Camera(list);
        ground.getRectangle().setFill(Color.BLUE);
        rec.getRectangle().setFill(Color.RED);
//        frictionLayer.getRectangle().setFill(Color.GREEN);
        player.setDragCoe(0.001);
        player.setGravity(new Vector(0,2000));

        (new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rec.setGravity(new Vector(0,2000*rec.getMass()));
        })).start();
        ground.getColliders()[0].setFill(Color.RED);
        ground.getColliders()[1].setFill(Color.RED);
        ground.getColliders()[2].setFill(Color.RED);
        ground.getColliders()[3].setFill(Color.RED);
        root.getChildren().addAll(player.getImage(),ground.getRectangle(),rec.getRectangle(),ground.getColliders()[0],ground.getColliders()[1], ground.getColliders()[2],ground.getColliders()[3]);
        primaryStage.setScene(scene);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(500);
        ground.setFrictionCoe(0.2);
        final long[] lastUpdatedTime = {0};

        AnimationTimer t = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if(lastUpdatedTime[0]>0) {
                    long elapsedTime = timestamp - lastUpdatedTime[0];
                    if (DPressed) {
                        appliedForce2.set(new Vector(1000, 0));
                    } else if (APressed) {
                        appliedForce2.set(new Vector(-1000, 0));
                    }else if(WPressed){
                        appliedForce2.set(new Vector(0, -3000));
                    }else{
                        appliedForce2.set(new Vector());
                    }
//                    if(DPressed) {
//                        if(player.isCollide(frictionLayer.getRectangle())){
//                            player.setAppliedForce(new Vector(4000,player.getAppliedForce().getY()));
//                        }else{
//                            player.setAppliedForce(new Vector(1200,player.getAppliedForce().getY()));
//                        }
//                    }
//                    else if(APressed) {
//                        if (player.isCollide(frictionLayer.getRectangle())) {
//                            player.setAppliedForce(new Vector(-4000, player.getAppliedForce().getY()));
//                        } else {
//                            player.setAppliedForce(new Vector(-1200, player.getAppliedForce().getY()));
//                        }
//                    }
//                    else{
//                        player.setAppliedForce(new Vector(0, player.getAppliedForce().getY()));
//                    }
//                    if(player.isCollide(ground.getRectangle())){
//                        player.setVelocity(new Vector(player.getVelocity().getX(),0));
//                        player.setPosition(new Vector(player.getPosition().getX(),ground.getPosition().getY()-player.getImage().getFitHeight()-2));
//                    }
//                    if(rec.isCollide(ground.getRectangle())){
//                        rec.setVelocity(new Vector(rec.getVelocity().getX(),0));
//                        rec.setPosition(new Vector(rec.getPosition().getX(),ground.getPosition().getY()-rec.getRectangle().getHeight()-2));
//                    }
//                    if(player.isCollide(frictionLayer.getRectangle())){
//                        normalForce.set(new Vector(0,-gravity.getY()));
//                        if(Math.abs(player.getVelocity().getX())<100){
//                            friction.set(new Vector(-player.getVelocity().getX()*20,0));
//                        }else{
//                            friction.set(new Vector(-player.getVelocity().getX()*2000/player.getVelocity().length(),0));
//                        }
//                    }else{
//                        normalForce.set(new Vector());
//                        friction.set(new Vector());
//                    }
//                    if(rec.isCollide(frictionLayer.getRectangle())){
//                        rec.setNormalForce(new Vector(0,-rec.getGravity().getY()));
//                        rec.setFriction(new Vector(-rec.getVelocity().getX()*1,0));

//                    }else{
//                        rec.setNormalForce(new Vector());
//                        rec.setFriction(new Vector());
//                    }
//                    if(player.isCollide(rec.getRectangle())){
//                        rec.setAppliedForce(new Vector(Math.pow(Math.abs(rec.getPosition().getX()-50-player.getPosition().getX()),3),0));
//                        AppliedForce2.set(new Vector(-Math.pow(Math.abs(rec.getPosition().getX()-50-player.getPosition().getX()),3),0));
//                    }else{
//                        rec.setAppliedForce(new Vector());
//                        AppliedForce2.set(new Vector());
//                    }

                    camera.setCameraPosition(new Vector(player.getPosition().getX()-100,0));
                    for(GameObject i:list){
                        i.update(elapsedTime);
                    }
                    System.out.println(player.getPosition());
//                    ground.collide();
//                    System.out.println(ground.collideWith(player).getCollisionPosition()[2]);
//                    System.out.println(Arrays.toString(ground.collideWith(player).getDepth()));
                }
//


                lastUpdatedTime[0] = timestamp;
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
            if(e.getCode().equals(KeyCode.W)&&ground.isCollide(player.getImage())){
                player.addAppliedForce(new Vector(0,-7000),200);
            }
//            if(e.getCode().equals(KeyCode.W)&&player.isCollide(frictionLayer.getRectangle())){
//
//                player.addAppliedForce(new Vector(0,-7000),160);
//                normalForce.set(new Vector());
//
//            }
//            if (e.getCode().equals(KeyCode.L)){
//                for(GameObject i:list){
//                    i.addCameraPosition(new Vector(10,0));
//                }
//            }
//            if (e.getCode().equals(KeyCode.K)){
//                for(GameObject i:list){
//                    i.addCameraPosition(new Vector(-10,0));
//                }
//            }
        });
        scene.setOnKeyReleased(e ->{
            if(e.getCode().equals(KeyCode.D)){
                DPressed = false;
            }
            if(e.getCode().equals(KeyCode.A)){
                APressed = false;
            }
//            if(e.getCode().equals(KeyCode.W)){
//                WPressed = false;
//            }

        });

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
