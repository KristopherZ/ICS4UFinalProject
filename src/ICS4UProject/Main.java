//Main

package ICS4UProject;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
        Menu menu1 = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction((e)->{
            System.exit(0);
        });
        menu1.getItems().add(exit);
        MenuBar mb = new MenuBar(menu1);
        mb.prefWidthProperty().bind(stage.widthProperty());
        Group group = new Group(mb);

        group.minHeight(500);
        group.minHeight(500);

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