package switch2019.project.controllers;

import switch2019.project.model.group.Group;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.Description;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.services.US004GetFamilyGroupsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class US004GetFamilyGroupsController {

   private US004GetFamilyGroupsService service;

   public US004GetFamilyGroupsController(US004GetFamilyGroupsService service){
       this.service =service;
   }

    /**
     * US004 - As system manager I want to know which groups are families
     * @param service
     * @param groupsRepository
     * @return
     */
    public Set<Group> getFamilyGroups(US004GetFamilyGroupsService service, GroupsRepository groupsRepository) {
        return service.getFamilyGroups(groupsRepository) ;
    }

}
