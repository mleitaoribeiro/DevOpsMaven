package switch2019.project.dataModel.dataAssemblers;

import switch2019.project.dataModel.entities.GroupJpa;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;

public class GroupDomainDataAssembler {

    public GroupJpa toData(Group group) {

        PersonID personID=group.getGroupCreator();

        String groupCreator= personID.toString();

        return new GroupJpa(group.getID().getDescription(),groupCreator, group.getStartingDate());
    }

    public Group toDomain(GroupJpa groupJPA) {

        Description groupDescription = new Description(groupJPA.getId());

        String groupCreator = groupJPA.getGroupCreator();

        PersonID personID = new PersonID(new Email(groupCreator));

        return new Group(groupDescription, personID);
    }
}

