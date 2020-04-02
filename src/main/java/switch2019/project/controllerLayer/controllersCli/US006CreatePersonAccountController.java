package switch2019.project.controllerLayer.controllersCli;

import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.DTO.SerializationDTO.CreatePersonAccountDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;

public class US006CreatePersonAccountController {

    private US006CreatePersonAccountService service;

    public US006CreatePersonAccountController(US006CreatePersonAccountService service) {
        this.service = service;
    }


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

