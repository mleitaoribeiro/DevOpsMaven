package switch2019.project.assemblers;

import switch2019.project.DTO.CategoryDTO;
import switch2019.project.DTO.CreateGroupCategoryDTO;
import switch2019.project.DTO.CreateGroupCategoryInfoDTO;
import switch2019.project.domain.domainEntities.category.Category;

public class CategoryDTOAssembler {

    private CategoryDTOAssembler () {}

    /**
     * This Method transforms the objects contained in a Category into Strings, in order to create a CategoryDTO.
     * @param denomination
     * @param ownerID
     * @return CategoryDTO
     */
    public static CategoryDTO createCategoryDTO(String denomination, String ownerID) {
        return new CategoryDTO(denomination,ownerID);
    }

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
    public static CreateGroupCategoryDTO createGroupCategoryDTOFromStrings(String personEmail, String groupDescription, String categoryDenomination) {
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
