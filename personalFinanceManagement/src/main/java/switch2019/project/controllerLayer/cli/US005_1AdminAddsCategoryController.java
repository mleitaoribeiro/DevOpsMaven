package switch2019.project.controllerLayer.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import switch2019.project.DTO.serializationDTO.CategoryDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupCategoryDTO;
import switch2019.project.applicationLayer.US005_1AdminAddsCategoryToGroupService;
import switch2019.project.assemblers.CategoryDTOAssembler;

@Controller
public class US005_1AdminAddsCategoryController {

    @Autowired
    private US005_1AdminAddsCategoryToGroupService service;

    /**
     * Call AdminAddsCategoryToCategoryListService to implement the User Story 5.1 using this controller.
     * @param personEmail for personEmail
     * @param groupDescription for groupDescription
     * @param categoryDenomination for categoryDenomination
     * @return CategoryDTO
     */

    public CategoryDTO addCategoryToGroupController(String personEmail, String groupDescription, String categoryDenomination) {
        // Create the entry dto for the service:
        CreateGroupCategoryDTO dto = CategoryDTOAssembler.createGroupCategoryDTOFromStrings(groupDescription,personEmail,categoryDenomination);
        // If the category can be created with the given information, the Category DTO is returned:
        return service.addCategoryToGroup(dto);
    }
}
