package switch2019.project.assemblers;

import switch2019.project.DTO.CategoryDTO;
import switch2019.project.domain.domainEntities.shared.CategoryID;
import switch2019.project.domain.domainEntities.shared.Denomination;

//Assembler for CategoryDTO:

public class CategoryDTOAssembler {

    /**
     * This Method transforms the objects contained in a Category into Strings, in order to create a CategoryDTO.
     * @param denomination - Category Denomination
     * @param categoryID - (Category Denomination, Owner ID)
     * @return
     */
    public static CategoryDTO createCategoryDTO(Denomination denomination, CategoryID categoryID) {
        return  new CategoryDTO(denomination.toString(),categoryID.toString());
    }
}
