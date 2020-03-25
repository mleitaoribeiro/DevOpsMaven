package switch2019.project.applicationLayer;

import switch2019.project.DTO.CreatePersonAccountDTO;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.infrastructure.repositories.AccountRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

import java.util.Optional;

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
     *
     * @param accountDTO
     */
    public Optional<Account> createPersonAccount(CreatePersonAccountDTO accountDTO) {

        PersonID personID = personRepository.findPersonByEmail(new Email(accountDTO.getPersonEmail())).getID();

        Denomination accountDenomination = new Denomination(accountDTO.getAccountDenomination());
        Description accountDescription = new Description(accountDTO.getAccountDescription());


        return Optional.of(accountRepository.createAccount(accountDenomination, accountDescription, personID));

    }

}
