class PowerStation {
    private String stationId;
    private String stationName;
    private int totalPowerAvailable;

    public PowerStation(String stationId, String stationName, int totalPowerAvailable) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.totalPowerAvailable = totalPowerAvailable;
    }

    public boolean validatePowerStation() {
        return stationId.startsWith("PS-") && totalPowerAvailable > 0;
    }

    public int getTotalPowerAvailable() {
        return totalPowerAvailable;
    }

    public String getStationName() {
        return stationName;
    }
}

class House {
    private String houseId;
    private String ownerName;
    private int requiredPower;
    private int allocatedPower;

    public House(String houseId, String ownerName, int requiredPower) {
        this.houseId = houseId;
        this.ownerName = ownerName;
        this.requiredPower = requiredPower;
        this.allocatedPower = 0;
    }

    public boolean validateHouseId() {
        return houseId.startsWith("H-") && requiredPower > 0;
    }

    public void setAllocatedPower(int allocatedPower) {
        this.allocatedPower = allocatedPower;
    }

    public int getRequiredPower() {
        return requiredPower;
    }

    public int getAllocatedPower() {
        return allocatedPower;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getHouseId() {
        return houseId;
    }
}

class Grid {
    private String gridId;
    private PowerStation powerStation;
    private House[] houses;
    private int totalDemand;

    public Grid(String gridId, PowerStation powerStation, House[] houses) {
        this.gridId = gridId;
        this.powerStation = powerStation;
        this.houses = houses;
        this.totalDemand = 0;
    }

    public int calculateTotalDemand() {
        totalDemand = 0;

        for (int i = 0; i < houses.length; i++) {
            if (houses[i].validateHouseId()) {
                totalDemand += houses[i].getRequiredPower();
            }
        }

        return totalDemand;
    }

    public boolean isOverloaded() {
        return totalDemand > powerStation.getTotalPowerAvailable();
    }

    public void distributePower() {
        if (!powerStation.validatePowerStation()) {
            for (int i = 0; i < houses.length; i++) {
                houses[i].setAllocatedPower(-1);
            }
            return;
        }

        calculateTotalDemand();

        if (!isOverloaded()) {
            for (int i = 0; i < houses.length; i++) {
                if (houses[i].validateHouseId()) {
                    houses[i].setAllocatedPower(houses[i].getRequiredPower());
                } else {
                    houses[i].setAllocatedPower(-1);
                }
            }
        }
    }

    public House[] getHouses() {
        return houses;
    }

    public PowerStation getPowerStation() {
        return powerStation;
    }

    public int getTotalDemand() {
        return totalDemand;
    }

    public String getGridId() {
        return gridId;
    }
}

class LoadBalancer {
    private Grid grid;

    public LoadBalancer(Grid grid) {
        this.grid = grid;
    }

    public void balanceLoad() {
        grid.distributePower();

        PowerStation powerStation = grid.getPowerStation();
        House[] houses = grid.getHouses();

        if (!powerStation.validatePowerStation()) {
            return;
        }

        if (grid.isOverloaded()) {
            int validHouseCount = 0;

            for (int i = 0; i < houses.length; i++) {
                if (houses[i].validateHouseId()) {
                    validHouseCount++;
                } else {
                    houses[i].setAllocatedPower(-1);
                }
            }

            int equalPower = powerStation.getTotalPowerAvailable() / validHouseCount;

            for (int i = 0; i < houses.length; i++) {
                if (houses[i].validateHouseId()) {
                    houses[i].setAllocatedPower(equalPower);
                }
            }
        }
    }

    public void displayPowerReport() {
        House[] houses = grid.getHouses();

        System.out.println("Total Demand: " + grid.getTotalDemand());
        System.out.println("Available Power: " + grid.getPowerStation().getTotalPowerAvailable());

        if (grid.isOverloaded()) {
            System.out.println("Grid Status: Overloaded");
        } else {
            System.out.println("Grid Status: Stable");
        }

        System.out.println();
        System.out.println("Power Distribution Report:");

        for (int i = 0; i < houses.length; i++) {
            System.out.println();
            System.out.println("House ID: " + houses[i].getHouseId());
            System.out.println("Owner Name: " + houses[i].getOwnerName());
            System.out.println("Required Power: " + houses[i].getRequiredPower());
            System.out.println("Allocated Power: " + houses[i].getAllocatedPower());

            if (houses[i].getAllocatedPower() == -1) {
                System.out.println("Status: Invalid Details");
            } else if (houses[i].getAllocatedPower() == houses[i].getRequiredPower()) {
                System.out.println("Status: Fully Supplied");
            } else {
                System.out.println("Status: Partially Supplied");
            }
        }
    }
}

public class SmartEnergyGridSystem {
    public static void main(String[] args) {
        PowerStation powerStation = new PowerStation(
            "PS-101",
            "Central Power Station",
            300
        );

        House[] houses = {
            new House("H-101", "Amit", 120),
            new House("H-102", "Riya", 150),
            new House("H-103", "Karan", 180)
        };

        Grid grid = new Grid("GRID-01", powerStation, houses);

        LoadBalancer loadBalancer = new LoadBalancer(grid);

        loadBalancer.balanceLoad();
        loadBalancer.displayPowerReport();
    }
}