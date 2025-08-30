package miniregatta.maps.data;

import miniregatta.maps.data.objects.*;

public class LittleWind extends MapTemplate {
    public LittleWind() {
        buoys.add(new Buoy(320, 140, 0, true));
        buoys.add(new Buoy(320, 220, 2, true));

        order.add(0);
        order.add(1);

        yacht = new Yacht(340, 200, 270, 180, 1, 1);

        start = new Start(320, 180, 100, 0, 1, true);

        startTime = 100;
    }
}
