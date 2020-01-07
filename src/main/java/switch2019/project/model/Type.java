package switch2019.project.model;

public class Type {
    private boolean description; //true (credit), false (debit)

    /**
     * Type constructor
     *
     * @param description - true (credit), false (debit)
     */
    public Type(boolean description) {
        this.description = description;
    }

    /**
     * Set Description
     */
    public boolean isDescription() {
        return description;
    }
}
