public class PremiumBooking extends TicketBooking {

    private int premiumCharge = 50;

    public PremiumBooking(Customer customer,
                          String movieType,
                          int noOfTickets) {
        super(customer, movieType, noOfTickets);
    }

    @Override
    public double calculateFinalAmount() {
        int price = MovieDetails.identifyTicketPrice(movieType);
        if (price == -1) {
            return -1;
        }

        double baseAmount = price * noOfTickets;
        baseAmount += premiumCharge;
        double totalAmount = baseAmount * 1.08;

        double discountedAmount = customer.calculateDiscount(totalAmount);
        if (discountedAmount == -1) {
            return totalAmount;
        }
        return discountedAmount;
    }
}
