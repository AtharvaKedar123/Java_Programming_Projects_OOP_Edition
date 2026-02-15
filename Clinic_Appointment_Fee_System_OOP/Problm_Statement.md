### \# MediAssist – Clinic Appointment Fee Management System



**## Problem Description**



MediAssist Clinic wants to automate its \*\*appointment fee calculation system\*\*.  

Based on patient details and appointment preferences, the system should validate eligibility, generate appointment id, and calculate the total consultation fee.



Implement the class diagram and functionality as specified below.



---



\## Class Diagram Specifications



┌───────────────────────────┐

│          Patient          │

├───────────────────────────┤

│ - patientName : String    │

│ - patientId   : String    │

│ - age         : int       │

├───────────────────────────┤

│ + Patient(String, String, │

│            int)           │

│ + validatePatientDetails │

│   () : Boolean            │

│ + getPatientName() :     │

│   String                  │

│ + getPatientId() : String│

│ + getAge() : int          │

│ + toString() : String    │

└─────────────▲─────────────┘

&nbsp;             │

&nbsp;             │  HAS-A

&nbsp;             │

┌─────────────┴─────────────┐

│     Appointment (abstract)│

├───────────────────────────┤

│ - patient : Patient       │

│ - doctorType : String     │

│ - consultationMinutes:int│

│ - consultationFee:double │

│ - appointmentId : String │

│ - counter : int (static) │

├───────────────────────────┤

│ + Appointment(Patient,   │

│   String, int)            │

│ + setAppointmentId(String│

│   ) : void                │

│ + setConsultationFee     │

│   (double) : void         │

│ + getDoctorType():String │

│ + getConsultationMinutes │

│   () : int                │

│ + getAppointmentId()     │

│   : String                │

│ + getConsultationFee()   │

│   : double                │

│ + generateAppointmentId │

│   () : void               │

│ + calculateConsultation │

│   Fee() : void (abstract) │

└─────────────▲─────────────┘

&nbsp;             │

&nbsp;             │  IS-A

&nbsp;             │

┌─────────────┴─────────────┐

│    SpecialistAppointment  │

├───────────────────────────┤

│ - urgencyLevel : String   │

│                             │

│ - doctorTypeAvailable     │

│   : String\[] (static)     │

│ - feePerMinute : double\[]│

│   (static)                │

├───────────────────────────┤

│ + SpecialistAppointment  │

│   (Patient, String, int, │

│    String)                │

│ + validateDoctorType()   │

│   : Integer               │

│ + calculateConsultation  │

│   Fee() : void            │

│ + toString() : String    │

└───────────────────────────┘



---



**## Implementation Details**



##### \### Patient class



**#### validatePatientDetails()**



This method validates patient details based on the following logic:



\- patientName should contain \*\*at least 3 characters\*\*

\- age should be \*\*greater than 0\*\*

\- patientId should start with \*\*"PT"\*\*



If all conditions are satisfied, return \*\*true\*\*, else return \*\*false\*\*.



\*\*Note:\*\*  

Perform \*\*case-sensitive comparison\*\*.



---



##### \### Appointment class



**#### generateAppointmentId()**



This method auto-generates and sets the appointmentId.



\- appointmentId should start with the \*\*first character of doctorType\*\*

\- followed by an auto-generated number starting from \*\*7001\*\*

\- the number should increment by \*\*1\*\* for each new appointment



Use static variable \*\*counter\*\* appropriately.



\*\*Example:\*\*  

If doctorType is Cardio, the first appointmentId would be \*\*C7001\*\*.



---



##### \### SpecialistAppointment class



**#### doctorTypeAvailable**



This is a static String array containing supported doctor types:



{"Cardio", "Neuro", "Ortho"}





---



**#### feePerMinute**



This is a static double array containing consultation fee per minute for each doctor type:



{5.0, 6.0, 4.0}





This array has one-to-one correspondence with doctorTypeAvailable.



---



**#### validateDoctorType()**



\- Check if doctorType is present in doctorTypeAvailable  

\- If present, return the corresponding index  

\- Otherwise, return \*\*-1\*\*



---



**#### calculateConsultationFee()**



This method calculates and sets the consultationFee and appointmentId using the following logic:



1\. Invoke validatePatientDetails() of Patient class  

2\. Check if doctorType is present in doctorTypeAvailable  

3\. If both validations pass:

&nbsp;  - Invoke validateDoctorType()  

&nbsp;  - If doctorTypeIndex ≠ -1 and consultationMinutes > 0:

&nbsp;    - baseFee = consultationMinutes × feePerMinute\[doctorTypeIndex]  

&nbsp;    - If urgencyLevel is \*\*"High"\*\*, add \*\*20% extra charge\*\*  

&nbsp;    - Set consultationFee with the final calculated amount  

&nbsp;    - Invoke generateAppointmentId()  

&nbsp;  - Else:

&nbsp;    - Set consultationFee to \*\*-1.0\*\*

&nbsp;    - Set appointmentId to \*\*"NA"\*\*

4\. If any validation fails:

&nbsp;  - Set consultationFee to \*\*-1.0\*\*

&nbsp;  - Set appointmentId to \*\*"NA"\*\*



\*\*Note:\*\*  

\- Perform \*\*case-sensitive comparison\*\* for urgencyLevel



---



**## Example**



If  

\- patientName = Kiran  

\- patientId = PT8899  

\- age = 45  

\- doctorType = Neuro  

\- consultationMinutes = 30  

\- urgencyLevel = High  



Then  

\- appointmentId = N7001  

\- consultationFee = 216.0  

