package switch2019.project.controllerLayer;

import switch2019.project.DTO.AccountDTO;
import switch2019.project.DTO.CreateGroupAccountDTO;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;


public class US007CreateGroupAccountController {


    private US007CreateGroupAccountService service;


    public US007CreateGroupAccountController (US007CreateGroupAccountService service) {
        this.service = service;
    }


    /**
     * US007 - As a group Admin, I want to create a group account
     *
     * @param createGroupAccountDTO
     * @param
     * @param
     * @return
     */

    public AccountDTO createGroupAccount(CreateGroupAccountDTO createGroupAccountDTO) {
        return service.createGroupAccount(createGroupAccountDTO);
    }

}
