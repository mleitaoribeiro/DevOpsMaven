package switch2019.project.controllers;

import switch2019.project.model.GroupsList;
import switch2019.project.model.Person;

public class US002_1 {

    /**
     * US002.1 - As a user I want to create a group becoming a group administrator.
     *
     * @param groupDescription
     * @param groupCreator
     * @param groupsList
     * @return
     */

    public boolean createGroup (String groupDescription, Person groupCreator, GroupsList groupsList) {
        return groupsList.createGroup(groupDescription,groupCreator);
    }

}
