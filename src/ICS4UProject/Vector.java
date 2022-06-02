package ICS4UProject;

public class Vector {

    private double x;
    private double y;

    public Vector(double x, double y){
        this.x=x;
        this.y=y;
    }

    public Vector(){
        this.x=0;
        this.y=0;
    }

    public void set(Vector v){
        x=v.x;
        y=v.y;
    }

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

    @Override
    public String toString() {
        return "<"+x+","+y+">";
    }

    public Vector add(Vector o){
        return new Vector(x+o.x,y+o.y);
    }

    public double dotProduct(Vector o){
        return x*o.x+y*o.y;
    }

    public double length(){
        return Math.sqrt(x*x+y*y);
    }

    public Vector multiply(double coe){
        return new Vector(x*coe,y*coe);
    }

    @Override
    public boolean equals(Object o) {
        return x==((Vector)o).x && y==((Vector)o).y;
    }

    public Vector getCurrentValue() {
        return this;
    }

    public double getAngle(){
        return Math.toDegrees(Math.atan2(y,x));
    }

}
