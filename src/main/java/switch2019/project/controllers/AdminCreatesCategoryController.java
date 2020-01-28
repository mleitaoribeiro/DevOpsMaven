package switch2019.project.controllers;

import switch2019.project.model.Group;
import switch2019.project.model.Person;

public class AdminCreatesCategoryController {

    /**
     *
     * @param categoryDescription
     * @param oneGroup
     * @param categoryCreator
     * @return
     */

    public boolean createCategory(String categoryDescription, Group oneGroup, Person categoryCreator) {

        //Check if the Category Creator is the group admin
        if (oneGroup.isGroupAdmin(categoryCreator)) {
            // Add the category to the Group Ledger:
            return oneGroup.createAndAddCategoryToCategoryList(categoryDescription);
        }
        return false; //If the Category creator isn´t the admin of the group the Category is not created
    }
}

