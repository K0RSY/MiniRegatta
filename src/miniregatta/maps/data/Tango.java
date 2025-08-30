package miniregatta.maps.data;

import miniregatta.maps.data.objects.*;

public class Tango extends MapTemplate {
    public Tango() {
        buoys.add(new Buoy(420, 60, 0, true));
        buoys.add(new Buoy(420, 300, 2, true));
        buoys.add(new Buoy(120, 140, 3, true));

        order.add(0);
        order.add(2);
        order.add(1);
        order.add(0);

        yacht = new Yacht(440, 280, 270, 180, 1, 3);

        start = new Start(420, 220, 200, 0, 1, false);

        startTime = 100;
    }
}
