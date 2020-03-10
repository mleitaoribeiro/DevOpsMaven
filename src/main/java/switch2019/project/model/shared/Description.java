package switch2019.project.model.shared;

import java.util.Objects;

public class Description {
    //Private Description instance variable
    private String description;

    /**
     * Constructor
     * @param description
     */

    public Description(String description){
        setValidDescription(description);
    }
    /**
     * setter Description
     *
     * @param description
     */

    private void setValidDescription(String description) {
        if (description == null || description.isEmpty()){
            throw new IllegalArgumentException("The description can't be null or empty. ");
        }
        else {
            this.description = description.toUpperCase();
        }
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
