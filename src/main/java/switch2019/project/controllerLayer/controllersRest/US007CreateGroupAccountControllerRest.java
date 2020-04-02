package switch2019.project.controllerLayer.controllersRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import switch2019.project.DTO.AccountDTO;
import switch2019.project.DTO.CreateGroupAccountDTO;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;


@RestController
public class US007CreateGroupAccountControllerRest {

    @Autowired
    US007CreateGroupAccountService service;

    @GetMapping("/addGroupAccount")
    public ResponseEntity<AccountDTO> addGroupAccount (@RequestParam(value = "personEmail" ) String personEmail,
                                   @RequestParam(value = "groupDescription") String groupDescription,
                                   @RequestParam(value = "accountDenomination") String accountDenomination,
                                   @RequestParam(value = "accountDescription") String accountDescription) {

        //Arrange the entry dto with the given strings:
        CreateGroupAccountDTO dto = new CreateGroupAccountDTO(personEmail, groupDescription, accountDenomination, accountDescription);

        AccountDTO result = service.createGroupAccount(dto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
