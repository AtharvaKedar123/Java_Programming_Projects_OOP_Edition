public class MovieTicketApp {

    public static void main(String[] args) {

        Customer customer = new Customer("CB309", "Amit");

        TicketBooking booking = new PremiumBooking(customer, "IMAX", 2);

        double finalAmount = booking.calculateFinalAmount();

        System.out.printf("Final Ticket Amount: %.1f", finalAmount);
    }
}
