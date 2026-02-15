class RoomBooking {

    private int roomSize;
    private double advanceAmount;

    public RoomBooking(int roomSize) {
        this.roomSize = roomSize;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public void calculateAdvanceAmount() {
        // Basic calculation
        advanceAmount = roomSize * 100;
    }

    public String toString() {
        return "Room Size: " + roomSize +
               "\nAdvance Amount: " + advanceAmount;
    }
}


class LuxuryRoomBooking extends RoomBooking {

    private String[] facilities;

    private static String[][] facilitiesDetailsArr = {
            {"Pool", "Spa", "Gym"},
            {"2000", "3000", "1500"}
    };

    public LuxuryRoomBooking(int roomSize, String[] facilities) {
        super(roomSize);
        this.facilities = facilities;
    }

    public String[] getFacilities() {
        return facilities;
    }

    @Override
    public void calculateAdvanceAmount() {

        super.calculateAdvanceAmount(); // basic amount
        double basicAmount = getAdvanceAmount();

        if (facilities == null || facilities.length == 0) {
            setAdvanceAmount(-1.0);
            return;
        }

        double facilityCharge = 0;
        boolean validFacilityFound = false;

        for (String facility : facilities) {

            boolean found = false;

            for (int i = 0; i < facilitiesDetailsArr[0].length; i++) {

                if (facility.equalsIgnoreCase(facilitiesDetailsArr[0][i])) {
                    facilityCharge += Double.parseDouble(facilitiesDetailsArr[1][i]);
                    found = true;
                    validFacilityFound = true;
                    break;
                }
            }

            if (!found) {
                setAdvanceAmount(-1.0);
                return;
            }
        }

        if (!validFacilityFound) {
            setAdvanceAmount(-1.0);
        } else {
            setAdvanceAmount(basicAmount + facilityCharge);
        }
    }

    @Override
    public String toString() {
        return "Luxury Room Booking\n" + super.toString();
    }
}


class StandardRoomBooking extends RoomBooking {

    private char bedType;

    public StandardRoomBooking(int roomSize, char bedType) {
        super(roomSize);
        this.bedType = bedType;
    }

    public char getBedType() {
        return bedType;
    }

    public int identifyBedCharge() {

        char type = Character.toUpperCase(bedType);

        switch (type) {
            case 'S': return 2000;
            case 'D': return 4000;
            case 'K': return 6000;
            default: return -1;
        }
    }

    @Override
    public void calculateAdvanceAmount() {

        super.calculateAdvanceAmount();
        double basicAmount = getAdvanceAmount();

        int bedCharge = identifyBedCharge();

        if (bedCharge == -1) {
            setAdvanceAmount(-1.0);
        } else {
            setAdvanceAmount(basicAmount + bedCharge);
        }
    }

    @Override
    public String toString() {
        return "Standard Room Booking\n" + super.toString();
    }
}


class Main {

    public static void main(String[] args) {

        // ----- Luxury Example -----
        String[] facilities = {"Pool", "Gym"};
        LuxuryRoomBooking luxury =
                new LuxuryRoomBooking(200, facilities);

        luxury.calculateAdvanceAmount();
        System.out.println(luxury);
        System.out.println("--------------------");

        // ----- Standard Example -----
        StandardRoomBooking standard =
                new StandardRoomBooking(150, 'D');

        standard.calculateAdvanceAmount();
        System.out.println(standard);
    }
}
