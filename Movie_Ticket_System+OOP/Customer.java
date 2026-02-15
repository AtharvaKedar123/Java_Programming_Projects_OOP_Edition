public class Customer {

    public static String[] memberCustIdArr =
            {"CB101", "CB205", "CB309", "CB450", "CB520"};

    public static int[] memberDiscountArr =
            {5, 10, 15, 20, 25};

    private String custId;
    private String custName;

    public Customer(String custId, String custName) {
        this.custId = custId;
        this.custName = custName;
    }

    public double calculateDiscount(double totalAmount) {
        for (int i = 0; i < memberCustIdArr.length; i++) {
            if (memberCustIdArr[i].equalsIgnoreCase(custId)) {
                double discount = (totalAmount * memberDiscountArr[i]) / 100.0;
                return totalAmount - discount;
            }
        }
        return -1;
    }
}
