# Practice Problem 1 – Building For Rent

## Problem Statement

AtoZ real estate has come up with an online facility for providing buildings for rent.
The process of calculation of advance amount has to be automated.

The class design for the same is given below.

---

## Class Diagram

```
                BuildingForRent
------------------------------------------------
- buildingDimensions : int
- advanceAmount      : float
------------------------------------------------
+ BuildingForRent(buildingDimensions : int)
+ getBuildingDimensions() : int
+ getAdvanceAmount() : float
+ setAdvanceAmount(advanceAmount : float) : void
+ calculateAdvanceAmount() : void
+ toString() : String
------------------------------------------------
            ▲
            |
---------------------------    -----------------------------
|        ShopForRent       |  |        HouseForRent        |
---------------------------    -----------------------------
- shopType : char               - noOfBedrooms : int
---------------------------    - facilities : String[]
+ ShopForRent(                 - facilitiesDetailsArr :
  buildingDimensions : int,       String[][] (static)
  shopType : char)
+ identifyShopRent() : int
+ calculateAdvanceAmount() : void
+ toString() : String
                                + HouseForRent(
                                  buildingDimensions : int,
                                  noOfBedrooms : int,
                                  facilities : String[])
                                + getNoOfBedrooms() : int
                                + getFacilities() : String[]
                                + validateNoOfBedrooms() : Boolean
                                + calculateAdvanceAmount() : void
                                + toString() : String
```

---

## Notes

- Do not include any extra instance/static variables or instance/static methods in the given classes.
- Case insensitive comparison is required unless specified explicitly.
- Do not change any value or case of the given variables.
- Read notes and examples carefully to build a better understanding of the logic.

---

## Implementation Details

| Class Name        | Implementation Details |
|------------------|------------------------|
| BuildingForRent  | Fully implemented      |
| HouseForRent     | Partially implemented  |
| ShopForRent      | Partially implemented  |

---

## HouseForRent Class

### facilitiesDetailsArr

- This is a static 2D array `String[][]`.
- First row contains facility names.
- Second row contains facility charges.
- Each facility has a one-to-one correspondence with facilityCharge.

```
facilitiesDetailsArr = {
  {"Security", "Parking", "Amusement-Park"},
  {"2000", "3400", "2900"}
}
```

**Note**
- The above 2D array is supplied. Hence no need to code.
- Do not change the CASE of elements in the array.

---

### validateNoOfBedrooms()

- Validates `noOfBedrooms`.
- If `noOfBedrooms` is between 0 and 6 (boundary values excluded), return `true`.
- Otherwise return `false`.

**Example:**  
If `noOfBedrooms` is 5, this method should return `true`.

---

### calculateAdvanceAmount()

- Invoke `calculateAdvanceAmount()` of `BuildingForRent` class.
- Set `basicAmount` with `advanceAmount` of `BuildingForRent`.
- Invoke `validateNoOfBedrooms()`.
- If `basicAmount == -1.0` OR `validateNoOfBedrooms()` returns `false`
  OR `facilities` is `null`, then set `advanceAmount = -1.0`.

Otherwise:
- For every facility in `facilities`:
  - Check if it exists in `facilitiesDetailsArr` (case insensitive).
  - Identify corresponding facilityCharge.
- Add all facilityCharges to obtain `totalFacilityCharge`.

**Example:**  
If facilities are `{ "Parking", "Security" }`  
Total facility charge = 5400

- If `totalFacilityCharge != 0.0`:
  - bedroomCharges = `noOfBedrooms * 20000`
  - advanceAmount = `basicAmount + totalFacilityCharge + bedroomCharges`
- Else set `advanceAmount = -1.0`.

**Example:**  
If `buildingDimensions = 2000`, `noOfBedrooms = 5`,  
`facilities = { "Parking", "Security" }`  
Advance amount = 125400.0

---

## ShopForRent Class

### identifyShopRent()

| shopType | shopRent |
|--------|----------|
| 'A'    | 45000    |
| 'B'    | 30000    |
| 'C'    | 25000    |
| Others | -1       |

**Note:** Case insensitive comparison.

---

### calculateAdvanceAmount()

- Invoke `calculateAdvanceAmount()` of `BuildingForRent`.
- Set `basicAmount` with `advanceAmount` of `BuildingForRent`.
- Identify `shopRent`.
- If `basicAmount == -1.0` OR `shopRent == -1`, set `advanceAmount = -1.0`.
- Otherwise:
  - `advanceAmount = basicAmount + shopRent`.

**Example:**  
If `buildingDimensions = 10000` and `shopType = 'A'`,  
Advance amount = 145000.0
