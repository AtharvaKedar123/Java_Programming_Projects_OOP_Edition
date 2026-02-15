import java.util.Arrays;

class VehicleForRent {
    private String vehicleType;
    private float distance;
    private float baseCost;

    public VehicleForRent(String vehicleType, float distance) {
        this.vehicleType = vehicleType;
        this.distance = distance;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public float getDistance() {
        return distance;
    }

    public float getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(float baseCost) {
        this.baseCost = baseCost;
    }

    public void calculateTotalCost() {
        if(vehicleType.equalsIgnoreCase("Car")) {
            baseCost = distance * 20;
        } else if(vehicleType.equalsIgnoreCase("Bike")) {
            baseCost = distance * 10;
        } else {
            baseCost = -1;
        }
    }

    @Override
    public String toString() {
        return "VehicleType: " + vehicleType + ", Distance: " + distance + ", Total Cost: " + baseCost;
    }
}

class CarForRent extends VehicleForRent {
    private int seatingCapacity;
    private String[] services;
    private static String[][] servicesDetailsArr = {
        {"GPS", "Extra Driver", "Insurance"},
        {"500", "1000", "1500"}
    };

    public CarForRent(String vehicleType, float distance, int seatingCapacity, String[] services) {
        super(vehicleType, distance);
        this.seatingCapacity = seatingCapacity;
        this.services = services;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public boolean validateSeatingCapacity() {
        return seatingCapacity >= 2 && seatingCapacity <= 7;
    }

    @Override
    public void calculateTotalCost() {
        super.calculateTotalCost();

        if (!validateSeatingCapacity()) {
            setBaseCost(-1);
            return;
        }

        float totalServiceCost = 0;
        for (String service : services) {
            for (int i = 0; i < servicesDetailsArr[0].length; i++) {
                if (servicesDetailsArr[0][i].equalsIgnoreCase(service)) {
                    totalServiceCost += Float.parseFloat(servicesDetailsArr[1][i]);
                    break;
                }
            }
        }

        setBaseCost(getBaseCost() + totalServiceCost);
    }

    @Override
    public String toString() {
        return super.toString() + ", SeatingCapacity: " + seatingCapacity + ", Services: " + Arrays.toString(services);
    }
}

class BikeForRent extends VehicleForRent {
    private int engineCapacity;
    private String[] services;
    private static String[][] servicesDetailsArr = {
        {"GPS", "Extra Driver", "Insurance"},
        {"500", "1000", "1500"}
    };

    public BikeForRent(String vehicleType, float distance, int engineCapacity, String[] services) {
        super(vehicleType, distance);
        this.engineCapacity = engineCapacity;
        this.services = services;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public boolean validateEngineCapacity() {
        return engineCapacity >= 100 && engineCapacity <= 500;
    }

    @Override
    public void calculateTotalCost() {
        super.calculateTotalCost();

        if (!validateEngineCapacity()) {
            setBaseCost(-1);
            return;
        }

        float totalServiceCost = 0;
        for (String service : services) {
            for (int i = 0; i < servicesDetailsArr[0].length; i++) {
                if (servicesDetailsArr[0][i].equalsIgnoreCase(service)) {
                    totalServiceCost += Float.parseFloat(servicesDetailsArr[1][i]);
                    break;
                }
            }
        }

        setBaseCost(getBaseCost() + totalServiceCost);
    }

    @Override
    public String toString() {
        return super.toString() + ", EngineCapacity: " + engineCapacity + ", Services: " + Arrays.toString(services);
    }
}

public class Main {
    public static void main(String[] args) {
        CarForRent car = new CarForRent("Car", 100, 5, new String[]{"GPS", "Insurance"});
        car.calculateTotalCost();
        System.out.println(car);

        BikeForRent bike = new BikeForRent("Bike", 50, 150, new String[]{"GPS"});
        bike.calculateTotalCost();
        System.out.println(bike);

        CarForRent invalidCar = new CarForRent("Car", 100, 8, new String[]{"GPS"});
        invalidCar.calculateTotalCost();
        System.out.println(invalidCar);

        BikeForRent invalidBike = new BikeForRent("Bike", 50, 50, new String[]{"Insurance"});
        invalidBike.calculateTotalCost();
        System.out.println(invalidBike);
    }
}
