package switch2019.project.controllers;

import switch2019.project.model.shared.GroupID;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.CategoryRepository;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US005_1AdminAddsCategoryToCategoryListService;


public class US005_1AdminAddsCategoryController {

    /**
     * Call AdminAddsCategoryToCategoryListService to implement the User Story 5.1 using this controller.
     * @param groupID
     * @param personID
     * @param categoryRepository
     * @param categoryDescription
     * @param groupsRepository
     * @param personRepository
     * @param service
     * @return
     */
    public boolean addCategoryToGroupController(GroupID groupID, PersonID personID, CategoryRepository categoryRepository, String categoryDescription,
                                      GroupsRepository groupsRepository, PersonRepository personRepository, US005_1AdminAddsCategoryToCategoryListService service) {

        return service.addCategoryToGroup(groupID, personID, categoryRepository, categoryDescription, groupsRepository, personRepository);
    }

}
