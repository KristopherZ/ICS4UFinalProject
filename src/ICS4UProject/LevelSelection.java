package ICS4UProject;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class LevelSelection {
    Scene scene;
    ScrollPane scrollPane;
    public LevelSelection(){

        Button b1 =new Button("111");
        Button b2 =new Button("222");

        HBox hb = new HBox(b1,b2);

        scrollPane = new ScrollPane(hb);
        scene = new Scene(scrollPane);
    }

    public Scene getScene() {
        return scene;
    }
}
