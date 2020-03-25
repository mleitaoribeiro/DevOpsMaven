package switch2019.project.DTO;

import java.util.Objects;

public class GroupAndFirstAdminDTO {
    // The Category DTO transfers strings that refer to Category attributes:

    private String description;
    private String adminID;

    public GroupAndFirstAdminDTO(String description, String adminID) {
        this.description = description.toUpperCase();
        this.adminID = adminID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupAndFirstAdminDTO dto = (GroupAndFirstAdminDTO) o;
        return Objects.equals(description, dto.description) &&
                Objects.equals(adminID, dto.adminID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, adminID);
    }
}
