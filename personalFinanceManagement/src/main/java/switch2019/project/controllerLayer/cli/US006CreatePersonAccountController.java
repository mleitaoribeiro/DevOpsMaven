package switch2019.project.controllerLayer.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonAccountDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;

@Controller
public class US006CreatePersonAccountController {

    @Autowired
    private US006CreatePersonAccountService service;

    public AccountDTO createPersonAccount(String personEmail, String accountDenomination, String accountDescription) {

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes(personEmail,
                accountDenomination, accountDescription);

        return service.createPersonAccount(createPersonAccountDTO);

    }
}

