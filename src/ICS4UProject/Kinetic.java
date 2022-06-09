package ICS4UProject;

import javafx.scene.Node;

import java.util.ArrayList;

/**
 * Kinetic interface
 * Implemented in various other classes in order to add kinetic properties to objects
 */

public interface Kinetic {
    /**
     * Getter and setter methods for position, displacement, velocity, acceleration and mass
     */
    public Vector getPosition();
    public void setPosition(Vector v);
    public void addDisplacement(Vector v);
    public Vector getVelocity();
    public void setVelocity(Vector v);
    public void addVelocity(Vector v);
    public Vector getAcceleration();
    public void setAcceleration(Vector v);
    public void addAcceleration(Vector v);
    public void setMass(double m);
    public double getMass();

    // Checks for collision between two objects
    public boolean isCollide(Object o);

    // List of applied forces to an object
    public ArrayList<Vector> getForceList();
}
