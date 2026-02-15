public abstract class TicketBooking {

    protected Customer customer;
    protected String movieType;
    protected int noOfTickets;

    public TicketBooking(Customer customer,
                         String movieType,
                         int noOfTickets) {
        this.customer = customer;
        this.movieType = movieType;
        this.noOfTickets = noOfTickets;
    }

    public abstract double calculateFinalAmount();
}
