**Practice Problem 9: Real-Time Stock Trading Simulator**

---

**1. Problem Statement**

Design and implement a **Real-Time Stock Trading Simulator** using object-oriented programming concepts.

* A trading platform wants to simulate buying and selling of stocks.
* Each stock has a stock symbol, company name, and current price.
* A trader can place buy or sell orders.
* The market must update stock prices dynamically.
* The system must calculate profit or loss after every trade.
* The simulator must process orders and display trading results by strictly following the rules described in this problem.

---

###### **2. Class Diagram**

```text
                   ----------------------------------
                   |             Stock              |
                   ----------------------------------
                   | - stockSymbol : String         |
                   | - companyName : String         |
                   | - currentPrice : double        |
                   ----------------------------------
                   | + validateStock() : boolean    |
                   | + updatePrice(double) : void   |
                   | + getCurrentPrice() : double   |
                   | + getStockSymbol() : String    |
                   | + getCompanyName() : String    |
                   ----------------------------------
                                  ▲
                                  |  HAS-A (Aggregation)
                                  ◇
                   ----------------------------------
                   |            Order               |
                   ----------------------------------
                   | - orderId : String             |
                   | - stock : Stock                |
                   | - orderType : String           |
                   | - quantity : int               |
                   | - orderPrice : double          |
                   ----------------------------------
                   | + validateOrder() : boolean    |
                   | + calculateOrderValue():double |
                   | + getOrderType() : String      |
                   | + getQuantity() : int          |
                   | + getStock() : Stock           |
                   ----------------------------------
                                  ▲
                                  |
                                  | HAS-A (Aggregation)
                                  ◇
                   ----------------------------------
                   |            Trader              |
                   ----------------------------------
                   | - traderId : String            |
                   | - traderName : String          |
                   | - balance : double             |
                   | - ownedQuantity : int          |
                   | - totalInvestment : double     |
                   ----------------------------------
                   | + validateTrader() : boolean   |
                   | + buyStock(Order) : void       |
                   | + sellStock(Order) : void      |
                   | + calculateProfitLoss()        |
                   |       : double                 |
                   | + displayPortfolio() : void    |
                   ----------------------------------
                                  ▲
                                  |
                                  | uses
                                  |
                   ----------------------------------
                   |            Market              |
                   ----------------------------------
                   | - marketName : String          |
                   | - stocks : Stock[]             |
                   ----------------------------------
                   | + updateMarketPrices() : void  |
                   | + findStock(String) : Stock    |
                   ----------------------------------
```

---

### **3. Explanation of Relationships**

* Order HAS-A Stock
  → This is **Aggregation**

* Trader uses Order
  → This is **Association**

* Market HAS-A Stock
  → This is **Aggregation**

---

### **4. Implementation Status**

| Class Name | Implementation Status |
| ---------- | --------------------- |
| Stock      | Partially implemented |
| Order      | Partially implemented |
| Trader     | Partially implemented |
| Market     | Partially implemented |

---

### **5. Static / Initial Data**

* Order types:

```text
{"BUY", "SELL"}
```

* Every trader starts with:

```text
ownedQuantity = 0
totalInvestment = 0
```

* Stock price must always be greater than 0.

---

### **6. Implementation Details**

###### **i. Stock Class**

**Method: validateStock()**

* This method checks whether the stock is valid.
* A stock is valid only if:

  * stockSymbol is not empty
  * currentPrice is greater than 0
* It returns true if the stock is valid.
* It returns false if the stock is invalid.

---

**Method: updatePrice(double newPrice)**

* Updates stock currentPrice only if newPrice is greater than 0.

---

###### **ii. Order Class**

**Method: validateOrder()**

* This method checks whether the order is valid.
* An order is valid only if:

  * orderId starts with `"ORD-"`
  * stock is valid
  * quantity is greater than 0
  * orderType is either `"BUY"` or `"SELL"`
* The comparison must be case-insensitive.

**It returns:**

* true if order is valid
* false if order is invalid

---

**Method: calculateOrderValue()**

* Calculates total order value using:

```text
orderValue = orderPrice × quantity
```

* It returns the calculated order value.

---

###### **iii. Trader Class**

**Method: validateTrader()**

* This method checks whether the trader is valid.
* A trader is valid only if:

  * traderId starts with `"TRD-"`
  * balance is greater than or equal to 0
* It returns true if trader is valid.
* It returns false if trader is invalid.

---

**Method: buyStock(Order order)**

Step-by-step logic:

* Validate trader using validateTrader()
* Validate order using validateOrder()
* If validation fails:

  * Display `"Invalid Buy Order"`
* Calculate order value.
* If trader balance is enough:

  * Reduce balance by order value
  * Increase ownedQuantity by order quantity
  * Increase totalInvestment by order value
  * Display `"Stock Purchased Successfully"`
* Otherwise:

  * Display `"Insufficient Balance"`

---

**Method: sellStock(Order order)**

Step-by-step logic:

* Validate trader using validateTrader()
* Validate order using validateOrder()
* If validation fails:

  * Display `"Invalid Sell Order"`
* If ownedQuantity is greater than or equal to order quantity:

  * Increase balance by order value
  * Decrease ownedQuantity by order quantity
  * Display `"Stock Sold Successfully"`
* Otherwise:

  * Display `"Insufficient Stock Quantity"`

---

**Method: calculateProfitLoss()**

* Calculates current portfolio value using:

```text
currentValue = ownedQuantity × current stock price
```

* Profit or loss is calculated using:

```text
profitLoss = currentValue - totalInvestment
```

* It returns profitLoss as double.

---

**Method: displayPortfolio()**

* Displays:

  * Trader ID
  * Trader name
  * Current balance
  * Owned quantity
  * Total investment
  * Profit or loss

---

###### **iv. Market Class**

**Method: updateMarketPrices()**

* Updates all stock prices using simulated price changes.
* Price should not become less than or equal to 0.

---

**Method: findStock(String stockSymbol)**

* Searches stock by stockSymbol.
* The comparison must be case-insensitive.

**It returns:**

* Stock object if found
* null if stock is not found

---

### **7. Sample Test Case**

**Input**

* traderId = "TRD-101"

* traderName = "Aman"

* balance = 100000

* Stock:

  * stockSymbol = "INFY"
  * companyName = "Infosys"
  * currentPrice = 1500

* Buy Order:

  * orderId = "ORD-101"
  * orderType = "BUY"
  * quantity = 10
  * orderPrice = 1500

* Updated Stock Price:

  * currentPrice = 1600

---

**Expected Output**

```text
Stock Purchased Successfully

Trader ID: TRD-101
Trader Name: Aman
Current Balance: 85000.0
Owned Quantity: 10
Total Investment: 15000.0
Profit/Loss: 1000.0
```
