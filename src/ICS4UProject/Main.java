//Main

package ICS4UProject;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;



public class Main extends Application{


    private double scaleFactor = 1;
    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image((new File("icon.png").toURI().toURL().toString()),false));
        stage.setHeight(720);
        stage.setWidth(1280);

        Button b = new Button("start");
        b.setOnAction(e->{
            initLevel(stage,"initializer.txt");
        });

        VBox vb = new VBox(b);
        Scene start = new Scene(vb);
        stage.setScene(start);
        stage.show();

    }

    private void initLevel(Stage stage, String address){
        Menu menu1 = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction((e)->{
            System.exit(0);
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
            Game game = new Game(address,group,k);
            game.start();
            stage.setScene(scene);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

}