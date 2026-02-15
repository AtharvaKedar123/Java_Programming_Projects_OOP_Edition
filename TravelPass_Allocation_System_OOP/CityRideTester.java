abstract class TravelPass {

    protected Commuter commuter;
    protected String routeName;
    protected double passCost;
    protected String passId;
    protected static int counter = 5000;

    public TravelPass(Commuter commuter, String routeName) {
        this.commuter = commuter;
        this.routeName = routeName;
    }

    public void setPassId(String passId) {
        this.passId = passId;
    }

    public void setPassCost(double passCost) {
        this.passCost = passCost;
    }

    public String getRouteName() {
        return routeName;
    }

    public Commuter getCommuter() {
        return commuter;
    }

    public String getPassId() {
        return passId;
    }

    public double getPassCost() {
        return passCost;
    }

    public void generatePassId() {
        counter++;
        passId = routeName.charAt(0) + String.valueOf(counter);
    }

    public abstract void calculatePassCost();
}


class Commuter {

    private String commuterName;
    private String idNumber;
    private int age;

    public Commuter(String commuterName, String idNumber, int age) {
        this.commuterName = commuterName;
        this.idNumber = idNumber;
        this.age = age;
    }

    public boolean validateCommuterDetails() {
        if (commuterName.length() >= 3 &&
            age >= 18 &&
            idNumber.startsWith("ID")) {
            return true;
        }
        return false;
    }

    public String getCommuterName() {
        return commuterName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return commuterName + " " + idNumber + " " + age;
    }
}


class MonthlyBusPass extends TravelPass {

    private String travelZone;
    private int distanceInKm;
    private int numberOfDays;

    private static String[] travelZoneAvailable = {"ZoneA", "ZoneB", "ZoneC"};
    private static double[] zoneRatePerKm = {1.5, 2.0, 2.5};

    public MonthlyBusPass(Commuter commuter, String routeName,
                           String travelZone, int distanceInKm, int numberOfDays) {
        super(commuter, routeName);
        this.travelZone = travelZone;
        this.distanceInKm = distanceInKm;
        this.numberOfDays = numberOfDays;
    }

    public Integer validateTravelZone() {
        for (int i = 0; i < travelZoneAvailable.length; i++) {
            if (travelZoneAvailable[i].equals(travelZone)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void calculatePassCost() {

        boolean validCommuter = commuter.validateCommuterDetails();
        int zoneIndex = validateTravelZone();

        if (validCommuter && zoneIndex != -1 &&
            distanceInKm > 0 && numberOfDays > 0) {

            double baseCost =
                    distanceInKm * zoneRatePerKm[zoneIndex] * numberOfDays;

            if (commuter.getAge() >= 60) {
                baseCost = baseCost - (baseCost * 20 / 100);
            }

            setPassCost(baseCost);
            generatePassId();

        } else {
            setPassCost(-1.0);
            setPassId("NA");
        }
    }

    public String toString() {
        return passId + " " + passCost;
    }
}


/* Tester class */
class CityRideTester {

    public static void main(String[] args) {

        Commuter c = new Commuter("Ravi", "ID4598", 62);

        MonthlyBusPass pass = new MonthlyBusPass(
                c,
                "CentralLine",
                "ZoneB",
                10,
                30
        );

        pass.calculatePassCost();

        System.out.println("PassId: " + pass.getPassId());
        System.out.println("PassCost: " + pass.getPassCost());
    }
}
