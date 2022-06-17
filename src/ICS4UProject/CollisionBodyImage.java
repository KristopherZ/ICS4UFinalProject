package ICS4UProject;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class CollisionBodyImage extends BodyImage{
    private final double COLLIDER_WIDTH = 3;
    private final double WALL_MARGIN = 3;
    private boolean isUpdate = true;

    private Rectangle[] colliders = new Rectangle[4];
    public CollisionBodyImage(double x, double y, double sizeX, double sizeY, Image image) {
        super(x, y, sizeX, sizeY, image);
        colliders[0] = new Rectangle(x+WALL_MARGIN, y-COLLIDER_WIDTH, sizeX-WALL_MARGIN*2, COLLIDER_WIDTH);
        colliders[1] = new Rectangle(x+WALL_MARGIN, y+sizeY, sizeX-WALL_MARGIN*2, COLLIDER_WIDTH);
        colliders[2] = new Rectangle(x-COLLIDER_WIDTH, y+WALL_MARGIN, COLLIDER_WIDTH, sizeY-WALL_MARGIN*2);
        colliders[3] = new Rectangle(x+sizeX, y+WALL_MARGIN, COLLIDER_WIDTH, sizeY-WALL_MARGIN*2);
    }

    public CollisionEvent collideWith(KineticsWithSize o) {
        CollisionEvent e = new CollisionEvent();
        if(isUpdate){
            e.setCollisionPosition(new boolean[]{o.isCollide(colliders[0]),
                    o.isCollide(colliders[1]),
                    o.isCollide(colliders[2]),
                    o.isCollide(colliders[3])});
            e.setDepth(new double[]{
                    o.isCollide(colliders[0]) ? ((o.getPosition().getY()+o.getSizeY()) - (getPosition().getY()-COLLIDER_WIDTH)):0,
                    o.isCollide(colliders[1]) ? (getPosition().getY() + this.getSizeY() + COLLIDER_WIDTH - o.getPosition().getY()):0,
                    o.isCollide(colliders[2]) ? (o.getPosition().getX()+o.getSizeX() - (getPosition().getX()-COLLIDER_WIDTH)):0,
                    o.isCollide(colliders[3]) ? (getPosition().getX() + getSizeX() + COLLIDER_WIDTH - o.getPosition().getX()):0});
        }
        return e;
    }

    @Override
    public void update(long elapsedTime){
        if(isUpdate) {
            updateRelativePosition();
            colliders[0].setX(getPosition().getX()+WALL_MARGIN-getCameraPosition().getX());
            colliders[0].setY((getPosition().getY()-COLLIDER_WIDTH)-getCameraPosition().getY());
            colliders[1].setX(getPosition().getX()+WALL_MARGIN-getCameraPosition().getX());
            colliders[1].setY((getPosition().getY()+getSizeY())-getCameraPosition().getY());
            colliders[2].setX(getPosition().getX()-COLLIDER_WIDTH-getCameraPosition().getX());
            colliders[2].setY(getPosition().getY()+WALL_MARGIN-getCameraPosition().getY());
            colliders[3].setX(getPosition().getX()+getSizeX()-getCameraPosition().getX());
            colliders[3].setY(getPosition().getY()+WALL_MARGIN-getCameraPosition().getY());
            getImage().setX(getRelativePosition().getX());
            getImage().setY(getRelativePosition().getY());
            updatePosition(elapsedTime);
        }
    }

    public Rectangle[] getColliders() {
        return colliders;
    }

    @Override
    public void close() {
        super.close();
        isUpdate = false;
        colliders = null;
    }
}
