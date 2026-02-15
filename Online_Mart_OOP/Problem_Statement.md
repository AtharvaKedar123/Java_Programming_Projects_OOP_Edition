
# Practice Problem 3

## Problem Statement

ABC Mart has made its service online for few of the products available in the mart by providing additional online discount and courier service for shipping orders. The process of calculating order price, shipment of order request, and providing the tracking id to customers has to be automated.

The class design for the same is given below.

---

## Class Diagram

```
                         -----------------------------
                         |            Mart           |
                         -----------------------------
                         | + itemNameArr : String[]  |
                         |       static              |
                         | + itemPriceArr : float[] |
                         |       static              |
                         | + itemQuantityArr : int[]|
                         |       static              |
                         -----------------------------
                         | + findPricePerItem(       |
                         |     itemName : String )   |
                         |       : float             |
                         -----------------------------
                                   ▲
                                   |
                    ----------------------------------
                    |          OnlineMart            |
                    ----------------------------------
                    | - onlineDiscountPercentage :   |
                    |       int                      |
                    | - order : Order                |
                    ----------------------------------
                    | + OnlineMart(                  |
                    |     order : Order )            |
                    | + getOrder() : Order           |
                    | + setOrder(order : Order)      |
                    | + findPricePerItem(            |
                    |     itemName : String )        |
                    |       : float                  |
                    | + checkItemAvailability() :   |
                    |       int                      |
                    | + shipOrder() : void           |
                    | + toString() : String          |
                    ----------------------------------


        ----------------------------------
        |              Order               |
        ----------------------------------
        | - trackingId : String            |
        | - itemName : String              |
        | - quantityRequired : int         |
        | - paymentMode : String           |
        | - orderPrice : double            |
        | - courierId : String             |
        ----------------------------------
        | + Order(                         |
        |     itemName : String,           |
        |     quantityRequired : int,      |
        |     paymentMode : String )       |
        | + getTrackingId() : String       |
        | + setTrackingId(trackingId :     |
        |       String) : void             |
        | + getItemName() : String         |
        | + setItemName(itemName :         |
        |       String) : void             |
        | + getQuantityRequired() : int   |
        | + setQuantityRequired(           |
        |       quantityRequired : int )   |
        | + getPaymentMode() : String      |
        | + setPaymentMode(                |
        |       paymentMode : String )     |
        | + getOrderPrice() : double       |
        | + setOrderPrice(                 |
        |       orderPrice : double )      |
        | + generateTrackingId() : String |
        | + toString() : String            |
        ----------------------------------
```

---

## Notes

- Do not include any extra instance/static variables/instance/static methods in the given classes
- Case sensitive comparison is required to be done wherever applicable
- Do not change any value or case of the given variables
- Result values and computation should be in valid currency format

---

## Implementation Details

| Class Name  | Implementation Details |
|------------|------------------------|
| Mart       | Fully implemented      |
| OnlineMart | Partially implemented  |
| Order      | Partially implemented  |

---

## Order Class

### generateTrackingId()

- This method auto-generates and sets the trackingId (String)
- The trackingId must be prefixed with "TR" in uppercase followed by auto-generated value starting from 1000
- The auto-generated value should be incremented by 1 for the next trackingId
- Use **static variable counter** appropriately to implement auto-generation logic

**Example:**  
The first trackingId would be "TR1000", second trackingId generated would be "TR1001" and so on

---

## Mart Class

### itemNameArr

- This is a static array (String[]) which has the itemName (String) as its elements
- The initial values of the itemNameArr is as below:

```java
itemNameArr = { "Chocolate", "Perfume", "Bouquet", "Apparel" };
```

#### Note:
- This array is supplied and hence no need to code
- Do not change the CASE of the elements of the array

---

### itemPriceArr

- This is a static array (float[]) which has the unitPrice (float) as its elements
- The initial values of the itemPriceArr is as below:

```java
itemPriceArr = { 200.0f, 400.0f, 150.0f, 300.0f };
```

#### Note:
- This array is supplied and hence no need to code

---

### itemQuantityArr

- This is a static array (int[]) which has the quantity (int) as its elements
- The initial values of the itemQuantityArr is as below:

```java
itemQuantityArr = { 10, 20, 30, 40 };
```

#### Note:
- This array is supplied and hence no need to code
- All three arrays have one-to-one correspondence, i.e., the unitPrice of "Bouquet" is 150.0 currency and there are 30 pieces (quantity) available in the Mart

---

## OnlineMart Class

### checkItemAvailability()

- This method validates the itemName (String) of the order class and returns the quantityRequired
- Check if the itemName of the order is available in the itemNameArr of the Mart class
- Note: Perform case sensitive comparison
- If not available, return -1
- Otherwise,
  - Check if sufficient quantityRequired is available in itemQuantityArr for the order
  - If not available, return -1
  - Otherwise,
    - Update the itemQuantityArr of Mart class by deducting the quantityRequired from existing value
    - Return quantityRequired for the order

**Example 1:**  
If itemName is "Bouquet", quantityRequired is 20 and itemQuantityArr is {10, 20, 30, 40}, this method updates the itemQuantityArr as {10, 20, 10, 40} and returns 20

**Example 2:**  
If itemName is "Blanket", quantityRequired is 20 and itemQuantityArr is {10, 20, 30, 40}, this method returns -1

**Example 3:**  
If itemName is "Bouquet", quantityRequired is 31 and itemQuantityArr is {10, 20, 30, 40}, this method returns -1

---

### findPricePerItem(String itemName)

- This is an overridden method which takes itemName (String) as input parameter and returns the pricePerItem (float) as per the logic below
- Identify unitPrice (float) by invoking the findPricePerItem() method of the Mart class by passing itemName
- Check if unitPrice per item is -1.0f, set the value of pricePerItem to -1.0f
- Otherwise,
  - Invoke the identifyOnlineDiscount() method to get onlineDiscountPercentage
  - Check if onlineDiscountPercentage is -1, if so, set the value of pricePerItem to -1.0f
  - Otherwise,
    - Calculate discount on unitPrice per item using onlineDiscountPercentage
    - Calculate the pricePerItem by deducting the above step from unitPrice per item

- Return pricePerItem

**Example 1:**  
If itemName requested is "Bouquet" and paymentMode is provided as "Prepaid", the pricePerItem is 142.5 currency

**Example 2:**  
If itemName requested is "Blanket" and paymentMode is provided as "Prepaid", the pricePerItem is -1

---

### shipOrder()

- This method calculates the orderPrice of order and generates trackingId
- If either quantity is -1 or pricePerItem is -1.0f, set orderPrice to -1.0 and trackingId to "NA" in uppercase
- Otherwise,
  - Calculate and set orderPrice of order as the product of quantity and pricePerItem
  - Invoke generateTrackingId() method of Order class to generate trackingId

**Example 1:**  
If an order where itemName requested is "Bouquet", quantityRequired is 20 and paymentMode is "Prepaid", the orderPrice would be 2850.0 currency and trackingId would be "TR1000"

**Example 2:**  
If an order where itemName requested is "Blanket", quantityRequired is 20 and paymentMode is "Prepaid", the orderPrice would be -1.0 and trackingId would be "NA"
