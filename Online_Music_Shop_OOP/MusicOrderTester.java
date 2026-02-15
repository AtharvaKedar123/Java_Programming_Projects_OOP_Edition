abstract class DigitalItemOrder {

    protected Customer customer;
    protected String itemName;
    protected double orderPrice;
    protected String orderId;
    protected static int counter = 1000;

    public DigitalItemOrder(Customer customer, String itemName) {
        this.customer = customer;
        this.itemName = itemName;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void generateOrderId() {
        counter++;
        orderId = itemName.charAt(0) + String.valueOf(counter);
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
        if (custName.length() >= 4 && age > 18 &&
            (emailId.endsWith(".com") || emailId.endsWith(".edu"))) {
            return true;
        }
        return false;
    }

    public String getCustName() {
        return custName;
    }

    public String getEmailId() {
        return emailId;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return custName + " " + emailId + " " + age;
    }
}


class MusicOrder extends DigitalItemOrder {

    private String musicFormat;
    private int bitRate;
    private int durationInSec;

    private static String[] musicFormatAvailable = {"mp3", "wav", "flac"};
    private static int[] bitRateAvailable = {192, 128, 256};
    private static double[] bitRateCost = {15.0, 10.0, 20.0};

    public MusicOrder(Customer customer, String itemName,
                      String musicFormat, int bitRate, int durationInSec) {
        super(customer, itemName);
        this.musicFormat = musicFormat;
        this.bitRate = bitRate;
        this.durationInSec = durationInSec;
    }

    public Integer validateBitRate() {
        for (int i = 0; i < bitRateAvailable.length; i++) {
            if (bitRateAvailable[i] == bitRate) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void calculateOrderPrice() {

        boolean validCustomer = customer.validateCustomerDetails();
        boolean validFormat = false;

        for (String format : musicFormatAvailable) {
            if (format.equalsIgnoreCase(musicFormat)) {
                validFormat = true;
                break;
            }
        }

        if (validCustomer && validFormat) {

            int index = validateBitRate();

            if (index != -1 && durationInSec > 0) {

                double baseCost = bitRateCost[index];

                int minutes = durationInSec / 60;
                baseCost = baseCost + (minutes * 3);

                baseCost = baseCost + (baseCost * 5 / 100);

                setOrderPrice(baseCost);
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

    public String toString() {
        return getOrderId() + " " + getOrderPrice();
    }
}


/* Renamed Tester Class */
class MusicOrderTester {

    public static void main(String[] args) {

        Customer c = new Customer("Bobby", "bob@xyz.com", 19);

        MusicOrder m = new MusicOrder(
                c,
                "Unforgiven",
                "FLAC",
                256,
                350
        );

        m.calculateOrderPrice();

        System.out.println("OrderId: " + m.getOrderId());
        System.out.println("OrderPrice: " + m.getOrderPrice());
    }
}
