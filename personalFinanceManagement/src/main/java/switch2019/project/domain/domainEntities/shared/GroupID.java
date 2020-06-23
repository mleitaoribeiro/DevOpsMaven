package switch2019.project.domain.domainEntities.shared;

import switch2019.project.domain.domainEntities.frameworks.OwnerID;

import java.util.Objects;

public class GroupID implements OwnerID {

    private final Description description;

    public GroupID(Description description) {
        if(description != null) this.description = description;
        else throw new IllegalArgumentException("The description can't be null.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupID groupID = (GroupID) o;
        return Objects.equals(description, groupID.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return description.toString();
    }

    public String getDescription() {
        return description.getDescription();
    }



}
