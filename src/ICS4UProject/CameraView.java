package ICS4UProject;

/**
 * This interface enable object to be moved by the camera
 */

public interface CameraView {
    /**
     * set the camera to the given position
     * @param v the vector represent the position of the camera
     */
    public void setCameraPosition(Vector v);
    /**
     * add the camera to the given position
     * @param v the vector represent the change of position of the camera
     */
    public void addCameraPosition(Vector v);

    /**
     *
     * @return the position of the camera
     */
    public Vector getCameraPosition();
}
