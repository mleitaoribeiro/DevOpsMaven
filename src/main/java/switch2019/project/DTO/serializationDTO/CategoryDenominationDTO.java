package switch2019.project.DTO.serializationDTO;

import java.util.Objects;

public class CategoryDenominationDTO {
    private String categoryDenomination;

    public CategoryDenominationDTO(String categoryDenomination) {
        this.categoryDenomination = categoryDenomination;
    }

    public String getCategoryDenomination() {
        return categoryDenomination;
    }

    public void CategoryDenomination(String categoryDenomination) {
        this.categoryDenomination = categoryDenomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDenominationDTO that = (CategoryDenominationDTO) o;
        return Objects.equals(categoryDenomination, that.categoryDenomination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryDenomination);
    }
}
