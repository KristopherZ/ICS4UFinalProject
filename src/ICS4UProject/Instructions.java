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

/**
 * This class shows the instruction scene
 */
public class Instructions {

    private Button back;
    private Scene scene;
    private StartUp startUp;
    private VBox vBox;

    private Group root;

    private Main main;

    /**
     * Create a new instruction pane
     * @param m the main class so that the go back button works
     */
    public Instructions(Main m){
        main = m;
        back = new Button("Back");
        vBox = new VBox(10);
        ImageView picture1 = new ImageView();
        ImageView picture2 = new ImageView();
        ImageView picture3 = new ImageView();
        Label sentence = new Label("Move left and right with the A and D keys.\n" +
                "                   Press W to jump");
        sentence.setFont(new Font("Arial", 24));
        try {
            picture1.setImage(new Image((new File("Sprites/game-description.png")).toURI().toURL().toString(), false));
            picture2.setImage(new Image((new File("Sprites/enemy-descriptions.png")).toURI().toURL().toString(), false));
            picture3.setImage(new Image((new File("Sprites/jumping-description.png")).toURI().toURL().toString(), false));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        vBox.getChildren().addAll(sentence, picture3, picture1, picture2, back);
        vBox.setAlignment(Pos.CENTER);
        scene = new Scene(vBox);
        back.setOnAction(e -> {
            main.setStartUp();
        });
    }

    /**
     * Return the instruction scene
     * @return the instruction scene
     */
    public Scene getScene() {
        return scene;
    }
}
