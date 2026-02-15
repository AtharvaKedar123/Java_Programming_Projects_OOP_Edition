**# Description**



StreamHub, a digital media company wants to automate its online content purchase system based on various parameters.  

Implement the class diagram given below to achieve the same.



---



**## Class Diagram Specifications**



&nbsp;              ┌──────────────────────────────┐

&nbsp;              │           Customer            │

&nbsp;              ├──────────────────────────────┤

&nbsp;              │ - custName : String           │

&nbsp;              │ - emailId : String            │

&nbsp;              │ - age : int                   │

&nbsp;              ├──────────────────────────────┤

&nbsp;              │ + Customer(custName,emailId, │

&nbsp;              │   age)                        │

&nbsp;              │ + validateCustomerDetails()  │

&nbsp;              │   : Boolean                  │

&nbsp;              │ + getCustName() : String     │

&nbsp;              │ + getEmailId() : String      │

&nbsp;              │ + getAge() : int             │

&nbsp;              │ + toString() : String        │

&nbsp;              └──────────────────────────────┘

&nbsp;                         ▲

&nbsp;                         │

&nbsp;                         │  HAS-A (Association)

&nbsp;                         │

&nbsp;       ┌──────────────────────────────────────────┐

&nbsp;       │     DigitalContentOrder (Abstract)        │

&nbsp;       ├──────────────────────────────────────────┤

&nbsp;       │ - customer : Customer                    │

&nbsp;       │ - contentName : String                   │

&nbsp;       │ - orderPrice : double                    │

&nbsp;       │ - orderId : String                       │

&nbsp;       │ - counter : int <<static>>                │

&nbsp;       ├──────────────────────────────────────────┤

&nbsp;       │ + DigitalContentOrder(customer,          │

&nbsp;       │   contentName)                           │

&nbsp;       │ + setOrderId(orderId) : void             │

&nbsp;       │ + setOrderPrice(orderPrice) : void       │

&nbsp;       │ + getContentName() : String              │

&nbsp;       │ + getCustomer() : Customer               │

&nbsp;       │ + getOrderId() : String                  │

&nbsp;       │ + getOrderPrice() : double               │

&nbsp;       │ + generateOrderId() : void               │

&nbsp;       │ + calculateOrderPrice() : void           │

&nbsp;       │   <<abstract>>                           │

&nbsp;       └──────────────────────────────────────────┘

&nbsp;                         ▲

&nbsp;                         │

&nbsp;                         │  IS-A (Inheritance)

&nbsp;                         │

&nbsp;       ┌──────────────────────────────────────────┐

&nbsp;       │               VideoOrder                 │

&nbsp;       ├──────────────────────────────────────────┤

&nbsp;       │ - videoQuality : String                  │

&nbsp;       │ - resolution : int                       │

&nbsp;       │ - durationInSec : int                    │

&nbsp;       │ - videoQualityAvailable : String\[]       │

&nbsp;       │   <<static>>                             │

&nbsp;       │ - resolutionAvailable : int\[] <<static>> │

&nbsp;       │ - resolutionCost : double\[] <<static>>   │

&nbsp;       ├──────────────────────────────────────────┤

&nbsp;       │ + VideoOrder(customer,contentName,       │

&nbsp;       │   videoQuality,resolution,durationInSec) │

&nbsp;       │ + validateResolution() : Integer         │

&nbsp;       │ + calculateOrderPrice() : void           │

&nbsp;       │ + toString() : String                    │

&nbsp;       └──────────────────────────────────────────┘





**## Notes**



\- Do not include any extra instance or static variables and methods.

\- Do not change the names or case of the given variables.

\- Case-insensitive comparison is to be done if not explicitly mentioned.

\- In derived class constructors, pass base class parameters first followed by derived class parameters.



---



**## Implementation Details**



---

###### 

###### **## Customer class()**



**### validateCustomerDetails()**



This method validates the customer details and returns Boolean based on the below logic:



\- If the custName contains at least 5 characters and age is greater than or equal to 21

&nbsp; and emailId ends with either `.com` or `.org`, then return true.

\- Otherwise, return false.



\*\*Note:\*\* Perform case-sensitive comparison.



\*\*Example:\*\*  

If the custName is Michael, age is 23 and emailId is mike@abc.com, then this method returns true.



---



##### **## DigitalContentOrder class()**



**### generateOrderId()**



This method auto-generates and sets the orderId (String).



\- The orderId must be prefixed with the first character of the contentName (String)

&nbsp; followed by the auto-generated value starting from 2001.

\- The auto-generated value must be incremented by one for every new order.

\- Use the static variable counter appropriately.



\*\*Note:\*\* Perform case-sensitive operations.



\*\*Example:\*\*  

If the contentName is Avatar, then the first orderId would be A2001.  

If the contentName is inception, then the next orderId would be i2002.



---



###### **## VideoOrder class()**



**### videoQualityAvailable**



This is a static array (String\[]) which contains the available video quality formats.



The initial values are given below:



videoQualityAvailable { "HD", "FullHD", "4K" }



\*\*Note:\*\*  

This array is supplied. Hence no need to code. Do not change the CASE of the elements.



---



**### resolutionAvailable**



This is a static array (int\[]) which contains the available resolution values.



The initial values are given below:



resolutionAvailable { 720, 1080, 2160 }





\*\*Note:\*\*  

This array is supplied. Hence no need to code.



---



**### resolutionCost**



This is a static array (double\[]) which contains the cost for each resolution.



This array has one-to-one correspondence with resolutionAvailable.



The initial values are given below:



resolutionCost { 12.0, 18.0, 30.0 }





\*\*Note:\*\*  

This array is supplied. Hence no need to code.



---



**### validateResolution()**



This method validates the resolution (int) and returns the index (Integer)

based on the following logic:



\- Check if the resolution is present in the resolutionAvailable array.

\- If present, return the corresponding index position.

\- Otherwise, return -1.



\*\*Example:\*\*  

If the resolution is 2160, then this method returns 2.



---



**### calculateOrderPrice()**



This method calculates and sets the orderPrice (double) and generates the orderId (String)

based on the below logic:



\- Invoke validateCustomerDetails() method of Customer class.

\- If the videoQuality (String) is available in the videoQualityAvailable array

&nbsp; and the above method returns true, then:



&nbsp;   - Invoke validateResolution() to obtain resolutionIndex.

&nbsp;   - If resolutionIndex is not -1 and durationInSec is greater than 0, then:



&nbsp;       - Set baseCost (double) using the corresponding cost from resolutionCost array.

&nbsp;       - Add currency 4 to the baseCost for each minute of video.

&nbsp;       - \*\*Note:\*\* Perform integer division for minutes calculation.

&nbsp;       - Add 8% service tax to the updated baseCost.

&nbsp;       - Set orderPrice with the final calculated value.

&nbsp;       - Invoke generateOrderId().



&nbsp;   - Otherwise, set orderPrice to -1.0 and orderId to NA.



\- Otherwise, set orderPrice to -1.0 and orderId to NA.



\*\*Note:\*\* Perform case-insensitive comparison.



---



\## Example



\- custName is Michael

\- emailId is mike@abc.com

\- age is 23

\- contentName is Avatar

\- videoQuality is 4k

\- resolution is 2160

\- durationInSec is 420



Then:

\- orderId would be A2001

\- orderPrice would be currency 41.04



