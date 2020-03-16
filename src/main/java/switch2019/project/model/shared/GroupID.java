package switch2019.project.model.shared;

import switch2019.project.model.frameworks.ID;

import java.util.Objects;

public class GroupID implements ID {

    private Description description;

    /**
     * GroupID constructor
     */
    public GroupID(Description description) {
        this.description = description;
    }

    /**
     * Method to get description
     * @return description
     */
    public String getDescription() {
        return description.getDescriptionValue();
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

}
