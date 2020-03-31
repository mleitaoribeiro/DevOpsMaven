package switch2019.project.assemblers;

import switch2019.project.DTO.CategoryDTO;
import switch2019.project.DTO.CreateCategoryInGroupDTO;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.shared.CategoryID;
import switch2019.project.domain.domainEntities.shared.Denomination;

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
     */
    public static CategoryDTO createCategoryDTOFromCategory(Category cat) {
        return new CategoryDTO(cat.getID().getDenominationString(), cat.getID().getOwnerIDString());
    }

    /**
     * This method generates the CreateCategoryInGroupDTO using primitive types:
     */
    public static CreateCategoryInGroupDTO createCategoryInGroupDTOFromStrings(String personEmail, String groupDescription, String categoryDenomination) {
        return new CreateCategoryInGroupDTO(groupDescription,personEmail,categoryDenomination);
    }
}
