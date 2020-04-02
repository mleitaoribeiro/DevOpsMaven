package switch2019.project.domain.domainEntities.ledger;

public class Periodicity {

    /**
     * Private Periodicity attribute
     */
    private final int periodicityMs;

    /**
     * Periodicity constructor
     */
    public Periodicity(String periodicityString) {
        periodicityMs = convertKeyWordIntoMilliseconds(periodicityString);
    }

    /**
     * Get Periodicity
     */
    public int getPeriodicityInMilliseconds() {
        return periodicityMs;
    }

    /**
     * Method to convert key word into milliseconds
     */
    private int convertKeyWordIntoMilliseconds(String periodicity) {
        switch (periodicity) {
            case "daily":
                return 500;
            case "working days":
                return 1000;
            case "weekly":
                return 1500;
            case "monthly":
                return 2000;
            default:
                throw new IllegalArgumentException("You have to choose between 'daily', 'working days', 'weekly' or 'monthly'.");
        }
    }
}
