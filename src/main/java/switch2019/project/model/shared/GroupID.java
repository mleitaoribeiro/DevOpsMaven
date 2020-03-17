package switch2019.project.model.shared;
import switch2019.project.model.frameworks.OwnerID;

import java.util.Objects;

public class GroupID implements OwnerID {

    private Description description;

    /**
     * GroupID constructor
     */
    public GroupID(Description description) {
        if(description != null) this.description = description;
        else throw new IllegalArgumentException("The description can't be null.");
    }

    /**
     * Method to get description
     * @return description
     */
    public String getDescription() {
        return description.getDescription();
    }

    /**
     * Override to equals method
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupID groupID = (GroupID) o;
        return Objects.equals(description, groupID.description);
    }

    /**
     * Override to hashCode
     *
     */
    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return description.toString();
    }

}
