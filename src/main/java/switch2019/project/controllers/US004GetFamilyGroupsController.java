package switch2019.project.controllers;

import switch2019.project.model.group.Group;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.Description;
import switch2019.project.services.US004GetFamilyGroupsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class US004GetFamilyGroupsController {

    //ALTERAR
    public Set<Group> getFamilyGroups(US004GetFamilyGroupsService service) {
        return service.getFamilyGroups() ;
    }

}
