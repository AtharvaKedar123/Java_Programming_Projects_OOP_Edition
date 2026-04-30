**Practice Problem 4: Distributed Cache System**

---

**1. Problem Statement**

Design and implement a **Distributed Cache System** using object-oriented programming concepts.

* A company wants to build a mini cache system similar to Redis.
* The cache stores key-value pairs for fast access.
* Each cache entry can have a time-to-live value.
* If the cache becomes full, the least recently used entry must be removed.
* The cache system must support storing, retrieving, expiring, and evicting data by strictly following the rules described in this problem.

---

###### **2. Class Diagram**

```text
                   ----------------------------------
                   |          CacheEntry            |
                   ----------------------------------
                   | - key : String                 |
                   | - value : String               |
                   | - ttlInSeconds : int           |
                   | - createdTime : long           |
                   | - lastAccessTime : long        |
                   ----------------------------------
                   | + isExpired() : boolean        |
                   | + updateAccessTime() : void    |
                   | + getKey() : String            |
                   | + getValue() : String          |
                   ----------------------------------
                                  ▲
                                  |  HAS-A (Aggregation)
                                  ◇
                   ----------------------------------
                   |             Node               |
                   ----------------------------------
                   | - nodeId : String              |
                   | - capacity : int               |
                   | - entries : CacheEntry[]       |
                   | - size : int                   |
                   ----------------------------------
                   | + put(CacheEntry) : void       |
                   | + get(String) : CacheEntry     |
                   | + removeExpiredEntries() : void|
                   | + evictLeastRecentlyUsed():void|
                   ----------------------------------
                                  ▲
                                  |
                                  | uses
                                  |
                   ----------------------------------
                   |          LRUPolicy             |
                   ----------------------------------
                   | + findLRUIndex(                |
                   |   entries: CacheEntry[],       |
                   |   size : int ) : int           |
                   ----------------------------------
                                  ▲
                                  |
                                  | HAS-A (Aggregation)
                                  ◇
                   ----------------------------------
                   |          CacheManager          |
                   ----------------------------------
                   | - node : Node                  |
                   | - policy : LRUPolicy           |
                   ----------------------------------
                   | + addData(String,String,int)   |
                   |       : void                   |
                   | + getData(String) : String     |
                   | + displayCache() : void        |
                   ----------------------------------
```

---

### **3. Explanation of Relationships**

* Node HAS-A CacheEntry
  → This is **Aggregation**

* Node uses LRUPolicy
  → This is **Association**

* CacheManager HAS-A Node and LRUPolicy
  → This is **Aggregation**

---

### **4. Implementation Status**

| Class Name   | Implementation Status |
| ------------ | --------------------- |
| CacheEntry   | Partially implemented |
| Node         | Partially implemented |
| LRUPolicy    | Fully implemented     |
| CacheManager | Partially implemented |

---

### **5. Static / Initial Data**

* Cache entry stores:

```text
key
value
ttlInSeconds
createdTime
lastAccessTime
```

* Node has fixed capacity.
* When the number of entries exceeds capacity, LRU eviction must happen.

---

### **6. Implementation Details**

###### **i. CacheEntry Class**

**Method: isExpired()**

* This method checks whether the cache entry has expired.
* Expiry is calculated using:

```text
currentTime - createdTime >= ttlInSeconds
```

* Time must be handled in seconds.
* It returns true if the entry is expired.
* It returns false if the entry is still valid.

---

**Method: updateAccessTime()**

* Updates lastAccessTime to current time.
* This method must be called whenever data is accessed.

---

###### **ii. LRUPolicy Class**

**Method: findLRUIndex(CacheEntry[] entries, int size)**

* This method finds the least recently used cache entry.
* The entry with the smallest lastAccessTime is considered least recently used.
* It returns the index of the least recently used entry.

---

###### **iii. Node Class**

**Method: put(CacheEntry entry)**

Step-by-step logic:

* Remove expired entries first.
* If the key already exists:

  * Replace the old entry with the new entry.
* If cache is full:

  * Remove the least recently used entry.
* Add the new cache entry.

---

**Method: get(String key)**

Step-by-step logic:

* Remove expired entries first.
* Search for the key.
* If the key is found:

  * Update its lastAccessTime.
  * Return the cache entry.
* If the key is not found:

  * Return null.

---

**Method: removeExpiredEntries()**

* Checks all cache entries.
* Removes entries whose isExpired() returns true.

---

**Method: evictLeastRecentlyUsed()**

* Uses LRUPolicy to identify the least recently used entry.
* Removes that entry from cache.

---

###### **iv. CacheManager Class**

**Method: addData(String key, String value, int ttlInSeconds)**

* Creates a CacheEntry object.
* Adds it into the Node using put().

---

**Method: getData(String key)**

* Retrieves cache entry from Node.
* If entry exists:

  * Return the value.
* If entry does not exist:

  * Return `"Cache Miss"`.

---

**Method: displayCache()**

* Displays all active cache entries.

---

### **7. Sample Test Case**

**Input**

* nodeId = "NODE-1"
* capacity = 3
* addData("A", "Apple", 60)
* addData("B", "Ball", 60)
* addData("C", "Cat", 60)
* getData("A")
* addData("D", "Dog", 60)

---

**Expected Output**

```text
Data added successfully: A
Data added successfully: B
Data added successfully: C
Value Found: Apple
Cache full. Least Recently Used entry removed.
Data added successfully: D

Active Cache Entries:
A : Apple
C : Cat
D : Dog
```
