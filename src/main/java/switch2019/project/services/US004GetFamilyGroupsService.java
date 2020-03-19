package switch2019.project.services;

import switch2019.project.model.group.Group;
import switch2019.project.repository.GroupsRepository;

import java.util.Set;

public class US004GetFamilyGroupsService {

    private GroupsRepository groupsRepository;

    public US004GetFamilyGroupsService(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    /**
     * US004 -  As system manager I want to know which groups are families
     *
     * @return set of families
     */

    public Set<Group> getFamilyGroups (){
        return groupsRepository.returnOnlyFamilies() ;
    }
}
