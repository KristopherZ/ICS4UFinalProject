package sample;

import javafx.concurrent.Task;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class GameObject extends Rectangle implements Physics {
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private Vector g;
    private Vector airDrag;
    private Vector friction;
    private Vector appliedForce;
    private Vector normalForce;
    private double DragCeo;

    public GameObject(double x, double y,double sizeX,double sizeY) {
        super(x,y,sizeX,sizeY);
        position = new Vector(x,y);
        velocity = new Vector();
        acceleration = new Vector();
        g = new Vector();
        airDrag = new Vector();
        friction = new Vector();
        appliedForce = new Vector();
        normalForce = new Vector();
        DragCeo = 0;
    }

    public GameObject(Vector v,double sizeX,double sizeY){
        this(v.getX(),v.getY(),sizeX,sizeY);
    }

    public boolean isCollide(GameObject o){
        return Shape.intersect(this,o).getBoundsInLocal().getWidth() != -1;
    }

    @Override
    public void update(long elapsedTime) {
        double elapsedSeconds = elapsedTime / 1_000_000_000.0;

        airDrag = velocity.multiply(velocity.length()*DragCeo).multiply(-1);
        acceleration = g.add(airDrag).add(friction).add(appliedForce).add(normalForce);
        velocity = velocity.add(acceleration.multiply(elapsedSeconds));
        position = position.add(velocity.multiply(elapsedSeconds));
        setX(position.getX());
        setY(position.getY());
    }

    public void setGravity(double g){
        this.g = new Vector(0,g);
    }

    public void setDrag(double coe){
        DragCeo = coe;
    }


    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector v) {
        position = v;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector v) {
        velocity = v;
    }

    public void addVelocity(Vector v) {
        acceleration = acceleration.add(v);
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    public void setAppliedForce(Vector v) {
        appliedForce = v;
    }

    public void addAppliedForce(Vector v){
        appliedForce = appliedForce.add(v);
    }

    public void addAppliedForce(Vector v,int duration){
        appliedForce = appliedForce.add(v);
        Thread t = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                {
                    try {
                        Thread.sleep(duration);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    appliedForce = appliedForce.add(v.multiply(-1));
                }
                return null;
            }
        } );
        t.start();
    }

    public Vector getNormalForce() {
        return normalForce;
    }

    public void setNormalForce(Vector normalForce) {
        this.normalForce = normalForce;
    }

    public Vector getG(){
        return g;
    }

    public Vector getFriction() {
        return friction;
    }

    public void setFriction(Vector friction) {
        this.friction = friction;
    }

    public Vector getAppliedForce() {
        return appliedForce;
    }

    //    public CollisionPosition getCollisionPosition(GameObject o){
//        if()
//    }


}