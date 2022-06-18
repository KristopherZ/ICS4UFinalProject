//Main

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

    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(500);
        stage.setHeight(500);
        Group group = new Group();
        Scene scene = new Scene(group);
        KeyInput k = new KeyInput(scene);
        stage.setScene(scene);
        Game game = new Game("initializer.txt",group,k);

        stage.show();
        game.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}