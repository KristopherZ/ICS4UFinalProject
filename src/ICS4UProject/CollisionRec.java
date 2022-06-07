package ICS4UProject;


import javafx.scene.shape.Rectangle;

public class CollisionRec extends GameObjectRec {
    private final double COLLIDER_WIDTH = 1.0;

    private final Rectangle[] colliders = new Rectangle[4];

    double x, y, sizeX, sizeY;


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
        e.setDepth(new double[]{(o.getPosition().getY()+o.getSizeY()) - (y-COLLIDER_WIDTH),
                y + this.getSizeY() + COLLIDER_WIDTH - o.getPosition().getY(),
                o.getPosition().getX()+o.getSizeX() - (x-COLLIDER_WIDTH),
                x + getSizeX() + COLLIDER_WIDTH - o.getPosition().getX()});
        return e;
    }

    public Rectangle getCollider(int num) {
        return colliders[num];
    }
}
