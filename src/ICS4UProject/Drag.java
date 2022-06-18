package ICS4UProject;

/**
 * This class calculation the air drag that proportional to v^2
 */

public class Drag extends Vector{

    private double dragCoe;
    private Kinetic obj;

    /**
     *
     * @param obj the object you want to contain a  air drag
     * @param dragCoe the drag coefficient
     */
    public Drag(Kinetic obj,double dragCoe){
        this.obj = obj;
        this.dragCoe = dragCoe;
    }

    /**
     * get the current drag
     * @return the current drag
     */

    @Override
    public Vector getCurrentValue() {
        Vector tempDrag = obj.getVelocity().multiply(obj.getVelocity().length()* dragCoe).multiply(-1);
        this.set(tempDrag);
        return tempDrag;
    }

    /**
     *
     * @return the frag coefficient
     */

    public double getDragCoe() {
        return dragCoe;
    }

    /**
     *
     * @param dragCoe set the drag coefficient
     */
    public void setDragCoe(double dragCoe) {
        this.dragCoe = dragCoe;
    }
}
