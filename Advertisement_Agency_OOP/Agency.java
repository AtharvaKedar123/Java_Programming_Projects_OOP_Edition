abstract class AdAgency {

    protected static String[] adAvailabilityArr = {"Product", "Service", "Marketing", "Brand"};
    protected static int counter = 100;

    protected Client client;
    protected String bookingId;
    protected String adPackage;
    protected boolean clientCollab;
    protected int totalBillAmount;

    public AdAgency(Client client, String adPackage, boolean clientCollab) {
        this.client = client;
        this.adPackage = adPackage;
        this.clientCollab = clientCollab;
    }

    public String getAdPackage() {
        return adPackage;
    }

    public Client getClient() {
        return client;
    }

    public boolean getClientCollab() {
        return clientCollab;
    }

    public int getTotalBillAmount() {
        return totalBillAmount;
    }

    public String getBookingId() {
        return bookingId;
    }

    public static String[] getAdAvailabilityArr() {
        return adAvailabilityArr;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setTotalBillAmount(int totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }

    public void generateBookingId() {
        counter++;
        String prefix = client.getAdRequirement().substring(0, 1).toUpperCase();
        bookingId = prefix + counter;
    }

    public Integer identifyWaveOffPercent() {
        int budget = client.getBudget();

        if (budget <= 200000) {
            return 0;
        } else if (budget > 200000 && budget <= 500000) {
            return 5;
        } else {
            return 10;
        }
    }

    public abstract void calculateTotalBill();
}


class Client {

    private String clientName;
    private String adRequirement;
    private int budget;
    private String paymentType;

    public Client(String clientName, String adRequirement, int budget, String paymentType) {
        this.clientName = clientName;
        this.adRequirement = adRequirement;
        this.budget = budget;
        this.paymentType = paymentType;
    }

    public String getAdRequirement() {
        return adRequirement;
    }

    public int getBudget() {
        return budget;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public boolean validateClient() {
        return clientName != null && clientName.length() >= 3 && budget > 0;
    }

    public Boolean validateClientAdRequirement() {
        for (String req : AdAgency.getAdAvailabilityArr()) {
            if (req.equalsIgnoreCase(adRequirement)) {
                return true;
            }
        }
        return false;
    }
}


class OutdoorAdAgency extends AdAgency {

    private static String[] outdoorAdTypeArr = {"Billboard", "Transit", "Banner"};
    private static int[] outdoorAdTypeCostArr = {250, 170, 150};

    private String outdoorAdType;
    private int displayTimeFrame;
    private int quantity;

    public OutdoorAdAgency(Client client, String adPackage, boolean clientCollab,
                           String outdoorAdType, int displayTimeFrame, int quantity) {
        super(client, adPackage, clientCollab);
        this.outdoorAdType = outdoorAdType;
        this.displayTimeFrame = displayTimeFrame;
        this.quantity = quantity;
    }

    @Override
    public void calculateTotalBill() {

        if (client.validateClient() && client.validateClientAdRequirement()) {

            int costPerAdType = -1;

            for (int i = 0; i < outdoorAdTypeArr.length; i++) {
                if (outdoorAdTypeArr[i].equals(outdoorAdType)) { // case-sensitive
                    costPerAdType = outdoorAdTypeCostArr[i];
                    break;
                }
            }

            if (costPerAdType != -1) {

                generateBookingId();

                int adCost = costPerAdType * quantity * displayTimeFrame;

                if (adPackage.equalsIgnoreCase("Digital")) {
                    adCost += 5000;
                } else if (adPackage.equalsIgnoreCase("Traditional")) {
                    adCost += 2000;
                }

                if (clientCollab && client.getPaymentType().equals("Prepaid")) {
                    int waveOff = identifyWaveOffPercent();
                    adCost = adCost - (adCost * waveOff / 100);
                }

                if (adCost <= client.getBudget()) {
                    setTotalBillAmount(adCost);
                } else {
                    setBookingId("NA");
                    setTotalBillAmount(-1);
                }

            } else {
                setBookingId("NA");
                setTotalBillAmount(-1);
            }

        } else {
            setBookingId("NA");
            setTotalBillAmount(-1);
        }
    }
}


/* Optional Tester */
class Agency {
    public static void main(String[] args) {

        Client client = new Client(
                "Goindia",
                "service",
                500000,
                "Prepaid"
        );

        OutdoorAdAgency agency = new OutdoorAdAgency(
                client,
                "Digital",
                true,
                "Banner",
                6,
                500
        );

        agency.calculateTotalBill();

        System.out.println("BookingId: " + agency.getBookingId());
        System.out.println("TotalBillAmount: " + agency.getTotalBillAmount());
    }
}
