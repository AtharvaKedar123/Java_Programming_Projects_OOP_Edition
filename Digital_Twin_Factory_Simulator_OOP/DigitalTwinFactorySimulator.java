class Machine {
    private String machineId;
    private String machineName;
    private String status;
    private double failureChance;

    public Machine(String machineId, String machineName, double failureChance) {
        this.machineId = machineId;
        this.machineName = machineName;
        this.failureChance = failureChance;
        this.status = "WORKING";
    }

    public boolean operate() {
        return Math.random() >= failureChance;
    }

    public void fail() {
        status = "FAILED";
    }

    public void repair() {
        status = "WORKING";
    }

    public String getStatus() {
        return status;
    }

    public String getMachineName() {
        return machineName;
    }

    public String getMachineId() {
        return machineId;
    }
}

class ProductionLine {
    private String lineId;
    private Machine[] machines;

    public ProductionLine(String lineId, Machine[] machines) {
        this.lineId = lineId;
        this.machines = machines;
    }

    public void runLine() {
        System.out.println("Production Line Started: " + lineId);

        for (int i = 0; i < machines.length; i++) {
            if (machines[i].operate()) {
                System.out.println(machines[i].getMachineName() + " is working properly.");
            } else {
                machines[i].fail();
                System.out.println(machines[i].getMachineName() + " has failed.");
            }
        }
    }

    public Machine[] getMachines() {
        return machines;
    }
}

class Technician {
    private String technicianName;
    private String specialization;

    public Technician(String technicianName, String specialization) {
        this.technicianName = technicianName;
        this.specialization = specialization;
    }

    public void repairMachine(Machine machine) {
        if (machine.getStatus().equalsIgnoreCase("FAILED")) {
            machine.repair();
            System.out.println(
                technicianName + " (" + specialization + ") repaired " + machine.getMachineName()
            );
        }
    }
}

class Factory {
    private String factoryName;
    private ProductionLine productionLine;
    private Technician technician;

    public Factory(String factoryName, ProductionLine productionLine, Technician technician) {
        this.factoryName = factoryName;
        this.productionLine = productionLine;
        this.technician = technician;
    }

    public void startSimulation() {
        System.out.println("====================================");
        System.out.println("Factory Simulation Started");
        System.out.println("Factory Name: " + factoryName);
        System.out.println("====================================");

        productionLine.runLine();

        System.out.println();
        scheduleMaintenance();

        System.out.println();
        displayFinalStatus();
    }

    public void scheduleMaintenance() {
        System.out.println("Maintenance Check Started");

        Machine[] machines = productionLine.getMachines();

        for (int i = 0; i < machines.length; i++) {
            if (machines[i].getStatus().equalsIgnoreCase("FAILED")) {
                technician.repairMachine(machines[i]);
            }
        }
    }

    public void displayFinalStatus() {
        System.out.println("Final Machine Status");

        Machine[] machines = productionLine.getMachines();

        for (int i = 0; i < machines.length; i++) {
            System.out.println(
                machines[i].getMachineId() + " - " +
                machines[i].getMachineName() + " : " +
                machines[i].getStatus()
            );
        }
    }
}

public class DigitalTwinFactorySimulator {
    public static void main(String[] args) {
        Machine[] machines = {
            new Machine("M101", "Cutting Machine", 0.2),
            new Machine("M102", "Drilling Machine", 0.5),
            new Machine("M103", "Packaging Machine", 0.1),
            new Machine("M104", "Welding Machine", 0.4)
        };

        ProductionLine productionLine = new ProductionLine("LINE-01", machines);

        Technician technician = new Technician("Rahul", "Mechanical Repair");

        Factory factory = new Factory(
            "Alpha Manufacturing Plant",
            productionLine,
            technician
        );

        factory.startSimulation();
    }
}