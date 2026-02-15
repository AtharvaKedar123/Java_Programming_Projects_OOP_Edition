





**Problem Description:** 

Digital Arts is a company which provides digital content like music to various customers and wants to automate the ordering process of digital items. 



Implement the class diagram given below to achieve the same. 









**Class Diagram Specifications:**



&nbsp;               ┌────────────────────────────┐

&nbsp;               │          Customer           │

&nbsp;               ├────────────────────────────┤

&nbsp;               │ - custName : String         │

&nbsp;               │ - emailld : String          │

&nbsp;               │ - age : int                 │

&nbsp;               ├────────────────────────────┤

&nbsp;               │ + Customer(name,email,age) │

&nbsp;               │ + validateCustomerDetails():Boolean │

&nbsp;               │ + getCustName():String     │

&nbsp;               │ + getEmailld():String      │

&nbsp;               │ + getAge():int             │

&nbsp;               │ + toString():String        │

&nbsp;               └────────────────────────────┘

&nbsp;                          ▲

&nbsp;                          │  (HAS-A / Association)

&nbsp;                          │

&nbsp;       ┌─────────────────────────────────────────┐

&nbsp;       │        DigitalItemOrder (Abstract)       │

&nbsp;       ├─────────────────────────────────────────┤

&nbsp;       │ - customer : Customer                   │

&nbsp;       │ - itemName : String                     │

&nbsp;       │ - orderPrice : double                   │

&nbsp;       │ - orderid : String                      │

&nbsp;       │ - counter : int <<static>>               │

&nbsp;       ├─────────────────────────────────────────┤

&nbsp;       │ + DigitalItemOrder(customer,itemName)   │

&nbsp;       │ + setOrderid(String):void               │

&nbsp;       │ + setOrderPrice(double):void            │

&nbsp;       │ + getItemName():String                  │

&nbsp;       │ + getCustomer():Customer                │

&nbsp;       │ + getOrderid():String                   │

&nbsp;       │ + getOrderPrice():double                │

&nbsp;       │ + generateOrderid():void                │

&nbsp;       │ + calculateOrderPrice():void <<abstract>>│

&nbsp;       └─────────────────────────────────────────┘

&nbsp;                          ▲

&nbsp;                          │  (IS-A / Inheritance)

&nbsp;                          │

&nbsp;       ┌─────────────────────────────────────────┐

&nbsp;       │              MusicOrder                 │

&nbsp;       ├─────────────────────────────────────────┤

&nbsp;       │ - musicFormat : String                  │

&nbsp;       │ - bitRate : int                         │

&nbsp;       │ - durationInSec : int                   │

&nbsp;       │ - musicFormatAvailable : String\[] <<static>> │

&nbsp;       │ - bitRateAvailable : int\[] <<static>>   │

&nbsp;       │ - bitRateCost : double\[] <<static>>     │

&nbsp;       ├─────────────────────────────────────────┤

&nbsp;       │ + MusicOrder(customer,itemName,format,  │

&nbsp;       │   bitRate,durationInSec)                │

&nbsp;       │ + calculateOrderPrice():void            │

&nbsp;       │ + validateBitRate():Integer             │

&nbsp;       │ + toString():String                     │

&nbsp;       └─────────────────────────────────────────┘





**# Implementation Details**



---



###### **## Customer class**



**### validateCustomerDetails():**



This method validates the customer details and returns Boolean based on the below logic:



\- If the custName contains at least 4 characters and age is more than 18 and emailld ends with either .com or .edu, then this method returns true

\- Otherwise, return false



\*\*Note:\*\* Perform case-sensitive comparison



**\*\*Example:\*\***  

If the custName is Bobby, age is 19 and emailld is bob@xyz.com, then this method returns true



---



###### \## DigitalItemOrder class



**### generateOrderld():**



This method auto-generates and sets the orderld(String)



\- The orderld would be prefixed with the first character of the itemName(String) followed by the auto-generated value starting from 1001

\- The auto-generated value would be incremented by one for the next orderld

\- Use static variable counter appropriately to implement the auto-generation logic



\*\*Note:\*\* Perform case-sensitive operations



\*\*Example:\*\*  

If the itemName is Unforgiven then the first orderld would be U1001, second orderld generated would be n1002 if the item Name is numb



---



###### \## MusicOrder class



**### musicFormatAvailable:**



This is a static array (String \[]), where each of the elements would be the musicFormat(String)



The initial values of musicFormatAvailable are given below:



musicFormatAvailable { "mp3", "wav", "flac"}





\*\*Note:\*\*  

This array is supplied. Hence no need to code. Do not change the CASE of the elements in the array



---



**### bitRateAvailable:**



This is a static array(int\[]) that contains the bitRate (int) available



The initial values of bitRateAvailable are given below:



bitRateAvailable (192, 128, 256)





\*\*Note:\*\*  

This array is supplied. Hence no need to code



---



**### bitRateCost:**



This is a static array(double\[]) that contains the cost (double) for each bitRate available



This array has one to one correspondence with bitRateAvailable array



The initial values are given below:



bitRateCost {15.0, 10.0, 20.0}





\*\*Note:\*\*  

This array is supplied. Hence no need to code



---



**### validateBitRate():**



This method validates the bitRate(int) and returns the index(Integer) based on the following logic:



\- Check if bitRate is present as one of the elements in bitRateAvailable array

\- If it is present, return the corresponding index position of the bitRate in bitRateAvailable array

\- Otherwise return -1  (from page 11 scan)



\*\*Example:\*\*  

If bitRate is 256, then this method would return 2



---



**### calculateOrderPrice():**



This method calculates and sets the orderPrice(double) and generates the orderld(String) based on the below logic:  (from page 12 scan)



\- Invoke validateCustomerDetails() method of Customer class

\- If the musicFormat(String) is available in the musicFormatAvailable(String\[]) array and the above method returns true, then:



&nbsp;   - Invoke validateBitRate() method to get bitRateIndex

&nbsp;   - If bitRateIndex is not -1 and the durationInSec(int) is greater than 0. If so, then:



&nbsp;       - Set the baseCost (double) with the corresponding cost(double) of bitRate in bitRateCost(double\[]) array by using above obtained bitRateIndex

&nbsp;       - Add currency 3 to the baseCost for each minute of music

&nbsp;       - Note: Perform integer division

&nbsp;       - Add 5% servicetax to the updated baseCost

&nbsp;       - Set orderPrice(double) with the above calculated baseCost

&nbsp;       - Invoke generateOrderld() method



&nbsp;   - Otherwise, set orderPrice to -1.0 and orderld to NA



\- Otherwise, set orderPrice to -1.0 and orderld to NA



\*\*Note:\*\* Perform case-insensitive comparison



\*\*Example:\*\*  

If the custName is Bobby, emailld is bob@xyz.com, age is 19, itemName is Unforgiven, musicFormat is FLAC, bitRate is 256 and durationInSec is 350 then, orderPrice would be currency 36.75 and orderld would be U1001, considering it to be the first order.





