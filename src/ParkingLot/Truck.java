package ParkingLot;

/**
 * Created by tianbingleng on 21/10/2017.
 */
public class Truck extends Vehicle {
    @Override
    public VehicleSize getSize() {
        return VehicleSize.Large;
    }
}
