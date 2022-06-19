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
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class can initialize the game
 */
public class Game extends AnimationTimer {

    private long lastUpdatedTime = 0;
    // coefficient that determines the amount of force from gravity
    private final double gravityCoefficient = 2000;
    private static final double cameraOffset = 100;
    private final Camera camera = new Camera();

    private final ArrayList<Player> playerList = new ArrayList<>();
    private final ArrayList<Enemy> enemyList = new ArrayList<>();
    private final ArrayList<EnemyShell> enemyShellList = new ArrayList<>();
    private final ArrayList<PlatformImage> platformImageList = new ArrayList<>();
    private ArrayList<Player> addPlayer = new ArrayList<>();

    /**
     * Scans the "Initializer.txt" file contained within the project folder
     * Inside the file is a list of all the objects that will be in the scene, with their parameter values
     * This simplifies the process of creating Kinetic objects and PlatformImage objects
     *
     * @param address The address of the "Initializer.txt" file inside the project folder
     * @param root A group - should be empty when passed into parameter
     * @param k the keyboard input
     * @throws FileNotFoundException
     * @throws MalformedURLException
     */
    public Game(String address, Group root, KeyInput k) throws FileNotFoundException, MalformedURLException {
        File textFile = new File(address);
        Scanner input = new Scanner(textFile);

        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.startsWith("1")) {
                Image image;
                String[] values = line.split(" ");
                image = new Image((new File(values[5])).toURI().toURL().toString(), false);
                Player p = new Player(Double.parseDouble(values[1]), Double.parseDouble(values[2]),
                        Double.parseDouble(values[3]), Double.parseDouble(values[4]), image, k);
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
                for (Player i : playerList) {
                    e.addPlayer(i);
                }
                enemyList.add(e);
            } else if (line.startsWith("3")) {
                Image image;
                String[] values = line.split(" ");
                image = new Image((new File(values[5])).toURI().toURL().toString(), false);
                EnemyShell e = new EnemyShell(Double.parseDouble(values[1]), Double.parseDouble(values[2]),
                        Double.parseDouble(values[3]), Double.parseDouble(values[4]), image);
                e.setGravity(new Vector(0, gravityCoefficient));
                enemyShellList.add(e);
            }else {
                Image image;
                String[] values = line.split(" ");
                image = new Image(new File(values[5]).toURI().toURL().toString(), false);
                PlatformImage platform = new PlatformImage(Double.parseDouble(values[1]), Double.parseDouble(values[2]),
                        Double.parseDouble(values[3]), Double.parseDouble(values[4]), image);
                platform.setFrictionCoe(1);
                platformImageList.add(platform);
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

    }

    /**
     * Updates all objects inside the scene
     * @param timestamp the timestamp when the method is called
     */
    @Override
    public void handle(long timestamp) {

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
        }
        lastUpdatedTime = timestamp;
    }

}