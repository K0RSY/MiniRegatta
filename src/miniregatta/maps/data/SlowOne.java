package miniregatta.maps.data;

import miniregatta.maps.data.objects.*;

public class SlowOne extends MapTemplate {
    public SlowOne() {
        buoys.add(new Buoy(320, 60, 0, true));
        buoys.add(new Buoy(320, 220, 2, true));
        buoys.add(new Buoy(120, 100, 3, true));
        buoys.add(new Buoy(520, 180, 1, true));

        order.add(0);
        order.add(2);
        order.add(1);
        order.add(0);
        order.add(3);
        order.add(0);

        yacht = new Yacht(320, 320, 0, 180, 1, 2);

        start = new Start(320, 280, 300, 0, 1, false);

        startTime = 100;
    }
}
