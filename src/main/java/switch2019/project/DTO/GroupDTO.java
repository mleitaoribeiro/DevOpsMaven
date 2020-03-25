package switch2019.project.DTO;

import java.util.Objects;

public class GroupDTO {
    private String familyGroupDescription;

    public GroupDTO(String familyGroupDescription) {
        this.familyGroupDescription = familyGroupDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDTO groupDTO = (GroupDTO) o;
        return Objects.equals(familyGroupDescription, groupDTO.familyGroupDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyGroupDescription);
    }

    /**
     * Method to get FamilyGroupDescription
     * @return
     */

    public String getFamilyGroupDescription() {
        return familyGroupDescription;
    }
}
