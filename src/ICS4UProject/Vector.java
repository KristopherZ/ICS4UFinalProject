package ICS4UProject;

/*
 * Vector class
 * Allows for force representation along the x-axis and y-axis
 */

public class Vector {

    // x and y forces for that respective vector
    private double x;
    private double y;

    // Specified x and y force constructor
    public Vector(double x, double y){
        this.x=x;
        this.y=y;
    }

    // No-args constructor
    public Vector(){
        this.x=0;
        this.y=0;
    }

    // Pass through another vector object
    public Vector(Vector v){
        this.x=v.x;
        this.y=v.y;
    }

    // Setting x and y forces for a pre-existing vector object
    public void set(Vector v){
        x=v.x;
        y=v.y;
    }

    // Getter and setter methods for x and y forces of the respective vector object
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    // toString() method returns current x and y forces in the "<x,y>" notation
    @Override
    public String toString() {
        return "<"+x+","+y+">";
    }

    /*
     * Adds the respective x and y forces of two vectors
     * Returns a new vector with the sum of the two vectors
     */
    public Vector add(Vector o){
        return new Vector(x+o.x,y+o.y);
    }

    // Obtains the dot product of two vectors (x1*x2+y1*y2)
    public double dotProduct(Vector o){
        return x*o.x+y*o.y;
    }

    // Uses the pythagorean theorem to obtain the length of the hypotenuse of this vector
    public double length(){
        return Math.sqrt(x*x+y*y);
    }

    /*
     * Multiplies the respective x and y forces of two vectors
     * Returns a new vector with the product of the two vectors
     */
    public Vector multiply(double coe){
        return new Vector(x*coe,y*coe);
    }

    // Checks if two vectors have equal x and y forces
    @Override
    public boolean equals(Object o) {
        return x==((Vector)o).x && y==((Vector)o).y;
    }

    // Getter method for current vector
    public Vector getCurrentValue() {
        return this;
    }

    public double getAngle(){
        return Math.toDegrees(Math.atan2(y,x));
    }
}