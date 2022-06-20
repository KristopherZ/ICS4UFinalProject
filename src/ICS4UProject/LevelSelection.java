package ICS4UProject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.StringJoiner;

public class LevelSelection {
    private ArrayList<Button> levels = new ArrayList<>();
    private Scene scene;
    private ScrollPane scrollPane;
    private HBox hBox;
    private VBox vBox;
    private Button back;
    private ArrayList<String> levelNames = new ArrayList<>();
    private ArrayList<Boolean> isLock = new ArrayList<>();
    private Main main;
    private String fileAddress;

    public LevelSelection(String address, Main m) throws MalformedURLException, FileNotFoundException {
        fileAddress = address;
        main = m;
        Scanner loader = new Scanner(new File(address));
        while (loader.hasNextLine()) {
            String line = loader.nextLine();
            String[] info = line.split(" ");
            levelNames.add(info[0]);
            isLock.add(info[1].equals("true"));
        }
        loader.close();

        for (int i = 0; i < levelNames.size(); i++) {
            int finalI = i;
            String pictureAddress = "";
            if (isLock.get(finalI)) {
                pictureAddress = levelNames.get(finalI) + "\\lock.jpg";
            } else {
                pictureAddress = levelNames.get(finalI) + "\\unlock.jpg";
            }
            File image = new File(pictureAddress);
            ImageView imageView = new ImageView(new Image(image.toURI().toURL().toString(), false));
            imageView.fitHeightProperty().bind(main.getStage().heightProperty().multiply(.7));
            imageView.setPreserveRatio(true);
            Button levelButton = new Button();
            levelButton.setGraphic(imageView);
            levelButton.setOnAction(e -> {
                System.out.println(levels.indexOf(e.getSource()));
                if (isLock.get(finalI)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "This level has not been unlocked yet", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    main.initLevel(levelNames.get(finalI) + "\\initializer.txt",levels.indexOf(e.getSource()));
                }
            });
            levels.add(levelButton);
        }
        hBox = new HBox(40);
        vBox = new VBox(10);
        Label label = new Label("Level Selection");
        Font font = new Font(40);
        label.setFont(font);
        hBox.setPadding(new Insets(10, 40, 10, 40));
        hBox.getChildren().addAll(levels);
        scrollPane = new ScrollPane(hBox);
        vBox.setAlignment(Pos.TOP_CENTER);
        Button back = new Button("Back");
        back.setOnAction(e -> {
            main.setStartUp();
        });
        vBox.getChildren().addAll(label, scrollPane, back);
        scene = new Scene(vBox);

    }

    public void update() throws MalformedURLException {
        for (int i = 0; i < levels.size(); i++) {
            int finalI = i;
            String pictureAddress = "";
            if (isLock.get(finalI)) {
                pictureAddress = levelNames.get(finalI) + "\\lock.jpg";
            } else {
                pictureAddress = levelNames.get(finalI) + "\\unlock.jpg";
            }
            File image = new File(pictureAddress);
            ImageView imageView = new ImageView(new Image(image.toURI().toURL().toString(), false));
            imageView.fitHeightProperty().bind(main.getStage().heightProperty().multiply(.7));
            imageView.setPreserveRatio(true);
            levels.get(i).setGraphic(imageView);
            levels.get(i).setOnAction(e -> {
                if (isLock.get(finalI)) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This level has not been unlocked yet", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    main.initLevel(levelNames.get(finalI) + "\\initializer.txt",levels.indexOf(e.getSource()));
                }
            });
        }


    }

    public void unlock(int index) {
        if(index<levels.size()){
            isLock.set(index,false);
            try {
                update();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                updateFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateFile() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File(fileAddress));
        StringJoiner joiner = new StringJoiner("\n");
        for(int i=0;i<levelNames.size();i++){
            joiner.add(levelNames.get(i)+" "+isLock.get(i));
        }
        pw.print(joiner);
        pw.close();
    }

    public Scene getScene() {
        return scene;
    }
}
