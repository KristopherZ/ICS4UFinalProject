package ICS4UProject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class LevelSelection {
    Scene scene;
    ScrollPane scrollPane;
    public LevelSelection(){

        Button b1 =new Button("Level 1");
        Button b2 =new Button("Level 2");
        Button b3 =new Button("Level 3");

        HBox hb = new HBox(100);
        hb.getChildren().addAll(b1, b2, b3);
        hb.setPadding(new Insets(100));
        hb.setAlignment(Pos.CENTER);

        scrollPane = new ScrollPane(hb);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scene = new Scene(scrollPane);
    }

    public Scene getScene() {
        return scene;
    }
}
