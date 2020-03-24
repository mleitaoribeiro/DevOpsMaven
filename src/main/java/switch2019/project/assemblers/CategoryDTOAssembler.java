package switch2019.project.assemblers;

import switch2019.project.DTO.CategoryDTO;
import switch2019.project.domain.domainEntities.shared.CategoryID;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;

public class CategoryDTOAssembler {
    public static CategoryDTO createCategoryDTO(Denomination denomination, CategoryID categoryID) {
        return  new CategoryDTO(denomination.toString(),categoryID.toString());
    }
}
