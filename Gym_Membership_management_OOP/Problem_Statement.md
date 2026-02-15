**Practice Problem 2: Gym Management System**

**1. Problem Statement**



Design and implement a Gym Management System using object-oriented programming concepts.



* The gym offers different membership plans to its customers.
* The total bill amount for a member depends on:
* The membership plan type
* The membership duration



* Optional personal trainer service (for monthly members)
* Optional yearly discount (for yearly members)
* The system must calculate the final bill amount by strictly following the rules described in this problem.



###### 2\. Class Diagram



* The class diagram below clearly shows:
* Inheritance (IS-A relationship)
* Aggregation (HAS-A relationship)



&nbsp;                   ----------------------------------

&nbsp;                   |         MemberDetails           |

&nbsp;                   ----------------------------------

&nbsp;                   | + validPrefixArr : String\[]     |

&nbsp;                   |        (static)                |

&nbsp;                   | + planTypeArr : String\[]        |

&nbsp;                   |        (static)                |

&nbsp;                   | + basePriceArr : int\[]          |

&nbsp;                   |        (static)                |

&nbsp;                   | - memberId : String             |

&nbsp;                   | - memberName : String           |

&nbsp;                   ----------------------------------

&nbsp;                   | + validateMemberId() : boolean  |

&nbsp;                   ----------------------------------

&nbsp;                                  ▲

&nbsp;                                  |  HAS-A (Aggregation)

&nbsp;                                  ◇

&nbsp;                   ----------------------------------

&nbsp;                   |          Membership             |

&nbsp;                   |          (Abstract)             |

&nbsp;                   ----------------------------------

&nbsp;                   | # member : MemberDetails        |

&nbsp;                   | # planType : String             |

&nbsp;                   | # durationInMonths : int        |

&nbsp;                   ----------------------------------

&nbsp;                   | + getPlanIndex(                 |

&nbsp;                   |     type : String ) : int       |

&nbsp;                   | + calculateBillAmount() :       |

&nbsp;                   |     double (abstract)           |

&nbsp;                   ----------------------------------

&nbsp;                                  ▲

&nbsp;                   --------------------------------

&nbsp;                   |                              |

&nbsp;       IS-A (Inheritance)                IS-A (Inheritance)

&nbsp;                   |                              |

&nbsp;       ----------------------------     ----------------------------

&nbsp;       |      MonthlyMember       |     |       YearlyMember       |

&nbsp;       ----------------------------     ----------------------------

&nbsp;       | - hasPersonalTrainer: boolean | - yearlyDiscount : float |

&nbsp;       | - trainerFee : float          ----------------------------

&nbsp;       ----------------------------     | + calculateBillAmount()  |

&nbsp;       | + calculateBillAmount()  |     |      : double            |

&nbsp;       |      : double            |     ----------------------------

&nbsp;       ----------------------------



**3. Explanation of Relationships**



* MonthlyMember IS-A Membership
* YearlyMember IS-A Membership
* → This is Inheritance



**Membership HAS-A MemberDetails**

* → This is Aggregation
* → A member exists independently, and the membership refers to the member



4\. Implementation Status

Class Name	Implementation Status

MemberDetails	Partially implemented

Membership	Fully implemented (Abstract)

MonthlyMember	Partially implemented

YearlyMember	Partially implemented



###### 5\. Static Data (MemberDetails Class)



The following arrays are static and must be shared across all objects.



* i. Valid Member ID Prefixes
* validPrefixArr = {"GYM-P", "GYM-G", "GYM-S"}





**A member ID is considered valid only if it starts with one of the above prefixes.**



**ii. Membership Plan Types**

* planTypeArr = {"Basic", "Pro", "Elite"}



**iii. Monthly Base Prices**

* basePriceArr = {1200, 2500, 4500}





**Each value in basePriceArr corresponds to the plan at the same index in planTypeA**rr



**Example:**



* Basic → 1200
* Pro → 2500
* Elite → 4500



6\. Implementation Details



###### i. MemberDetails Class()

Method: validateMemberId()



* This method checks whether the member ID is valid
* It returns true if the memberId starts with any prefix in validPrefixArr
* It returns false if the member ID does not start with any valid prefix



###### ii. Membership Class (Abstract)

**Method: getPlanIndex(String type)**



* This method finds the index of the given plan type in planTypeArr
* The comparison must be case-insensitive

**It returns:**

* The plan index if the plan exists
* -1 if the plan type is invalid



Method: calculateBillAmount()



* This is an abstract method
* It must be implemented in all subclasses
* It returns the final bill amount as a double



###### iii. MonthlyMember Class

**Method: calculateBillAmount()**



* Step-by-step logic:
* Validate the member ID using validateMemberId()
* Retrieve the plan index using getPlanIndex()
* If validation fails or the plan index is invalid, return -1



**Calculate the base cost**:



* BaseCost = basePrice × durationInMonths
* If hasPersonalTrainer is true, add:
* trainerFee × durationInMonths
* Apply a 5% service tax:
* FinalAmount = BaseCost × 1.05
* Return the final bill amount



###### iv. YearlyMember Class()

**Method: calculateBillAmount()**



**Step-by-step logic:**



* Validate the member ID
* Retrieve the plan index
* If validation fails or the plan index is invalid, return -1
* Calculate the annual cost:
* AnnualCost = basePrice × 12
* Apply the yearly discount:
* DiscountedAmount = AnnualCost − (AnnualCost × yearlyDiscount / 100)
* Add a fixed administration fee of 200
* Return the final bill amount



###### 7\. Sample Test Case

**Input**

* memberName = "Alice"
* memberId = "GYM-P100"
* planType = "Pro"
* durationInMonths = 2
* hasPersonalTrainer = true
* trainerFee = 500.0



**Expected Output**

* Total Bill Amount: 6300.0
