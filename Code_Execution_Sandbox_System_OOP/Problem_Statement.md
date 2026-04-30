    **Practice Problem 8: Code Execution Sandbox System**

---

**1. Problem Statement**

Design and implement a **Code Execution Sandbox System** using object-oriented programming concepts.

* An online coding platform wants to safely execute user-submitted code.
* Each submitted code has:

  * code ID
  * programming language
  * execution time
  * memory usage
* The sandbox must check whether the submitted code is safe to execute.
* The system must apply execution limits such as time limit and memory limit.
* The system must return execution status by strictly following the rules described in this problem.

---

###### **2. Class Diagram**

```text
                   ----------------------------------
                   |            CodeRunner           |
                   ----------------------------------
                   | + supportedLanguages : String[] |
                   |        (static)                 |
                   | - codeId : String               |
                   | - language : String             |
                   | - executionTime : int           |
                   | - memoryUsage : int             |
                   ----------------------------------
                   | + validateCode() : boolean      |
                   | + validateLanguage() : boolean  |
                   | + getExecutionTime() : int      |
                   | + getMemoryUsage() : int        |
                   | + getLanguage() : String        |
                   ----------------------------------
                                  ▲
                                  |  HAS-A (Aggregation)
                                  ◇
                   ----------------------------------
                   |            Sandbox              |
                   ----------------------------------
                   | - codeRunner : CodeRunner       |
                   | - timeLimit : int               |
                   | - memoryLimit : int             |
                   ----------------------------------
                   | + checkTimeLimit() : boolean    |
                   | + checkMemoryLimit() : boolean  |
                   | + executeCode() : ExecutionResult|
                   ----------------------------------
                                  ▲
                                  |
                                  | uses
                                  |
                   ----------------------------------
                   |        SecurityManager          |
                   ----------------------------------
                   | + detectDangerousCode(          |
                   |   codeRunner : CodeRunner )     |
                   |        : boolean                |
                   | + checkPermission() : boolean   |
                   ----------------------------------
                                  ▲
                                  |
                                  | HAS-A (Aggregation)
                                  ◇
                   ----------------------------------
                   |       ExecutionResult           |
                   ----------------------------------
                   | - status : String               |
                   | - message : String              |
                   | - executionTime : int           |
                   | - memoryUsage : int             |
                   ----------------------------------
                   | + displayResult() : void        |
                   ----------------------------------
```

---

### **3. Explanation of Relationships**

* Sandbox HAS-A CodeRunner
  → This is **Aggregation**

* Sandbox uses SecurityManager
  → This is **Association**

* Sandbox returns ExecutionResult
  → This is **Association**

---

### **4. Implementation Status**

| Class Name      | Implementation Status |
| --------------- | --------------------- |
| CodeRunner      | Partially implemented |
| Sandbox         | Partially implemented |
| SecurityManager | Fully implemented     |
| ExecutionResult | Partially implemented |

---

### **5. Static Data**

The following array is static and must be shared across all objects.

###### **i. Supported Programming Languages**

```text
supportedLanguages = {"Java", "Python", "C++", "JavaScript"}
```

A programming language is considered valid only if it is present in the above array.

---

### **6. Implementation Details**

###### **i. CodeRunner Class**

**Method: validateCode()**

* This method checks whether the code submission is valid.
* A code submission is valid only if:

  * codeId starts with `"CODE-"`
  * executionTime is greater than 0
  * memoryUsage is greater than 0
* It returns true if code submission is valid.
* It returns false if code submission is invalid.

---

**Method: validateLanguage()**

* This method checks whether language exists in supportedLanguages.
* The comparison must be case-insensitive.

**It returns:**

* true if language is supported
* false if language is not supported

---

###### **ii. SecurityManager Class**

**Method: detectDangerousCode(CodeRunner codeRunner)**

* This method detects whether submitted code is dangerous.
* For this simulation:

  * If language is `"C++"` and memoryUsage is greater than 900:

    * Treat it as dangerous.
  * Otherwise:

    * Treat it as safe.

**It returns:**

* true if dangerous code is detected
* false if code is safe

---

**Method: checkPermission()**

* This method checks whether sandbox permission is available.
* For this simulation, it always returns true.

---

###### **iii. Sandbox Class**

**Method: checkTimeLimit()**

* This method checks whether executionTime is within the allowed time limit.

**It returns:**

* true if executionTime is less than or equal to timeLimit
* false if executionTime exceeds timeLimit

---

**Method: checkMemoryLimit()**

* This method checks whether memoryUsage is within the allowed memory limit.

**It returns:**

* true if memoryUsage is less than or equal to memoryLimit
* false if memoryUsage exceeds memoryLimit

---

**Method: executeCode()**

Step-by-step logic:

* Validate code using validateCode()
* Validate language using validateLanguage()
* Check sandbox permission using checkPermission()
* Detect dangerous code using detectDangerousCode()
* Check time limit using checkTimeLimit()
* Check memory limit using checkMemoryLimit()

If validation fails:

```text
status = "FAILED"
message = "Invalid Code Submission"
```

If language is unsupported:

```text
status = "FAILED"
message = "Unsupported Language"
```

If permission fails:

```text
status = "FAILED"
message = "Permission Denied"
```

If dangerous code is detected:

```text
status = "BLOCKED"
message = "Dangerous Code Detected"
```

If time limit is exceeded:

```text
status = "FAILED"
message = "Time Limit Exceeded"
```

If memory limit is exceeded:

```text
status = "FAILED"
message = "Memory Limit Exceeded"
```

If all checks pass:

```text
status = "SUCCESS"
message = "Code Executed Successfully"
```

---

###### **iv. ExecutionResult Class**

**Method: displayResult()**

* Displays:

  * Status
  * Message
  * Execution time
  * Memory usage

---

### **7. Sample Test Case**

**Input**

* codeId = "CODE-101"
* language = "Java"
* executionTime = 3
* memoryUsage = 256
* timeLimit = 5
* memoryLimit = 512

---

**Expected Output**

```text
Status: SUCCESS
Message: Code Executed Successfully
Execution Time: 3 seconds
Memory Usage: 256 MB
```
