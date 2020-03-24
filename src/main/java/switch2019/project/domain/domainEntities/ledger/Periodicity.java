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
                return 250;
            case "working days":
                return 500;
            case "weekly":
                return 750;
            case "monthly":
                return 1000;
            default:
                throw new IllegalArgumentException("You have to choose between 'daily', 'working days', 'weekly' or 'monthly'.");
        }
    }
}
