package switch2019.project.services;

import switch2019.project.model.category.Category;
import switch2019.project.model.group.Group;
import switch2019.project.model.shared.Denomination;
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
    public boolean addCategoryToGroup(GroupID groupID, PersonID personID, Denomination categoryDescription) {
        //finding the right group where the new category will be added:
        Group group = groupsRepository.findGroupByID(groupID);

        //verify if the category creator is a group admin in order to continue with the method:
        if (group.isGroupAdmin(personID)) {

            //create category and associate it with the group
            //This method also verifies if the category was created inside the CategoryRepository;
            categoryRepository.createCategory(categoryDescription, groupID);
            return true;
        } else return false;
    }
}
