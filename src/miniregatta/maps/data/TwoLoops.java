package miniregatta.maps.data;

import miniregatta.maps.data.objects.*;

public class TwoLoops extends MapTemplate {
    public TwoLoops() {
        buoys.add(new Buoy(60, 60, 3, true));
        buoys.add(new Buoy(260, 300, 2, true));
        buoys.add(new Buoy(380, 300, 1, true));
        buoys.add(new Buoy(580, 180, 0, true));

        order.add(0);
        order.add(1);
        order.add(2);
        order.add(1);
        order.add(2);
        order.add(3);

        yacht = new Yacht(540, 180, 200, 90, 1, 2);

        start = new Start(500, 180, 120, 1, 0, true);

        startTime = 100;
    }
}
