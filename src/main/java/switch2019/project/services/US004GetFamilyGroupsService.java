package switch2019.project.services;

import switch2019.project.model.group.Group;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;

import java.util.ArrayList;
import java.util.Set;

public class US004GetFamilyGroupsService {
    public Set<Group> getFamilyGroups (){
        GroupsRepository groupsRepository =new GroupsRepository();
        return groupsRepository.returnOnlyFamilies() ;
    }
}
