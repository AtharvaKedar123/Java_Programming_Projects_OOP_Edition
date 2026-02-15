class Patient {
    private String patientName;
    private String patientId;
    private int age;

    public Patient(String patientName, String patientId, int age) {
        this.patientName = patientName;
        this.patientId = patientId;
        this.age = age;
    }

    public boolean validatePatientDetails() {
        if (patientName != null && patientName.length() >= 3
                && age > 0
                && patientId != null && patientId.startsWith("PT")) {
            return true;
        }
        return false;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientId() {
        return patientId;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return "Patient Name: " + patientName +
               ", Patient Id: " + patientId +
               ", Age: " + age;
    }
}

abstract class Appointment {
    protected Patient patient;
    protected String doctorType;
    protected int consultationMinutes;
    protected double consultationFee;
    protected String appointmentId;
    protected static int counter = 7000;

    public Appointment(Patient patient, String doctorType, int consultationMinutes) {
        this.patient = patient;
        this.doctorType = doctorType;
        this.consultationMinutes = consultationMinutes;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public int getConsultationMinutes() {
        return consultationMinutes;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void generateAppointmentId() {
        counter++;
        appointmentId = doctorType.charAt(0) + String.valueOf(counter);
    }

    public abstract void calculateConsultationFee();
}

class SpecialistAppointment extends Appointment {
    private String urgencyLevel;

    private static String[] doctorTypeAvailable = {"Cardio", "Neuro", "Ortho"};
    private static double[] feePerMinute = {5.0, 6.0, 4.0};

    public SpecialistAppointment(Patient patient, String doctorType,
                                 int consultationMinutes, String urgencyLevel) {
        super(patient, doctorType, consultationMinutes);
        this.urgencyLevel = urgencyLevel;
    }

    public Integer validateDoctorType() {
        for (int i = 0; i < doctorTypeAvailable.length; i++) {
            if (doctorTypeAvailable[i].equals(doctorType)) {
                return i;
            }
        }
        return -1;
    }

    public void calculateConsultationFee() {
        if (patient.validatePatientDetails()) {
            int index = validateDoctorType();
            if (index != -1 && consultationMinutes > 0) {
                double baseFee = consultationMinutes * feePerMinute[index];

                if (urgencyLevel.equals("High")) {
                    baseFee = baseFee + (baseFee * 0.20);
                }

                setConsultationFee(baseFee);
                generateAppointmentId();
                return;
            }
        }
        setConsultationFee(-1.0);
        setAppointmentId("NA");
    }

    public String toString() {
        return "Appointment Id: " + appointmentId +
               "\nConsultation Fee: " + consultationFee;
    }
}

public class MediAssistTester {
    public static void main(String[] args) {

        Patient patient = new Patient("Kiran", "PT8899", 45);

        SpecialistAppointment appointment =
                new SpecialistAppointment(patient, "Neuro", 30, "High");

        appointment.calculateConsultationFee();

        System.out.println("Appointment Id: " + appointment.getAppointmentId());
        System.out.println("Consultation Fee: " + appointment.getConsultationFee());
    }
}
