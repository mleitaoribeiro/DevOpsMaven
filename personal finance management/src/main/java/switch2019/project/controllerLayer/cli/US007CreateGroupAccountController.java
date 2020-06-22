package switch2019.project.controllerLayer.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupAccountDTO;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;

@Controller
public class US007CreateGroupAccountController {

    @Autowired
    private US007CreateGroupAccountService service;

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
