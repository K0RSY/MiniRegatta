package miniregatta.maps.data;

import miniregatta.maps.data.objects.*;

public class ALotOfWind extends MapTemplate {
    public ALotOfWind() {
        buoys.add(new Buoy(60, 180, 3, true));
        buoys.add(new Buoy(500, 180, 1, true));

        order.add(0);
        order.add(1);

        yacht = new Yacht(460, 180, 180, 90, 1, 4);

        start = new Start(420, 180, 100, 1, 0, true);

        startTime = 100;
    }
}
