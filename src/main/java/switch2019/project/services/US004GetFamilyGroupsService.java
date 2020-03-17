package switch2019.project.services;

import switch2019.project.model.group.Group;
import switch2019.project.repository.GroupsRepository;

import java.util.Set;

public class US004GetFamilyGroupsService {
    public Set<Group> getFamilyGroups (GroupsRepository groupsRepository){
        return groupsRepository.returnOnlyFamilies() ;
    }
}
