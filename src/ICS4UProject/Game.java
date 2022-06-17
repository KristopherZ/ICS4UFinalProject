package ICS4UProject;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends AnimationTimer {

    private long lastUpdatedTime = 0;

    private final double gravityCoefficient = 2000;
    private static final double cameraOffset = 100;
    private final Camera camera = new Camera();

    private final ArrayList<Player> playerList = new ArrayList<>();
    private final ArrayList<Enemy> enemyList = new ArrayList<>();
    private final ArrayList<EnemyShell> enemyShellList = new ArrayList<>();
    private final ArrayList<Platform> platformList = new ArrayList<>();

    public Game(String address, Group root) throws FileNotFoundException, MalformedURLException {

        File textFile = new File(address);
        Scanner input = new Scanner(textFile);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.startsWith("1")) {
                Image image;
                String[] values = line.split(" ");
                image = new Image((new File(values[5])).toURI().toURL().toString(), false);
                Player p = new Player(Double.parseDouble(values[1]), Double.parseDouble(values[2]),
                        Double.parseDouble(values[3]), Double.parseDouble(values[4]), image);
                p.setGravity(new Vector(0, gravityCoefficient));
//                p.setElasticity(new double[]{0, 0, 0, 0});
                playerList.add(p);

            } else if (line.startsWith("2")) {
                Image image;
                String[] values = line.split(" ");
                image = new Image((new File(values[5])).toURI().toURL().toString(), false);
                enemyList.add(new Enemy(Double.parseDouble(values[1]), Double.parseDouble(values[2]),
                        Double.parseDouble(values[3]), Double.parseDouble(values[4]), image));
            } else if (line.startsWith("3")) {
                Image image;
                String[] values = line.split(" ");
                image = new Image((new File(values[5])).toURI().toURL().toString(), false);
                enemyShellList.add(new EnemyShell(Double.parseDouble(values[1]), Double.parseDouble(values[2]),
                        Double.parseDouble(values[3]), Double.parseDouble(values[4]), image));
            } else {
                String[] values = line.split(" ");
                Platform platform = new Platform(Double.parseDouble(values[1]), Double.parseDouble(values[2]),
                        Double.parseDouble(values[3]), Double.parseDouble(values[4]));
                platformList.add(platform);
            }
        }

        for (Platform platform : platformList) {
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

        for (Platform platform : platformList) {
            for (Enemy enemy : enemyList) {
                enemy.getPlatformList().add(platform);
            }
        }

        for (Platform platform : platformList) {
            for (EnemyShell enemy : enemyShellList) {
                enemy.getPlatformList().add(platform);
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

        for (Platform platform : platformList) {
            root.getChildren().add(platform.getRectangle());
        }


    }

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
            for (Platform platform : platformList) {
                platform.update(elapsedTime);
            }
        }
        lastUpdatedTime = timestamp;
    }

}