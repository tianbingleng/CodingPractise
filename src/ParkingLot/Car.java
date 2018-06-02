package ParkingLot;

/**
 * Created by tianbingleng on 21/10/2017.
 */
public class Car extends Vehicle {
    @Override
    public VehicleSize getSize() {
        return VehicleSize.Compact;
    }
}
