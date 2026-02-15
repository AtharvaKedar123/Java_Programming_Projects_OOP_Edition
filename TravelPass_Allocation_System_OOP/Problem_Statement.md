###### \# CityRide Authority – Travel Pass Automation System



**## Problem Description**



CityRide Authority wants to automate its public transport pass allocation system.  

Based on commuter details and travel preferences, the system should validate eligibility, generate pass numbers, and calculate the total pass cost.



Implement the class diagram and functionality as specified below.



---



**## Class Diagram Specifications**







&nbsp;            ┌──────────────────────────────┐

&nbsp;            │           Commuter            │

&nbsp;            ├──────────────────────────────┤

&nbsp;            │ - commuterName : String       │

&nbsp;            │ - idNumber : String           │

&nbsp;            │ - age : int                   │

&nbsp;            ├──────────────────────────────┤

&nbsp;            │ + Commuter(name,id,age)       │

&nbsp;            │ + validateCommuterDetails()  │

&nbsp;            │   : Boolean                  │

&nbsp;            │ + getCommuterName() : String │

&nbsp;            │ + getIdNumber() : String     │

&nbsp;            │ + getAge() : int             │

&nbsp;            │ + toString() : String        │

&nbsp;            └──────────────────────────────┘

&nbsp;                       ▲

&nbsp;                       │

&nbsp;                       │ HAS-A (Association)

&nbsp;                       │

&nbsp;   ┌──────────────────────────────────────────┐

&nbsp;   │         TravelPass (Abstract)             │

&nbsp;   ├──────────────────────────────────────────┤

&nbsp;   │ - commuter : Commuter                    │

&nbsp;   │ - routeName : String                     │

&nbsp;   │ - passCost : double                      │

&nbsp;   │ - passId : String                        │

&nbsp;   │ - counter : int <<static>>                │

&nbsp;   ├──────────────────────────────────────────┤

&nbsp;   │ + TravelPass(commuter,routeName)         │

&nbsp;   │ + setPassId(passId) : void               │

&nbsp;   │ + setPassCost(passCost) : void           │

&nbsp;   │ + getRouteName() : String                │

&nbsp;   │ + getCommuter() : Commuter               │

&nbsp;   │ + getPassId() : String                   │

&nbsp;   │ + getPassCost() : double                 │

&nbsp;   │ + generatePassId() : void                │

&nbsp;   │ + calculatePassCost() : void             │

&nbsp;   │   <<abstract>>                           │

&nbsp;   └──────────────────────────────────────────┘

&nbsp;                       ▲

&nbsp;                       │

&nbsp;                       │ IS-A (Inheritance)

&nbsp;                       │

&nbsp;   ┌──────────────────────────────────────────┐

&nbsp;   │             MonthlyBusPass               │

&nbsp;   ├──────────────────────────────────────────┤

&nbsp;   │ - travelZone : String                    │

&nbsp;   │ - distanceInKm : int                     │

&nbsp;   │ - numberOfDays : int                     │

&nbsp;   │ - travelZoneAvailable : String\[]         │

&nbsp;   │   <<static>>                             │

&nbsp;   │ - zoneRatePerKm : double\[] <<static>>    │

&nbsp;   ├──────────────────────────────────────────┤

&nbsp;   │ + MonthlyBusPass(commuter,routeName,     │

&nbsp;   │   travelZone,distanceInKm,numberOfDays)  │

&nbsp;   │ + validateTravelZone() : Integer         │

&nbsp;   │ + calculatePassCost() : void             │

&nbsp;   │ + toString() : String                    │

&nbsp;   └──────────────────────────────────────────┘





---

\## Implementation Status



&nbsp;Class Name         Implementation Status                                                          



&nbsp;Commuter           Partially Implemented 

&nbsp;TravelPass         Partially Implemented 

&nbsp;MonthlyBusPass     Partially Implemented 





\## Implementation Details



###### \### Commuter class()



**#### validateCommuterDetails()**



This method validates commuter details based on the following logic:



\- commuterName should contain at least 3 characters  

\- age should be greater than or equal to 18  

\- idNumber should start with "ID"  



If all conditions are satisfied, return true; otherwise return false.



\*\*Note:\*\*  

Perform case-sensitive comparison.



\*\*Example:\*\*  

If commuterName is Ravi, age is 25, and idNumber is ID4598, then the method returns true.



---



###### **### TravelPass class()**



**#### generatePassId()**



This method auto-generates and sets the passId.



\- passId should start with the first character of routeName  

\- followed by an auto-generated number starting from 5001  

\- the number should be incremented by one for each new pass  



Use static variable counter appropriately.



\*\*Example:\*\*  

If routeName is CentralLine, the first generated passId would be C5001.



---



###### \### MonthlyBusPass class()



**#### travelZoneAvailable**



This is a static String array containing the supported zones:



{"ZoneA", "ZoneB", "ZoneC"}





---



**#### zoneRatePerKm**



This is a static double array containing cost per kilometer for each zone:



{1.5, 2.0, 2.5}





This array has one-to-one correspondence with travelZoneAvailable.



---



**#### validateTravelZone()**



\- Check if travelZone is present in travelZoneAvailable  

\- If present, return the corresponding index  

\- Otherwise return -1  



---



**#### calculatePassCost()**



This method calculates and sets the passCost and passId using the following logic:



1\. Invoke validateCommuterDetails() of Commuter class  

2\. Check if travelZone is present in travelZoneAvailable  

3\. If both validations pass:

&nbsp;  - Invoke validateTravelZone()  

&nbsp;  - If zoneIndex is not -1 and distanceInKm > 0 and numberOfDays > 0:

&nbsp;    - baseCost = distanceInKm × zoneRatePerKm\[zoneIndex] × numberOfDays  

&nbsp;    - If age is 60 or above, apply 20% senior citizen discount  

&nbsp;    - Set passCost with the final calculated amount  

&nbsp;    - Invoke generatePassId()  

&nbsp;  - Else:

&nbsp;    - Set passCost to -1.0  

&nbsp;    - Set passId to "NA"  

4\. If any validation fails:

&nbsp;  - Set passCost to -1.0  

&nbsp;  - Set passId to "NA"  



---



**## Example**



If  

\- commuterName = Ravi  

\- idNumber = ID4598  

\- age = 62  

\- routeName = CentralLine  

\- travelZone = ZoneB  

\- distanceInKm = 10  

\- numberOfDays = 30  



**Then**  

\- passId = C5001  

\- passCost = 480.0  

