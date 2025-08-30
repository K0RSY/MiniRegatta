package miniregatta.maps.data;

import miniregatta.maps.data.objects.*;

public class LoopsTwo extends MapTemplate {
    public LoopsTwo() {
        buoys.add(new Buoy(400, 60, 0, true));
        buoys.add(new Buoy(400, 220, 2, true));
        buoys.add(new Buoy(240, 180, 3, true));

        order.add(0);
        order.add(1);
        order.add(0);
        order.add(2);

        yacht = new Yacht(400, 320, 0, 180, 1, 2);

        start = new Start(320, 280, 240, 0, 1, false);

        startTime = 100;
    }
}
