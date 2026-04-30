**Practice Problem 5: Smart Energy Grid System**

---

**1. Problem Statement**

Design and implement a **Smart Energy Grid System** using object-oriented programming concepts.

* A city wants to manage electricity distribution between houses dynamically.
* The system receives electricity from a power station.
* Each house has a required power demand.
* The grid must supply power to houses based on availability.
* If total demand is greater than available power, the load balancer must reduce supply fairly.
* The system must identify overload situations and redistribute power by strictly following the rules described in this problem.

---

###### **2. Class Diagram**

```text
                   ----------------------------------
                   |          PowerStation          |
                   ----------------------------------
                   | - stationId : String           |
                   | - stationName : String         |
                   | - totalPowerAvailable : int    |
                   ----------------------------------
                   | + validatePowerStation():boolean|
                   | + getTotalPowerAvailable():int |
                   | + getStationName() : String    |
                   ----------------------------------
                                  ▲
                                  |  HAS-A (Aggregation)
                                  ◇
                   ----------------------------------
                   |             Grid               |
                   ----------------------------------
                   | - gridId : String              |
                   | - powerStation : PowerStation  |
                   | - houses : House[]             |
                   | - totalDemand : int            |
                   ----------------------------------
                   | + calculateTotalDemand() : int |
                   | + isOverloaded() : boolean     |
                   | + distributePower() : void     |
                   | + getHouses() : House[]        |
                   ----------------------------------
                                  ▲
                                  |
                                  | HAS-A (Aggregation)
                                  ◇
                   ----------------------------------
                   |             House              |
                   ----------------------------------
                   | - houseId : String             |
                   | - ownerName : String           |
                   | - requiredPower : int          |
                   | - allocatedPower : int         |
                   ----------------------------------
                   | + validateHouseId() : boolean  |
                   | + setAllocatedPower(int) : void|
                   | + getRequiredPower() : int     |
                   | + getAllocatedPower() : int    |
                   | + getOwnerName() : String      |
                   ----------------------------------
                                  ▲
                                  |
                                  | uses
                                  |
                   ----------------------------------
                   |          LoadBalancer          |
                   ----------------------------------
                   | - grid : Grid                  |
                   ----------------------------------
                   | + balanceLoad() : void         |
                   | + displayPowerReport() : void  |
                   ----------------------------------
```

---

### **3. Explanation of Relationships**

* Grid HAS-A PowerStation
  → This is **Aggregation**

* Grid HAS-A House
  → This is **Aggregation**

* LoadBalancer uses Grid
  → This is **Association**

---

### **4. Implementation Status**

| Class Name   | Implementation Status |
| ------------ | --------------------- |
| PowerStation | Partially implemented |
| House        | Partially implemented |
| Grid         | Partially implemented |
| LoadBalancer | Partially implemented |

---

### **5. Static / Initial Data**

* Power is measured in units.
* Every house has:

```text
requiredPower
allocatedPower
```

* Initially:

```text
allocatedPower = 0
```

* A power station is valid only if totalPowerAvailable is greater than 0.

---

### **6. Implementation Details**

###### **i. PowerStation Class**

**Method: validatePowerStation()**

* This method checks whether the power station is valid.
* A power station is valid only if:

  * stationId starts with `"PS-"`
  * totalPowerAvailable is greater than 0
* It returns true if the power station is valid.
* It returns false if the power station is invalid.

---

###### **ii. House Class**

**Method: validateHouseId()**

* This method checks whether the house ID is valid.
* A house ID is valid only if:

  * houseId starts with `"H-"`
  * requiredPower is greater than 0
* It returns true if the house is valid.
* It returns false if the house is invalid.

---

###### **iii. Grid Class**

**Method: calculateTotalDemand()**

* This method calculates the total required power of all houses.
* Only valid houses must be considered.
* It returns totalDemand.

---

**Method: isOverloaded()**

* This method checks whether total demand is greater than total power available.

**It returns:**

* true if totalDemand is greater than totalPowerAvailable
* false if power is enough

---

**Method: distributePower()**

Step-by-step logic:

* Validate the power station using validatePowerStation()
* If the power station is invalid:

  * Set allocatedPower of every house to -1
  * Stop distribution
* Calculate total demand using calculateTotalDemand()
* If grid is not overloaded:

  * Allocate each valid house its full requiredPower
* If grid is overloaded:

  * Do not allocate power directly
  * LoadBalancer must handle redistribution

---

###### **iv. LoadBalancer Class**

**Method: balanceLoad()**

Step-by-step logic:

* Call distributePower() from Grid
* If grid is overloaded:

  * Calculate total available power
  * Count only valid houses
  * Divide available power equally among valid houses
  * Allocate equal power to every valid house
* Invalid houses should receive:

```text
allocatedPower = -1
```

---

**Method: displayPowerReport()**

* Displays:

  * House ID
  * Owner name
  * Required power
  * Allocated power
  * Power status

Power status rules:

```text
allocatedPower == -1        → Invalid Details
allocatedPower == requiredPower → Fully Supplied
allocatedPower < requiredPower  → Partially Supplied
```

---

### **7. Sample Test Case**

**Input**

* stationId = "PS-101"

* stationName = "Central Power Station"

* totalPowerAvailable = 300

* House1:

  * houseId = "H-101"
  * ownerName = "Amit"
  * requiredPower = 120

* House2:

  * houseId = "H-102"
  * ownerName = "Riya"
  * requiredPower = 150

* House3:

  * houseId = "H-103"
  * ownerName = "Karan"
  * requiredPower = 180

---

**Expected Output**

```text
Total Demand: 450
Available Power: 300
Grid Status: Overloaded

Power Distribution Report:
House ID: H-101
Owner Name: Amit
Required Power: 120
Allocated Power: 100
Status: Partially Supplied

House ID: H-102
Owner Name: Riya
Required Power: 150
Allocated Power: 100
Status: Partially Supplied

House ID: H-103
Owner Name: Karan
Required Power: 180
Allocated Power: 100
Status: Partially Supplied
```
