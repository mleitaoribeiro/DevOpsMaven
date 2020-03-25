package switch2019.project.controllerLayer;

import switch2019.project.DTO.CategoryDTO;
import switch2019.project.DTO.CreateCategoryInGroupDTO;
import switch2019.project.applicationLayer.US005_1AdminAddsCategoryToCategoryListService;
import switch2019.project.assemblers.CategoryDTOAssembler;
import switch2019.project.domain.domainEntities.category.Category;
import java.util.Optional;


public class US005_1AdminAddsCategoryController {

    private US005_1AdminAddsCategoryToCategoryListService service;

    //Service is initialized as attribute:
    public US005_1AdminAddsCategoryController(US005_1AdminAddsCategoryToCategoryListService service){
        this.service = service;
    }

    /**
     * Call AdminAddsCategoryToCategoryListService to implement the User Story 5.1 using this controller.
     * @param categoryDTO
     * @return
     */

    public Optional <CategoryDTO> addCategoryToGroupController(CreateCategoryInGroupDTO categoryDTO) {

        Optional <Category> ret =  service.addCategoryToGroup(categoryDTO);
        if(ret.isPresent()) {
            Category cat = ret.get();
            // Transformar a categoria dentro do ret em CategoryDTO
            CategoryDTO dto = CategoryDTOAssembler.createCategoryDTO(cat.getID().getDenomination(), cat.getID());
            return Optional.of(dto);
        }
        else {
            return Optional.empty();
        }
    }
}
