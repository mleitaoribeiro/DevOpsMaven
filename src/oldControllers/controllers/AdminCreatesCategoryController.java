package switch2019.project.controllers;

import switch2019.project.model.group.Group;
import switch2019.project.model.person.Person;

public class AdminCreatesCategoryController {

    /**
     * US005.1 - As a Group Administrator, I want to create a category and add it to a group.
     *
     * @param nameOfCategory
     * @param oneGroup
     * @param categoryCreator
     * @return
     */

    public boolean createCategory(String nameOfCategory, Group oneGroup, Person categoryCreator) {
            return oneGroup.createAndAddCategoryToCategoryList(nameOfCategory, categoryCreator);
        }

}

