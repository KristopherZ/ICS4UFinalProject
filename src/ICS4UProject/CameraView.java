package ICS4UProject;

/**
 * This interface enable object to be moved by the camera
 */

public interface CameraView {
    public void setCameraPosition(Vector v);
    public void addCameraPosition(Vector v);
    public Vector getCameraPosition();
}
