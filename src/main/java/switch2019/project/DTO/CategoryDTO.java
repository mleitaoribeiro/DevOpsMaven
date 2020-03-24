package switch2019.project.DTO;

import switch2019.project.domain.domainEntities.shared.Denomination;

import java.util.Objects;

public class CategoryDTO {
    // The Category DTO transfers strings that refer to Category attributes:

    private String denomination;
    private String categoryID;

    public CategoryDTO(String denomination, String categoryID) {
        this.denomination = denomination;
        this.categoryID = categoryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO dto = (CategoryDTO) o;
        return Objects.equals(denomination, dto.denomination) &&
                Objects.equals(categoryID, dto.categoryID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, categoryID);
    }

    /**
     * Getter for the Category description
     * @return
     */
    public String getDenomination() {
        return denomination;
    }

    /**
     * Getter for the CategoryID
     * @return
     */
    public String getCategoryID() {
        return categoryID;
    }
}



