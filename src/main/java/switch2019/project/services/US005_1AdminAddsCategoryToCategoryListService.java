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
     * User Story 5.1 .- As a group admin i want to associate a category with my group.
     *
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

        //Validation for non-null parameters:
        if(categoryDescription == null || groupID == null || personID == null){
            throw new IllegalArgumentException("Category could not be added to group because a null object was given as parameter");
        }

        //finding the right group and the person who is trying to add the new category:
        Group group = groupsRepository.findGroupByID(groupID);

        //verify if person is a group admin in order to continue with the method:
        if (group.isGroupMember(personID) && group.isGroupAdmin(personID) == true) {

            //create category and associate it with the group:
            categoryRepository.createCategory(categoryDescription, groupID);

            //verify if category was added to the repository
            return categoryRepository.validateIfCategoryIsInTheCategoryList(categoryRepository.findByID(new Category(categoryDescription, groupID).getID()));
        } else return false;
    }
}
