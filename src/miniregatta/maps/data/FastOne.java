package miniregatta.maps.data;

import miniregatta.maps.data.objects.*;

public class FastOne extends MapTemplate {
    public FastOne() {
        buoys.add(new Buoy(320, 120, 0, true));

        order.add(0);

        yacht = new Yacht(320, 300, 0, 180, 1, 4);

        start = new Start(320, 240, 100, 0, 1, false);

        startTime = 60;
    }
}
