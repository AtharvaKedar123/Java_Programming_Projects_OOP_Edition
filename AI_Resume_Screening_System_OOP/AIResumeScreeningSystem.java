class Candidate {
    private String candidateId;
    private String candidateName;
    private String email;

    public Candidate(String candidateId, String candidateName, String email) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.email = email;
    }

    public boolean validateCandidateId() {
        return candidateId.startsWith("CAN-") && candidateId.length() >= 7;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public String getEmail() {
        return email;
    }
}

class JobRole {
    public static String[] validEducationArr = {"BTech", "MTech", "BCA", "MCA", "BSc", "MSc"};

    private String roleName;
    private String[] requiredSkills;
    private int minExperience;
    private String requiredEducation;

    public JobRole(String roleName, String[] requiredSkills, int minExperience, String requiredEducation) {
        this.roleName = roleName;
        this.requiredSkills = requiredSkills;
        this.minExperience = minExperience;
        this.requiredEducation = requiredEducation;
    }

    public boolean validateEducation() {
        for (int i = 0; i < validEducationArr.length; i++) {
            if (validEducationArr[i].equalsIgnoreCase(requiredEducation)) {
                return true;
            }
        }
        return false;
    }

    public String[] getRequiredSkills() {
        return requiredSkills;
    }

    public int getMinExperience() {
        return minExperience;
    }

    public String getRequiredEducation() {
        return requiredEducation;
    }

    public String getRoleName() {
        return roleName;
    }
}

class Resume {
    private Candidate candidate;
    private String[] skills;
    private int experience;
    private String education;

    public Resume(Candidate candidate, String[] skills, int experience, String education) {
        this.candidate = candidate;
        this.skills = skills;
        this.experience = experience;
        this.education = education;
    }

    public int countMatchingSkills(JobRole jobRole) {
        int count = 0;
        String[] requiredSkills = jobRole.getRequiredSkills();

        for (int i = 0; i < skills.length; i++) {
            for (int j = 0; j < requiredSkills.length; j++) {
                if (skills[i].equalsIgnoreCase(requiredSkills[j])) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    public boolean validateExperience(JobRole jobRole) {
        return experience >= jobRole.getMinExperience();
    }

    public boolean validateEducation(JobRole jobRole) {
        return education.equalsIgnoreCase(jobRole.getRequiredEducation());
    }

    public Candidate getCandidate() {
        return candidate;
    }
}

class ScreeningEngine {
    private Resume resume;
    private JobRole jobRole;
    private double score;
    private String result;

    public ScreeningEngine(Resume resume, JobRole jobRole) {
        this.resume = resume;
        this.jobRole = jobRole;
        this.score = 0.0;
        this.result = "Not Screened";
    }

    public double calculateScore() {
        Candidate candidate = resume.getCandidate();

        if (!candidate.validateCandidateId() || !jobRole.validateEducation()) {
            score = -1;
            return score;
        }

        int matchingSkills = resume.countMatchingSkills(jobRole);

        double skillScore = matchingSkills * 20;
        score = skillScore;

        if (resume.validateExperience(jobRole)) {
            score += 25;
        }

        if (resume.validateEducation(jobRole)) {
            score += 15;
        }

        return score;
    }

    public void screenCandidate() {
        double finalScore = calculateScore();

        if (finalScore == -1) {
            result = "Invalid Details";
        } else if (finalScore >= 60) {
            result = "Selected";
        } else {
            result = "Rejected";
        }
    }

    public void displayResult() {
        Candidate candidate = resume.getCandidate();

        System.out.println("Candidate ID: " + candidate.getCandidateId());
        System.out.println("Candidate Name: " + candidate.getCandidateName());
        System.out.println("Final Score: " + score);
        System.out.println("Result: " + result);
    }
}

public class AIResumeScreeningSystem {
    public static void main(String[] args) {
        Candidate candidate = new Candidate(
            "CAN-101",
            "Rohan",
            "rohan@gmail.com"
        );

        String[] skills = {"Java", "SQL", "Python"};

        Resume resume = new Resume(
            candidate,
            skills,
            2,
            "BTech"
        );

        String[] requiredSkills = {"Java", "Spring", "SQL"};

        JobRole jobRole = new JobRole(
            "Backend Developer",
            requiredSkills,
            2,
            "BTech"
        );

        ScreeningEngine engine = new ScreeningEngine(resume, jobRole);

        engine.screenCandidate();
        engine.displayResult();
    }
}