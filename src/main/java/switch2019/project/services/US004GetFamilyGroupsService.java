package switch2019.project.services;

import switch2019.project.model.group.Group;
import switch2019.project.repository.GroupsRepository;

import java.util.Set;

public class US004GetFamilyGroupsService {

    /**
     * US004 -  As system manager I want to know which groups are families
     * @param groupsRepository
     * @return set of families
     */

    public Set<Group> getFamilyGroups (GroupsRepository groupsRepository){
        return groupsRepository.returnOnlyFamilies() ;
    }
}
