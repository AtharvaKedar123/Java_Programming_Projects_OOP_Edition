#### \# AquaCare – Water Usage Billing System



**## Problem Description**



AquaCare Corporation wants to automate its \*\*domestic water usage billing system\*\*.  

Based on consumer details and water consumption, the system should validate the consumer, generate a bill number, and calculate the final water bill amount.



Implement the class diagram and functionality as specified below.



---



**## Class Diagram Specifications**



&nbsp;               ┌──────────────────────────────┐

&nbsp;               │           Consumer            │

&nbsp;               ├──────────────────────────────┤

&nbsp;               │ - consumerName : String       │

&nbsp;               │ - connectionId : String       │

&nbsp;               │ - areaType : String           │

&nbsp;               ├──────────────────────────────┤

&nbsp;               │ + Consumer(name,id,area)      │

&nbsp;               │ + validateConsumerDetails()  │

&nbsp;               │   : Boolean                  │

&nbsp;               │ + getConsumerName() : String │

&nbsp;               │ + getConnectionId() : String │

&nbsp;               │ + getAreaType() : String     │

&nbsp;               │ + toString() : String        │

&nbsp;               └──────────────────────────────┘

&nbsp;                          ▲

&nbsp;                          │

&nbsp;                          │ HAS-A (Association)

&nbsp;                          │

&nbsp;       ┌──────────────────────────────────────────┐

&nbsp;       │           WaterBill (Abstract)            │

&nbsp;       ├──────────────────────────────────────────┤

&nbsp;       │ - consumer : Consumer                    │

&nbsp;       │ - usageInLitres : int                    │

&nbsp;       │ - billAmount : double                    │

&nbsp;       │ - billId : String                        │

&nbsp;       │ - counter : int <<static>>                │

&nbsp;       ├──────────────────────────────────────────┤

&nbsp;       │ + WaterBill(consumer,usageInLitres)     │

&nbsp;       │ + setBillId(billId) : void               │

&nbsp;       │ + setBillAmount(billAmount) : void       │

&nbsp;       │ + getUsageInLitres() : int               │

&nbsp;       │ + getBillId() : String                   │

&nbsp;       │ + getBillAmount() : double               │

&nbsp;       │ + generateBillId() : void                │

&nbsp;       │ + calculateBillAmount() : void           │

&nbsp;       │   <<abstract>>                           │

&nbsp;       └──────────────────────────────────────────┘

&nbsp;                          ▲

&nbsp;                          │

&nbsp;                          │ IS-A (Inheritance)

&nbsp;                          │

&nbsp;       ┌──────────────────────────────────────────┐

&nbsp;       │          DomesticWaterBill               │

&nbsp;       ├──────────────────────────────────────────┤

&nbsp;       │ - supplyType : String                    │

&nbsp;       │ - supplyTypeAvailable : String\[]         │

&nbsp;       │   <<static>>                             │

&nbsp;       │ - ratePerLitre : double\[] <<static>>     │

&nbsp;       ├──────────────────────────────────────────┤

&nbsp;       │ + DomesticWaterBill(consumer,usage,     │

&nbsp;       │   supplyType)                            │

&nbsp;       │ + validateSupplyType() : Integer         │

&nbsp;       │ + calculateBillAmount() : void           │

&nbsp;       │ + toString() : String                    │

&nbsp;       └──────────────────────────────────────────┘

**## Implementation Sta**tus



 Class Name          | Implementation Status



 Consumer             Partially Implemented

 WaterBill            Partially Implemented

 DomesticWaterBill    Partially Implemented







**## Implementation Details**



##### \### Consumer class()



**#### validateConsumerDetails()**



This method validates consumer details based on the following logic:



\- consumerName should contain \*\*at least 3 characters\*\*

\- connectionId should start with \*\*"WC"\*\*

\- areaType should be either \*\*"Urban"\*\* or \*\*"Rural"\*\*



If all conditions are satisfied, return \*\*true\*\*, else return \*\*false\*\*.



\*\*Note:\*\*  

Perform \*\*case-sensitive comparison\*\*.



---

##### 

##### \### WaterBill class



**#### generateBillId()**



This method auto-generates and sets the billId.



\- billId should start with the \*\*first character of consumerName\*\*

\- followed by an auto-generated number starting from \*\*8001\*\*

\- the number should increment by \*\*1\*\* for each new bill



Use static variable \*\*counter\*\* appropriately.



---



##### \### DomesticWaterBill class



**#### supplyTypeAvailable**



This is a static String array containing supported water supply types:



{"Municipal", "Borewell"}





---



**#### ratePerLitre**



This is a static double array containing rate per litre for each supply type:



{0.02, 0.03}





This array has one-to-one correspondence with supplyTypeAvailable.



---



**#### validateSupplyType()**



\- Check if supplyType is present in supplyTypeAvailable  

\- If present, return the corresponding index  

\- Otherwise, return \*\*-1\*\*



---



**#### calculateBillAmount()**



This method calculates and sets the billAmount and billId using the following logic:



1\. Invoke validateConsumerDetails() of Consumer class  

2\. Check if supplyType is present in supplyTypeAvailable  

3\. If both validations pass:

&nbsp;  - Invoke validateSupplyType()  

&nbsp;  - If supplyTypeIndex is not -1 and usageInLitres > 0:

&nbsp;    - baseAmount = usageInLitres × ratePerLitre\[supplyTypeIndex]  

&nbsp;    - If areaType is \*\*"Rural"\*\*, apply \*\*10% subsidy\*\*  

&nbsp;    - Set billAmount with the final calculated value  

&nbsp;    - Invoke generateBillId()  

&nbsp;  - Else:

&nbsp;    - Set billAmount to \*\*-1.0\*\*  

&nbsp;    - Set billId to \*\*"NA"\*\*

4\. If any validation fails:

&nbsp;  - Set billAmount to \*\*-1.0\*\*  

&nbsp;  - Set billId to \*\*"NA"\*\*



---





---



\## Example



If  

\- consumerName = Suresh  

\- connectionId = WC1023  

\- areaType = Rural  

\- usageInLitres = 2000  

\- supplyType = Municipal  



Then  

\- billId = S8001  

\- billAmount = 36.0  

