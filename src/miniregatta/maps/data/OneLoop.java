package miniregatta.maps.data;

import miniregatta.maps.data.objects.*;

public class OneLoop extends MapTemplate {
    public OneLoop() {
        buoys.add(new Buoy(320, 60, 0, true));
        buoys.add(new Buoy(320, 300, 2, true));

        order.add(0);
        order.add(1);

        yacht = new Yacht(340, 280, 270, 180, 1, 2);

        start = new Start(320, 240, 100, 0, 1, true);

        startTime = 100;
    }
}
