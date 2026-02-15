abstract class WaterBill {

    protected Consumer consumer;
    protected int usageInLitres;
    protected double billAmount;
    protected String billId;
    protected static int counter = 8000;

    public WaterBill(Consumer consumer, int usageInLitres) {
        this.consumer = consumer;
        this.usageInLitres = usageInLitres;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillId() {
        return billId;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void generateBillId() {
        counter++;
        billId = consumer.getConsumerName().charAt(0) + String.valueOf(counter);
    }

    public abstract void calculateBillAmount();
}


class Consumer {

    private String consumerName;
    private String connectionId;
    private String areaType;

    public Consumer(String consumerName, String connectionId, String areaType) {
        this.consumerName = consumerName;
        this.connectionId = connectionId;
        this.areaType = areaType;
    }

    public boolean validateConsumerDetails() {
        if (consumerName.length() >= 3 &&
            connectionId.startsWith("WC") &&
            (areaType.equals("Urban") || areaType.equals("Rural"))) {
            return true;
        }
        return false;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public String getAreaType() {
        return areaType;
    }

    public String toString() {
        return consumerName + " " + connectionId + " " + areaType;
    }
}


class DomesticWaterBill extends WaterBill {

    private String supplyType;

    private static String[] supplyTypeAvailable = {"Municipal", "Borewell"};
    private static double[] ratePerLitre = {0.02, 0.03};

    public DomesticWaterBill(Consumer consumer, int usageInLitres, String supplyType) {
        super(consumer, usageInLitres);
        this.supplyType = supplyType;
    }

    public Integer validateSupplyType() {
        for (int i = 0; i < supplyTypeAvailable.length; i++) {
            if (supplyTypeAvailable[i].equals(supplyType)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void calculateBillAmount() {

        boolean validConsumer = consumer.validateConsumerDetails();
        int supplyIndex = validateSupplyType();

        if (validConsumer && supplyIndex != -1 && usageInLitres > 0) {

            double baseAmount = usageInLitres * ratePerLitre[supplyIndex];

            if (consumer.getAreaType().equals("Rural")) {
                baseAmount = baseAmount - (baseAmount * 10 / 100);
            }

            setBillAmount(baseAmount);
            generateBillId();

        } else {
            setBillAmount(-1.0);
            setBillId("NA");
        }
    }

    public String toString() {
        return billId + " " + billAmount;
    }
}


/* Tester class */
class AquaCareTester {

    public static void main(String[] args) {

        Consumer c = new Consumer("Suresh", "WC1023", "Rural");

        DomesticWaterBill bill = new DomesticWaterBill(
                c,
                2000,
                "Municipal"
        );

        bill.calculateBillAmount();

        System.out.println("BillId: " + bill.getBillId());
        System.out.println("BillAmount: " + bill.getBillAmount());
    }
}
