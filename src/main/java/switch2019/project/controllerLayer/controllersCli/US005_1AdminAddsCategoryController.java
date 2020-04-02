package switch2019.project.controllerLayer.controllersCli;

import switch2019.project.DTO.CategoryDTO;
import switch2019.project.DTO.CreateCategoryInGroupDTO;
import switch2019.project.applicationLayer.US005_1AdminAddsCategoryToGroupService;
import switch2019.project.assemblers.CategoryDTOAssembler;


public class US005_1AdminAddsCategoryController {

    private US005_1AdminAddsCategoryToGroupService service;

    //Service is initialized as attribute:
    public US005_1AdminAddsCategoryController(US005_1AdminAddsCategoryToGroupService service){
        this.service = service;
    }

    /**
     * Call AdminAddsCategoryToCategoryListService to implement the User Story 5.1 using this controller.
     * @param personEmail
     * @param groupDescription
     * @param categoryDenomination
     * @return
     */

    public CategoryDTO addCategoryToGroupController(String personEmail, String groupDescription, String categoryDenomination) {
        // Create the entry dto for the service:
        CreateCategoryInGroupDTO dto = CategoryDTOAssembler.createCategoryInGroupDTOFromStrings(groupDescription,personEmail,categoryDenomination);
        // If the category can be created with the given information, the Category DTO is returned:
        return service.addCategoryToGroup(dto);
    }
}
