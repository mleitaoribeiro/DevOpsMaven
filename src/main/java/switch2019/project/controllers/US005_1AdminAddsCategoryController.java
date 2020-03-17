package switch2019.project.controllers;

import switch2019.project.model.shared.GroupID;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.CategoryRepository;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US005_1AdminAddsCategoryToCategoryListService;

/**
 * Call AdminAddsCategoryToCategoryListService to implement the User Story 5.1 using this controller.
 *
 */

public class US005_1AdminAddsCategoryController {

    public boolean addCategoryToGroupController(GroupID groupID, PersonID personID, CategoryRepository categoryRepository, String categoryDescription,
                                      GroupsRepository groupsRepository, PersonRepository personRepository, US005_1AdminAddsCategoryToCategoryListService service) {

        return service.addCategoryToGroup(groupID, personID, categoryRepository, categoryDescription, groupsRepository, personRepository);
    }

}
