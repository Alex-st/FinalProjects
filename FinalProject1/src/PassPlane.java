/**
 * Created by alex on 4/5/15.
 */
public class PassPlane extends Plane {
    private String model;

    public PassPlane(String name) {
        super();
        this.model = name;
    }

    public PassPlane(String name, int passengers, int capacity, int flyingRange, int fuelConsumption){
        this.model = name;
        this.passengerNumber = passengers;
        this.capacity = capacity;
        this.flyingRange = flyingRange;
        this.fuelConsumption = fuelConsumption;
    }

    public String toString(){
        return (model+": Capacity:"+capacity+", Range:"+flyingRange+", Consumption:"+fuelConsumption);
    }

}
