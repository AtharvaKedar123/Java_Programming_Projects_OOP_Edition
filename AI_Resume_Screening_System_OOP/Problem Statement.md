**Practice Problem 3: AI Resume Screening System**

---

**1. Problem Statement**

Design and implement an **AI Resume Screening System** using object-oriented programming concepts.

* A company wants to shortlist candidates automatically based on resume details.
* Each candidate submits a resume containing:

  * Candidate name
  * Skills
  * Experience
  * Education
* A job role contains required skills, minimum experience, and required education.
* The screening engine must calculate a score for each candidate.
* The system must decide whether the candidate is selected or rejected by strictly following the rules described in this problem.

---

###### **2. Class Diagram**

```text
                   ----------------------------------
                   |           Candidate            |
                   ----------------------------------
                   | - candidateId : String         |
                   | - candidateName : String       |
                   | - email : String               |
                   ----------------------------------
                   | + validateCandidateId():boolean|
                   | + getCandidateName() : String  |
                   | + getCandidateId() : String    |
                   ----------------------------------
                                  ▲
                                  |  HAS-A (Aggregation)
                                  ◇
                   ----------------------------------
                   |            Resume              |
                   ----------------------------------
                   | - candidate : Candidate        |
                   | - skills : String[]            |
                   | - experience : int             |
                   | - education : String           |
                   ----------------------------------
                   | + countMatchingSkills(JobRole) |
                   |       : int                    |
                   | + validateExperience(JobRole)  |
                   |       : boolean                |
                   | + validateEducation(JobRole)   |
                   |       : boolean                |
                   ----------------------------------
                                  ▲
                                  |
                                  | uses
                                  |
                   ----------------------------------
                   |            JobRole             |
                   ----------------------------------
                   | + validEducationArr : String[] |
                   |        (static)                |
                   | - roleName : String            |
                   | - requiredSkills : String[]    |
                   | - minExperience : int          |
                   | - requiredEducation : String   |
                   ----------------------------------
                   | + validateEducation() : boolean|
                   | + getRequiredSkills():String[] |
                   | + getMinExperience() : int     |
                   | + getRequiredEducation():String|
                   ----------------------------------
                                  ▲
                                  |
                                  | uses
                                  |
                   ----------------------------------
                   |        ScreeningEngine         |
                   ----------------------------------
                   | - resume : Resume              |
                   | - jobRole : JobRole            |
                   | - score : double               |
                   | - result : String              |
                   ----------------------------------
                   | + calculateScore() : double    |
                   | + screenCandidate() : void     |
                   | + displayResult() : void       |
                   ----------------------------------
```

---

### **3. Explanation of Relationships**

* Resume HAS-A Candidate
  → This is **Aggregation**

* Resume uses JobRole
  → This is **Association**

* ScreeningEngine HAS-A Resume and JobRole
  → This is **Aggregation**

---

### **4. Implementation Status**

| Class Name      | Implementation Status |
| --------------- | --------------------- |
| Candidate       | Partially implemented |
| Resume          | Partially implemented |
| JobRole         | Partially implemented |
| ScreeningEngine | Partially implemented |

---

### **5. Static Data**

The following array is static and must be shared across all objects.

###### **i. Valid Education Types**

```text
validEducationArr = {"BTech", "MTech", "BCA", "MCA", "BSc", "MSc"}
```

A job role education is considered valid only if it is present in the above array.

---

### **6. Implementation Details**

###### **i. Candidate Class**

**Method: validateCandidateId()**

* This method checks whether the candidate ID is valid.
* A candidate ID is valid only if:

  * It starts with `"CAN-"`
  * Its length is greater than or equal to 7
* It returns true if the candidate ID is valid.
* It returns false if the candidate ID is invalid.

---

###### **ii. JobRole Class**

**Method: validateEducation()**

* This method checks whether requiredEducation is present in validEducationArr.
* The comparison must be case-insensitive.

**It returns:**

* true if requiredEducation is valid
* false if requiredEducation is invalid

---

###### **iii. Resume Class**

**Method: countMatchingSkills(JobRole jobRole)**

* This method compares candidate skills with required skills.
* The comparison must be case-insensitive.
* It returns the total number of matching skills.

---

**Method: validateExperience(JobRole jobRole)**

* This method checks whether candidate experience is greater than or equal to the minimum experience required for the job role.

**It returns:**

* true if experience is valid
* false if experience is invalid

---

**Method: validateEducation(JobRole jobRole)**

* This method checks whether candidate education matches the required education of the job role.
* The comparison must be case-insensitive.

**It returns:**

* true if education matches
* false if education does not match

---

###### **iv. ScreeningEngine Class**

**Method: calculateScore()**

Step-by-step logic:

* Validate candidate ID using validateCandidateId()
* Validate job role education using validateEducation()
* If any validation fails, return -1
* Count matching skills
* Calculate skill score:

```text
skillScore = matchingSkills × 20
```

* If candidate experience is valid, add 25 marks
* If candidate education is valid, add 15 marks
* Final score should be returned as double

---

**Method: screenCandidate()**

Step-by-step logic:

* Calculate score using calculateScore()
* If score is -1:

  * Set result as `"Invalid Details"`
* If score is greater than or equal to 60:

  * Set result as `"Selected"`
* Otherwise:

  * Set result as `"Rejected"`

---

**Method: displayResult()**

* Displays:

  * Candidate ID
  * Candidate name
  * Final score
  * Screening result

---

### **7. Sample Test Case**

**Input**

* candidateId = "CAN-101"
* candidateName = "Rohan"
* email = "[rohan@gmail.com](mailto:rohan@gmail.com)"
* skills = {"Java", "SQL", "Python"}
* experience = 2
* education = "BTech"
* roleName = "Backend Developer"
* requiredSkills = {"Java", "Spring", "SQL"}
* minExperience = 2
* requiredEducation = "BTech"

---

**Expected Output**

```text
Candidate ID: CAN-101
Candidate Name: Rohan
Final Score: 80.0
Result: Selected
```
