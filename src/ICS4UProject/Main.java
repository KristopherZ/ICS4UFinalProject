//Main
//We used J"ava in Two Semester" as GUI components reference
//https://link.springer.com/book/10.1007/978-3-319-99420-8

package ICS4UProject;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Optional;


public class Main extends Application {

    double scaleFactor = 1;
    Stage stage;
    StartUp startUp;
    LevelSelection levelSelection;
    int gameLevel = 0;

    @Override
    public void start(Stage PrimaryStage) throws Exception {
        stage = PrimaryStage;
        startUp = new StartUp(this);
        levelSelection = new LevelSelection("LevelSelection.txt",this);
        stage.getIcons().add(new Image((new File("icon.png").toURI().toURL().toString()),false));
        stage.setHeight(720);
        stage.setWidth(1280);

        stage.setScene(startUp.getScene());
        stage.show();
    }

    public void initLevel(String address, int level){
        gameLevel=level;
        Menu menu1 = new Menu("File");
        MenuItem exit = new MenuItem("Back to menu");
        exit.setOnAction((e)->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Progress will not be saved",ButtonType.OK,ButtonType.CANCEL);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK)
                stage.setScene(startUp.getScene());

        });
        menu1.getItems().add(exit);
        MenuBar mb = new MenuBar(menu1);
        mb.prefWidthProperty().bind(stage.widthProperty());
        Group root = new Group();
        Group group = new Group();
        Scale scale = new Scale();
        scale.setPivotX(0);
        scale.setPivotY(0);
        scale.setX(scaleFactor);
        scale.setY(scaleFactor);

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            scaleFactor = newVal.doubleValue()/720;
            scale.setX(scaleFactor);
            scale.setY(scaleFactor);
        });

        group.getTransforms().add(scale);
        root.getChildren().add(group);
        root.getChildren().add(mb);
        Scene scene = new Scene(root);
        group.setTranslateX(group.getScene().getWidth()/2);
        KeyInput k = new KeyInput(scene);
        try {
            Game game = new Game(address,group,k,this);
            game.start();
            stage.setScene(scene);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public void gameEnd(boolean isWin) {
        if(isWin)
            levelSelection.unlock(gameLevel+1);
        setLevelSelection();
    }

    public Stage getStage(){
        return stage;
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void setStartUp(){
        stage.setScene(startUp.getScene());
    }

    public void setLevelSelection() {
        stage.setScene(levelSelection.getScene());
    }
}