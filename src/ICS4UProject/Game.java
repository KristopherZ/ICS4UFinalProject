package ICS4UProject;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class can initialize the game
 */
public class Game extends AnimationTimer {

    private long lastUpdatedTime = 0;
    // coefficient that determines the amount of force from gravity
    private boolean isUpdate = true;
    private final double gravityCoefficient = 2000;
    private static final double cameraOffset = 100;
    private final Camera camera = new Camera();
    private final ArrayList<Player> playerList = new ArrayList<>();
    private final ArrayList<Enemy> enemyList = new ArrayList<>();
    private final ArrayList<EnemyShell> enemyShellList = new ArrayList<>();
    private final ArrayList<PlatformImage> platformImageList = new ArrayList<>();
    private final ArrayList<Mushroom> mushroomList = new ArrayList<>();
    private Main main;
    private Text score;

    /**
     * Scans the "Initializer.txt" file contained within the project folder
     * Inside the file is a list of all the objects that will be in the scene, with their parameter values
     * This simplifies the process of creating Kinetic objects and PlatformImage objects
     *
     * @param address The address of the "Initializer.txt" file inside the project folder
     * @param root A group - should be empty when passed into parameter
     * @throws FileNotFoundException
     * @throws MalformedURLException
     */
    public Game(String address, Group root, KeyInput k,Main m) throws FileNotFoundException, MalformedURLException {
        main = m;
        Font font = new Font(30);
        score = new Text("Score:0");
        score.setFont(font);
        score.setX(10);
        score.setY(70);
        File textFile = new File(address);
        Scanner input = new Scanner(textFile);

        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.startsWith("1")) {
                Image image;
                String[] values = line.split(" ");
                image = new Image((new File(values[5])).toURI().toURL().toString(), false);
                Player p = new Player(Double.parseDouble(values[1]), Double.parseDouble(values[2]),
                        Double.parseDouble(values[3]), Double.parseDouble(values[4]), image, k,this);
                p.setGravity(new Vector(0, gravityCoefficient));
                p.setFrictionCoe(1);
                p.setDragCoe(0.001);
//                p.setElasticity(new double[]{0, 0, 0, 0});
                playerList.add(p);

            } else if (line.startsWith("2")) {
                Image image;
                String[] values = line.split(" ");
                image = new Image((new File(values[5])).toURI().toURL().toString(), false);
                Enemy e = new Enemy(Double.parseDouble(values[1]), Double.parseDouble(values[2]),
                        Double.parseDouble(values[3]), Double.parseDouble(values[4]), image);
                e.setGravity(new Vector(0, gravityCoefficient));
                enemyList.add(e);
            } else if (line.startsWith("3")) {
                Image image;
                String[] values = line.split(" ");
                image = new Image((new File(values[5])).toURI().toURL().toString(), false);
                EnemyShell e = new EnemyShell(Double.parseDouble(values[1]), Double.parseDouble(values[2]),
                        Double.parseDouble(values[3]), Double.parseDouble(values[4]), image);
                e.setGravity(new Vector(0, gravityCoefficient));
                enemyShellList.add(e);
            }else if(line.startsWith("4")) {
                Image image;
                String[] values = line.split(" ");
                image = new Image(new File(values[5]).toURI().toURL().toString(), false);
                PlatformImage platform = new PlatformImage(Double.parseDouble(values[1]), Double.parseDouble(values[2]),
                        Double.parseDouble(values[3]), Double.parseDouble(values[4]), image);
                platform.setFrictionCoe(1);
                platformImageList.add(platform);
            } else {
                Image pImage;
                Image mImage;
                String[] values = line.split(" ");
                pImage = new Image(new File(values[5]).toURI().toURL().toString(), false);
                mImage = new Image(new File(values[8]).toURI().toURL().toString(), false);
                PlatformImage trigger = new PlatformImage(Double.parseDouble(values[1]), Double.parseDouble(values[2]),
                        Double.parseDouble(values[3]), Double.parseDouble(values[4]), pImage);
                Mushroom mushroom = new Mushroom(trigger, Double.parseDouble(values[6]),
                        Double.parseDouble(values[7]), mImage);
                trigger.setFrictionCoe(1);
                mushroom.setGravity(new Vector(0,2000));
                mushroom.setMovingVelocity(new Vector(300,0));
                trigger.setFrictionCoe(1);
                platformImageList.add(trigger);
                mushroomList.add(mushroom);
            }
        }

        for (PlatformImage platform : platformImageList) {
            for (Enemy enemy : enemyList) {
                platform.addKinetic(enemy);
            }
            for (EnemyShell enemyShell : enemyShellList) {
                platform.addKinetic(enemyShell);
            }
            for (Player player : playerList) {
                platform.addKinetic(player);

            }
            for (Mushroom mushroom: mushroomList) {
                platform.addKinetic(mushroom);
            }
        }

        for (Enemy enemy : enemyList) {
            camera.add(enemy);
        }
        for (EnemyShell enemyShell : enemyShellList) {
            camera.add(enemyShell);
        }
        for (Player player : playerList) {
            camera.add(player);
        }
        for (PlatformImage platform : platformImageList) {
            camera.add(platform);
        }
        for (Mushroom mushroom: mushroomList) {
            camera.add(mushroom);
        }

        for (PlatformImage platform : platformImageList) {
            for (Enemy enemy : enemyList) {
                enemy.getPlatformImageList().add(platform);
            }
        }

        for (PlatformImage platform : platformImageList) {
            for (EnemyShell enemy : enemyShellList) {
                enemy.getPlatformImageList().add(platform);
            }
        }

        for (PlatformImage platform : platformImageList) {
            for (Player player : playerList) {
                player.getPlatformImageList().add(platform);
            }
        }

        for (PlatformImage platform: platformImageList) {
            for (Mushroom mushroom: mushroomList) {
                mushroom.getPlatformImageList().add(platform);
            }
        }

        for (Enemy enemy : enemyList) {
            root.getChildren().add(enemy.getImage());
        }
        for (EnemyShell enemyShell : enemyShellList) {
            root.getChildren().add(enemyShell.getImage());
        }
        for (Player player : playerList) {
            root.getChildren().add(player.getImage());
        }

        for (PlatformImage platform : platformImageList) {
            root.getChildren().add(platform.getImage());
        }
        for (Mushroom mushroom: mushroomList) {
            root.getChildren().add(mushroom.getImage());
        }

        root.getChildren().add(score);

        for (Enemy enemy: enemyList) {
            for (Player p: playerList) {
                enemy.addPlayer(p);
            }

        }

        for (Mushroom mushroom: mushroomList) {
            for (Player p: playerList) {
                mushroom.addPlayer(p);
            }

        }
        start();

    }

    /**
     * Updates all objects inside the scene
     * @param timestamp the timestamp when the method is called
     */
    @Override
    public void handle(long timestamp) {
        score.setText("Score:"+playerList.get(0).getScore());
        if (lastUpdatedTime > 0) {
            long elapsedTime = timestamp - lastUpdatedTime;

            camera.setCameraPosition(new Vector(playerList.get(0).getPosition().getX()-cameraOffset, 0));
            for (Enemy enemy : enemyList) {
                enemy.update(elapsedTime);
            }
            for (EnemyShell enemyShell : enemyShellList) {
                enemyShell.update(elapsedTime);
            }
            for (Player player : playerList) {
                player.update(elapsedTime);
            }
            for (PlatformImage platform : platformImageList) {
                platform.update(elapsedTime);
            }
            for (Mushroom mushroom: mushroomList) {
                mushroom.update(elapsedTime);
            }
        }
        lastUpdatedTime = timestamp;
    }

    public void endGame() {
        for (Enemy enemy : enemyList) {
            enemy.close();
        }
        for (EnemyShell enemyShell : enemyShellList) {
            enemyShell.close();
        }
        for (Player player : playerList) {
            player.close();
        }
        for (PlatformImage platform : platformImageList) {
            platform.close();
        }
        for (Mushroom mushroom: mushroomList) {
            mushroom.close();
        }
    }

    public void gameEnd(boolean isWin){
        stop();
        main.gameEnd(isWin);
    }

}