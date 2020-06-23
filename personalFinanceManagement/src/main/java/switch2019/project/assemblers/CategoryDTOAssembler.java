package switch2019.project.assemblers;

import switch2019.project.DTO.deserializationDTO.CreateGroupCategoryInfoDTO;
import switch2019.project.DTO.serializationDTO.CategoryDTO;
import switch2019.project.DTO.serializationDTO.CategoryDenominationDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupCategoryDTO;
import switch2019.project.domain.domainEntities.category.Category;

public class CategoryDTOAssembler {

    private CategoryDTOAssembler() {
    }

    public static CategoryDTO createCategoryDTOFromCategory(Category cat) {
        return new CategoryDTO(cat.getID().getDenominationString(), cat.getID().getOwnerIDString());
    }

    public static CreateGroupCategoryDTO createGroupCategoryDTOFromStrings(String groupDescription, String personEmail, String categoryDenomination) {
        return new CreateGroupCategoryDTO(groupDescription, personEmail, categoryDenomination);
    }

    public static CreateGroupCategoryDTO transformToCreateGroupCategoryDTO(String groupDescription, CreateGroupCategoryInfoDTO dto) {
        return new CreateGroupCategoryDTO(groupDescription, dto.getPersonEmail(), dto.getCategoryDenomination());
    }

    public static CategoryDenominationDTO createCategoryDenominationDTO(Category cat) {
        return new CategoryDenominationDTO(cat.getID().getDenominationString());
    }
}
