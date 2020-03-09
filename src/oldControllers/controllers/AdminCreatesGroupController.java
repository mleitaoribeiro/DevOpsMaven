package switch2019.project.controllers;

import switch2019.project.repository.GroupsRepository;
import switch2019.project.model.person.Person;

public class AdminCreatesGroupController {

    /**
     * US002.1 - As a user I want to create a group becoming a group administrator.
     *
     * @param groupDescription
     * @param groupCreator
     * @param groupsRepository
     * @return
     */

    public boolean createGroup(String groupDescription, Person groupCreator, GroupsRepository groupsRepository) {
        return groupsRepository.createGroup(groupDescription, groupCreator);
    }
}
