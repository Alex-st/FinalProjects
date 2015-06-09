/**
 * Created by alex on 4/5/15.
 */
public class CargoPlane extends Plane{
    private String name;

    public CargoPlane(String name) {
        super();
        this.name = name;
    }

    public CargoPlane(String name, int capacity, int flyingRange, int fuelConsumption){
        this.name = name;
        this.capacity = capacity;
        this.flyingRange = flyingRange;
        this.fuelConsumption = fuelConsumption;
    }

    public String toString(){
        return (name+": Capacity:"+capacity+", Range:"+flyingRange+", Consumption:"+fuelConsumption);
    }
}
