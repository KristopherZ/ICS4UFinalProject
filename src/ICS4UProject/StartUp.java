package ICS4UProject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;


public class StartUp  {
    HBox buttonBox;
    Button start, instructions, settings;
    Image startIcon, instructionsIcon, settingsIcon;
    Group root;
    Scene scene;
    private Main main;
    public StartUp(Main m) throws MalformedURLException, FileNotFoundException {
        main = m;
        InputStream stream = new FileInputStream("Startup-background.jpg");
        Image backgroundImage = new Image(stream);
        ImageView background = new ImageView();
        background.setImage(backgroundImage);
        background.setX(0);
        background.setY(0);
        background.setFitWidth(1280);
        background.setPreserveRatio(true);
        settingsIcon = new Image((new File("Settings-icon.png")).toURI().toURL().toString(), false);
        ImageView settingsView = new ImageView(settingsIcon);
        settingsView.setFitHeight(120);
        settingsView.setPreserveRatio(true);
        startIcon = new Image((new File("play-button.png")).toURI().toURL().toString(), false);
        ImageView startView = new ImageView(startIcon);
        startView.setFitHeight(120);
        startView.setPreserveRatio(true);
        instructionsIcon = new Image((new File("instructions-icon.png")).toURI().toURL().toString(), false);
        ImageView instructionsView = new ImageView(instructionsIcon);
        instructionsView.setFitHeight(120);
        instructionsView.setPreserveRatio(true);
        buttonBox = new HBox(100);
        start = new Button("Start");
        instructions = new Button("Instructions");
        settings = new Button("Settings");
        start.setGraphic(startView);
        instructions.setGraphic(instructionsView);
        settings.setGraphic(settingsView);
        Font font = new Font(40);
        start.setFont(font);
        instructions.setFont(font);
        settings.setFont(font);
        buttonBox.getChildren().addAll(start, instructions, settings);
        buttonBox.setAlignment(Pos.CENTER);
//        buttonBox.setPadding(new Insets(10, 20, 10, 20));
        start.setOnAction(e -> {
            main.initLevel("initializer.txt");
        });
        instructions.setOnAction(e -> {

        });
        settings.setOnAction(e -> {

        });
        StackPane sp = new StackPane(background, buttonBox);
        scene = new Scene(sp);
    }

    public Scene getScene() {
        return scene;
    }

}