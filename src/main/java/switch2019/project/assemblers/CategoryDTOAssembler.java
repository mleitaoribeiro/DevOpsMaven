package switch2019.project.assemblers;

import switch2019.project.DTO.serializationDTO.CategoryDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupCategoryDTO;
import switch2019.project.DTO.deserializationDTO.CreateGroupCategoryInfoDTO;
import switch2019.project.domain.domainEntities.category.Category;

public class CategoryDTOAssembler {

    private CategoryDTOAssembler () {}


    /**
     * This method creates the CategoryDTO from a Category.
     * @param cat
     * @return CategoryDTO
     */
    public static CategoryDTO createCategoryDTOFromCategory(Category cat) {
        return new CategoryDTO(cat.getID().getDenominationString(), cat.getID().getOwnerIDString());
    }

    /**
     * This method generates the CreateCategoryInGroupDTO using primitive types:
     * @param personEmail
     * @param groupDescription
     * @param categoryDenomination
     * @return CreateCategoryInGroupDTO
     */
    public static CreateGroupCategoryDTO createGroupCategoryDTOFromStrings( String groupDescription, String personEmail, String categoryDenomination) {
        return new CreateGroupCategoryDTO(groupDescription,personEmail,categoryDenomination);
    }

    /**
     * This method transformes a input DTO to the CreateCategoryInGroupDTO.
     * @param dto
     * @return CreateCategoryInGroupDTO
     */
    public static CreateGroupCategoryDTO transformToCreateGroupCategoryDTO(CreateGroupCategoryInfoDTO dto) {
        return new CreateGroupCategoryDTO(dto.getGroupDescription(), dto.getPersonEmail(), dto.getCategoryDenomination());
    }
}
