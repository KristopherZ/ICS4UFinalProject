package ICS4UProject;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class KeyInput {

    private boolean wPressed, aPressed, sPressed, dPressed, spaceBarPressed;

    public KeyInput(Scene scene) {
        scene.setOnKeyPressed(e ->{
            if(e.getCode().equals(KeyCode.W)) {
                wPressed = true;
            }

            if(e.getCode().equals(KeyCode.A)) {
                aPressed = true;
            }
            if(e.getCode().equals(KeyCode.S)) {
                sPressed = true;
            }
            if(e.getCode().equals(KeyCode.D)) {
                dPressed = true;
            }
            if(e.getCode().equals(KeyCode.SPACE)) {
                spaceBarPressed = true;
            }
        });

        scene.setOnKeyReleased(e ->{
            if(e.getCode().equals(KeyCode.W)) {
                wPressed = false;
            }
            if(e.getCode().equals(KeyCode.A)) {
                aPressed = false;
            }
            if(e.getCode().equals(KeyCode.S)) {
                sPressed = false;
            }
            if(e.getCode().equals(KeyCode.D)) {
                dPressed = false;
            }
            if(e.getCode().equals(KeyCode.SPACE)) {
                spaceBarPressed = false;
            }

        });

    }

    public boolean iswPressed() {
        return wPressed;
    }

    public boolean isaPressed(){
        return aPressed;
    }
    public boolean issPressed(){
        return sPressed;
    }

    public boolean isdPressed() {
        return dPressed;
    }

    public boolean isSpaceBarPressed() {
        return spaceBarPressed;
    }
}
