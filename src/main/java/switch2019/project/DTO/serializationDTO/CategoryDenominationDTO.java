package switch2019.project.DTO.serializationDTO;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class CategoryDenominationDTO extends RepresentationModel<CategoryDenominationDTO> {
    private String categoryDenomination;

    public CategoryDenominationDTO(String categoryDenomination) {
        this.categoryDenomination = categoryDenomination;
    }

    public String getCategoryDenomination() {
        return categoryDenomination;
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
