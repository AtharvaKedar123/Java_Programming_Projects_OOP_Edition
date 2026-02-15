**# Description**



**AdTrends, an advertising agency wants to automate its booking process based on various parameters.**  

**Implement the class diagram given below to achieve the same.**







**## Class Diagram**



┌───────────────────────────────────────────────┐

│               <<abstract>>                     │

│                  AdAgency                     │

├───────────────────────────────────────────────┤

│ - adAvailabilityArr : String\[]   {static}     │

│ - counter            : int        {static}     │

│ - client             : Client                  │◄──────────────┐

│ - bookingId          : String                  │               │

│ - adPackage          : String                  │               │

│ - clientCollab       : boolean                 │               │

│ - totalBillAmount    : int                     │               │

├───────────────────────────────────────────────┤               │

│ + AdAgency(Client, String, boolean)            │               │

│ + getAdPackage()        : String               │               │

│ + getClient()           : Client               │               │

│ + getClientCollab()     : boolean              │               │

│ + getTotalBillAmount()  : int                  │               │

│ + getBookingId()        : String               │               │

│ + getAdAvailabilityArr(): String\[] {static}   │               │

│ + setBookingId(String)  : void                 │               │

│ + setTotalBillAmount(int): void                │               │

│ + generateBookingId()   : void                 │               │

│ + identifyWaveOffPercent(): Integer            │               │

│ + calculateTotalBill()  : void  {abstract}     │               │

└───────────────────────────────────────────────┘               │

&nbsp;                ▲                                               │

&nbsp;                │ extends                                       │

&nbsp;                │                                               │

┌───────────────────────────────────────────────┐               │

│              OutdoorAdAgency                  │               │

├───────────────────────────────────────────────┤               │

│ - outdoorAdTypeArr      : String\[] {static}   │               │

│ - outdoorAdTypeCostArr  : int\[]    {static}   │               │

│ - outdoorAdType         : String               │               │

│ - displayTimeFrame      : int                  │               │

│ - quantity              : int                  │               │

├───────────────────────────────────────────────┤               │

│ + OutdoorAdAgency(Client, String, boolean,    │               │

│   String, int, int)                            │               │

│ + calculateTotalBill() : void                 │               │

└───────────────────────────────────────────────┘               │

&nbsp;                                                                │

&nbsp;                                                                │

&nbsp;                                          ┌─────────────────────▼─────────────────────┐

&nbsp;                                          │                   Client                    │

&nbsp;                                          ├─────────────────────────────────────────────┤

&nbsp;                                          │ - clientName    : String                    │

&nbsp;                                          │ - adRequirement : String                    │

&nbsp;                                          │ - budget        : int                       │

&nbsp;                                          │ - paymentType   : String                    │

&nbsp;                                          ├─────────────────────────────────────────────┤

&nbsp;                                          │ + Client(String, String, int, String)        │

&nbsp;                                          │ + getAdRequirement() : String               │

&nbsp;                                          │ + getBudget()        : int                  │

&nbsp;                                          │ + getPaymentType()   : String               │

&nbsp;                                          │ + validateClient()   : boolean              │

&nbsp;                                          │ + validateClientAdRequirement(): boolean    │

&nbsp;                                          └─────────────────────────────────────────────┘







\## Notes



\- Do not include any extra instance or static variables and instance or static methods in the given classes.

\- Case-insensitive comparison is to be done if not explicitly mentioned.

\- Do not change any value or case of the given variables.

\- Read notes and examples for better understanding of the logic.

\- In the derived classes, the order of passing arguments to the constructor would be:

&nbsp; - Base class variables followed by derived class variables.



---



\## Implementation Details



\- AdAgency : Partially implemented

\- OutdoorAdAgency : Partially implemented

\- Client : Partially implemented



---



\## AdAgency Class Details



\### adAvailabilityArr



\- This is a static array (String\[]) which contains availableServiceArea (String) as its elements.

\- Initial value of adAvailabilityArr:



{"Product", "Service", "Marketing", "Brand"}





\#### Note

\- This array is supplied and hence, no need to code.

\- Do not change the CASE of the elements in the array.



---



\### generateBookingId()



\- This method auto-generates and sets bookingId (String).

\- The bookingId must be prefixed by the first character of adRequirement in uppercase,

&nbsp; followed by the auto-generated value starting from 101.

\- The auto-generated value would be incremented by one for the next bookingId.

\- Use static variable counter appropriately to implement the auto-generation logic.



\#### Example

\- First bookingId: S101 (if adRequirement is service)

\- Second bookingId: B102 (if adRequirement is Brand)



---



\### identifyWaveOffPercent()



This method finds and returns the waveOffPercent (Integer) based on the budget (int)

provided by the client.



| Budget Range                                   | waveOffPercent |

|-----------------------------------------------|----------------|

| Less than or equal to 200000                  | 0              |

| Between 200000 (excluded) and 500000 (included) | 5              |

| Greater than 500000                           | 10             |



\#### Example

If the budget is 500000, then the waveOffPercent would be 5.



---



\## Client Class Details



\### validateClientAdRequirement()



\- This method checks if the adRequirement (String) is present in the adAvailabilityArr

&nbsp; of AdAgency class.

\- Return true if present, else return false.

\- Perform case-insensitive string comparison.



\#### Example

If adRequirement is "service", the method returns true.



---



\## OutdoorAdAgency Class Details



\### outdoorAdTypeArr



\- This is a static array (String\[]) which has adType (String) as its elements:



{"Billboard", "Transit", "Banner"}





\#### Note

\- This array is supplied and hence, no need to code.

\- Do not change the CASE of the elements in the array.



---



\### outdoorAdTypeCostArr



\- This is a static array (int\[]) which has costPerAdType (int) as its elements:



{250, 170, 150}





\- This array has one-to-one correspondence with outdoorAdTypeArr.

\- This array is supplied and hence, no need to code.



---



\## calculateTotalBill()



This method generates and sets the bookingId (String) and calculates and sets the

totalBillAmount (int) paid by the client.



\### Logic



\- Invoke validateClient() and validateClientAdRequirement() methods of Client class.

\- If both return true and outdoorAdType is present in outdoorAdTypeArr:



&nbsp; - Identify the corresponding costPerAdType from outdoorAdTypeCostArr.

&nbsp; - Invoke generateBookingId().

&nbsp; - Invoke identifyWaveOffPercent().

&nbsp; - Calculate adCost:



&nbsp;   ```

&nbsp;   adCost = costPerAdType \* quantity \* displayTimeFrame

&nbsp;   ```



&nbsp; - If adPackage is "Digital", add 5000.

&nbsp; - If adPackage is "Traditional", add 2000.

&nbsp; - If clientCollab is true and paymentType is "Prepaid",

&nbsp;   apply the waveOffPercent to the adCost.

&nbsp; - Set totalBillAmount with the obtained adCost.

&nbsp; - If totalBillAmount exceeds budget:

&nbsp;   - set bookingId to "NA"

&nbsp;   - set totalBillAmount to -1



\- Otherwise (if validation fails or outdoorAdType not found):

&nbsp; - set bookingId to "NA"

&nbsp; - set totalBillAmount to -1



---



\## Additional Notes



\- Case-sensitive comparison for:

&nbsp; - outdoorAdType

&nbsp; - paymentType ("Prepaid" or "Postpaid")

\- Case-insensitive comparison for:

&nbsp; - adPackage ("Digital" or "Traditional")

\- Valid values are assumed for adPackage and paymentType.

















