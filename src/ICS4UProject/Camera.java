package ICS4UProject;

import java.util.ArrayList;
import java.util.Arrays;

public class Camera implements CameraView{

    private ArrayList<CameraView> arr;

    public Camera(){
        arr = new ArrayList<>();
    }

    public Camera(ArrayList<? extends CameraView> arr){
        this.arr = (ArrayList<CameraView>) arr;
    }

    public <E extends CameraView> Camera(E ... arr){
        this.arr = (ArrayList<CameraView>) Arrays.asList(arr);
    }

    public  void add(int i,CameraView e){
        arr.add(i,e);
    }

    public void get(int i){
        arr.get(i);
    }

    public  void set(int i,CameraView e){
        arr.set(i,e);
    }

    @Override
    public void setCameraPosition(Vector v) {
        for(CameraView i: arr){
            i.setCameraPosition(v);
        }
    }

    @Override
    public void addCameraPosition(Vector v) {
        for(CameraView i: arr){
            i.addCameraPosition(v);
        }
    }

    @Override
    public Vector getCameraPosition() {
        if(arr.get(0)==null){
            return null;
        }else{
            return arr.get(0).getCameraPosition();
        }
    }

}
