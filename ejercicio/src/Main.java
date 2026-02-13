/**
 * Main execution class to demonstrate the Singleton property
 * of the NuclearCommander.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Initializing Command Center ---");

        // 1. Attempt to get two instances
        NuclearCommander commanderA = NuclearCommander.getInstance();
        NuclearCommander commanderB = NuclearCommander.getInstance();

        // 2. Verify both references point to the exact same memory address
        System.out.println("Reference Commander A: " + commanderA);
        System.out.println("Reference Commander B: " + commanderB);

        if (commanderA == commanderB) {
            System.out.println("SUCCESS: Both references point to the same Commander instance.");
        } else {
            System.out.println("FAILURE: Multiple Commander instances detected!");
        }

        System.out.println("\n--- Operational Workflow Test ---");

        // 3. Change state using reference A
        System.out.println("Commander A is increasing Defense Level...");
        commanderA.setDefenseLevel(1);

        // 4. Verify state change is reflected in reference B
        System.out.println("Checking status via Commander B:");
        System.out.print(commanderB.getStatus());

        // 5. Test Launch Authorization
        String testCode = "ALPHA-99-OMEGA";
        if (commanderB.authorizeLaunch(testCode)) {
            System.out.println("\n[SYSTEM] Launch Code '" + testCode + "' Verified via Commander B.");
        }

        System.out.println("\n--- Command Center Simulation Complete ---");
    }
}
