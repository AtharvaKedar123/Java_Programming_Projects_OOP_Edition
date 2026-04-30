**Practice Problem 7: Fraud Detection Engine (Banking System)**

---

**1. Problem Statement**

Design and implement a **Fraud Detection Engine** using object-oriented programming concepts.

* A banking system wants to monitor transactions and detect fraudulent activities.
* Each user performs multiple transactions such as deposits, withdrawals, and transfers.
* The system must evaluate each transaction based on predefined rules.
* A transaction is marked as suspicious if it violates any fraud detection rule.
* The fraud detection engine must assign a fraud score and classify transactions accordingly by strictly following the rules described in this problem.

---

###### **2. Class Diagram**

```text
                   ----------------------------------
                   |             User               |
                   ----------------------------------
                   | - userId : String              |
                   | - userName : String            |
                   | - accountBalance : double      |
                   ----------------------------------
                   | + validateUser() : boolean     |
                   | + getUserName() : String       |
                   | + getUserId() : String         |
                   | + getAccountBalance(): double  |
                   ----------------------------------
                                  ▲
                                  |  HAS-A (Aggregation)
                                  ◇
                   ----------------------------------
                   |         Transaction            |
                   ----------------------------------
                   | - transactionId : String       |
                   | - user : User                 |
                   | - amount : double             |
                   | - transactionType : String    |
                   | - location : String           |
                   ----------------------------------
                   | + validateTransaction():boolean|
                   | + getAmount() : double        |
                   | + getTransactionType():String |
                   | + getLocation() : String      |
                   ----------------------------------
                                  ▲
                                  |
                                  | uses
                                  |
                   ----------------------------------
                   |          RuleEngine            |
                   ----------------------------------
                   | + highAmountRule(Transaction) |
                   |        : boolean              |
                   | + locationRule(Transaction)   |
                   |        : boolean              |
                   | + frequencyRule(Transaction[])|
                   |        : boolean              |
                   ----------------------------------
                                  ▲
                                  |
                                  | HAS-A (Aggregation)
                                  ◇
                   ----------------------------------
                   |        FraudDetector           |
                   ----------------------------------
                   | - transactions : Transaction[] |
                   | - fraudScore : double          |
                   | - result : String              |
                   ----------------------------------
                   | + calculateFraudScore():double |
                   | + detectFraud() : void         |
                   | + displayReport() : void       |
                   ----------------------------------
```

---

### **3. Explanation of Relationships**

* Transaction HAS-A User
  → This is **Aggregation**

* RuleEngine uses Transaction
  → This is **Association**

* FraudDetector HAS-A Transaction
  → This is **Aggregation**

---

### **4. Implementation Status**

| Class Name    | Implementation Status |
| ------------- | --------------------- |
| User          | Partially implemented |
| Transaction   | Partially implemented |
| RuleEngine    | Fully implemented     |
| FraudDetector | Partially implemented |

---

### **5. Static / Initial Data**

* Transaction types:

```text
{"DEPOSIT", "WITHDRAW", "TRANSFER"}
```

* Locations are represented as strings (e.g., "India", "USA", etc.)

* Fraud thresholds:

```text
High Amount Threshold = 50000
```

---

### **6. Implementation Details**

###### **i. User Class**

**Method: validateUser()**

* This method checks whether the user is valid.
* A user is valid only if:

  * userId starts with `"USR-"`
  * accountBalance is greater than or equal to 0
* It returns true if the user is valid.
* It returns false if the user is invalid.

---

###### **ii. Transaction Class**

**Method: validateTransaction()**

* This method checks whether the transaction is valid.
* A transaction is valid only if:

  * transactionId starts with `"TXN-"`
  * amount is greater than 0
  * transactionType is one of the allowed types
* The comparison must be case-insensitive.

**It returns:**

* true if valid
* false if invalid

---

###### **iii. RuleEngine Class**

**Method: highAmountRule(Transaction txn)**

* Checks if transaction amount exceeds threshold (50000)
* Returns true if amount is suspicious
* Returns false otherwise

---

**Method: locationRule(Transaction txn)**

* If transaction location is different from user's usual location (assume "India"):

  * Mark as suspicious
* Returns true if suspicious
* Returns false otherwise

---

**Method: frequencyRule(Transaction[] txns)**

* If more than 3 transactions occur in a short time window (simulated):

  * Mark as suspicious
* Returns true if suspicious
* Returns false otherwise

---

###### **iv. FraudDetector Class**

**Method: calculateFraudScore()**

Step-by-step logic:

* Initialize fraudScore = 0
* For each transaction:

  * Validate transaction and user
  * If invalid → skip
  * Apply rules:

    * If highAmountRule → add 40 points
    * If locationRule → add 30 points
    * If frequencyRule → add 30 points
* Return final fraudScore

---

**Method: detectFraud()**

Step-by-step logic:

* Calculate fraudScore using calculateFraudScore()
* If fraudScore ≥ 70:

  * result = "Fraud Detected"
* If fraudScore between 40 and 69:

  * result = "Suspicious Activity"
* Otherwise:

  * result = "Normal Transaction"

---

**Method: displayReport()**

* Displays:

  * User ID
  * User Name
  * Total Fraud Score
  * Final Result

---

### **7. Sample Test Case**

**Input**

* userId = "USR-101"

* userName = "Rohit"

* accountBalance = 100000

* Transaction1:

  * transactionId = "TXN-1"
  * amount = 60000
  * type = "TRANSFER"
  * location = "USA"

* Transaction2:

  * transactionId = "TXN-2"
  * amount = 2000
  * type = "WITHDRAW"
  * location = "India"

---

**Expected Output**

```text
User ID: USR-101
User Name: Rohit
Fraud Score: 70.0
Result: Fraud Detected
```
