package switch2019.project.controllerLayer;

import switch2019.project.DTO.AccountDTO;
import switch2019.project.DTO.CreateGroupAccountDTO;
import switch2019.project.applicationLayer.US007CreateGroupAccountService;
import switch2019.project.domain.domainEntities.account.Account;

import java.util.Optional;

public class US007CreateGroupAccountController {


    private US007CreateGroupAccountService service;


    public US007CreateGroupAccountController (US007CreateGroupAccountService service) {
        this.service = service;
    }


    /**
     * US007 - As a group Admin, I want to create a group account
     *
     * @param accountDTO
     * @param
     * @param
     * @return
     */

    public Optional<AccountDTO> createGroupAccount (CreateGroupAccountDTO accountDTO) {

        Optional <Account> ret = service.createGroupAccount(accountDTO);
        if (ret.isPresent()){
            Account account = ret.get();
            AccountDTO dto = new AccountDTO(account.getOwnerID().toString(), account.denominationToString(), account.getDescription());
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }


    }

}
