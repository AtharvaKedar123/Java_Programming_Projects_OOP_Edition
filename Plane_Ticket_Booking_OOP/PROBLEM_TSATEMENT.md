Practice Problem 2

Problem Statement

Problem Description:



SkyHigh Airlines has come up with an online facility for booking flight tickets.

The process of calculating ticket fare needs to be automated.



The class design for the same is given below.



###### Class Diagram:

**Ticket()**

* distance : int
* fare : double
* Ticket(distance : int)
* getDistance() : int
* getFare() : double
* setFare(fare : double) : void
* calculateFare() : void
* toString() : String



###### BusinessClassTicket (extends Ticket)

* services : String\[]
* servicesDetailsArr : String\[]\[] (static)
* BusinessClassTicket(distance : int, services : String\[])
* getServices() : String\[]
* calculateFare() : void
* toString() : String



###### EconomyClassTicket (extends Ticket)



* baggageWeight : int
* EconomyClassTicket(distance : int, baggageWeight : int)
* getBaggageWeight() : int
* validateBaggageWeight() : boolean
* calculateFare() : void
* toString() : String



**Notes:**



* Do not include any extra instance/static variables and methods.
* Case insensitive comparison is required unless specified explicitly.
* Do not change any value or case of given variables.
* Read notes and examples carefully for understanding the logic.





**Implementation Details**:

Class name	        Implementation details

Ticket	                Fully implemented

BusinessClassTicket	Partially implemented

EconomyClassTicket	Partially implemented







###### Ticket Class

###### calculateFare()



* This method calculates and sets the basic fare.
* fare = distance × 5



###### BusinessClassTicket Class

###### servicesDetailsArr:

* 
* This is a static 2D-array (String\[]\[]) which contains:
* First row → service names
* Second row → corresponding service charges
* Initial values:
* {
* &nbsp;{"Meal", "ExtraBaggage", "Lounge"},
* &nbsp;{"1500", "2500", "3000"}
* }
* 
* One-to-one mapping exists between service and charge.
* The array is already supplied. No need to code it.
* Do not change the CASE of elements.



###### calculateFare()



This method calculates and sets the fare.

**Steps:**



* Invoke calculateFare() of Ticket class.
* Set the basicAmount with fare of Ticket class.
* If services is null → set fare = -1.0
* Otherwise:
* For every service in services array:
* Check if service exists in servicesDetailsArr (case insensitive)
* Identify corresponding charge
* Add charges to totalServiceCharge
* If any sErvice is not found → fare = -1.0
* If totalServiceCharge > 0:
* fare = basicAmount + totalServiceCharge



**Otherwise:**



* fare = -1.0





**Example:**



* If distance = 1000
* services = {"Meal", "Lounge"}
* Basic fare = 1000 × 5 = 5000
* Service charges = 1500 + 3000 = 4500
* Final fare = 9500



###### EconomyClassTicket Class

###### validateBaggageWeight()



* This method validates baggageWeight.
* Return:
* true → if baggageWeight between 0 and 30 (boundary excluded)
* false → otherwise



**Example:**



* 20 → true
* 0 → false
* 30 → false



###### calculateFare()



**Steps:**



* Invoke calculateFare() of Ticket class.
* Set basicAmount with fare of Ticket class.
* If validateBaggageWeight() returns false:
* fare = -1.0



**Otherwise:**

* 
* extraCharge = baggageWeight × 200
* fare = basicAmount + extraCharge



**Example:**



* If distance = 500
* baggageWeight = 10
* 
* Basic fare = 500 × 5 = 2500
* Extra charge = 10 × 200 = 2000
* 
* Final fare = 4500
