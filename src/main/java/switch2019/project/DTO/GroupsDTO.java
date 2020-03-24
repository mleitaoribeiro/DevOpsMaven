package switch2019.project.DTO;

import java.util.Objects;

public class GroupsDTO {
    private String familyGroupDescription;

    public GroupsDTO(String familyGroupDescription) {
        this.familyGroupDescription = familyGroupDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupsDTO groupsDTO = (GroupsDTO) o;
        return Objects.equals(familyGroupDescription, groupsDTO.familyGroupDescription);
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
