package switch2019.project.controllerLayer;

import switch2019.project.DTO.AccountDTO;
import switch2019.project.DTO.CreateGroupAccountDTO;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;


public class US007CreateGroupAccountController {


    private US007CreateGroupAccountService service;


    public US007CreateGroupAccountController (US007CreateGroupAccountService service) {
        this.service = service;
    }


    /**
     * US007 - As a group Admin, I want to create a group account
     *
     * @param personEmail
     * @param groupDescription
     * @param accountDenomination
     * @param accountDescription
     * @return accountDTO
     */

    public AccountDTO createGroupAccount(String personEmail, String groupDescription,
                                         String accountDenomination, String accountDescription) {

        CreateGroupAccountDTO createGroupAccountDTO = AccountDTOAssembler.createGroupAccountDTOFromPrimitiveTypes(
                personEmail, groupDescription, accountDenomination, accountDescription);

        return service.createGroupAccount(createGroupAccountDTO);
    }

}
