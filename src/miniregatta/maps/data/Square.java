package miniregatta.maps.data;

import miniregatta.maps.data.objects.*;

public class Square extends MapTemplate {
    public Square() {
        buoys.add(new Buoy(440, 300, 1, true));
        buoys.add(new Buoy(440, 60, 0, true));
        buoys.add(new Buoy(200, 60, 3, true));
        buoys.add(new Buoy(200, 300, 2, true));

        order.add(0);
        order.add(1);
        order.add(2);
        order.add(3);

        yacht = new Yacht(240, 180, 90, 270, 1, 3);

        start = new Start(320, 180, 120, -1, 0, true);

        startTime = 100;
    }
}
