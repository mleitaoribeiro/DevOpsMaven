package switch2019.project.dataModel.dataAssemblers;

import switch2019.project.dataModel.entities.AdminsJpa;
import switch2019.project.dataModel.entities.GroupJpa;
import switch2019.project.dataModel.entities.MembersJpa;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupDomainDataAssembler {

    private GroupDomainDataAssembler() {
    }

    public static GroupJpa toData(Group group) {

        PersonID personID = group.getGroupCreator();

        String groupCreator = personID.toString();

        return new GroupJpa(group.getID().getDescription(), groupCreator, group.getStartingDate());
    }

    public static Group toDomain(GroupJpa groupJPA, List<MembersJpa> membersJpa, List<AdminsJpa> adminsJpa) {

        Description groupDescription = new Description(groupJPA.getId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        System.out.println(groupJPA.getCreationDate());
        LocalDate localDateCreationDate = LocalDate.parse(groupJPA.getCreationDate(), formatter);
        DateAndTime creationDate = new DateAndTime(localDateCreationDate.getYear(), localDateCreationDate.getMonthValue(),
                localDateCreationDate.getDayOfMonth());

        PersonID personID = new PersonID(new Email(groupJPA.getGroupCreator()));

        Set<PersonID> members = new HashSet<>();
        for(MembersJpa member : membersJpa) {
            members.add(new PersonID(new Email(member.getPersonID())));
        }

        Set<PersonID> admins = new HashSet<>();
        for(AdminsJpa admin : adminsJpa) {
            admins.add(new PersonID(new Email(admin.getPersonID())));
        }

        return new Group(groupDescription, personID, creationDate, members, admins);
    }
}

