class CodeRunner {
    public static String[] supportedLanguages = {"Java", "Python", "C++", "JavaScript"};

    private String codeId;
    private String language;
    private int executionTime;
    private int memoryUsage;

    public CodeRunner(String codeId, String language, int executionTime, int memoryUsage) {
        this.codeId = codeId;
        this.language = language;
        this.executionTime = executionTime;
        this.memoryUsage = memoryUsage;
    }

    public boolean validateCode() {
        return codeId.startsWith("CODE-") && executionTime > 0 && memoryUsage > 0;
    }

    public boolean validateLanguage() {
        for (int i = 0; i < supportedLanguages.length; i++) {
            if (supportedLanguages[i].equalsIgnoreCase(language)) {
                return true;
            }
        }
        return false;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public int getMemoryUsage() {
        return memoryUsage;
    }

    public String getLanguage() {
        return language;
    }
}

class ExecutionResult {
    private String status;
    private String message;
    private int executionTime;
    private int memoryUsage;

    public ExecutionResult(String status, String message, int executionTime, int memoryUsage) {
        this.status = status;
        this.message = message;
        this.executionTime = executionTime;
        this.memoryUsage = memoryUsage;
    }

    public void displayResult() {
        System.out.println("Status: " + status);
        System.out.println("Message: " + message);
        System.out.println("Execution Time: " + executionTime + " seconds");
        System.out.println("Memory Usage: " + memoryUsage + " MB");
    }
}

class SandboxSecurityManager {
    public boolean detectDangerousCode(CodeRunner codeRunner) {
        return codeRunner.getLanguage().equalsIgnoreCase("C++")
                && codeRunner.getMemoryUsage() > 900;
    }

    public boolean checkPermission() {
        return true;
    }
}

class Sandbox {
    private CodeRunner codeRunner;
    private int timeLimit;
    private int memoryLimit;

    public Sandbox(CodeRunner codeRunner, int timeLimit, int memoryLimit) {
        this.codeRunner = codeRunner;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
    }

    public boolean checkTimeLimit() {
        return codeRunner.getExecutionTime() <= timeLimit;
    }

    public boolean checkMemoryLimit() {
        return codeRunner.getMemoryUsage() <= memoryLimit;
    }

    public ExecutionResult executeCode() {
        SandboxSecurityManager securityManager = new SandboxSecurityManager();

        if (!codeRunner.validateCode()) {
            return new ExecutionResult(
                    "FAILED",
                    "Invalid Code Submission",
                    codeRunner.getExecutionTime(),
                    codeRunner.getMemoryUsage()
            );
        }

        if (!codeRunner.validateLanguage()) {
            return new ExecutionResult(
                    "FAILED",
                    "Unsupported Language",
                    codeRunner.getExecutionTime(),
                    codeRunner.getMemoryUsage()
            );
        }

        if (!securityManager.checkPermission()) {
            return new ExecutionResult(
                    "FAILED",
                    "Permission Denied",
                    codeRunner.getExecutionTime(),
                    codeRunner.getMemoryUsage()
            );
        }

        if (securityManager.detectDangerousCode(codeRunner)) {
            return new ExecutionResult(
                    "BLOCKED",
                    "Dangerous Code Detected",
                    codeRunner.getExecutionTime(),
                    codeRunner.getMemoryUsage()
            );
        }

        if (!checkTimeLimit()) {
            return new ExecutionResult(
                    "FAILED",
                    "Time Limit Exceeded",
                    codeRunner.getExecutionTime(),
                    codeRunner.getMemoryUsage()
            );
        }

        if (!checkMemoryLimit()) {
            return new ExecutionResult(
                    "FAILED",
                    "Memory Limit Exceeded",
                    codeRunner.getExecutionTime(),
                    codeRunner.getMemoryUsage()
            );
        }

        return new ExecutionResult(
                "SUCCESS",
                "Code Executed Successfully",
                codeRunner.getExecutionTime(),
                codeRunner.getMemoryUsage()
        );
    }
}

public class CodeExecutionSandboxSystem {
    public static void main(String[] args) {
        CodeRunner codeRunner = new CodeRunner(
                "CODE-101",
                "Java",
                3,
                256
        );

        Sandbox sandbox = new Sandbox(codeRunner, 5, 512);

        ExecutionResult result = sandbox.executeCode();

        result.displayResult();
    }
}