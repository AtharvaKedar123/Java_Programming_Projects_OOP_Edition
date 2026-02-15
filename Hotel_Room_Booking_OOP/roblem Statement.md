roblem Statement

Problem Description:



QuickStay Hotels has introduced an automated system to calculate the advance amount for room bookings.

&nbsp;The calculation depends on room size and additional facilities chosen by the customer.



The class design for the same is given below.



**Class Diagram**



###### RoomBooking()



* roomSize : int
* advanceAmount : double
* RoomBooking(roomSize : int)
* getRoomSize() : int
* getAdvanceAmount() : double
* setAdvanceAmount(advanceAmount : double) : void
* calculateAdvanceAmount() : void
* toString() : String



###### LuxuryRoomBooking (extends RoomBooking)



* facilities : String\[]
* facilitiesDetailsArr : String\[]\[] (static)
* LuxuryRoomBooking(roomSize : int, facilities : String\[])
* getFacilities() : String\[]
* calculateAdvanceAmount() : void
* toString() : String

###### 

###### StandardRoomBooking (extends RoomBooking)



* bedType : char
* StandardRoomBooking(roomSize : int, bedType : char)
* getBedType() : char
* identifyBedCharge() : int
* calculateAdvanceAmount() : void
* toString() : String



**Notes**



* Do not include extra variables.
* Case insensitive comparison required.
* Do not change case of given array elements.
* Set advanceAmount to -1.0 if invalid.



Implementation Details

Class Name	Implementation

RoomBooking	        Fully implemented

LuxuryRoomBooking	Partially implemented

StandardRoomBooking	Partially implemented



###### RoomBooking Class



**calculateAdvanceAmount()**



* advanceAmount = roomSize × 100



###### **LuxuryRoomBooking Class**



**facilitiesDetailsArr()**



* Static 2D Array:
* {
* &nbsp;{"Pool", "Spa", "Gym"},
* &nbsp;{"5000", "3000", "2000"}
* }
* 
* First row → facility name
* Second row → facility charge
* One-to-one mapping exists



**calculateAdvanceAmount()**



**Steps:**

* Invoke calculateAdvanceAmount() of RoomBooking.
* Store basicAmount from parent.
* If facilities is null → advanceAmount = -1.0
* For each facility:
* Check case-insensitive match
* Add respective charge
* If any facility not found → -1.0



* If totalFacilityCharge > 0:
* advanceAmount = basicAmount + totalFacilityCharge





**Else:**



* advanceAmount = -1.0



**Example**

* If roomSize = 200
* facilities = {"Pool", "Gym"}
* Basic = 200 × 100 = 20000
* Facility charge = 5000 + 2000 = 7000
* Final advanceAmount = 27000



StandardRoomBooking Class

identifyBedCharge()



Return bed charge based on bedType:



Bed Type	Charge

'S'	2000

'D'	4000

'K'	6000

Others	-1



Case insensitive.



calculateAdvanceAmount()



Steps:



Invoke parent calculateAdvanceAmount().



Get basicAmount.



Call identifyBedCharge().



If bedCharge == -1:



advanceAmount = -1.0





Otherwise:



advanceAmount = basicAmount + bedCharge



Example



If roomSize = 150

bedType = 'D'



Basic = 150 × 100 = 15000

Bed charge = 4000



Final advanceAmount = 19000

