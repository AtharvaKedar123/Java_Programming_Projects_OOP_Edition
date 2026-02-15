**Problem Statement**



FastCar Rental Services rent cars and want to automate the process of final bill calculation.

Write a Java program to achieve this by following the class design given below.



**Class Diagram**

&nbsp;                        ----------------------------------

&nbsp;                        |        VehicleRental           |

&nbsp;                        ----------------------------------

&nbsp;                        | - customer : Customer           |

&nbsp;                        | - noOfKms : int                 |

&nbsp;                        | - journeyDays : int             |

&nbsp;                        ----------------------------------

&nbsp;                        | + VehicleRental(                |

&nbsp;                        |     customer : Customer,        |

&nbsp;                        |     noOfKms : int )              |

&nbsp;                        | + getJourneyDays() : int        |

&nbsp;                        | + getCustomer() : Customer     |

&nbsp;                        | + identifyJourneyDays() : int  |

&nbsp;                        | + calculateFinalAmount() :      |

&nbsp;                        |       double (abstract)         |

&nbsp;                        ----------------------------------

&nbsp;                                     ▲

&nbsp;                                     |

&nbsp;                         ----------------------------

&nbsp;                         |          CarRental       |

&nbsp;                         ----------------------------

&nbsp;                         | - carType : String       |

&nbsp;                         ----------------------------

&nbsp;                         | + CarRental(             |

&nbsp;                         |     customer : Customer, |

&nbsp;                         |     noOfKms : int,        |

&nbsp;                         |     carType : String )    |

&nbsp;                         | + calculateFinalAmount() |

&nbsp;                         |       : double            |

&nbsp;                         ----------------------------





&nbsp;       ----------------------------------      ----------------------------------

&nbsp;       |            Customer             |      |           CarDetails           |

&nbsp;       ----------------------------------      ----------------------------------

&nbsp;       | + memberCustIdArr : String\[]    |      | + carTypesArr : String\[]        |

&nbsp;       |       static                     |      |       static                   |

&nbsp;       | + memberBillAmountArr : int\[]   |      | + perDayRentsArr : int\[]        |

&nbsp;       |       static                     |      |       static                   |

&nbsp;       | - custName : String             |      ----------------------------------

&nbsp;       | - custId : String               |      | + identifyPerDayRent(          |

&nbsp;       ----------------------------------      |     carType : String ) : int   |

&nbsp;       | + Customer(                     |      |       static                   |

&nbsp;       |     custId : String,            |      ----------------------------------

&nbsp;       |     custName : String )         |

&nbsp;       | + getCustId() : String          |

&nbsp;       | + getCustName() : String        |

&nbsp;       | + calculateDiscount(            |

&nbsp;       |     rentalAmount : float )      |

&nbsp;       |       : float                   |

&nbsp;       | + upgradeCustomer(              |

&nbsp;       |     rentalAmount : float )      |

&nbsp;       |       : void                    |

&nbsp;       ----------------------------------



Implementation Details



Class Name	Implementation Status

VehicleRental	Fully implemented

Customer	Partially implemented

CarRental	Partially implemented

CarDetails	Partially implemented



###### Customer Class()

**memberCustIdArr**

* 
* A static String array containing member customer IDs.
* 
* static String\[] memberCustIdArr = {
* &nbsp;   "1001P", "1051R", "1072P", "2019R", "2913R", "2931P"
* };
* 
* 
* Values are already supplied
* Do not modify the array
* Do not change the case of elements



**memberBillAmountArr**



* A static int array containing corresponding bill amounts.
* static int\[] memberBillAmountArr = {
* &nbsp;   2050, 5345, 6896, 9100, 4500, 3234
* };
* 
* One-to-one correspondence with memberCustIdArr
* Do not modify the array



**calculateDiscount(float rentalAmount)**



* Check if custId exists in memberCustIdArr (case-insensitive)
* Identify customer type using last character of custId
* 
* custType	Discount
* 'P'	15% of rentalAmount
* 'R'	10% of rentalAmount
* 
* If custId is not present, return -1.0
* Return calculated discount amount



**Example:**

* If custType = 'R' and rentalAmount = 120.0
* → discount = 12.0



**Assumptions**



* custType will be either 'P' or 'R'
* No validation required for assumption
* Case-insensitive comparison



###### CarDetails Class()



**carTypesArr**



* static String\[] carTypesArr = {
* &nbsp;   "Hatch-back", "Sedan", "SUV"
* };
* 
* perDayRentsArr
* static int\[] perDayRentsArr = {
* &nbsp;   3500, 5000, 6000
* };
* 
* One-to-one correspondence with carTypesArr

Do not modify values or case



**identifyPerDayRent(String carType)**



* Compare carType with elements of carTypesArr (case-insensitive)
* Return corresponding per-day rent
* If no match found, return -1



Example:

* If carType = "SUV" → return 6000



###### CarRental Class()

**calculateFinalAmount()**



* Invoke identifyPerDayRent() of CarDetails
* Invoke identifyJourneyDays() of VehicleRental



* If either value is -1, return -1.0
* **Calculate:**
* rentalAmount = journeyDays × perDayRent
* Invoke calculateDiscount() of Customer
* If discount is -1, finalAmount = -1.0



**Else:**



* finalAmount = rentalAmount - discount
* Upgrade customer using upgradeCustomer(finalAmount)
* Return finalAmount



**Assumptions**



* All cost values are in currency
* Journeydays calculation logic is already implemented
* No validation required for assumptions



Example



**If:**



* custId = "1051R"
* custName = "Mike"
* noOfKms = 18
* carType = "SUV"



**Then:**



* Final Amount = 108.0
