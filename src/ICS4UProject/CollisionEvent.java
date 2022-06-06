package ICS4UProject;

public class CollisionEvent {
    private boolean[] collisionPosition;
    private boolean[] depth;

    public void setCollisionPosition(boolean[] collisionPosition) {
        this.collisionPosition = collisionPosition;
    }

    public void setDepth(boolean[] depth) {
        this.depth = depth;
    }

    public boolean[] getCollisionPosition() {
        return collisionPosition;
    }

    public boolean[] getDepth() {
        return depth;
    }
}
