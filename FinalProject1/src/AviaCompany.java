import java.util.*;

/**
 * Created by alex on 4/5/15.
 */
public class AviaCompany {
    private List<Plane> planes;

    public AviaCompany() {
        planes = new ArrayList<Plane>();
    }

    public void addPlane(){
        System.out.println("Input your plane");
        System.out.println("Pass - 1; Cargo -2");
        Scanner in = new Scanner(System.in);
        int type = in.nextInt();
        in.nextLine();

        System.out.println("Model or name:");
        String nm = in.nextLine();

        System.out.println("Capacity:");
        int capas = in.nextInt();

        System.out.println("Range:");
        int rng = in.nextInt();

        System.out.println("Fuel consumption:");
        int cnspt = in.nextInt();

        int pas = 0;

        if (type == 1) {
            System.out.println("Passengers number:");
            pas = in.nextInt();
        }

        switch (type){
            case 1: planes.add(new PassPlane(nm, pas, capas, rng, cnspt));
                    break;
            case 2: planes.add(new CargoPlane(nm, capas, rng, cnspt));
                    break;
            default:
                System.out.println("Sorry, we haven't such type of planes");
        }
    }

    public int commonNumberOfPassengers(){
        int sum = 0;

        for (Plane i:planes) {
            sum+=i.getPassengerNumber();
        }

        return sum;
    }

    public int commonCapacity(){
        int sum = 0;

        for (Plane i:planes) {
            sum+=i.getCapacity();
        }

        return sum;
    }

    public void getFuelConsumptionRange(int min, int max) {
        List<Plane> tmp = new ArrayList<Plane>();

        for (Plane i:planes) {
            if (i.fuelConsumption > min && i.fuelConsumption < max)
            tmp.add(i);
        }

        for (Plane j:tmp)
            System.out.println(j.toString());
    }

    public void sortPlanesByCapacity(){
        Collections.sort(planes, Plane.BY_CAPACITY);
        for (Plane i : planes)
            System.out.println(i.toString());
    }

    public void showAllPlanes() {
        for (Plane i : planes)
            System.out.println(i.toString());
    }

    public static void main(String[] args) {
        AviaCompany test = new AviaCompany();
//        test.addPlane();
//        System.out.println("---------------------");

        test.planes.add(new PassPlane("PassPlane1", 200, 1000, 5000, 500));
        test.planes.add(new CargoPlane("CargoPlane1", 5000, 10000, 700));
        test.planes.add(new PassPlane("PassPlane2", 250, 1500, 7000, 600));
        test.planes.add(new CargoPlane("CargoPlane2", 5500, 8000, 650));

//        System.out.println(test.commonCapacity());
//        System.out.println(test.commonNumberOfPassengers());
        //test.getFuelConsumptionRange(550, 651);
        test.sortPlanesByCapacity();

      //  test.showAllPlanes();
    }
}
