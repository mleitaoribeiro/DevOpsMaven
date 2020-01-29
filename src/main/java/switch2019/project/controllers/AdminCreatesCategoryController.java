package switch2019.project.controllers;

import switch2019.project.model.Group;
import switch2019.project.model.Person;

public class AdminCreatesCategoryController {

    /**
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

