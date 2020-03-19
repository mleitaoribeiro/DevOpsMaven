package switch2019.project.controllers;

import switch2019.project.services.US004GetFamilyGroupsService;

import java.util.Set;

public class US004GetFamilyGroupsController {

   private US004GetFamilyGroupsService service;

   public US004GetFamilyGroupsController(US004GetFamilyGroupsService service){
       this.service =service;
   }

    /**
     * US004 - As system manager I want to know which groups are families
     *
     * @return family groups
     */
    public Set<String> getFamilyGroups() {
        return service.getFamilyGroups() ;
    }

}
