package ICS4UProject;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class Instructions {

    Button back;
    Scene scene;
    StartUp startUp;
    VBox vBox;

    Group root;

    Main main;

    public Instructions(Main m) throws MalformedURLException, FileNotFoundException {
        main = m;
        back = new Button("Back");
        vBox = new VBox(10);
        ImageView picture1 = new ImageView();
        ImageView picture2 = new ImageView();
        ImageView picture3 = new ImageView();
        Label sentence = new Label("Move left and right with the A and D keys.\n" +
                "                   Press W to jump");
        sentence.setFont(new Font("Arial", 24));
        picture1.setImage(new Image((new File("game-description.png")).toURI().toURL().toString(), false));
        picture2.setImage(new Image((new File("enemy-descriptions.png")).toURI().toURL().toString(), false));
        picture3.setImage(new Image((new File("jumping-description.png")).toURI().toURL().toString(), false));
        vBox.getChildren().addAll(sentence, picture3, picture1, picture2, back);
        vBox.setAlignment(Pos.CENTER);
        scene = new Scene(vBox);
        back.setOnAction(e -> {
            main.setStartUp();
        });
    }

    public Scene getScene() {
        return scene;
    }
}
