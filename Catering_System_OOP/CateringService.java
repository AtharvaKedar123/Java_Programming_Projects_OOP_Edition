abstract class Caterer {

    protected static int counter = 1000;

    protected BookCatering bookCatering;
    protected int noOfPlates;
    protected double billAmount;
    protected String bookingID;

    public Caterer(BookCatering bookCatering, int noOfPlates) {
        this.bookCatering = bookCatering;
        this.noOfPlates = noOfPlates;
    }

    public BookCatering getBookCatering() {
        return bookCatering;
    }

    public int getNoOfPlates() {
        return noOfPlates;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public int identifyDiscountPercentage(double cost) {
        if (cost > 100000) {
            return 15;
        } else if (cost >= 50000 && cost <= 100000) {
            return 10;
        } else {
            return 5;
        }
    }

    public void generateBookingID() {
        counter++;
        bookingID = "C" + counter;
    }

    public int findCostPerPlate() {
        if (noOfPlates < 25) {
            return -1;
        } else if (noOfPlates >= 25 && noOfPlates <= 100) {
            return 300;
        } else if (noOfPlates >= 101 && noOfPlates <= 200) {
            return 200;
        } else {
            return 150;
        }
    }

    public abstract void calculateBillAmount();
}


class BookCatering {

    public static String[] availableCuisineArr = {"indian", "italian", "chinese"};

    private String customerName;
    private String cuisine;

    public BookCatering(String customerName, String cuisine) {
        this.customerName = customerName;
        this.cuisine = cuisine;
    }

    public boolean validateCustomer() {
        return customerName != null && customerName.length() >= 3;
    }

    public boolean validateCuisine() {
        for (String c : availableCuisineArr) {
            if (c.equalsIgnoreCase(cuisine)) {
                return true;
            }
        }
        return false;
    }
}


class WeddingCaterer extends Caterer {

    private int noOfDays;
    private String serviceType;

    public WeddingCaterer(BookCatering bookCatering, int noOfPlates,
                           int noOfDays, String serviceType) {
        super(bookCatering, noOfPlates);
        this.noOfDays = noOfDays;
        this.serviceType = serviceType;
    }

    @Override
    public void calculateBillAmount() {

        boolean isCustomerValid = getBookCatering().validateCustomer();
        boolean isCuisineValid = getBookCatering().validateCuisine();
        int costPerPlate = findCostPerPlate();

        if (isCustomerValid && isCuisineValid && noOfDays >= 1 && costPerPlate != -1) {

            generateBookingID();

            double basicCost = costPerPlate * getNoOfPlates();

            int discount = identifyDiscountPercentage(basicCost);
            basicCost = basicCost - (basicCost * discount / 100);

            if (serviceType.equalsIgnoreCase("Buffet")) {
                basicCost += 5000;
            }

            setBillAmount(basicCost * noOfDays);

        } else {
            setBookingID("-1");
            setBillAmount(-1.0);
        }
    }
}



class CateringService {

    public static void main(String[] args) {

        BookCatering bc = new BookCatering("Rahul", "Indian");
        WeddingCaterer wc = new WeddingCaterer(bc, 280, 2, "Buffet");

        wc.calculateBillAmount();

        System.out.println("Booking ID: " + wc.getBookingID());
        System.out.println("Bill Amount: " + wc.getBillAmount());
    }
}
