class Ticket {

    private int distance;
    private double fare;

    public Ticket(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public void calculateFare() {
        fare = distance * 5;
    }

    public String toString() {
        return "Distance: " + distance + ", Fare: " + fare;
    }
}


class BusinessClassTicket extends Ticket {

    private String[] services;

    private static String[][] servicesDetailsArr = {
        {"Meal", "ExtraBaggage", "Lounge"},
        {"1500", "2500", "3000"}
    };

    public BusinessClassTicket(int distance, String[] services) {
        super(distance);
        this.services = services;
    }

    public String[] getServices() {
        return services;
    }

    @Override
    public void calculateFare() {

        super.calculateFare();
        double basicAmount = getFare();

        if (services == null) {
            setFare(-1.0);
            return;
        }

        double totalServiceCharge = 0;
        boolean valid = true;

        for (String service : services) {

            boolean found = false;

            for (int i = 0; i < servicesDetailsArr[0].length; i++) {

                if (service.equalsIgnoreCase(servicesDetailsArr[0][i])) {

                    totalServiceCharge +=
                            Double.parseDouble(servicesDetailsArr[1][i]);

                    found = true;
                    break;
                }
            }

            if (!found) {
                valid = false;
                break;
            }
        }

        if (!valid || totalServiceCharge <= 0) {
            setFare(-1.0);
        } else {
            setFare(basicAmount + totalServiceCharge);
        }
    }
}


class EconomyClassTicket extends Ticket {

    private int baggageWeight;

    public EconomyClassTicket(int distance, int baggageWeight) {
        super(distance);
        this.baggageWeight = baggageWeight;
    }

    public int getBaggageWeight() {
        return baggageWeight;
    }

    public boolean validateBaggageWeight() {
        return baggageWeight > 0 && baggageWeight < 30;
    }

    @Override
    public void calculateFare() {

        super.calculateFare();
        double basicAmount = getFare();

        if (!validateBaggageWeight()) {
            setFare(-1.0);
            return;
        }

        double extraCharge = baggageWeight * 200;
        setFare(basicAmount + extraCharge);
    }
}


class Main {
    public static void main(String[] args) {

        // Test Business Class
        String[] services = {"Meal", "Lounge"};
        BusinessClassTicket b = new BusinessClassTicket(1000, services);
        b.calculateFare();
        System.out.println("Business Class Fare: " + b.getFare());

        // Test Economy Class
        EconomyClassTicket e = new EconomyClassTicket(500, 10);
        e.calculateFare();
        System.out.println("Economy Class Fare: " + e.getFare());
    }
}
