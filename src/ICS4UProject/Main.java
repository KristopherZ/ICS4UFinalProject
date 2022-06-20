//Main

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


public class Main extends Application{

    double scaleFactor = 1;
    Stage stage;
    StartUp startUp;
    Game game;

    @Override
    public void start(Stage PrimaryStage) throws Exception {
        stage = PrimaryStage;
        stage.titleProperty().setValue("Mario");
        stage.getIcons().add(new Image((new File("icon.png").toURI().toURL().toString()),false));
        stage.setHeight(720);
        stage.setWidth(1280);
        startUp = new StartUp(this);
        stage.setScene(startUp.getScene());
//        LevelSelection ls = new LevelSelection();
//        stage.setScene(ls.getScene());
        stage.show();
    }

    public void initLevel(String address){

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
            game = new Game(address,group,k,this);
            game.start();
            stage.setScene(scene);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public void gameEnd(boolean isWin){
        Label lb = new Label("Game End");
        Group root = new Group(lb);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        game = null;
    }

    public Stage getStage(){
        return stage;
    }


    public static void main(String[] args) {
        launch(args);
    }

}