package ParkingLot;

/**
 * Created by tianbingleng on 21/10/2017.
 */
public enum VehicleSize {
    Compact(1),
    Large(2);

    private final int size;

    VehicleSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
