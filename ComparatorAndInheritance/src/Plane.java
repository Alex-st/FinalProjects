import java.util.Comparator;

/**
 * Created by alex on 4/5/15.
 */
public class Plane {
    protected int passengerNumber;
    protected int capacity;
    protected int flyingRange;
    protected int fuelConsumption;

    public static final Comparator<Plane> BY_CAPACITY = new ByCapacity();

    private static class ByCapacity implements Comparator<Plane> {
         public int compare(Plane one, Plane two){
            return one.capacity - two.capacity;
        }
    }

     public Plane(){
        passengerNumber = 0;
        capacity = 0;
        flyingRange = 0;
        fuelConsumption = 0;
    }

    public int getCapacity() {
        return capacity;
    }
    public int getPassengerNumber() {
        return passengerNumber;
    }

}
