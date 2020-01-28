package switch2019.project.controllers;

import switch2019.project.model.Group;
import switch2019.project.model.Person;

public class AdminCreatesCategory {

    public boolean createCategory(String categorDescription, Group oneGroup, Person categoryCreator) {

        //Check if the Category Creator is a member of the group
        if (oneGroup.isGroupMember(categoryCreator)) {
            // Add the transaction to the Group Ledger:
            return oneGroup.createAndAddCategoryToCategoryList(categorDescription);
        }
        return false;//If the Category creator isn´t a member of the group the Category is not created
    }
}

