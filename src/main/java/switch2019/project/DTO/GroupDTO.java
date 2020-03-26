package switch2019.project.DTO;

import java.util.Objects;

public class GroupDTO {
    private String groupDescription;

    public GroupDTO(String groupDescription) {
        this.groupDescription = groupDescription.toUpperCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDTO groupDTO = (GroupDTO) o;
        return Objects.equals(groupDescription, groupDTO.groupDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupDescription);
    }

    /**
     * Method to get GroupDescription
     *
     * @return groupDescription
     */

    public String getGroupDescription() {
        return groupDescription;
    }
}
