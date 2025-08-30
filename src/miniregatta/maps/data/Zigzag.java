package miniregatta.maps.data;

import miniregatta.maps.data.objects.*;

public class Zigzag extends MapTemplate {
    public Zigzag() {
        buoys.add(new Buoy(440, 60, 0, true));
        buoys.add(new Buoy(200, 120, 3, true));
        buoys.add(new Buoy(440, 180, 2, true));
        buoys.add(new Buoy(200, 240, 3, true));

        order.add(0);
        order.add(1);
        order.add(2);
        order.add(3);

        yacht = new Yacht(400, 320, 0, 180, 1, 2);

        start = new Start(320, 280, 240, 0, 1, false);

        startTime = 100;
    }
}
