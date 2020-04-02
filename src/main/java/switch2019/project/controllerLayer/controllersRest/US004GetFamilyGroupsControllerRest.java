package switch2019.project.controllerLayer.controllersRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import switch2019.project.DTO.GroupDTO;
import switch2019.project.applicationLayer.US004GetFamilyGroupsService;
import java.util.Set;

@RestController
public class US004GetFamilyGroupsControllerRest {

    @Autowired
    US004GetFamilyGroupsService service;

    @GetMapping("/getFamilyGroups")
    public ResponseEntity <Object> getFamilyGroups() {

        Set<GroupDTO> result = service.getFamilyGroups();

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
