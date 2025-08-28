package miniregatta.maps.data;

import java.io.Serializable;
import java.util.ArrayList;

import miniregatta.maps.data.objects.*;

public class MapTemplate implements Serializable {
    public ArrayList<Buoy> buoys = new ArrayList<Buoy>();
    public ArrayList<Integer> order = new ArrayList<Integer>();
    public Yacht yacht;
    public Start start;
    public int startTime;
}
