package OOD.ParkingLot;

import java.util.ArrayList;
import java.util.List;

import com.sun.tools.javac.util.Assert;


/**
 * Created by tianbingleng on 21/10/2017.
 */
public class Test {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(4, 10);
        List<Vehicle> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            final Vehicle v = i % 2 == 0 ? new Car() : new Truck();
            list.add(v);
            boolean hasSpot = lot.hasSpot(v);
            if (i < 40) {
                Assert.check(hasSpot);
                Assert.check(lot.park(v));
            } else {
                Assert.check(!hasSpot);
                Assert.check(!lot.park(v));
            }
        }
        assert list.size() == 50;
        int i = 0;
        for (Vehicle v : list) {
            Assert.check(i >= 40 || lot.leave(v));
            i++;
        }
    }
}
