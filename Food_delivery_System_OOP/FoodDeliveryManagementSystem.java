// Single file solution for Practice Problem: Food Delivery Management System

class CustomerDetails {

    // Static arrays (shared data)
    public static String[] validPrefixArr = {"FD-C", "FD-P", "FD-X"};
    public static String[] foodTypeArr = {"Veg", "NonVeg", "Combo"};
    public static int[] basePriceArr = {150, 250, 400};

    private String customerId;
    private String customerName;

    // Constructor
    public CustomerDetails(String customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    // Validate customer ID
    public boolean validateCustomerId() {
        for (String prefix : validPrefixArr) {
            if (customerId.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }
}

// Abstract Order class
abstract class Order {

    protected CustomerDetails customer;
    protected String foodType;
    protected int quantity;

    // Constructor
    public Order(CustomerDetails customer, String foodType, int quantity) {
        this.customer = customer;
        this.foodType = foodType;
        this.quantity = quantity;
    }

    // Get index of food type
    public int getFoodIndex(String type) {
        for (int i = 0; i < CustomerDetails.foodTypeArr.length; i++) {
            if (CustomerDetails.foodTypeArr[i].equalsIgnoreCase(type)) {
                return i;
            }
        }
        return -1;
    }

    // Abstract method
    public abstract double calculateBillAmount();
}

// RegularOrder class
class RegularOrder extends Order {

    private boolean isExpressDelivery;
    private float deliveryCharge;

    // Constructor
    public RegularOrder(CustomerDetails customer, String foodType, int quantity,
                        boolean isExpressDelivery, float deliveryCharge) {
        super(customer, foodType, quantity);
        this.isExpressDelivery = isExpressDelivery;
        this.deliveryCharge = deliveryCharge;
    }

    @Override
    public double calculateBillAmount() {

        // Step 1: Validate customer ID
        if (!customer.validateCustomerId()) {
            return -1;
        }

        // Step 2: Get food index
        int index = getFoodIndex(foodType);
        if (index == -1) {
            return -1;
        }

        // Step 3: Calculate base cost
        double baseCost = CustomerDetails.basePriceArr[index] * quantity;

        // Step 4: Add delivery charge if express delivery is selected
        if (isExpressDelivery) {
            baseCost += deliveryCharge;
        }

        // Step 5: Apply 5% service tax
        return baseCost * 1.05;
    }
}

// PremiumOrder class
class PremiumOrder extends Order {

    private float festivalDiscount;

    // Constructor
    public PremiumOrder(CustomerDetails customer, String foodType,
                        int quantity, float festivalDiscount) {
        super(customer, foodType, quantity);
        this.festivalDiscount = festivalDiscount;
    }

    @Override
    public double calculateBillAmount() {

        // Step 1: Validate customer ID
        if (!customer.validateCustomerId()) {
            return -1;
        }

        // Step 2: Get food index
        int index = getFoodIndex(foodType);
        if (index == -1) {
            return -1;
        }

        // Step 3: Calculate base cost
        double baseCost = CustomerDetails.basePriceArr[index] * quantity;

        // Step 4: Apply festival discount
        double discountedAmount =
                baseCost - (baseCost * festivalDiscount / 100);

        // Step 5: Add premium packaging charge
        return discountedAmount + 50;
    }
}

// Main class
class FoodDeliveryManagementSystem {

    public static void main(String[] args) {

        CustomerDetails customer =
                new CustomerDetails("FD-C101", "Rahul");

        RegularOrder order =
                new RegularOrder(customer, "Combo",
                        2, true, 40);

        double billAmount = order.calculateBillAmount();

        System.out.println("Total Bill Amount: " + billAmount);
    }
}
