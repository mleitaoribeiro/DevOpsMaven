package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.ledger.Periodicity;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.MonetaryValue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface GroupRepository extends Repository {

    /**
     * As a user I want to create a group becoming a group administrator(US02.1)
     * @param groupDescription
     * @param groupCreator
     */
    Group createGroup(Description groupDescription, Person groupCreator);


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


    /**
     * Method to return Only Families
     */
    Set<Group> returnOnlyFamilies();

    /**
     * Method to check if a person is admin on a group
     * @param groupID
     * @param person
     */
    boolean checkIfAPersonIsAdminInAGivenGroup(GroupID groupID, Person person);

}
