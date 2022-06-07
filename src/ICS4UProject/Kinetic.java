package ICS4UProject;

public interface Kinetic {
    public Vector getPosition();
    public void setPosition(Vector v);
    public void addDisplacement(Vector v);
    public Vector getVelocity();
    public void setVelocity(Vector v);
    public void addVelocity(Vector v);
    public Vector getAcceleration();
    public void setAcceleration(Vector v);
    public void addAcceleration(Vector v);
    public boolean isCollide(Object o);
    public void setMass(double m);
    public double getMass();
}
