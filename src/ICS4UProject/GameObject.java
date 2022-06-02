package ICS4UProject;

import javafx.concurrent.Task;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class GameObject extends Rectangle implements PhysicsUpdate, Kinetic {
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private ArrayList<Vector> forceList;
    private Vector appliedForce;

    public GameObject(double x, double y,double sizeX,double sizeY) {
        super(x,y,sizeX,sizeY);
        position = new Vector(x,y);
        velocity = new Vector();
        acceleration = new Vector();
        appliedForce = new Vector();
        forceList = new ArrayList<>();
        forceList.add(appliedForce);
    }

    public GameObject(Vector v,double sizeX,double sizeY){
        this(v.getX(),v.getY(),sizeX,sizeY);
    }

    @Override
    public boolean isCollide(Object o){
        return Shape.intersect(this,(GameObject)o).getBoundsInLocal().getWidth() != -1;
    }

    @Override
    public void update(long elapsedTime) {
        double elapsedSeconds = elapsedTime / 1_000_000_000.0;
        acceleration = new Vector();
        for(int i=0;i<forceList.size();i++){
            acceleration = acceleration.add(forceList.get(i).getCurrentValue());

        }

        velocity = velocity.add(acceleration.multiply(elapsedSeconds));
        position = position.add(velocity.multiply(elapsedSeconds));
        setX(position.getX());
        setY(position.getY());
    }

    @Override
    public Vector getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector v) {
        position = v;
    }

    @Override
    public void addDisplacement(Vector v){
        position = position.add(v);
    }

    @Override
    public Vector getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(Vector v) {
        velocity = v;
    }

    @Override
    public void addVelocity(Vector v) {
        acceleration = acceleration.add(v);
    }

    @Override
    public Vector getAcceleration() {
        return acceleration;
    }

    @Override
    public void setAcceleration(Vector v) {
        setAppliedForce(v);
    }

    @Override
    public void addAcceleration(Vector v) {
        addAppliedForce(v);
    }

    public void setAppliedForce(Vector v) {
        appliedForce.set(v);
    }

    public void addAppliedForce(Vector v){
        appliedForce.set(appliedForce.add(v));
    }

    public void addAppliedForce(Vector v,int duration){
        appliedForce.set(appliedForce.add(v));
        Thread t = new Thread(new Task() {
            @Override
            protected Object call() throws Exception {
                {
                    try {
                        Thread.sleep(duration);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    appliedForce.set(appliedForce.add(v.multiply(-1)));
                }
                return null;
            }
        } );
        t.start();
    }

    public Vector getAppliedForce() {
        return appliedForce;
    }

    public ArrayList<Vector> getForceList() {
        return forceList;
    }
}