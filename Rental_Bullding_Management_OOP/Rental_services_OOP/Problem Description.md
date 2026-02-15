**Problem Description:**

* TechCorp is developing a system to manage the booking of vehicles for corporate use. The system should calculate the total cost for vehicle rental based on the type of vehicle, distance traveled, and additional services availed. The system should be fully automated.



The class design for the system is given below.



**Class Diagram**

&nbsp;               VehicleForRent()

&nbsp;               ----------------------

&nbsp;               - vehicleType: String

&nbsp;               - distance: float

&nbsp;               - baseCost: float

&nbsp;               + VehicleForRent(vehicleType: String, distance: float)

&nbsp;               + getVehicleType(): String

&nbsp;               + getDistance(): float

&nbsp;               + getBaseCost(): float

&nbsp;               + setBaseCost(baseCost: float): void

&nbsp;               + calculateTotalCost(): void

&nbsp;               + toString(): String

&nbsp;                       ^

&nbsp;                       |

&nbsp;       ---------------------------------------

&nbsp;       |                                     |

CarForRent                             BikeForRent

-------------                           --------------

\- seatingCapacity: int                  - engineCapacity: int

\- services: String\[]                     - services: String\[]

\- servicesDetailsArr: String\[]\[] (static)

\+ CarForRent(vehicleType: String, distance: float, seatingCapacity: int, services: String\[])

\+ getSeatingCapacity(): int             + getEngineCapacity(): int

\+ validateSeatingCapacity(): Boolean    + validateEngineCapacity(): Boolean

\+ calculateTotalCost(): void            + calculateTotalCost(): void

\+ toString(): String                    + toString(): String



**Notes**



* Do not add extra instance/static variables or methods.
* Case insensitive comparisons are required.
* Do not change the names of variables or methods.
* Read the notes carefully for calculation logic.
* Implementation Details



###### VehicleForRent Class():



**calculateTotalCost():**



* This method calculates and sets the baseCost (float) of VehicleForRent class.
* Base cost is calculated as distance \* ratePerKm based on vehicleType.
* Car: 20 currency/km
* Bike: 10 currency/km
* Invoke calculateTotalCost() method of child classes (CarForRent or BikeForRent).



###### CarForRent() Class:



**validateSeatingCapacity():**



* Return true if seatingCapacity is between 2 and 7.
* Otherwise return false.



**calculateTotalCost():**



* Invoke calculateTotalCost() of VehicleForRent.
* If validateSeatingCapacity() is false, set baseCost to -1.
* Otherwise, calculate total service cost:
* Use the 2D array servicesDetailsArr:
* servicesDetailsArr = {
* &nbsp;   {"GPS", "Extra Driver", "Insurance"},
* &nbsp;   {"500", "1000", "1500"}
* }



* For each service selected in services, add the corresponding charge from servicesDetailsArr to baseCost.



###### BikeForRent Class():



**validateEngineCapacity():**



* Return true if engineCapacity is between 100cc and 500cc.
* Otherwise return false.



**calculateTotalCost():**



* Invoke calculateTotalCost() of VehicleForRent.
* If validateEngineCapacity() is false, set baseCost to -1.
* Otherwise, calculate total service cost similar to CarForRent using servicesDetailsArr.



**Example**



* If vehicleType = "Car", distance = 100 km, seatingCapacity = 5, and services = {"GPS","Insurance"}, then the total cost would be:
* (100\*20) + 500 + 1500 = 4000 currency
