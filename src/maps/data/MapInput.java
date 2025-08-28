package maps.data;

import maps.data.objects.*;

public class MapInput extends MapTemplate {
    public MapInput() {
        buoys.add(new Buoy(320, 60, 0, true));
        buoys.add(new Buoy(320, 300, 2, true));

        order.add(0);
        order.add(1);

        yacht = new Yacht(340, 280, 270, 180, 1, 2);

        start = new Start(320, 240, 100, 0, 1, true);

        startTime = 100;
    }
}
