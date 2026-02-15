# Practice Problem – Player Rating System

## Problem Statement

### Problem Description:

A very popular cricket magazine is being published quarterly in the month of March, June, September and December.  
The magazine also ranks the cricket players especially batsman based on their performance in the previous quarter.  
The magazine publisher wants to automate the rating point calculation and ranking process.

Write a Java program to implement the below class diagram.

---

## Class Diagram

```
                         Player
------------------------------------------------
- noOfMatches : int
- pointsEarned : int
------------------------------------------------
+ Player(noOfMatches : int)
+ getPointsEarned() : int
+ setPointsEarned(pointsEarned : int) : void
+ getNoOfMatches() : int
+ validateNoOfMatches() : Boolean
+ toString() : String
------------------------------------------------
            ▲
            |
         Batsman
------------------------------------------------
- runsScored : int
- centuryCount : int
- batsmanRank : int
------------------------------------------------
+ Batsman(noOfMatches : int,
          runsScored : int,
          centuryCount : int)
+ getRunsScored() : int
+ getCenturyCount() : int
+ getBatsmanRank() : int
+ validateBatsmanRecord() : Boolean
+ calculatePoints(index : int) : void
+ toString() : String

------------------------------------------------
                    PlayerDetails
------------------------------------------------
+ playersPointsArr : int[] (static)
------------------------------------------------
+ swap(numbers : int[], firstIndex : int,
       secondIndex : int) : void (static)
+ sort(pointsArr : int[]) : int[] (static)
+ rankPlayer(pointsEarned : int, index : int)
  : Integer (static)
```

---

## Notes

- Do not include any extra instance variables in the given classes.
- **Case sensitive** comparison is required to be done wherever applicable.
- Do not change any value or case of the given variables.

---

## Implementation Details

| Class Name      | Status of implementation |
|-----------------|--------------------------|
| Player          | Partially implemented    |
| Batsman         | Partially implemented    |
| PlayerDetails   | Partially implemented    |

---

## PlayerDetails Class

### playersPointsArr

- This is a static array containing the `playerPoints(int)` as its elements.
- The initial values of the array are as follows:

```
playersPointsArr = {1000, 934, 800, 550}
```

**Note:** This array is supplied. Trainees need not code this array.

---

### rankPlayer(pointsEarned, index)

- This is a static method which accepts `pointsEarned (int)` and `index (int)` as input parameters, updates `playersPointsArr` and returns the `playerRank (int)`.
- Update the `pointsEarned` in the `playersPointsArr` at the given index value.
- Invoke the method `sort()` to sort the `playersPointsArr` in descending order.
- Identify the position of the `pointsEarned` in the `playersPointsArr` as `playerRank`.
- Return the `playerRank`.

**Note:** playerRank of the playerPoints found at index 0 (zero) is 1 and so on.

**Example:**  
If `playersPointsArr` is `{1000, 934, 800, 550}` and `playerPoints` is `915`,  
then the updated `playersPointsArr` would be `{934, 915, 800, 550}`  
and this method would return `2` as playerRank.

---

## Player Class

### validateNoOfMatches()

- This method validates the `noOfMatches` played by the player and returns Boolean.
- If the `noOfMatches` played is more than `0` and less than or equal to `100`, then return `true`.
- Otherwise return `false`.

**Example:**  
If the `noOfMatches` is `10`, then this method would return `true`.

---

## Batsman Class

### validateBatsmanRecord()

- This method validates `runsScored` and `centuryCount` and returns Boolean.
- Validation of `runsScored` and `centuryCount` is done based on following rules:
  - `centuryCount` should be greater than or equal to `0`
  - `centuryCount` should be less than or equal to `noOfMatches` played
  - Total runs score by `centuryCount` i.e., `centuryCount * 100`, should be less than or equal to `runsScored`
- If all the above conditions satisfy, then this method should return `true`, otherwise return `false`.

---

### calculatePoints(index)

- This method accepts `index (int)` as parameter and calculates, sets `pointsEarned` and `batsmanRank`.
- It invokes `validateNoOfMatches()` of Player class and `validateBatsmanRecord()` methods.
- If any of the above method returns false, then set `pointsEarned` and `batsmanRank` with `-1`.
- Otherwise:
  - It calculates and sets `pointsEarned` based on following rule:
    - 2 points are awarded for every `runsScored` and 25 points for every `centuryCount`.
  - It invokes `rankPlayer()` method of PlayerDetails class by passing above calculated `pointsEarned` and index value to get the `rank (int)`.
  - It sets the `batsmanRank` with identified rank in above step.

**Note:** Here index refers to player points.

**Example:**  
If `noOfMatches` played is `10`, `runsScored` is `420` and `centuryCount` is `3`,  
then this method would set `pointsEarned` as `915`, `batsmanRank` as `2`  
and updated `playersPointsArr` would be `{934, 915, 800, 550}`.
