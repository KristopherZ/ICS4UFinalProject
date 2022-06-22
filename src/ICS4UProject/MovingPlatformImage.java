package ICS4UProject;

import javafx.scene.image.Image;

public class MovingPlatformImage extends PlatformImage{

    boolean isUpdate = true;

    /**
     * To construct a moving platform
     *
     * @param x     the x coordinate of the platform
     * @param y     the y coordinate of the platform
     * @param sizeX the width
     * @param sizeY the height
     * @param image the image of the platform
     */
    public MovingPlatformImage(double x, double y, double sizeX, double sizeY, Image image) {
        super(x, y, sizeX, sizeY, image);
        setVelocity(new Vector(0, 50));
    }

    public void update(long elapsedTime) {
        System.out.println(1);

        if(isUpdate){
            super.update(elapsedTime);
            if(getPosition().getY() > 800) {
                setVelocity(new Vector(0, -50));
                System.out.println("bounced up");
            }
            if (getPosition().getY() < 0) {
                setVelocity(new Vector(0, 50));
                System.out.println("Bounced down");
            }
        }
    }

    public void close() {
        super.close();
        isUpdate = false;
    }
}
