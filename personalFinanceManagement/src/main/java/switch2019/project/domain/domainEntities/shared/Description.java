package switch2019.project.domain.domainEntities.shared;

import switch2019.project.utils.StringUtils;

import java.util.Objects;

public class Description {

    //Private Description instance variable
    private final String descriptionValue;

    public Description(String descriptionValue){
        this.descriptionValue = setValidDescription(descriptionValue);
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

   @Override
    public String toString() {
        return this.descriptionValue;
    }

    private String setValidDescription(String description) {
        if (description == null || description.isEmpty())
            throw new IllegalArgumentException("The description can't be null or empty.");
        else return StringUtils.removeExtraSpaces(description.toUpperCase());
    }

    public String getDescription() {
        return descriptionValue;
    }


}
