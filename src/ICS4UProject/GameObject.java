package ICS4UProject;

import java.util.ArrayList;

public abstract class GameObject implements PhysicsUpdate, Kinetic, CameraView{

    private Vector ObjectPosition;
    private Vector CameraPosition;
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private ArrayList<Vector> forceList;
    private Vector appliedForce;
    private double mass = 1;
    public GameObject(double x, double y){

        ObjectPosition = new Vector(x,y);
        velocity = new Vector();
        acceleration = new Vector();
        appliedForce = new Vector();
        forceList = new ArrayList<>();
        forceList.add(appliedForce);
        CameraPosition = new Vector();
    }

    /**
     * Default constructor
     */
    public GameObject(){
        this(0,0);
    }

    /**
     * Constructor with vector parameter
     * @param v Vector
     */
    public GameObject(Vector v){
        this(v.getX(),v.getY());
    }

    /**
     * updates the object's position
     * @param elapsedTime
     */
    public void updatePosition(long elapsedTime){
        double elapsedSeconds = elapsedTime / 1_000_000_000.0;
        Vector netF = new Vector();
        for(Vector v:forceList){
            netF = netF.add(v.getCurrentValue());
        }
        acceleration = netF.multiply(1/mass);
        velocity = velocity.add(acceleration.multiply(elapsedSeconds));
        ObjectPosition = ObjectPosition.add(velocity.multiply(elapsedSeconds));
    }

    /**
     * Updates the objectcom's position relative to the camera position
     */
    public void updateRelativePosition(){
        position = ObjectPosition.add(CameraPosition.multiply(-1));
    }

    @Override
    public void update(long elapsedTime) {
        updateRelativePosition();
        updatePosition(elapsedTime);
    }


    @Override
    public Vector getPosition() {
        return ObjectPosition;
    }

    public Vector getRelativePosition(){
        return position;
    }

    @Override
    public void setPosition(Vector v) {
        ObjectPosition = v;
    }

    @Override
    public void addDisplacement(Vector v){
        ObjectPosition = ObjectPosition.add(v);
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
        acceleration.set(v);
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
        Thread t = new Thread(() ->{
                    try {
                        Thread.sleep(duration);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    appliedForce.set(appliedForce.add(v.multiply(-1)));
                });
        t.start();
    }

    public Vector getAppliedForce() {
        return appliedForce;
    }

    public ArrayList<Vector> getForceList() {
        return forceList;
    }


    @Override
    public void setCameraPosition(Vector v){
        CameraPosition.set(v);
    }
    @Override
    public void addCameraPosition(Vector v){
        CameraPosition.set(CameraPosition.add(v));
    }
    @Override
    public Vector getCameraPosition(){
        return CameraPosition;
    }
    @Override
    public void setMass(double m){
        mass = m;
    }
    @Override
    public double getMass(){
        return mass;
    }

}
