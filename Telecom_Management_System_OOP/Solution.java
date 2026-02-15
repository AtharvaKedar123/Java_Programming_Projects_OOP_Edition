// Base Class
class SmartPlan {
    private int dataLimit;
    private double monthlyBill;

    public SmartPlan(int dataLimit) {
        this.dataLimit = dataLimit;
    }

    public int getDataLimit() {
        return dataLimit;
    }

    public double getMonthlyBill() {
        return monthlyBill;
    }

    public void setMonthlyBill(double monthlyBill) {
        this.monthlyBill = monthlyBill;
    }

    public void calculateMonthlyBill() {
        // Basic calculation: Limit * 15.0
        this.monthlyBill = (double) this.dataLimit * 15.0;
    }

    @Override
    public String toString() {
        return "Data Limit: " + dataLimit + ", Total Bill: " + monthlyBill;
    }
}

// Subclass 1: Array-based lookup logic
class ValueAddedPlan extends SmartPlan {
    private String[] services;
    // Static 2D Array for service charges
    private static String[][] serviceRatesArr = {
        {"Streaming", "International", "Cloud"},
        {"400", "800", "200"}
    };

    public ValueAddedPlan(int dataLimit, String[] services) {
        super(dataLimit);
        this.services = services;
    }

    @Override
    public void calculateMonthlyBill() {
        // Step 1: Invoke parent calculation
        super.calculateMonthlyBill();
        double basicBill = super.getMonthlyBill();

        // Step 2: Validation for null array
        if (this.services == null) {
            super.setMonthlyBill(-1.0);
            return;
        }

        double totalServiceCharge = 0.0;

        // Step 3: Iterate and find charges (Case-Insensitive)
        for (String service : this.services) {
            boolean found = false;
            for (int i = 0; i < serviceRatesArr[0].length; i++) {
                if (serviceRatesArr[0][i].equalsIgnoreCase(service)) {
                    totalServiceCharge += Double.parseDouble(serviceRatesArr[1][i]);
                    found = true;
                    break;
                }
            }
            // Step 4: If any single service is not found, set bill to -1.0
            if (!found) {
                super.setMonthlyBill(-1.0);
                return;
            }
        }

        // Step 5: Final sum
        if (totalServiceCharge > 0 || this.services.length == 0) {
            super.setMonthlyBill(basicBill + totalServiceCharge);
        } else {
            super.setMonthlyBill(-1.0);
        }
    }
}

// Subclass 2: Character-based lookup logic
class DeviceBundledPlan extends SmartPlan {
    private char deviceType;

    public DeviceBundledPlan(int dataLimit, char deviceType) {
        super(dataLimit);
        this.deviceType = deviceType;
    }

    public int identifyDeviceSubsidy() {
        // Case-insensitive check for char
        char upperType = Character.toUpperCase(this.deviceType);
        switch (upperType) {
            case 'M': return 100;
            case 'T': return 150;
            case 'R': return 50;
            default: return -1;
        }
    }

    @Override
    public void calculateMonthlyBill() {
        // Step 1: Invoke parent calculation
        super.calculateMonthlyBill();
        double basicBill = super.getMonthlyBill();

        // Step 2: Get subsidy
        int subsidy = identifyDeviceSubsidy();

        // Step 3: Logic for invalid or valid types
        if (subsidy == -1) {
            super.setMonthlyBill(-1.0);
        } else {
            double finalBill = basicBill - subsidy;
            // Ensure bill doesn't go below 0
            super.setMonthlyBill(finalBill < 0 ? 0.0 : finalBill);
        }
    }
}

// Main Class for Testing
public class Solution {
    public static void main(String[] args) {
        // Test Case 1: ValueAddedPlan - Valid
        String[] myServices = {"Cloud", "streaming"}; // Case variations
        ValueAddedPlan v1 = new ValueAddedPlan(20, myServices);
        v1.calculateMonthlyBill();
        System.out.println("ValueAdded (Valid): " + v1.getMonthlyBill()); // Expected: (20*15) + 200 + 400 = 900.0

        // Test Case 2: ValueAddedPlan - Invalid Service
        String[] invalidServices = {"Gaming"};
        ValueAddedPlan v2 = new ValueAddedPlan(20, invalidServices);
        v2.calculateMonthlyBill();
        System.out.println("ValueAdded (Invalid): " + v2.getMonthlyBill()); // Expected: -1.0

        // Test Case 3: DeviceBundledPlan - Valid (Tablet)
        DeviceBundledPlan d1 = new DeviceBundledPlan(10, 't'); // Lowercase check
        d1.calculateMonthlyBill();
        System.out.println("DeviceBundled (Tablet): " + d1.getMonthlyBill()); // Expected: (10*15) - 150 = 0.0

        // Test Case 4: DeviceBundledPlan - Invalid Type
        DeviceBundledPlan d2 = new DeviceBundledPlan(10, 'X');
        d2.calculateMonthlyBill();
        System.out.println("DeviceBundled (Invalid): " + d2.getMonthlyBill()); // Expected: -1.0
    }
}