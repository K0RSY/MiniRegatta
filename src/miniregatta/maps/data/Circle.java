package miniregatta.maps.data;

import miniregatta.maps.data.objects.*;

public class Circle extends MapTemplate {
    public Circle() {
        buoys.add(new Buoy(330, 50, 0, true));
        buoys.add(new Buoy(330, 70, 1, true));
        buoys.add(new Buoy(310, 70, 2, true));
        buoys.add(new Buoy(310, 50, 3, true));

        order.add(0);
        order.add(3);
        order.add(2);
        order.add(1);
        order.add(0);
        order.add(3);
        order.add(2);
        order.add(1);
        order.add(0);
        order.add(3);

        yacht = new Yacht(320, 300, 0, 180, 1, 3);

        start = new Start(320, 260, 100, 0, 1, false);

        startTime = 100;
    }
}
