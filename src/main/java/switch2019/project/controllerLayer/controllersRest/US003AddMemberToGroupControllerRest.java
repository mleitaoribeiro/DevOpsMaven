package switch2019.project.controllerLayer.controllersRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import switch2019.project.controllerLayer.controllersCli.US003AddMemberToGroupController;

@RestController
public class US003AddMemberToGroupControllerRest {

    @Autowired
    US003AddMemberToGroupController controller;

    @GetMapping("/addMemberToGroup")
    public String addMemberToGroup(@RequestParam(value = "personEmail") String personEmail,
                                   @RequestParam(value = "groupDescription") String groupDescription){

        boolean memberAdded = controller.addMemberToGroup(personEmail, groupDescription);

        return String.format("Result %b!", memberAdded);
    }
}