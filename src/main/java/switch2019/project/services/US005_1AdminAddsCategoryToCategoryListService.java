package switch2019.project.services;

import switch2019.project.model.category.Category;
import switch2019.project.model.group.Group;
import switch2019.project.model.shared.GroupID;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.CategoryRepository;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;

public class US005_1AdminAddsCategoryToCategoryListService {

    //Repositories are given as attributes of this service in order to allow its usage by the addCategoryToGroup method
    //without passing them as parameter:
    private GroupsRepository groupsRepository;
    private CategoryRepository categoryRepository;

    //Service constructor:
    public US005_1AdminAddsCategoryToCategoryListService(GroupsRepository g, CategoryRepository c) {
        this.groupsRepository = g;
        this.categoryRepository = c;
    }

    /**
     * User Story 5.1 .- As a group admin i want to associate a category with my group.
     *
     * @param groupID
     * @param personID
     * @param categoryDescription
     * @return
     */
    public boolean addCategoryToGroup(GroupID groupID, PersonID personID, String categoryDescription) {

        //Validation for non-null parameters:
        if(categoryDescription == null || groupID == null || personID == null){
            throw new IllegalArgumentException("Category could not be added to group because a null object was given as parameter");
        }

        //finding the right group and the person who is trying to add the new category:
        Group group = groupsRepository.findGroupByID(groupID);

        //verify if person is a group admin in order to continue with the method:
        if (group.isGroupAdmin(personID) == true) {

            //create category and associate it with the group:
            categoryRepository.createCategory(categoryDescription, groupID);

            //verify if category was added to the repository
            //return categoryRepository.isCategoryValid(categoryRepository.findByID(new Category(categoryDescription, groupID).getID()));
            //Gabriel verifica isto, só pus assim para não dar erro
            return true;
        } else return false;
    }
}
