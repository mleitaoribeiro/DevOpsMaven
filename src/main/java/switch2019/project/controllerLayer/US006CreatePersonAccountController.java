package switch2019.project.controllerLayer;

import switch2019.project.DTO.AccountDTO;
import switch2019.project.DTO.CreatePersonAccountDTO;
import switch2019.project.applicationLayer.US006CreatePersonAccountService;
import switch2019.project.assemblers.AccountDTOAssembler;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;

import java.util.Optional;


public class US006CreatePersonAccountController {
/*
    private US006CreatePersonAccountService service;

    public US006CreatePersonAccountController(US006CreatePersonAccountService service) {
        this.service = service;
    }


    /**
     * User Story 6
     * As a user, I want to create a account
     *
     * @param accountDTO
     */
/*
    public Optional<AccountDTO> createPersonAccount(CreatePersonAccountDTO accountDTO) {

        Optional<Account> ret = service.createPersonAccount(accountDTO);
        if (ret.isPresent()) {
            Account account = ret.get();

            Denomination denomination = new Denomination(account.getID().getDenomination());
            Description description = new Description(account.descriptionToString());

            AccountDTO dto = AccountDTOAssembler.createAccountDTO(account.getOwnerID(), denomination, description);
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }

    }


    */
}

