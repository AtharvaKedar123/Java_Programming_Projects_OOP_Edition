abstract class DigitalContentOrder {

    protected Customer customer;
    protected String contentName;
    protected double orderPrice;
    protected String orderId;
    protected static int counter = 2000;

    public DigitalContentOrder(Customer customer, String contentName) {
        this.customer = customer;
        this.contentName = contentName;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void generateOrderId() {
        counter++;
        orderId = contentName.charAt(0) + String.valueOf(counter);
    }

    public abstract void calculateOrderPrice();
}


class Customer {

    private String custName;
    private String emailId;
    private int age;

    public Customer(String custName, String emailId, int age) {
        this.custName = custName;
        this.emailId = emailId;
        this.age = age;
    }

    public boolean validateCustomerDetails() {
        if (custName.length() >= 5 &&
            age >= 21 &&
            (emailId.endsWith(".com") || emailId.endsWith(".org"))) {
            return true;
        }
        return false;
    }
}


class VideoOrder extends DigitalContentOrder {

    private String videoQuality;
    private int resolution;
    private int durationInSec;

    private static String[] videoQualityAvailable = {"HD", "FullHD", "4K"};
    private static int[] resolutionAvailable = {720, 1080, 2160};
    private static double[] resolutionCost = {12.0, 18.0, 30.0};

    public VideoOrder(Customer customer, String contentName,
                      String videoQuality, int resolution, int durationInSec) {
        super(customer, contentName);
        this.videoQuality = videoQuality;
        this.resolution = resolution;
        this.durationInSec = durationInSec;
    }

    public Integer validateResolution() {
        for (int i = 0; i < resolutionAvailable.length; i++) {
            if (resolutionAvailable[i] == resolution) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void calculateOrderPrice() {

        boolean validCustomer = customer.validateCustomerDetails();
        boolean validQuality = false;

        for (String q : videoQualityAvailable) {
            if (q.equalsIgnoreCase(videoQuality)) {
                validQuality = true;
                break;
            }
        }

        if (validCustomer && validQuality) {

            int index = validateResolution();

            if (index != -1 && durationInSec > 0) {

                // Step 1: Base cost from resolution
                double baseCost = resolutionCost[index]; // 30.0

                // Step 2: Add per-minute cost (integer division)
                int minutes = durationInSec / 60; // 420 / 60 = 7
                baseCost = baseCost + (minutes * 4); // 30 + 28 = 58

                // Step 3: Add 8% service tax
                baseCost = baseCost + (baseCost * 8 / 100); // 58 + 4.64

                setOrderPrice(baseCost); // 62.64
                generateOrderId();

            } else {
                setOrderPrice(-1.0);
                setOrderId("NA");
            }

        } else {
            setOrderPrice(-1.0);
            setOrderId("NA");
        }
    }
}


/* Tester class */
class VideoOrderTester {

    public static void main(String[] args) {

        Customer c = new Customer("Michael", "mike@abc.com", 23);

        VideoOrder v = new VideoOrder(
                c,
                "Avatar",
                "4k",
                2160,
                420
        );

        v.calculateOrderPrice();

        System.out.println("OrderId: " + v.getOrderId());
        System.out.println("OrderPrice: " + v.getOrderPrice());
    }
}
