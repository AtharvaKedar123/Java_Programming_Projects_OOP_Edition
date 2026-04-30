class Road {
    private String roadName;
    private int vehicleCount;
    private boolean hasEmergencyVehicle;

    public Road(String roadName, int vehicleCount, boolean hasEmergencyVehicle) {
        this.roadName = roadName;
        this.vehicleCount = vehicleCount;
        this.hasEmergencyVehicle = hasEmergencyVehicle;
    }

    public String getRoadName() {
        return roadName;
    }

    public int getVehicleCount() {
        return vehicleCount;
    }

    public boolean hasEmergencyVehicle() {
        return hasEmergencyVehicle;
    }
}

class TrafficSignal {
    private String signalId;
    private String currentColor;
    private int greenTime;

    public TrafficSignal(String signalId) {
        this.signalId = signalId;
        this.currentColor = "RED";
        this.greenTime = 0;
    }

    public void updateSignalTime(int vehicleCount) {
        currentColor = "GREEN";

        if (vehicleCount > 100) {
            greenTime = 60;
        } else if (vehicleCount >= 50) {
            greenTime = 40;
        } else {
            greenTime = 25;
        }
    }

    public void setEmergencyMode() {
        currentColor = "GREEN";
        greenTime = 90;
    }

    public void displaySignalStatus(Road road) {
        System.out.println("--------------------------------");
        System.out.println("Signal ID          : " + signalId);
        System.out.println("Road Name          : " + road.getRoadName());
        System.out.println("Vehicle Count      : " + road.getVehicleCount());
        System.out.println("Emergency Vehicle  : " + road.hasEmergencyVehicle());
        System.out.println("Signal Color       : " + currentColor);
        System.out.println("Green Time         : " + greenTime + " seconds");
    }
}

class Sensor {
    private Road road;

    public Sensor(Road road) {
        this.road = road;
    }

    public int detectVehicleCount() {
        return road.getVehicleCount();
    }

    public boolean detectEmergencyVehicle() {
        return road.hasEmergencyVehicle();
    }
}

class TrafficController {
    private Road[] roads;
    private TrafficSignal[] signals;

    public TrafficController(Road[] roads, TrafficSignal[] signals) {
        this.roads = roads;
        this.signals = signals;
    }

    public void controlTraffic() {
        for (int i = 0; i < roads.length; i++) {
            Sensor sensor = new Sensor(roads[i]);

            int vehicleCount = sensor.detectVehicleCount();
            boolean emergency = sensor.detectEmergencyVehicle();

            if (emergency) {
                signals[i].setEmergencyMode();
            } else {
                signals[i].updateSignalTime(vehicleCount);
            }

            signals[i].displaySignalStatus(roads[i]);
        }
    }
}

public class SmartTrafficControlSystem {
    public static void main(String[] args) {

        Road[] roads = {
            new Road("Main Road", 120, false),
            new Road("Hospital Road", 35, true),
            new Road("Market Road", 80, false),
            new Road("School Road", 25, false)
        };

        TrafficSignal[] signals = {
            new TrafficSignal("S101"),
            new TrafficSignal("S102"),
            new TrafficSignal("S103"),
            new TrafficSignal("S104")
        };

        TrafficController controller = new TrafficController(roads, signals);
        controller.controlTraffic();
    }
}