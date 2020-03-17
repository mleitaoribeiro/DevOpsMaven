package switch2019.project.services;

import switch2019.project.model.category.Category;
import switch2019.project.model.group.Group;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.GroupID;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.CategoryRepository;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;

public class US005_1AdminAddsCategoryToCategoryListService {

    /**
     * User Story 5.1 .- As a group admin i want to associate a category to my group.
     * @param groupID
     * @param personID
     * @param categoryRepository
     * @param categoryDescription
     * @param groupsRepository
     * @param personRepository
     * @return
     */
    public boolean addCategoryToGroup(GroupID groupID, PersonID personID, CategoryRepository categoryRepository, String categoryDescription,
                                      GroupsRepository groupsRepository, PersonRepository personRepository) {

        //finding the right group and the person who is trying to add the new category:
        Group group = groupsRepository.findGroupByID(groupID);
        Person admin = personRepository.findPersonByID(personID);

        //verify if person is a group admin in order to continue with the method
        if (group.isGroupMember(admin) && group.isGroupAdmin(admin) == true) {

            //To change after OwnerID is complete, To substitute with categoryRepository method.
            return categoryRepository.createCategory(categoryDescription, personID);
        }
        else return false;
    }
}
