package switch2019.project.model.shared;

import java.util.Objects;

public class Description {
    //Private Description instance variable
    private final String descriptionValue;

    /**
     * Constructor
     * @param descriptionValue
     */

    public Description(String descriptionValue){
        this.descriptionValue = setValidDescription(descriptionValue);
    }

    /**
     * toString Method for Description
     */
    public String toString() {
        return this.descriptionValue;
    }

    /**
     * setter Description
     *
     * @param description
     */
    private String setValidDescription(String description) {
        if (description == null || description.isEmpty()){
            throw new IllegalArgumentException("The description can't be null or empty. ");
        }
        else {
            return description.toUpperCase();
        }
    }

    public String getDescriptionValue() {
        return descriptionValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return Objects.equals(descriptionValue, that.descriptionValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptionValue);
    }
}
