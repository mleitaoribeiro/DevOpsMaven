package switch2019.project.controllerLayer;

import switch2019.project.DTO.AccountDTO;
import switch2019.project.DTO.CreatePersonAccountDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;

public class US006CreatePersonAccountController {

    private US006CreatePersonAccountService service;

    public US006CreatePersonAccountController(US006CreatePersonAccountService service) {
        this.service = service;
    }


    /**
     * User Story 6
     * As a user, I want to create a account
     *
     * @param createPersonAccountDTO
     * @return AccountDTO
     */
    public AccountDTO createPersonAccount(CreatePersonAccountDTO createPersonAccountDTO) {

        return service.createPersonAccount(createPersonAccountDTO);

    }
}

