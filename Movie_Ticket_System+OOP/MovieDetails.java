public class MovieDetails {

    public static String[] movieTypeArr = {"Regular", "3D", "IMAX"};
    public static int[] ticketPriceArr = {150, 250, 400};

    public static int identifyTicketPrice(String movieType) {
        for (int i = 0; i < movieTypeArr.length; i++) {
            if (movieTypeArr[i].equalsIgnoreCase(movieType)) {
                return ticketPriceArr[i];
            }
        }
        return -1;
    }
}
