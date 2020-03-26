package switch2019.project.applicationLayer;

import switch2019.project.DTO.AccountDTO;
import switch2019.project.DTO.CreatePersonAccountDTO;
import switch2019.project.assemblers.AccountDTOAssembler;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.infrastructure.repositories.AccountRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

public class US006CreatePersonAccountService {

    private PersonRepository personRepository;
    private AccountRepository accountRepository;

    public US006CreatePersonAccountService(PersonRepository personRepository, AccountRepository accountRepository) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
    }


    /**
     * User Story 6
     * As a user, I want to create a account
     * @param accountDTO
     * @return AccountDTO
     */
/*
    public AccountDTO createPersonAccount(CreatePersonAccountDTO accountDTO) {

        PersonID personID = personRepository.findPersonByEmail(new Email(accountDTO.getPersonEmail())).getID();

        Denomination denomination = new Denomination(accountDTO.getAccountDenomination());
        Description description = new Description(accountDTO.getAccountDescription());

        Account account = accountRepository.createAccount(denomination, description, personID);

        return AccountDTOAssembler.createAccountDTO(account.getOwnerID(), denomination, description);

    }
*/
}
