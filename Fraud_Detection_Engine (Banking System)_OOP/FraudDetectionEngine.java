class User {
    private String userId;
    private String userName;
    private double accountBalance;

    public User(String userId, String userName, double accountBalance) {
        this.userId = userId;
        this.userName = userName;
        this.accountBalance = accountBalance;
    }

    public boolean validateUser() {
        return userId.startsWith("USR-") && accountBalance >= 0;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public double getAccountBalance() {
        return accountBalance;
    }
}

class Transaction {
    private String transactionId;
    private User user;
    private double amount;
    private String transactionType;
    private String location;

    public Transaction(String transactionId, User user, double amount, String transactionType, String location) {
        this.transactionId = transactionId;
        this.user = user;
        this.amount = amount;
        this.transactionType = transactionType;
        this.location = location;
    }

    public boolean validateTransaction() {
        if (!transactionId.startsWith("TXN-") || amount <= 0) {
            return false;
        }

        return transactionType.equalsIgnoreCase("DEPOSIT")
            || transactionType.equalsIgnoreCase("WITHDRAW")
            || transactionType.equalsIgnoreCase("TRANSFER");
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getLocation() {
        return location;
    }

    public User getUser() {
        return user;
    }
}

class RuleEngine {
    public boolean highAmountRule(Transaction txn) {
        return txn.getAmount() > 50000;
    }

    public boolean locationRule(Transaction txn) {
        return !txn.getLocation().equalsIgnoreCase("India");
    }

    public boolean frequencyRule(Transaction[] txns) {
        return txns.length > 3;
    }
}

class FraudDetector {
    private Transaction[] transactions;
    private double fraudScore;
    private String result;

    public FraudDetector(Transaction[] transactions) {
        this.transactions = transactions;
        this.fraudScore = 0.0;
        this.result = "Not Checked";
    }

    public double calculateFraudScore() {
        RuleEngine ruleEngine = new RuleEngine();
        fraudScore = 0;

        boolean frequencySuspicious = ruleEngine.frequencyRule(transactions);

        for (int i = 0; i < transactions.length; i++) {
            Transaction txn = transactions[i];
            User user = txn.getUser();

            if (!user.validateUser() || !txn.validateTransaction()) {
                continue;
            }

            if (ruleEngine.highAmountRule(txn)) {
                fraudScore += 40;
            }

            if (ruleEngine.locationRule(txn)) {
                fraudScore += 30;
            }
        }

        if (frequencySuspicious) {
            fraudScore += 30;
        }

        return fraudScore;
    }

    public void detectFraud() {
        double finalScore = calculateFraudScore();

        if (finalScore >= 70) {
            result = "Fraud Detected";
        } else if (finalScore >= 40) {
            result = "Suspicious Activity";
        } else {
            result = "Normal Transaction";
        }
    }

    public void displayReport() {
        User user = transactions[0].getUser();

        System.out.println("User ID: " + user.getUserId());
        System.out.println("User Name: " + user.getUserName());
        System.out.println("Fraud Score: " + fraudScore);
        System.out.println("Result: " + result);
    }
}

public class FraudDetectionEngine {
    public static void main(String[] args) {
        User user = new User("USR-101", "Rohit", 100000);

        Transaction[] transactions = {
            new Transaction("TXN-1", user, 60000, "TRANSFER", "USA"),
            new Transaction("TXN-2", user, 2000, "WITHDRAW", "India")
        };

        FraudDetector detector = new FraudDetector(transactions);

        detector.detectFraud();
        detector.displayReport();
    }
}