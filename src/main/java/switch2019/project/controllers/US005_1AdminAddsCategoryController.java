package switch2019.project.controllers;

import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.GroupID;
import switch2019.project.model.shared.PersonID;
import switch2019.project.services.US005_1AdminAddsCategoryToCategoryListService;


public class US005_1AdminAddsCategoryController {

    private US005_1AdminAddsCategoryToCategoryListService service;

    //Service is initialized as attribute:
    public US005_1AdminAddsCategoryController(US005_1AdminAddsCategoryToCategoryListService service){
        this.service = service;
    }

    /**
     * Call AdminAddsCategoryToCategoryListService to implement the User Story 5.1 using this controller.
     * @param groupDescription
     * @param personEmail
     * @param categoryDenomination
     * @return
     */

    public boolean addCategoryToGroupController(String groupDescription, String personEmail, String categoryDenomination) {

        return service.addCategoryToGroup(groupDescription, personEmail, categoryDenomination);
    }

}
