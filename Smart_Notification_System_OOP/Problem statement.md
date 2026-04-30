**Practice Problem 10: Smart Notification System**

---

**1. Problem Statement**

Design and implement a **Smart Notification System** using object-oriented programming concepts.

* A communication platform wants to send notifications to users based on priority and user preferences.
* Each user can receive notifications through different channels such as Email, SMS, or App.
* Every notification has a priority level.
* High-priority notifications must be sent before low-priority notifications.
* The system must support notification batching and channel-based delivery.
* The notification manager must process and send notifications by strictly following the rules described in this problem.

---

###### **2. Class Diagram**

```text
                   ----------------------------------
                   |             User               |
                   ----------------------------------
                   | - userId : String              |
                   | - userName : String            |
                   | - preferredChannel : String    |
                   ----------------------------------
                   | + validateUser() : boolean     |
                   | + getPreferredChannel():String |
                   | + getUserName() : String       |
                   | + getUserId() : String         |
                   ----------------------------------
                                  ▲
                                  |  HAS-A (Aggregation)
                                  ◇
                   ----------------------------------
                   |         Notification           |
                   ----------------------------------
                   | - notificationId : String      |
                   | - user : User                  |
                   | - message : String             |
                   | - priority : int               |
                   | - status : String              |
                   ----------------------------------
                   | + validateNotification():boolean|
                   | + markAsSent() : void          |
                   | + getPriority() : int          |
                   | + getUser() : User             |
                   | + getMessage() : String        |
                   | + getStatus() : String         |
                   ----------------------------------
                                  ▲
                                  |
                                  | uses
                                  |
                   ----------------------------------
                   |            Channel             |
                   ----------------------------------
                   | + supportedChannels:String[]   |
                   |        (static)                |
                   ----------------------------------
                   | + validateChannel(String)      |
                   |       : boolean                |
                   | + send(Notification) : void    |
                   ----------------------------------
                                  ▲
                                  |
                                  | HAS-A (Aggregation)
                                  ◇
                   ----------------------------------
                   |      NotificationManager       |
                   ----------------------------------
                   | - notifications : Notification[]|
                   | - channel : Channel            |
                   | - count : int                  |
                   ----------------------------------
                   | + addNotification(Notification)|
                   |       : void                   |
                   | + processNotifications() : void|
                   | + displayReport() : void       |
                   ----------------------------------
```

---

### **3. Explanation of Relationships**

* Notification HAS-A User
  → This is **Aggregation**

* Channel uses Notification
  → This is **Association**

* NotificationManager HAS-A Notification
  → This is **Aggregation**

* NotificationManager HAS-A Channel
  → This is **Aggregation**

---

### **4. Implementation Status**

| Class Name          | Implementation Status |
| ------------------- | --------------------- |
| User                | Partially implemented |
| Notification        | Partially implemented |
| Channel             | Fully implemented     |
| NotificationManager | Partially implemented |

---

### **5. Static Data**

The following array is static and must be shared across all objects.

###### **i. Supported Notification Channels**

```text
supportedChannels = {"Email", "SMS", "App"}
```

A preferred channel is considered valid only if it is present in the above array.

---

### **6. Implementation Details**

###### **i. User Class**

**Method: validateUser()**

* This method checks whether the user is valid.
* A user is valid only if:

  * userId starts with `"USR-"`
  * preferredChannel is present in supportedChannels
* The channel comparison must be case-insensitive.
* It returns true if the user is valid.
* It returns false if the user is invalid.

---

###### **ii. Notification Class**

**Method: validateNotification()**

* This method checks whether the notification is valid.
* A notification is valid only if:

  * notificationId starts with `"NOT-"`
  * message length is greater than or equal to 5
  * priority is between 1 and 5
  * user is valid
* Priority meaning:

```text
1 = Lowest Priority
5 = Highest Priority
```

**It returns:**

* true if notification is valid
* false if notification is invalid

---

**Method: markAsSent()**

* Sets notification status as:

```text
SENT
```

---

###### **iii. Channel Class**

**Method: validateChannel(String channelName)**

* This method checks whether the channelName exists in supportedChannels.
* The comparison must be case-insensitive.

**It returns:**

* true if channel is supported
* false if channel is not supported

---

**Method: send(Notification notification)**

* Sends notification through the user’s preferred channel.
* If notification is valid:

  * Mark notification as sent.
  * Display message:

```text
Notification sent through <preferredChannel>
```

---

###### **iv. NotificationManager Class**

**Method: addNotification(Notification notification)**

* Adds notification into notifications array.
* Only valid notifications must be added.
* If notification is valid:

  * Add it to the array.
* If notification is invalid:

  * Do not add it.

---

**Method: processNotifications()**

Step-by-step logic:

* Process notifications based on priority.
* Highest priority notifications must be processed first.
* Priority order must be:

```text
5 → 4 → 3 → 2 → 1
```

* For each valid notification:

  * Send notification using Channel class.

---

**Method: displayReport()**

* Displays:

  * Notification ID
  * User name
  * Preferred channel
  * Priority
  * Status

---

### **7. Sample Test Case**

**Input**

* User1:

  * userId = "USR-101"
  * userName = "Aarav"
  * preferredChannel = "Email"

* User2:

  * userId = "USR-102"
  * userName = "Riya"
  * preferredChannel = "SMS"

* Notification1:

  * notificationId = "NOT-101"
  * message = "Server down"
  * priority = 5

* Notification2:

  * notificationId = "NOT-102"
  * message = "Daily report generated"
  * priority = 2

---

**Expected Output**

```text
Notification sent through Email
Notification sent through SMS

Notification Report:
Notification ID: NOT-101
User Name: Aarav
Preferred Channel: Email
Priority: 5
Status: SENT

Notification ID: NOT-102
User Name: Riya
Preferred Channel: SMS
Priority: 2
Status: SENT
```
