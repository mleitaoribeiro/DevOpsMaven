package switch2019.project.domain.repositories;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.Set;

public interface GroupRepository extends Repository {

    /**
     * As a user I want to create a group becoming a group administrator(US02.1)
     * @param groupDescription
     * @param groupCreator
     */
    Group createGroup(Description groupDescription, PersonID groupCreator);


    /**
     * Method to add group to the Repository
     * @param group
     */
    boolean addGroupToRepository(Group group);


    /**
     * Method used to find a specific group by its Description
     * @param groupDescription
     */
    Group findGroupByDescription(Description groupDescription);

    /**
     * Method to return the group corespondent to the given GroupID
     * @param groupID
     */

    Group getByID (ID groupID);


    /**
     * Method to validate if the group t is in the groups Repository
     * @param groupID
     */
    boolean isIDOnRepository(ID groupID);



}
