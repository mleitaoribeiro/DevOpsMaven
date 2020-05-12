package switch2019.project.controllerLayer.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.DTO.serializationDTO.GroupDTO;
import switch2019.project.applicationLayer.US004GetFamilyGroupsService;

import java.util.List;
import java.util.Set;

@Component
public class US004GetFamilyGroupsController {

    @Autowired
    private US004GetFamilyGroupsService service;
    /**
     * US004 - As system manager I want to know which groups are families
     *
     * @return family groups
     */

    public List<GroupDTO> getFamilyGroups() {
        return service.getFamilyGroups();
    }
}

