package switch2019.project.controllerLayer.controllersCli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.DTO.ServiceDTO.CreatePersonAccountDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;

@Controller
public class US006CreatePersonAccountController {

    @Autowired
    private US006CreatePersonAccountService service;

    /**
     * User Story 6
     * As a user, I want to create a account
     *
     * @param personEmail
     * @param accountDenomination
     * @param accountDescription
     * @return AccountDTO
     */

    public AccountDTO createPersonAccount(String personEmail, String accountDenomination, String accountDescription) {

        CreatePersonAccountDTO createPersonAccountDTO = AccountDTOAssembler.createPersonAccountDTOFromPrimitiveTypes(personEmail,
                accountDenomination, accountDescription);

        return service.createPersonAccount(createPersonAccountDTO);

    }
}

