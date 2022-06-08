package ICS4UProject;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class CollisionRec extends GameObjectRec {
    private final double COLLIDER_WIDTH = 4;

    private final Rectangle[] colliders = new Rectangle[4];


    public CollisionRec(double x, double y, double sizeX, double sizeY) {
        super(x, y,sizeX,sizeY);
        colliders[0] = new Rectangle(x, y-COLLIDER_WIDTH, sizeX, COLLIDER_WIDTH);
        colliders[1] = new Rectangle(x, y+sizeY, sizeX, COLLIDER_WIDTH);
        colliders[2] = new Rectangle(x-COLLIDER_WIDTH, y, COLLIDER_WIDTH, sizeY);
        colliders[3] = new Rectangle(x+sizeX, y, COLLIDER_WIDTH, sizeY);
    }

    public CollisionEvent collideWith(KineticsWithSize o) {
        CollisionEvent e = new CollisionEvent();
        e.setCollisionPosition(new boolean[]{o.isCollide(colliders[0]),
                o.isCollide(colliders[1]),
                o.isCollide(colliders[2]),
                o.isCollide(colliders[3])});
        e.setDepth(new double[]{
                o.isCollide(colliders[0]) ? ((o.getPosition().getY()+o.getSizeY()) - (getPosition().getY()-COLLIDER_WIDTH)):Double.POSITIVE_INFINITY,
                o.isCollide(colliders[1]) ? (getPosition().getY() + this.getSizeY() + COLLIDER_WIDTH - o.getPosition().getY()):Double.POSITIVE_INFINITY,
                o.isCollide(colliders[2]) ? (o.getPosition().getX()+o.getSizeX() - (getPosition().getX()-COLLIDER_WIDTH)):Double.POSITIVE_INFINITY,
                o.isCollide(colliders[3]) ? (getPosition().getX() + getSizeX() + COLLIDER_WIDTH - o.getPosition().getX()):Double.POSITIVE_INFINITY});
        return e;
    }

    @Override
    public void update(long elapsedTime){
        super.update(elapsedTime);
        colliders[0].setX(getPosition().getX()-getCameraPosition().getX());
        colliders[0].setY((getPosition().getY()-COLLIDER_WIDTH)-getCameraPosition().getY());
        colliders[1].setX(getPosition().getX()-getCameraPosition().getX());
        colliders[1].setY((getPosition().getY()+COLLIDER_WIDTH)-getCameraPosition().getY());
        colliders[2].setX((getPosition().getX()-COLLIDER_WIDTH)-getCameraPosition().getX());
        colliders[2].setY(getPosition().getY()-getCameraPosition().getY());
        colliders[3].setX((getPosition().getX()+COLLIDER_WIDTH)-getCameraPosition().getX());
        colliders[3].setY(getPosition().getY()-getCameraPosition().getY());
    }
}
