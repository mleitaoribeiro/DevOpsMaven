package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.List;

public interface GroupRepository extends Repository {

    /**
     * As a user I want to create a group becoming a group administrator(US02.1)
     *
     * @param groupDescription for groupDescription
     * @param groupCreator for groupCreator
     * @return - Group
     */
    Group createGroup(Description groupDescription, PersonID groupCreator);


    /**
     * Method used to find a specific group by its Description
     *
     * @param groupDescription - groupDescription
     * @return - Group
     */
    Group findGroupByDescription(Description groupDescription);

    /**
     * Method to return the group corespondent to the given GroupID
     *
     * @param groupID - groupID
     * @return - Group
     */
    Group getByID(ID groupID);


    /**
     * Method to validate if the group t is in the groups Repository
     *
     * @param groupID - groupID
     * @return - Boolean
     */
    boolean isIDOnRepository(ID groupID);

    /**
     * Method to return all groups in the repository
     *
     * @return - List of Groups
     */
    List<Group> getAllGroups();

    /**
     * Method to add a member to a Group
     *
     * @param group - group
     * @param personID - personID
     * @return - true if member added
     */
    boolean addMember(Group group, String personID);

    /**
     * Method to add an admin to a Group
     *
     * @param group - group
     * @param personID - personID
     * @return - true if admin set
     */
    boolean setAdmin(Group group, String personID);
}
