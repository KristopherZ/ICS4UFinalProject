package ICS4UProject;

public class CollisionEvent {
    private boolean[] collisionPosition; // Boolean array that detects which direction the collision in coming from
    private double[] depth;

    public void setCollisionPosition(boolean[] collisionPosition) {
        this.collisionPosition = collisionPosition;
    }

    public void setDepth(double[] depth) {
        this.depth = depth;
    }

    public boolean[] getCollisionPosition() {
        return collisionPosition;
    }

    public double[] getDepth() {
        return depth;
    }
}
