package switch2019.project.assemblers;

import switch2019.project.DTO.CategoryDTO;
import switch2019.project.DTO.CreateGroupCategoryDTO;
import switch2019.project.DTO.CreateGroupCategoryInputDTO;
import switch2019.project.domain.domainEntities.category.Category;

//Assembler for CategoryDTO:

public class CategoryDTOAssembler {

    private CategoryDTOAssembler () {}

    /**
     * This Method transforms the objects contained in a Category into Strings, in order to create a CategoryDTO.
     * @param denomination - Category Denomination
     * @param ownerID - (Category Denomination, Owner ID)
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
    public static CreateGroupCategoryDTO createCategoryInGroupDTOFromStrings(String personEmail, String groupDescription, String categoryDenomination) {
        return new CreateGroupCategoryDTO(groupDescription,personEmail,categoryDenomination);
    }

    /**
     * This method transformes a input DTO to the CreateCategoryInGroupDTO.
     * @param dto
     * @return CreateCategoryInGroupDTO
     */
    public static CreateGroupCategoryDTO transformToCreateGroupCategoryDTO(CreateGroupCategoryInputDTO dto) {
        return new CreateGroupCategoryDTO(dto.getGroupDescription(), dto.getPersonEmail(), dto.getCategoryDenomination());
    }

}
