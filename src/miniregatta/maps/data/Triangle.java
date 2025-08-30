package miniregatta.maps.data;

import miniregatta.maps.data.objects.*;

public class Triangle extends MapTemplate {
    public Triangle() {
        buoys.add(new Buoy(320, 60, 0, true));
        buoys.add(new Buoy(220, 300, 2, true));
        buoys.add(new Buoy(420, 300, 1, true));

        order.add(0);
        order.add(1);
        order.add(2);

        yacht = new Yacht(340, 220, 0, 180, 1, 2);

        start = new Start(320, 180, 300, 0, 1, true);

        startTime = 100;
    }
}
