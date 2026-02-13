/**
 * NuclearCommander - A Singleton class representing the global
 * authority for nuclear launch operations.
 */
public class NuclearCommander {
    private static NuclearCommander instance;

    // Business Logic Attributes
    private String launchCode;
    private int defenseLevel;

    /**
     * Private constructor prevents instantiation from other classes.
     */
    private NuclearCommander() {
        this.launchCode = "ALPHA-99-OMEGA"; // Default secure code
        this.defenseLevel = 5;               // Default DEFCON 5
    }

    public static synchronized NuclearCommander getInstance() {
        if (instance == null) {
            instance = new NuclearCommander();
        }
        return instance;
    }


    public boolean authorizeLaunch(String inputCode) {
        return this.launchCode.equals(inputCode);
    }

    public void setDefenseLevel(int level) {
        this.defenseLevel = level;
        System.out.println("[ALERT] Defense Level updated to: DEFCON " + level);
    }

    public String getStatus() {
        return "Commander Status: ACTIVE | Defense Level: DEFCON " + defenseLevel;
    }

}
