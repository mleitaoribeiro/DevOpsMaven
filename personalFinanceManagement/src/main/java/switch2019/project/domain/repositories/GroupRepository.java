package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.List;

public interface GroupRepository extends Repository {

    Group createGroup(Description groupDescription, PersonID groupCreator);

    Group findGroupByDescription(Description groupDescription);

    Group getByID(ID groupID);

    boolean isIDOnRepository(ID groupID);

    List<Group> getAllGroups();

    boolean addMember(Group group, String personID);

    boolean setAdmin(Group group, String personID);
}
