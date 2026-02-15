// Single file solution for Practice Problem 2: Gym Management System

class MemberDetails {

    // Static arrays
    public static String[] validPrefixArr = {"GYM-P", "GYM-G", "GYM-S"};
    public static String[] planTypeArr = {"Basic", "Pro", "Elite"};
    public static int[] basePriceArr = {1200, 2500, 4500};

    private String memberId;
    private String memberName;

    // Constructor
    public MemberDetails(String memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }

    // Getter
    public String getMemberId() {
        return memberId;
    }

    // Method to validate member ID
    public boolean validateMemberId() {
        for (String prefix : validPrefixArr) {
            if (memberId.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }
}

// Abstract class
abstract class Membership {

    protected MemberDetails member;
    protected String planType;
    protected int durationInMonths;

    // Constructor
    public Membership(MemberDetails member, String planType, int durationInMonths) {
        this.member = member;
        this.planType = planType;
        this.durationInMonths = durationInMonths;
    }

    // Get index of plan type
    public int getPlanIndex(String type) {
        for (int i = 0; i < MemberDetails.planTypeArr.length; i++) {
            if (MemberDetails.planTypeArr[i].equalsIgnoreCase(type)) {
                return i;
            }
        }
        return -1;
    }

    // Abstract method
    public abstract double calculateBillAmount();
}

// MonthlyMember class
class MonthlyMember extends Membership {

    private boolean hasPersonalTrainer;
    private float trainerFee;

    // Constructor
    public MonthlyMember(MemberDetails member, String planType,
                         int durationInMonths, boolean hasPersonalTrainer,
                         float trainerFee) {
        super(member, planType, durationInMonths);
        this.hasPersonalTrainer = hasPersonalTrainer;
        this.trainerFee = trainerFee;
    }

    @Override
    public double calculateBillAmount() {

        // Validate member ID
        if (!member.validateMemberId()) {
            return -1;
        }

        // Get plan index
        int index = getPlanIndex(planType);
        if (index == -1) {
            return -1;
        }

        // Base cost
        double baseCost = MemberDetails.basePriceArr[index] * durationInMonths;

        // Add trainer fee if applicable
        if (hasPersonalTrainer) {
            baseCost += trainerFee * durationInMonths;
        }

        // Apply 5% service tax
        return baseCost * 1.05;
    }
}

// YearlyMember class
class YearlyMember extends Membership {

    private float yearlyDiscount;

    // Constructor
    public YearlyMember(MemberDetails member, String planType,
                        int durationInMonths, float yearlyDiscount) {
        super(member, planType, durationInMonths);
        this.yearlyDiscount = yearlyDiscount;
    }

    @Override
    public double calculateBillAmount() {

        // Validate member ID
        if (!member.validateMemberId()) {
            return -1;
        }

        // Get plan index
        int index = getPlanIndex(planType);
        if (index == -1) {
            return -1;
        }

        // Annual cost
        double annualCost = MemberDetails.basePriceArr[index] * 12;

        // Apply discount
        double discountedAmount =
                annualCost - (annualCost * yearlyDiscount / 100);

        // Add admin fee
        return discountedAmount + 200;
    }
}

// Main class
class GymManagementSystem {

    public static void main(String[] args) {

        MemberDetails member =
                new MemberDetails("GYM-P100", "Alice");

        MonthlyMember monthlyMember =
                new MonthlyMember(member, "Pro",
                        2, true, 500);

        double billAmount = monthlyMember.calculateBillAmount();

        System.out.println("Total Bill Amount: " + billAmount);
    }
}
