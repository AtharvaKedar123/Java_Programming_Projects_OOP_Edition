Problem Statement: Telecom Plan Management

Problem Description



ConnectPoint Telecom needs an automated system to calculate the monthly bill amount for different types of subscription plans. 

The calculation depends on the base data limit and additional value-added services or device types.

Class Diagram



###### **SmartPlan (Base Class)**



* &nbsp;   dataLimit : int (in GB)
* &nbsp;   monthlyBill : double
* &nbsp;   SmartPlan(dataLimit : int)
* &nbsp;   getDataLimit() : int
* &nbsp;   getMonthlyBill() : double
* &nbsp;   setMonthlyBill(monthlyBill : double) : void
* &nbsp;   calculateMonthlyBill() : void
* &nbsp;   toString() : String



###### ValueAddedPlan (extends SmartPlan)

* 
* &nbsp;   services : String\[]
* &nbsp;   serviceRatesArr : String\[]\[] (static)
* &nbsp;   ValueAddedPlan(dataLimit : int, services : String\[])
* &nbsp;   getServices() : String\[]
* &nbsp;   calculateMonthlyBill() : void



###### DeviceBundledPlan (extends SmartPlan)



* &nbsp;   deviceType : char
* &nbsp;   DeviceBundledPlan(dataLimit : int, deviceType : char)
* &nbsp;   getDeviceType() : char
* &nbsp;   identifyDeviceSubsidy() : int
* &nbsp;   calculateMonthlyBill() : void



Implementation Details

Class Name	        Implementation

SmartPlan	        Fully implemented

ValueAddedPlan	        Partially implemented

DeviceBundledPlan	Partially implemented



###### 1\. SmartPlan Class()



**calculateMonthlyBill()**



* &nbsp;   monthlyBill = dataLimit × 15.0



###### 2\. ValueAddedPlan Class()



**serviceRatesArr (Static 2D Array)**



* &nbsp;   Row 0 (Service Name): {"Streaming", "International", "Cloud"}
* 
* &nbsp;   Row 1 (Service Rate): {"400", "800", "200"}



**calculateMonthlyBill()**



* &nbsp;   Invoke calculateMonthlyBill() of SmartPlan.
* 
* &nbsp;   Store the basicBill from the parent.
* 
* &nbsp;   If services array is null or empty, set monthlyBill to -1.0.



&nbsp;   **For each service in the input array:**



* &nbsp;       Perform a case-insensitive search in serviceRatesArr.
* 
* &nbsp;       If found, add the respective rate to a totalServiceCharge.
* 
* &nbsp;       If any service is not found in the array, set monthlyBill to -1.0 and stop.



&nbsp;   **If all services are valid:**



* &nbsp;       monthlyBill = basicBill + totalServiceCharge.



**Example:**



* &nbsp;   dataLimit = 20, services = {"Cloud", "Streaming"}
* &nbsp;   Basic = 20×15=300
* &nbsp;   Service Charge = 200+400=600
* &nbsp;   Final monthlyBill = 900.0



###### 3\. DeviceBundledPlan Class()



**identifyDeviceSubsidy()**

* Return a monthly discount (subsidy) based on deviceType:
* Device Type	Subsidy Amount
* 'M' (Mobile)	100
* 'T' (Tablet)	150
* 'R' (Router)	50
* Others	-1



    **Comparison must be case-insensitive.**



**calculateMonthlyBill()**



* &nbsp;   Invoke parent calculateMonthlyBill().
* &nbsp;   Get the basicBill.
* &nbsp;   Call identifyDeviceSubsidy().



&nbsp;   If subsidy == -1:

* &nbsp;       Set monthlyBill to -1.0.



&nbsp;   Otherwise:



* &nbsp;       monthlyBill = basicBill - subsidy.
* &nbsp;       Note: If the subsidy is greater than the bill, set monthlyBill to 0.0.



**Example:**



* &nbsp;   dataLimit = 10, deviceType = 'T'
* &nbsp;   Basic = 10×15=150
* &nbsp;   Subsidy = 150
* &nbsp;   Final monthlyBill = 0.0
