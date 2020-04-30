package switch2019.project.DTO.serializationDTO;

import java.util.Objects;

public class CategoryDenominationDTO {
    private String groupDescription;

    public CategoryDenominationDTO(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDenominationDTO that = (CategoryDenominationDTO) o;
        return Objects.equals(groupDescription, that.groupDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupDescription);
    }
}
