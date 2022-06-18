package ICS4UProject;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        File file = new File("playerSprite.png");
        File playerSprite = new File("image.jpg");

        Image image = new Image(file.toURI().toURL().toString(), false);
        Image playerImage = new Image(playerSprite.toURI().toURL().toString(), false);
//        Enemy enemy = new Enemy(500,0,50,50,image);
        final EnemyShell[] enemy = {new EnemyShell(500, 0, 50, 50, image)};
        pt1.addKinetic(enemy[0]);
        pt2.addKinetic(enemy[0]);
        pt3.addKinetic(enemy[0]);
        pt4.addKinetic(enemy[0]);

        enemy[0].getPlatformList().add(pt1);
        enemy[0].getPlatformList().add(pt2);
        enemy[0].getPlatformList().add(pt3);
        enemy[0].getPlatformList().add(pt4);
        enemy[0].setGravity(new Vector(0,2000));
        enemy[0].setVelocity(new Vector(500,0));
//        Player player = new Player(500,0,50,50,playerImage,k);
        Group root = new Group(pt1.getRectangle(),pt2.getRectangle(),pt3.getRectangle(),pt4.getRectangle(), enemy[0].getImage());
        primaryStage.setWidth(1000);
        primaryStage.setHeight(500);
        Scene scene = new Scene(root);
        KeyInput k = new KeyInput(scene);

        Player player = new Player(500,40,50,50,playerImage,k);
        root.getChildren().add(player.getImage());

        // PLAYER
        pt1.addKinetic(player);
        pt2.addKinetic(player);
        pt3.addKinetic(player);
        pt4.addKinetic(player);

        player.setPhysics();

        primaryStage.setScene(scene);
        AnimationTimer t = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if(lastUpdatedTime > 0){
                    long elapsedTime = timestamp - lastUpdatedTime;
                    enemy[0].update(elapsedTime);
                    player.update(elapsedTime);
                    if(enemy[0].hit)  {
                        enemy[0].close();
                    }
                    pt1.update(elapsedTime);
                    pt2.update(elapsedTime);
                    pt3.update(elapsedTime);
                    pt4.update(elapsedTime);
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
