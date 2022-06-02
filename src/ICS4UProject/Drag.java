package ICS4UProject;

public class Drag extends Vector{

    private double dragCoe;
    private Kinetic obj;

    public Drag(Kinetic obj,double dragCoe){
        this.obj = obj;
        this.dragCoe = dragCoe;
    }


    @Override
    public Vector getCurrentValue() {
        Vector tempDrag = obj.getVelocity().multiply(obj.getVelocity().length()* dragCoe).multiply(-1);
        this.set(tempDrag);
        return tempDrag;
    }

    public double getDragCoe() {
        return dragCoe;
    }

    public void setDragCoe(double dragCoe) {
        this.dragCoe = dragCoe;
    }
}
